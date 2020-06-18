package com.demo.service;

import com.demo.model.ExcelStyle;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.util.AreaReference;
import org.apache.poi.ss.util.CellReference;
import org.apache.poi.xssf.usermodel.*;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTTable;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTTableStyleInfo;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.*;

@Service
public class ReporteService {
    private XSSFWorkbook workbook;
    private XSSFSheet sheet;
    private int numRow;
    private ExcelStyle excelStyle;
    private  List<String> listaColumnas;
    private  List<String> listaColumnasTabla5;
    private  List<String> listaColumnasTabla2;
    private  List<String> listaFunciones;
    private  List<String> listaFuncionesTabla2;
    private  List<String> listaColumnasTabla3;
    private  List<String> listaColumnasTabla4;
    private  List<String> listaColumnasTabla6;
    private  List<String> listaColumnasTabla7;
    private int numDias;
    private int numRepeticiones;
    private int numMediciones;
    private int numMuestra;
    private int valorReferencia;
    private final int PROMEDIO=0;
    private final int DESVIACION_ESTANDAR_PROMEDIO=1;
    private final int DESVIACION_ESTANDAR=2;
    private final int VALOR_MAX=3;
    private final int VALOR_MIN=4;
    private final int RANGO_R=5;
    private final int RANGO_PROMEDIO=6;
    private final int PROMEDIO_RANGOS_PROMEDIO=7;
    private final int INDICADOR_VARIACION=8;
    private final int INDICE_RELATIVO=9;
    private final int REPETIBILIDAD_ANALISTA=10;
    private final int PROMEDIO_TODAS_DIMENSIONES=11;
    private final int PROMEDIO_RANGOS_PROMEDIO_ANALISTAS=0;
    private final int REPETIBILIDAD=1;
    private List<Double>rangosPromedioAnalistas;
    private List<Double>promedioMedicionesListaAnalistas;
    private List<Double>desviacionEstandarPromedioListaAnalistas;
    private List<Double>listaSesgoAnalistas;
    private List<Double>repPromedio;
    private List<Double>diferenciaSextaTabla;

    public ResponseEntity<?> generarReporte(Map<String, String> request) throws IOException {

        numRow=0;
        workbook=new XSSFWorkbook();
        sheet= workbook.createSheet("Reporte");
        excelStyle=new ExcelStyle(workbook,sheet);

        numDias=Integer.parseInt(request.get("numDias"));
        numRepeticiones=Integer.parseInt(request.get("numRepeticiones"));
        numMediciones=Integer.parseInt(request.get("numMediciones"));
        numMuestra=Integer.parseInt(request.get("numMuestra"));
        valorReferencia=Integer.parseInt(request.get("valorReferencia"));

        //Eliminar mierda que estorba
        removeElementFromPrincipalMap(request,"numDias");
        removeElementFromPrincipalMap(request,"numRepeticiones");
        removeElementFromPrincipalMap(request,"numMediciones");
        removeElementFromPrincipalMap(request,"numMuestra");
        removeElementFromPrincipalMap(request,"valorReferencia");

        List<String>analistas=getAnalistas(request);

        //numAnalista_numDia_numMuestra_numRepeticion
        Map<Integer,Map<Integer,Map<Integer,Map<Integer,Double>>>>dataMap=new HashMap<>();
        removeElementFromPrincipalMap(request,"nombreAnalista");
        buildMap(dataMap,request);

        return generarExcel(dataMap,analistas);
    }

    private void removeElementFromPrincipalMap(Map<String,String>map,String cadena){
        map.keySet().removeIf(key -> key.contains(cadena));
    }

