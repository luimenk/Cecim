package com.demo.service.formatos.metodos;

import com.demo.model.operacion.metodos.fra01at.FRA_AT_001;
import com.demo.repository.operacion.metodos.fra01at.FRA_AT_001_Repository;
import com.demo.utils.EstructuraNombres;
import com.demo.utils.FormatoFechas;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.util.Units;
import org.apache.poi.xwpf.usermodel.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.io.*;
import java.net.URL;

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
}
