package com.demo.service.formatos.metodos;

import com.demo.model.operacion.MetodoMuestra;
import com.demo.model.operacion.RecepcionVerificacionRegistroCodificacion;
import com.demo.model.operacion.metodos.fra14oit.FRA_OIT_001;
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
public class FRA_15_DSC_Print {

    @Autowired
    private FRA_DSC_Repository fra_dsc_repository;

    @Autowired
    private FRA_DSC_DATA_Repository fra_dsc_data_repository;

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

    public int fragmentoReporte(XWPFDocument doc, XWPFDocument plantilla, int contTabla, int contador, List<FRA_DSC> lista) throws XmlException, IOException {

        XWPFStyles newStyles = doc.createStyles();
        newStyles.setStyles(plantilla.getStyle());
        XWPFParagraph para = doc.createParagraph();
        para.setAlignment(ParagraphAlignment.LEFT);
        para.setStyle("Ttulo2");
        XWPFRun run = para.createRun();
        run.setText("Determinación de transiciones térmicas de polímeros");

        /***** INICIO DE TABLA DE MARCA DE EQUIPO *****/
        XWPFTable tabl = doc.createTable();
        tabl.removeRow(0);
        XWPFTable tableDocummento = plantilla.getTables().get(63);
        try {
            //tableDocummento.getRow(1).getCell(1).setText("N/A");
            tableDocummento.getRow(4).getCell(1).setText(lista.get(0).getDinamicaCal1() + " °C");
            tableDocummento.getRow(5).getCell(1).setText(lista.get(0).getTasaCalentamiento() + " °C/min");
            tableDocummento.getRow(6).getCell(1).setText(lista.get(0).getDinamicaEnf1() + " °C");
            tableDocummento.getRow(7).getCell(1).setText(lista.get(0).getTasaEnfriamiento() + " °C/min");
        } catch (NullPointerException e) {
            System.out.println("Ocurrió un error con la tabla 1 de OIT");
        }

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
        XWPFTable tableDocumment = plantilla.getTables().get(64);
        CTTbl cTTblTemplate = tableDocumment.getCTTbl();
        table = new XWPFTable((CTTbl) cTTblTemplate.copy(), doc);
        table.getRow(0).setRepeatHeader(true);
        table.removeRow(2);
        for (int l = 0; l < contador; l++) {
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
            } catch (IndexOutOfBoundsException ex) {
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
        run2.setText("Resultados de la determinación de transiciones térmicas de polímeros correspondientes al segundo calentamiento.");
        /***** FIN DE TÍTULO DE TABLA *****/

        /***** INICIO DE TABLA DE RESULTADOS *****/
        XWPFTable table_2 = doc.createTable();
        table_2.removeRow(0);
        XWPFTable tableDocumment_2 = plantilla.getTables().get(65);
        CTTbl cTTblTemplate_2 = tableDocumment_2.getCTTbl();
        table_2 = new XWPFTable((CTTbl) cTTblTemplate_2.copy(), doc);
        table_2.getRow(0).setRepeatHeader(true);
        table_2.removeRow(4);
        table_2.removeRow(3);
        table_2.removeRow(2);
        table_2.removeRow(1);
        for (int k = 0; k < contador; k++) {
            try {
                XWPFTableRow row = table_2.createRow();
                row.getCell(0).setText(lista.get(k).getMetodoMuestra().getSolicitudServicioClienteMuestras().getIdClienteMuestra());
                List<FRA_DSC_001_DATA> lista2 = fra_dsc_data_repository.buscarTodosPorEnsayo(lista.get(k).getIdFRADSC());
                row.getCell(1).setText(lista2.get(0).getTemperaturaTransicion());
                row.getCell(2).setText(lista2.get(0).getTemperaturaFusion());
                row.getCell(3).setText(lista2.get(0).getCalorFusion());
                row.getCell(4).setText(lista2.get(0).getTransicionTermica());
                row.getCell(5).setText(lista2.get(0).getTemperaturaCristalizacion());
                row.getCell(6).setText(lista2.get(0).getCalorCristalizacion());
                row.getCell(7).setText(lista2.get(0).getTransmisionTermica());
            } catch (NullPointerException | IndexOutOfBoundsException e) {
                System.out.println("Ocurrió error en row de DSC resultados");
                //table_2.addRow(tableDocumment_2.getRow(1));
            }
        }

        XWPFTable tableDocumment_4 = plantilla.getTables().get(66);
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
                listaRun2.get(k).setText("Termograma "+ (k + 1) +". Determinación de transiciones térmicas de polímeros de la muestra \"" + lista.get(k).getMetodoMuestra().getSolicitudServicioClienteMuestras().getIdClienteMuestra() + "\".");
            } catch (NullPointerException e) {
                System.out.println("Valida el error");
            }
        }

        contTabla++;

        return contTabla;
    }
}