    private ResponseEntity<?> generarExcel(Map<Integer,Map<Integer,Map<Integer,Map<Integer,Double>>>>dataMap,List<String>analistas) throws IOException {
        listaColumnas=new ArrayList<>();
        listaFunciones=new ArrayList<>();
        listaColumnasTabla2=new ArrayList<>();
        listaFuncionesTabla2=new ArrayList<>();
        rangosPromedioAnalistas=new ArrayList<>();
        promedioMedicionesListaAnalistas=new ArrayList<>();
        listaColumnasTabla3=new ArrayList<>();
        listaColumnasTabla4=new ArrayList<>();
        desviacionEstandarPromedioListaAnalistas=new ArrayList<>();
        listaColumnasTabla5=new ArrayList<>();
        listaSesgoAnalistas=new ArrayList<>();
        listaColumnasTabla7 =new ArrayList<>();
        listaColumnasTabla6=new ArrayList<>();

        //dia, repeticion 1, 2, n...
        this.setListaColumnas();
        this.setListaFunciones();
        this.setListaFuncionesTabla2();
        this.setListaColumnasTabla2();
        this.setListaColumnasTabla3();
        this.setListaColumnasTabla4();
        this.setListaColumnasTabla5();
        this.setListaColumnasTabla6();
        this.setListaColumnasTabla7();
        int sizeListaFunciones=listaFunciones.size();

        numRow++;
        this.addRow("Caracterización e Investigación en Materiales",excelStyle.titleStyle());
        this.addRow("Muestra: "+numMuestra,excelStyle.subjectStyle());
        numRow++;
        int analistaCount=1;
        int count=0;
        //numAnalista_numDia_numMuestra_numRepeticion
        for (Map.Entry<Integer,Map<Integer,Map<Integer,Map<Integer,Double>>>>pair : dataMap.entrySet()) {
            this.addRow("Analista: "+analistas.get(count),excelStyle.subjectStyle());
            count++;
            numRow--;
            //2 constante, + numero de mediciones + el numRow + Constante de num de operaciones por tabla
            createTable(2+numMediciones+numRow+sizeListaFunciones,listaColumnas.size(),analistaCount++,pair.getValue(),count);
            numRow=numRow+numMediciones+5+sizeListaFunciones;
        }
        numRow--;
        //numRow+2 constante + numero de filas
//        *Importante: numRow +2 siempre*
        createTable2(numRow+2+5+count, count,dataMap);
        numRow=2+5+count+numRow+2;
        this.addRow("Prueba de exactitud (Veracidad): ",excelStyle.subjectStyle());
        createTable3(numRow+2+count,count);
        numRow=count+numRow+3;
        this.addRow("Precisión",excelStyle.subjectStyle());
        //num 2 de más, 1 el que dicta numero de filas+ num row
        createTable4(numRow+2+count,count);
        numRow=count+numRow+3;
        this.addRow("Sesgo",excelStyle.subjectStyle());
        this.addRow("Valor nominal de referencia: "+valorReferencia,excelStyle.subjectStyle());
        analistaCount=1;
        count=0;
        numRow++;

        for (Map.Entry<Integer,Map<Integer,Map<Integer,Map<Integer,Double>>>>pair : dataMap.entrySet()) {
            this.addRow("Analista: "+analistas.get(count),excelStyle.subjectStyle());
            count++;
            numRow--;
            //2 constante, + numero de mediciones + el numRow + Constante de num de operaciones por tabla
            createTable5(2+numMediciones+numRow,listaColumnasTabla5.size(),analistaCount++,pair.getValue());
            numRow=numRow+numMediciones+5;
        }
        numRow=numRow-2;
        createTable6(numRow+2+numMediciones,count,dataMap);
        numRow=numRow+numMediciones+2;
        this.addRow("Promedio de promedios: "+getPromedio(repPromedio),excelStyle.boldStyle());
        numRow=numRow+2;
        this.addRow("Incertidumbre",excelStyle.subjectStyle());
        createTable7(numRow+2+1,count);
        return this.generateFile();
    }

    private void setListaColumnasTabla2(){
        listaColumnasTabla2.add("Título");
        listaColumnasTabla2.add("Detalles");
    }

    private void setListaColumnasTabla3(){
        listaColumnasTabla3.add("No. Analista");
        listaColumnasTabla3.add("Valor nominal referencia");
        listaColumnasTabla3.add("Valor de medida");
        listaColumnasTabla3.add("Error de medida (mm)");
        listaColumnasTabla3.add("Veracidad Analista");
    }

    private void setListaColumnasTabla4(){
        listaColumnasTabla4.add("No. Analista");
        listaColumnasTabla4.add("Coeficiente de variabilidad CV%");
    }
    private void setListaColumnasTabla6(){
        listaColumnasTabla6.add("Medición");
        listaColumnasTabla6.add("Promedio");
        listaColumnasTabla6.add("Diferencia");
    }


    private void setListaColumnasTabla7(){
        listaColumnasTabla7.add("Incertidumbre del método");
        listaColumnasTabla7.add("Incertidumbre del equipo");
        listaColumnasTabla7.add("Incertidumbre del operador ");
    }

    private void setListaColumnas(){
        for(int i=0; i<numDias; i++){
            this.listaColumnas.add("Día "+(i+1));
            for(int j=0; j<numRepeticiones; j++){
                this.listaColumnas.add( "Dia "+(i+1)+"-Repetición "+(j+1));
            }
        }
    }

    private void setListaColumnasTabla5(){
        for(int i=0; i<numDias; i++){
            this.listaColumnasTabla5.add("Día "+(i+1));
            for(int j=0; j<numRepeticiones; j++){
                this.listaColumnasTabla5.add( "Dia "+(i+1)+"-Repetición "+(j+1));
                this.listaColumnasTabla5.add( "Dia "+(i+1)+"-Repetición "+(j+1)+"-Sesgo ");
            }
        }
    }

    private void createTable6(int numRows, int numAnalistas,Map<Integer,Map<Integer,Map<Integer,Map<Integer,Double>>>>dataMap) {
        AreaReference reference = workbook.getCreationHelper().createAreaReference(new CellReference(numRow + 1, 1), new CellReference(numRows - 1, 3));
        XSSFTable table = sheet.createTable(reference);

        CTTable cttable = table.getCTTable();
        cttable.setDisplayName("SextaTabla");
        cttable.setId(numAnalistas + 30);
        cttable.setName("Sexta tabla");
        cttable.setTotalsRowShown(false);

        CTTableStyleInfo styleInfo = cttable.addNewTableStyleInfo();
        styleInfo.setName("TableStyleMedium2");
        styleInfo.setShowColumnStripes(false);
        styleInfo.setShowRowStripes(true);

        int firtsTableRow = numRow + 1;
        int numMedicion=-1;
        List<Double>repeticionesPorMedicion=new ArrayList<>();
        repPromedio=new ArrayList<>();
        diferenciaSextaTabla=new ArrayList<>();
        double rep;
        double diferencia;

        for (int r = firtsTableRow; r < numRows; r++) {
            numMedicion=numMedicion+1;
            XSSFRow row = sheet.createRow(r);
            for (int c = 1; c < 3 + 1; c++) {
                XSSFCell cell = row.createCell(c);
                cell.setCellStyle(excelStyle.tableCellStyle());
                if (c == 1) {
                    sheet.setColumnWidth(c, 10000); //Set column width, you'll probably want to tweak the second int
                } else {
                    sheet.setColumnWidth(c, 7000); //Set column width, you'll probably want to tweak the second int
                }
                if (r == firtsTableRow) { //first row is for column headers
                    cell.setCellValue(listaColumnasTabla6.get(c - 1)); //content **must** be here for table column names
                }else{
                    switch (c){
                        case 1:
                            cell.setCellValue("Medición "+numMedicion);
                            break;
                        case 2:
                            repeticionesPorMedicion.clear();
                            getAllRepeticionesPorMedicion(repeticionesPorMedicion,dataMap,numMedicion);
                            rep=getPromedio(repeticionesPorMedicion);
                            repPromedio.add(rep);
                            cell.setCellValue(rep);
                            break;
                        case 3:
                            diferencia=valorReferencia-repPromedio.get(numMedicion-1);
                            cell.setCellValue(diferencia);
                            diferenciaSextaTabla.add(diferencia);
                            break;
                    }
                }
            }
        }
    }

