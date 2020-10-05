package com.demo.service.operacion.metodos;

import com.demo.model.operacion.metodos.*;
import com.demo.repository.operacion.metodos.FRA_EAT_001_DATA_Repository;
import com.demo.repository.operacion.metodos.FRA_EAT_001_Repository;
import com.demo.repository.operacion.metodos.FRA_HUM_001_Repository;
import com.demo.utils.EstructuraNombres;
import com.demo.model.operacion.metodos.FRA_AT_001;
import com.demo.repository.operacion.metodos.FRA_AT_001_Repository;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;

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
public class FRA_EAT_001_Service {

    @Autowired
    private FRA_EAT_001_Repository fra_eat_001_repository;

    @Autowired
    private FRA_EAT_001_DATA_Repository fra_eat_001_data_repository;

    EstructuraNombres estructuraNombres = new EstructuraNombres();

    private static final Logger LOGGER = LoggerFactory.getLogger("info");

    private static final Logger APP = LoggerFactory.getLogger("info");

    public FRA_EAT_001 save(FRA_EAT_001 fra_eat_001) {
        return fra_eat_001_repository.save(fra_eat_001);
    }

    public List<FRA_EAT_001> findAll() {
        return fra_eat_001_repository.findAll();
    }

    public FRA_EAT_001 findById(Long id) {
        return fra_eat_001_repository.findByIdFRAEAT(id);
    }

    public void delete(Long id) {
        fra_eat_001_repository.deleteById(id);
    }

    public long contar() {
        return fra_eat_001_repository.count();
    }

    public ResponseEntity<InputStreamResource> crearFormato(Long id) throws InvalidFormatException, IOException{
        ClassPathResource resource = new ClassPathResource("/documentos/METODOS/FRA-EAT-001.docx");
        XWPFDocument doc = new XWPFDocument(resource.getInputStream());

        FRA_EAT_001 fra_eat_001 = fra_eat_001_repository.findByIdFRAEAT(id);
        List<FRA_EAT_001_DATA> lista = fra_eat_001_data_repository.findRandPreguntas(id);

        XWPFTable table0 = doc.getTables().get(0);
        table0.getRow(0).getCell(1).setText(fra_eat_001.getFolioSolicitudServicioInterno());
        table0.getRow(0).getCell(3).setText(fra_eat_001.getFechaInicioAnalisis());
        table0.getRow(1).getCell(1).setText(fra_eat_001.getIdInternoMuestra());
        table0.getRow(1).getCell(3).setText(fra_eat_001.getFechaFinalAnalisis());

        XWPFTable table1 = doc.getTables().get(1);
        table1.getRow(0).getCell(1).setText(fra_eat_001.getTemperatura());
        table1.getRow(0).getCell(3).setText(fra_eat_001.getHumedadRelativa());
        table1.getRow(1).getCell(1).setText(fra_eat_001.getTemperaturaEnsayo());
        table1.getRow(1).getCell(3).setText(fra_eat_001.getCodigoHorno());

        int contadorRow=0;
        int contadorCell=1;
        XWPFTable table = doc.getTables().get(2);
        for (int i = 0; i<lista.size(); i++) {
            contadorRow++;
            if (contadorRow == 15){
                contadorRow = 1;
                contadorCell = contadorCell + 2;
            }
            table.getRow(contadorRow).getCell(contadorCell).setText(lista.get(i).getTiempoExposicion());
        }

        XWPFTable table5 = doc.getTables().get(3);
        table5.getRow(0).getCell(1).setText(fra_eat_001.getObservaciones());

        XWPFTable table6 = doc.getTables().get(4);
        table6.getRow(1).getCell(0).setText(fra_eat_001.getRealizo());
        table6.getRow(1).getCell(1).setText(fra_eat_001.getSupervisor());

        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Disposition", "inline; filename=FRA-EAT-"+estructuraNombres.getNombre()+".docx");
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
