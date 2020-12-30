package com.demo.service.formatos;

import java.io.*;
import java.lang.reflect.Array;
import java.util.*;

import com.demo.model.operacion.MetodoMuestra;
import com.demo.model.operacion.SolicitudServicioCliente;
import com.demo.model.operacion.SolicitudServicioClienteMuestras;
import com.demo.service.operacion.MetodoMuestraService;
import com.demo.service.operacion.SolicitudServicioClienteMuestrasService;
import com.demo.service.operacion.SolicitudServicioClienteService;
import com.demo.service.operacion.RecepcionVerificacionRegistroCodificacionService;
import com.demo.utils.EstructuraNombres;
import com.demo.utils.FormatoFechas;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.xwpf.usermodel.*;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTbl;
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

    @Autowired
    private MetodoMuestraService metodoMuestraService;

    EstructuraNombres estructuraNombres = new EstructuraNombres();
    FormatoFechas formatoFechas = new FormatoFechas();

    public ResponseEntity<InputStreamResource> crearFormato(Long id) throws InvalidFormatException, IOException{
        ClassPathResource resource = new ClassPathResource("/documentos/FSS-SOC-003.docx");
        ClassPathResource resource2 = new ClassPathResource("/documentos/plantilla-FSS-SOC-003.docx");
        XWPFDocument doc = new XWPFDocument(resource.getInputStream());
        XWPFDocument doc2 = new XWPFDocument(resource2.getInputStream());
        List<SolicitudServicioClienteMuestras> lista = solicitudServicioClienteMuestrasService.findAllBySolicitud(id);
        SolicitudServicioCliente solicitudServicioCliente = solicitudServicioClienteService.findById(id);
        List<String>contactosAux;
        int bandera=0;

        XWPFTable table0 = doc.getTables().get(0);
        table0.getRow(0).getCell(1).setText(solicitudServicioCliente.getServicioUrgente());
        table0.getRow(0).getCell(3).setText(solicitudServicioCliente.getFolioSolitudServicioCliente());

        XWPFTable table1 = doc.getTables().get(1);
        table1.getRow(0).getCell(1).setText(solicitudServicioCliente.getClient().getNombreRazonSocial());
        String direccion =  solicitudServicioCliente.getClient().getCalle() + " " +
                solicitudServicioCliente.getClient().getNumero() + " " +
                solicitudServicioCliente.getClient().getColonia() + " " +
                solicitudServicioCliente.getClient().getMunicipio() + " " +
                solicitudServicioCliente.getClient().getEstado() + " " +
                solicitudServicioCliente.getClient().getCodigoPostal();
        table1.getRow(1).getCell(1).setText(direccion);
        try {
            JSONArray jsonArray = new JSONArray(solicitudServicioCliente.getClient().getContactosDatos());
            table1.getRow(2).getCell(1).setText(getAttributeContacto(bandera, jsonArray,"nombrePersonaContacto"));
            table1.getRow(3).getCell(1).setText(getAttributeContacto(bandera, jsonArray,"cargo"));
            //String telefonos = "Teléfono Oficina: " + getAttributeContacto(bandera, jsonArray,"telefonoOficina") +", Celular: "+ getAttributeContacto(bandera, jsonArray,"telefonoCelular");
            String telefonos = "" + getAttributeContacto(bandera, jsonArray,"telefonoOficina") +", "+ getAttributeContacto(bandera, jsonArray,"telefonoCelular");
            table1.getRow(4).getCell(1).setText(telefonos);
            table1.getRow(5).getCell(1).setText(getAttributeContacto(bandera, jsonArray,"email"));
        } catch (JSONException e) {
            System.out.println("e: " + e);
        }

        XWPFTable table2 = doc.getTables().get(2);
        table2.getRow(1).getCell(0).setText(formatoFechas.formateadorFechas(solicitudServicioCliente.getFechaPago()));
        table2.getRow(1).getCell(1).setText(solicitudServicioCliente.getConfirmacion());

        XWPFTable table3 = doc.getTables().get(3);
        table3.getRow(1).getCell(0).setText(formatoFechas.formateadorFechas(solicitudServicioCliente.getFechaRecepcionMuestras()));
        table3.getRow(1).getCell(1).setText(solicitudServicioCliente.getNombreFirmaReceptor());

        XWPFTable table4 = doc.getTables().get(4);
        table4.getRow(1).getCell(0).setText(formatoFechas.formateadorFechas(solicitudServicioCliente.getFechaCompromisoEntrega()));
        table4.getRow(1).getCell(1).setText(solicitudServicioCliente.getNombreFirmaCalidad());

        XWPFTable table5 = doc.getTables().get(5);
        table5.getRow(0).getCell(1).setText(solicitudServicioCliente.getFolioSolitudServicioCliente());

        int contadorMuestrasTablas = 6;
        /*List<XWPFTable> listaTablas = new ArrayList<>();
        List<XWPFTable> listaTablasDocumment = new ArrayList<>();
        List<CTTbl> listaCTTbl = new ArrayList<>();
        List<XWPFTableRow> listaRows = new ArrayList<>();

        for (int i = 0; i< lista.size(); i++){
            listaTablas.add(i, doc.createTable());
            listaTablas.get(i).removeRow(0); // El default row no es necesario
            listaTablasDocumment.add(i, doc2.getTables().get(6));
            listaCTTbl.add(i, listaTablasDocumment.get(i).getCTTbl());
            listaTablas.set(i, new XWPFTable((CTTbl) listaCTTbl.get(i).copy(), doc));
            listaTablas.get(i).removeRow(2);
            listaTablas.get(i).removeRow(1);
            listaRows.add(listaTablasDocumment.get(i).getRow(1));
            int numero = i+1;
            listaRows.get(i).getCell(0).setText(numero+"");
            listaRows.get(i).getCell(1).setText(lista.get(i).getIdClienteMuestra());
            listaRows.get(i).getCell(2).setText(lista.get(i).getTipoMuestra());
            listaRows.get(i).getCell(3).setText(lista.get(i).getDescripcionMuestra());
            listaRows.get(i).getCell(4).setText(lista.get(i).getLote());

            List<MetodoMuestra> lista2 = metodoMuestraService.findAllByMuestra(lista.get(i).getSolicitudServicioClienteMuestrasId());

            XWPFParagraph paragraph1 = listaRows.get(i).getCell(5).addParagraph();
            XWPFRun run1 = paragraph1.createRun();

            for (MetodoMuestra metodoMuestra : lista2) {
                run1.setText(metodoMuestra.getMethod().getCodigoMetodo());
                run1.addBreak();
            }

            listaRows.get(i).getCell(6).setText(lista.get(i).getCondicionesEspeciales());

            XWPFParagraph paragraph2 = listaRows.get(i).getCell(7).addParagraph();
            XWPFRun run2 = paragraph2.createRun();

            for (MetodoMuestra metodoMuestra : lista2) {
                run2.setText(metodoMuestra.getMethod().getCantidadTotal());
                run2.addBreak();
            }

            listaRows.get(i).getCell(8).setText(lista.get(i).getObservaciones());

            listaTablas.get(i).addRow(listaRows.get(i));

            listaRows.set(i, listaTablasDocumment.get(i).getRow(2));

            listaTablas.get(i).addRow(listaRows.get(i));

            doc.setTable(contadorMuestrasTablas, listaTablas.get(i));

            XWPFParagraph para3 = doc.createParagraph();
            XWPFRun run3 = para3.createRun();
            run3.addBreak();
            contadorMuestrasTablas++;
        }*/
        for (int i = 0; i< lista.size(); i++){
            XWPFTable table_6 = doc.createTable();
            table_6.removeRow(0); // El default row no es necesario
            XWPFTable tableDocumment_6 = doc2.getTables().get(6);
            CTTbl cTTblTemplate_6 = tableDocumment_6.getCTTbl();
            table_6 = new XWPFTable((CTTbl) cTTblTemplate_6.copy(), doc);
            table_6.removeRow(2);
            //table_6.removeRow(1);
            XWPFTableRow row = table_6.getRow(1);
            row.getCell(0).setText((i+1)+"");
            row.getCell(1).setText(lista.get(i).getIdClienteMuestra());
            row.getCell(2).setText(lista.get(i).getTipoMuestra());
            row.getCell(3).setText(lista.get(i).getDescripcionMuestra());
            row.getCell(4).setText(lista.get(i).getLote());

            List<MetodoMuestra> lista2 = metodoMuestraService.findAllByMuestra(lista.get(i).getSolicitudServicioClienteMuestrasId());

            XWPFParagraph paragraph1 = row.getCell(5).addParagraph();
            XWPFRun run1 = paragraph1.createRun();

            for (MetodoMuestra metodoMuestra : lista2) {
                run1.setText(metodoMuestra.getMethod().getCodigoMetodo());
                run1.addBreak();
            }

            row.getCell(6).setText(lista.get(i).getCondicionesEspeciales());

            XWPFParagraph paragraph2 = row.getCell(7).addParagraph();
            XWPFRun run2 = paragraph2.createRun();

            for (MetodoMuestra metodoMuestra : lista2) {
                run2.setText(metodoMuestra.getMethod().getCantidadTotal());
                run2.addBreak();
            }

            row.getCell(8).setText(lista.get(i).getObservaciones());

            //table_6.addRow(row);

            row = tableDocumment_6.getRow(2);

            table_6.addRow(row);

            doc.setTable(contadorMuestrasTablas, table_6);

            XWPFParagraph para3 = doc.createParagraph();
            XWPFRun run3 = para3.createRun();
            run3.addBreak();
            contadorMuestrasTablas++;
        }

        XWPFTable table_7 = doc.createTable();
        table_7.removeRow(0);
        XWPFTable tableDocumment_7 = doc2.getTables().get(7);
        tableDocumment_7.getRow(0).getCell(1).setText(solicitudServicioCliente.getDevolucionMuestras());
        CTTbl cTTblTemplate_7 = tableDocumment_7.getCTTbl();
        table_7 = new XWPFTable((CTTbl) cTTblTemplate_7.copy(), doc);
        doc.setTable(contadorMuestrasTablas, table_7);

        XWPFParagraph para1 = doc.createParagraph();
        XWPFRun run1 = para1.createRun();
        run1.addBreak();
        run1.setText("Si aplica, se le informa que se cobrarán gastos de envío por concepto de devolución de las muestras.");
        run1.setFontFamily("Century Gothic");
        run1.setFontSize(9);

        XWPFParagraph para2 = doc.createParagraph();
        XWPFRun run2 = para2.createRun();
        run2.setText("Nota: La mayoría de los ensayos son destructivos");
        run2.setFontFamily("Century Gothic");
        run2.setFontSize(9);
        run2.setBold(true);

        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Disposition", "inline; filename=FSS_"+solicitudServicioCliente.getFolioSolitudServicioCliente()+".docx");
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