    private void getAllRepeticionesPorMedicion(List<Double>todasRepeticionesPorMedicion,Map<Integer,Map<Integer,Map<Integer,Map<Integer,Double>>>>dataMap, int numMedicion){
        //numAnalista_numDia_numMedicion_numRepeticion
        Map<Integer,Map<Integer,Map<Integer,Double>>> diaTemp;
        Map<Integer,Map<Integer,Double>> medicionTemp;
        Map<Integer,Double> repeticionTemp;
        for (Map.Entry<Integer,Map<Integer,Map<Integer,Map<Integer,Double>>>>pair : dataMap.entrySet()) {
            diaTemp=pair.getValue();
            for (Map.Entry<Integer,Map<Integer,Map<Integer,Double>>>dia : diaTemp.entrySet()) {
                medicionTemp=dia.getValue();
                for (Map.Entry<Integer,Map<Integer,Double>> medicion : medicionTemp.entrySet()) {
                    if(medicion.getKey()==numMedicion){
                        repeticionTemp=medicion.getValue();
                        for (Map.Entry<Integer,Double> repeticion : repeticionTemp.entrySet()) {
                            todasRepeticionesPorMedicion.add(repeticion.getValue());
                        }
                    }
                }
            }
        }
    }

    private void createTable7(int numRows, int numAnalistas) {
        AreaReference reference = workbook.getCreationHelper().createAreaReference(new CellReference(numRow + 1, 1), new CellReference(numRows - 1, 3));
        XSSFTable table = sheet.createTable(reference);

        CTTable cttable = table.getCTTable();
        cttable.setDisplayName("SeptimaTabla");
        cttable.setId(numAnalistas + 20);
        cttable.setName("Septima tabla");
        cttable.setTotalsRowShown(false);

        CTTableStyleInfo styleInfo = cttable.addNewTableStyleInfo();
        styleInfo.setName("TableStyleMedium2");
        styleInfo.setShowColumnStripes(false);
        styleInfo.setShowRowStripes(true);

        int firtsTableRow = numRow + 1;

        for (int r = firtsTableRow; r < numRows; r++) {
            XSSFRow row = sheet.createRow(r);
            for (int c = 1; c < 3 + 1; c++) {
                XSSFCell cell = row.createCell(c);
                cell.setCellStyle(excelStyle.tableCellStyle());
                if (c == 1) {
                    sheet.setColumnWidth(c, 10000); //Set column width, you'll probably want to tweak the second int
                } else {
                    sheet.setColumnWidth(c, 7000); //Set column width, you'll probably want to tweak the second int
                }
                if (r == firtsTableRow) { //first row is for column headers
                    cell.setCellValue(listaColumnasTabla7.get(c - 1)); //content **must** be here for table column names
                }else{
                    switch (c){
                        case 1:
                            cell.setCellValue(desviacionEstandarPromedio(listaSesgoAnalistas));
                            break;
                        case 2:
                            cell.setCellValue("0.0015");
                            break;
                        case 3:
                            cell.setCellValue(desviacionEstandarPromedio(diferenciaSextaTabla));
                            break;
                    }
                }
            }
        }
    }

    private void setListaFuncionesTabla2(){
        //0: PROMEDIO_RANGOS_PROMEDIO_ANALISTAS
        listaFuncionesTabla2.add("Promedio de los rangos promedio");
        //1: REPETIBILIDAD
        listaFuncionesTabla2.add("%Repetibilidad");
    }

    private void setListaFunciones(){
        //0: PROMEDIO
        listaFunciones.add("Promedio");
        //1: Desviación estándar promedio
        listaFunciones.add("Desviación estándar promedio");
        //2: Desviación estándar
        listaFunciones.add("Desviación estándar");
        //3: Valor max
        listaFunciones.add("Valor max");
        //4: Valor min
        listaFunciones.add("Valor min");
        //5: RANGO_R
        listaFunciones.add("Rango (R)\nR=Xmax-Xmin");
        //6: RANGO_PROMEDIO
        listaFunciones.add("Rango promedio");
        //7: PROMEDIO_RANGOS_PROMEDIO
        listaFunciones.add("Promedio de los rangos promedio");
        //8: INDICADOR_VARIACION
        listaFunciones.add("Indicador de variación");
        //9: INDICE_RELATIVO
        listaFunciones.add("índice relativo");
        //10: REPETIBILIDAD_ANALISTA
        listaFunciones.add("%Repetibilidad Analista");
        //11: PROMEDIO_TODAS_DIMENSIONES
        listaFunciones.add("Promedio de todas las dimensiones");
    }

