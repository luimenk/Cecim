package com.demo.service.formatos.metodos;

import com.demo.model.operacion.metodos.fra09tga.FRA_TGA_001;
import com.demo.model.operacion.metodos.fra09tga.datas.FRA_TGA_001_DATA;
import com.demo.repository.operacion.metodos.fra09tga.FRA_TGA_001_Repository;
import com.demo.repository.operacion.metodos.fra09tga.datas.FRA_TGA_001_DATA_Repository;
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
public class FRA_09_TGA_Print {

    @Autowired
    private FRA_TGA_001_Repository fra_tga_001_repository;

    @Autowired
    private FRA_TGA_001_DATA_Repository fra_tga_001_data_repository;

    EstructuraNombres estructuraNombres = new EstructuraNombres();
    FormatoFechas formatoFechas = new FormatoFechas();

    public ResponseEntity<InputStreamResource> crearFormato(Long id, int band) throws InvalidFormatException, IOException {
        URL url = new URL("https://resources.adpmx.com/cecim/laboratorio/doc/register/methods/09-FRA-TGA-001.docx");
        XWPFDocument doc = new XWPFDocument(url.openStream());

        FRA_TGA_001 fra_tga_001;
        List<FRA_TGA_001_DATA> lista;
        if (band == 1){
            fra_tga_001 = fra_tga_001_repository.findByIdFRATGA(id);
        } else {
            fra_tga_001 = fra_tga_001_repository.findByMetodoMuestra_MetodoMuestraId(id);
        }
        lista = fra_tga_001_data_repository.buscarTodosPorEnsayo(fra_tga_001.getIdFRATGA());

        XWPFTable table0 = doc.getTables().get(0);
        table0.getRow(0).getCell(1).setText(fra_tga_001.getFolioTecnica());

        XWPFTable table1 = doc.getTables().get(1);
        table1.getRow(0).getCell(1).setText(fra_tga_001.getFolioSolicitudServicioInterno());
        table1.getRow(0).getCell(3).setText(formatoFechas.formateadorFechas(fra_tga_001.getFechaInicioAnalisis()));
        table1.getRow(1).getCell(1).setText(fra_tga_001.getIdInternoMuestra());
        table1.getRow(1).getCell(3).setText(formatoFechas.formateadorFechas(fra_tga_001.getFechaFinalAnalisis()));

        XWPFTable table2 = doc.getTables().get(2);
        table2.getRow(0).getCell(1).setText(fra_tga_001.getTemperatura() + " °C");
        table2.getRow(0).getCell(3).setText(fra_tga_001.getHumedadRelativa() + " %");

        XWPFTable table3 = doc.getTables().get(3);
        table3.getRow(1).getCell(1).setText(fra_tga_001.getPeso());
        table3.getRow(1).getCell(3).setText(fra_tga_001.getPosicionPortadorMuestra());
        table3.getRow(1).getCell(5).setText(fra_tga_001.getTipoMaterial());

        XWPFTable table4 = doc.getTables().get(4);
        table4.getRow(1).getCell(1).setText(fra_tga_001.getPurga());
        table4.getRow(1).getCell(2).setText(fra_tga_001.getTiempoPurga());
        table4.getRow(2).getCell(1).setText(fra_tga_001.getInicio());
        table4.getRow(2).getCell(2).setText(fra_tga_001.getDinamicaCal1());
        table4.getRow(2).getCell(4).setText(fra_tga_001.getTipoAtmosfera1());
        table4.getRow(3).getCell(1).setText(fra_tga_001.getDinamicaCal1());
        table4.getRow(3).getCell(2).setText(fra_tga_001.getDinamicaCal2());
        table4.getRow(3).getCell(4).setText(fra_tga_001.getTipoAtmosfera2());
        table4.getRow(4).getCell(1).setText(fra_tga_001.getTasaCalentamiento());
        table4.getRow(5).getCell(1).setText(fra_tga_001.getDinamicaCal2());
        table4.getRow(5).getCell(2).setText(fra_tga_001.getDinamicaEnf1());
        table4.getRow(5).getCell(3).setText(fra_tga_001.getDinamica());
        table4.getRow(6).getCell(1).setText(fra_tga_001.getTasaEnfriamiento());
        table4.getRow(7).getCell(1).setText(fra_tga_001.getTemperaturaEmergencia());


        XWPFTable table5 = doc.getTables().get(5);
        table5.getRow(1).getCell(1).setText(lista.get(0).getAvTemperatura());
        table5.getRow(1).getCell(2).setText(lista.get(0).getAvPeso());
        table5.getRow(2).getCell(1).setText(lista.get(0).getMvTemperatura());
        table5.getRow(2).getCell(2).setText(lista.get(0).getMvPeso());
        table5.getRow(3).getCell(1).setText(lista.get(0).getCombustibleTemperatura());
        table5.getRow(3).getCell(2).setText(lista.get(0).getCombustiblePeso());
        table5.getRow(4).getCell(1).setText(lista.get(0).getCenizasTemperatura());
        table5.getRow(4).getCell(2).setText(lista.get(0).getCenizasPeso());


        XWPFTable table6 = doc.getTables().get(6);
        table6.getRow(0).getCell(1).setText(fra_tga_001.getObservaciones());

        XWPFTable table7 = doc.getTables().get(7);
        table7.getRow(1).getCell(0).removeParagraph(0);
        XWPFParagraph paragraph = table7.getRow(1).getCell(0).addParagraph();
        paragraph.setAlignment(ParagraphAlignment.CENTER);
        XWPFRun run = paragraph.createRun();
        InputStream inputStream = new URL(fra_tga_001.getRubricaRealizo()).openStream();
        XWPFPicture xwpfPicture = run.addPicture(inputStream, XWPFDocument.PICTURE_TYPE_PNG, "Name", Units.pixelToEMU(110), Units.pixelToEMU(73));
        run.addBreak();
        run.setText(fra_tga_001.getRealizo());
        table7.getRow(1).getCell(1).setText(fra_tga_001.getSupervisor());
        

        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Disposition", "inline; filename=FRA-TGA-"+estructuraNombres.getNombre()+".docx");
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
