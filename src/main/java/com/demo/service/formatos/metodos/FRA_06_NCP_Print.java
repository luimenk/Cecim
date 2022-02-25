package com.demo.service.formatos.metodos;

import com.demo.model.operacion.metodos.fra05hum.FRA_HUM_001;
import com.demo.model.operacion.metodos.fra06ncp.FRA_NCP_001;
import com.demo.model.operacion.metodos.fra06ncp.datas.FRA_NCP_001_DATA_01;
import com.demo.model.operacion.metodos.fra06ncp.datas.FRA_NCP_001_DATA_02;
import com.demo.model.operacion.metodos.fra06ncp.datas.FRA_NCP_001_DATA_03;
import com.demo.model.operacion.metodos.fra06ncp.datas.FRA_NCP_001_DATA_04;
import com.demo.repository.FoliosRepository;
import com.demo.repository.operacion.metodos.fra06ncp.FRA_NCP_001_Repository;
import com.demo.repository.operacion.metodos.fra06ncp.datas.FRA_NCP_001_DATA_01_Repository;
import com.demo.repository.operacion.metodos.fra06ncp.datas.FRA_NCP_001_DATA_02_Repository;
import com.demo.repository.operacion.metodos.fra06ncp.datas.FRA_NCP_001_DATA_03_Repository;
import com.demo.repository.operacion.metodos.fra06ncp.datas.FRA_NCP_001_DATA_04_Repository;
import com.demo.utils.EstructuraNombres;
import com.demo.utils.FormatoFechas;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.util.Units;
import org.apache.poi.xwpf.usermodel.*;
import org.apache.xmlbeans.XmlException;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTbl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.validation.constraints.Null;
import java.io.*;
import java.math.BigInteger;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

@Service
public class FRA_06_NCP_Print {

    @Autowired
    private FRA_NCP_001_Repository fra_ncp_001_repository;

    @Autowired
    private FRA_NCP_001_DATA_01_Repository fra_ncp_001_data_01_repository;

    @Autowired
    private FRA_NCP_001_DATA_02_Repository fra_ncp_001_data_02_repository;

    @Autowired
    private FRA_NCP_001_DATA_03_Repository fra_ncp_001_data_03_repository;

    @Autowired
    private FRA_NCP_001_DATA_04_Repository fra_ncp_001_data_04_repository;

    EstructuraNombres estructuraNombres = new EstructuraNombres();
    FormatoFechas formatoFechas = new FormatoFechas();

