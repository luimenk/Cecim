package com.demo.service.formatos;

import java.io.*;
import java.lang.reflect.Array;
import java.util.*;

import com.demo.model.Client;
import com.demo.model.Folios;
import com.demo.model.operacion.MetodoMuestra;
import com.demo.model.operacion.RecepcionVerificacionRegistroCodificacion;
import com.demo.model.operacion.SolicitudServicioCliente;
import com.demo.model.operacion.SolicitudServicioClienteMuestras;
import com.demo.service.ClientService;
import com.demo.service.operacion.MetodoMuestraService;
import com.demo.service.operacion.RecepcionVerificacionRegistroCodificacionService;
import com.demo.service.operacion.SolicitudServicioClienteMuestrasService;
import com.demo.service.operacion.SolicitudServicioClienteService;
import com.demo.utils.EstructuraNombres;
import com.demo.utils.FormatoFechas;
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
public class FRM_SOC_005_Service {

    @Autowired
    private SolicitudServicioClienteService solicitudServicioClienteService;

    @Autowired
    private SolicitudServicioClienteMuestrasService solicitudServicioClienteMuestrasService;

    @Autowired
    private RecepcionVerificacionRegistroCodificacionService recepcionVerificacionRegistroCodificacionService;

    @Autowired
    private MetodoMuestraService metodoMuestraService;

    EstructuraNombres estructuraNombres = new EstructuraNombres();
    FormatoFechas formatoFechas = new FormatoFechas();

