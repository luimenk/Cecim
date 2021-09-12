package com.demo.service.formatos.metodos;

import com.demo.model.operacion.metodos.fra09tga.FRA_TGA_001;
import com.demo.model.operacion.metodos.fra09tga.datas.FRA_TGA_001_DATA;
import com.demo.model.operacion.metodos.fra14oit.FRA_OIT_001;
import com.demo.model.operacion.metodos.fra18tto.FRA_TTO_001;
import com.demo.model.operacion.metodos.fra18tto.datas.FRA_TTO_001_DATA;
import com.demo.repository.operacion.metodos.fra18tto.FRA_TTO_001_Repository;
import com.demo.repository.operacion.metodos.fra18tto.datas.FRA_TTO_001_DATA_Repository;
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

import java.io.*;
import java.math.BigInteger;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

@Service
public class FRA_18_TTO_Print {

    @Autowired
    private FRA_TTO_001_Repository fra_tto_001_repository;

    @Autowired
    private FRA_TTO_001_DATA_Repository fra_tto_001_data_repository;

    EstructuraNombres estructuraNombres = new EstructuraNombres();
    FormatoFechas formatoFechas = new FormatoFechas();

    public ResponseEntity<InputStreamResource> crearFormato(Long id, int band) throws InvalidFormatException, IOException {
        URL url = new URL("https://resources.adpmx.com/cecim/laboratorio/doc/register/methods/18-FRA-TTO-001.docx");
        XWPFDocument doc = new XWPFDocument(url.openStream());

        FRA_TTO_001 fra_tto_001;
        List<FRA_TTO_001_DATA> lista;
        if (band == 1) {
            fra_tto_001 = fra_tto_001_repository.findByIdFRATTO(id);
        } else {
            fra_tto_001 = fra_tto_001_repository.findByMetodoMuestra_MetodoMuestraId(id);
        }
        lista = fra_tto_001_data_repository.buscarTodosPorEnsayo(fra_tto_001.getIdFRATTO());

        XWPFTable table0 = doc.getTables().get(0);
        table0.getRow(0).getCell(1).setText(fra_tto_001.getFolioTecnica());

        XWPFTable table1 = doc.getTables().get(1);
        table1.getRow(0).getCell(1).setText(fra_tto_001.getFolioSolicitudServicioInterno());
        table1.getRow(0).getCell(3).setText(formatoFechas.formateadorFechas(fra_tto_001.getFechaInicioAnalisis()));
        table1.getRow(1).getCell(1).setText(fra_tto_001.getIdInternoMuestra());
        table1.getRow(1).getCell(3).setText(formatoFechas.formateadorFechas(fra_tto_001.getFechaFinalAnalisis()));

        XWPFTable table2 = doc.getTables().get(2);
        table2.getRow(0).getCell(1).setText(fra_tto_001.getTemperatura() + " °C");
        table2.getRow(0).getCell(3).setText(fra_tto_001.getHumedadRelativa() + " %");

        XWPFTable table3 = doc.getTables().get(3);
        table3.getRow(0).getCell(1).setText(fra_tto_001.getTemperatura() + " °C");
        table3.getRow(0).getCell(3).setText(fra_tto_001.getHumedadRelativa() + " %");
        table3.getRow(5).getCell(1).setText(fra_tto_001.getMascarillaCeldaA());
        table3.getRow(5).getCell(3).setText(fra_tto_001.getMascarillaCeldaB());
        table3.getRow(6).getCell(1).setText(fra_tto_001.getPresionBarometrica());

        XWPFTable table4 = doc.getTables().get(4);
        XWPFTable table5 = doc.getTables().get(5);
        for (int i = 0; i < lista.size(); i++) {
            table4.getRow(i + 2).getCell(1).setText(lista.get(i).getE1());
            table4.getRow(i + 2).getCell(2).setText(lista.get(i).getE2());
            table4.getRow(i + 2).getCell(3).setText(lista.get(i).getE3());
            table4.getRow(i + 2).getCell(4).setText(lista.get(i).getE4());
            table4.getRow(i + 2).getCell(5).setText(lista.get(i).getE5());
            table4.getRow(i + 2).getCell(6).setText(lista.get(i).getPromedio());

            table5.getRow(i + 2).getCell(1).setText(lista.get(i).getCc());
            table5.getRow(i + 2).getCell(2).setText(lista.get(i).getMol());
        }

        XWPFTable table6 = doc.getTables().get(6);
        table6.getRow(0).getCell(1).setText(fra_tto_001.getObservaciones());

        XWPFTable table7 = doc.getTables().get(7);
        table7.getRow(1).getCell(0).removeParagraph(0);
        XWPFParagraph paragraph = table7.getRow(1).getCell(0).addParagraph();
        paragraph.setAlignment(ParagraphAlignment.CENTER);
        XWPFRun run = paragraph.createRun();
        InputStream inputStream = new URL(fra_tto_001.getRubricaRealizo()).openStream();
        XWPFPicture xwpfPicture = run.addPicture(inputStream, XWPFDocument.PICTURE_TYPE_PNG, "Name", Units.pixelToEMU(110), Units.pixelToEMU(73));
        run.addBreak();
        run.setText(fra_tto_001.getRealizo());
        table7.getRow(1).getCell(1).setText(fra_tto_001.getSupervisor());

        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Disposition", "inline; filename=FRA-TTO-" + estructuraNombres.getNombre() + ".docx");
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

    public int fragmentoReporte(XWPFDocument doc, XWPFDocument plantilla, int contTabla, int contador, List<FRA_TTO_001> lista) throws XmlException, IOException {

        XWPFStyles newStyles = doc.createStyles();
        newStyles.setStyles(plantilla.getStyle());
        XWPFParagraph para = doc.createParagraph();
        para.setAlignment(ParagraphAlignment.LEFT);
        para.setStyle("Ttulo2");
        XWPFRun run = para.createRun();
        run.setText("Determinación de la tasa de transmisión de oxígeno a través de películas plásticas y láminas usando un sensor coulométrico");

        /***** INICIO DE TABLA DE MARCA DE EQUIPO *****/
        XWPFTable tabl = doc.createTable();
        tabl.removeRow(0);
        XWPFTable tableDocummento = plantilla.getTables().get(75);
        try {
            tableDocummento.getRow(1).getCell(1).setText("N/A");
            tableDocummento.getRow(6).getCell(1).setText(lista.get(0).getPresionBarometrica() + " mm Hg");
        } catch (NullPointerException e) {
            System.out.println("Ocurrió un error con la tabla 1");
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
        XWPFTable tableDocumment = plantilla.getTables().get(76);
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
        para1.setAlignment(ParagraphAlignment.LEFT);
        XWPFRun run1 = para1.createRun();
        run1.addBreak();
        contTabla++;

        /***** INICIO DE TÍTULO DE TABLA *****/
        XWPFParagraph para2 = doc.createParagraph();
        para2.setAlignment(ParagraphAlignment.LEFT);
        para2.setStyle("Tablas");
        XWPFRun run2 = para2.createRun();
        run2.setText("Resultados de la determinación de la permeabilidad al oxígeno.");
        /***** FIN DE TÍTULO DE TABLA *****/

        /***** INICIO DE TABLA DE RESULTADOS *****/
        XWPFTable table_2 = doc.createTable();
        table_2.removeRow(0);
        XWPFTable tableDocumment_2 = plantilla.getTables().get(77);
        CTTbl cTTblTemplate_2 = tableDocumment_2.getCTTbl();
        table_2 = new XWPFTable((CTTbl) cTTblTemplate_2.copy(), doc);
        table_2.getRow(0).setRepeatHeader(true);
        table_2.removeRow(4);
        table_2.removeRow(3);
        table_2.removeRow(2);
        table_2.removeRow(1);
        for (int k = 0; k < contador; k++) {
            Double desviacionEstandarA = 0.0;
            Double desviacionEstandarB = 0.0;
            try {
                XWPFTableRow row = table_2.createRow();
                row.getCell(0).setText(lista.get(k).getMetodoMuestra().getSolicitudServicioClienteMuestras().getIdClienteMuestra());
                List<FRA_TTO_001_DATA> lista2 = fra_tto_001_data_repository.buscarTodosPorEnsayo(lista.get(k).getIdFRATTO());
                row.getCell(1).setText("A: " + lista2.get(0).getCc() + "; B: " + lista2.get(1).getCc());
                row.getCell(2).setText("A: " + lista2.get(0).getPromedio() + "; B: " + lista2.get(1).getPromedio());

                desviacionEstandarA =   Math.pow(Double.parseDouble(lista2.get(0).getE1()) - Double.parseDouble(lista2.get(0).getPromedio()), 2) +
                                        Math.pow(Double.parseDouble(lista2.get(0).getE2()) - Double.parseDouble(lista2.get(0).getPromedio()), 2) +
                                        Math.pow(Double.parseDouble(lista2.get(0).getE3()) - Double.parseDouble(lista2.get(0).getPromedio()), 2) +
                                        Math.pow(Double.parseDouble(lista2.get(0).getE4()) - Double.parseDouble(lista2.get(0).getPromedio()), 2) +
                                        Math.pow(Double.parseDouble(lista2.get(0).getE5()) - Double.parseDouble(lista2.get(0).getPromedio()), 2);

                desviacionEstandarB =   Math.pow(Double.parseDouble(lista2.get(1).getE1()) - Double.parseDouble(lista2.get(1).getPromedio()), 2) +
                                        Math.pow(Double.parseDouble(lista2.get(1).getE2()) - Double.parseDouble(lista2.get(1).getPromedio()), 2) +
                                        Math.pow(Double.parseDouble(lista2.get(1).getE3()) - Double.parseDouble(lista2.get(1).getPromedio()), 2) +
                                        Math.pow(Double.parseDouble(lista2.get(1).getE4()) - Double.parseDouble(lista2.get(1).getPromedio()), 2) +
                                        Math.pow(Double.parseDouble(lista2.get(1).getE5()) - Double.parseDouble(lista2.get(1).getPromedio()), 2);

                row.getCell(3).setText("A: " + String.format("%.3f", Math.sqrt((desviacionEstandarA / 5))) + "; B: " + String.format("%.3f", Math.sqrt((desviacionEstandarB / 5))));

            } catch (NullPointerException | IndexOutOfBoundsException e) {
                //table_2.addRow(tableDocumment_2.getRow(1));
            }
        }

        XWPFTable tableDocumment_4 = plantilla.getTables().get(78);
        try {
            XWPFTableRow row2 = tableDocumment_4.getRow(0);
            row2.getCell(1).setText("N/A");
            row2.getCell(1).getCTTc().getTcPr().addNewGridSpan();
            row2.getCell(1).getCTTc().getTcPr().getGridSpan().setVal(BigInteger.valueOf((long) 3));
            table_2.addRow(row2);

            XWPFTableRow row3 = tableDocumment_4.getRow(1);
            row3.getCell(1).setText("N/A");
            row3.getCell(1).getCTTc().getTcPr().addNewGridSpan();
            row3.getCell(1).getCTTc().getTcPr().getGridSpan().setVal(BigInteger.valueOf((long) 3));
            table_2.addRow(row3);

            XWPFTableRow row4 = tableDocumment_4.getRow(2);
            row4.getCell(1).setText(lista.get(0).getObservaciones());
            row4.getCell(1).getCTTc().getTcPr().addNewGridSpan();
            row4.getCell(1).getCTTc().getTcPr().getGridSpan().setVal(BigInteger.valueOf((long) 3));
            table_2.addRow(row4);
        } catch (NullPointerException e) {
            table_2.addRow(tableDocumment_4.getRow(2));
        }
        /***** FIN DE TABLA DE RESULTADOS *****/

        doc.setTable(contTabla, table_2);

        XWPFParagraph para3 = doc.createParagraph();
        para3.setAlignment(ParagraphAlignment.LEFT);
        XWPFRun run3 = para3.createRun();
        run3.addBreak();

        contTabla++;

        return contTabla;
    }
}