    public ResponseEntity<InputStreamResource> crearFormato(Long id, int band) throws InvalidFormatException, IOException {
//        ClassPathResource resource = new ClassPathResource("/documentos/METODOS/FRA-NCP-001.docx");
//        XWPFDocument doc = new XWPFDocument(resource.getInputStream());
        URL url = new URL("https://resources.adpmx.com/cecim/laboratorio/doc/register/methods/06-FRA-NCP-001.docx");
        XWPFDocument doc = new XWPFDocument(url.openStream());

        FRA_NCP_001 fra_ncp_001;
        List<FRA_NCP_001_DATA_01> lista1;
        List<FRA_NCP_001_DATA_02> lista2;
        List<FRA_NCP_001_DATA_03> lista3;
        List<FRA_NCP_001_DATA_04> lista4;

        if (band == 1){
            fra_ncp_001 = fra_ncp_001_repository.findByIdFRANCP(id);
            lista1 = fra_ncp_001_data_01_repository.buscarTodosPorEnsayo(fra_ncp_001.getIdFRANCP());
            lista2 = fra_ncp_001_data_02_repository.buscarTodosPorEnsayo(fra_ncp_001.getIdFRANCP());
            lista3 = fra_ncp_001_data_03_repository.buscarTodosPorEnsayo(fra_ncp_001.getIdFRANCP());
            lista4 = fra_ncp_001_data_04_repository.buscarTodosPorEnsayo(fra_ncp_001.getIdFRANCP());
        } else {
            fra_ncp_001 = fra_ncp_001_repository.findByMetodoMuestra_MetodoMuestraId(id);
            lista1 = fra_ncp_001_data_01_repository.buscarTodosPorEnsayo(fra_ncp_001.getIdFRANCP());
            lista2 = fra_ncp_001_data_02_repository.buscarTodosPorEnsayo(fra_ncp_001.getIdFRANCP());
            lista3 = fra_ncp_001_data_03_repository.buscarTodosPorEnsayo(fra_ncp_001.getIdFRANCP());
            lista4 = fra_ncp_001_data_04_repository.buscarTodosPorEnsayo(fra_ncp_001.getIdFRANCP());
        }

        XWPFTable table0 = doc.getTables().get(0);
        table0.getRow(0).getCell(1).setText(fra_ncp_001.getFolioTecnica());

        XWPFTable table1 = doc.getTables().get(1);
        table1.getRow(0).getCell(1).setText(fra_ncp_001.getFolioSolicitudServicioInterno());
        table1.getRow(0).getCell(3).setText(formatoFechas.formateadorFechas(fra_ncp_001.getFechaInicioAnalisis()));
        table1.getRow(1).getCell(1).setText(fra_ncp_001.getIdInternoMuestra());
        table1.getRow(1).getCell(3).setText(formatoFechas.formateadorFechas(fra_ncp_001.getFechaFinalAnalisis()));

        XWPFTable table2 = doc.getTables().get(2);
        table2.getRow(0).getCell(1).setText(fra_ncp_001.getTemperatura() + " °C");
        table2.getRow(0).getCell(3).setText(fra_ncp_001.getHumedadRelativa() + " %");
        table2.getRow(2).getCell(3).setText(fra_ncp_001.getLente());

        XWPFTable table3 = doc.getTables().get(3);
        for (int i=0; i<lista1.size(); i++) {
            table3.getRow(i+2).getCell(1).setText(lista1.get(i).getEspesorTotalMicrometro());
            table3.getRow(i+2).getCell(3).setText(lista2.get(i).getEspesorTotalMicrometro());
        }
        table3.getRow(5).getCell(1).setText(fra_ncp_001.getEspesorTotalPromedioMM1());
        table3.getRow(5).getCell(3).setText(fra_ncp_001.getEspesorTotalPromedioMM2());
        table3.getRow(6).getCell(1).setText(fra_ncp_001.getEspesorTotalPromedioUM1());
        table3.getRow(6).getCell(3).setText(fra_ncp_001.getEspesorTotalPromedioUM2());

        XWPFTable table4 = doc.getTables().get(4);
        for (int i=0; i < 6; i++){
            if (i < lista3.size()) {
                table4.getRow(i+2).getCell(1).setText(lista3.get(i).getEspesorPorMicroscopia());
            } else {
                table4.getRow(i+2).getCell(1).setText("N/A");
            }

            if (i < lista4.size()) {
                table4.getRow(i+2).getCell(3).setText(lista4.get(i).getEspesorPorMicroscopia());
            } else {
                table4.getRow(i+2).getCell(3).setText("N/A");
            }
        }
        table4.getRow(8).getCell(1).setText(fra_ncp_001.getNumeroTotalCapas1());
        table4.getRow(8).getCell(3).setText(fra_ncp_001.getEspesorTotalMicroscopia1());
        table4.getRow(8).getCell(5).setText(fra_ncp_001.getNumeroTotalCapas2());
        table4.getRow(8).getCell(7).setText(fra_ncp_001.getEspesorTotalMicroscopia2());

        XWPFTable table5 = doc.getTables().get(5);
        table5.getRow(0).getCell(1).setText(fra_ncp_001.getObservaciones());

        XWPFTable table6 = doc.getTables().get(6);
        table6.getRow(1).getCell(0).removeParagraph(0);
        XWPFParagraph paragraph = table6.getRow(1).getCell(0).addParagraph();
        paragraph.setAlignment(ParagraphAlignment.CENTER);
        XWPFRun run = paragraph.createRun();
        InputStream inputStream = new URL(fra_ncp_001.getRubricaRealizo()).openStream();
        XWPFPicture xwpfPicture = run.addPicture(inputStream, XWPFDocument.PICTURE_TYPE_PNG, "Name", Units.pixelToEMU(110), Units.pixelToEMU(73));
        run.addBreak();
        run.setText(fra_ncp_001.getRealizo());
        table6.getRow(1).getCell(1).setText(fra_ncp_001.getSupervisor());

        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Disposition", "inline; filename=FRA-NCP-"+estructuraNombres.getNombre()+".docx");
        ByteArrayOutputStream byteArrayOutputStream=new ByteArrayOutputStream();
        doc.write(byteArrayOutputStream);
        doc.close();
        MediaType word = MediaType.valueOf("application/vnd.openxmlformats-officedocument.wordprocessingml.document");
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(byteArrayOutputStream.toByteArray());
        return ResponseEntity.ok()
                .headers(headers)
                .contentType(word)
                .body(new InputStreamResource(byteArrayInputStream));
    }

