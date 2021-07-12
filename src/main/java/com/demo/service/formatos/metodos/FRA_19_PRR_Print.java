package com.demo.service.formatos.metodos;

import com.demo.model.operacion.metodos.fra19prr.FRA_PRR_001;
import com.demo.model.operacion.metodos.fra19prr.datas.FRA_PRR_001_DATA_01;
import com.demo.model.operacion.metodos.fra19prr.datas.FRA_PRR_001_DATA_02;
import com.demo.repository.operacion.metodos.fra19prr.FRA_PRR_001_Repository;
import com.demo.repository.operacion.metodos.fra19prr.datas.FRA_PRR_001_DATA_01_Repository;
import com.demo.repository.operacion.metodos.fra19prr.datas.FRA_PRR_001_DATA_02_Repository;
import com.demo.utils.EstructuraNombres;
import com.demo.utils.FormatoFechas;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.util.Units;
import org.apache.poi.xwpf.usermodel.*;
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
import java.io.InputStream;
import java.net.URL;
import java.util.List;

@Service
public class FRA_19_PRR_Print {

    @Autowired
    private FRA_PRR_001_Repository fra_prr_001_repository;

    @Autowired
    private FRA_PRR_001_DATA_01_Repository fra_prr_001_data_01_repository;

    @Autowired
    private FRA_PRR_001_DATA_02_Repository fra_prr_001_data_02_repository;

    EstructuraNombres estructuraNombres = new EstructuraNombres();
    FormatoFechas formatoFechas = new FormatoFechas();

