package com.demo.service.operacion.metodos;

import com.demo.model.operacion.MetodoMuestra;
import com.demo.model.operacion.RecepcionVerificacionRegistroCodificacion;
import com.demo.model.operacion.metodos.fra14oit.FRA_OIT_001;
import com.demo.repository.operacion.MetodoMuestraRepository;
import com.demo.repository.operacion.RecepcionVerificacionRegistroCodificacionRepository;
import com.demo.repository.operacion.metodos.FRA_OIT_001_Repository;
import com.demo.utils.EstructuraNombres;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;

import com.demo.utils.FormatoFechas;
import org.apache.poi.xwpf.usermodel.XWPFTableRow;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFTable;

@Service
public class FRA_OIT_001_Service {

    @Autowired
    private FRA_OIT_001_Repository fra_oit_001_repository;

    @Autowired
    private MetodoMuestraRepository metodoMuestraRepository;

    @Autowired
    private RecepcionVerificacionRegistroCodificacionRepository recepcionVerificacionRegistroCodificacionRepository;

    EstructuraNombres estructuraNombres = new EstructuraNombres();
    FormatoFechas formatoFechas = new FormatoFechas();

    private static final Logger LOGGER = LoggerFactory.getLogger("info");

    private static final Logger APP = LoggerFactory.getLogger("info");

    public FRA_OIT_001 save(FRA_OIT_001 fra_oit_001) {
        return fra_oit_001_repository.save(fra_oit_001);
    }

    public List<FRA_OIT_001> findAll() {
        return fra_oit_001_repository.findAll();
    }

    public FRA_OIT_001 findById(Long id) {
        return fra_oit_001_repository.findByIdFRAOIT(id);
    }

    public FRA_OIT_001 findByIdInternoMuestra(String id) {
        return fra_oit_001_repository.findByIdInternoMuestra(id);
    }

    public void delete(Long id) {
        fra_oit_001_repository.deleteById(id);
    }

    public long contar() {
        return fra_oit_001_repository.count();
    }

    public ResponseEntity<InputStreamResource> crearFormato(Long id, int band) throws InvalidFormatException, IOException{
        ClassPathResource resource = new ClassPathResource("/documentos/METODOS/FRA-OIT-001.docx");
        XWPFDocument doc = new XWPFDocument(resource.getInputStream());
        FRA_OIT_001 fra_oit_001;
        if (band == 1){
            fra_oit_001 = fra_oit_001_repository.findByIdFRAOIT(id);
        }else {
            fra_oit_001 = fra_oit_001_repository.findByMetodoMuestra_MetodoMuestraId(id);
        }

        //FRA_GR_001 fra_gr_001 = fra_gr_001_repository.findByFragr001Id(id);

        XWPFTable table0 = doc.getTables().get(0);
        table0.getRow(0).getCell(3).setText(fra_oit_001.getFolioTecnica());
        table0.getRow(1).getCell(1).setText(fra_oit_001.getFolioSolicitudServicioInterno());
        table0.getRow(1).getCell(3).setText(formatoFechas.formateadorFechas(fra_oit_001.getFechaInicioAnalisis()));
        table0.getRow(2).getCell(1).setText(fra_oit_001.getIdInternoMuestra());
        table0.getRow(2).getCell(3).setText(formatoFechas.formateadorFechas(fra_oit_001.getFechaFinalAnalisis()));

        XWPFTable table1 = doc.getTables().get(1);
        table1.getRow(0).getCell(1).setText(fra_oit_001.getTemperatura() + "°C");
        table1.getRow(0).getCell(3).setText(fra_oit_001.getHumedadRelativa() + "%");
        table1.getRow(1).getCell(1).setText(fra_oit_001.getCodigoDSC());
        table1.getRow(1).getCell(3).setText(fra_oit_001.getCodigoBalanza());

        XWPFTable table2 = doc.getTables().get(2);
        table2.getRow(1).getCell(1).setText(fra_oit_001.getEspesor1());
        table2.getRow(1).getCell(2).setText(fra_oit_001.getPeso1());
        table2.getRow(1).getCell(3).setText(fra_oit_001.getPpmdsc1());
        table2.getRow(2).getCell(1).setText(fra_oit_001.getEspesor2());
        table2.getRow(2).getCell(2).setText(fra_oit_001.getPeso2());
        table2.getRow(2).getCell(3).setText(fra_oit_001.getPpmdsc2());

        XWPFTable table3 = doc.getTables().get(3);
        table3.getRow(4).getCell(2).setText(fra_oit_001.getTiempoIsoterma());

        XWPFTable table4 = doc.getTables().get(4);
        table4.getRow(1).getCell(1).setText(fra_oit_001.getRepeticion1OIT());
        table4.getRow(1).getCell(2).setText(fra_oit_001.getRepeticion2OIT());
        table4.getRow(1).getCell(3).setText(fra_oit_001.getPromedio());

        XWPFTable table5 = doc.getTables().get(5);
        table5.getRow(0).getCell(1).setText(fra_oit_001.getObservaciones());

        XWPFTable table6 = doc.getTables().get(6);
        table6.getRow(1).getCell(0).setText(fra_oit_001.getRealizo());
        table6.getRow(1).getCell(1).setText(fra_oit_001.getSupervisor());

        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Disposition", "inline; filename=FRA-OIT-"+estructuraNombres.getNombre()+".docx");
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

    public ResponseEntity<InputStreamResource> crearListaFolios() throws InvalidFormatException, IOException{
        ClassPathResource resource = new ClassPathResource("/documentos/METODOS/Listas/BFF-MIE-015.docx");
        XWPFDocument doc = new XWPFDocument(resource.getInputStream());
        List<MetodoMuestra> metodoMuestraList = metodoMuestraRepository.findAllByMethod_MethodId(62L);

        XWPFTable table = doc.getTables().get(0);
        table.getRow(0).setRepeatHeader(true);
        for (int i = 0; i<metodoMuestraList.size(); i++) {
            try {
                XWPFTableRow tableRow = table.createRow();
                tableRow.getCell(0).setText(metodoMuestraList.get(i).getFolioTecnica());
                RecepcionVerificacionRegistroCodificacion recepcionVerificacionRegistroCodificacion = recepcionVerificacionRegistroCodificacionRepository.findBySolicitudServicioClienteMuestras_SolicitudServicioClienteMuestrasId(metodoMuestraList.get(i).getSolicitudServicioClienteMuestras().getSolicitudServicioClienteMuestrasId());
                tableRow.getCell(1).setText(recepcionVerificacionRegistroCodificacion.getIdInternoMuestra1());
                tableRow.getCell(2).setText(metodoMuestraList.get(i).getSolicitudServicioClienteMuestras().getSolicitudServicioCliente().getFechaRecepcionMuestras());
                tableRow.getCell(3).setText(metodoMuestraList.get(i).getSolicitudServicioClienteMuestras().getSolicitudServicioCliente().getFolioSolitudServicioCliente());
            } catch (NullPointerException e){
                System.out.println("Error en la iteración " + i);
            }
        }

        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Disposition", "inline; filename=BFF-MIE-015-"+estructuraNombres.getNombre()+".docx");
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