    public int fragmentoReporte (XWPFDocument doc, XWPFDocument plantilla, int contTabla, int contador, List<FRA_NCP_001> lista) throws XmlException, IOException {

        List<FRA_NCP_001_DATA_01> lista1;
        List<FRA_NCP_001_DATA_02> lista2;
        List<FRA_NCP_001_DATA_03> lista3;
        List<FRA_NCP_001_DATA_04> lista4;

        XWPFStyles newStyles = doc.createStyles();
        newStyles.setStyles(plantilla.getStyle());
        XWPFParagraph para = doc.createParagraph();
        para.setStyle("Ttulo2");
        XWPFRun run = para.createRun();
        run.setText("Determinación del número de capas en películas");

        /***** INICIO DE TABLA DE MARCA DE EQUIPO *****/
        XWPFTable tabl = doc.createTable();
        tabl.removeRow(0);
        XWPFTable tableDocummento = plantilla.getTables().get(25);
        tableDocummento.getRow(1).getCell(1).setText("Micrómetro, Mitutoyo ID-C112EXBS. Microscopio óptico, Motic BA410.");
        CTTbl cTTblTemplat = tableDocummento.getCTTbl();
        tabl = new XWPFTable((CTTbl) cTTblTemplat.copy(), doc);
        doc.setTable(contTabla, tabl);
        XWPFParagraph para0 = doc.createParagraph();
        XWPFRun run0 = para0.createRun();
        run0.addBreak();
        contTabla++;
        /***** FIN DE TABLA DE MARCA DE EQUIPO *****/

        /***** INICIO DE TABLA DE MUESTRAS *****/
        XWPFTable table = doc.createTable();
        table.removeRow(0);
        XWPFTable tableDocumment = plantilla.getTables().get(26);
        CTTbl cTTblTemplate = tableDocumment.getCTTbl();
        table = new XWPFTable((CTTbl) cTTblTemplate.copy(), doc);
        table.removeRow(2);
        for (int l =0; l< contador; l++) {
            try {
                XWPFTableRow row1 = table.createRow();
                row1.getCell(0).setText(lista.get(l).getMetodoMuestra().getSolicitudServicioClienteMuestras().getIdClienteMuestra());
                row1.getCell(1).setText(formatoFechas.formateadorFechas(lista.get(l).getFechaInicioAnalisis()) + " - " + formatoFechas.formateadorFechas(lista.get(l).getFechaFinalAnalisis()));
                row1.getCell(2).setText(lista.get(l).getTemperatura());
                row1.createCell();
                row1.getCell(3).setText(lista.get(l).getHumedadRelativa());
            } catch (NullPointerException e) {
                System.out.println("El ensayo aún no ha sido desarrollado");
                table.addRow(tableDocumment.getRow(2));
            } catch (IndexOutOfBoundsException ex){
                System.out.println("El ensayo no ha sido desarrollado");
                table.addRow(tableDocumment.getRow(2));
            }
        }
        doc.setTable(contTabla, table);
        /***** FIN DE TABLA DE MUESTRAS *****/

        XWPFParagraph para1 = doc.createParagraph();
        XWPFRun run1 = para1.createRun();
        run1.addBreak();
        contTabla++;

        /***** INICIO DE TÍTULO DE TABLA *****/
        XWPFParagraph para2 = doc.createParagraph();
        para2.setStyle("Tablas");
        XWPFRun run2 = para2.createRun();
        run2.setText("Resultados de la determinación de número de capas con micrómetro.");
        /***** FIN DE TÍTULO DE TABLA *****/

        lista1 = fra_ncp_001_data_01_repository.buscarTodosPorEnsayo(lista.get(0).getIdFRANCP());
        lista2 = fra_ncp_001_data_02_repository.buscarTodosPorEnsayo(lista.get(0).getIdFRANCP());
        lista3 = fra_ncp_001_data_03_repository.buscarTodosPorEnsayo(lista.get(0).getIdFRANCP());
        lista4 = fra_ncp_001_data_04_repository.buscarTodosPorEnsayo(lista.get(0).getIdFRANCP());

        /***** INICIO DE TABLA DE RESULTADOS *****/
        XWPFTable table_2 = doc.createTable();
        table_2.removeRow(0);
        XWPFTable tableDocumment_2 = plantilla.getTables().get(27);
        CTTbl cTTblTemplate_2 = tableDocumment_2.getCTTbl();
        table_2 = new XWPFTable((CTTbl) cTTblTemplate_2.copy(), doc);
        try {
            table_2.getRow(1).getCell(0).setText(lista.get(0).getMetodoMuestra().getSolicitudServicioClienteMuestras().getIdClienteMuestra());
            table_2.getRow(1).getCell(3).setText("3");
        } catch (NullPointerException e) {
            System.out.println("Ocurrió un error en la tabla de resultados 1");
        }
///TODO: REVISAR ESTO
        try {
            if (lista.get(0).getMuestraEnReporte().equals("1")) {
                for (int i = 0; i < lista1.size(); i++) {
                    table_2.getRow(i +1).getCell(2).setText(lista1.get(i).getEspesorTotalMicrometro());
                    table_2.getRow(1).getCell(4).setText(lista.get(0).getEspesorTotalPromedioMM1());
                }
            } else {
                for (int i = 0; i < lista2.size(); i++) {
                    table_2.getRow(i +1).getCell(2).setText(lista2.get(i).getEspesorTotalMicrometro());
                    table_2.getRow(1).getCell(4).setText(lista.get(0).getEspesorTotalPromedioMM2());
                }
            }
        } catch (NullPointerException e) {
            System.out.println("Ocurrió un error en la tabla de resultados 1");
        }

        XWPFTable tableDocumment_4 = plantilla.getTables().get(28);
        try {
            XWPFTableRow row2 = tableDocumment_4.getRow(0);
            row2.getCell(1).setText("N/A");
            row2.getCell(1).getCTTc().getTcPr().addNewGridSpan();
            row2.getCell(1).getCTTc().getTcPr().getGridSpan().setVal(BigInteger.valueOf((long) 4));
            table_2.addRow(row2);

            XWPFTableRow row3 = tableDocumment_4.getRow(1);
            row3.getCell(1).setText("N/A");
            row3.getCell(1).getCTTc().getTcPr().addNewGridSpan();
            row3.getCell(1).getCTTc().getTcPr().getGridSpan().setVal(BigInteger.valueOf((long) 4));
            table_2.addRow(row3);

            XWPFTableRow row4 = tableDocumment_4.getRow(2);
            row4.getCell(1).setText(lista.get(0).getObservaciones());
            row4.getCell(1).getCTTc().getTcPr().addNewGridSpan();
            row4.getCell(1).getCTTc().getTcPr().getGridSpan().setVal(BigInteger.valueOf((long) 4));
            table_2.addRow(row4);
        } catch (NullPointerException e) {
            table_2.addRow(tableDocumment_4.getRow(2));
        }

        doc.setTable(contTabla, table_2);
        /***** FIN DE TABLA DE RESULTADOS *****/

        XWPFParagraph para3 = doc.createParagraph();
        XWPFRun run3 = para3.createRun();
        run3.addBreak();
        contTabla++;

        /***** INICIO DE TÍTULO DE TABLA *****/
        XWPFParagraph para4 = doc.createParagraph();
        para4.setStyle("Tablas");
        XWPFRun run4 = para4.createRun();
        run4.setText("Resultados de la determinación de número de capas con microscopio.");
        /***** FIN DE TÍTULO DE TABLA *****/

        /***** INICIO DE TABLA DE RESULTADOS *****/
        XWPFTable table_3 = doc.createTable();
        table_3.removeRow(0);
        XWPFTable tableDocumment_3 = plantilla.getTables().get(29);
        CTTbl cTTblTemplate_3 = tableDocumment_3.getCTTbl();
        table_3 = new XWPFTable((CTTbl) cTTblTemplate_3.copy(), doc);
        try {
            table_3.getRow(1).getCell(0).setText(lista.get(0).getMetodoMuestra().getSolicitudServicioClienteMuestras().getIdClienteMuestra());
            if (lista.get(0).getMuestraEnReporte().equals("1")) {
                table_3.getRow(1).getCell(3).setText(lista3.size() + "");
                table_3.getRow(1).getCell(4).setText(lista.get(0).getEspesorTotalMicroscopia1());
            } else {
                table_3.getRow(1).getCell(3).setText(lista4.size() + "");
                table_3.getRow(1).getCell(4).setText(lista.get(0).getEspesorTotalMicroscopia2());
            }

        } catch (NullPointerException e) {
            System.out.println("Ocurrió un error en la tabla de resultados 1");
        }

        try {
            if (lista.get(0).getMuestraEnReporte().equals("1")) {
                for (int i = 0; i < lista1.size(); i++) {
                    table_3.getRow(i +1).getCell(2).setText(lista3.get(i).getEspesorPorMicroscopia());
                }
            } else {
                for (int i = 0; i < lista2.size(); i++) {
                    table_3.getRow(i +1).getCell(2).setText(lista4.get(i).getEspesorPorMicroscopia());
                }
            }
        } catch (NullPointerException e) {
            System.out.println("Ocurrió un error en la tabla de resultados 1");
        }

        XWPFTable tableDocumment_5 = plantilla.getTables().get(30);
        try {
            XWPFTableRow row2 = tableDocumment_5.getRow(0);
            row2.getCell(1).setText("N/A");
            row2.getCell(1).getCTTc().getTcPr().addNewGridSpan();
            row2.getCell(1).getCTTc().getTcPr().getGridSpan().setVal(BigInteger.valueOf((long) 4));
            table_2.addRow(row2);

            XWPFTableRow row3 = tableDocumment_5.getRow(1);
            row3.getCell(1).setText("N/A");
            row3.getCell(1).getCTTc().getTcPr().addNewGridSpan();
            row3.getCell(1).getCTTc().getTcPr().getGridSpan().setVal(BigInteger.valueOf((long) 4));
            table_2.addRow(row3);

            XWPFTableRow row4 = tableDocumment_5.getRow(2);
            row4.getCell(1).setText(lista.get(0).getObservaciones());
            row4.getCell(1).getCTTc().getTcPr().addNewGridSpan();
            row4.getCell(1).getCTTc().getTcPr().getGridSpan().setVal(BigInteger.valueOf((long) 4));
            table_2.addRow(row4);
        } catch (NullPointerException e) {
            table_3.addRow(tableDocumment_5.getRow(2));
        }

        doc.setTable(contTabla, table_3);
        /***** FIN DE TABLA DE RESULTADOS *****/

        XWPFParagraph para5 = doc.createParagraph();
        XWPFRun run5 = para5.createRun();
        run5.addBreak();

        List<XWPFParagraph> listaParagraph = new ArrayList<>();
        List<XWPFRun> listaRun = new ArrayList<>();
        List<InputStream> listaFis = new ArrayList<>();
        List<XWPFPicture> listaPircures = new ArrayList<>();
        List<XWPFParagraph> listaParagraph2 = new ArrayList<>();
        List<XWPFRun> listaRun2 = new ArrayList<>();


        for (int k = 0; k < contador; k++) {
            try {
                listaParagraph.add(k, doc.createParagraph());
                listaParagraph.get(k).setAlignment(ParagraphAlignment.CENTER);
                listaRun.add(k, listaParagraph.get(k).createRun());
                try {
                    if (lista.get(k).getMuestraEnReporte().equals("1")) {
                        listaFis.add(k, new URL(lista.get(k).getPathImagen()).openStream());
                    } else {
                        listaFis.add(k, new URL(lista.get(k).getPathImagen2()).openStream());
                    }
                } catch (NullPointerException | FileNotFoundException e) {
                    System.out.println("No se encontró la imágen o el formato de la imagen es incorrecto");
                }
                try {
                    listaPircures.add(k, listaRun.get(k).addPicture(listaFis.get(k), XWPFDocument.PICTURE_TYPE_PNG, "Name", Units.pixelToEMU(480), Units.pixelToEMU(360)));
                } catch (NullPointerException | InvalidFormatException e) {
                    System.out.println("No se encontró la imágen (1)");
                } catch (IndexOutOfBoundsException e) {
                    System.out.println("Ocurrió un error de index en la lista");
                }
                listaParagraph2.add(k, doc.createParagraph());
                listaParagraph2.get(k).setAlignment(ParagraphAlignment.CENTER);
                //listaParagraph2.get(k).setStyle("Termograma");
                listaRun2.add(k, listaParagraph2.get(k).createRun());
                listaRun2.get(k).setBold(true);
                listaRun2.get(k).setFontSize(8);
                listaRun2.get(k).setColor("b7b7b7");
                listaRun2.get(k).setFontFamily("Century Gothic");
                listaRun2.get(k).setText("Micrografía "+ (k + 1) +". Análisis del número de capas de la muestra \"" + lista.get(k).getMetodoMuestra().getSolicitudServicioClienteMuestras().getIdClienteMuestra() + "\".");
            } catch (NullPointerException e) {
                System.out.println("Valida el error");
            }
        }

        contTabla++;

        return contTabla;
    }
}