    public ResponseEntity<InputStreamResource> crearFormato(Long id, int band) throws InvalidFormatException, IOException {
        URL url = new URL("https://resources.adpmx.com/cecim/laboratorio/doc/register/methods/19-FRA-PRR-001.docx");
        XWPFDocument doc = new XWPFDocument(url.openStream());

        FRA_PRR_001 fra_prr_001;
        List<FRA_PRR_001_DATA_01> lista1;
        List<FRA_PRR_001_DATA_02> lista2;

        if (band == 1) {
            fra_prr_001 = fra_prr_001_repository.findByIdFRAPRR(id);
            lista1 = fra_prr_001_data_01_repository.buscarTodosPorEnsayo(fra_prr_001.getIdFRAPRR());
            lista2 = fra_prr_001_data_02_repository.buscarTodosPorEnsayo(fra_prr_001.getIdFRAPRR());

        } else {
            fra_prr_001 = fra_prr_001_repository.findByMetodoMuestra_MetodoMuestraId(id);
            lista1 = fra_prr_001_data_01_repository.buscarTodosPorEnsayo(fra_prr_001.getIdFRAPRR());
            lista2 = fra_prr_001_data_02_repository.buscarTodosPorEnsayo(fra_prr_001.getIdFRAPRR());
        }



        XWPFTable table0 = doc.getTables().get(0);
        table0.getRow(0).getCell(1).setText(fra_prr_001.getFolioTecnica());

        XWPFTable table1 = doc.getTables().get(1);
        table1.getRow(0).getCell(1).setText(fra_prr_001.getFolioSolicitudServicioInterno());
        table1.getRow(0).getCell(3).setText(fra_prr_001.getIdInternoMuestra());
        table1.getRow(0).getCell(5).setText(formatoFechas.formateadorFechas(fra_prr_001.getFechaInicioAnalisis()));
        table1.getRow(0).getCell(7).setText(formatoFechas.formateadorFechas(fra_prr_001.getFechaFinalAnalisis()));

        XWPFTable table2 = doc.getTables().get(2);
        table2.getRow(0).getCell(1).setText(fra_prr_001.getTemperatura() + " °C");
        table2.getRow(0).getCell(3).setText(fra_prr_001.getHumedadRelativa() + " %");
        table2.getRow(1).getCell(1).setText(fra_prr_001.getPrensaEnsayo());
        table2.getRow(1).getCell(3).setText(fra_prr_001.getPesaCalibracion());

        XWPFTable table3 = doc.getTables().get(3);
        for (int i=0; i < lista1.size(); i++){
            table3.getRow(i+3).getCell(1).setText(lista1.get(i).getEspesor1());
            table3.getRow(i+3).getCell(2).setText(lista1.get(i).getEspesor2());
            table3.getRow(i+3).getCell(3).setText(lista1.get(i).getEspesor3());
            table3.getRow(i+3).getCell(4).setText(lista1.get(i).getEspesorPromedio());
            table3.getRow(i+3).getCell(5).setText(lista1.get(i).getResistenciaRasgado());
            table3.getRow(i+3).getCell(6).setText(lista1.get(i).getDesgarreOblicuo());

            table3.getRow(i+3).getCell(7).setText(lista2.get(i).getEspesor1());
            table3.getRow(i+3).getCell(8).setText(lista2.get(i).getEspesor2());
            table3.getRow(i+3).getCell(9).setText(lista2.get(i).getEspesor3());
            table3.getRow(i+3).getCell(10).setText(lista2.get(i).getEspesorPromedio());
            table3.getRow(i+3).getCell(11).setText(lista2.get(i).getResistenciaRasgado());
            table3.getRow(i+3).getCell(12).setText(lista2.get(i).getDesgarreOblicuo());
        }
        table3.getRow(13).getCell(2).setText(fra_prr_001.getPromedioResistenciaRasgadoMD());
        table3.getRow(13).getCell(3).setText("% Total: "+fra_prr_001.getDesgarreOblicuioMD());
        table3.getRow(13).getCell(5).setText(fra_prr_001.getPromedioResistenciaRasgadoTD());
        table3.getRow(13).getCell(6).setText("% Total: "+fra_prr_001.getDesgarreOblicuioTD());

        XWPFTable table4 = doc.getTables().get(4);
        table4.getRow(1).getCell(1).setText(fra_prr_001.getPromedioResistenciaRasgadoMD());
        table4.getRow(1).getCell(2).setText(fra_prr_001.getDesgarreOblicuioMD());
        table4.getRow(1).getCell(3).setText(fra_prr_001.getMinMD());
        table4.getRow(1).getCell(4).setText(fra_prr_001.getMaxMD());
        table4.getRow(1).getCell(5).setText(fra_prr_001.getDesvEstandarMD());
        table4.getRow(1).getCell(6).setText(fra_prr_001.getEspesorPromedioMD());

        table4.getRow(2).getCell(1).setText(fra_prr_001.getPromedioResistenciaRasgadoTD());
        table4.getRow(2).getCell(2).setText(fra_prr_001.getDesgarreOblicuioTD());
        table4.getRow(2).getCell(3).setText(fra_prr_001.getMinTD());
        table4.getRow(2).getCell(4).setText(fra_prr_001.getMaxTD());
        table4.getRow(2).getCell(5).setText(fra_prr_001.getDesvEstandarTD());
        table4.getRow(2).getCell(6).setText(fra_prr_001.getEspesorPromedioTD());

        XWPFTable table5 = doc.getTables().get(5);
        table5.getRow(0).getCell(1).setText(fra_prr_001.getObservaciones());

        XWPFTable table6 = doc.getTables().get(6);
        table6.getRow(1).getCell(0).removeParagraph(0);
        XWPFParagraph paragraph = table6.getRow(1).getCell(0).addParagraph();
        paragraph.setAlignment(ParagraphAlignment.CENTER);
        XWPFRun run = paragraph.createRun();
        InputStream inputStream = new URL(fra_prr_001.getRubricaRealizo()).openStream();
        XWPFPicture xwpfPicture = run.addPicture(inputStream, XWPFDocument.PICTURE_TYPE_PNG, "Name", Units.pixelToEMU(110), Units.pixelToEMU(73));
        run.addBreak();
        run.setText(fra_prr_001.getRealizo());
        table6.getRow(1).getCell(1).setText(fra_prr_001.getSupervisor());

        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Disposition", "inline; filename=FRA-PRR-" + estructuraNombres.getNombre() + ".docx");
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
