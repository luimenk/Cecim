package com.demo.service.formatos.metodos;

import com.demo.model.operacion.MetodoMuestra;
import com.demo.model.operacion.RecepcionVerificacionRegistroCodificacion;
import com.demo.model.operacion.metodos.fra15dsc.FRA_DSC;
import com.demo.model.operacion.metodos.fra15dsc.datas.FRA_DSC_001_DATA;
import com.demo.repository.operacion.MetodoMuestraRepository;
import com.demo.repository.operacion.RecepcionVerificacionRegistroCodificacionRepository;
import com.demo.repository.operacion.metodos.fra15dsc.FRA_DSC_Repository;
import com.demo.repository.operacion.metodos.fra15dsc.datas.FRA_DSC_DATA_Repository;
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
public class FRA_15_DSC_Print {

    @Autowired
    private FRA_DSC_Repository fra_dsc_repository;

    @Autowired
    private FRA_DSC_DATA_Repository fra_dsc_data_repository;

    @Autowired
    private MetodoMuestraRepository metodoMuestraRepository;

    @Autowired
    private RecepcionVerificacionRegistroCodificacionRepository recepcionVerificacionRegistroCodificacionRepository;

    EstructuraNombres estructuraNombres = new EstructuraNombres();
    FormatoFechas formatoFechas = new FormatoFechas();

    public ResponseEntity<InputStreamResource> crearFormato(Long id, int band) throws InvalidFormatException, IOException {
        URL url = new URL("https://resources.adpmx.com/cecim/laboratorio/doc/register/methods/15-FRA-DSC-001.docx");
        XWPFDocument doc = new XWPFDocument(url.openStream());

        FRA_DSC fra_dsc;
        List<FRA_DSC_001_DATA> lista;

        if (band == 1){
            fra_dsc = fra_dsc_repository.findByIdFRADSC(id);
        }else {
            fra_dsc = fra_dsc_repository.findByMetodoMuestra_MetodoMuestraId(id);
        }
        lista = fra_dsc_data_repository.buscarTodosPorEnsayo(fra_dsc.getIdFRADSC());

        XWPFTable table0 = doc.getTables().get(0);
        table0.getRow(0).getCell(1).setText(fra_dsc.getFolioTecnica());

        XWPFTable table1 = doc.getTables().get(1);
        table1.getRow(0).getCell(1).setText(fra_dsc.getFolioSolicitudServicioInterno());
        table1.getRow(0).getCell(3).setText(formatoFechas.formateadorFechas(fra_dsc.getFechaInicioAnalisis()));
        table1.getRow(1).getCell(1).setText(fra_dsc.getIdInternoMuestra());
        table1.getRow(1).getCell(3).setText(formatoFechas.formateadorFechas(fra_dsc.getFechaFinalAnalisis()));

        XWPFTable table2 = doc.getTables().get(2);
        table2.getRow(0).getCell(1).setText(fra_dsc.getTemperatura() + " °C");
        table2.getRow(0).getCell(3).setText(fra_dsc.getHumedadRelativa() + " %");

        XWPFTable table3 = doc.getTables().get(3);
        table3.getRow(1).getCell(1).setText(fra_dsc.getPeso());
        table3.getRow(1).getCell(3).setText(fra_dsc.getPosicionPortadorMuestra());

        XWPFTable table4 = doc.getTables().get(4);
        table4.getRow(1).getCell(1).setText(fra_dsc.getInicio());
        table4.getRow(1).getCell(2).setText(fra_dsc.getDinamicaCal1());
        table4.getRow(2).getCell(2).setText(fra_dsc.getTiempoIsotermico1());
        table4.getRow(3).getCell(1).setText(fra_dsc.getDinamicaCal1());
        table4.getRow(3).getCell(2).setText(fra_dsc.getDinamicaEnf1());
        table4.getRow(4).getCell(2).setText(fra_dsc.getTiempoIsotermico2());
        table4.getRow(5).getCell(1).setText(fra_dsc.getDinamicaEnf1());
        table4.getRow(5).getCell(2).setText(fra_dsc.getDinamicaCal2());
        table4.getRow(6).getCell(2).setText(fra_dsc.getTiempoIsotermico3());
        table4.getRow(7).getCell(1).setText(fra_dsc.getDinamicaCal2());
        table4.getRow(7).getCell(2).setText(fra_dsc.getDinamicaEnf2());
        table4.getRow(8).getCell(1).setText(fra_dsc.getTemperaturaEmergencia());
        table4.getRow(9).getCell(1).setText(fra_dsc.getTasaCalentamiento());
        table4.getRow(9).getCell(3).setText(fra_dsc.getTasaEnfriamiento());

        XWPFTable table5 = doc.getTables().get(5);
        for (int i=0; i<lista.size(); i++) {
            table5.getRow(i+1).getCell(0).setText(lista.get(i).getEtapa());
            table5.getRow(i+1).getCell(1).setText(lista.get(i).getTemperaturaTransicion());
            table5.getRow(i+1).getCell(2).setText(lista.get(i).getTemperaturaFusion());
            table5.getRow(i+1).getCell(3).setText(lista.get(i).getCalorFusion());
            table5.getRow(i+1).getCell(4).setText(lista.get(i).getTransicionTermica());
            table5.getRow(i+1).getCell(5).setText(lista.get(i).getTemperaturaCristalizacion());
            table5.getRow(i+1).getCell(6).setText(lista.get(i).getCalorCristalizacion());
            table5.getRow(i+1).getCell(7).setText(lista.get(i).getTransmisionTermica());
        }


        XWPFTable table6 = doc.getTables().get(6);
        table6.getRow(0).getCell(1).setText(fra_dsc.getObservaciones());

        XWPFTable table7 = doc.getTables().get(7);
        table7.getRow(1).getCell(0).removeParagraph(0);
        XWPFParagraph paragraph = table7.getRow(1).getCell(0).addParagraph();
        paragraph.setAlignment(ParagraphAlignment.CENTER);
        XWPFRun run = paragraph.createRun();
        InputStream inputStream = new URL(fra_dsc.getRubricaRealizo()).openStream();
        XWPFPicture xwpfPicture = run.addPicture(inputStream, XWPFDocument.PICTURE_TYPE_PNG, "Name", Units.pixelToEMU(110), Units.pixelToEMU(73));
        run.addBreak();
        run.setText(fra_dsc.getRealizo());
        table7.getRow(1).getCell(1).setText(fra_dsc.getSupervisor());

        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Disposition", "inline; filename=FRA-DSC-"+estructuraNombres.getNombre()+".docx");
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
        ClassPathResource resource = new ClassPathResource("/documentos/METODOS/Listas/BFF-MIE-016.docx");
        XWPFDocument doc = new XWPFDocument(resource.getInputStream());
        List<MetodoMuestra> metodoMuestraList = metodoMuestraRepository.findAllByMethod_MethodId(63L);

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
        headers.add("Content-Disposition", "inline; filename=BFF-MIE-016-"+estructuraNombres.getNombre()+".docx");
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
