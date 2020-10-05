package com.demo.service.operacion.metodos;

import com.demo.model.operacion.metodos.FRA_DSC;
import com.demo.model.operacion.metodos.FRA_HUM_001;
import com.demo.model.operacion.metodos.FRA_NCP_001;
import com.demo.repository.operacion.metodos.FRA_NCP_001_Repository;
import com.demo.repository.operacion.metodos.FRA_PO_001_Repository;
import com.demo.utils.EstructuraNombres;
import com.demo.model.operacion.metodos.FRA_PO_001;
import com.demo.repository.operacion.metodos.FRA_HUM_001_Repository;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;

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
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFTable;

@Service
public class FRA_NCP_001_Service {
    @Autowired
    private FRA_NCP_001_Repository fra_ncp_001_repository;

    EstructuraNombres estructuraNombres = new EstructuraNombres();

    private static final Logger LOGGER = LoggerFactory.getLogger("info");

    private static final Logger APP = LoggerFactory.getLogger("info");

    public FRA_NCP_001 save(FRA_NCP_001 fra_ncp_001) {
        return fra_ncp_001_repository.save(fra_ncp_001);
    }

    public List<FRA_NCP_001> findAll() {
        return fra_ncp_001_repository.findAll();
    }

    public FRA_NCP_001 findById(Long id) {
        return fra_ncp_001_repository.findByIdFRANCP(id);
    }

    public void delete(Long id) {
        fra_ncp_001_repository.deleteById(id);
    }

    public long contar() {
        return fra_ncp_001_repository.count();
    }

    public ResponseEntity<InputStreamResource> crearFormato(Long id) throws InvalidFormatException, IOException{
        ClassPathResource resource = new ClassPathResource("/documentos/METODOS/FRA-NCP-001.docx");
        XWPFDocument doc = new XWPFDocument(resource.getInputStream());

        FRA_NCP_001 fra_ncp_001 = fra_ncp_001_repository.findByIdFRANCP(id);

        XWPFTable table0 = doc.getTables().get(0);
        table0.getRow(0).getCell(1).setText(fra_ncp_001.getFolioSolicitudServicioInterno());
        table0.getRow(0).getCell(3).setText(fra_ncp_001.getFechaInicioAnalisis());
        table0.getRow(1).getCell(1).setText(fra_ncp_001.getIdInternoMuestra());
        table0.getRow(1).getCell(3).setText(fra_ncp_001.getFechaFinalAnalisis());

        XWPFTable table1 = doc.getTables().get(1);
        table1.getRow(0).getCell(1).setText(fra_ncp_001.getTemperatura());
        table1.getRow(0).getCell(3).setText(fra_ncp_001.getHumedadRelativa());
        table1.getRow(1).getCell(1).setText(fra_ncp_001.getCodigoMicroscopio());
        table1.getRow(1).getCell(3).setText(fra_ncp_001.getCpc());
        table1.getRow(2).getCell(1).setText(fra_ncp_001.getCodigoMicrometro());

        XWPFTable table2 = doc.getTables().get(2);
        table2.getRow(0).getCell(1).setText(fra_ncp_001.getEspesorTotalMuestra());
        table2.getRow(2).getCell(1).setText(fra_ncp_001.getEspesor1());
        table2.getRow(3).getCell(1).setText(fra_ncp_001.getEspesor2());
        table2.getRow(4).getCell(1).setText(fra_ncp_001.getEspesor3());
        table2.getRow(5).getCell(1).setText(fra_ncp_001.getEspesor4());
        table2.getRow(6).getCell(1).setText(fra_ncp_001.getEspesor5());
        table2.getRow(7).getCell(1).setText(fra_ncp_001.getEspesor6());
        table2.getRow(8).getCell(1).setText(fra_ncp_001.getTotal());

        XWPFTable table5 = doc.getTables().get(3);
        table5.getRow(0).getCell(1).setText(fra_ncp_001.getObservaciones());

        XWPFTable table6 = doc.getTables().get(4);
        table6.getRow(1).getCell(0).setText(fra_ncp_001.getRealizo());
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
}
