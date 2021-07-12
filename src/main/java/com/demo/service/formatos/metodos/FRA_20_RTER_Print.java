package com.demo.service.formatos.metodos;

import com.demo.model.operacion.metodos.fra20rter.FRA_RTER_001;
import com.demo.model.operacion.metodos.fra20rter.datas.FRA_RTER_001_DATA_01;
import com.demo.model.operacion.metodos.fra20rter.datas.FRA_RTER_001_DATA_02;
import com.demo.repository.operacion.metodos.fra20rter.FRA_RTER_001_Repository;
import com.demo.repository.operacion.metodos.fra20rter.datas.FRA_RTER_001_DATA_01_Repository;
import com.demo.repository.operacion.metodos.fra20rter.datas.FRA_RTER_001_DATA_02_Repository;
import com.demo.service.operacion.metodos.FRA_RTER_001_Service;
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
public class FRA_20_RTER_Print {

    @Autowired
    private FRA_RTER_001_Repository fra_rter_001_repository;

    @Autowired
    private FRA_RTER_001_DATA_01_Repository fra_rter_001_data_01_repository;

    @Autowired
    private FRA_RTER_001_DATA_02_Repository fra_rter_001_data_02_repository;

    EstructuraNombres estructuraNombres = new EstructuraNombres();
    FormatoFechas formatoFechas = new FormatoFechas();

