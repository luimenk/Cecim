package com.demo.service.formatos;

import java.io.*;
import java.util.*;

import com.demo.model.operacion.MetodoMuestra;
import com.demo.model.operacion.RecepcionVerificacionRegistroCodificacion;
import com.demo.model.operacion.SolicitudServicioCliente;
import com.demo.model.operacion.SolicitudServicioClienteMuestras;
import com.demo.service.operacion.MetodoMuestraService;
import com.demo.service.operacion.RecepcionVerificacionRegistroCodificacionService;
import com.demo.service.operacion.SolicitudServicioClienteMuestrasService;
import com.demo.service.operacion.SolicitudServicioClienteService;
import com.demo.utils.EstructuraNombres;
import com.demo.utils.FormatoFechas;
import com.demo.utils.GenerateQR;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.util.Units;
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

@Service
public class FERI_MIE_002_Service {

    @Autowired
    private SolicitudServicioClienteService solicitudServicioClienteService;

    @Autowired
    private SolicitudServicioClienteMuestrasService solicitudServicioClienteMuestrasService;

    @Autowired
    private MetodoMuestraService metodoMuestraService;

    @Autowired
    private RecepcionVerificacionRegistroCodificacionService recepcionVerificacionRegistroCodificacionService;

    EstructuraNombres estructuraNombres = new EstructuraNombres();
    FormatoFechas formatoFechas = new FormatoFechas();

    public ResponseEntity<InputStreamResource> crearFormato(Long id, int band) throws InvalidFormatException, IOException {

        List<RecepcionVerificacionRegistroCodificacion> lista = new ArrayList<>();
        SolicitudServicioCliente solicitudServicioCliente;

        if (band == 1){
            lista = recepcionVerificacionRegistroCodificacionService.findAllBySolicitudServicioClienteMuestrasId(id);
            solicitudServicioCliente = solicitudServicioClienteService.findById(id);
        } else if (band == 3){
            solicitudServicioCliente = solicitudServicioClienteService.findById(id);
            lista = recepcionVerificacionRegistroCodificacionService.findAllBySolicitudServicioCliente(solicitudServicioCliente);
        }


        String pathLista = "/documentos/FERI_MIE_002/FERI-MIE-002-" + lista.size() + ".docx";
        ClassPathResource resource = new ClassPathResource(pathLista);
        XWPFDocument doc = new XWPFDocument(resource.getInputStream());

        XWPFTable table;
        for (int i = 0; i < lista.size(); i++) {
            try {


                table = doc.getTables().get(i);
                table.getRow(1).getCell(1).setText(formatoFechas.formateadorFechas(lista.get(i).getSolicitudServicioClienteMuestras().getSolicitudServicioCliente().getFechaRecepcionMuestras()));
                table.getRow(2).getCell(1).setText(formatoFechas.formateadorFechas(lista.get(i).getSolicitudServicioClienteMuestras().getSolicitudServicioCliente().getFechaCompromisoEntrega()));
                table.getRow(3).getCell(1).setText(lista.get(i).getSolicitudServicioClienteMuestras().getIdClienteMuestra());
                table.getRow(4).getCell(1).setText(lista.get(i).getIdInternoMuestra2());
                table.getRow(5).getCell(1).setText(lista.get(i).getSolicitudServicioClienteMuestras().getSolicitudServicioCliente().getFolioSolitudServicioCliente());
                table.getRow(6).getCell(1).setText(lista.get(i).getSolicitudServicioClienteMuestras().getDescripcionMuestra());
                table.getRow(7).getCell(1).setText(lista.get(i).getCantidadMuestraRetencion());
                table.getRow(8).getCell(1).setText(lista.get(i).getNombrePersonaAcondicionara());

                XWPFParagraph paragraph = table.getRow(9).getCell(1).addParagraph();
                XWPFRun run = paragraph.createRun();
                FileInputStream fis = new FileInputStream(lista.get(i).getSolicitudServicioClienteMuestras().getPathQRIdentificacion());

                XWPFPicture picture = run.addPicture(fis, XWPFDocument.PICTURE_TYPE_PNG, "Name", Units.pixelToEMU(150), Units.pixelToEMU(150));

            } catch (NullPointerException e){
                System.out.println("No se han hecho todas las recepciones");
            }
        }
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Disposition", "inline; filename=FERI_MIE_" + lista.get(0).getSolicitudServicioClienteMuestras().getSolicitudServicioCliente().getFolioSolitudServicioCliente() + ".docx");
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
}
