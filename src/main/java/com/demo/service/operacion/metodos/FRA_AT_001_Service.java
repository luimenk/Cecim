package com.demo.service.operacion.metodos;

import com.demo.model.operacion.metodos.FRA_AT_001;
import com.demo.model.operacion.metodos.FRA_HUM_001;
import com.demo.repository.operacion.metodos.FRA_HUM_001_Repository;
import com.demo.utils.EstructuraNombres;
import com.demo.model.operacion.metodos.FRA_AT_001;
import com.demo.repository.operacion.metodos.FRA_AT_001_Repository;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;

import com.demo.utils.FormatoFechas;
import org.apache.poi.util.Units;
import org.apache.poi.xwpf.usermodel.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;

@Service
public class FRA_AT_001_Service {

    @Autowired
    private FRA_AT_001_Repository fra_at_001_repository;

    EstructuraNombres estructuraNombres = new EstructuraNombres();
    FormatoFechas formatoFechas = new FormatoFechas();

    private static final Logger LOGGER = LoggerFactory.getLogger("info");

    private static final Logger APP = LoggerFactory.getLogger("info");

    public FRA_AT_001 save(FRA_AT_001 fra_at_001) {
        return fra_at_001_repository.save(fra_at_001);
    }

    public List<FRA_AT_001> findAll() {
        return fra_at_001_repository.findAll();
    }

    public FRA_AT_001 findById(Long id) {
        return fra_at_001_repository.findByIdFRAAT(id);
    }

    public void delete(Long id) {
        fra_at_001_repository.deleteById(id);
    }

    public long contar() {
        return fra_at_001_repository.count();
    }

    public ResponseEntity<InputStreamResource> crearFormato(Long id, int band) throws InvalidFormatException, IOException{
        ClassPathResource resource = new ClassPathResource("/documentos/METODOS/FRA-AT-001.docx");
        XWPFDocument doc = new XWPFDocument(resource.getInputStream());

        FRA_AT_001 fra_at_001;
        if (band == 1){
            fra_at_001 = fra_at_001_repository.findByIdFRAAT(id);
        } else {
            fra_at_001 = fra_at_001_repository.findByMetodoMuestra_MetodoMuestraId(id);
        }

        XWPFTable table0 = doc.getTables().get(0);
        table0.getRow(0).getCell(1).setText(fra_at_001.getFolioSolicitudServicioInterno());
        table0.getRow(0).getCell(3).setText(formatoFechas.formateadorFechas(fra_at_001.getFechaInicioAnalisis()));
        table0.getRow(1).getCell(1).setText(fra_at_001.getIdInternoMuestra());
        table0.getRow(1).getCell(3).setText(formatoFechas.formateadorFechas(fra_at_001.getFechaFinalAnalisis()));

        XWPFTable table1 = doc.getTables().get(1);
        table1.getRow(0).getCell(1).setText(fra_at_001.getTemperatura());
        table1.getRow(0).getCell(3).setText(fra_at_001.getHumedadRelativa());

        XWPFTable table2 = doc.getTables().get(2);
        XWPFParagraph paragraph = table2.getRow(1).getCell(1).addParagraph();
        paragraph.setAlignment(ParagraphAlignment.CENTER);
        XWPFRun run = paragraph.createRun();
        FileInputStream fis = new FileInputStream(fra_at_001.getZona1());
        XWPFPicture picture = run.addPicture(fis, XWPFDocument.PICTURE_TYPE_PNG, "Name", Units.pixelToEMU(150), Units.pixelToEMU(100));

        XWPFParagraph paragraph2 = table2.getRow(2).getCell(1).addParagraph();
        paragraph2.setAlignment(ParagraphAlignment.CENTER);
        XWPFRun run2 = paragraph2.createRun();
        FileInputStream fis2 = new FileInputStream(fra_at_001.getZona2());
        XWPFPicture picture2 = run2.addPicture(fis2, XWPFDocument.PICTURE_TYPE_PNG, "Name", Units.pixelToEMU(150), Units.pixelToEMU(100));

        XWPFParagraph paragraph3 = table2.getRow(3).getCell(1).addParagraph();
        paragraph3.setAlignment(ParagraphAlignment.CENTER);
        XWPFRun run3 = paragraph3.createRun();
        FileInputStream fis3 = new FileInputStream(fra_at_001.getZona3());
        XWPFPicture picture3 = run3.addPicture(fis3, XWPFDocument.PICTURE_TYPE_PNG, "Name", Units.pixelToEMU(150), Units.pixelToEMU(100));

        table2.getRow(1).getCell(2).setText(fra_at_001.getDesprendimiento1());
        table2.getRow(2).getCell(2).setText(fra_at_001.getDesprendimiento2());
        table2.getRow(3).getCell(2).setText(fra_at_001.getDesprendimiento3());

        table2.getRow(4).getCell(1).setText(fra_at_001.getAtp());

        XWPFTable table5 = doc.getTables().get(3);
        table5.getRow(0).getCell(1).setText(fra_at_001.getObservaciones());

        XWPFTable table6 = doc.getTables().get(4);
        table6.getRow(1).getCell(0).setText(fra_at_001.getRealizo());
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
