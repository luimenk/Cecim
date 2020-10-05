package com.demo.service.operacion;

import com.demo.model.operacion.MetodoMuestra;
import com.demo.model.operacion.SolicitudServicioCliente;
import com.demo.model.operacion.SolicitudServicioClienteMuestras;
import com.demo.model.operacion.metodos.FRA_AT_001;
import com.demo.repository.operacion.MetodoMuestraRepository;
import com.demo.repository.operacion.SolicitudServicioClienteMuestrasRepository;
import com.demo.repository.operacion.SolicitudServicioClienteRepository;

import com.demo.utils.EstructuraNombres;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.util.Units;
import org.apache.poi.xwpf.usermodel.*;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;

@Service
public class SolicitudServicioClienteService {

    @Autowired
    private SolicitudServicioClienteRepository solicitudServicioClienteRepository;

    @Autowired
    private SolicitudServicioClienteMuestrasRepository solicitudServicioClienteMuestrasRepository;

    @Autowired
    private MetodoMuestraRepository metodoMuestraRepository;

    EstructuraNombres estructuraNombres = new EstructuraNombres();

    private static final Logger LOGGER = LoggerFactory.getLogger("info");

    private static final Logger APP = LoggerFactory.getLogger("info");

    public SolicitudServicioCliente save(SolicitudServicioCliente solicitudServicioCliente) {
        return solicitudServicioClienteRepository.save(solicitudServicioCliente);
    }

    public List<SolicitudServicioCliente> findAll() {
        return solicitudServicioClienteRepository.findAll();
    }

    public SolicitudServicioCliente findById(Long id) {
        return solicitudServicioClienteRepository.findBySolicitudServicioClienteId(id);
    }

    public SolicitudServicioCliente findByIdFolio(String folio) {
        return solicitudServicioClienteRepository.findByFolioSolitudServicioCliente(folio);
    }

    public void delete(Long id) {
        solicitudServicioClienteRepository.deleteById(id);
    }

    public long contar() {
        return solicitudServicioClienteRepository.count();
    }

