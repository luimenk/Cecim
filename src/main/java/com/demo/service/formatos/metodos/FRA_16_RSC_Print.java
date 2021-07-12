package com.demo.service.formatos.metodos;

import com.demo.model.operacion.metodos.fra16rsc.FRA_RSC_001;
import com.demo.model.operacion.metodos.fra16rsc.datas.FRA_RSC_001_DATA;
import com.demo.repository.operacion.metodos.fra16rsc.FRA_RSC_001_Repository;
import com.demo.repository.operacion.metodos.fra16rsc.datas.FRA_RSC_001_DATA_Repository;
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
public class FRA_16_RSC_Print {

    @Autowired
    private FRA_RSC_001_Repository fra_rsc_001_repository;

    @Autowired
    private FRA_RSC_001_DATA_Repository fra_rsc_001_data_repository;

    EstructuraNombres estructuraNombres = new EstructuraNombres();
    FormatoFechas formatoFechas = new FormatoFechas();

    public ResponseEntity<InputStreamResource> crearFormato(Long id, int band) throws InvalidFormatException, IOException {
        URL url = new URL("https://resources.adpmx.com/cecim/laboratorio/doc/register/methods/16-FRA-RSC-001.docx");
        XWPFDocument doc = new XWPFDocument(url.openStream());

        FRA_RSC_001 fra_rsc_001;
        List<FRA_RSC_001_DATA> lista;
        if (band == 1) {
            fra_rsc_001 = fra_rsc_001_repository.findByIdFRARSC(id);
        } else {
            fra_rsc_001 = fra_rsc_001_repository.findByMetodoMuestra_MetodoMuestraId(id);
        }
        lista = fra_rsc_001_data_repository.buscarTodosPorEnsayo(fra_rsc_001.getIdFRARSC());

        XWPFTable table0 = doc.getTables().get(0);
        table0.getRow(0).getCell(1).setText(fra_rsc_001.getFolioTecnica());

        XWPFTable table1 = doc.getTables().get(1);
        table1.getRow(0).getCell(1).setText(fra_rsc_001.getFolioSolicitudServicioInterno());
        table1.getRow(0).getCell(3).setText(formatoFechas.formateadorFechas(fra_rsc_001.getFechaInicioAnalisis()));
        table1.getRow(1).getCell(1).setText(fra_rsc_001.getIdInternoMuestra());
        table1.getRow(1).getCell(3).setText(formatoFechas.formateadorFechas(fra_rsc_001.getFechaFinalAnalisis()));

        XWPFTable table2 = doc.getTables().get(2);
        table2.getRow(0).getCell(1).setText(fra_rsc_001.getTemperatura() + " °C");
        table2.getRow(0).getCell(3).setText(fra_rsc_001.getHumedadRelativa() + " %");

        XWPFTable table3 = doc.getTables().get(3);
        table3.getRow(0).getCell(1).setText(fra_rsc_001.getAnchoMuestra());
        table3.getRow(0).getCell(3).setText(fra_rsc_001.getAnchoMordazas());
        table3.getRow(0).getCell(7).setText(fra_rsc_001.getPresion());

        XWPFTable table4 = doc.getTables().get(4);
        table4.getRow(0).getCell(1).setText(fra_rsc_001.getTiempoSellado());
        table4.getRow(0).getCell(3).setText(fra_rsc_001.getTiempoRetardo());
        table4.getRow(0).getCell(5).setText(fra_rsc_001.getVelocidadDesprendimiento());

        XWPFTable table5 = doc.getTables().get(5);
        table5.getRow(0).getCell(1).setText(fra_rsc_001.getTemperaturaSellado1());
        table5.getRow(1).getCell(1).setText(fra_rsc_001.getTemperaturaSellado2());
        table5.getRow(2).getCell(1).setText(fra_rsc_001.getTemperaturaSellado3());
        table5.getRow(3).getCell(1).setText(fra_rsc_001.getTemperaturaSellado4());
        table5.getRow(4).getCell(1).setText(fra_rsc_001.getTemperaturaSellado5());

        table5.getRow(0).getCell(3).setText(fra_rsc_001.getTemperaturaMordazaSuperior1());
        table5.getRow(1).getCell(3).setText(fra_rsc_001.getTemperaturaMordazaSuperior2());
        table5.getRow(2).getCell(3).setText(fra_rsc_001.getTemperaturaMordazaSuperior3());
        table5.getRow(3).getCell(3).setText(fra_rsc_001.getTemperaturaMordazaSuperior4());
        table5.getRow(4).getCell(3).setText(fra_rsc_001.getTemperaturaMordazaSuperior5());

        table5.getRow(0).getCell(5).setText(fra_rsc_001.getTemperaturaMordazaInferior1());
        table5.getRow(1).getCell(5).setText(fra_rsc_001.getTemperaturaMordazaInferior2());
        table5.getRow(2).getCell(5).setText(fra_rsc_001.getTemperaturaMordazaInferior3());
        table5.getRow(3).getCell(5).setText(fra_rsc_001.getTemperaturaMordazaInferior4());
        table5.getRow(4).getCell(5).setText(fra_rsc_001.getTemperaturaMordazaInferior5());

        table5.getRow(0).getCell(7).setText(fra_rsc_001.getTasaIncrementoTemperaturaMordaza());

        XWPFTable table6 = doc.getTables().get(6);
        table6.getRow(0).getCell(1).setText(fra_rsc_001.getEjeAnalisis());
        table6.getRow(0).getCell(3).setText(fra_rsc_001.getCaraAnalisis());
        table6.getRow(0).getCell(5).setText(fra_rsc_001.getTipoMaterial());

        XWPFTable table7 = doc.getTables().get(7);
        table7.getRow(1).getCell(1).setText(lista.get(0).getTemperatura());

        table7.getRow(1).getCell(3).setText(lista.get(0).getFuerzaSello1());
        table7.getRow(1).getCell(4).setText(lista.get(0).getDesviacionEstandar1());
        table7.getRow(1).getCell(5).setText(lista.get(0).getModoFalla1());

        table7.getRow(2).getCell(3).setText(lista.get(0).getFuerzaSello2());
        table7.getRow(2).getCell(4).setText(lista.get(0).getDesviacionEstandar2());
        table7.getRow(2).getCell(5).setText(lista.get(0).getModoFalla2());

        table7.getRow(3).getCell(3).setText(lista.get(0).getFuerzaSello3());
        table7.getRow(3).getCell(4).setText(lista.get(0).getDesviacionEstandar3());
        table7.getRow(3).getCell(5).setText(lista.get(0).getModoFalla3());

        table7.getRow(4).getCell(1).setText(fra_rsc_001.getPromedioFuerzaSello1());
        table7.getRow(4).getCell(2).setText(fra_rsc_001.getPromedioDesvEstandar1());

        table7.getRow(5).getCell(1).setText(lista.get(1).getTemperatura());

        table7.getRow(5).getCell(3).setText(lista.get(1).getFuerzaSello1());
        table7.getRow(5).getCell(4).setText(lista.get(1).getDesviacionEstandar1());
        table7.getRow(5).getCell(5).setText(lista.get(1).getModoFalla1());

        table7.getRow(6).getCell(3).setText(lista.get(1).getFuerzaSello2());
        table7.getRow(6).getCell(4).setText(lista.get(1).getDesviacionEstandar2());
        table7.getRow(6).getCell(5).setText(lista.get(1).getModoFalla2());

        table7.getRow(7).getCell(3).setText(lista.get(1).getFuerzaSello3());
        table7.getRow(7).getCell(4).setText(lista.get(1).getDesviacionEstandar3());
        table7.getRow(7).getCell(5).setText(lista.get(1).getModoFalla3());

        table7.getRow(8).getCell(1).setText(fra_rsc_001.getPromedioFuerzaSello2());
        table7.getRow(8).getCell(2).setText(fra_rsc_001.getPromedioDesvEstandar2());

        table7.getRow(9).getCell(1).setText(lista.get(2).getTemperatura());

        table7.getRow(9).getCell(3).setText(lista.get(2).getFuerzaSello1());
        table7.getRow(9).getCell(4).setText(lista.get(2).getDesviacionEstandar1());
        table7.getRow(9).getCell(5).setText(lista.get(2).getModoFalla1());

        table7.getRow(10).getCell(3).setText(lista.get(2).getFuerzaSello2());
        table7.getRow(10).getCell(4).setText(lista.get(2).getDesviacionEstandar2());
        table7.getRow(10).getCell(5).setText(lista.get(2).getModoFalla2());

        table7.getRow(11).getCell(3).setText(lista.get(2).getFuerzaSello3());
        table7.getRow(11).getCell(4).setText(lista.get(2).getDesviacionEstandar3());
        table7.getRow(11).getCell(5).setText(lista.get(2).getModoFalla3());

        table7.getRow(12).getCell(1).setText(fra_rsc_001.getPromedioFuerzaSello3());
        table7.getRow(12).getCell(2).setText(fra_rsc_001.getPromedioDesvEstandar3());

        table7.getRow(13).getCell(1).setText(lista.get(3).getTemperatura());

        table7.getRow(13).getCell(3).setText(lista.get(3).getFuerzaSello1());
        table7.getRow(13).getCell(4).setText(lista.get(3).getDesviacionEstandar1());
        table7.getRow(13).getCell(5).setText(lista.get(3).getModoFalla1());

        table7.getRow(14).getCell(3).setText(lista.get(3).getFuerzaSello2());
        table7.getRow(14).getCell(4).setText(lista.get(3).getDesviacionEstandar2());
        table7.getRow(14).getCell(5).setText(lista.get(3).getModoFalla2());

        table7.getRow(15).getCell(3).setText(lista.get(3).getFuerzaSello3());
        table7.getRow(15).getCell(4).setText(lista.get(3).getDesviacionEstandar3());
        table7.getRow(15).getCell(5).setText(lista.get(3).getModoFalla3());

        table7.getRow(16).getCell(1).setText(fra_rsc_001.getPromedioFuerzaSello4());
        table7.getRow(16).getCell(2).setText(fra_rsc_001.getPromedioDesvEstandar4());

        table7.getRow(17).getCell(1).setText(lista.get(4).getTemperatura());

        table7.getRow(17).getCell(3).setText(lista.get(4).getFuerzaSello1());
        table7.getRow(17).getCell(4).setText(lista.get(4).getDesviacionEstandar1());
        table7.getRow(17).getCell(5).setText(lista.get(4).getModoFalla1());

        table7.getRow(18).getCell(3).setText(lista.get(4).getFuerzaSello2());
        table7.getRow(18).getCell(4).setText(lista.get(4).getDesviacionEstandar2());
        table7.getRow(18).getCell(5).setText(lista.get(4).getModoFalla2());

        table7.getRow(19).getCell(3).setText(lista.get(4).getFuerzaSello3());
        table7.getRow(19).getCell(4).setText(lista.get(4).getDesviacionEstandar3());
        table7.getRow(19).getCell(5).setText(lista.get(4).getModoFalla3());

        table7.getRow(20).getCell(1).setText(fra_rsc_001.getPromedioFuerzaSello5());
        table7.getRow(20).getCell(2).setText(fra_rsc_001.getPromedioDesvEstandar5());

        XWPFTable table8 = doc.getTables().get(8);
        table8.getRow(0).getCell(1).setText(fra_rsc_001.getObservaciones());

        XWPFTable table9 = doc.getTables().get(9);
        table9.getRow(1).getCell(0).removeParagraph(0);
        XWPFParagraph paragraph = table9.getRow(1).getCell(0).addParagraph();
        paragraph.setAlignment(ParagraphAlignment.CENTER);
        XWPFRun run = paragraph.createRun();
        InputStream inputStream = new URL(fra_rsc_001.getRubricaRealizo()).openStream();
        XWPFPicture xwpfPicture = run.addPicture(inputStream, XWPFDocument.PICTURE_TYPE_PNG, "Name", Units.pixelToEMU(110), Units.pixelToEMU(73));
        run.addBreak();
        run.setText(fra_rsc_001.getRealizo());
        table9.getRow(1).getCell(1).setText(fra_rsc_001.getSupervisor());

        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Disposition", "inline; filename=FRA-RSC-" + estructuraNombres.getNombre() + ".docx");
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
