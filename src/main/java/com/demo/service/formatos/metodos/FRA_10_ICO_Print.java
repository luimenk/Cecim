package com.demo.service.formatos.metodos;

import com.demo.model.operacion.metodos.fra10ico.FRA_ICO_001;
import com.demo.model.operacion.metodos.fra10ico.datas.FRA_ICO_001_DATA;
import com.demo.repository.operacion.metodos.fra10ico.FRA_ICO_001_Repository;
import com.demo.repository.operacion.metodos.fra10ico.datas.FRA_ICO_001_DATA_Repository;
import com.demo.utils.EstructuraNombres;
import com.demo.utils.FormatoFechas;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.util.Units;
import org.apache.poi.xwpf.usermodel.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.InputStreamResource;
import org.springframework.data.annotation.AccessType;
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
public class FRA_10_ICO_Print {

    @Autowired
    private FRA_ICO_001_Repository fra_ico_001_repository;

    @Autowired
    private FRA_ICO_001_DATA_Repository fra_ico_001_data_repository;

    EstructuraNombres estructuraNombres = new EstructuraNombres();
    FormatoFechas formatoFechas = new FormatoFechas();

    public ResponseEntity<InputStreamResource> crearFormato(Long id, int band) throws InvalidFormatException, IOException {
        URL url = new URL("https://resources.adpmx.com/cecim/laboratorio/doc/register/methods/10-FRA-ICO-001.docx");
        XWPFDocument doc = new XWPFDocument(url.openStream());

        FRA_ICO_001 fra_ico_001;
        List<FRA_ICO_001_DATA> lista;

        if (band == 1) {
            fra_ico_001 = fra_ico_001_repository.findByIdFRAICO(id);
            lista = fra_ico_001_data_repository.buscarTodosPorEnsayo(id);
        } else {
            fra_ico_001 = fra_ico_001_repository.findByMetodoMuestra_MetodoMuestraId(id);
            lista = fra_ico_001_data_repository.buscarTodosPorEnsayo(fra_ico_001.getIdFRAICO());
        }

        XWPFTable table0 = doc.getTables().get(0);
        table0.getRow(0).getCell(1).setText(fra_ico_001.getFolioTecnica());

        XWPFTable table1 = doc.getTables().get(1);
        table1.getRow(0).getCell(1).setText(fra_ico_001.getFolioSolicitudServicioInterno());
        table1.getRow(0).getCell(3).setText(formatoFechas.formateadorFechas(fra_ico_001.getFechaInicioAnalisis()));
        table1.getRow(1).getCell(1).setText(fra_ico_001.getIdInternoMuestra());
        table1.getRow(1).getCell(3).setText(formatoFechas.formateadorFechas(fra_ico_001.getFechaFinalAnalisis()));

        XWPFTable table2 = doc.getTables().get(2);
        table2.getRow(0).getCell(1).setText(fra_ico_001.getTemperatura() + " °C");
        table2.getRow(0).getCell(3).setText(fra_ico_001.getHumedadRelativa() + " %");
        table2.getRow(1).getCell(1).setText(fra_ico_001.getTipoMaterial());
        table2.getRow(1).getCell(3).setText(fra_ico_001.getCaraAnalisis());
        table2.getRow(2).getCell(1).setText(fra_ico_001.getTipoSuperficie());
        table2.getRow(2).getCell(2).setText(fra_ico_001.getEspecifiqueTipoSuperficie());
        table2.getRow(3).getCell(1).setText(fra_ico_001.getTipoProducto());
        table2.getRow(3).getCell(3).setText(fra_ico_001.getGeometria());
        table2.getRow(4).getCell(1).setText(fra_ico_001.getAditivoBiodegradable());
        table2.getRow(4).getCell(3).setText(fra_ico_001.getGradoAditivo());
        table2.getRow(4).getCell(5).setText(fra_ico_001.getPorcentajeInclusion());
        table2.getRow(5).getCell(1).setText(fra_ico_001.getTipoEnvejecimiento());
        table2.getRow(5).getCell(3).setText(fra_ico_001.getTiempoEnvejecimiento());
        table2.getRow(7).getCell(3).setText(fra_ico_001.getMetodoAnalisis());
        table2.getRow(8).getCell(1).setText(fra_ico_001.getNumeroBarridos());
        table2.getRow(9).getCell(1).setText(fra_ico_001.getNumeroOnda());
        table2.getRow(9).getCell(3).setText(fra_ico_001.getLineaBase());
        table2.getRow(10).getCell(1).setText(fra_ico_001.getGrupoCarbonillo());
        table2.getRow(10).getCell(3).setText(fra_ico_001.getGrupoAlifatico());

        int tiempo = 0;
        XWPFTable table3 = doc.getTables().get(3);
        table3.getRow(0).setRepeatHeader(true);
        for (int i = 0; i < lista.size(); i++) {
            tiempo = tiempo + Integer.parseInt(lista.get(i).getTiempoExposicion());
            XWPFTableRow tableRow = table3.createRow();
            tableRow.getCell(0).setText(tiempo + "");
            tableRow.getCell(1).setText(lista.get(i).getE1());
            tableRow.getCell(2).setText(lista.get(i).getE2());
            tableRow.getCell(3).setText(lista.get(i).getE3());
            tableRow.getCell(4).setText(lista.get(i).getPromedioEspesor());
            tableRow.getCell(5).setText(lista.get(i).getMedicion1());
            tableRow.getCell(6).setText(lista.get(i).getMedicion2());
            tableRow.getCell(7).setText(lista.get(i).getPromedioCarbonillo());
        }

        XWPFTable table4 = doc.getTables().get(4);
        table4.getRow(0).getCell(1).setText(fra_ico_001.getObservaciones());

        XWPFTable table5 = doc.getTables().get(5);
        table5.getRow(1).getCell(0).removeParagraph(0);
        XWPFParagraph paragraph = table5.getRow(1).getCell(0).addParagraph();
        paragraph.setAlignment(ParagraphAlignment.CENTER);
        XWPFRun run = paragraph.createRun();
        InputStream inputStream = new URL(fra_ico_001.getRubricaRealizo()).openStream();
        XWPFPicture xwpfPicture = run.addPicture(inputStream, XWPFDocument.PICTURE_TYPE_PNG, "Name", Units.pixelToEMU(110), Units.pixelToEMU(73));
        run.addBreak();
        run.setText(fra_ico_001.getRealizo());
        table5.getRow(1).getCell(1).setText(fra_ico_001.getSupervisor());

        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Disposition", "inline; filename=FRA-ICO-" + estructuraNombres.getNombre() + ".docx");
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
