package com.demo.service.operacion.metodos;

import com.demo.model.operacion.metodos.FRA_AT_001;
import com.demo.model.operacion.metodos.FRA_DSC;
import com.demo.model.operacion.metodos.FRA_HUM_001;
import com.demo.repository.operacion.metodos.FRA_PO_001_Repository;
import com.demo.utils.EstructuraNombres;
import com.demo.model.operacion.metodos.FRA_PO_001;
import com.demo.repository.operacion.metodos.FRA_HUM_001_Repository;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;

import com.demo.utils.FormatoFechas;
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
public class FRA_PO_001_Service {

    @Autowired
    private FRA_PO_001_Repository fra_po_001_repository;

    EstructuraNombres estructuraNombres = new EstructuraNombres();
    FormatoFechas formatoFechas = new FormatoFechas();

    private static final Logger LOGGER = LoggerFactory.getLogger("info");

    private static final Logger APP = LoggerFactory.getLogger("info");

    public FRA_PO_001 save(FRA_PO_001 fra_po_001) {
        return fra_po_001_repository.save(fra_po_001);
    }

    public List<FRA_PO_001> findAll() {
        return fra_po_001_repository.findAll();
    }

    public FRA_PO_001 findById(Long id) {
        return fra_po_001_repository.findByIdFRAPO(id);
    }

    public void delete(Long id) {
        fra_po_001_repository.deleteById(id);
    }

    public long contar() {
        return fra_po_001_repository.count();
    }

    public ResponseEntity<InputStreamResource> crearFormato(Long id, int band) throws InvalidFormatException, IOException{
        ClassPathResource resource = new ClassPathResource("/documentos/METODOS/FRA-PO-001.docx");
        XWPFDocument doc = new XWPFDocument(resource.getInputStream());

        FRA_PO_001 fra_po_001;
        if (band == 1){
            fra_po_001 = fra_po_001_repository.findByIdFRAPO(id);
        } else {
            fra_po_001 = fra_po_001_repository.findByMetodoMuestra_MetodoMuestraId(id);
        }

        //FRA_PO_001 fra_po_001 = fra_po_001_repository.findByIdFRAPO(id);

        XWPFTable table0 = doc.getTables().get(0);
        table0.getRow(0).getCell(1).setText(fra_po_001.getFolioSolicitudServicioInterno());
        table0.getRow(0).getCell(3).setText(formatoFechas.formateadorFechas(fra_po_001.getFechaInicioAnalisis()));
        table0.getRow(1).getCell(1).setText(fra_po_001.getIdInternoMuestra());
        table0.getRow(1).getCell(3).setText(formatoFechas.formateadorFechas(fra_po_001.getFechaFinalAnalisis()));

        XWPFTable table1 = doc.getTables().get(1);
        table1.getRow(0).getCell(1).setText(fra_po_001.getTemperatura());
        table1.getRow(0).getCell(3).setText(fra_po_001.getHumedadRelativa());
        table1.getRow(1).getCell(1).setText(fra_po_001.getCodigoOXTRAN());
        table1.getRow(1).getCell(3).setText(fra_po_001.getCodigoMicrometro());

        XWPFTable table2 = doc.getTables().get(2);
        table2.getRow(1).getCell(1).setText(fra_po_001.getEspesor1());
        table2.getRow(1).getCell(2).setText(fra_po_001.getTiempoPurga1());
        table2.getRow(1).getCell(3).setText(fra_po_001.getTipoMascarilla1());

        table2.getRow(2).getCell(1).setText(fra_po_001.getEspesor2());
        table2.getRow(2).getCell(2).setText(fra_po_001.getTiempoPurga2());
        table2.getRow(2).getCell(3).setText(fra_po_001.getTipoMascarilla2());

        XWPFTable table3 = doc.getTables().get(3);
        table3.getRow(1).getCell(1).setText(fra_po_001.getNumeroCiclos());
        table3.getRow(2).getCell(1).setText(fra_po_001.getTiempoCiclo());
        table3.getRow(3).getCell(1).setText(fra_po_001.getConvergencia());

        XWPFTable table4 = doc.getTables().get(4);
        table4.getRow(1).getCell(1).setText(fra_po_001.getPermeabilidadOxigeno1());
        table4.getRow(2).getCell(1).setText(fra_po_001.getPermeabilidadOxigeno2());

        XWPFTable table5 = doc.getTables().get(5);
        table5.getRow(0).getCell(1).setText(fra_po_001.getObservaciones());

        XWPFTable table6 = doc.getTables().get(6);
        table6.getRow(1).getCell(0).setText(fra_po_001.getRealizo());
        table6.getRow(1).getCell(1).setText(fra_po_001.getSupervisor());

        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Disposition", "inline; filename=FRA-PO-"+estructuraNombres.getNombre()+".docx");
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
