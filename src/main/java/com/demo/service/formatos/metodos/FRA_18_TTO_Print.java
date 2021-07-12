package com.demo.service.formatos.metodos;

import com.demo.model.operacion.metodos.fra09tga.FRA_TGA_001;
import com.demo.model.operacion.metodos.fra09tga.datas.FRA_TGA_001_DATA;
import com.demo.model.operacion.metodos.fra18tto.FRA_TTO_001;
import com.demo.model.operacion.metodos.fra18tto.datas.FRA_TTO_001_DATA;
import com.demo.repository.operacion.metodos.fra18tto.FRA_TTO_001_Repository;
import com.demo.repository.operacion.metodos.fra18tto.datas.FRA_TTO_001_DATA_Repository;
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
public class FRA_18_TTO_Print {

    @Autowired
    private FRA_TTO_001_Repository fra_tto_001_repository;

    @Autowired
    private FRA_TTO_001_DATA_Repository fra_tto_001_data_repository;

    EstructuraNombres estructuraNombres = new EstructuraNombres();
    FormatoFechas formatoFechas = new FormatoFechas();

    public ResponseEntity<InputStreamResource> crearFormato(Long id, int band) throws InvalidFormatException, IOException {
        URL url = new URL("https://resources.adpmx.com/cecim/laboratorio/doc/register/methods/18-FRA-TTO-001.docx");
        XWPFDocument doc = new XWPFDocument(url.openStream());

        FRA_TTO_001 fra_tto_001;
        List<FRA_TTO_001_DATA> lista;
        if (band == 1) {
            fra_tto_001 = fra_tto_001_repository.findByIdFRATTO(id);
        } else {
            fra_tto_001 = fra_tto_001_repository.findByMetodoMuestra_MetodoMuestraId(id);
        }
        lista = fra_tto_001_data_repository.buscarTodosPorEnsayo(fra_tto_001.getIdFRATTO());

        XWPFTable table0 = doc.getTables().get(0);
        table0.getRow(0).getCell(1).setText(fra_tto_001.getFolioTecnica());

        XWPFTable table1 = doc.getTables().get(1);
        table1.getRow(0).getCell(1).setText(fra_tto_001.getFolioSolicitudServicioInterno());
        table1.getRow(0).getCell(3).setText(formatoFechas.formateadorFechas(fra_tto_001.getFechaInicioAnalisis()));
        table1.getRow(1).getCell(1).setText(fra_tto_001.getIdInternoMuestra());
        table1.getRow(1).getCell(3).setText(formatoFechas.formateadorFechas(fra_tto_001.getFechaFinalAnalisis()));

        XWPFTable table2 = doc.getTables().get(2);
        table2.getRow(0).getCell(1).setText(fra_tto_001.getTemperatura() + " °C");
        table2.getRow(0).getCell(3).setText(fra_tto_001.getHumedadRelativa() + " %");

        XWPFTable table3 = doc.getTables().get(3);
        table3.getRow(0).getCell(1).setText(fra_tto_001.getTemperatura() + " °C");
        table3.getRow(0).getCell(3).setText(fra_tto_001.getHumedadRelativa() + " %");
        table3.getRow(5).getCell(1).setText(fra_tto_001.getMascarillaCeldaA());
        table3.getRow(5).getCell(3).setText(fra_tto_001.getMascarillaCeldaB());
        table3.getRow(6).getCell(1).setText(fra_tto_001.getPresionBarometrica());

        XWPFTable table4 = doc.getTables().get(4);
        XWPFTable table5 = doc.getTables().get(5);
        for (int i = 0; i < lista.size(); i++) {
            table4.getRow(i+2).getCell(1).setText(lista.get(i).getE1());
            table4.getRow(i+2).getCell(2).setText(lista.get(i).getE2());
            table4.getRow(i+2).getCell(3).setText(lista.get(i).getE3());
            table4.getRow(i+2).getCell(4).setText(lista.get(i).getE4());
            table4.getRow(i+2).getCell(5).setText(lista.get(i).getE5());
            table4.getRow(i+2).getCell(6).setText(lista.get(i).getPromedio());

            table5.getRow(i+2).getCell(1).setText(lista.get(i).getCc());
            table5.getRow(i+2).getCell(2).setText(lista.get(i).getMol());
        }

        XWPFTable table6 = doc.getTables().get(6);
        table6.getRow(0).getCell(1).setText(fra_tto_001.getObservaciones());

        XWPFTable table7 = doc.getTables().get(7);
        table7.getRow(1).getCell(0).removeParagraph(0);
        XWPFParagraph paragraph = table7.getRow(1).getCell(0).addParagraph();
        paragraph.setAlignment(ParagraphAlignment.CENTER);
        XWPFRun run = paragraph.createRun();
        InputStream inputStream = new URL(fra_tto_001.getRubricaRealizo()).openStream();
        XWPFPicture xwpfPicture = run.addPicture(inputStream, XWPFDocument.PICTURE_TYPE_PNG, "Name", Units.pixelToEMU(110), Units.pixelToEMU(73));
        run.addBreak();
        run.setText(fra_tto_001.getRealizo());
        table7.getRow(1).getCell(1).setText(fra_tto_001.getSupervisor());

        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Disposition", "inline; filename=FRA-TTO-" + estructuraNombres.getNombre() + ".docx");
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
