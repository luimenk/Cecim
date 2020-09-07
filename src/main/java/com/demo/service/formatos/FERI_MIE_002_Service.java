package com.demo.service.formatos;

import java.io.*;
import java.util.*;

import com.demo.model.operacion.MetodoMuestra;
import com.demo.model.operacion.SolicitudServicioCliente;
import com.demo.model.operacion.SolicitudServicioClienteMuestras;
import com.demo.service.operacion.MetodoMuestraService;
import com.demo.service.operacion.SolicitudServicioClienteMuestrasService;
import com.demo.service.operacion.SolicitudServicioClienteService;
import com.demo.utils.EstructuraNombres;
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

    EstructuraNombres estructuraNombres = new EstructuraNombres();

    public ResponseEntity<InputStreamResource> crearFormato(Long id) throws InvalidFormatException, IOException {

        List<SolicitudServicioClienteMuestras> lista = solicitudServicioClienteMuestrasService.findAllBySolicitud(id);
        SolicitudServicioCliente solicitudServicioCliente = solicitudServicioClienteService.findById(id);

        String pathLista = "/documentos/FERI_MIE_002/FERI-MIE-002-" + lista.size() + ".docx";
        ClassPathResource resource = new ClassPathResource(pathLista);
        XWPFDocument doc = new XWPFDocument(resource.getInputStream());

        XWPFTable table;
        for (int i = 0; i < lista.size(); i++) {
            table = doc.getTables().get(i);
            table.getRow(1).getCell(1).setText(solicitudServicioCliente.getFechaEnvioMuestras());
            table.getRow(2).getCell(1).setText(solicitudServicioCliente.getClient().getFolioCliente());
            table.getRow(3).getCell(1).setText(lista.get(i).getDescripcionMuestra());
            table.getRow(4).getCell(1).setText(lista.get(i).getTipoMuestra());

            List<MetodoMuestra> lista2 = metodoMuestraService.findAllByMuestra(lista.get(i).getSolicitudServicioClienteMuestrasId());

            XWPFParagraph paragraph1 = table.getRow(5).getCell(1).addParagraph();
            XWPFParagraph paragraph2 = table.getRow(7).getCell(1).addParagraph();

            XWPFRun run1 = paragraph1.createRun();
            XWPFRun run2 = paragraph2.createRun();

            for (MetodoMuestra metodoMuestra : lista2) {
                run1.setText(metodoMuestra.getMethod().getCantidadTotal());
                run2.setText(metodoMuestra.getMethod().getCodigoMetodo());
                run1.addBreak();
                run2.addBreak();
            }

            table.getRow(6).getCell(1).setText("Por definir");
            table.getRow(8).getCell(1).setText(lista.get(i).getObservaciones());

            XWPFParagraph paragraph = table.getRow(9).getCell(1).addParagraph();
            XWPFRun run = paragraph.createRun();
            FileInputStream fis = new FileInputStream(lista.get(i).getPathQRIdentificacion());

            XWPFPicture picture = run.addPicture(fis, XWPFDocument.PICTURE_TYPE_PNG, "Name", Units.pixelToEMU(150), Units.pixelToEMU(150));
        }

        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Disposition", "inline; filename=FEIM_SOC-" + estructuraNombres.getNombre() + ".docx");
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