    private void createTable4(int numRows, int numAnalistas) {
        AreaReference reference = workbook.getCreationHelper().createAreaReference(new CellReference(numRow + 1, 1), new CellReference(numRows - 1, 2));
        XSSFTable table = sheet.createTable(reference);

        CTTable cttable = table.getCTTable();
        cttable.setDisplayName("CuartaTabla");
        cttable.setId(numAnalistas + 3);
        cttable.setName("Cuarta tabla");
        cttable.setTotalsRowShown(false);

        CTTableStyleInfo styleInfo = cttable.addNewTableStyleInfo();
        styleInfo.setName("TableStyleMedium7");
        styleInfo.setShowColumnStripes(false);
        styleInfo.setShowRowStripes(true);

        int firtsTableRow = numRow + 1;
        int numAnalistaAux = 1;
        int numAnalistaAux2 = 0;

        for (int r = firtsTableRow; r < numRows; r++) {
            XSSFRow row = sheet.createRow(r);
            for (int c = 1; c < 2 + 1; c++) {
                XSSFCell cell = row.createCell(c);
                cell.setCellStyle(excelStyle.tableCellStyle());
                if (c == 1) {
                    sheet.setColumnWidth(c, 10000); //Set column width, you'll probably want to tweak the second int
                } else {
                    sheet.setColumnWidth(c, 7000); //Set column width, you'll probably want to tweak the second int
                }
                if (r == firtsTableRow) { //first row is for column headers
                    cell.setCellValue(listaColumnasTabla4.get(c - 1)); //content **must** be here for table column names
                }else{
                    if(c==1){
                        cell.setCellValue("Analista "+numAnalistaAux);
                        numAnalistaAux2=numAnalistaAux;
                        cell.setCellStyle(excelStyle.boldStyle());
                        numAnalistaAux++;
                    }else{
                        cell.setCellValue(
                                (desviacionEstandarPromedioListaAnalistas.get(numAnalistaAux2-1)/promedioMedicionesListaAnalistas.get(numAnalistaAux2-1))*100
                        );
                    }
                }
            }
        }
    }

    private void createTable3(int numRows,int numAnalistas){
        AreaReference reference=workbook.getCreationHelper().createAreaReference(new CellReference(numRow+1,1),new CellReference(numRows-1, 5));
        XSSFTable table = sheet.createTable(reference);

        CTTable cttable = table.getCTTable();
        cttable.setDisplayName("TercerTabla");
        cttable.setId(numAnalistas+2);
        cttable.setName("Tercer tabla");
        cttable.setTotalsRowShown(false);

        CTTableStyleInfo styleInfo = cttable.addNewTableStyleInfo();
        styleInfo.setName("TableStyleMedium7");
        styleInfo.setShowColumnStripes(false);
        styleInfo.setShowRowStripes(true);

        int firtsTableRow=numRow+1;
        int numAnalistaAux=1;
        int numAnalistaAux2=0;

        for (int r = firtsTableRow; r < numRows; r++) {
            XSSFRow row = sheet.createRow(r);
            for(int c = 1; c < 5 +1; c++) {
                XSSFCell cell = row.createCell(c);
                cell.setCellStyle(excelStyle.tableCellStyle());
                if(c==1){
                    sheet.setColumnWidth(c, 10000); //Set column width, you'll probably want to tweak the second int
                }else{
                    sheet.setColumnWidth(c, 7000); //Set column width, you'll probably want to tweak the second int
                }
                if(r == firtsTableRow) { //first row is for column headers
                    cell.setCellValue(listaColumnasTabla3.get(c-1)); //content **must** be here for table column names
                }else{
                    if(c==1){
                        cell.setCellValue("Analista "+numAnalistaAux);
                        numAnalistaAux2=numAnalistaAux;
                        cell.setCellStyle(excelStyle.boldStyle());
                        numAnalistaAux++;
                    }else{
                        //valorReferencia
                        //promedioMedicionesListaAnalistas
                        switch (c){
                            case 2: //valor referencia
                                cell.setCellValue(valorReferencia);
                                break;
                            case 3: //valor medida
                                cell.setCellValue(promedioMedicionesListaAnalistas.get(numAnalistaAux2-1));
                                break;
                            case 4:
                                //referencia-medida
                                cell.setCellValue(valorReferencia-promedioMedicionesListaAnalistas.get(numAnalistaAux2-1));
                                break;
                            case 5:
                                double medida=promedioMedicionesListaAnalistas.get(numAnalistaAux2-1);
                                //referencia-medida/medida
                                cell.setCellValue(((valorReferencia-medida)/medida)*100);
                                break;
                        }
                    }
                }
            }
        }

    }

