package com.demo.service.formatos.metodos;

import com.demo.model.operacion.metodos.fra03es.FRA_ES_001;
import com.demo.model.operacion.metodos.fra03es.datas.FRA_ES_001_DATA;
import com.demo.repository.operacion.metodos.fra03es.datas.FRA_ES_001_DATA_Repository;
import com.demo.service.operacion.metodos.FRA_ES_001_Service;
import com.demo.utils.EstructuraNombres;
import com.demo.utils.FormatoFechas;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.util.Units;
import org.apache.poi.xwpf.usermodel.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.List;

@Service
public class FRA_03_ES_Print {

    @Autowired
    private FRA_ES_001_Service fra_es_001_service;

    @Autowired
    private FRA_ES_001_DATA_Repository fra_es_001_data_repository;

    EstructuraNombres estructuraNombres = new EstructuraNombres();
    FormatoFechas formatoFechas = new FormatoFechas();

    public ResponseEntity<InputStreamResource> crearFormato(Long id, int band) throws InvalidFormatException, IOException {

        FRA_ES_001 fra_es_001;
        List<FRA_ES_001_DATA> lista;

        if (band == 1) {
            fra_es_001 = fra_es_001_service.findById(id);
            lista = fra_es_001_data_repository.buscarTodosPorEnsayo(fra_es_001.getIdFRAES());
        } else {
            fra_es_001 = fra_es_001_service.findByMuestra(id);
            lista = fra_es_001_data_repository.buscarTodosPorEnsayo(fra_es_001.getIdFRAES());
        }

        URL url = new URL("https://resources.adpmx.com/cecim/laboratorio/doc/register/methods/03-FRA-ES-001-" + lista.size() + ".docx");
        XWPFDocument doc = new XWPFDocument(url.openStream());

        XWPFTable table0 = doc.getTables().get(0);
        table0.getRow(0).getCell(1).setText(fra_es_001.getFolioTecnica());

        XWPFTable table1 = doc.getTables().get(1);
        table1.getRow(0).getCell(1).setText(fra_es_001.getFolioSolicitudServicioInterno());
        table1.getRow(0).getCell(3).setText(formatoFechas.formateadorFechas(fra_es_001.getFechaInicioAnalisis()));
        table1.getRow(1).getCell(1).setText(fra_es_001.getIdInternoMuestra());
        table1.getRow(1).getCell(3).setText(formatoFechas.formateadorFechas(fra_es_001.getFechaFinalAnalisis()));

        XWPFTable table2 = doc.getTables().get(2);
        table2.getRow(0).getCell(1).setText(fra_es_001.getTemperatura() + " °C");
        table2.getRow(0).getCell(3).setText(fra_es_001.getHumedadRelativa() + " %");
        table2.getRow(1).getCell(1).setText(fra_es_001.getCodigoMicrometro());
        table2.getRow(1).getCell(3).setText(fra_es_001.getNormaReferencia());
        table2.getRow(2).getCell(1).setText(fra_es_001.getAnchoProveta());
        table2.getRow(2).getCell(3).setText(fra_es_001.getNumeroMediciones());

        XWPFTable table3 = doc.getTables().get(3);
        for (int i = 0; i < lista.size(); i++) {
            table3.getRow(i + 1).getCell(1).setText(lista.get(i).getLargo());
            table3.getRow(i + 1).getCell(2).setText(lista.get(i).getAncho());
        }
        table3.getRow(lista.size() + 1).getCell(1).setText(fra_es_001.getPromedioLargo());
        table3.getRow(lista.size() + 1).getCell(2).setText(fra_es_001.getPromedioAncho());


        XWPFTable table4 = doc.getTables().get(4);
        table4.getRow(0).getCell(1).setText(fra_es_001.getObservaciones());

        XWPFTable table5 = doc.getTables().get(5);
        table5.getRow(1).getCell(0).removeParagraph(0);
        XWPFParagraph paragraph = table5.getRow(1).getCell(0).addParagraph();
        paragraph.setAlignment(ParagraphAlignment.CENTER);
        XWPFRun run = paragraph.createRun();
        InputStream inputStream = new URL(fra_es_001.getRubricaRealizo()).openStream();
        XWPFPicture xwpfPicture = run.addPicture(inputStream, XWPFDocument.PICTURE_TYPE_PNG, "Name", Units.pixelToEMU(110), Units.pixelToEMU(73));
        run.addBreak();
        run.setText(fra_es_001.getRealizo());
        table5.getRow(1).getCell(1).setText(fra_es_001.getSupervisor());

        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Disposition", "inline; filename=FRA-ES-" + estructuraNombres.getNombre() + ".docx");
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
