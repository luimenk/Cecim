package com.demo.service.formatos.metodos;

import com.demo.model.operacion.metodos.fra17if.FRA_IF_001;
import com.demo.model.operacion.metodos.fra17if.datas.FRA_IF_001_DATA;
import com.demo.repository.operacion.MetodoMuestraRepository;
import com.demo.repository.operacion.RecepcionVerificacionRegistroCodificacionRepository;
import com.demo.repository.operacion.metodos.fra17if.FRA_IF_001_Repository;
import com.demo.repository.operacion.metodos.fra17if.datas.FRA_IF_001_DATA_Repository;
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
public class FRA_17_IF_Print {

    @Autowired
    private FRA_IF_001_Repository fra_if_001_repository;

    @Autowired
    private FRA_IF_001_DATA_Repository fra_if_001_data_repository;

    @Autowired
    private MetodoMuestraRepository metodoMuestraRepository;

    @Autowired
    private RecepcionVerificacionRegistroCodificacionRepository recepcionVerificacionRegistroCodificacionRepository;

    EstructuraNombres estructuraNombres = new EstructuraNombres();
    FormatoFechas formatoFechas = new FormatoFechas();

    public ResponseEntity<InputStreamResource> crearFormato(Long id, int band) throws InvalidFormatException, IOException {
        URL url = new URL("https://resources.adpmx.com/cecim/laboratorio/doc/register/methods/17-FRA-IF-001.docx");
        XWPFDocument doc = new XWPFDocument(url.openStream());

        FRA_IF_001 fra_if_001;
        List<FRA_IF_001_DATA> lista;

        if (band == 1) {
            fra_if_001 = fra_if_001_repository.findByIdFRAIF(id);
        } else {
            fra_if_001 = fra_if_001_repository.findByMetodoMuestra_MetodoMuestraId(id);
        }
        lista = fra_if_001_data_repository.buscarTodosPorEnsayo(fra_if_001.getIdFRAIF());

        XWPFTable table0 = doc.getTables().get(0);
        table0.getRow(0).getCell(1).setText(fra_if_001.getFolioTecnica());

        XWPFTable table1 = doc.getTables().get(1);
        table1.getRow(0).getCell(1).setText(fra_if_001.getFolioSolicitudServicioInterno());
        table1.getRow(0).getCell(3).setText(formatoFechas.formateadorFechas(fra_if_001.getFechaInicioAnalisis()));
        table1.getRow(1).getCell(1).setText(fra_if_001.getIdInternoMuestra());
        table1.getRow(1).getCell(3).setText(formatoFechas.formateadorFechas(fra_if_001.getFechaFinalAnalisis()));

        XWPFTable table2 = doc.getTables().get(2);
        table2.getRow(0).getCell(1).setText(fra_if_001.getTemperatura() + " °C");
        table2.getRow(0).getCell(3).setText(fra_if_001.getHumedadRelativa() + " %");

        XWPFTable table3 = doc.getTables().get(3);
        table3.getRow(1).getCell(1).setText(fra_if_001.getTemperaturaEnsayo());
        table3.getRow(1).getCell(3).setText(fra_if_001.getPesaEnsayo());
        table3.getRow(1).getCell(5).setText(fra_if_001.getTiempoCorte());

        XWPFTable table5 = doc.getTables().get(5);
        table5.getRow(0).getCell(1).setText(fra_if_001.getTipoMaterial());
        table5.getRow(1).getCell(1).setText(fra_if_001.getFormaFisicaMaterial());
        table5.getRow(2).getCell(1).setText(fra_if_001.getIndiceFuidez());

        XWPFTable table6 = doc.getTables().get(6);
        for (int i=0; i<lista.size(); i++) {
            table6.getRow(i+1).getCell(1).setText(lista.get(i).getPesoFilamento());
            table6.getRow(i+1).getCell(2).setText(lista.get(i).getIndiceFluidez());
            table6.getRow(i+1).getCell(3).setText(lista.get(i).getMfi());
        }
        table6.getRow(5).getCell(1).setText(fra_if_001.getPromedioPesoFilamento());
        table6.getRow(5).getCell(2).setText(fra_if_001.getPromedioIndiceFluidez());
        table6.getRow(5).getCell(3).setText(fra_if_001.getPromedioMFI());

        XWPFTable table8 = doc.getTables().get(8);
        table8.getRow(0).getCell(1).setText(fra_if_001.getObservaciones());

        XWPFTable table9 = doc.getTables().get(9);
        table9.getRow(1).getCell(0).removeParagraph(0);
        XWPFParagraph paragraph = table9.getRow(1).getCell(0).addParagraph();
        paragraph.setAlignment(ParagraphAlignment.CENTER);
        XWPFRun run = paragraph.createRun();
        InputStream inputStream = new URL(fra_if_001.getRubricaRealizo()).openStream();
        XWPFPicture xwpfPicture = run.addPicture(inputStream, XWPFDocument.PICTURE_TYPE_PNG, "Name", Units.pixelToEMU(110), Units.pixelToEMU(73));
        run.addBreak();
        run.setText(fra_if_001.getRealizo());
        table9.getRow(1).getCell(1).setText(fra_if_001.getSupervisor());

        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Disposition", "inline; filename=FRA-IF-" + estructuraNombres.getNombre() + ".docx");
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
