package com.demo.service.formatos.metodos;

import com.demo.model.operacion.metodos.fra02di.FRA_DI_001;
import com.demo.model.operacion.metodos.fra03es.FRA_ES_001;
import com.demo.model.operacion.metodos.fra03es.datas.FRA_ES_001_DATA;
import com.demo.repository.operacion.metodos.fra03es.datas.FRA_ES_001_DATA_Repository;
import com.demo.service.operacion.metodos.FRA_ES_001_Service;
import com.demo.utils.EstructuraNombres;
import com.demo.utils.FormatoFechas;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.util.Units;
import org.apache.poi.xwpf.usermodel.*;
import org.apache.xmlbeans.XmlException;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTbl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigInteger;
import java.net.URL;
import java.util.List;

@Service
public class FRA_03_ES_Print {

    @Autowired
    private FRA_ES_001_Service fra_es_001_service;

    @Autowired
    private FRA_ES_001_DATA_Repository fra_es_001_data_repository;

    EstructuraNombres estructuraNombres = new EstructuraNombres();
    FormatoFechas formatoFechas = new FormatoFechas();

    public ResponseEntity<InputStreamResource> crearFormato(Long id, int band) throws InvalidFormatException, IOException {

        FRA_ES_001 fra_es_001;
        List<FRA_ES_001_DATA> lista;

        if (band == 1) {
            fra_es_001 = fra_es_001_service.findById(id);
            lista = fra_es_001_data_repository.buscarTodosPorEnsayo(fra_es_001.getIdFRAES());
        } else {
            fra_es_001 = fra_es_001_service.findByMuestra(id);
            lista = fra_es_001_data_repository.buscarTodosPorEnsayo(fra_es_001.getIdFRAES());
        }

        URL url = new URL("https://resources.adpmx.com/cecim/laboratorio/doc/register/methods/03-FRA-ES-001-" + lista.size() + ".docx");
        XWPFDocument doc = new XWPFDocument(url.openStream());

        XWPFTable table0 = doc.getTables().get(0);
        table0.getRow(0).getCell(1).setText(fra_es_001.getFolioTecnica());

        XWPFTable table1 = doc.getTables().get(1);
        table1.getRow(0).getCell(1).setText(fra_es_001.getFolioSolicitudServicioInterno());
        table1.getRow(0).getCell(3).setText(formatoFechas.formateadorFechas(fra_es_001.getFechaInicioAnalisis()));
        table1.getRow(1).getCell(1).setText(fra_es_001.getIdInternoMuestra());
        table1.getRow(1).getCell(3).setText(formatoFechas.formateadorFechas(fra_es_001.getFechaFinalAnalisis()));

        XWPFTable table2 = doc.getTables().get(2);
        table2.getRow(0).getCell(1).setText(fra_es_001.getTemperatura() + " °C");
        table2.getRow(0).getCell(3).setText(fra_es_001.getHumedadRelativa() + " %");
        table2.getRow(1).getCell(1).setText(fra_es_001.getCodigoMicrometro());
        table2.getRow(1).getCell(3).setText(fra_es_001.getNormaReferencia());
        table2.getRow(2).getCell(1).setText(fra_es_001.getAnchoProveta());
        table2.getRow(2).getCell(3).setText(fra_es_001.getNumeroMediciones());

        XWPFTable table3 = doc.getTables().get(3);
        for (int i = 0; i < lista.size(); i++) {
            table3.getRow(i + 1).getCell(1).setText(lista.get(i).getLargo());
            table3.getRow(i + 1).getCell(2).setText(lista.get(i).getAncho());
        }
        table3.getRow(lista.size() + 1).getCell(1).setText(fra_es_001.getPromedioLargo());
        table3.getRow(lista.size() + 1).getCell(2).setText(fra_es_001.getPromedioAncho());


        XWPFTable table4 = doc.getTables().get(4);
        table4.getRow(0).getCell(1).setText(fra_es_001.getObservaciones());

        XWPFTable table5 = doc.getTables().get(5);
        table5.getRow(1).getCell(0).removeParagraph(0);
        XWPFParagraph paragraph = table5.getRow(1).getCell(0).addParagraph();
        paragraph.setAlignment(ParagraphAlignment.CENTER);
        XWPFRun run = paragraph.createRun();
        InputStream inputStream = new URL(fra_es_001.getRubricaRealizo()).openStream();
        XWPFPicture xwpfPicture = run.addPicture(inputStream, XWPFDocument.PICTURE_TYPE_PNG, "Name", Units.pixelToEMU(110), Units.pixelToEMU(73));
        run.addBreak();
        run.setText(fra_es_001.getRealizo());
        table5.getRow(1).getCell(1).setText(fra_es_001.getSupervisor());

        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Disposition", "inline; filename=FRA-ES-" + estructuraNombres.getNombre() + ".docx");
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        doc.write(byteArrayOutputStream);
        doc.close();
        MediaType word = MediaType.valueOf("application/vnd.openxmlformats-officedocument.wordprocessingml.document");
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(byteArrayOutputStream.toByteArray());
        return ResponseEntity.ok()
                .headers(headers)
                .contentType(word)
                .body(new InputStreamResource(byteArrayInputStream));
    }

