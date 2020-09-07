package com.demo.service.operacion.metodos;

import com.demo.utils.EstructuraNombres;
import com.demo.model.operacion.metodos.FRA_DSC;
import com.demo.repository.operacion.metodos.FRA_DSC_Repository;

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
public class FRA_DSC_Service {

    @Autowired
    private FRA_DSC_Repository fra_dsc_repository;

    EstructuraNombres estructuraNombres = new EstructuraNombres();

    private static final Logger LOGGER = LoggerFactory.getLogger("info");

    private static final Logger APP = LoggerFactory.getLogger("info");

    public FRA_DSC save(FRA_DSC fra_dsc) {
        return fra_dsc_repository.save(fra_dsc);
    }

    public List<FRA_DSC> findAll() {
        return fra_dsc_repository.findAll();
    }

    public FRA_DSC findById(Long id) {
        return fra_dsc_repository.findByIdFRADSC(id);
    }

    public void delete(Long id) {
        fra_dsc_repository.deleteById(id);
    }

    public long contar() {
        return fra_dsc_repository.count();
    }

    public ResponseEntity<InputStreamResource> crearFormato(Long id) throws InvalidFormatException, IOException{
        ClassPathResource resource = new ClassPathResource("/documentos/METODOS/FRA-DSC-001.docx");
        XWPFDocument doc = new XWPFDocument(resource.getInputStream());

        FRA_DSC fra_dsc = fra_dsc_repository.findByIdFRADSC(id);

        XWPFTable table0 = doc.getTables().get(0);
        table0.getRow(0).getCell(1).setText(fra_dsc.getFolioSolicitudServicioInterno());
        table0.getRow(0).getCell(3).setText(fra_dsc.getFechaInicioAnalisis());
        table0.getRow(1).getCell(1).setText(fra_dsc.getIdInternoMuestra());
        table0.getRow(1).getCell(3).setText(fra_dsc.getFechaFinalAnalisis());

        XWPFTable table1 = doc.getTables().get(1);
        table1.getRow(0).getCell(1).setText(fra_dsc.getTemperatura());
        table1.getRow(0).getCell(3).setText(fra_dsc.getHumedadRelativa());
        table1.getRow(1).getCell(1).setText(fra_dsc.getCodigoDSC());
        table1.getRow(1).getCell(3).setText(fra_dsc.getCodigoBalanza());

        XWPFTable table2 = doc.getTables().get(2);
        table2.getRow(1).getCell(1).setText(fra_dsc.getEspesor1());
        table2.getRow(1).getCell(2).setText(fra_dsc.getPeso1());
        table2.getRow(1).getCell(3).setText(fra_dsc.getPpmDSC1());

        XWPFTable table3 = doc.getTables().get(3);
        table3.getRow(1).getCell(1).setText(fra_dsc.getTemperatura1());
        table3.getRow(1).getCell(2).setText(fra_dsc.getFlujoOxigeno1());
        table3.getRow(1).getCell(3).setText(fra_dsc.getFnatmosfera1());
        table3.getRow(1).getCell(4).setText(fra_dsc.getFnproteccion1());

        table3.getRow(2).getCell(1).setText(fra_dsc.getTemperatura2());
        table3.getRow(2).getCell(2).setText(fra_dsc.getFlujoOxigeno2());
        table3.getRow(2).getCell(3).setText(fra_dsc.getFnatmosfera2());
        table3.getRow(2).getCell(4).setText(fra_dsc.getFnproteccion2());

        table3.getRow(3).getCell(1).setText(fra_dsc.getTemperatura3());
        table3.getRow(3).getCell(2).setText(fra_dsc.getFlujoOxigeno3());
        table3.getRow(3).getCell(3).setText(fra_dsc.getFnatmosfera3());
        table3.getRow(3).getCell(4).setText(fra_dsc.getFnproteccion3());

        table3.getRow(4).getCell(1).setText(fra_dsc.getTemperatura4());
        table3.getRow(4).getCell(2).setText(fra_dsc.getFlujoOxigeno4());
        table3.getRow(4).getCell(3).setText(fra_dsc.getFnatmosfera4());
        table3.getRow(4).getCell(4).setText(fra_dsc.getFnproteccion4());

        table3.getRow(5).getCell(1).setText(fra_dsc.getTemperatura5());
        table3.getRow(5).getCell(2).setText(fra_dsc.getFlujoOxigeno5());
        table3.getRow(5).getCell(3).setText(fra_dsc.getFnatmosfera5());
        table3.getRow(5).getCell(4).setText(fra_dsc.getFnproteccion5());

        table3.getRow(6).getCell(1).setText(fra_dsc.getTemperatura6());
        table3.getRow(6).getCell(2).setText(fra_dsc.getFlujoOxigeno6());
        table3.getRow(6).getCell(3).setText(fra_dsc.getFnatmosfera6());
        table3.getRow(6).getCell(4).setText(fra_dsc.getFnproteccion6());

        table3.getRow(7).getCell(1).setText(fra_dsc.getTemperatura7());
        table3.getRow(7).getCell(2).setText(fra_dsc.getFlujoOxigeno7());
        table3.getRow(7).getCell(3).setText(fra_dsc.getFnatmosfera7());
        table3.getRow(7).getCell(4).setText(fra_dsc.getFnproteccion7());

        table3.getRow(8).getCell(1).setText(fra_dsc.getTemperatura8());
        table3.getRow(8).getCell(2).setText(fra_dsc.getFlujoOxigeno8());
        table3.getRow(8).getCell(3).setText(fra_dsc.getFnatmosfera8());
        table3.getRow(8).getCell(4).setText(fra_dsc.getFnproteccion8());

        table3.getRow(9).getCell(1).setText(fra_dsc.getTasaCalentamiento());
        table3.getRow(9).getCell(3).setText(fra_dsc.getTasaEnfriamiento());

        XWPFTable table4 = doc.getTables().get(4);
        table4.getRow(1).getCell(1).setText(fra_dsc.getTemperaturaFusion1());
        table4.getRow(1).getCell(2).setText(fra_dsc.getCalorFusion1());
        table4.getRow(1).getCell(3).setText(fra_dsc.getTemperaturaCristalizacion1());
        table4.getRow(1).getCell(4).setText(fra_dsc.getCalorCristalizacion1());

        XWPFTable table5 = doc.getTables().get(5);
        table5.getRow(0).getCell(1).setText(fra_dsc.getObservaciones());

        XWPFTable table6 = doc.getTables().get(6);
        table6.getRow(1).getCell(0).setText(fra_dsc.getRealizo());
        table6.getRow(1).getCell(1).setText(fra_dsc.getSupervisor());

        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Disposition", "inline; filename=FRA-DSC-"+estructuraNombres.getNombre()+".docx");
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
