package com.demo.service.formatos.metodos;

import com.demo.model.operacion.metodos.fra01at.FRA_AT_001;
import com.demo.repository.operacion.metodos.fra01at.FRA_AT_001_Repository;
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
import java.net.URL;
import java.util.List;

@Service
public class FRA_01_AT_Print {

    @Autowired
    private FRA_AT_001_Repository fra_at_001_repository;

    EstructuraNombres estructuraNombres = new EstructuraNombres();
    FormatoFechas formatoFechas = new FormatoFechas();

    public ResponseEntity<InputStreamResource> crearFormato(Long id, int band) throws InvalidFormatException, IOException {
        URL url = new URL("https://resources.adpmx.com/cecim/laboratorio/doc/register/methods/01-FRA-AT-001.docx");
        XWPFDocument doc = new XWPFDocument(url.openStream());
        //ClassPathResource resource = new ClassPathResource("/documentos/METODOS/FRA-AT-001.docx");
        //XWPFDocument doc = new XWPFDocument(resource.getInputStream());

        FRA_AT_001 fra_at_001;
        if (band == 1){
            fra_at_001 = fra_at_001_repository.findByIdFRAAT(id);
        } else {
            fra_at_001 = fra_at_001_repository.findByMetodoMuestra_MetodoMuestraId(id);
        }

        XWPFTable table = doc.getTables().get(0);
        table.getRow(0).getCell(1).setText(fra_at_001.getFolioTecnica());

        XWPFTable table0 = doc.getTables().get(1);
        table0.getRow(0).getCell(1).setText(fra_at_001.getFolioSolicitudServicioInterno());
        table0.getRow(0).getCell(3).setText(formatoFechas.formateadorFechas(fra_at_001.getFechaInicioAnalisis()));
        table0.getRow(1).getCell(1).setText(fra_at_001.getIdInternoMuestra());
        table0.getRow(1).getCell(3).setText(formatoFechas.formateadorFechas(fra_at_001.getFechaFinalAnalisis()));

        XWPFTable table1 = doc.getTables().get(2);
        table1.getRow(0).getCell(1).setText(fra_at_001.getTemperatura() + " °C");
        table1.getRow(0).getCell(3).setText(fra_at_001.getHumedadRelativa() + " %");

        XWPFTable table2 = doc.getTables().get(3);
        table2.getRow(1).getCell(1).removeParagraph(0);
        XWPFParagraph paragraph = table2.getRow(1).getCell(1).addParagraph();
        paragraph.setAlignment(ParagraphAlignment.CENTER);
        XWPFRun run = paragraph.createRun();
        //FileInputStream fis = new FileInputStream(fra_at_001.getZona1());
        InputStream fis = new URL(fra_at_001.getZona1()).openStream();
        XWPFPicture picture = run.addPicture(fis, XWPFDocument.PICTURE_TYPE_PNG, "Name", Units.pixelToEMU(150), Units.pixelToEMU(100));

        table2.getRow(2).getCell(1).removeParagraph(0);
        XWPFParagraph paragraph2 = table2.getRow(2).getCell(1).addParagraph();
        paragraph2.setAlignment(ParagraphAlignment.CENTER);
        XWPFRun run2 = paragraph2.createRun();
        //FileInputStream fis2 = new FileInputStream(fra_at_001.getZona2());
        InputStream fis2 = new URL(fra_at_001.getZona2()).openStream();
        XWPFPicture picture2 = run2.addPicture(fis2, XWPFDocument.PICTURE_TYPE_PNG, "Name", Units.pixelToEMU(150), Units.pixelToEMU(100));

        table2.getRow(3).getCell(1).removeParagraph(0);
        XWPFParagraph paragraph3 = table2.getRow(3).getCell(1).addParagraph();
        paragraph3.setAlignment(ParagraphAlignment.CENTER);
        XWPFRun run3 = paragraph3.createRun();
        //FileInputStream fis3 = new FileInputStream(fra_at_001.getZona3());
        InputStream fis3 = new URL(fra_at_001.getZona3()).openStream();
        XWPFPicture picture3 = run3.addPicture(fis3, XWPFDocument.PICTURE_TYPE_PNG, "Name", Units.pixelToEMU(150), Units.pixelToEMU(100));

        table2.getRow(1).getCell(2).setText(fra_at_001.getDesprendimiento1());
        table2.getRow(2).getCell(2).setText(fra_at_001.getDesprendimiento2());
        table2.getRow(3).getCell(2).setText(fra_at_001.getDesprendimiento3());

        table2.getRow(4).getCell(1).setText(fra_at_001.getAtp());

        XWPFTable table5 = doc.getTables().get(4);
        table5.getRow(0).getCell(1).setText(fra_at_001.getObservaciones());

        XWPFTable table6 = doc.getTables().get(5);
        table6.getRow(1).getCell(0).removeParagraph(0);
        XWPFParagraph paragraph4 = table6.getRow(1).getCell(0).addParagraph();
        paragraph4.setAlignment(ParagraphAlignment.CENTER);
        XWPFRun run4 = paragraph4.createRun();
        InputStream fis4 = new URL(fra_at_001.getRubricaRealizo()).openStream();
        XWPFPicture picture4 = run4.addPicture(fis4, XWPFDocument.PICTURE_TYPE_PNG, "Name", Units.pixelToEMU(110), Units.pixelToEMU(73));
        run4.addBreak();
        run4.setText(fra_at_001.getRealizo());
        table6.getRow(1).getCell(1).setText(fra_at_001.getSupervisor());

        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Disposition", "inline; filename=FRA-AT-"+estructuraNombres.getNombre()+".docx");
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

    public int fragmentoReporte (XWPFDocument doc, XWPFDocument plantilla, int contTabla, int contador, List<FRA_AT_001> lista) throws XmlException, IOException {

        XWPFStyles newStyles = doc.createStyles();
        newStyles.setStyles(plantilla.getStyle());
        XWPFParagraph para = doc.createParagraph();
        para.setStyle("Ttulo2");
        XWPFRun run = para.createRun();
        run.setText("Determinación de adhesión de tintas");

        XWPFTable tabl = doc.createTable();
        tabl.removeRow(0); // El default row no es necesario
        XWPFTable tableDocummento = plantilla.getTables().get(4);
        tableDocummento.getRow(1).getCell(1).setText("N/A");
        CTTbl cTTblTemplat = tableDocummento.getCTTbl();
        tabl = new XWPFTable((CTTbl) cTTblTemplat.copy(), doc);
        doc.setTable(contTabla, tabl);
        XWPFParagraph para0 = doc.createParagraph();
        XWPFRun run0 = para0.createRun();
        run0.addBreak();
        contTabla++;

        /***** INICIO DE TABLA DE MUESTRAS *****/
        XWPFTable table = doc.createTable();
        table.removeRow(0); // El default row no es necesario
        XWPFTable tableDocumment = plantilla.getTables().get(5);
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
        run2.setText("Resultados de la determinación de adhesión de tintas");
        /***** FIN DE TÍTULO DE TABLA *****/

        /***** INICIO DE TABLA DE RESULTADOS *****/
        XWPFTable table_2 = doc.createTable();
        table_2.removeRow(0); // El default row no es necesario
        XWPFTable tableDocumment_2 = plantilla.getTables().get(6);
        CTTbl cTTblTemplate_2 = tableDocumment_2.getCTTbl();
        table_2 = new XWPFTable((CTTbl) cTTblTemplate_2.copy(), doc);
        table_2.getRow(0).setRepeatHeader(true);
        table_2.removeRow(4);
        table_2.removeRow(3);
        table_2.removeRow(2);
        table_2.removeRow(1);
        for (int k = 0; k < contador; k++) {
            try {
                XWPFTableRow row = table_2.createRow();
                row.getCell(0).setText(lista.get(k).getMetodoMuestra().getSolicitudServicioClienteMuestras().getIdClienteMuestra());
                row.getCell(1).setText(lista.get(k).getAtp());
            } catch (NullPointerException e) {
                table_2.addRow(tableDocumment_2.getRow(1));
            }
        }

        XWPFTable tableDocumment_4 = plantilla.getTables().get(7);
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
        } catch (NullPointerException e) {
            table_2.addRow(tableDocumment_4.getRow(2));
        }

        doc.setTable(contTabla, table_2);

        XWPFParagraph para3 = doc.createParagraph();
        XWPFRun run3 = para3.createRun();
        run3.addBreak();
        contTabla++;
        return contTabla;
    }

}
