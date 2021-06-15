package com.demo.service.operacion.metodos;

import com.demo.utils.EstructuraNombres;
import com.demo.utils.FormatoFechas;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.demo.model.operacion.metodos.fra19prr.FRA_PRR_001;
import com.demo.repository.operacion.metodos.FRA_PRR_001_Repository;

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

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

import java.util.List;

@Service
public class FRA_PRR_001_Service {

    @Autowired
    private FRA_PRR_001_Repository fra_prr_001_repository;

    EstructuraNombres estructuraNombres = new EstructuraNombres();
    FormatoFechas formatoFechas = new FormatoFechas();

    private static final Logger LOGGER = LoggerFactory.getLogger("info");

    private static final Logger APP = LoggerFactory.getLogger("info");

    public FRA_PRR_001 save(FRA_PRR_001 fra_prr_001) {
        return fra_prr_001_repository.save(fra_prr_001);
    }

    public List<FRA_PRR_001> findAll() {
        return fra_prr_001_repository.findAll();
    }

    public FRA_PRR_001 findById(Long id) {
        return fra_prr_001_repository.findByIdFRAPRR(id);
    }

    public void delete(Long id) {
        fra_prr_001_repository.deleteById(id);
    }

    public long contar() {
        return fra_prr_001_repository.count();
    }

