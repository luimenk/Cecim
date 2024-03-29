package com.demo.service.formatos.metodos;

import com.demo.model.operacion.metodos.fra07ppg.FRA_PPG_001;
import com.demo.model.operacion.metodos.fra08ftir.FRA_FTIR_001;
import com.demo.model.operacion.metodos.fra08ftir.datas.FRA_FTIR_001_DATA;
import com.demo.repository.operacion.metodos.fra08ftir.datas.FRA_FTIR_001_DATA_Repository;
import com.demo.service.operacion.metodos.FRA_FTIR_001_Service;
import com.demo.utils.EstructuraNombres;
import com.demo.utils.FormatoFechas;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.util.Units;
import org.apache.poi.xwpf.usermodel.*;
import org.apache.xmlbeans.XmlException;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTbl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.io.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

@Service
public class FRA_08_FTIR_Print {

    @Autowired
    private FRA_FTIR_001_Service fra_ftir_001_service;

    @Autowired
    private FRA_FTIR_001_DATA_Repository fra_ftir_001_data_repository;

    EstructuraNombres estructuraNombres = new EstructuraNombres();
    FormatoFechas formatoFechas = new FormatoFechas();

    public ResponseEntity<InputStreamResource> crearFormato(Long id, int band) throws InvalidFormatException, IOException {
        //ClassPathResource resource = new ClassPathResource("/documentos/METODOS/FRA-FTIR-001.docx");
        //XWPFDocument doc = new XWPFDocument(resource.getInputStream());
        URL url = new URL("https://resources.adpmx.com/cecim/laboratorio/doc/register/methods/08-FRA-FTIR-001.docx");
        XWPFDocument doc = new XWPFDocument(url.openStream());

        FRA_FTIR_001 fra_ftir_001;
        List<FRA_FTIR_001_DATA> lista;

        if (band == 1){
            fra_ftir_001 = fra_ftir_001_service.findById(id);
        }else {
            fra_ftir_001 = fra_ftir_001_service.findByMuestra(id);
        }
        lista = fra_ftir_001_data_repository.buscarTodosPorEnsayo(fra_ftir_001.getIdFRAFTIR());

        List<String> contactosAux;
        int bandera=0;

        XWPFTable table0 = doc.getTables().get(0);
        table0.getRow(0).getCell(1).setText(fra_ftir_001.getFolioTecnica());

        XWPFTable table1 = doc.getTables().get(1);
        table1.getRow(0).getCell(1).setText(fra_ftir_001.getFolioSolicitudServicioInterno());
        table1.getRow(0).getCell(3).setText(formatoFechas.formateadorFechas(fra_ftir_001.getFechaInicioAnalisis()));
        table1.getRow(1).getCell(1).setText(fra_ftir_001.getIdInternoMuestra());
        table1.getRow(1).getCell(3).setText(formatoFechas.formateadorFechas(fra_ftir_001.getFechaFinalAnalisis()));

        XWPFTable table2 = doc.getTables().get(2);
        table2.getRow(0).getCell(1).setText(fra_ftir_001.getTemperatura() + " °C");
        table2.getRow(0).getCell(3).setText(fra_ftir_001.getHumedadRelativa() + " %");
        //table2.getRow(1).getCell(1).setText(fra_ftir_001.getCodigoEspectrometro());
        table2.getRow(1).getCell(3).setText(fra_ftir_001.getMetodoAnalisis());
        table2.getRow(2).getCell(1).setText(fra_ftir_001.getNumeroOnda());
        table2.getRow(2).getCell(3).setText(fra_ftir_001.getNumeroBarridos());
        //table2.getRow(2).getCell(5).setText(fra_ftir_001.getResolucion());

        XWPFTable table3 = doc.getTables().get(3);
        for (int i=0; i< lista.size(); i++) {
            table3.getRow(i+1).getCell(1).setText(lista.get(i).getTipoCompuesto());
            table3.getRow(i+1).getCell(3).setText(lista.get(i).getIdentidad());
        }
        table3.getRow(1).getCell(2).setText(fra_ftir_001.getTipoEspectro());

        XWPFTable table4 = doc.getTables().get(4);
        table4.getRow(0).getCell(1).setText(fra_ftir_001.getObservaciones());

        XWPFTable table5 = doc.getTables().get(5);
        table5.getRow(1).getCell(0).removeParagraph(0);
        XWPFParagraph paragraph = table5.getRow(1).getCell(0).addParagraph();
        paragraph.setAlignment(ParagraphAlignment.CENTER);
        XWPFRun run = paragraph.createRun();
        InputStream inputStream = new URL(fra_ftir_001.getRubricaRealizo()).openStream();
        XWPFPicture xwpfPicture = run.addPicture(inputStream, XWPFDocument.PICTURE_TYPE_PNG, "Name", Units.pixelToEMU(110), Units.pixelToEMU(73));
        run.addBreak();
        run.setText(fra_ftir_001.getRealizo());
        table5.getRow(1).getCell(1).setText(fra_ftir_001.getSupervisor());



        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Disposition", "inline; filename=FRA-FTIR-"+estructuraNombres.getNombre()+".docx");
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

    public int fragmentoReporte (XWPFDocument doc, XWPFDocument plantilla, int contTabla, int contador, List<FRA_FTIR_001> lista) throws XmlException, IOException {

        XWPFStyles newStyles = doc.createStyles();
        newStyles.setStyles(plantilla.getStyle());
        XWPFParagraph para = doc.createParagraph();
        para.setAlignment(ParagraphAlignment.LEFT);
        para.setStyle("Ttulo2");
        XWPFRun run = para.createRun();
        run.setText("Determinación de análisis cualitativo de polímeros por espectroscopia de infrarrojo");

        /***** INICIO DE TABLA DE MARCA DE EQUIPO *****/
        XWPFTable tabl = doc.createTable();
        tabl.removeRow(0);
        XWPFTable tableDocummento = plantilla.getTables().get(35);
        tableDocummento.getRow(1).getCell(1).setText("Espectrofotómetro infrarrojo FTIR iS20/iZ10, Thermo Scientific Nicolet iS20/iZ10");
        CTTbl cTTblTemplat = tableDocummento.getCTTbl();
        tabl = new XWPFTable((CTTbl) cTTblTemplat.copy(), doc);
        doc.setTable(contTabla, tabl);
        XWPFParagraph para0 = doc.createParagraph();
        XWPFRun run0 = para0.createRun();
        run0.addBreak();
        contTabla++;
        /***** FIN DE TABLA DE MARCA DE EQUIPO *****/

        /***** INICIO DE TABLA DE MUESTRAS *****/
        XWPFTable table = doc.createTable();
        table.removeRow(0);
        XWPFTable tableDocumment = plantilla.getTables().get(36);
        CTTbl cTTblTemplate = tableDocumment.getCTTbl();
        table = new XWPFTable((CTTbl) cTTblTemplate.copy(), doc);
        table.removeRow(2);
        for (int l =0; l< contador; l++) {
            try {
                XWPFTableRow row1 = table.createRow();
                row1.getCell(0).setText(lista.get(l).getMetodoMuestra().getSolicitudServicioClienteMuestras().getIdClienteMuestra());
                row1.getCell(1).setText(formatoFechas.formateadorFechas(lista.get(l).getFechaInicioAnalisis()) + " - " + formatoFechas.formateadorFechas(lista.get(l).getFechaFinalAnalisis()));
                row1.getCell(2).setText(lista.get(l).getTemperatura());
                row1.createCell();
                row1.getCell(3).setText(lista.get(l).getHumedadRelativa());
            } catch (NullPointerException e) {
                System.out.println("El ensayo aún no ha sido desarrollado");
                table.addRow(tableDocumment.getRow(2));
            } catch (IndexOutOfBoundsException ex){
                System.out.println("El ensayo no ha sido desarrollado");
                table.addRow(tableDocumment.getRow(2));
            }
        }
        doc.setTable(contTabla, table);
        /***** FIN DE TABLA DE MUESTRAS *****/

        XWPFParagraph para1 = doc.createParagraph();
        para1.setAlignment(ParagraphAlignment.LEFT);
        XWPFRun run1 = para1.createRun();
        run1.addBreak();
        contTabla++;

        /***** INICIO DE TÍTULO DE TABLA *****/
        XWPFParagraph para2 = doc.createParagraph();
        para2.setAlignment(ParagraphAlignment.LEFT);
        para2.setStyle("Tablas");
        XWPFRun run2 = para2.createRun();
        run2.setText("Resultados de la determinación de análisis cualitativo de polímeros por espectroscopia de infrarrojo.");
        /***** FIN DE TÍTULO DE TABLA *****/

        /***** INICIO DE TABLA DE RESULTADOS *****/
        XWPFTable table_2 = doc.createTable();
        table_2.removeRow(0);
        XWPFTable tableDocumment_2 = plantilla.getTables().get(37);
        CTTbl cTTblTemplate_2 = tableDocumment_2.getCTTbl();
        table_2 = new XWPFTable((CTTbl) cTTblTemplate_2.copy(), doc);
        table_2.removeRow(5);
        table_2.removeRow(4);
        table_2.removeRow(3);
        table_2.removeRow(2);
        for (int k = 0; k < contador; k++) {
            try {
                XWPFTableRow row = table_2.createRow();
                row.getCell(0).setText(lista.get(k).getMetodoMuestra().getSolicitudServicioClienteMuestras().getIdClienteMuestra());
                List<FRA_FTIR_001_DATA> lista2 = fra_ftir_001_data_repository.buscarTodosPorEnsayo(lista.get(k).getIdFRAFTIR());
                row.getCell(1).setText(lista2.get(0).getTipoCompuesto() + " - " + lista2.get(1).getTipoCompuesto());
            } catch (NullPointerException e) {
                table_2.addRow(tableDocumment_2.getRow(2));
            }
        }

        XWPFTable tableDocumment_4 = plantilla.getTables().get(38);
        try {
            XWPFTableRow row2 = tableDocumment_4.getRow(0);
            row2.getCell(1).setText("N/A");
            table_2.addRow(row2);

            XWPFTableRow row3 = tableDocumment_4.getRow(1);
            row3.getCell(1).setText("N/A");
            table_2.addRow(row3);

            XWPFTableRow row4 = tableDocumment_4.getRow(2);
            row4.getCell(1).setText(lista.get(0).getObservaciones());
            table_2.addRow(row4);
        } catch (NullPointerException e) {
            table_2.addRow(tableDocumment_4.getRow(2));
        }
        /***** FIN DE TABLA DE RESULTADOS *****/

        doc.setTable(contTabla, table_2);

        XWPFParagraph para3 = doc.createParagraph();
        para3.setAlignment(ParagraphAlignment.LEFT);
        XWPFRun run3 = para3.createRun();
        run3.addBreak();
        run3.setText("Nota: Espectrometría infrarroja por transformada de Fourier.");
        run3.addBreak();

        List<XWPFParagraph> listaParagraph = new ArrayList<>();
        List<XWPFRun> listaRun = new ArrayList<>();
        List<InputStream> listaFis = new ArrayList<>();
        List<XWPFPicture> listaPircures = new ArrayList<>();
        List<XWPFParagraph> listaParagraph2 = new ArrayList<>();
        List<XWPFRun> listaRun2 = new ArrayList<>();

        for (int k = 0; k < contador; k++) {
            try {
                listaParagraph.add(k, doc.createParagraph());
                listaParagraph.get(k).setAlignment(ParagraphAlignment.CENTER);
                listaRun.add(k, listaParagraph.get(k).createRun());
                try {
                    listaFis.add(k, new URL(lista.get(k).getPathImagen()).openStream());
                } catch (NullPointerException | FileNotFoundException e) {
                    System.out.println("No se encontró la imágen o el formato de la imagen es incorrecto");
                }
                try {
                    listaPircures.add(k, listaRun.get(k).addPicture(listaFis.get(k), XWPFDocument.PICTURE_TYPE_PNG, "Name", Units.pixelToEMU(430), Units.pixelToEMU(430)));
                } catch (NullPointerException | InvalidFormatException e) {
                    System.out.println("No se encontró la imágen (1)");
                } catch (IndexOutOfBoundsException e) {
                    System.out.println("Ocurrió un error de index en la lista");
                }
                listaParagraph2.add(k, doc.createParagraph());
                listaParagraph2.get(k).setAlignment(ParagraphAlignment.CENTER);
                listaParagraph2.get(k).setStyle("Termograma");
                listaRun2.add(k, listaParagraph2.get(k).createRun());
                listaRun2.get(k).setBold(true);
                listaRun2.get(k).setFontSize(8);
                listaRun2.get(k).setColor("b7b7b7");
                listaRun2.get(k).setFontFamily("Century Gothic");
                listaRun2.get(k).setText("Espectro "+ (k + 1) +". Determinación de compuestos por FTIR de la muestra \"" + lista.get(k).getMetodoMuestra().getSolicitudServicioClienteMuestras().getIdClienteMuestra() + "\".");
            } catch (NullPointerException e) {
                System.out.println("Valida el error");
            }
        }

        contTabla++;

        return contTabla;
    }
}
