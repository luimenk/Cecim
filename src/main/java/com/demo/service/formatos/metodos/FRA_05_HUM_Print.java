package com.demo.service.formatos.metodos;

import com.demo.model.operacion.metodos.fra05hum.FRA_HUM_001;
import com.demo.model.operacion.metodos.fra05hum.datas.FRA_HUM_001_DATA_01;
import com.demo.model.operacion.metodos.fra05hum.datas.FRA_HUM_001_DATA_02;
import com.demo.repository.operacion.metodos.fra05hum.datas.FRA_HUM_001_DATA_01_Repository;
import com.demo.repository.operacion.metodos.fra05hum.datas.FRA_HUM_001_DATA_02_Repository;
import com.demo.repository.operacion.metodos.fra05hum.FRA_HUM_001_Repository;
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
public class FRA_05_HUM_Print {

    @Autowired
    private FRA_HUM_001_Repository fra_hum_001_repository;

    @Autowired
    private FRA_HUM_001_DATA_01_Repository fra_hum_001_data_01_repository;

    @Autowired
    private FRA_HUM_001_DATA_02_Repository fra_hum_001_data_02_repository;

    EstructuraNombres estructuraNombres = new EstructuraNombres();
    FormatoFechas formatoFechas = new FormatoFechas();

    public ResponseEntity<InputStreamResource> crearFormato(Long id, int band) throws InvalidFormatException, IOException {
        //ClassPathResource resource = new ClassPathResource("/documentos/METODOS/FRA-HUM-001.docx");
        //XWPFDocument doc = new XWPFDocument(resource.getInputStream());
        URL url = new URL("https://resources.adpmx.com/cecim/laboratorio/doc/register/methods/05-FRA-HUM-001.docx");
        XWPFDocument doc = new XWPFDocument(url.openStream());

        FRA_HUM_001 fra_hum_001;
        List<FRA_HUM_001_DATA_01> lista1;
        List<FRA_HUM_001_DATA_02> lista2;

        if (band == 1){
            fra_hum_001 = fra_hum_001_repository.findByIdFRAHUM(id);
            lista1 = fra_hum_001_data_01_repository.buscarTodosPorEnsayo(fra_hum_001.getIdFRAHUM());
            lista2 = fra_hum_001_data_02_repository.buscarTodosPorEnsayo(fra_hum_001.getIdFRAHUM());
        }else {
            fra_hum_001 = fra_hum_001_repository.findByMetodoMuestra_MetodoMuestraId(id);
            lista1 = fra_hum_001_data_01_repository.buscarTodosPorEnsayo(fra_hum_001.getIdFRAHUM());
            lista2 = fra_hum_001_data_02_repository.buscarTodosPorEnsayo(fra_hum_001.getIdFRAHUM());
        }

        XWPFTable table0 = doc.getTables().get(0);
        table0.getRow(0).getCell(1).setText(fra_hum_001.getFolioTecnica());

        XWPFTable table1 = doc.getTables().get(1);
        table1.getRow(0).getCell(1).setText(fra_hum_001.getFolioSolicitudServicioInterno());
        table1.getRow(0).getCell(3).setText(formatoFechas.formateadorFechas(fra_hum_001.getFechaInicioAnalisis()));
        table1.getRow(1).getCell(1).setText(fra_hum_001.getIdInternoMuestra());
        table1.getRow(1).getCell(3).setText(formatoFechas.formateadorFechas(fra_hum_001.getFechaFinalAnalisis()));

        XWPFTable table2 = doc.getTables().get(2);
        table2.getRow(0).getCell(1).setText(fra_hum_001.getTemperatura() + " °C");
        table2.getRow(0).getCell(3).setText(fra_hum_001.getHumedadRelativa() + " %");
        //table2.getRow(1).getCell(1).setText(fra_hum_001.getCodigoBalanza());
        table2.getRow(1).getCell(3).setText(fra_hum_001.getCodigoHorno());
        table2.getRow(2).getCell(1).setText(fra_hum_001.getTemperaturaSecado() + " °C");
        table2.getRow(2).getCell(3).setText(fra_hum_001.getTipoMaterial());

        XWPFTable table3 = doc.getTables().get(3);
        for (int i=0; i < lista1.size(); i++){
            table3.getRow(i+2).getCell(1).setText(lista1.get(i).getPesoCharola());
            table3.getRow(i+2).getCell(2).setText(lista1.get(i).getPesoInicial());
            table3.getRow(i+2).getCell(3).setText(lista1.get(i).getTiempoReposo());
            table3.getRow(i+2).getCell(4).setText(lista1.get(i).getPesoFinal());
            table3.getRow(i+2).getCell(5).setText(lista1.get(i).getPesoSeco());
            table3.getRow(i+2).getCell(6).setText(lista1.get(i).getPips());
            table3.getRow(i+2).getCell(7).setText(lista1.get(i).getPorcentajeHumedad());

            table3.getRow(i+6).getCell(1).setText(lista2.get(i).getPesoCharola());
            table3.getRow(i+6).getCell(2).setText(lista2.get(i).getPesoInicial());
            table3.getRow(i+6).getCell(3).setText(lista2.get(i).getTiempoReposo());
            table3.getRow(i+6).getCell(4).setText(lista2.get(i).getPesoFinal());
            table3.getRow(i+6).getCell(5).setText(lista2.get(i).getPesoSeco());
            table3.getRow(i+6).getCell(6).setText(lista2.get(i).getPips());
            table3.getRow(i+6).getCell(7).setText(lista2.get(i).getPorcentajeHumedad());
        }
        table3.getRow(10).getCell(3).setText(fra_hum_001.getHumedadPromedio0());
        table3.getRow(11).getCell(3).setText(fra_hum_001.getHumedadPromedio4());
        table3.getRow(12).getCell(3).setText(fra_hum_001.getHumedadPromedio8());


        XWPFTable table4 = doc.getTables().get(4);
        table4.getRow(0).getCell(1).setText(fra_hum_001.getObservaciones());

        XWPFTable table5 = doc.getTables().get(5);
        table5.getRow(1).getCell(0).removeParagraph(0);
        XWPFParagraph paragraph = table5.getRow(1).getCell(0).addParagraph();
        paragraph.setAlignment(ParagraphAlignment.CENTER);
        XWPFRun run = paragraph.createRun();
        InputStream inputStream = new URL(fra_hum_001.getRubricaRealizo()).openStream();
        XWPFPicture xwpfPicture = run.addPicture(inputStream, XWPFDocument.PICTURE_TYPE_PNG, "Name", Units.pixelToEMU(110), Units.pixelToEMU(73));
        run.addBreak();
        run.setText(fra_hum_001.getRealizo());
        table5.getRow(1).getCell(1).setText(fra_hum_001.getSupervisor());

        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Disposition", "inline; filename=FRA-HUM-"+estructuraNombres.getNombre()+".docx");
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
