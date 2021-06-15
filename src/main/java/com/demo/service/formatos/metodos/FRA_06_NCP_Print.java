package com.demo.service.formatos.metodos;

import com.demo.model.operacion.metodos.fra06ncp.FRA_NCP_001;
import com.demo.model.operacion.metodos.fra06ncp.datas.FRA_NCP_001_DATA_01;
import com.demo.model.operacion.metodos.fra06ncp.datas.FRA_NCP_001_DATA_02;
import com.demo.model.operacion.metodos.fra06ncp.datas.FRA_NCP_001_DATA_03;
import com.demo.model.operacion.metodos.fra06ncp.datas.FRA_NCP_001_DATA_04;
import com.demo.repository.operacion.metodos.fra06ncp.FRA_NCP_001_Repository;
import com.demo.repository.operacion.metodos.fra06ncp.datas.FRA_NCP_001_DATA_01_Repository;
import com.demo.repository.operacion.metodos.fra06ncp.datas.FRA_NCP_001_DATA_02_Repository;
import com.demo.repository.operacion.metodos.fra06ncp.datas.FRA_NCP_001_DATA_03_Repository;
import com.demo.repository.operacion.metodos.fra06ncp.datas.FRA_NCP_001_DATA_04_Repository;
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
public class FRA_06_NCP_Print {

    @Autowired
    private FRA_NCP_001_Repository fra_ncp_001_repository;

    @Autowired
    private FRA_NCP_001_DATA_01_Repository fra_ncp_001_data_01_repository;

    @Autowired
    private FRA_NCP_001_DATA_02_Repository fra_ncp_001_data_02_repository;

    @Autowired
    private FRA_NCP_001_DATA_03_Repository fra_ncp_001_data_03_repository;

    @Autowired
    private FRA_NCP_001_DATA_04_Repository fra_ncp_001_data_04_repository;

    EstructuraNombres estructuraNombres = new EstructuraNombres();
    FormatoFechas formatoFechas = new FormatoFechas();

