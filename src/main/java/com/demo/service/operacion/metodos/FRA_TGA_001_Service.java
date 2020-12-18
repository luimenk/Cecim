package com.demo.service.operacion.metodos;

import com.demo.model.operacion.metodos.FRA_AT_001;
import com.demo.model.operacion.metodos.FRA_DSC;
import com.demo.model.operacion.metodos.FRA_TGA_001;
import com.demo.repository.operacion.metodos.FRA_TGA_001_Repository;
import com.demo.utils.EstructuraNombres;
import com.demo.model.operacion.metodos.FRA_HUM_001;
import com.demo.repository.operacion.metodos.FRA_HUM_001_Repository;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;

import com.demo.utils.FormatoFechas;
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
public class FRA_TGA_001_Service {

    @Autowired
    private FRA_TGA_001_Repository fra_tga_001_repository;

    EstructuraNombres estructuraNombres = new EstructuraNombres();
    FormatoFechas formatoFechas = new FormatoFechas();

    private static final Logger LOGGER = LoggerFactory.getLogger("info");

    private static final Logger APP = LoggerFactory.getLogger("info");

    public FRA_TGA_001 save(FRA_TGA_001 fra_tga_001) {
        return fra_tga_001_repository.save(fra_tga_001);
    }

    public List<FRA_TGA_001> findAll() {
        return fra_tga_001_repository.findAll();
    }

    public FRA_TGA_001 findById(Long id) {
        return fra_tga_001_repository.findByIdFRATGA(id);
    }

    public void delete(Long id) {
        fra_tga_001_repository.deleteById(id);
    }

    public long contar() {
        return fra_tga_001_repository.count();
    }

    public ResponseEntity<InputStreamResource> crearFormato(Long id, int band) throws InvalidFormatException, IOException{
        ClassPathResource resource = new ClassPathResource("/documentos/METODOS/FRA-TGA-001.docx");
        XWPFDocument doc = new XWPFDocument(resource.getInputStream());

        FRA_TGA_001 fra_tga_001;
        if (band == 1){
            fra_tga_001 = fra_tga_001_repository.findByIdFRATGA(id);
        } else {
            fra_tga_001 = fra_tga_001_repository.findByMetodoMuestra_MetodoMuestraId(id);
        }

        //FRA_TGA_001 fra_tga_001 = fra_tga_001_repository.findByIdFRATGA(id);

        XWPFTable table0 = doc.getTables().get(0);
        table0.getRow(0).getCell(1).setText(fra_tga_001.getFolioSolicitudServicioInterno());
        table0.getRow(0).getCell(3).setText(formatoFechas.formateadorFechas(fra_tga_001.getFechaInicioAnalisis()));
        table0.getRow(1).getCell(1).setText(fra_tga_001.getIdInternoMuestra());
        table0.getRow(1).getCell(3).setText(formatoFechas.formateadorFechas(fra_tga_001.getFechaFinalAnalisis()));

        XWPFTable table1 = doc.getTables().get(1);
        table1.getRow(0).getCell(1).setText(fra_tga_001.getTemperatura());
        table1.getRow(0).getCell(3).setText(fra_tga_001.getHumedadRelativa());
        table1.getRow(1).getCell(1).setText(fra_tga_001.getCodigoTGA());
        table1.getRow(1).getCell(3).setText(fra_tga_001.getCodigoBalanza());

        XWPFTable table2 = doc.getTables().get(2);
        table2.getRow(1).getCell(1).setText(fra_tga_001.getPeso());
        table2.getRow(1).getCell(2).setText(fra_tga_001.getPpmTGA());

        XWPFTable table3 = doc.getTables().get(3);
        table3.getRow(1).getCell(1).setText(fra_tga_001.getTemperatura1());
        table3.getRow(1).getCell(2).setText(fra_tga_001.getFlujoOxigeno1());
        table3.getRow(1).getCell(3).setText(fra_tga_001.getFnpa1());
        table3.getRow(1).getCell(4).setText(fra_tga_001.getFnpp1());

        table3.getRow(2).getCell(1).setText(fra_tga_001.getTemperatura2());
        table3.getRow(2).getCell(2).setText(fra_tga_001.getFlujoOxigeno2());
        table3.getRow(2).getCell(3).setText(fra_tga_001.getFnpa2());
        table3.getRow(2).getCell(4).setText(fra_tga_001.getFnpp2());

        table3.getRow(3).getCell(1).setText(fra_tga_001.getTemperatura3());
        table3.getRow(3).getCell(2).setText(fra_tga_001.getFlujoOxigeno3());
        table3.getRow(3).getCell(3).setText(fra_tga_001.getFnpa3());
        table3.getRow(3).getCell(4).setText(fra_tga_001.getFnpp3());

        table3.getRow(4).getCell(1).setText(fra_tga_001.getTemperatura4());
        table3.getRow(4).getCell(2).setText(fra_tga_001.getFlujoOxigeno4());
        table3.getRow(4).getCell(3).setText(fra_tga_001.getFnpa4());
        table3.getRow(4).getCell(4).setText(fra_tga_001.getFnpp4());

        table3.getRow(5).getCell(1).setText(fra_tga_001.getTemperatura5());
        table3.getRow(5).getCell(2).setText(fra_tga_001.getFlujoOxigeno5());
        table3.getRow(5).getCell(3).setText(fra_tga_001.getFnpa5());
        table3.getRow(5).getCell(4).setText(fra_tga_001.getFnpp5());

        table3.getRow(6).getCell(1).setText(fra_tga_001.getTasaCalentamiento());
        table3.getRow(6).getCell(3).setText(fra_tga_001.getTasaEnfriamiento());

        XWPFTable table4 = doc.getTables().get(4);
        table4.getRow(1).getCell(1).setText(fra_tga_001.getRangoTemperatura1());
        table4.getRow(1).getCell(2).setText(fra_tga_001.getCambioMasa1());

        table4.getRow(2).getCell(1).setText(fra_tga_001.getRangoTemperatura2());
        table4.getRow(2).getCell(2).setText(fra_tga_001.getCambioMasa2());

        table4.getRow(3).getCell(1).setText(fra_tga_001.getRangoTemperatura3());
        table4.getRow(3).getCell(2).setText(fra_tga_001.getCambioMasa3());

        table4.getRow(4).getCell(1).setText(fra_tga_001.getRangoTemperatura4());
        table4.getRow(4).getCell(2).setText(fra_tga_001.getCambioMasa4());

        XWPFTable table5 = doc.getTables().get(5);
        table5.getRow(0).getCell(1).setText(fra_tga_001.getObservaciones());

        XWPFTable table6 = doc.getTables().get(6);
        table6.getRow(1).getCell(0).setText(fra_tga_001.getRealizo());
        table6.getRow(1).getCell(1).setText(fra_tga_001.getSupervisor());

        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Disposition", "inline; filename=FRA-TGA-"+estructuraNombres.getNombre()+".docx");
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
