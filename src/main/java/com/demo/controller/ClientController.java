package com.demo.controller;

//import com.pmisys.admin.api.service.TokenValidator;

import com.demo.model.ClientAux;
import com.demo.service.FoliosService;
import com.demo.service.LabReporteService;
import com.demo.utils.FixContactsJson;
import com.demo.utils.GeneratePDFReport;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.demo.model.Client;
import com.demo.model.Folios;
import com.demo.service.ClientService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.time.LocalTime;
import java.util.*;

@RestController
@RequestMapping(path = "/client")
public class ClientController {

    @Autowired
    private ClientService clientService;

    @Autowired
    private FoliosService foliosService;

    @Autowired
    private LabReporteService labReporteService;

    private static final Logger APP = LoggerFactory.getLogger("info");
    private static final ObjectMapper MAPPER = new ObjectMapper();

    //ListarTodo
    @RequestMapping(method = RequestMethod.GET)
    @CrossOrigin(origins = "*", methods = {RequestMethod.GET})
    public List<Client> getAll() {
        return clientService.findAll();
    }

    //ListarUnElemento
    @RequestMapping(method = RequestMethod.GET, value = "/{clientId}")
    @CrossOrigin(origins = "*", methods = {RequestMethod.GET})
    public Client get(@PathVariable("clientId") Long clientId) {
        Client client = clientService.findById(clientId);

        if (client == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }

        if (client.getNombreRazonSocial() == null) {
            client.setNombreRazonSocial("");
        }

        return client;
    }

    //GuardarElemento
    @RequestMapping(method = RequestMethod.POST)
    @CrossOrigin(origins = "*", methods = {RequestMethod.POST})
    @ResponseStatus(code = HttpStatus.CREATED)
    public ResponseEntity<?> create(@RequestBody ClientAux client) throws Exception {

        System.out.println(client);

        Folios folios = foliosService.findByNombreFolio("FolioCliente");
        String structureFolio, dia, mes, nombre;
        String consecutivo = folios.getConsecutivo();
        int cont, anio, me, di;
        Calendar calendario = new GregorianCalendar();

        if (consecutivo.length() == 1) {
            consecutivo = "000"+folios.getConsecutivo();
        } else if (consecutivo.length() == 2) {
            consecutivo = "00"+folios.getConsecutivo();
        } else if (consecutivo.length() == 3) {
            consecutivo = "0"+folios.getConsecutivo();
        } else {
            consecutivo = folios.getConsecutivo();
        }

        anio = calendario.get(Calendar.YEAR);
        me = calendario.get(Calendar.MONTH);
        di = calendario.get(Calendar.DAY_OF_MONTH);
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

        nombre = "" + mes + dia + anio + "_";
        //structureFolio = nombre+consecutivo;
        structureFolio = consecutivo;
        cont = Integer.parseInt(folios.getConsecutivo());
        cont++;
        folios.setConsecutivo(cont+"");

        foliosService.save(folios);

        Client client1=new Client(
                structureFolio,
                client.getNombreRazonSocial(),
                client.getNombreComunEmpresa(),
                client.getCalle(),
                client.getNumero(),
                client.getColonia(),
                client.getMunicipio(),
                client.getEstado(),
                client.getCodigoPostal(),
                client.getRfc(),
                client.getContactos().toString());
        System.out.println(client1);
        System.out.println(client1.getContactosDatos());
        clientService.save(client1);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    //EliminarElemento
    @RequestMapping(method = RequestMethod.DELETE, value = "/{clientId}")
    @CrossOrigin(origins = "*", methods = {RequestMethod.DELETE})
    public void delete(@PathVariable("clientId") Long clientId){
        clientService.delete(clientId);
    }

    //GuardarElemento
    /*@RequestMapping(value = "/borrarContacto/{clientId}",method = RequestMethod.POST)
    @CrossOrigin(origins = "*", methods = {RequestMethod.POST})
    @ResponseStatus(code = HttpStatus.CREATED)*/

    @RequestMapping(method = RequestMethod.POST,value = "/borrarContacto/{clientId}")
    @CrossOrigin(origins = "*", methods = {RequestMethod.POST})
    @ResponseStatus(code = HttpStatus.CREATED)
    public ResponseEntity<?> deleteContacts(@PathVariable("clientId") Long clientId,@RequestBody List<Map<String, String>> contactos){
        System.out.println(FixContactsJson.fixJsonContacts(contactos).toString());

        return clientService.actualizarContactos(FixContactsJson.fixJsonContacts(contactos).toString(),clientId);
    }

    /*@RequestMapping(value = "/generadorReporte", method = RequestMethod.POST, produces = MediaType.APPLICATION_PDF_VALUE)
    //public ResponseEntity<InputStreamResource> reporteEncuesta(@RequestParam String param) throws IOException {
    public ResponseEntity<InputStreamResource> reporteEncuesta() throws IOException {

        List<Client> clientes;
        clientes = (List<Client>) clientService.findAll();
        //encuestas = (List<Encuesta>) encuestaI.findByFecha(param);

        ByteArrayInputStream bis = GeneratePDFReport.reporteEncuesta(clientes);

        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Disposition", "inline; filename=clientes.pdf");

        return ResponseEntity.ok()
                .headers(headers)
                .contentType(MediaType.APPLICATION_PDF)
                .body(new InputStreamResource(bis));

    }*/
    /*
    * private ResponseEntity<InputStreamResource> generateFile() throws IOException {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Disposition", "inline; filename=reporte.xlsx");
        ByteArrayOutputStream byteArrayOutputStream=new ByteArrayOutputStream();
        workbook.write(byteArrayOutputStream);
        MediaType excel=MediaType.valueOf("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(byteArrayOutputStream.toByteArray());
        return ResponseEntity.ok()
                .headers(headers)
                .contentType(excel)
                .body(new InputStreamResource(byteArrayInputStream));
    }*/
    @RequestMapping(value = "/generarListaCliente", method = RequestMethod.GET)
    //public ResponseEntity<InputStreamResource> reporteEncuesta(@RequestParam String param) throws IOException {
    public ResponseEntity<InputStreamResource> reporteClientes() throws Exception {
        System.out.println("Se generó LCC-SOC");
        System.out.println(LocalTime.now());

        return labReporteService.createDocFormTemplate();
    }

    @RequestMapping(value = "/imprimirCliente/{clientId}", method = RequestMethod.GET)
    //public ResponseEntity<InputStreamResource> reporteEncuesta(@RequestParam String param) throws IOException {
    public ResponseEntity<InputStreamResource> reporteCliente(@PathVariable("clientId") Long clientId) throws Exception {
        System.out.println("Se generó FCC-SOC");
        System.out.println(LocalTime.now());

        return labReporteService.createDocFCC_SOC(clientId);
    }
}
