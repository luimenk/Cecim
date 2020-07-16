package com.demo.service.formatos;

import java.io.*;
import java.lang.reflect.Array;
import java.util.*;

import com.demo.model.Client;
import com.demo.model.Folios;
import com.demo.model.operacion.SolicitudServicioCliente;
import com.demo.model.operacion.SolicitudServicioClienteMuestras;
import com.demo.service.ClientService;
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
public class FSS_SOC_001_Service {

    @Autowired
    private SolicitudServicioClienteService solicitudServicioClienteService;

    @Autowired
    private SolicitudServicioClienteMuestrasService solicitudServicioClienteMuestrasService;

    EstructuraNombres estructuraNombres = new EstructuraNombres();

    public ResponseEntity<InputStreamResource> crearFormato(Long id) throws InvalidFormatException, IOException{
        ClassPathResource resource = new ClassPathResource("/documentos/FSS-SOC-001.docx");
        XWPFDocument doc = new XWPFDocument(resource.getInputStream());

        List<SolicitudServicioClienteMuestras> lista = solicitudServicioClienteMuestrasService.findAllBySolicitud(id);
        SolicitudServicioCliente solicitudServicioCliente = solicitudServicioClienteService.findById(id);

        List<String>contactosAux;
        int bandera=0;

        XWPFTable table0 = doc.getTables().get(0);
        table0.getRow(0).getCell(1).setText(solicitudServicioCliente.getFolioSolitudServicioCliente());

        XWPFTable table1 = doc.getTables().get(1);
        table1.getRow(0).getCell(1).setText(solicitudServicioCliente.getFechaEnvioMuestras());
        table1.getRow(0).getCell(3).setText(solicitudServicioCliente.getFechaPago());
        table1.getRow(2).getCell(0).setText(solicitudServicioCliente.getNombreFirmaEmisor());
        table1.getRow(2).getCell(1).setText(solicitudServicioCliente.getAlmacenamientoEspecial());
        table1.getRow(3).getCell(1).setText("Especifique: "+ solicitudServicioCliente.getEspecifique());

        XWPFTable table2 = doc.getTables().get(2);
        table2.getRow(0).getCell(1).setText(solicitudServicioCliente.getClient().getNombreRazonSocial());
        String direccion =  solicitudServicioCliente.getClient().getCalle() + " " +
                solicitudServicioCliente.getClient().getNumero() + " " +
                solicitudServicioCliente.getClient().getColonia() + " " +
                solicitudServicioCliente.getClient().getMunicipio() + " " +
                solicitudServicioCliente.getClient().getEstado() + " " +
                solicitudServicioCliente.getClient().getCodigoPostal();
        table2.getRow(1).getCell(1).setText(direccion);

        try {
            JSONArray jsonArray = new JSONArray(solicitudServicioCliente.getClient().getContactosDatos());
            table2.getRow(2).getCell(1).setText(getAttributeContacto(bandera, jsonArray,"nombrePersonaContacto"));
            table2.getRow(3).getCell(1).setText(getAttributeContacto(bandera, jsonArray,"cargo"));
            String telefonos = "Teléfono Oficina: " + getAttributeContacto(bandera, jsonArray,"telefonoOficina") +", Celular: "+ getAttributeContacto(bandera, jsonArray,"telefonoCelular");
            table2.getRow(4).getCell(1).setText(telefonos);
            table2.getRow(5).getCell(1).setText(getAttributeContacto(bandera, jsonArray,"email"));
        } catch (JSONException e) {
            System.out.println("e: " + e);
        }

        XWPFTable table3 = doc.getTables().get(3);
        table3.getRow(0).getCell(1).setText(solicitudServicioCliente.getFechaRecepcionMuestras());
        table3.getRow(0).getCell(3).setText(solicitudServicioCliente.getFechaCompromisoEntrega());
        table3.getRow(2).getCell(0).setText(solicitudServicioCliente.getNombreFirmaReceptor());
        table3.getRow(2).getCell(1).setText(solicitudServicioCliente.getNombreFirmaCalidad());

        XWPFTable table4 = doc.getTables().get(4);
        table4.getRow(0).getCell(1).setText(solicitudServicioCliente.getFolioSolitudServicioCliente());

        XWPFTable table5 = doc.getTables().get(5);
        for (int i = 0; i<lista.size(); i++) {
            XWPFTableRow tableRow = table5.createRow();
            tableRow.getCell(0).setText((i+1)+"");
            tableRow.getCell(1).setText(lista.get(i).getIdClienteMuestra());
            tableRow.getCell(2).setText(lista.get(i).getTipoMuestra());
            tableRow.getCell(3).setText(lista.get(i).getDescripcionMuestra());
            tableRow.getCell(4).setText(lista.get(i).getMethod().getCodigoMetodo());
            tableRow.getCell(5).setText(lista.get(i).getCondicionesEspeciales());
            tableRow.getCell(6).setText(lista.get(i).getMethod().getCantidadTotal());
            tableRow.getCell(7).setText(lista.get(i).getObservaciones());
        }

        XWPFTable table6 = doc.getTables().get(6);
        table6.getRow(1).getCell(1).setText(solicitudServicioCliente.getDevolucionMuestras());

        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Disposition", "inline; filename=FCC-SOC-"+estructuraNombres.getNombre()+".docx");
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