    public ResponseEntity<InputStreamResource> crearFormato(Long id, int band) throws InvalidFormatException, IOException {
//        ClassPathResource resource = new ClassPathResource("/documentos/METODOS/FRA-NCP-001.docx");
//        XWPFDocument doc = new XWPFDocument(resource.getInputStream());
        URL url = new URL("https://resources.adpmx.com/cecim/laboratorio/doc/register/methods/06-FRA-NCP-001.docx");
        XWPFDocument doc = new XWPFDocument(url.openStream());

        FRA_NCP_001 fra_ncp_001;
        List<FRA_NCP_001_DATA_01> lista1;
        List<FRA_NCP_001_DATA_02> lista2;
        List<FRA_NCP_001_DATA_03> lista3;
        List<FRA_NCP_001_DATA_04> lista4;

        if (band == 1){
            fra_ncp_001 = fra_ncp_001_repository.findByIdFRANCP(id);
            lista1 = fra_ncp_001_data_01_repository.buscarTodosPorEnsayo(fra_ncp_001.getIdFRANCP());
            lista2 = fra_ncp_001_data_02_repository.buscarTodosPorEnsayo(fra_ncp_001.getIdFRANCP());
            lista3 = fra_ncp_001_data_03_repository.buscarTodosPorEnsayo(fra_ncp_001.getIdFRANCP());
            lista4 = fra_ncp_001_data_04_repository.buscarTodosPorEnsayo(fra_ncp_001.getIdFRANCP());
        } else {
            fra_ncp_001 = fra_ncp_001_repository.findByMetodoMuestra_MetodoMuestraId(id);
            lista1 = fra_ncp_001_data_01_repository.buscarTodosPorEnsayo(fra_ncp_001.getIdFRANCP());
            lista2 = fra_ncp_001_data_02_repository.buscarTodosPorEnsayo(fra_ncp_001.getIdFRANCP());
            lista3 = fra_ncp_001_data_03_repository.buscarTodosPorEnsayo(fra_ncp_001.getIdFRANCP());
            lista4 = fra_ncp_001_data_04_repository.buscarTodosPorEnsayo(fra_ncp_001.getIdFRANCP());
        }

        XWPFTable table0 = doc.getTables().get(0);
        table0.getRow(0).getCell(1).setText(fra_ncp_001.getFolioTecnica());

        XWPFTable table1 = doc.getTables().get(1);
        table1.getRow(0).getCell(1).setText(fra_ncp_001.getFolioSolicitudServicioInterno());
        table1.getRow(0).getCell(3).setText(formatoFechas.formateadorFechas(fra_ncp_001.getFechaInicioAnalisis()));
        table1.getRow(1).getCell(1).setText(fra_ncp_001.getIdInternoMuestra());
        table1.getRow(1).getCell(3).setText(formatoFechas.formateadorFechas(fra_ncp_001.getFechaFinalAnalisis()));

        XWPFTable table2 = doc.getTables().get(2);
        table2.getRow(0).getCell(1).setText(fra_ncp_001.getTemperatura() + " °C");
        table2.getRow(0).getCell(3).setText(fra_ncp_001.getHumedadRelativa() + " %");
        table2.getRow(2).getCell(3).setText(fra_ncp_001.getLente());

        XWPFTable table3 = doc.getTables().get(3);
        for (int i=0; i<lista1.size(); i++) {
            table3.getRow(i+2).getCell(1).setText(lista1.get(i).getEspesorTotalMicrometro());
            table3.getRow(i+2).getCell(3).setText(lista2.get(i).getEspesorTotalMicrometro());
        }
        table3.getRow(5).getCell(1).setText(fra_ncp_001.getEspesorTotalPromedioMM1());
        table3.getRow(5).getCell(3).setText(fra_ncp_001.getEspesorTotalPromedioMM2());
        table3.getRow(6).getCell(1).setText(fra_ncp_001.getEspesorTotalPromedioUM1());
        table3.getRow(6).getCell(3).setText(fra_ncp_001.getEspesorTotalPromedioUM2());

        XWPFTable table4 = doc.getTables().get(4);
        for (int i=0; i < 6; i++){
            if (i < lista3.size()) {
                table4.getRow(i+2).getCell(1).setText(lista3.get(i).getEspesorPorMicroscopia());
            } else {
                table4.getRow(i+2).getCell(1).setText("N/A");
            }

            if (i < lista4.size()) {
                table4.getRow(i+2).getCell(3).setText(lista4.get(i).getEspesorPorMicroscopia());
            } else {
                table4.getRow(i+2).getCell(3).setText("N/A");
            }
        }
        table4.getRow(8).getCell(1).setText(fra_ncp_001.getNumeroTotalCapas1());
        table4.getRow(8).getCell(3).setText(fra_ncp_001.getEspesorTotalMicroscopia1());
        table4.getRow(8).getCell(5).setText(fra_ncp_001.getNumeroTotalCapas2());
        table4.getRow(8).getCell(7).setText(fra_ncp_001.getEspesorTotalMicroscopia2());

        XWPFTable table5 = doc.getTables().get(5);
        table5.getRow(0).getCell(1).setText(fra_ncp_001.getObservaciones());

        XWPFTable table6 = doc.getTables().get(6);
        table6.getRow(1).getCell(0).removeParagraph(0);
        XWPFParagraph paragraph = table6.getRow(1).getCell(0).addParagraph();
        paragraph.setAlignment(ParagraphAlignment.CENTER);
        XWPFRun run = paragraph.createRun();
        InputStream inputStream = new URL(fra_ncp_001.getRubricaRealizo()).openStream();
        XWPFPicture xwpfPicture = run.addPicture(inputStream, XWPFDocument.PICTURE_TYPE_PNG, "Name", Units.pixelToEMU(110), Units.pixelToEMU(73));
        run.addBreak();
        run.setText(fra_ncp_001.getRealizo());
        table6.getRow(1).getCell(1).setText(fra_ncp_001.getSupervisor());

        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Disposition", "inline; filename=FRA-NCP-"+estructuraNombres.getNombre()+".docx");
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
