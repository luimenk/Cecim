package com.demo.service.formatos.metodos;

import com.demo.model.operacion.metodos.fra09tga.FRA_TGA_001;
import com.demo.model.operacion.metodos.fra09tga.datas.FRA_TGA_001_DATA;
import com.demo.model.operacion.metodos.fra10ico.FRA_ICO_001;
import com.demo.model.operacion.metodos.fra10ico.datas.FRA_ICO_001_DATA;
import com.demo.repository.operacion.metodos.fra10ico.FRA_ICO_001_Repository;
import com.demo.repository.operacion.metodos.fra10ico.datas.FRA_ICO_001_DATA_Repository;
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
import org.springframework.data.annotation.AccessType;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigInteger;
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

    public int fragmentoReporte(XWPFDocument doc, XWPFDocument plantilla, int contTabla, int contador, List<FRA_ICO_001> lista) throws XmlException, IOException {

        XWPFStyles newStyles = doc.createStyles();
        newStyles.setStyles(plantilla.getStyle());
        XWPFParagraph para = doc.createParagraph();
        para.setAlignment(ParagraphAlignment.LEFT);
        para.setStyle("Ttulo2");
        XWPFRun run = para.createRun();
        run.setText("Determinación del índice de carbonilo por espectroscopía de infrarrojo por transformada de Fourier");

        /***** INICIO DE TABLA DE MARCA DE EQUIPO *****/
        XWPFTable tabl = doc.createTable();
        tabl.removeRow(0);
        XWPFTable tableDocummento = plantilla.getTables().get(43);
        try{
            tableDocummento.getRow(1).getCell(1).setText("N/A");
            tableDocummento.getRow(1).getCell(2).setText(lista.get(0).getTipoEnvejecimiento());
            tableDocummento.getRow(1).getCell(3).setText("En dónde se localiza esto en el formato de ensayo?");
            tableDocummento.getRow(1).getCell(4).setText("En dónde se localiza esto en el formato de ensayo?");
            tableDocummento.getRow(1).getCell(5).setText(lista.get(0).getGrupoCarbonillo());
            tableDocummento.getRow(1).getCell(6).setText(lista.get(0).getGrupoAlifatico());
        } catch (NullPointerException e) {
            System.out.println("Ocurrió un error ICO tabla 1");
        } catch (IndexOutOfBoundsException ex) {
            System.out.println("Ocurrió un error ICO tabla 1");
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
        XWPFTable tableDocumment = plantilla.getTables().get(44);
        CTTbl cTTblTemplate = tableDocumment.getCTTbl();
        table = new XWPFTable((CTTbl) cTTblTemplate.copy(), doc);
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
        run2.setText("Resultados del análisis de la determinación del índice de carbonilo por espectroscopía de infrarrojo por transformada de Fourier.");
        /***** FIN DE TÍTULO DE TABLA *****/

        /***** INICIO DE TABLA DE RESULTADOS *****/
        XWPFTable table_2 = doc.createTable();
        table_2.removeRow(0);
        XWPFTable tableDocumment_2 = plantilla.getTables().get(45);
        CTTbl cTTblTemplate_2 = tableDocumment_2.getCTTbl();
        table_2 = new XWPFTable((CTTbl) cTTblTemplate_2.copy(), doc);

        try {
            List<FRA_ICO_001_DATA> datas = fra_ico_001_data_repository.buscarTodosPorEnsayo(lista.get(0).getIdFRAICO());

            for (int i = 0; i<datas.size(); i++) {
                table_2.getRow(i+1).getCell(2).setText(datas.get(i).getPromedioCarbonillo());
                table_2.getRow(i+1).getCell(3).setText(datas.get(i).getPromedioEspesor());
            }
        } catch (NullPointerException e) {
            System.out.println("Ocurrió un error en la tabla de resultados de TGA");
        } catch (IndexOutOfBoundsException ex) {
            System.out.println("Ocurrió un error ICO tabla 1");
        }

        XWPFTable tableDocumment_4 = plantilla.getTables().get(46);
        try {
            XWPFTableRow row2 = tableDocumment_4.getRow(0);
            row2.getCell(1).setText("N/A");
            row2.getCell(1).getCTTc().getTcPr().addNewGridSpan();
            row2.getCell(1).getCTTc().getTcPr().getGridSpan().setVal(BigInteger.valueOf((long) 3));
            table_2.addRow(row2);

            XWPFTableRow row3 = tableDocumment_4.getRow(1);
            row3.getCell(1).setText("N/A");
            row3.getCell(1).getCTTc().getTcPr().addNewGridSpan();
            row3.getCell(1).getCTTc().getTcPr().getGridSpan().setVal(BigInteger.valueOf((long) 3));
            table_2.addRow(row3);

            XWPFTableRow row4 = tableDocumment_4.getRow(2);
            row4.getCell(1).setText(lista.get(0).getObservaciones());
            row4.getCell(1).getCTTc().getTcPr().addNewGridSpan();
            row4.getCell(1).getCTTc().getTcPr().getGridSpan().setVal(BigInteger.valueOf((long) 3));
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
        run3.setText("Nota: Índice o densidad óptica de carbonilos.");
        run3.addBreak();
        contTabla++;

        return contTabla;
    }
}
