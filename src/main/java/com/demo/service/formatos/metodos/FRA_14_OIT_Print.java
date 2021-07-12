package com.demo.service.formatos.metodos;

import com.demo.model.operacion.MetodoMuestra;
import com.demo.model.operacion.RecepcionVerificacionRegistroCodificacion;
import com.demo.model.operacion.metodos.fra14oit.FRA_OIT_001;
import com.demo.model.operacion.metodos.fra14oit.datas.FRA_OIT_001_DATA;
import com.demo.repository.operacion.MetodoMuestraRepository;
import com.demo.repository.operacion.RecepcionVerificacionRegistroCodificacionRepository;
import com.demo.repository.operacion.metodos.fra14oit.FRA_OIT_001_Repository;
import com.demo.repository.operacion.metodos.fra14oit.datas.FRA_OIT_001_DATA_Repository;
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
public class FRA_14_OIT_Print {

    @Autowired
    private FRA_OIT_001_Repository fra_oit_001_repository;

    @Autowired
    private FRA_OIT_001_DATA_Repository fra_oit_001_data_repository;

    @Autowired
    private MetodoMuestraRepository metodoMuestraRepository;

    @Autowired
    private RecepcionVerificacionRegistroCodificacionRepository recepcionVerificacionRegistroCodificacionRepository;

    EstructuraNombres estructuraNombres = new EstructuraNombres();
    FormatoFechas formatoFechas = new FormatoFechas();

