package com.demo.service.formatos.metodos;

import java.io.*;
import java.lang.reflect.Array;
import java.util.*;

import com.demo.model.Client;
import com.demo.model.Folios;
import com.demo.model.operacion.RecepcionVerificacionRegistroCodificacion;
import com.demo.model.operacion.SolicitudServicioCliente;
import com.demo.model.operacion.SolicitudServicioClienteMuestras;
import com.demo.model.operacion.metodos.FRA_CST_001;
import com.demo.model.operacion.metodos.FRA_DI_001;
import com.demo.model.operacion.metodos.FRA_ES_001;
import com.demo.model.operacion.metodos.FRA_FTIR_001;
import com.demo.service.ClientService;
import com.demo.service.operacion.RecepcionVerificacionRegistroCodificacionService;
import com.demo.service.operacion.SolicitudServicioClienteMuestrasService;
import com.demo.service.operacion.SolicitudServicioClienteService;
import com.demo.service.operacion.metodos.FRA_CST_001_Service;
import com.demo.service.operacion.metodos.FRA_DI_001_Service;
import com.demo.service.operacion.metodos.FRA_ES_001_Service;
import com.demo.service.operacion.metodos.FRA_FTIR_001_Service;
import com.demo.utils.EstructuraNombres;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.xwpf.usermodel.*;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;

@Service
public class IMPRIMIR_FRA_FTIR_001_Service {

    @Autowired
    private FRA_FTIR_001_Service fra_ftir_001_service;

    EstructuraNombres estructuraNombres = new EstructuraNombres();

    public ResponseEntity<InputStreamResource> crearFormato(Long id) throws InvalidFormatException, IOException{
        ClassPathResource resource = new ClassPathResource("/documentos/METODOS/FRA-FTIR-001.docx");
        XWPFDocument doc = new XWPFDocument(resource.getInputStream());

        FRA_FTIR_001 fra_ftir_001 = fra_ftir_001_service.findById(id);

        List<String>contactosAux;
        int bandera=0;

        XWPFTable table0 = doc.getTables().get(0);
        table0.getRow(0).getCell(1).setText(fra_ftir_001.getFolioSolicitudServicioInterno());
        table0.getRow(0).getCell(3).setText(fra_ftir_001.getFechaInicioAnalisis());
        table0.getRow(1).getCell(1).setText(fra_ftir_001.getIdInternoMuestra());
        table0.getRow(1).getCell(3).setText(fra_ftir_001.getFechaFinalAnalisis());

        XWPFTable table1 = doc.getTables().get(1);
        table1.getRow(0).getCell(1).setText(fra_ftir_001.getTemperatura());
        table1.getRow(0).getCell(3).setText(fra_ftir_001.getHumedadRelativa());
        table1.getRow(1).getCell(1).setText(fra_ftir_001.getCodigoEspectrometro());

        XWPFTable table2 = doc.getTables().get(2);
        table2.getRow(1).getCell(0).setText(fra_ftir_001.getCompuesto1());
        table2.getRow(1).getCell(1).setText(fra_ftir_001.getIdentidad1());
        table2.getRow(3).getCell(0).setText(fra_ftir_001.getCompuesto2());
        table2.getRow(3).getCell(1).setText(fra_ftir_001.getIdentidad2());

        XWPFTable table3 = doc.getTables().get(3);
        table3.getRow(0).getCell(1).setText(fra_ftir_001.getObservaciones());

        XWPFTable table4 = doc.getTables().get(4);
        table4.getRow(1).getCell(0).setText(fra_ftir_001.getRealizo());
        table4.getRow(1).getCell(1).setText(fra_ftir_001.getSupervisor());



        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Disposition", "inline; filename=FRA-FTIR-"+estructuraNombres.getNombre()+".docx");
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
