package com.demo.service.formatos.metodos.listas;

import com.demo.model.operacion.MetodoMuestra;
import com.demo.model.operacion.RecepcionVerificacionRegistroCodificacion;
import com.demo.repository.operacion.MetodoMuestraRepository;
import com.demo.repository.operacion.RecepcionVerificacionRegistroCodificacionRepository;
import com.demo.utils.EstructuraNombres;
import com.demo.utils.FormatoFechas;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFTable;
import org.apache.poi.xwpf.usermodel.XWPFTableRow;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.URL;
import java.util.List;

@Service
public class LFF_MIE_MET_XX_Print {

    @Autowired
    private MetodoMuestraRepository metodoMuestraRepository;

    @Autowired
    private RecepcionVerificacionRegistroCodificacionRepository recepcionVerificacionRegistroCodificacionRepository;

    EstructuraNombres estructuraNombres = new EstructuraNombres();
    FormatoFechas formatoFechas = new FormatoFechas();

    public ResponseEntity<InputStreamResource> crearListaFolios(String nombreDoc, Long id) throws InvalidFormatException, IOException {
        URL url = new URL("https://resources.adpmx.com/cecim/laboratorio/doc/list/methods/" + nombreDoc + ".docx");
        XWPFDocument doc = new XWPFDocument(url.openStream());

        List<MetodoMuestra> metodoMuestraList = metodoMuestraRepository.findAllByMethod_MethodId(id);

        XWPFTable table = doc.getTables().get(1);
        table.getRow(0).setRepeatHeader(true);
        for (int i = 0; i < metodoMuestraList.size(); i++) {
            try {
                XWPFTableRow tableRow = table.createRow();
                tableRow.getCell(0).setText(metodoMuestraList.get(i).getFolioTecnica());
                RecepcionVerificacionRegistroCodificacion recepcionVerificacionRegistroCodificacion = recepcionVerificacionRegistroCodificacionRepository.findBySolicitudServicioClienteMuestras_SolicitudServicioClienteMuestrasId(metodoMuestraList.get(i).getSolicitudServicioClienteMuestras().getSolicitudServicioClienteMuestrasId());
                tableRow.getCell(1).setText(recepcionVerificacionRegistroCodificacion.getIdInternoMuestra1());
                tableRow.getCell(2).setText(metodoMuestraList.get(i).getSolicitudServicioClienteMuestras().getSolicitudServicioCliente().getFechaRecepcionMuestras());
                tableRow.getCell(3).setText(metodoMuestraList.get(i).getSolicitudServicioClienteMuestras().getSolicitudServicioCliente().getFolioSolitudServicioCliente());
            } catch (NullPointerException e) {
                System.out.println("Error en la iteración " + i);
            }
        }

        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Disposition", "inline; filename=" + nombreDoc + estructuraNombres.getNombre() + ".docx");
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