    public ResponseEntity<InputStreamResource> crearFormato(Long id, int band) throws InvalidFormatException, IOException {
        URL url = new URL("https://resources.adpmx.com/cecim/laboratorio/doc/register/methods/14-FRA-OIT-001.docx");
        XWPFDocument doc = new XWPFDocument(url.openStream());

        FRA_OIT_001 fra_oit_001;
        List<FRA_OIT_001_DATA> lista;

        if (band == 1) {
            fra_oit_001 = fra_oit_001_repository.findByIdFRAOIT(id);
        } else {
            fra_oit_001 = fra_oit_001_repository.findByMetodoMuestra_MetodoMuestraId(id);
        }
        lista = fra_oit_001_data_repository.buscarTodosPorEnsayo(fra_oit_001.getIdFRAOIT());

        XWPFTable table0 = doc.getTables().get(0);
        table0.getRow(0).getCell(1).setText(fra_oit_001.getFolioTecnica());

        XWPFTable table1 = doc.getTables().get(1);
        table1.getRow(0).getCell(1).setText(fra_oit_001.getFolioSolicitudServicioInterno());
        table1.getRow(0).getCell(3).setText(formatoFechas.formateadorFechas(fra_oit_001.getFechaInicioAnalisis()));
        table1.getRow(1).getCell(1).setText(fra_oit_001.getIdInternoMuestra());
        table1.getRow(1).getCell(3).setText(formatoFechas.formateadorFechas(fra_oit_001.getFechaFinalAnalisis()));

        XWPFTable table2 = doc.getTables().get(2);
        table2.getRow(0).getCell(1).setText(fra_oit_001.getTemperatura() + " °C");
        table2.getRow(0).getCell(3).setText(fra_oit_001.getHumedadRelativa() + " %");

        XWPFTable table3 = doc.getTables().get(3);
        table3.getRow(1).getCell(1).setText("M1: " + fra_oit_001.getPesom1());
        table3.getRow(1).getCell(2).setText("M2: " + fra_oit_001.getPesom2());
        table3.getRow(1).getCell(4).setText("M1: " + fra_oit_001.getPosicionm1());
        table3.getRow(1).getCell(5).setText("M2: " + fra_oit_001.getPosicionm2());

        XWPFTable table4 = doc.getTables().get(4);
        table4.getRow(1).getCell(2).setText(fra_oit_001.getPurga());
        table4.getRow(2).getCell(1).setText(fra_oit_001.getTpurga());
        table4.getRow(2).getCell(2).setText(fra_oit_001.getTequilibrio());
        table4.getRow(3).getCell(1).setText(fra_oit_001.getTequilibrio());
        table4.getRow(3).getCell(2).setText(fra_oit_001.getTiempoEquilibrio());
        table4.getRow(4).getCell(1).setText(fra_oit_001.getTequilibrio());
        table4.getRow(4).getCell(2).setText(fra_oit_001.getDinamicaCal1());
        table4.getRow(6).getCell(2).setText(fra_oit_001.getTiempoIsotermico1());
        table4.getRow(8).getCell(2).setText(fra_oit_001.getTiempoIsotermico2());
        table4.getRow(10).getCell(1).setText(fra_oit_001.getDinamicaCal1());
        table4.getRow(10).getCell(2).setText(fra_oit_001.getDinamicaEnf1());
        table4.getRow(11).getCell(1).setText(fra_oit_001.getTasaEnfriamiento());
        table4.getRow(12).getCell(1).setText(fra_oit_001.getTemperaturaEmergencia());

        XWPFTable table5 = doc.getTables().get(5);
        for (int i = 0; i < lista.size(); i++) {
            table5.getRow(i+2).getCell(1).setText(lista.get(i).getNoDobleces());
            table5.getRow(i+2).getCell(2).setText(lista.get(i).getE1());
            table5.getRow(i+2).getCell(3).setText(lista.get(i).getE2());
            table5.getRow(i+2).getCell(4).setText(lista.get(i).getE3());
            table5.getRow(i+2).getCell(5).setText(lista.get(i).getEspesorPromedio());
        }

        if (lista.size()==1) {
            table5.getRow(3).getCell(1).setText("N/A");
            table5.getRow(3).getCell(2).setText("N/A");
            table5.getRow(3).getCell(3).setText("N/A");
            table5.getRow(3).getCell(4).setText("N/A");
            table5.getRow(3).getCell(5).setText("N/A");
        }

        XWPFTable table6 = doc.getTables().get(6);
        table6.getRow(1).getCell(0).setText(fra_oit_001.getM1OIT());
        table6.getRow(1).getCell(1).setText(fra_oit_001.getM2OIT());
        table6.getRow(1).getCell(2).setText(fra_oit_001.getPromedioOIT());

        XWPFTable table7 = doc.getTables().get(7);
        table7.getRow(0).getCell(1).setText(fra_oit_001.getObservaciones());

        XWPFTable table8 = doc.getTables().get(8);
        table8.getRow(1).getCell(0).removeParagraph(0);
        XWPFParagraph paragraph = table8.getRow(1).getCell(0).addParagraph();
        paragraph.setAlignment(ParagraphAlignment.CENTER);
        XWPFRun run = paragraph.createRun();
        InputStream inputStream = new URL(fra_oit_001.getRubricaRealizo()).openStream();
        XWPFPicture xwpfPicture = run.addPicture(inputStream, XWPFDocument.PICTURE_TYPE_PNG, "Name", Units.pixelToEMU(110), Units.pixelToEMU(73));
        run.addBreak();
        run.setText(fra_oit_001.getRealizo());
        table8.getRow(1).getCell(1).setText(fra_oit_001.getSupervisor());

        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Disposition", "inline; filename=FRA-OIT-" + estructuraNombres.getNombre() + ".docx");
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

    public ResponseEntity<InputStreamResource> crearListaFolios() throws InvalidFormatException, IOException {
        ClassPathResource resource = new ClassPathResource("/documentos/METODOS/Listas/BFF-MIE-015.docx");
        XWPFDocument doc = new XWPFDocument(resource.getInputStream());
        List<MetodoMuestra> metodoMuestraList = metodoMuestraRepository.findAllByMethod_MethodId(62L);

        XWPFTable table = doc.getTables().get(0);
        table.getRow(0).setRepeatHeader(true);
        for (int i = 0; i < metodoMuestraList.size(); i++) {
            try {
                XWPFTableRow tableRow = table.createRow();
                tableRow.getCell(0).setText(metodoMuestraList.get(i).getFolioTecnica());
                RecepcionVerificacionRegistroCodificacion recepcionVerificacionRegistroCodificacion = recepcionVerificacionRegistroCodificacionRepository.findBySolicitudServicioClienteMuestras_SolicitudServicioClienteMuestrasId(metodoMuestraList.get(i).getSolicitudServicioClienteMuestras().getSolicitudServicioClienteMuestrasId());
                tableRow.getCell(1).setText(recepcionVerificacionRegistroCodificacion.getIdInternoMuestra1());
                tableRow.getCell(2).setText(metodoMuestraList.get(i).getSolicitudServicioClienteMuestras().getSolicitudServicioCliente().getFechaRecepcionMuestras());
                tableRow.getCell(3).setText(metodoMuestraList.get(i).getSolicitudServicioClienteMuestras().getSolicitudServicioCliente().getFolioSolitudServicioCliente());
            } catch (NullPointerException e) {
                System.out.println("Error en la iteración " + i);
            }
        }

        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Disposition", "inline; filename=BFF-MIE-015-" + estructuraNombres.getNombre() + ".docx");
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
