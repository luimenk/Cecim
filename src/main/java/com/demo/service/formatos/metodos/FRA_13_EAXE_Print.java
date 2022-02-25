package com.demo.service.formatos.metodos;

import com.demo.model.operacion.metodos.fra11eat.FRA_EAT_001;
import com.demo.model.operacion.metodos.fra12eauv.datas.FRA_EAUV_001_DATA;
import com.demo.model.operacion.metodos.fra13eaxe.FRA_EAXE_013;
import com.demo.model.operacion.metodos.fra13eaxe.datas.FRA_EAXE_013_DATA;
import com.demo.repository.operacion.metodos.fra13eaxe.FRA_EAXE_013_Repository;
import com.demo.repository.operacion.metodos.fra13eaxe.datas.FRA_EAXE_013_DATA_Repository;
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

import java.io.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

@Service
public class FRA_13_EAXE_Print {

    @Autowired
    private FRA_EAXE_013_Repository fra_eaxe_013_repository;

    @Autowired
    private FRA_EAXE_013_DATA_Repository fra_eaxe_013_data_repository;

    EstructuraNombres estructuraNombres = new EstructuraNombres();
    FormatoFechas formatoFechas = new FormatoFechas();

    public ResponseEntity<InputStreamResource> crearFormato(Long id, int band) throws InvalidFormatException, IOException {
        URL url = new URL("https://resources.adpmx.com/cecim/laboratorio/doc/register/methods/13-FRA-EAXE-001.docx");
        XWPFDocument doc = new XWPFDocument(url.openStream());

        FRA_EAXE_013 fra_eaxe_013;
        List<FRA_EAXE_013_DATA> lista;
        if (band == 1){
            fra_eaxe_013 = fra_eaxe_013_repository.findByIdFRAEAXE(id);
            lista = fra_eaxe_013_data_repository.buscarTodosPorEnsayo(id);
        }else {
            fra_eaxe_013 = fra_eaxe_013_repository.findByMetodoMuestra_MetodoMuestraId(id);
            lista = fra_eaxe_013_data_repository.buscarTodosPorEnsayo(fra_eaxe_013.getIdFRAEAXE());
        }

        XWPFTable table0 = doc.getTables().get(0);
        table0.getRow(0).getCell(1).setText(fra_eaxe_013.getFolioTecnica());

        XWPFTable table1 = doc.getTables().get(1);
        table1.getRow(0).getCell(1).setText(fra_eaxe_013.getFolioSolicitudServicioInterno());
        table1.getRow(0).getCell(3).setText(formatoFechas.formateadorFechas(fra_eaxe_013.getFechaInicioAnalisis()));
        table1.getRow(1).getCell(1).setText(fra_eaxe_013.getIdInternoMuestra());
        table1.getRow(1).getCell(3).setText(formatoFechas.formateadorFechas(fra_eaxe_013.getFechaFinalAnalisis()));

        XWPFTable table2 = doc.getTables().get(2);
        table2.getRow(0).getCell(1).setText(fra_eaxe_013.getTemperatura() + " °C");
        table2.getRow(0).getCell(3).setText(fra_eaxe_013.getHumedadRelativa() + " %");
        table2.getRow(1).getCell(1).setText(fra_eaxe_013.getCodigoCamaraXE());
        table2.getRow(1).getCell(3).setText(fra_eaxe_013.getCodigoRadiometro());
        //table2.getRow(2).getCell(1).setText(fra_eaxe_013.getIrradiacion());
        //table2.getRow(2).getCell(3).setText(fra_eaxe_013.getTemperaturaPanel());
        //table2.getRow(3).getCell(1).setText(fra_eaxe_013.getLongitudOnda());
        //table2.getRow(3).getCell(3).setText(fra_eaxe_013.getTiempoCapturasFotograficas());
        table2.getRow(4).getCell(1).setText(fra_eaxe_013.getTiempoCiclo());
        table2.getRow(4).getCell(3).setText(fra_eaxe_013.getCicloTomaFotografica());
        table2.getRow(5).getCell(1).setText(fra_eaxe_013.getDescripcionMuestra());
        table2.getRow(5).getCell(3).setText(fra_eaxe_013.getTipoProducto());
        table2.getRow(6).getCell(1).setText(fra_eaxe_013.getTipoMaterial());
        table2.getRow(6).getCell(3).setText(fra_eaxe_013.getCaraAnalisis());
        table2.getRow(7).getCell(1).setText(fra_eaxe_013.getAditivoBiodegradable());
        table2.getRow(7).getCell(3).setText(fra_eaxe_013.getPorcentajeInclusion());
        table2.getRow(8).getCell(1).setText(fra_eaxe_013.getTipoSuperficie());
        table2.getRow(8).getCell(2).setText(fra_eaxe_013.getEspecifique());

        int contadorRow = 0;
        int contadorCell = 1;
        int tiempo = 0;
        int k = 0;

        XWPFTable table = doc.getTables().get(3);
        for (int i = 0; i < 56; i++) {
            contadorRow++;
            if (contadorRow == 15) {
                contadorRow = 1;
                contadorCell = contadorCell + 3;
            }

            String tiempoObtenido = table.getRow(contadorRow).getCell(contadorCell - 1).getText();


            if (tiempoObtenido.equals(String.valueOf(tiempo))) {
                table.getRow(contadorRow).getCell(contadorCell).setText(lista.get(k).getCreatedAt().toString().split(" ")[0]);
                table.getRow(contadorRow).getCell(contadorCell + 1).setText(lista.get(k).getNombreAnalista());
                try {
                    tiempo = tiempo + Integer.parseInt(lista.get(k + 1).getTiempoExposicion());
                } catch (IndexOutOfBoundsException e) {
                    System.out.println("Límite de EAUV");
                }
                k++;
            } else {
                table.getRow(contadorRow).getCell(contadorCell).setText("N/A");
                table.getRow(contadorRow).getCell(contadorCell + 1).setText("N/A");
            }
        }
        table.getRow(15).getCell(1).setText(fra_eaxe_013.getTiempoTotalExposicion());

        XWPFTable table4 = doc.getTables().get(4);
        table4.getRow(0).getCell(1).setText(fra_eaxe_013.getObservaciones());

        XWPFTable table5 = doc.getTables().get(5);
        table5.getRow(1).getCell(0).removeParagraph(0);
        XWPFParagraph paragraph = table5.getRow(1).getCell(0).addParagraph();
        paragraph.setAlignment(ParagraphAlignment.CENTER);
        XWPFRun run = paragraph.createRun();
        InputStream inputStream = new URL(fra_eaxe_013.getRubricaRealizo()).openStream();
        XWPFPicture xwpfPicture = run.addPicture(inputStream, XWPFDocument.PICTURE_TYPE_PNG, "Name", Units.pixelToEMU(110), Units.pixelToEMU(73));
        run.addBreak();
        run.setText(fra_eaxe_013.getRealizo());
        table5.getRow(1).getCell(1).setText(fra_eaxe_013.getSupervisor());

        List<FRA_EAXE_013_DATA> listaAuxiliar = new ArrayList<>();

        int tiempoExpo = 0;
        int tiempoAux;
        for (FRA_EAXE_013_DATA fra_eaxe_013_data : lista) {
            tiempoAux = Integer.parseInt(fra_eaxe_013_data.getTiempoExposicion());
            tiempoExpo = tiempoAux + tiempoExpo;
            fra_eaxe_013_data.setTiempoExposicion(tiempoExpo + "");
            if (fra_eaxe_013_data.getImagenSeleccionada().equals("Si")) {
                listaAuxiliar.add(fra_eaxe_013_data);
            }
        }

        XWPFTable tablaImagenes = doc.createTable();
        XWPFTableRow rowPicture;
        int cont = 0;
        int cont2 = 0;
        int aux = 0;

        int tiempoExposición = 0;
        while (true) {
            if (cont2 == 0) {
                rowPicture = tablaImagenes.getRow(cont2);
                tablaImagenes.removeBorders();
            } else {
                rowPicture = tablaImagenes.createRow();
                tablaImagenes.removeBorders();
            }

            for (int j = 0; j < 4; j++) {
                if (j == 0 && cont2 == 0) {
                    XWPFParagraph paragraph1 = rowPicture.getCell(0).addParagraph();
                    XWPFRun run1 = paragraph1.createRun();
                    InputStream inputStream1 = new URL(listaAuxiliar.get(cont).getPathImage()).openStream();

                    XWPFPicture picture = run1.addPicture(inputStream1, XWPFDocument.PICTURE_TYPE_PNG, "Name", Units.pixelToEMU(150), Units.pixelToEMU(100));
                    run1.addBreak();
                    run1.setText("Tiempo exposición: " + listaAuxiliar.get(cont).getTiempoExposicion() + "(H)");
                }
                if (j != 0 && cont2 == 0) {
                    XWPFParagraph paragraph1 = rowPicture.addNewTableCell().addParagraph();
                    XWPFRun run1 = paragraph1.createRun();
                    InputStream inputStream1 = new URL(listaAuxiliar.get(cont).getPathImage()).openStream();

                    XWPFPicture picture = run1.addPicture(inputStream1, XWPFDocument.PICTURE_TYPE_PNG, "Name", Units.pixelToEMU(150), Units.pixelToEMU(100));
                    run1.addBreak();
                    run1.setText("Tiempo exposición: " + listaAuxiliar.get(cont).getTiempoExposicion() + "(H)");
                }
                if (j == 0 && cont2 != 0) {
                    XWPFParagraph paragraph1 = rowPicture.getCell(j).addParagraph();
                    XWPFRun run1 = paragraph1.createRun();
                    InputStream inputStream1 = new URL(listaAuxiliar.get(cont).getPathImage()).openStream();

                    XWPFPicture picture = run1.addPicture(inputStream1, XWPFDocument.PICTURE_TYPE_PNG, "Name", Units.pixelToEMU(150), Units.pixelToEMU(100));
                    run1.addBreak();
                    run1.setText("Tiempo exposición: " + listaAuxiliar.get(cont).getTiempoExposicion() + "(H)");
                }
                if (j != 0 && cont2 != 0) {
                    XWPFParagraph paragraph1 = rowPicture.getCell(j).addParagraph();
                    XWPFRun run1 = paragraph1.createRun();
                    InputStream inputStream1 = new URL(listaAuxiliar.get(cont).getPathImage()).openStream();

                    XWPFPicture picture = run1.addPicture(inputStream1, XWPFDocument.PICTURE_TYPE_PNG, "Name", Units.pixelToEMU(150), Units.pixelToEMU(100));
                    run1.addBreak();
                    run1.setText("Tiempo exposición: " + listaAuxiliar.get(cont).getTiempoExposicion() + "(H)");
                }
                cont++;
                if (cont >= listaAuxiliar.size()) {
                    break;
                }
            }
            if (cont >= listaAuxiliar.size()) {
                break;
            }
            cont2++;
        }

        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Disposition", "inline; filename=FRA-EAXE-"+estructuraNombres.getNombre()+".docx");
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

    public int fragmentoReporte (XWPFDocument doc, XWPFDocument plantilla, int contTabla, int contador, List<FRA_EAXE_013> lista) throws XmlException, IOException {

        XWPFStyles newStyles = doc.createStyles();
        newStyles.setStyles(plantilla.getStyle());
        XWPFParagraph para = doc.createParagraph();
        para.setAlignment(ParagraphAlignment.LEFT);
        para.setStyle("Ttulo2");
        XWPFRun run = para.createRun();
        run.setText("Envejecimiento acelerado por arco xenón");

        /***** INICIO DE TABLA DE MARCA DE EQUIPO *****/
        XWPFTable tabl = doc.createTable();
        tabl.removeRow(0);
        XWPFTable tableDocummento = plantilla.getTables().get(55);
        try {
            tableDocummento.getRow(1).getCell(1).setText("Radiometro Xenón, Q-Lab Corporation CR20/340/D. Cámara de envejecimiento Xe, Q-Lab Corporation QSUN Xe-1-S");
        } catch (NullPointerException e) {
            System.out.println("Ocurrió un error con la tabla 1 de eat");
        }

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
        XWPFTable tableDocumment = plantilla.getTables().get(56);
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
        para1.setAlignment(ParagraphAlignment.LEFT);
        XWPFRun run1 = para1.createRun();
        run1.addBreak();
        contTabla++;

        /***** INICIO DE TÍTULO DE TABLA *****/
        XWPFParagraph para2 = doc.createParagraph();
        para2.setAlignment(ParagraphAlignment.LEFT);
        para2.setStyle("Tablas");
        XWPFRun run2 = para2.createRun();
        run2.setText("Resultados del envejecimiento acelerado por arco xenón.");
        /***** FIN DE TÍTULO DE TABLA *****/


        //TODO: REVISAR

        /***** INICIO DE TABLA DE RESULTADOS *****/
        XWPFTable table_2 = doc.createTable();
        table_2.removeRow(0);
        XWPFTable tableDocumment_2 = plantilla.getTables().get(57);
        CTTbl cTTblTemplate_2 = tableDocumment_2.getCTTbl();
        table_2 = new XWPFTable((CTTbl) cTTblTemplate_2.copy(), doc);
        table_2.removeRow(4);
        table_2.removeRow(3);
        table_2.removeRow(2);
        table_2.removeRow(1);
        for (int k = 0; k < contador; k++) {
            try {
                XWPFTableRow row = table_2.createRow();
                row.getCell(0).setText(lista.get(k).getMetodoMuestra().getSolicitudServicioClienteMuestras().getIdClienteMuestra());
                row.getCell(1).setText(lista.get(k).getTiempoTotalExposicion());
            } catch (NullPointerException e) {
                table_2.addRow(tableDocumment_2.getRow(1));
            }
        }

        XWPFTable tableDocumment_4 = plantilla.getTables().get(58);
        try {
            XWPFTableRow row2 = tableDocumment_4.getRow(0);
            row2.getCell(1).setText("N/A");
            table_2.addRow(row2);

            XWPFTableRow row3 = tableDocumment_4.getRow(1);
            row3.getCell(1).setText("N/A");
            table_2.addRow(row3);

            XWPFTableRow row4 = tableDocumment_4.getRow(2);
            row4.getCell(1).setText(lista.get(0).getObservaciones());
            table_2.addRow(row4);
        } catch (NullPointerException | IndexOutOfBoundsException e) {
            table_2.addRow(tableDocumment_4.getRow(2));
        }
        /***** FIN DE TABLA DE RESULTADOS *****/

        doc.setTable(contTabla, table_2);

        XWPFParagraph para3 = doc.createParagraph();
        para3.setAlignment(ParagraphAlignment.LEFT);
        XWPFRun run3 = para3.createRun();
        run3.addBreak();
        run3.setText("Nota: Espectrometría infrarroja por transformada de Fourier.");
        run3.addBreak();
        contTabla++;

        return contTabla;
    }
}