    public ResponseEntity<InputStreamResource> crearFormato(Long id) throws InvalidFormatException, IOException {
        ClassPathResource resource2 = new ClassPathResource("/documentos/FIR-ERAI-002.docx");
        ClassPathResource resource = new ClassPathResource("/documentos/FIR-ERAI-003.docx");
        XWPFDocument plantilla = new XWPFDocument(resource2.getInputStream());
        XWPFDocument doc = new XWPFDocument(resource.getInputStream());

        SolicitudServicioCliente solicitudServicioCliente = solicitudServicioClienteRepository.findBySolicitudServicioClienteId(id);
        List<SolicitudServicioClienteMuestras> solicitudServicioClienteMuestrasLista = solicitudServicioClienteMuestrasRepository.findAllBySolicitudServicioCliente_SolicitudServicioClienteId(id);
        List<MetodoMuestra> metodoMuestraList = metodoMuestraRepository.findAllBySolicitudServicioClienteMuestras_SolicitudServicioCliente(solicitudServicioCliente);

        List<String> contactosAux;
        int bandera = 0;

        XWPFTable table1 = doc.getTables().get(1);
        table1.getRow(0).getCell(1).setText(solicitudServicioCliente.getClient().getNombreRazonSocial());
        String direccion = solicitudServicioCliente.getClient().getCalle() + " " +
                solicitudServicioCliente.getClient().getNumero() + " " +
                solicitudServicioCliente.getClient().getColonia() + " " +
                solicitudServicioCliente.getClient().getMunicipio() + " " +
                solicitudServicioCliente.getClient().getEstado() + " " +
                solicitudServicioCliente.getClient().getCodigoPostal();
        table1.getRow(1).getCell(1).setText(direccion);
        try {
            JSONArray jsonArray = new JSONArray(solicitudServicioCliente.getClient().getContactosDatos());
            table1.getRow(2).getCell(1).setText(getAttributeContacto(bandera, jsonArray, "nombrePersonaContacto"));
            String telefonos = "Teléfono Oficina: " + getAttributeContacto(bandera, jsonArray, "telefonoOficina") + ", Celular: " + getAttributeContacto(bandera, jsonArray, "telefonoCelular");
            table1.getRow(3).getCell(1).setText(telefonos);
            table1.getRow(4).getCell(1).setText(getAttributeContacto(bandera, jsonArray, "email"));

            table1.getRow(5).getCell(1).setText("N/A");
            table1.getRow(5).getCell(3).setText("N/A");

        } catch (JSONException e) {
            System.out.println("e: " + e);
        }

        XWPFTable table2 = doc.getTables().get(2);
        for (int i = 0; i < solicitudServicioClienteMuestrasLista.size(); i++) {
            XWPFTableRow tableRow = table2.createRow();
            tableRow.getCell(0).setText((i + 1) + "");
            tableRow.getCell(1).setText("Falta relacionar");
            tableRow.getCell(2).setText(solicitudServicioClienteMuestrasLista.get(i).getTipoMuestra());
            tableRow.getCell(3).setText(solicitudServicioClienteMuestrasLista.get(i).getIdClienteMuestra());
            tableRow.getCell(4).setText(solicitudServicioClienteMuestrasLista.get(i).getDescripcionMuestra());
            tableRow.getCell(5).setText(solicitudServicioClienteMuestrasLista.get(i).getLote());
        }

        XWPFTable table3 = doc.getTables().get(3);
        for (int i = 0; i < metodoMuestraList.size(); i++) {
            XWPFTableRow tableRow = table3.createRow();
            tableRow.getCell(0).setText(metodoMuestraList.get(i).getMethod().getNombreMetodo());
            tableRow.getCell(1).setText(metodoMuestraList.get(i).getMethod().getCodigoMetodo());
            tableRow.getCell(2).setText(metodoMuestraList.get(i).getMethod().getNormaReferencia());

        }


        XWPFTable table = doc.createTable();
        table.removeRow(0); // El default row no es necesario
        XWPFTable tableDocumment = plantilla.getTables().get(4);
        //COPIAR LA CABECERA
        XWPFTableRow tableRow = tableDocumment.getRow(0);
        tableRow.getCell(0).getParagraphArray(0).getRuns().get(0).setText("hola");
        table.addRow(tableRow);



//        XWPFTable table1 = doc.getTables().get(1);
//        table1.getRow(0).getCell(1).setText(fra_at_001.getTemperatura());
//        table1.getRow(0).getCell(3).setText(fra_at_001.getHumedadRelativa());
//
//        XWPFTable table2 = doc.getTables().get(2);
//        XWPFParagraph paragraph = table2.getRow(1).getCell(1).addParagraph();
//        paragraph.setAlignment(ParagraphAlignment.CENTER);
//        XWPFRun run = paragraph.createRun();
//        FileInputStream fis = new FileInputStream(fra_at_001.getZona1());
//        XWPFPicture picture = run.addPicture(fis, XWPFDocument.PICTURE_TYPE_PNG, "Name", Units.pixelToEMU(150), Units.pixelToEMU(100));
//
//        XWPFParagraph paragraph2 = table2.getRow(2).getCell(1).addParagraph();
//        paragraph2.setAlignment(ParagraphAlignment.CENTER);
//        XWPFRun run2 = paragraph2.createRun();
//        FileInputStream fis2 = new FileInputStream(fra_at_001.getZona2());
//        XWPFPicture picture2 = run2.addPicture(fis2, XWPFDocument.PICTURE_TYPE_PNG, "Name", Units.pixelToEMU(150), Units.pixelToEMU(100));
//
//        XWPFParagraph paragraph3 = table2.getRow(3).getCell(1).addParagraph();
//        paragraph3.setAlignment(ParagraphAlignment.CENTER);
//        XWPFRun run3 = paragraph3.createRun();
//        FileInputStream fis3 = new FileInputStream(fra_at_001.getZona3());
//        XWPFPicture picture3 = run3.addPicture(fis3, XWPFDocument.PICTURE_TYPE_PNG, "Name", Units.pixelToEMU(150), Units.pixelToEMU(100));
//
//        table2.getRow(1).getCell(2).setText(fra_at_001.getDesprendimiento1());
//        table2.getRow(2).getCell(2).setText(fra_at_001.getDesprendimiento2());
//        table2.getRow(3).getCell(2).setText(fra_at_001.getDesprendimiento3());
//
//        table2.getRow(4).getCell(1).setText(fra_at_001.getAtp());
//
//        XWPFTable table5 = doc.getTables().get(3);
//        table5.getRow(0).getCell(1).setText(fra_at_001.getObservaciones());
//
//        XWPFTable table6 = doc.getTables().get(4);
//        table6.getRow(1).getCell(0).setText(fra_at_001.getRealizo());
//        table6.getRow(1).getCell(1).setText(fra_at_001.getSupervisor());

        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Disposition", "inline; filename=FIR-ERAI-" + estructuraNombres.getNombre() + ".docx");
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        doc.write(byteArrayOutputStream);
        doc.close();
        MediaType word = MediaType.valueOf("application/vnd.openxmlformats-officedocument.wordprocessingml.document");
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(byteArrayOutputStream.toByteArray());
        return ResponseEntity.ok()
                .headers(headers)
                .contentType(word)
                .body(new InputStreamResource(byteArrayInputStream));
    }

    private String getAttributeContacto(int bandera, JSONArray contactosAux, String attribute) throws JSONException {
        StringBuilder contacto = new StringBuilder();
        JSONObject jsonObject;
        for (int i = 0; i < contactosAux.length(); i++) {
            jsonObject = (JSONObject) contactosAux.get(i);
            if (attribute.equals("nombrePersonaContacto")) {
                if (bandera == i) {
                    return jsonObject.get("nombrePersonaContacto").toString();
                }
            }
            if (attribute.equals("cargo")) {
                if (bandera == i) {
                    return jsonObject.get("cargo").toString();
                }
            }
            if (attribute.equals("email")) {
                if (bandera == i) {
                    return jsonObject.get("email").toString();
                }
            }
            if (attribute.equals("telefonoOficina")) {
                if (bandera == i) {
                    return jsonObject.get("telefonoOficina").toString();
                }
            }
            if (attribute.equals("telefonoCelular")) {
                if (bandera == i) {
                    return jsonObject.get("telefonoCelular").toString();
                }
            }
        }
        return contacto.toString();
    }

    private static void addRow(XWPFTable table, XWPFTableRow row, String... colTexts){
        for (int col = 0; col < colTexts.length; col++) {
            row.getCell(col).getParagraphArray(0).getRuns().get(0).setText(colTexts[col], 0);
        }
        table.addRow(row);
    }
}