    private void createTable2(int numRows, int numAnalistas,Map<Integer,Map<Integer,Map<Integer,Map<Integer,Double>>>>dataMap){
        int indexAux;

        AreaReference reference=workbook.getCreationHelper().createAreaReference(new CellReference(numRow+1,1),new CellReference(numRows-1, 2));
        XSSFTable table = sheet.createTable(reference);

        CTTable cttable = table.getCTTable();
        cttable.setDisplayName("SegundaTabla");
        cttable.setId(numAnalistas+1);
        cttable.setName("Segunda tabla");
        cttable.setTotalsRowShown(false);

        CTTableStyleInfo styleInfo = cttable.addNewTableStyleInfo();
        styleInfo.setName("TableStyleMedium3");
        styleInfo.setShowColumnStripes(false);
        styleInfo.setShowRowStripes(true);

        int firtsTableRow=numRow+1;
        int numFuncionAux=-1;
        double promedioRangosPromedioAnalistas=0;
        double repetibilidad=0;
        int countAnalista=1;
        List<Double>diasMedicionesRepeticionesList;
        Map<Integer,Map<Integer,Map<Integer,Double>>>data;
        int contFuncionesSobrantes=1;
        double mayor;
        double menor;
        double diferencia=0;
        double reproducibilidad=0;
        for (int r = firtsTableRow; r < numRows; r++) {
            XSSFRow row = sheet.createRow(r);
            diasMedicionesRepeticionesList=new ArrayList<>();
            for(int c = 1; c < 2 +1; c++) {
                XSSFCell cell = row.createCell(c);
                cell.setCellStyle(excelStyle.tableCellStyle());
                if(c==1){
                    sheet.setColumnWidth(c, 10000); //Set column width, you'll probably want to tweak the second int
                }else{
                    sheet.setColumnWidth(c, 8000); //Set column width, you'll probably want to tweak the second int
                }
                if(r == firtsTableRow) { //first row is for column headers
                    cell.setCellValue(listaColumnasTabla2.get(c-1)); //content **must** be here for table column names
                }  else {
                    indexAux=c-1;
                    if(indexAux==0){
                       if(numFuncionAux<2){
                           if(numFuncionAux==PROMEDIO_RANGOS_PROMEDIO_ANALISTAS){
                               cell.setCellValue(listaFuncionesTabla2.get(numFuncionAux)+"\n("+numAnalistas+" analistas)");
                           }
                           if(numFuncionAux==REPETIBILIDAD){
                               cell.setCellValue(listaFuncionesTabla2.get(numFuncionAux));
                           }
                       }else{
                           if(countAnalista<=numAnalistas){
                               cell.setCellValue("Promedio de las mediciones analista "+countAnalista);
                           }else {
                               switch (contFuncionesSobrantes){
                                   case 1:
                                       cell.setCellValue("Diferencia entre el promedio mayor y menor");
                                       break;
                                   case 2:
                                       cell.setCellValue("%Reproducibilidad");
                                       break;
                                   case 3:
                                       cell.setCellValue("%%R&R");
                                       break;
                               }
                           }
                       }
                       cell.setCellStyle(excelStyle.boldStyle());
                    }else{
                        if(numFuncionAux<2){
                            if(numFuncionAux==PROMEDIO_RANGOS_PROMEDIO_ANALISTAS){
                                promedioRangosPromedioAnalistas=getPromedio(rangosPromedioAnalistas);
                                cell.setCellValue(promedioRangosPromedioAnalistas);
                            }
                            if(numFuncionAux==REPETIBILIDAD){
                                repetibilidad=(2.5*(promedioRangosPromedioAnalistas)/0.1)*100;
                                cell.setCellValue(repetibilidad);
                            }
                        }else{
                            if(countAnalista<=numAnalistas){
                                double promedio;
                                data=dataMap.get(countAnalista);
                                getDiasMedicionesRepeticionesList(diasMedicionesRepeticionesList,data);
                                //obtener promedios de numAnalista
                                promedio=getPromedio(diasMedicionesRepeticionesList);
                                //añadir a lista de clase
                                promedioMedicionesListaAnalistas.add(promedio);
                                //mostrar
                                cell.setCellValue(promedio);
                                countAnalista=countAnalista+1;
                            }else {
                                switch (contFuncionesSobrantes){
                                    case 1:
                                        mayor=Collections.max(promedioMedicionesListaAnalistas);
                                        menor=Collections.min(promedioMedicionesListaAnalistas);
                                        diferencia=mayor-menor;
                                        cell.setCellValue(diferencia);
                                        break;
                                    case 2:
                                        reproducibilidad=(Math.sqrt( elevarCuadrado((3.65)*(diferencia))  - elevarCuadrado((2.5)*(promedioRangosPromedioAnalistas)) /((4)*(1)))/0.1)*100;
                                        cell.setCellValue(reproducibilidad);
                                        break;
                                    case 3:
                                        double r_r=Math.sqrt( elevarCuadrado(reproducibilidad) + elevarCuadrado(repetibilidad) );
                                        cell.setCellValue(r_r);
                                        break;
                                }
                                contFuncionesSobrantes=contFuncionesSobrantes+1;
                            }
                        }
                    }
                }
            }
            numFuncionAux=numFuncionAux+1;
        }
    }

