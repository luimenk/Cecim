package com.demo.service;

import com.demo.model.Method;
import com.demo.model.operacion.SolicitudServicioCliente;
import com.demo.repository.MethodRepository;

import com.demo.utils.EstructuraNombres;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFTable;
import org.apache.poi.xwpf.usermodel.XWPFTableRow;
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
import java.io.IOException;
import java.util.List;

@Service
public class MethodService {

    @Autowired
    private MethodRepository methodRepository;

    EstructuraNombres estructuraNombres = new EstructuraNombres();

    private static final Logger LOGGER = LoggerFactory.getLogger("info");

    private static final Logger APP = LoggerFactory.getLogger("info");

    public Method save(Method method) {
        return methodRepository.save(method);
    }

    public List<Method> findAll() {
        return methodRepository.findAll();
    }

    public Method findById(Long methodId) {
        return methodRepository.findByMethodId(methodId);
    }

    public Method findByCodigo(String codigo){
        return methodRepository.findByCodigoMetodo(codigo);
    }

    public void delete(Long methodId) {
        methodRepository.deleteById(methodId);
    }

    public long contar() {
        return methodRepository.count();
    }

    public ResponseEntity<InputStreamResource> generarListaMetodos() throws InvalidFormatException, IOException {
        ClassPathResource resource = new ClassPathResource("/documentos/tabla.docx");
        XWPFDocument doc = new XWPFDocument(resource.getInputStream());
        List<Method> lista = methodRepository.findAll();

        List<String>contactosAux;
        int bandera=0;

        XWPFTable table = doc.getTables().get(0);
        for (int i = 0; i<lista.size(); i++) {
            XWPFTableRow tableRow = table.createRow();
            tableRow.getCell(0).setText((i+1)+"");
            tableRow.getCell(1).setText(lista.get(i).getCodigoMetodo());
            tableRow.getCell(2).setText(lista.get(i).getCantidadMuestraEnsayo());
            tableRow.getCell(3).setText(lista.get(i).getCantidadMuestraRetencion());
            tableRow.getCell(4).setText(lista.get(i).getDimensionesCorteProbeta());
            tableRow.getCell(5).setText(lista.get(i).getNumeroProbetasMuestras());
            tableRow.getCell(6).setText(lista.get(i).getCondicionesEspecialesAcondicionamiento());
        }

        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Disposition", "inline; filename=EAMM-SOC-"+estructuraNombres.getNombre()+".docx");
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
