package com.demo.service.formatos.metodos;

import com.demo.model.operacion.metodos.fra11eat.FRA_EAT_001;
import com.demo.model.operacion.metodos.fra11eat.datas.FRA_EAT_001_DATA;
import com.demo.repository.operacion.metodos.FRA_EAT_001_DATA_Repository;
import com.demo.repository.operacion.metodos.FRA_EAT_001_Repository;
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

import java.io.*;
import java.net.URL;
import java.util.List;

@Service
public class FRA_11_EAT_Print {

    @Autowired
    private FRA_EAT_001_Repository fra_eat_001_repository;

    @Autowired
    private FRA_EAT_001_DATA_Repository fra_eat_001_data_repository;

    EstructuraNombres estructuraNombres = new EstructuraNombres();
    FormatoFechas formatoFechas = new FormatoFechas();

    public ResponseEntity<InputStreamResource> crearFormato(Long id, int band) throws InvalidFormatException, IOException {
//        ClassPathResource resource = new ClassPathResource("/documentos/METODOS/FRA-EAT-001.docx");
//        XWPFDocument doc = new XWPFDocument(resource.getInputStream());
        URL url = new URL("https://resources.adpmx.com/cecim/laboratorio/doc/register/methods/11-FRA-EAT-001-V2.docx");
        XWPFDocument doc = new XWPFDocument(url.openStream());

        FRA_EAT_001 fra_eat_001;
        List<FRA_EAT_001_DATA> lista;
        if (band == 1){
            fra_eat_001 = fra_eat_001_repository.findByIdFRAEAT(id);
            lista = fra_eat_001_data_repository.findRandPreguntas(id);
        }else {
            fra_eat_001 = fra_eat_001_repository.findByMetodoMuestra_MetodoMuestraId(id);
            lista = fra_eat_001_data_repository.findRandPreguntas(fra_eat_001.getIdFRAEAT());
        }

        XWPFTable table0 = doc.getTables().get(0);
        table0.getRow(0).getCell(1).setText(fra_eat_001.getFolioTecnica());

        XWPFTable table1 = doc.getTables().get(1);
        table1.getRow(0).getCell(1).setText(fra_eat_001.getFolioSolicitudServicioInterno());
        table1.getRow(0).getCell(3).setText(formatoFechas.formateadorFechas(fra_eat_001.getFechaInicioAnalisis()));
        table1.getRow(1).getCell(1).setText(fra_eat_001.getIdInternoMuestra());
        table1.getRow(1).getCell(3).setText(formatoFechas.formateadorFechas(fra_eat_001.getFechaFinalAnalisis()));

        XWPFTable table2 = doc.getTables().get(2);
        table2.getRow(0).getCell(1).setText(fra_eat_001.getTemperatura());
        table2.getRow(0).getCell(3).setText(fra_eat_001.getHumedadRelativa());
        table2.getRow(1).getCell(1).setText(fra_eat_001.getCodigoHorno());
        table2.getRow(1).getCell(3).setText(fra_eat_001.getTemperaturaEnsayo());
        table2.getRow(2).getCell(1).setText(fra_eat_001.getCicloTomaFotografica());
        table2.getRow(2).getCell(3).setText(fra_eat_001.getTipoMaterial());
        table2.getRow(3).getCell(1).setText(fra_eat_001.getDescripcionCiclo());

        int contadorRow=0;
        int contadorCell=1;
        XWPFTable table = doc.getTables().get(3);
        for (int i = 0; i<lista.size(); i++) {
            contadorRow++;
            if (contadorRow == 15){
                contadorRow = 1;
                contadorCell = contadorCell + 3;
            }
            table.getRow(contadorRow).getCell(contadorCell).setText(lista.get(i).getCreatedAt().toString());
            table.getRow(contadorRow).getCell(contadorCell+1).removeParagraph(0);
            XWPFParagraph paragraph = table.getRow(contadorRow).getCell(contadorCell+1).addParagraph();
            XWPFRun run = paragraph.createRun();
            InputStream inputStream = new URL(lista.get(i).getRubrica()).openStream();
            XWPFPicture picture = run.addPicture(inputStream, XWPFDocument.PICTURE_TYPE_PNG, "Name", Units.pixelToEMU(70), Units.pixelToEMU(35));
        }
        table.getRow(15).getCell(1).setText(fra_eat_001.getTiempoTotalExposicion());
        table.getRow(15).getCell(3).setText(lista.size()+"");

        XWPFTable table4 = doc.getTables().get(4);
        table4.getRow(0).getCell(1).setText(fra_eat_001.getObservaciones());

        XWPFTable table5 = doc.getTables().get(5);
        table5.getRow(1).getCell(0).setText(fra_eat_001.getRealizo());
        table5.getRow(1).getCell(1).setText(fra_eat_001.getSupervisor());

        XWPFTable tablaImagenes = doc.createTable();

        int cont = 0;
        int cont2 = 0;
        XWPFTableRow rowPicture;

        int tiempoExposición = 0;
        while (true){
            if (cont2==0){
                rowPicture = tablaImagenes.getRow(cont2);
                tablaImagenes.removeBorders();
            } else {
                rowPicture = tablaImagenes.createRow();
                tablaImagenes.removeBorders();
            }
            for (int j = 0; j<4; j++){
                if (j==0 && cont2 == 0){
                    XWPFParagraph paragraph = rowPicture.getCell(0).addParagraph();
                    XWPFRun run = paragraph.createRun();
                    InputStream inputStream = new URL(lista.get(cont).getPathImage()).openStream();

                    XWPFPicture picture = run.addPicture(inputStream, XWPFDocument.PICTURE_TYPE_PNG, "Name", Units.pixelToEMU(150), Units.pixelToEMU(100));
                    run.addBreak();
                    run.setText("Tiempo exposición: " + tiempoExposición + "(H)");
                    tiempoExposición += 48;
                }
                if (j!=0 && cont2 == 0){
                    XWPFParagraph paragraph = rowPicture.addNewTableCell().addParagraph();
                    XWPFRun run = paragraph.createRun();
                    InputStream inputStream = new URL(lista.get(cont).getPathImage()).openStream();

                    XWPFPicture picture = run.addPicture(inputStream, XWPFDocument.PICTURE_TYPE_PNG, "Name", Units.pixelToEMU(150), Units.pixelToEMU(100));
                    run.addBreak();
                    run.setText("Tiempo exposición: " + tiempoExposición + "(H)");
                    tiempoExposición += 48;
                }
                if (j==0 && cont2 != 0){
                    XWPFParagraph paragraph = rowPicture.getCell(j).addParagraph();
                    XWPFRun run = paragraph.createRun();
                    InputStream inputStream = new URL(lista.get(cont).getPathImage()).openStream();

                    XWPFPicture picture = run.addPicture(inputStream, XWPFDocument.PICTURE_TYPE_PNG, "Name", Units.pixelToEMU(150), Units.pixelToEMU(100));
                    run.addBreak();
                    run.setText("Tiempo exposición: " + tiempoExposición + "(H)");
                    tiempoExposición += 48;
                }
                if (j!=0 && cont2 != 0) {
                    XWPFParagraph paragraph = rowPicture.getCell(j).addParagraph();
                    XWPFRun run = paragraph.createRun();
                    InputStream inputStream = new URL(lista.get(cont).getPathImage()).openStream();

                    XWPFPicture picture = run.addPicture(inputStream, XWPFDocument.PICTURE_TYPE_PNG, "Name", Units.pixelToEMU(150), Units.pixelToEMU(100));
                    run.addBreak();
                    run.setText("Tiempo exposición: " + tiempoExposición + "(H)");
                    tiempoExposición += 48;
                }
                cont++;
                if (cont >= lista.size()){
                    break;
                }
            }
            if (cont >= lista.size()){
                break;
            }
            cont2++;
        }

        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Disposition", "inline; filename=FRA-EAT-"+estructuraNombres.getNombre()+".docx");
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