    public int fragmentoReporte(XWPFDocument doc, XWPFDocument plantilla, int contTabla, int contador, List<FRA_ES_001> lista) throws XmlException, IOException {

        XWPFStyles newStyles = doc.createStyles();
        newStyles.setStyles(plantilla.getStyle());
        XWPFParagraph para = doc.createParagraph();
        para.setStyle("Ttulo2");
        XWPFRun run = para.createRun();
        run.setText("Determinación del espesor");

        /***** INICIO DE TABLA DE MARCA DE EQUIPO *****/
        XWPFTable tabl = doc.createTable();
        tabl.removeRow(0);
        XWPFTable tableDocummento = plantilla.getTables().get(12);
        tableDocummento.getRow(1).getCell(1).setText("Micrómetro, Mitutoyo ID-C112EXBS");
        try {
            tableDocummento.getRow(2).getCell(1).setText(lista.get(0).getMetodoMuestra().getMethod().getCantidadTotal());
        } catch (NullPointerException e) {
            tableDocummento.getRow(2).getCell(1).setText("");
        }
        tableDocummento.getRow(3).getCell(1).setText("3");
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
        XWPFTable tableDocumment = plantilla.getTables().get(13);
        CTTbl cTTblTemplate = tableDocumment.getCTTbl();
        table = new XWPFTable((CTTbl) cTTblTemplate.copy(), doc);
        table.getRow(0).setRepeatHeader(true);
        table.removeRow(2);
        for (int l = 0; l < contador; l++) {
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
            } catch (IndexOutOfBoundsException ex) {
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
        run2.setText("Resultados de la determinación de espesor");
        /***** FIN DE TÍTULO DE TABLA *****/

        /***** INICIO DE TABLA DE RESULTADOS *****/
        XWPFTable table_2 = doc.createTable();
        table_2.removeRow(0);
        XWPFTable tableDocumment_2 = plantilla.getTables().get(14);
        CTTbl cTTblTemplate_2 = tableDocumment_2.getCTTbl();
        table_2 = new XWPFTable((CTTbl) cTTblTemplate_2.copy(), doc);
        table_2.getRow(0).setRepeatHeader(true);
        for (int k = 0; k < contador; k++) {
            try {
                XWPFTableRow row = table_2.createRow();
                row.getCell(0).setText(lista.get(k).getMetodoMuestra().getSolicitudServicioClienteMuestras().getIdClienteMuestra());
                row.createCell();
                row.getCell(1).setText(Double.parseDouble(lista.get(k).getPromedioLargo()) * 3.937 + "");
                row.createCell();
                row.getCell(2).setText(lista.get(k).getPromedioLargo());
            } catch (NullPointerException e) {
                table_2.createRow();
            }
        }

        XWPFTable tableDocumment_4 = plantilla.getTables().get(15);
        CTTbl cTTblTemplate_4 = tableDocumment_4.getCTTbl();
        table_2.addRow(tableDocumment_4.getRow(0));
        table_2.addRow(tableDocumment_4.getRow(1));

        for (int k = 0; k < contador; k++) {
            try {
                XWPFTableRow row = table_2.createRow();
                row.getCell(0).setText(lista.get(k).getMetodoMuestra().getSolicitudServicioClienteMuestras().getIdClienteMuestra());
                row.createCell();
                row.getCell(1).setText(Double.parseDouble(lista.get(k).getPromedioAncho()) * 3.937 + "");
                row.createCell();
                row.getCell(2).setText(lista.get(k).getPromedioAncho());
            } catch (NullPointerException e) {
                table_2.createRow();
            }
        }

        XWPFTable tableDocumment_5 = plantilla.getTables().get(16);
        try {
            XWPFTableRow row2 = tableDocumment_4.getRow(0);
            row2.getCell(1).setText("N/A");
            row2.getCell(1).getCTTc().getTcPr().addNewGridSpan();
            row2.getCell(1).getCTTc().getTcPr().getGridSpan().setVal(BigInteger.valueOf((long) 2));
            table_2.addRow(row2);

            XWPFTableRow row3 = tableDocumment_4.getRow(1);
            row3.getCell(1).setText("N/A");
            row3.getCell(1).getCTTc().getTcPr().addNewGridSpan();
            row3.getCell(1).getCTTc().getTcPr().getGridSpan().setVal(BigInteger.valueOf((long) 2));
            table_2.addRow(row3);

            XWPFTableRow row4 = tableDocumment_4.getRow(2);
            row4.getCell(1).setText(lista.get(0).getObservaciones());
            row4.getCell(1).getCTTc().getTcPr().addNewGridSpan();
            row4.getCell(1).getCTTc().getTcPr().getGridSpan().setVal(BigInteger.valueOf((long) 2));
            table_2.addRow(row4);
        } catch (NullPointerException e) {
            table_2.addRow(tableDocumment_5.getRow(2));
        }

        /***** FIN DE TABLA DE RESULTADOS *****/


        doc.setTable(contTabla, table_2);

        XWPFParagraph para3 = doc.createParagraph();
        XWPFRun run3 = para3.createRun();
        run3.addBreak();
        contTabla++;
        return contTabla;
    }
}