    public ResponseEntity<InputStreamResource> crearFormato(Long id, int band) throws InvalidFormatException, IOException {
        URL url = new URL("https://resources.adpmx.com/cecim/laboratorio/doc/register/methods/20-FRA-RTER-001.docx");
        XWPFDocument doc = new XWPFDocument(url.openStream());

        FRA_RTER_001 fra_rter_001;
        List<FRA_RTER_001_DATA_01> lista1;
        List<FRA_RTER_001_DATA_02> lista2;

        if (band == 1) {
            fra_rter_001 = fra_rter_001_repository.findByIdFRARTER(id);
            lista1 = fra_rter_001_data_01_repository.buscarTodosPorEnsayo(fra_rter_001.getIdFRARTER());
            lista2 = fra_rter_001_data_02_repository.buscarTodosPorEnsayo(fra_rter_001.getIdFRARTER());

        } else {
            fra_rter_001 = fra_rter_001_repository.findByMetodoMuestra_MetodoMuestraId(id);
            lista1 = fra_rter_001_data_01_repository.buscarTodosPorEnsayo(fra_rter_001.getIdFRARTER());
            lista2 = fra_rter_001_data_02_repository.buscarTodosPorEnsayo(fra_rter_001.getIdFRARTER());
        }

        XWPFTable table0 = doc.getTables().get(0);
        table0.getRow(0).getCell(1).setText(fra_rter_001.getFolioTecnica());

        XWPFTable table1 = doc.getTables().get(1);
        table1.getRow(0).getCell(1).setText(fra_rter_001.getFolioSolicitudServicioInterno());
        table1.getRow(0).getCell(3).setText(fra_rter_001.getIdInternoMuestra());
        table1.getRow(1).getCell(1).setText(formatoFechas.formateadorFechas(fra_rter_001.getFechaInicioAnalisis()));
        table1.getRow(1).getCell(3).setText(formatoFechas.formateadorFechas(fra_rter_001.getFechaFinalAnalisis()));

        XWPFTable table2 = doc.getTables().get(2);
        table2.getRow(0).getCell(1).setText(fra_rter_001.getTemperatura() + " °C");
        table2.getRow(0).getCell(3).setText(fra_rter_001.getHumedadRelativa() + " %");
        table2.getRow(0).getCell(7).setText(fra_rter_001.getCodigoMicrometro());
        table2.getRow(1).getCell(1).setText(fra_rter_001.getDistanciaEntreMordazas());
        table2.getRow(1).getCell(3).setText(fra_rter_001.getVelocidadDeformacion());
        table2.getRow(1).getCell(5).setText(fra_rter_001.getAnchoProbeta());

        XWPFTable table3 = doc.getTables().get(3);
        for (int i=0; i < lista1.size(); i++){
            table3.getRow(i+2).getCell(0).setText((i+1)+" / " + lista1.get(i).getMedicion());
            table3.getRow(i+2).getCell(1).setText(lista1.get(i).getE1());
            table3.getRow(i+2).getCell(2).setText(lista1.get(i).getE2());
            table3.getRow(i+2).getCell(3).setText(lista1.get(i).getE3());
            table3.getRow(i+2).getCell(4).setText(lista1.get(i).getPromedioEspesor());
            table3.getRow(i+2).getCell(5).setText(lista1.get(i).getFuerzaFluenciaTension());
            table3.getRow(i+2).getCell(6).setText(lista1.get(i).getElongacionRuptura());
            table3.getRow(i+2).getCell(7).setText(lista1.get(i).getResistenciaTension());
            table3.getRow(i+2).getCell(8).setText(lista1.get(i).getPendiente());
            table3.getRow(i+2).getCell(9).setText(lista1.get(i).getCoeficienteCorrelacionPearson());
            table3.getRow(i+2).getCell(10).setText(lista1.get(i).getModuloElastico());
        }
        table3.getRow(7).getCell(1).setText(fra_rter_001.getPromedioEspesorPromedioMD());
        table3.getRow(7).getCell(2).setText(fra_rter_001.getPromedioFuerzaFluenciatensiónMD());
        table3.getRow(7).getCell(3).setText(fra_rter_001.getPromedioElongacionRupturaMD());
        table3.getRow(7).getCell(4).setText(fra_rter_001.getPromedioResistenciaTensionMD());
        table3.getRow(7).getCell(5).setText(fra_rter_001.getPromedioPendienteMD());
        table3.getRow(7).getCell(6).setText(fra_rter_001.getPromedioCoeficienteCorrelacionPearsonMD());
        table3.getRow(7).getCell(7).setText(fra_rter_001.getPromedioModuloElasticoMD());

        XWPFTable table4 = doc.getTables().get(4);
        for (int i=0; i < lista2.size(); i++){
            table4.getRow(i+2).getCell(0).setText((i+1)+" / " + lista2.get(i).getMedicion());
            table4.getRow(i+2).getCell(1).setText(lista2.get(i).getE1());
            table4.getRow(i+2).getCell(2).setText(lista2.get(i).getE2());
            table4.getRow(i+2).getCell(3).setText(lista2.get(i).getE3());
            table4.getRow(i+2).getCell(4).setText(lista2.get(i).getPromedioEspesor());
            table4.getRow(i+2).getCell(5).setText(lista2.get(i).getFuerzaFluenciaTension());
            table4.getRow(i+2).getCell(6).setText(lista2.get(i).getElongacionRuptura());
            table4.getRow(i+2).getCell(7).setText(lista2.get(i).getResistenciaTension());
            table4.getRow(i+2).getCell(8).setText(lista2.get(i).getPendiente());
            table4.getRow(i+2).getCell(9).setText(lista2.get(i).getCoeficienteCorrelacionPearson());
            table4.getRow(i+2).getCell(10).setText(lista2.get(i).getModuloElastico());
        }
        table4.getRow(7).getCell(1).setText(fra_rter_001.getPromedioEspesorPromedioTD());
        table4.getRow(7).getCell(2).setText(fra_rter_001.getPromedioFuerzaFluenciatensiónTD());
        table4.getRow(7).getCell(3).setText(fra_rter_001.getPromedioElongacionRupturaTD());
        table4.getRow(7).getCell(4).setText(fra_rter_001.getPromedioResistenciaTensionTD());
        table4.getRow(7).getCell(5).setText(fra_rter_001.getPromedioPendienteTD());
        table4.getRow(7).getCell(6).setText(fra_rter_001.getPromedioCoeficienteCorrelacionPearsonTD());
        table4.getRow(7).getCell(7).setText(fra_rter_001.getPromedioModuloElasticoTD());

        XWPFTable table5 = doc.getTables().get(5);
        table5.getRow(1).getCell(0).setText(fra_rter_001.getPromedioElongacionRupturaMD());
        table5.getRow(1).getCell(1).setText(fra_rter_001.getTasaDeformacion());
        table5.getRow(3).getCell(0).setText(fra_rter_001.getPromedioElongacionRupturaTD());


        XWPFTable table7 = doc.getTables().get(7);
        table7.getRow(0).getCell(1).setText(fra_rter_001.getObservaciones());

        XWPFTable table8 = doc.getTables().get(8);
        table8.getRow(1).getCell(0).removeParagraph(0);
        XWPFParagraph paragraph = table8.getRow(1).getCell(0).addParagraph();
        paragraph.setAlignment(ParagraphAlignment.CENTER);
        XWPFRun run = paragraph.createRun();
        InputStream inputStream = new URL(fra_rter_001.getRubricaRealizo()).openStream();
        XWPFPicture xwpfPicture = run.addPicture(inputStream, XWPFDocument.PICTURE_TYPE_PNG, "Name", Units.pixelToEMU(110), Units.pixelToEMU(73));
        run.addBreak();
        run.setText(fra_rter_001.getRealizo());
        table8.getRow(1).getCell(1).setText(fra_rter_001.getSupervisor());

        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Disposition", "inline; filename=FRA-RTER-" + estructuraNombres.getNombre() + ".docx");
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