    private void createTable5(int numRows, int col, int index,Map<Integer,Map<Integer,Map<Integer,Double>>>data){
        AreaReference reference=workbook.getCreationHelper().createAreaReference(new CellReference(numRow+1,1),new CellReference(numRows-1,col));
        XSSFTable table = sheet.createTable(reference);

        CTTable cttable = table.getCTTable();
        cttable.setDisplayName("PenultimaUltimaTabla"+index);
        cttable.setId(index+8);
        cttable.setName("Penultima tabla_"+index);
        cttable.setTotalsRowShown(false);

        CTTableStyleInfo styleInfo = cttable.addNewTableStyleInfo();
        styleInfo.setName("TableStyleMedium2");
        styleInfo.setShowColumnStripes(false);
        styleInfo.setShowRowStripes(true);

        int firtsTableRow=numRow+1;
        int dia;
        int numRepeticionAux;
        int medicionAux=-1;
        int numRepSesgo=numRepeticiones*2;
        int repSesgo;
        boolean aumentoDia=false;
        double valor;
        Map<Integer,Map<Integer,Double>>muestraTemp;
        Map<Integer,Double> repeticionTemp;
        double sesgo;
        for (int r = firtsTableRow; r < numRows; r++) {
            numRepeticionAux=1;
            dia=1;
            repSesgo=1;
            medicionAux=medicionAux+1;
            XSSFRow row = sheet.createRow(r);
            for(int c = 1; c <col+1; c++) {
                XSSFCell cell = row.createCell(c);
                cell.setCellStyle(excelStyle.tableCellStyle());
                if(c==1){
                    sheet.setColumnWidth(c, 10000); //Set column width, you'll probably want to tweak the second int
                }else{
                    sheet.setColumnWidth(c, 6000); //Set column width, you'll probably want to tweak the second int
                }
                if(r == firtsTableRow) { //first row is for column headers
                    cell.setCellValue(listaColumnasTabla5.get(c-1)); //content **must** be here for table column names
                } else {
                    if(c==1){
                        cell.setCellValue("Pieza "+medicionAux+" Largo");
                        cell.setCellStyle(excelStyle.boldStyle());
                    }else{
                        if(repSesgo>2){
                            repSesgo=1;
                        }
                        if(numRepeticionAux>numRepeticiones){
                            numRepeticionAux=1;
                            dia=dia+1;
                            aumentoDia=true;
                        }
                        if(aumentoDia){
                            cell.setCellValue("Pieza "+medicionAux+" Largo");
                            cell.setCellStyle(excelStyle.boldStyle());
                        }else{
                            if(numRepeticionAux<numRepSesgo){
                                if(repSesgo==1){
                                    muestraTemp=data.get(dia);
                                    repeticionTemp=muestraTemp.get(medicionAux);
                                    valor=repeticionTemp.get(numRepeticionAux);
                                    cell.setCellValue(valor);
                                }
                                if(repSesgo==2){
                                    muestraTemp=data.get(dia);
                                    repeticionTemp=muestraTemp.get(medicionAux);
                                    valor=repeticionTemp.get(numRepeticionAux);
                                    sesgo=valorReferencia-valor;
                                    listaSesgoAnalistas.add(sesgo);
                                    cell.setCellValue(sesgo);
                                    numRepeticionAux=numRepeticionAux+1;
                                }
                            }
                        }
                        if(!aumentoDia){
                            repSesgo=repSesgo+1;
                        }else {
                            aumentoDia=false;
                        }
                    }
                }
            }
        }
    }