    public ResponseEntity<InputStreamResource> crearFormato(Long id) throws InvalidFormatException, IOException{
        ClassPathResource resource = new ClassPathResource("/documentos/FRM-SOC-005.docx");
        XWPFDocument doc = new XWPFDocument(resource.getInputStream());

        RecepcionVerificacionRegistroCodificacion recepcionVerificacionRegistroCodificacion = recepcionVerificacionRegistroCodificacionService.findById(id);

        List<String>contactosAux;
        int bandera=0;

        XWPFTable table0 = doc.getTables().get(0);
        table0.getRow(0).getCell(1).setText(recepcionVerificacionRegistroCodificacion.getCuentaConEtiqueta());
        table0.getRow(0).getCell(5).setText(recepcionVerificacionRegistroCodificacion.getUtilizoFeim());
        table0.getRow(1).getCell(1).setText(formatoFechas.formateadorFechas(recepcionVerificacionRegistroCodificacion.getSolicitudServicioClienteMuestras().getSolicitudServicioCliente().getFechaRecepcionMuestras()));
        table0.getRow(1).getCell(3).setText(recepcionVerificacionRegistroCodificacion.getSolicitudServicioClienteMuestras().getIdClienteMuestra());
        table0.getRow(2).getCell(1).setText(recepcionVerificacionRegistroCodificacion.getSolicitudServicioClienteMuestras().getDescripcionMuestra());
        table0.getRow(3).getCell(1).setText("por definir");
        table0.getRow(3).getCell(3).setText(recepcionVerificacionRegistroCodificacion.getSolicitudServicioClienteMuestras().getTipoMuestra());
        table0.getRow(4).getCell(3).setText(recepcionVerificacionRegistroCodificacion.getCantidadMuestraEntregada());
        table0.getRow(6).getCell(1).setText(recepcionVerificacionRegistroCodificacion.getSolicitudServicioClienteMuestras().getObservaciones());
        //table0.getRow(4).getCell(1).setText(recepcionVerificacionRegistroCodificacion.getSolicitudServicioClienteMuestras().getMethod().getCantidadTotal());
        //table0.getRow(5).getCell(1).setText(recepcionVerificacionRegistroCodificacion.getSolicitudServicioClienteMuestras().getMethod().getCodigoMetodo());
        //List<MetodoMuestra> lista2 = metodoMuestraService.findAllByMuestra(lista.get(i).getSolicitudServicioClienteMuestrasId());
        List<MetodoMuestra> lista2 = metodoMuestraService.findAllByMuestra(recepcionVerificacionRegistroCodificacion.getSolicitudServicioClienteMuestras().getSolicitudServicioClienteMuestrasId());

        XWPFParagraph paragraph1 = table0.getRow(4).getCell(1).addParagraph();
        XWPFParagraph paragraph2 = table0.getRow(5).getCell(1).addParagraph();

        XWPFRun run1 = paragraph1.createRun();
        XWPFRun run2 = paragraph2.createRun();

        for (MetodoMuestra metodoMuestra : lista2) {
            run1.setText(metodoMuestra.getMethod().getCantidadTotal()+"  ");
            run2.setText(metodoMuestra.getMethod().getCodigoMetodo()+ "  ");
            //run1.addBreak();
            //run2.addBreak();
        }

        XWPFTable table1 = doc.getTables().get(1);
//        table1.getRow(0).getCell(1).setText(formatoFechas.formateadorFechas(recepcionVerificacionRegistroCodificacion.getFechaRecepcion()));
        table1.getRow(0).getCell(1).setText(formatoFechas.formateadorFechas(recepcionVerificacionRegistroCodificacion.getSolicitudServicioClienteMuestras().getSolicitudServicioCliente().getFechaRecepcionMuestras()));
        table1.getRow(0).getCell(3).setText(recepcionVerificacionRegistroCodificacion.getSolicitudServicioClienteMuestras().getSolicitudServicioCliente().getFolioSolitudServicioCliente());
        //table1.getRow(1).getCell(1).setText(recepcionVerificacionRegistroCodificacion.getNombrePersonaRecibe());
        table1.getRow(1).getCell(1).setText(recepcionVerificacionRegistroCodificacion.getSolicitudServicioClienteMuestras().getSolicitudServicioCliente().getNombreFirmaReceptor());
        //table1.getRow(2).getCell(1).setText(recepcionVerificacionRegistroCodificacion.getNombrePersonaEntrega());

        try {
            JSONArray jsonArray = new JSONArray(recepcionVerificacionRegistroCodificacion.getSolicitudServicioClienteMuestras().getSolicitudServicioCliente().getClient().getContactosDatos());
            table1.getRow(2).getCell(1).setText(getAttributeContacto(bandera, jsonArray,"nombrePersonaContacto"));
        } catch (JSONException e) {
            System.out.println("e: " + e);
        }

        table1.getRow(3).getCell(1).setText(recepcionVerificacionRegistroCodificacion.getSolicitudServicioClienteMuestras().getSolicitudServicioCliente().getClient().getNombreRazonSocial());
        table1.getRow(3).getCell(3).setText(recepcionVerificacionRegistroCodificacion.getMedioRecepcion());
        table1.getRow(4).getCell(1).setText(recepcionVerificacionRegistroCodificacion.getIdInternoMuestra1());
        table1.getRow(4).getCell(3).setText(recepcionVerificacionRegistroCodificacion.getIdInternoMuestra2());
        table1.getRow(5).getCell(1).setText(recepcionVerificacionRegistroCodificacion.getCondicionesMuestra1());
        table1.getRow(5).getCell(3).setText(recepcionVerificacionRegistroCodificacion.getCondicionesMuestra2());
        table1.getRow(6).getCell(1).setText(recepcionVerificacionRegistroCodificacion.getCumpleCantidad());
        table1.getRow(6).getCell(3).setText(recepcionVerificacionRegistroCodificacion.getSinoEspecifiqueCantidad());
        table1.getRow(7).getCell(1).setText(recepcionVerificacionRegistroCodificacion.getCantidadMuestraAnalisis());
        table1.getRow(7).getCell(3).setText(recepcionVerificacionRegistroCodificacion.getCantidadMuestraRetencion());
        table1.getRow(8).getCell(1).setText(recepcionVerificacionRegistroCodificacion.getNombrePersonaAcondicionara());
        table1.getRow(8).getCell(3).setText(recepcionVerificacionRegistroCodificacion.getUbicacionMuestraRetencion());

        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Disposition", "inline; filename=FRM_SOC_"+recepcionVerificacionRegistroCodificacion.getSolicitudServicioClienteMuestras().getSolicitudServicioCliente().getFolioSolitudServicioCliente()+".docx");
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

    private String getAttributeContacto(int bandera, JSONArray contactosAux,String attribute) throws JSONException {
        StringBuilder contacto= new StringBuilder();
        JSONObject jsonObject;
        for(int i=0; i<contactosAux.length(); i++){
            jsonObject= (JSONObject) contactosAux.get(i);
            if (attribute.equals("nombrePersonaContacto")){
                if (bandera == i){
                    return jsonObject.get("nombrePersonaContacto").toString();
                }
            }
            if (attribute.equals("cargo")){
                if (bandera == i){
                    return jsonObject.get("cargo").toString();
                }
            }
            if (attribute.equals("email")){
                if (bandera == i){
                    return jsonObject.get("email").toString();
                }
            }
            if (attribute.equals("telefonoOficina")){
                if (bandera == i){
                    return jsonObject.get("telefonoOficina").toString();
                }
            }
            if (attribute.equals("telefonoCelular")){
                if (bandera == i){
                    return jsonObject.get("telefonoCelular").toString();
                }
            }
        }
        return contacto.toString();
    }
}
