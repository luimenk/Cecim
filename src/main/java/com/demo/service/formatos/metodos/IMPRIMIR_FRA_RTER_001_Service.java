package com.demo.service.formatos.metodos;

import java.io.*;
import java.util.*;

import com.demo.model.operacion.metodos.fra20rter.FRA_RTER_001;
import com.demo.service.operacion.metodos.FRA_RTER_001_Service;
import com.demo.utils.EstructuraNombres;
import com.demo.utils.FormatoFechas;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.xwpf.usermodel.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class IMPRIMIR_FRA_RTER_001_Service {

    @Autowired
    private FRA_RTER_001_Service fra_rter_001_service;

    EstructuraNombres estructuraNombres = new EstructuraNombres();
    FormatoFechas formatoFechas = new FormatoFechas();

    public ResponseEntity<InputStreamResource> crearFormato(Long id, int band) throws InvalidFormatException, IOException{
        ClassPathResource resource = new ClassPathResource("/documentos/METODOS/FRA-RTER-001.docx");
        XWPFDocument doc = new XWPFDocument(resource.getInputStream());
        FRA_RTER_001 fra_rter_001;

        if (band == 1){
            fra_rter_001 = fra_rter_001_service.findById(id);
        } else {
            fra_rter_001 = fra_rter_001_service.findByIdMuestra(id);
        }

        //FRA_RTER_001 fra_rter_001 = fra_rter_001_service.findById(id);

        List<String>contactosAux;
        int bandera=0;

        XWPFTable table0 = doc.getTables().get(0);
        table0.getRow(0).getCell(1).setText(fra_rter_001.getFolioSolicitudServicioInterno());
        table0.getRow(0).getCell(3).setText(formatoFechas.formateadorFechas(fra_rter_001.getFechaInicioAnalisis()));
        table0.getRow(1).getCell(1).setText(fra_rter_001.getIdInternoMuestra());
        table0.getRow(1).getCell(3).setText(formatoFechas.formateadorFechas(fra_rter_001.getFechaFinalAnalisis()));

        XWPFTable table1 = doc.getTables().get(1);
        table1.getRow(0).getCell(1).setText(fra_rter_001.getTemperatura());
        table1.getRow(0).getCell(3).setText(fra_rter_001.getHumedadRelativa());
        table1.getRow(1).getCell(1).setText(fra_rter_001.getCodigoEquipoUniversal());
        table1.getRow(1).getCell(3).setText(fra_rter_001.getCodigoMicrometro());
//        table1.getRow(2).getCell(1).setText(fra_rter_001.getVelocidadMordazas());
        table1.getRow(2).getCell(3).setText(fra_rter_001.getDistanciaEntreMordazas());

        //Resultados dirección máquina (MD)
//        XWPFTable table2 = doc.getTables().get(2);
//        table2.getRow(1).getCell(1).setText(fra_rter_001.getMDespesor1());
//        table2.getRow(1).getCell(2).setText(fra_rter_001.getMDfuerzaFluencia1());
//        table2.getRow(1).getCell(3).setText(fra_rter_001.getMDelongacionRuptura1());
//        table2.getRow(1).getCell(4).setText(fra_rter_001.getMDresistenciaTension1());
//        table2.getRow(1).getCell(5).setText(fra_rter_001.getMDmoduloElastico1());
//
//        table2.getRow(2).getCell(1).setText(fra_rter_001.getMDespesor2());
//        table2.getRow(2).getCell(2).setText(fra_rter_001.getMDfuerzaFluencia2());
//        table2.getRow(2).getCell(3).setText(fra_rter_001.getMDelongacionRuptura2());
//        table2.getRow(2).getCell(4).setText(fra_rter_001.getMDresistenciaTension2());
//        table2.getRow(2).getCell(5).setText(fra_rter_001.getMDmoduloElastico2());
//
//        table2.getRow(3).getCell(1).setText(fra_rter_001.getMDespesor3());
//        table2.getRow(3).getCell(2).setText(fra_rter_001.getMDfuerzaFluencia3());
//        table2.getRow(3).getCell(3).setText(fra_rter_001.getMDelongacionRuptura3());
//        table2.getRow(3).getCell(4).setText(fra_rter_001.getMDresistenciaTension3());
//        table2.getRow(3).getCell(5).setText(fra_rter_001.getMDmoduloElastico3());
//
//        table2.getRow(4).getCell(1).setText(fra_rter_001.getMDespesor4());
//        table2.getRow(4).getCell(2).setText(fra_rter_001.getMDfuerzaFluencia4());
//        table2.getRow(4).getCell(3).setText(fra_rter_001.getMDelongacionRuptura4());
//        table2.getRow(4).getCell(4).setText(fra_rter_001.getMDresistenciaTension4());
//        table2.getRow(4).getCell(5).setText(fra_rter_001.getMDmoduloElastico4());
//
//        table2.getRow(5).getCell(1).setText(fra_rter_001.getMDespesor5());
//        table2.getRow(5).getCell(2).setText(fra_rter_001.getMDfuerzaFluencia5());
//        table2.getRow(5).getCell(3).setText(fra_rter_001.getMDelongacionRuptura5());
//        table2.getRow(5).getCell(4).setText(fra_rter_001.getMDresistenciaTension5());
//        table2.getRow(5).getCell(5).setText(fra_rter_001.getMDmoduloElastico5());
//
//        table2.getRow(6).getCell(1).setText(fra_rter_001.getMDpromedioEspesor());
//        table2.getRow(6).getCell(2).setText(fra_rter_001.getMDpromedioFuerzaFluencia());
//        table2.getRow(6).getCell(3).setText(fra_rter_001.getMDpromedioElongacionRuptura());
//        table2.getRow(6).getCell(4).setText(fra_rter_001.getMDpromedioResistenciaTension());
//        table2.getRow(6).getCell(5).setText(fra_rter_001.getMDpromedioModuloElastico());
//
//        //Resultados dirección transversal (TD)
//        XWPFTable table3 = doc.getTables().get(3);
//        table3.getRow(1).getCell(1).setText(fra_rter_001.getTDespesor1());
//        table3.getRow(1).getCell(2).setText(fra_rter_001.getTDfuerzaFluencia1());
//        table3.getRow(1).getCell(3).setText(fra_rter_001.getTDelongacionRuptura1());
//        table3.getRow(1).getCell(4).setText(fra_rter_001.getTDresistenciaTension1());
//        table3.getRow(1).getCell(5).setText(fra_rter_001.getTDmoduloElastico1());
//
//        table3.getRow(2).getCell(1).setText(fra_rter_001.getTDespesor2());
//        table3.getRow(2).getCell(2).setText(fra_rter_001.getTDfuerzaFluencia2());
//        table3.getRow(2).getCell(3).setText(fra_rter_001.getTDelongacionRuptura2());
//        table3.getRow(2).getCell(4).setText(fra_rter_001.getTDresistenciaTension2());
//        table3.getRow(2).getCell(5).setText(fra_rter_001.getTDmoduloElastico2());
//
//        table3.getRow(3).getCell(1).setText(fra_rter_001.getTDespesor3());
//        table3.getRow(3).getCell(2).setText(fra_rter_001.getTDfuerzaFluencia3());
//        table3.getRow(3).getCell(3).setText(fra_rter_001.getTDelongacionRuptura3());
//        table3.getRow(3).getCell(4).setText(fra_rter_001.getTDresistenciaTension3());
//        table3.getRow(3).getCell(5).setText(fra_rter_001.getTDmoduloElastico3());
//
//        table3.getRow(4).getCell(1).setText(fra_rter_001.getTDespesor4());
//        table3.getRow(4).getCell(2).setText(fra_rter_001.getTDfuerzaFluencia4());
//        table3.getRow(4).getCell(3).setText(fra_rter_001.getTDelongacionRuptura4());
//        table3.getRow(4).getCell(4).setText(fra_rter_001.getTDresistenciaTension4());
//        table3.getRow(4).getCell(5).setText(fra_rter_001.getTDmoduloElastico4());
//
//        table3.getRow(5).getCell(1).setText(fra_rter_001.getTDespesor5());
//        table3.getRow(5).getCell(2).setText(fra_rter_001.getTDfuerzaFluencia5());
//        table3.getRow(5).getCell(3).setText(fra_rter_001.getTDelongacionRuptura5());
//        table3.getRow(5).getCell(4).setText(fra_rter_001.getTDresistenciaTension5());
//        table3.getRow(5).getCell(5).setText(fra_rter_001.getTDmoduloElastico5());
//
//        table3.getRow(6).getCell(1).setText(fra_rter_001.getTDpromedioEspesor());
//        table3.getRow(6).getCell(2).setText(fra_rter_001.getTDpromedioFuerzaFluencia());
//        table3.getRow(6).getCell(3).setText(fra_rter_001.getTDpromedioElongacionRuptura());
//        table3.getRow(6).getCell(4).setText(fra_rter_001.getTDpromedioResistenciaTension());
//        table3.getRow(6).getCell(5).setText(fra_rter_001.getTDpromedioModuloElastico());

        XWPFTable table4 = doc.getTables().get(4);
        table4.getRow(0).getCell(1).setText(fra_rter_001.getObservaciones());

        XWPFTable table5 = doc.getTables().get(5);
        table5.getRow(1).getCell(0).setText(fra_rter_001.getRealizo());
        table5.getRow(1).getCell(1).setText(fra_rter_001.getSupervisor());

        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Disposition", "inline; filename=FRA-RTER-"+estructuraNombres.getNombre()+".docx");
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