    //createTable(2+numMediciones+numRow+sizeListaFunciones,listaColumnas.size(),analistaCount++,pair.getValue(),count);
    private void createTable(int numRows, int col, int index,Map<Integer,Map<Integer,Map<Integer,Double>>>data, int numAnalista){
        //inicio, fin
        AreaReference reference=workbook.getCreationHelper().createAreaReference(new CellReference(numRow+1,1),new CellReference(numRows-1,col));
        XSSFTable table = sheet.createTable(reference);

        CTTable cttable = table.getCTTable();
        cttable.setDisplayName("Reporte_"+index);
        cttable.setId(index);
        cttable.setName("Reporte tabla_"+index);
        cttable.setTotalsRowShown(false);

        CTTableStyleInfo styleInfo = cttable.addNewTableStyleInfo();
        styleInfo.setName("TableStyleMedium2");
        styleInfo.setShowColumnStripes(false);
        styleInfo.setShowRowStripes(true);

        int firtsTableRow=numRow+1;
        boolean pintarDesviacionEstandarPromedio=true;
        boolean pintarRangosPromedio=true;
        int dia;
        int numRepeticionAux;
        int indexAux;
        Map<Integer,Map<Integer,Double>>muestraTemp;
        Map<Integer,Double> repeticionTemp;
        double valor;
        int medicionC=1;
        int medicionAux=1;
        int numFuncion=0;
        int numFuncionAux=0;
        double valorProcesoFuncion;
        boolean pintarRangoPromedio=true;
        boolean pintarIndicacionVariacion=true;
        List<Double>medicionesList=new ArrayList<>();
        List<Double>diasMedicionesRepeticionesList=new ArrayList<>();
        Map<Integer,List<Double>>rangoRPorDia=new HashMap<>();
        rangoRPorDia.put(1,new ArrayList<>());
        List<Double>listaRangosPromedio=new ArrayList<>();
        double promedioRangosPromedio=0;
        double indicadorVariacion=0;
        double indiceRelativo;
        boolean pintarIndiceRelativo=true;
        double repetibilidad;
        boolean pintarRepetibilidad=true;
        boolean pintarPromedioTodasDimenciones=true;
        double promedioTodasDimensiones;

        for (int r = firtsTableRow; r < numRows; r++) {
            XSSFRow row = sheet.createRow(r);
            numRepeticionAux=1;
            dia=1;
            for(int c = 1; c <col+1; c++) {
                XSSFCell cell = row.createCell(c);
                cell.setCellStyle(excelStyle.tableCellStyle());
                if(c==1){
                    sheet.setColumnWidth(c, 10000); //Set column width, you'll probably want to tweak the second int
                }else{
                    sheet.setColumnWidth(c, 6000); //Set column width, you'll probably want to tweak the second int
                }
                if(r == firtsTableRow) { //first row is for column headers
                    cell.setCellValue(listaColumnas.get(c-1)); //content **must** be here for table column names
                } else {
                    //this.addColumns(data,c-1,cell,rowCont++);
                    indexAux=c-1;
                    if(indexAux==0){
                        medicionAux=medicionC++;
                        if(medicionAux>numMediciones){
                            numFuncionAux=numFuncion++;
                            if(numFuncionAux==REPETIBILIDAD_ANALISTA){
                                cell.setCellValue(listaFunciones.get(numFuncionAux)+" "+numAnalista);
                            }else{
                                cell.setCellValue(listaFunciones.get(numFuncionAux));
                            }
                        }else{
                            cell.setCellValue("Medición "+medicionAux);
                        }
                        cell.setCellStyle(excelStyle.boldStyle());
                    }else{
                        if(medicionAux<=numMediciones){
                            if(numRepeticionAux>numRepeticiones){
                                numRepeticionAux=1;
                                cell.setCellValue("Medición "+medicionAux);
                                cell.setCellStyle(excelStyle.boldStyle());
                                dia=dia+1;
                                rangoRPorDia.put(dia,new ArrayList<>());
                            }else {
                                muestraTemp=data.get(dia);
                                repeticionTemp=muestraTemp.get(medicionAux);
                                valor=repeticionTemp.get(numRepeticionAux);
                                cell.setCellValue(valor);
                                numRepeticionAux=numRepeticionAux+1;
                            }
                        }else{
                            if(numRepeticionAux>numRepeticiones){
                                numRepeticionAux=1;
                                dia=dia+1;
                                if(numFuncionAux!=DESVIACION_ESTANDAR_PROMEDIO){
                                    cell.setCellValue(listaFunciones.get(numFuncionAux));
                                    cell.setCellStyle(excelStyle.boldStyle());
                                    if(numFuncionAux==RANGO_PROMEDIO){
                                        pintarRangoPromedio=true;
                                    }
                                }
                            }else{
                                if(numFuncionAux==DESVIACION_ESTANDAR_PROMEDIO){
                                    if(pintarDesviacionEstandarPromedio) {
                                        pintarDesviacionEstandarPromedio=false;
                                        //primero conseguir lista
                                        getDiasMedicionesRepeticionesList(diasMedicionesRepeticionesList,data);
                                        //hacer operación, guardar y pintar
                                        double desviacionEstandarPromedio=desviacionEstandarPromedio(diasMedicionesRepeticionesList);
                                        desviacionEstandarPromedioListaAnalistas.add(desviacionEstandarPromedio);
                                        cell.setCellValue(desviacionEstandarPromedio);
                                    }
                                }
                                if(numFuncionAux!=DESVIACION_ESTANDAR_PROMEDIO && numFuncionAux!= RANGO_PROMEDIO
                                        && numFuncionAux!=PROMEDIO_RANGOS_PROMEDIO && numFuncionAux!=INDICADOR_VARIACION
                                        && numFuncionAux!=INDICE_RELATIVO && numFuncionAux!=REPETIBILIDAD_ANALISTA
                                        && numFuncionAux!=PROMEDIO_TODAS_DIMENSIONES){
                                    medicionesList.clear();
                                    getMedicionesList(data,dia,numRepeticionAux,medicionesList);
                                    valorProcesoFuncion=procesarFuncion(numFuncionAux,medicionesList);
                                    if(numFuncionAux==RANGO_R){
                                        rangoRPorDia.get(dia).add(valorProcesoFuncion);
                                    }
                                    cell.setCellValue(valorProcesoFuncion);
                                }
                                if(numFuncionAux==RANGO_PROMEDIO){
                                    if(pintarRangoPromedio){
                                        pintarRangoPromedio=false;
                                        List<Double>rangos=rangoRPorDia.get(dia);
                                        double promedio=getPromedio(rangos);
                                        listaRangosPromedio.add(promedio);
                                        cell.setCellValue(promedio);
                                    }
                                }
                                if(numFuncionAux==PROMEDIO_RANGOS_PROMEDIO) {
                                    if(pintarRangosPromedio){
                                        promedioRangosPromedio=getPromedio(listaRangosPromedio);
                                        rangosPromedioAnalistas.add(promedioRangosPromedio);
                                        cell.setCellValue(promedioRangosPromedio);
                                        pintarRangosPromedio=false;
                                    }
                                }
                                if(numFuncionAux==INDICADOR_VARIACION){
                                    if(pintarIndicacionVariacion){
                                        pintarIndicacionVariacion=false;
                                        indicadorVariacion=(promedioRangosPromedio)*(5.15/1.41);
                                        cell.setCellValue(indicadorVariacion);
                                    }
                                }
                                if(numFuncionAux==INDICE_RELATIVO){
                                    if(pintarIndiceRelativo){
                                        pintarIndiceRelativo=false;
                                        indiceRelativo=(indicadorVariacion/100)*100;
                                        cell.setCellValue(indiceRelativo);
                                    }
                                }
                                if(numFuncionAux==REPETIBILIDAD_ANALISTA){
                                    if(pintarRepetibilidad){
                                        pintarRepetibilidad=false;
                                        repetibilidad=((2.5*promedioRangosPromedio)/0.1)*100;
                                        cell.setCellValue(repetibilidad);
                                    }
                                }
                                if(numFuncionAux==PROMEDIO_TODAS_DIMENSIONES){
                                    if(pintarPromedioTodasDimenciones){
                                        pintarPromedioTodasDimenciones=false;
                                        promedioTodasDimensiones=getPromedio(diasMedicionesRepeticionesList);
                                        cell.setCellValue(promedioTodasDimensiones);
                                    }
                                }
                                if(numFuncionAux!=DESVIACION_ESTANDAR_PROMEDIO && numFuncionAux!=PROMEDIO_RANGOS_PROMEDIO && numFuncionAux!=INDICADOR_VARIACION
                                        && numFuncionAux!=INDICE_RELATIVO && numFuncionAux!=REPETIBILIDAD_ANALISTA && numFuncionAux!=PROMEDIO_TODAS_DIMENSIONES){
                                    numRepeticionAux=numRepeticionAux+1;
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    private void getDiasMedicionesRepeticionesList(List<Double> diasMedicionesRepeticionesList,Map<Integer,Map<Integer,Map<Integer,Double>>>data){
        Map<Integer,Map<Integer,Double>> muestras;
        Map<Integer,Double> repeticiones;
        for (Map.Entry<Integer,Map<Integer,Map<Integer,Double>>> dia: data.entrySet()){
            muestras=dia.getValue();
            for (Map.Entry<Integer,Map<Integer,Double>> muestra: muestras.entrySet()){
                repeticiones=muestra.getValue();
                for (Map.Entry<Integer, Double>repeticion: repeticiones.entrySet()){
                    diasMedicionesRepeticionesList.add(repeticion.getValue());
                }
            }
        }
    }

    private double desviacionEstandarPromedio(List<Double> list){
        double promedio=getPromedio(list);
        double sumatoria=0;
        double subResultado;
        //hacer sumatoria
        for(double elemento: list){
            sumatoria=sumatoria+(elevarCuadrado((elemento-promedio)));
        }
        subResultado=sumatoria/(list.size()-1);
        return Math.sqrt(subResultado);
    }

    private double elevarCuadrado(double valor){
        return valor*valor;
    }

    private double procesarFuncion(int numFuncion,List<Double>medicionesList){
        switch (numFuncion){
            case PROMEDIO:
                return getPromedio(medicionesList);
            case DESVIACION_ESTANDAR:
                medicionesList.add(getPromedio(medicionesList));
                return desviacionEstandarPromedio(medicionesList);
            case VALOR_MAX:
                return getMax(medicionesList);
            case VALOR_MIN:
                return getMin(medicionesList);
            case RANGO_R:
                //max-min
                return getMax(medicionesList)-getMin(medicionesList);
        }
        return 0;
    }

    private double getMax(List<Double> list){
        return Collections.max(list);
    }

    private double getMin(List<Double>list){
        return Collections.min(list);
    }

    private double getPromedio(List<Double>list){
        double valor=0;
        for(double medicion : list){
            valor=valor+medicion;
        }
        valor=valor/list.size();
        return valor;
    }

    private void getMedicionesList(Map<Integer,Map<Integer,Map<Integer,Double>>>data, int dia, int rep,List<Double>medicionesList){
        Map<Integer,Map<Integer,Double>> muestras=data.get(dia);
        Map<Integer,Double> repeticion;
        double valor;
        for (Map.Entry<Integer,Map<Integer,Double>> muestra: muestras.entrySet()){
            repeticion=muestra.getValue();
            valor=repeticion.get(rep);
            medicionesList.add(valor);
        }
    }

    private void addRow(String name, CellStyle style){
        Row row = sheet.createRow(numRow);
        Cell cell = row.createCell(1);
        cell.setCellValue(name);
        cell.setCellStyle(style);
        numRow=numRow+1;
    }

    private ResponseEntity<InputStreamResource> generateFile() throws IOException {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Disposition", "inline; filename=reporte.xlsx");
        ByteArrayOutputStream byteArrayOutputStream=new ByteArrayOutputStream();
        workbook.write(byteArrayOutputStream);
        MediaType excel=MediaType.valueOf("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(byteArrayOutputStream.toByteArray());
        return ResponseEntity.ok()
                .headers(headers)
                .contentType(excel)
                .body(new InputStreamResource(byteArrayInputStream));
    }

    private void buildMap(Map<Integer,Map<Integer,Map<Integer,Map<Integer,Double>>>>dataMap,Map<String, String> request){
        String key;
        String analista;
        int numAnalista;
        String dia;
        int numDia;
        String muestra;
        int muestraNum;
        String repeticion;
        int repeticionNum;
        Map<Integer,Map<Integer,Map<Integer,Double>>>diaTemp;
        Map<Integer,Map<Integer,Double>> muestraTemp;
        Map<Integer,Double>repeticionTemp;
        for (Map.Entry<String, String> pair : request.entrySet()) {
            key=pair.getKey();//1111
            analista=key.substring(0,1);
            numAnalista=Integer.parseInt(analista);

            if(!dataMap.containsKey(numAnalista)){
                dataMap.put(numAnalista,new HashMap<>());
            }
            diaTemp=dataMap.get(numAnalista);

            dia=key.substring(1,2);
            numDia=Integer.parseInt(dia);

            if(!diaTemp.containsKey(numDia)){
                diaTemp.put(numDia,new HashMap<>());
            }
            muestraTemp=diaTemp.get(numDia);

            muestra=key.substring(2,3);
            muestraNum=Integer.parseInt(muestra);

            if(!muestraTemp.containsKey(muestraNum)){
                muestraTemp.put(muestraNum,new HashMap<>());
            }
            repeticionTemp=muestraTemp.get(muestraNum);

            repeticion=key.substring(3,4);
            repeticionNum=Integer.parseInt(repeticion);

            if(!repeticionTemp.containsKey(repeticionNum)){
                repeticionTemp.put(repeticionNum,Double.parseDouble(pair.getValue()));
            }
        }
    }

    private List<String> getAnalistas(Map<String, String>request){
        List<String>analistas=new ArrayList<>();
        for (Map.Entry<String, String> pair : request.entrySet()) {
            if(pair.getKey().contains("nombreAnalista")){
                analistas.add(pair.getValue());
            }
        }
        return analistas;
    }
}