    public ResponseEntity<InputStreamResource> crearFormato(Long id, int band) throws InvalidFormatException, IOException{
        ClassPathResource resource = new ClassPathResource("/documentos/METODOS/FRA-PRR-001.docx");
        XWPFDocument doc = new XWPFDocument(resource.getInputStream());
        FRA_PRR_001 fra_prr_001;

        if (band == 1){
            fra_prr_001 = fra_prr_001_repository.findByIdFRAPRR(id);
        } else {
            fra_prr_001 = fra_prr_001_repository.findByMetodoMuestra_MetodoMuestraId(id);
        }
        //FRA_PRR_001 fra_prr_001 = fra_prr_001_repository.findByIdFRAPRR(id);

        List<String>contactosAux;
        int bandera=0;

        XWPFTable table0 = doc.getTables().get(0);
        table0.getRow(0).getCell(1).setText(fra_prr_001.getFolioSolicitudServicioInterno());
        table0.getRow(0).getCell(3).setText(fra_prr_001.getIdInternoMuestra());
        table0.getRow(0).getCell(5).setText(formatoFechas.formateadorFechas(fra_prr_001.getFechaInicioAnalisis()));
        table0.getRow(0).getCell(7).setText(formatoFechas.formateadorFechas(fra_prr_001.getFechaFinalAnalisis()));

        XWPFTable table1 = doc.getTables().get(1);
        table1.getRow(0).getCell(1).setText(fra_prr_001.getTemperatura());
        table1.getRow(0).getCell(3).setText(fra_prr_001.getHumedadRelativa());
        table1.getRow(0).getCell(5).setText(fra_prr_001.getCodigoPendulo());
        table1.getRow(0).getCell(7).setText(fra_prr_001.getCodigoManometro());
        table1.getRow(0).getCell(9).setText(fra_prr_001.getPrensaEnsayo());

        XWPFTable table2 = doc.getTables().get(2);
        table2.getRow(3).getCell(1).setText(fra_prr_001.getMDespesor11());
        table2.getRow(3).getCell(2).setText(fra_prr_001.getMDespesor12());
        table2.getRow(3).getCell(3).setText(fra_prr_001.getMDespesor13());
        table2.getRow(3).getCell(4).setText(fra_prr_001.getMDEspesorPromedio1());
        table2.getRow(3).getCell(5).setText(fra_prr_001.getMDResistenciaRasgado1());

        table2.getRow(4).getCell(1).setText(fra_prr_001.getMDespesor21());
        table2.getRow(4).getCell(2).setText(fra_prr_001.getMDespesor22());
        table2.getRow(4).getCell(3).setText(fra_prr_001.getMDespesor23());
        table2.getRow(4).getCell(4).setText(fra_prr_001.getMDEspesorPromedio2());
        table2.getRow(4).getCell(5).setText(fra_prr_001.getMDResistenciaRasgado2());

        table2.getRow(5).getCell(1).setText(fra_prr_001.getMDespesor31());
        table2.getRow(5).getCell(2).setText(fra_prr_001.getMDespesor32());
        table2.getRow(5).getCell(3).setText(fra_prr_001.getMDespesor33());
        table2.getRow(5).getCell(4).setText(fra_prr_001.getMDEspesorPromedio3());
        table2.getRow(5).getCell(5).setText(fra_prr_001.getMDResistenciaRasgado3());

        table2.getRow(6).getCell(1).setText(fra_prr_001.getMDespesor41());
        table2.getRow(6).getCell(2).setText(fra_prr_001.getMDespesor42());
        table2.getRow(6).getCell(3).setText(fra_prr_001.getMDespesor43());
        table2.getRow(6).getCell(4).setText(fra_prr_001.getMDEspesorPromedio4());
        table2.getRow(6).getCell(5).setText(fra_prr_001.getMDResistenciaRasgado4());

        table2.getRow(7).getCell(1).setText(fra_prr_001.getMDespesor51());
        table2.getRow(7).getCell(2).setText(fra_prr_001.getMDespesor52());
        table2.getRow(7).getCell(3).setText(fra_prr_001.getMDespesor53());
        table2.getRow(7).getCell(4).setText(fra_prr_001.getMDEspesorPromedio5());
        table2.getRow(7).getCell(5).setText(fra_prr_001.getMDResistenciaRasgado5());

        table2.getRow(8).getCell(1).setText(fra_prr_001.getMDespesor61());
        table2.getRow(8).getCell(2).setText(fra_prr_001.getMDespesor62());
        table2.getRow(8).getCell(3).setText(fra_prr_001.getMDespesor63());
        table2.getRow(8).getCell(4).setText(fra_prr_001.getMDEspesorPromedio6());
        table2.getRow(8).getCell(5).setText(fra_prr_001.getMDResistenciaRasgado6());

        table2.getRow(9).getCell(1).setText(fra_prr_001.getMDespesor71());
        table2.getRow(9).getCell(2).setText(fra_prr_001.getMDespesor72());
        table2.getRow(9).getCell(3).setText(fra_prr_001.getMDespesor73());
        table2.getRow(9).getCell(4).setText(fra_prr_001.getMDEspesorPromedio7());
        table2.getRow(9).getCell(5).setText(fra_prr_001.getMDResistenciaRasgado7());

        table2.getRow(10).getCell(1).setText(fra_prr_001.getMDespesor81());
        table2.getRow(10).getCell(2).setText(fra_prr_001.getMDespesor82());
        table2.getRow(10).getCell(3).setText(fra_prr_001.getMDespesor83());
        table2.getRow(10).getCell(4).setText(fra_prr_001.getMDEspesorPromedio8());
        table2.getRow(10).getCell(5).setText(fra_prr_001.getMDResistenciaRasgado8());

        table2.getRow(11).getCell(1).setText(fra_prr_001.getMDespesor91());
        table2.getRow(11).getCell(2).setText(fra_prr_001.getMDespesor92());
        table2.getRow(11).getCell(3).setText(fra_prr_001.getMDespesor93());
        table2.getRow(11).getCell(4).setText(fra_prr_001.getMDEspesorPromedio9());
        table2.getRow(11).getCell(5).setText(fra_prr_001.getMDResistenciaRasgado9());

        table2.getRow(12).getCell(1).setText(fra_prr_001.getMDespesor101());
        table2.getRow(12).getCell(2).setText(fra_prr_001.getMDespesor102());
        table2.getRow(12).getCell(3).setText(fra_prr_001.getMDespesor103());
        table2.getRow(12).getCell(4).setText(fra_prr_001.getMDEspesorPromedio10());
        table2.getRow(12).getCell(5).setText(fra_prr_001.getMDResistenciaRasgado10());

        table2.getRow(13).getCell(2).setText(fra_prr_001.getMDPromedio());

        table2.getRow(3).getCell(6).setText(fra_prr_001.getTDespesor11());
        table2.getRow(3).getCell(7).setText(fra_prr_001.getTDespesor12());
        table2.getRow(3).getCell(8).setText(fra_prr_001.getTDespesor13());
        table2.getRow(3).getCell(9).setText(fra_prr_001.getTDEspesorPromedio1());
        table2.getRow(3).getCell(10).setText(fra_prr_001.getTDResistenciaRasgado1());

        table2.getRow(4).getCell(6).setText(fra_prr_001.getTDespesor21());
        table2.getRow(4).getCell(7).setText(fra_prr_001.getTDespesor22());
        table2.getRow(4).getCell(8).setText(fra_prr_001.getTDespesor23());
        table2.getRow(4).getCell(9).setText(fra_prr_001.getTDEspesorPromedio2());
        table2.getRow(4).getCell(10).setText(fra_prr_001.getTDResistenciaRasgado2());

        table2.getRow(5).getCell(6).setText(fra_prr_001.getTDespesor31());
        table2.getRow(5).getCell(7).setText(fra_prr_001.getTDespesor32());
        table2.getRow(5).getCell(8).setText(fra_prr_001.getTDespesor33());
        table2.getRow(5).getCell(9).setText(fra_prr_001.getTDEspesorPromedio3());
        table2.getRow(5).getCell(10).setText(fra_prr_001.getTDResistenciaRasgado3());

        table2.getRow(6).getCell(6).setText(fra_prr_001.getTDespesor41());
        table2.getRow(6).getCell(7).setText(fra_prr_001.getTDespesor42());
        table2.getRow(6).getCell(8).setText(fra_prr_001.getTDespesor43());
        table2.getRow(6).getCell(9).setText(fra_prr_001.getTDEspesorPromedio4());
        table2.getRow(6).getCell(10).setText(fra_prr_001.getTDResistenciaRasgado4());

        table2.getRow(7).getCell(6).setText(fra_prr_001.getTDespesor51());
        table2.getRow(7).getCell(7).setText(fra_prr_001.getTDespesor52());
        table2.getRow(7).getCell(8).setText(fra_prr_001.getTDespesor53());
        table2.getRow(7).getCell(9).setText(fra_prr_001.getTDEspesorPromedio5());
        table2.getRow(7).getCell(10).setText(fra_prr_001.getTDResistenciaRasgado5());

        table2.getRow(8).getCell(6).setText(fra_prr_001.getTDespesor61());
        table2.getRow(8).getCell(7).setText(fra_prr_001.getTDespesor62());
        table2.getRow(8).getCell(8).setText(fra_prr_001.getTDespesor63());
        table2.getRow(8).getCell(9).setText(fra_prr_001.getTDEspesorPromedio6());
        table2.getRow(8).getCell(10).setText(fra_prr_001.getTDResistenciaRasgado6());

        table2.getRow(9).getCell(6).setText(fra_prr_001.getTDespesor71());
        table2.getRow(9).getCell(7).setText(fra_prr_001.getTDespesor72());
        table2.getRow(9).getCell(8).setText(fra_prr_001.getTDespesor73());
        table2.getRow(9).getCell(9).setText(fra_prr_001.getTDEspesorPromedio7());
        table2.getRow(9).getCell(10).setText(fra_prr_001.getTDResistenciaRasgado7());

        table2.getRow(10).getCell(6).setText(fra_prr_001.getTDespesor81());
        table2.getRow(10).getCell(7).setText(fra_prr_001.getTDespesor82());
        table2.getRow(10).getCell(8).setText(fra_prr_001.getTDespesor83());
        table2.getRow(10).getCell(9).setText(fra_prr_001.getTDEspesorPromedio8());
        table2.getRow(10).getCell(10).setText(fra_prr_001.getTDResistenciaRasgado8());

        table2.getRow(11).getCell(6).setText(fra_prr_001.getTDespesor91());
        table2.getRow(11).getCell(7).setText(fra_prr_001.getTDespesor92());
        table2.getRow(11).getCell(8).setText(fra_prr_001.getTDespesor93());
        table2.getRow(11).getCell(9).setText(fra_prr_001.getTDEspesorPromedio9());
        table2.getRow(11).getCell(10).setText(fra_prr_001.getTDResistenciaRasgado9());

        table2.getRow(12).getCell(6).setText(fra_prr_001.getTDespesor101());
        table2.getRow(12).getCell(7).setText(fra_prr_001.getTDespesor102());
        table2.getRow(12).getCell(8).setText(fra_prr_001.getTDespesor103());
        table2.getRow(12).getCell(9).setText(fra_prr_001.getTDEspesorPromedio10());
        table2.getRow(12).getCell(10).setText(fra_prr_001.getTDResistenciaRasgado10());

        table2.getRow(13).getCell(4).setText(fra_prr_001.getTDPromedio());

        XWPFTable table3 = doc.getTables().get(3);
        table3.getRow(0).getCell(1).setText(fra_prr_001.getObservaciones());

        XWPFTable table4 = doc.getTables().get(4);
        table4.getRow(1).getCell(0).setText(fra_prr_001.getRealizo());
        table4.getRow(1).getCell(1).setText(fra_prr_001.getSupervisor());

        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Disposition", "inline; filename=FRA-PRR-"+estructuraNombres.getNombre()+".docx");
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
