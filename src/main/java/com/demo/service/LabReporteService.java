package com.demo.service;

import java.io.*;
import java.lang.reflect.Array;
import java.util.*;

import com.demo.model.Client;
import com.demo.model.Folios;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.xwpf.usermodel.*;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class LabReporteService {

    @Autowired
    private ClientService clientService;

    public ResponseEntity<InputStreamResource> createDocFormTemplate() throws InvalidFormatException, IOException{
        XWPFDocument doc = new XWPFDocument(new FileInputStream("/home/luimenk/IdeaProjects/Cecim/src/main/resources/documentos/LCC-SOC-004.docx"));

        List<Client> lista = clientService.findAll();

        List<String>contactosAux;

        XWPFTable table = doc.getTables().get(2);
        for (int i = 0; i<lista.size(); i++) {
            XWPFTableRow tableRow = table.createRow();
            tableRow.getCell(0).setText(lista.get(i).getFolioCliente());
            tableRow.getCell(1).setText(lista.get(i).getNombreRazonSocial());
            tableRow.getCell(2).setText(lista.get(i).getNombreComunEmpresa());
            String direccion = lista.get(i).getCalle() + " " +
                               lista.get(i).getNumero() + " " +
                               lista.get(i).getColonia() + " " +
                               lista.get(i).getMunicipio() + " " +
                               lista.get(i).getEstado() + " " +
                               lista.get(i).getCodigoPostal();
            tableRow.getCell(3).setText(direccion);
            tableRow.getCell(4).setText(lista.get(i).getRfc());
            contactosAux = new ArrayList<>(Arrays.asList(lista.get(i).getContactosDatos().split(",")));
            try {
                JSONArray jsonArray = new JSONArray(lista.get(i).getContactosDatos());
                tableRow.getCell(5).setText(getAttributeContactos(jsonArray,"nombrePersonaContacto"));
                tableRow.getCell(6).setText(getAttributeContactos(jsonArray,"email"));
                tableRow.getCell(7).setText(getAttributeContactos(jsonArray,"telefonoCelular"));
            } catch (JSONException e) {
                System.out.println("e: "+e);
            }
        }

        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Disposition", "inline; filename=LCC-SOC-"+getNombre()+".docx");
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

    public ResponseEntity<InputStreamResource> createDocFCC_SOC(Long clientId) throws InvalidFormatException, IOException{
        XWPFDocument doc = new XWPFDocument(new FileInputStream("/home/luimenk/IdeaProjects/Cecim/src/main/resources/documentos/FCC-SOC-002.docx"));

        List<Client> lista = clientService.findAll();
        Client client = clientService.findById(clientId);

        List<String>contactosAux;

        XWPFTable table3 = doc.getTables().get(3);
        table3.getRow(0).getCell(1).setText(client.getNombreRazonSocial());
        table3.getRow(1).getCell(1).setText(client.getNombreComunEmpresa());
        String direccion =  client.getCalle() + " " +
                            client.getNumero() + " " +
                            client.getColonia() + " " +
                            client.getMunicipio() + " " +
                            client.getEstado() + " " +
                            client.getCodigoPostal();
        table3.getRow(2).getCell(1).setText(direccion);
        table3.getRow(3).getCell(1).setText(client.getRfc());

        contactosAux = new ArrayList<>(Arrays.asList(client.getContactosDatos().split(",")));

        int bandera = 0;

        XWPFTable table4 = doc.getTables().get(4);
        try {
            JSONArray jsonArray = new JSONArray(client.getContactosDatos());
            table4.getRow(0).getCell(1).setText(getAttributeContacto(bandera, jsonArray,"nombrePersonaContacto"));
            table4.getRow(1).getCell(1).setText(getAttributeContacto(bandera, jsonArray,"cargo"));
            table4.getRow(2).getCell(1).setText(getAttributeContacto(bandera, jsonArray,"email"));
            table4.getRow(3).getCell(1).setText(getAttributeContacto(bandera, jsonArray,"telefonoOficina"));
            table4.getRow(4).getCell(1).setText(getAttributeContacto(bandera, jsonArray,"telefonoCelular"));
        } catch (JSONException e) {
            System.out.println("e: "+e);
        }

        bandera++;

        XWPFTable table5 = doc.getTables().get(5);
        try {
            JSONArray jsonArray = new JSONArray(client.getContactosDatos());
            table5.getRow(0).getCell(1).setText(getAttributeContacto(bandera, jsonArray,"nombrePersonaContacto"));
            table5.getRow(1).getCell(1).setText(getAttributeContacto(bandera, jsonArray,"cargo"));
            table5.getRow(2).getCell(1).setText(getAttributeContacto(bandera, jsonArray,"email"));
            table5.getRow(3).getCell(1).setText(getAttributeContacto(bandera, jsonArray,"telefonoOficina"));
            table5.getRow(4).getCell(1).setText(getAttributeContacto(bandera, jsonArray,"telefonoCelular"));
        } catch (JSONException e) {
            System.out.println("e: "+e);
        }

        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Disposition", "inline; filename=FCC-SOC-"+getNombre()+".docx");
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

    private String getAttributeContactos(JSONArray contactosAux,String attribute) throws JSONException {
        StringBuilder contactos= new StringBuilder();
        JSONObject jsonObject;
        for(int i=0; i<contactosAux.length(); i++){
            jsonObject= (JSONObject) contactosAux.get(i);
            contactos.append(jsonObject.get(attribute)).append("\n\n");
        }
        return contactos.toString();
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

    private String getNombre(){
        String dia, mes, sec, nombre;
        int anio, me, di, ho, mi, se;
        Calendar calendario = new GregorianCalendar();

        anio = calendario.get(Calendar.YEAR);
        me = calendario.get(Calendar.MONTH);
        di = calendario.get(Calendar.DAY_OF_MONTH);
        ho = calendario.get(Calendar.HOUR_OF_DAY);
        mi = calendario.get(Calendar.MINUTE);
        se = calendario.get(Calendar.SECOND);

        me++;

        if (di < 10) {
            dia = "0" + di;
        } else {
            dia = "" + di;
        }
        if (me < 10) {
            mes = "0" + me;
        } else {
            mes = "" + me;
        }
        if (se < 10) {
            sec = "0" + se;
        } else {
            sec = "" + se;
        }

        return "" + anio + mes + dia + "_" + ho + mi + sec;
    }
}
