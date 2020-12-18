package com.demo.service.formatos;

import java.io.*;
import java.lang.reflect.Array;
import java.util.*;

import com.demo.model.Client;
import com.demo.model.Folios;
import com.demo.model.operacion.RecepcionVerificacionRegistroCodificacion;
import com.demo.model.operacion.SolicitudServicioCliente;
import com.demo.model.operacion.SolicitudServicioClienteMuestras;
import com.demo.service.ClientService;
import com.demo.service.operacion.RecepcionVerificacionRegistroCodificacionService;
import com.demo.service.operacion.SolicitudServicioClienteMuestrasService;
import com.demo.service.operacion.SolicitudServicioClienteService;
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
public class BRMR_MIE_001_Service {

    @Autowired
    private SolicitudServicioClienteService solicitudServicioClienteService;

    @Autowired
    private SolicitudServicioClienteMuestrasService solicitudServicioClienteMuestrasService;

    @Autowired
    private RecepcionVerificacionRegistroCodificacionService recepcionVerificacionRegistroCodificacionService;

    EstructuraNombres estructuraNombres = new EstructuraNombres();

    public ResponseEntity<InputStreamResource> crearFormato() throws InvalidFormatException, IOException{
        ClassPathResource resource = new ClassPathResource("/documentos/BRMR-MIE-003.docx");
        XWPFDocument doc = new XWPFDocument(resource.getInputStream());
        List<RecepcionVerificacionRegistroCodificacion> lista = recepcionVerificacionRegistroCodificacionService.findAll();

        List<String>contactosAux;
        int bandera=0;

        XWPFTable table = doc.getTables().get(0);
        for (int i = 0; i<lista.size(); i++) {
            XWPFTableRow tableRow = table.createRow();
            tableRow.getCell(0).setText((i+1)+"");
            tableRow.getCell(1).setText(lista.get(i).getSolicitudServicioClienteMuestras().getSolicitudServicioCliente().getFolioSolitudServicioCliente());
            tableRow.getCell(2).setText(lista.get(i).getIdInternoMuestra1());
            tableRow.getCell(3).setText(lista.get(i).getSolicitudServicioClienteMuestras().getIdClienteMuestra());
            tableRow.getCell(4).setText(lista.get(i).getUbicacionMuestraRetencion());
            tableRow.getCell(5).setText(lista.get(i).getSolicitudServicioClienteMuestras().getCondicionesEspeciales());
            tableRow.getCell(6).setText(lista.get(i).getCantidadMuestraEntregada());
            tableRow.getCell(7).setText(lista.get(i).getSolicitudServicioClienteMuestras().getObservaciones());
        }

        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Disposition", "inline; filename=BRMR_MIE_"+lista.get(0).getSolicitudServicioClienteMuestras().getSolicitudServicioCliente().getFolioSolitudServicioCliente()+".docx");
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
