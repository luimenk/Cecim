package com.demo.service.operacion;

import com.demo.model.operacion.MetodoMuestra;
import com.demo.model.operacion.SolicitudServicioCliente;
import com.demo.model.operacion.SolicitudServicioClienteMuestras;
import com.demo.model.operacion.metodos.FRA_AT_001;
import com.demo.repository.operacion.MetodoMuestraRepository;
import com.demo.repository.operacion.SolicitudServicioClienteMuestrasRepository;
import com.demo.repository.operacion.SolicitudServicioClienteRepository;

import com.demo.utils.EstructuraNombres;
import com.demo.utils.WordCopyTableAfterTable;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.util.Units;
import org.apache.poi.xwpf.usermodel.*;
import org.apache.xmlbeans.XmlCursor;
import org.apache.xmlbeans.XmlException;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTbl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class SolicitudServicioClienteService {

    @Autowired
    private SolicitudServicioClienteRepository solicitudServicioClienteRepository;

    @Autowired
    private SolicitudServicioClienteMuestrasRepository solicitudServicioClienteMuestrasRepository;

    @Autowired
    private MetodoMuestraRepository metodoMuestraRepository;

    EstructuraNombres estructuraNombres = new EstructuraNombres();
    WordCopyTableAfterTable wordCopyTableAfterTable = new WordCopyTableAfterTable();

    private static final Logger LOGGER = LoggerFactory.getLogger("info");

    private static final Logger APP = LoggerFactory.getLogger("info");

    public SolicitudServicioCliente save(SolicitudServicioCliente solicitudServicioCliente) {
        return solicitudServicioClienteRepository.save(solicitudServicioCliente);
    }

    public List<SolicitudServicioCliente> findAll() {
        return solicitudServicioClienteRepository.findAll();
    }

    public SolicitudServicioCliente findById(Long id) {
        return solicitudServicioClienteRepository.findBySolicitudServicioClienteId(id);
    }

    public SolicitudServicioCliente findByIdFolio(String folio) {
        return solicitudServicioClienteRepository.findByFolioSolitudServicioCliente(folio);
    }

    public void delete(Long id) {
        solicitudServicioClienteRepository.deleteById(id);
    }

    public long contar() {
        return solicitudServicioClienteRepository.count();
    }

    public ResponseEntity<InputStreamResource> crearFormato(Long id) throws InvalidFormatException, IOException, XmlException {
        ClassPathResource resource2 = new ClassPathResource("/documentos/FIR-ERAI-002.docx");
        ClassPathResource resource = new ClassPathResource("/documentos/FIR-ERAI-003.docx");
        ClassPathResource resource3 = new ClassPathResource("/documentos/test.docx");
        XWPFDocument plantilla = new XWPFDocument(resource2.getInputStream());
        XWPFDocument doc = new XWPFDocument(resource.getInputStream());
        XWPFDocument test = new XWPFDocument(resource3.getInputStream());

        SolicitudServicioCliente solicitudServicioCliente = solicitudServicioClienteRepository.findBySolicitudServicioClienteId(id);
        List<SolicitudServicioClienteMuestras> solicitudServicioClienteMuestrasLista = solicitudServicioClienteMuestrasRepository.findAllBySolicitudServicioCliente_SolicitudServicioClienteId(id);
        List<MetodoMuestra> metodoMuestraList = metodoMuestraRepository.findAllBySolicitudServicioClienteMuestras_SolicitudServicioCliente(solicitudServicioCliente);

        List<String> sinRepetir = metodoMuestraList.stream()
                .map(item->item.getMethod().getCodigoMetodo())
                .distinct()
                .collect(Collectors.toList());

        Collections.sort(metodoMuestraList, new Comparator<MetodoMuestra>() {
            public int compare(MetodoMuestra metodoMuestra1, MetodoMuestra metodoMuestra2){
                return metodoMuestra1.getMethod().getCodigoMetodo().compareTo(metodoMuestra2.getMethod().getCodigoMetodo());
            }
        });

//        System.out.println("*** Se imprimen Ordenados ***");
//        for (MetodoMuestra temp: metodoMuestraList){
//            System.out.println(temp.getMethod().getCodigoMetodo());
//        }
//
//        System.out.println("*** Se imprimen SIN REPETIR");
//        sinRepetir.forEach(System.out::println);
//
//        System.out.println("termina la prueba");


        //metodoMuestraList = metodoMuestraList.stream().distinct().collect(Collectors.toList());
        //metodoMuestraList = metodoMuestraList.stream().distinct().collect(Collectors.toList());
        //metodoMuestraList.forEach(System.out::println);

        List<String> contactosAux;
        int bandera = 0;

        XWPFTable table1 = doc.getTables().get(1);
        table1.getRow(0).getCell(1).setText(solicitudServicioCliente.getClient().getNombreRazonSocial());
        String direccion = solicitudServicioCliente.getClient().getCalle() + " " +
                solicitudServicioCliente.getClient().getNumero() + " " +
                solicitudServicioCliente.getClient().getColonia() + " " +
                solicitudServicioCliente.getClient().getMunicipio() + " " +
                solicitudServicioCliente.getClient().getEstado() + " " +
                solicitudServicioCliente.getClient().getCodigoPostal();
        table1.getRow(1).getCell(1).setText(direccion);
        try {
            JSONArray jsonArray = new JSONArray(solicitudServicioCliente.getClient().getContactosDatos());
            table1.getRow(2).getCell(1).setText(getAttributeContacto(bandera, jsonArray, "nombrePersonaContacto"));
            String telefonos = "Teléfono Oficina: " + getAttributeContacto(bandera, jsonArray, "telefonoOficina") + ", Celular: " + getAttributeContacto(bandera, jsonArray, "telefonoCelular");
            table1.getRow(3).getCell(1).setText(telefonos);
            table1.getRow(4).getCell(1).setText(getAttributeContacto(bandera, jsonArray, "email"));

            table1.getRow(5).getCell(1).setText("N/A");
            table1.getRow(5).getCell(3).setText("N/A");

        } catch (JSONException e) {
            System.out.println("e: " + e);
        }

        XWPFTable table2 = doc.getTables().get(2);
        for (int i = 0; i < solicitudServicioClienteMuestrasLista.size(); i++) {
            XWPFTableRow tableRow = table2.createRow();
            tableRow.getCell(0).setText((i + 1) + "");
            tableRow.getCell(1).setText("Falta relacionar");
            tableRow.getCell(2).setText(solicitudServicioClienteMuestrasLista.get(i).getTipoMuestra());
            tableRow.getCell(3).setText(solicitudServicioClienteMuestrasLista.get(i).getIdClienteMuestra());
            tableRow.getCell(4).setText(solicitudServicioClienteMuestrasLista.get(i).getDescripcionMuestra());
            tableRow.getCell(5).setText(solicitudServicioClienteMuestrasLista.get(i).getLote());
        }

        XWPFTable table3 = doc.getTables().get(3);
        for (int i = 0; i < metodoMuestraList.size(); i++) {
            XWPFTableRow tableRow = table3.createRow();
            tableRow.getCell(0).setText(metodoMuestraList.get(i).getMethod().getNombreMetodo());
            tableRow.getCell(1).setText(metodoMuestraList.get(i).getMethod().getCodigoMetodo());
            tableRow.getCell(2).setText(metodoMuestraList.get(i).getMethod().getNormaReferencia());
        }

        int bandAT=0, bandDI=0, bandES=0, bandGR=0, bandHUM=0, bandNCP=0, bandPPG=0, bandFTIR=0, bandTGA=0, bandICI=0;
        int bandEAT=0, bandEAUV=0, bandAEXE=0, bandOIT=0, bandDSC=0, bandCST=0, bandIF=0, bandPO=0, bandPRR=0;
        int bandRTER=0, bandRCD=0, bandRI=0;
        int contTabla = 4;
        int contador = 0;
//Tablas
//Grfica
        for (int i = 0; i<metodoMuestraList.size(); i++) {
            if (metodoMuestraList.get(i).getMethod().getCodigoMetodo().equals("MET-AT-001") && bandAT == 0){
                bandAT++;
                for (int j = 0; j<metodoMuestraList.size(); j++){
                    if (metodoMuestraList.get(j).getMethod().getCodigoMetodo().equals("MET-AT-001")){
                        contador++;
                    }
                }

                XWPFStyles newStyles = doc.createStyles();
                newStyles.setStyles(plantilla.getStyle());
                XWPFParagraph para = doc.createParagraph();
                para.setStyle("Ttulo2");
                XWPFRun run = para.createRun();
                run.setText("Determinación de adhesión de tintas");

                XWPFTable table = doc.createTable();
                table.removeRow(0); // El default row no es necesario
                XWPFTable tableDocumment = plantilla.getTables().get(4);
                CTTbl cTTblTemplate=tableDocumment.getCTTbl();
                table = new XWPFTable((CTTbl)cTTblTemplate.copy(), doc);
                doc.setTable(contTabla, table);
                XWPFParagraph para1 = doc.createParagraph();
                XWPFRun run1 = para1.createRun();
                run1.addBreak();
                contTabla++;

                XWPFParagraph para2 = doc.createParagraph();
                para2.setStyle("Tablas");
                XWPFRun run2 = para2.createRun();
                run2.setText("Resultados de la determinación de adhesión de tintas");

                XWPFTable table_2 = doc.createTable();
                table_2.removeRow(0); // El default row no es necesario
                XWPFTable tableDocumment_2 = plantilla.getTables().get(5);
                CTTbl cTTblTemplate_2=tableDocumment_2.getCTTbl();
                table_2 = new XWPFTable((CTTbl)cTTblTemplate_2.copy(), doc);
                table_2.removeRow(4);
                table_2.removeRow(3);
                table_2.removeRow(2);
                table_2.removeRow(1);
                for (int k=0; k<contador; k++){
                    table_2.addRow(tableDocumment_2.getRow(1));
                }
                table_2.addRow(tableDocumment_2.getRow(2));
                table_2.addRow(tableDocumment_2.getRow(3));
                table_2.addRow(tableDocumment_2.getRow(4));

                doc.setTable(contTabla, table_2);

                XWPFParagraph para3 = doc.createParagraph();
                XWPFRun run3 = para3.createRun();
                run3.addBreak();
                contTabla++;
                contador=0;
            }

            if (metodoMuestraList.get(i).getMethod().getCodigoMetodo().equals("MET-DI-002") && bandDI == 0){
                bandDI++;
                for (int j = 0; j<metodoMuestraList.size(); j++){
                    if (metodoMuestraList.get(j).getMethod().getCodigoMetodo().equals("MET-DI-002")){
                        contador++;
                    }
                }

                XWPFStyles newStyles = doc.createStyles();
                newStyles.setStyles(plantilla.getStyle());
                XWPFParagraph para = doc.createParagraph();
                para.setStyle("Ttulo2");
                XWPFRun run = para.createRun();
                run.setText("Determinación de dimensiones");

                XWPFTable table = doc.createTable();
                table.removeRow(0); // El default row no es necesario
                XWPFTable tableDocumment = plantilla.getTables().get(6);
                CTTbl cTTblTemplate=tableDocumment.getCTTbl();
                table = new XWPFTable((CTTbl)cTTblTemplate.copy(), doc);
                doc.setTable(contTabla, table);
                XWPFParagraph para1 = doc.createParagraph();
                XWPFRun run1 = para1.createRun();
                run1.addBreak();
                contTabla++;

                XWPFParagraph para2 = doc.createParagraph();
                para2.setStyle("Tablas");
                XWPFRun run2 = para2.createRun();
                run2.setText("Resultados de la determinación de dimensiones");

                XWPFTable table_2 = doc.createTable();
                table_2.removeRow(0); // El default row no es necesario
                XWPFTable tableDocumment_2 = plantilla.getTables().get(7);
                CTTbl cTTblTemplate_2=tableDocumment_2.getCTTbl();
                table_2 = new XWPFTable((CTTbl)cTTblTemplate_2.copy(), doc);
                table_2.removeRow(5);
                table_2.removeRow(4);
                table_2.removeRow(3);
                table_2.removeRow(2);
                for (int k=0; k<contador; k++){
                    table_2.addRow(tableDocumment_2.getRow(2));
                }
                table_2.addRow(tableDocumment_2.getRow(3));
                table_2.addRow(tableDocumment_2.getRow(4));
                table_2.addRow(tableDocumment_2.getRow(5));

                doc.setTable(contTabla, table_2);

                XWPFParagraph para3 = doc.createParagraph();
                XWPFRun run3 = para3.createRun();
                run3.addBreak();
                contTabla++;
                contador=0;
            }

            if (metodoMuestraList.get(i).getMethod().getCodigoMetodo().equals("MET-ES-003") && bandES == 0){
                bandES++;
                for (int j = 0; j<metodoMuestraList.size(); j++){
                    if (metodoMuestraList.get(j).getMethod().getCodigoMetodo().equals("MET-ES-003")){
                        contador++;
                    }
                }

                XWPFStyles newStyles = doc.createStyles();
                newStyles.setStyles(plantilla.getStyle());
                XWPFParagraph para = doc.createParagraph();
                para.setStyle("Ttulo2");
                XWPFRun run = para.createRun();
                run.setText("Determinación del espesor");

                XWPFTable table = doc.createTable();
                table.removeRow(0); // El default row no es necesario
                XWPFTable tableDocumment = plantilla.getTables().get(8);
                CTTbl cTTblTemplate=tableDocumment.getCTTbl();
                table = new XWPFTable((CTTbl)cTTblTemplate.copy(), doc);
                doc.setTable(contTabla, table);
                XWPFParagraph para1 = doc.createParagraph();
                XWPFRun run1 = para1.createRun();
                run1.addBreak();
                contTabla++;

                XWPFTable table_2 = doc.createTable();
                table_2.removeRow(0); // El default row no es necesario
                XWPFTable tableDocumment_2 = plantilla.getTables().get(9);
                CTTbl cTTblTemplate_2=tableDocumment_2.getCTTbl();
                table_2 = new XWPFTable((CTTbl)cTTblTemplate_2.copy(), doc);
                doc.setTable(contTabla, table_2);
                XWPFParagraph para2 = doc.createParagraph();
                XWPFRun run2 = para2.createRun();
                run2.addBreak();
                contTabla++;

                XWPFParagraph para3 = doc.createParagraph();
                para3.setStyle("Tablas");
                XWPFRun run3 = para3.createRun();
                run3.setText("Resultados de la determinación de espesor");

                XWPFTable table_3 = doc.createTable();
                table_3.removeRow(0); // El default row no es necesario
                XWPFTable tableDocumment_3 = plantilla.getTables().get(10);
                CTTbl cTTblTemplate_3=tableDocumment_3.getCTTbl();
                table_3 = new XWPFTable((CTTbl)cTTblTemplate_3.copy(), doc);
                table_3.removeRow(10);
                table_3.removeRow(9);
                table_3.removeRow(8);
                table_3.removeRow(7);
                table_3.removeRow(6);
                table_3.removeRow(5);
                table_3.removeRow(4);
                table_3.removeRow(3);
                table_3.removeRow(2);
                for (int k=0; k<contador; k++){
                    table_3.addRow(tableDocumment_3.getRow(2));
                }
                table_3.addRow(tableDocumment_3.getRow(3));
                table_3.addRow(tableDocumment_3.getRow(4));
                for (int k=0; k<contador; k++){
                    table_3.addRow(tableDocumment_3.getRow(5));
                }
                table_3.addRow(tableDocumment_3.getRow(6));
                table_3.addRow(tableDocumment_3.getRow(7));
                table_3.addRow(tableDocumment_3.getRow(8));
                table_3.addRow(tableDocumment_3.getRow(9));
                table_3.addRow(tableDocumment_3.getRow(10));

                doc.setTable(contTabla, table_3);

                XWPFParagraph para4 = doc.createParagraph();
                XWPFRun run4 = para4.createRun();
                run4.addBreak();
                contTabla++;
                contador = 0;

                //TODO: SECCIÓN DE IMAGEN MET-ES-003
            }

            if (metodoMuestraList.get(i).getMethod().getCodigoMetodo().equals("MET-GR-004") && bandGR == 0){
                bandGR++;
                for (int j = 0; j<metodoMuestraList.size(); j++){
                    if (metodoMuestraList.get(j).getMethod().getCodigoMetodo().equals("MET-GR-004")){
                        contador++;
                    }
                }

                XWPFStyles newStyles = doc.createStyles();
                newStyles.setStyles(plantilla.getStyle());
                XWPFParagraph para = doc.createParagraph();
                para.setStyle("Ttulo2");
                XWPFRun run = para.createRun();
                run.setText("Determinación de gramaje");

                XWPFTable table = doc.createTable();
                table.removeRow(0); // El default row no es necesario
                XWPFTable tableDocumment = plantilla.getTables().get(11);
                CTTbl cTTblTemplate=tableDocumment.getCTTbl();
                table = new XWPFTable((CTTbl)cTTblTemplate.copy(), doc);
                doc.setTable(contTabla, table);
                XWPFParagraph para1 = doc.createParagraph();
                XWPFRun run1 = para1.createRun();
                run1.addBreak();
                contTabla++;

                XWPFParagraph para2 = doc.createParagraph();
                para2.setStyle("Tablas");
                XWPFRun run2 = para2.createRun();
                run2.setText("Resultados de la determinación de gramaje");

                XWPFTable table_2 = doc.createTable();
                table_2.removeRow(0); // El default row no es necesario
                XWPFTable tableDocumment_2 = plantilla.getTables().get(12);
                CTTbl cTTblTemplate_2=tableDocumment_2.getCTTbl();
                table_2 = new XWPFTable((CTTbl)cTTblTemplate_2.copy(), doc);
                table_2.removeRow(4);
                table_2.removeRow(3);
                table_2.removeRow(2);
                table_2.removeRow(1);
                for (int k=0; k<contador; k++){
                    table_2.addRow(tableDocumment_2.getRow(1));
                }
                table_2.addRow(tableDocumment_2.getRow(2));
                table_2.addRow(tableDocumment_2.getRow(3));
                table_2.addRow(tableDocumment_2.getRow(4));

                doc.setTable(contTabla, table_2);

                XWPFParagraph para3 = doc.createParagraph();
                XWPFRun run3 = para3.createRun();
                run3.addBreak();
                contTabla++;
                contador=0;
            }

            if (metodoMuestraList.get(i).getMethod().getCodigoMetodo().equals("MET-HUM-005") && bandHUM == 0){
                bandHUM++;
                for (int j = 0; j<metodoMuestraList.size(); j++){
                    if (metodoMuestraList.get(j).getMethod().getCodigoMetodo().equals("MET-HUM-005")){
                        contador++;
                    }
                }

                XWPFStyles newStyles = doc.createStyles();
                newStyles.setStyles(plantilla.getStyle());
                XWPFParagraph para = doc.createParagraph();
                para.setStyle("Ttulo2");
                XWPFRun run = para.createRun();
                run.setText("Determinación humedad");

                XWPFTable table = doc.createTable();
                table.removeRow(0); // El default row no es necesario
                XWPFTable tableDocumment = plantilla.getTables().get(13);
                CTTbl cTTblTemplate=tableDocumment.getCTTbl();
                table = new XWPFTable((CTTbl)cTTblTemplate.copy(), doc);
                doc.setTable(contTabla, table);
                XWPFParagraph para1 = doc.createParagraph();
                XWPFRun run1 = para1.createRun();
                run1.addBreak();
                contTabla++;

                XWPFParagraph para2 = doc.createParagraph();
                para2.setStyle("Tablas");
                XWPFRun run2 = para2.createRun();
                run2.setText("Resultados de la determinación de humedad");

                XWPFTable table_2 = doc.createTable();
                table_2.removeRow(0); // El default row no es necesario
                XWPFTable tableDocumment_2 = plantilla.getTables().get(14);
                CTTbl cTTblTemplate_2=tableDocumment_2.getCTTbl();
                table_2 = new XWPFTable((CTTbl)cTTblTemplate_2.copy(), doc);
                table_2.removeRow(4);
                table_2.removeRow(3);
                table_2.removeRow(2);
                table_2.removeRow(1);
                for (int k=0; k<contador; k++){
                    table_2.addRow(tableDocumment_2.getRow(1));
                }
                table_2.addRow(tableDocumment_2.getRow(2));
                table_2.addRow(tableDocumment_2.getRow(3));
                table_2.addRow(tableDocumment_2.getRow(4));

                doc.setTable(contTabla, table_2);

                XWPFParagraph para3 = doc.createParagraph();
                XWPFRun run3 = para3.createRun();
                run3.addBreak();
                contTabla++;
                contador=0;
            }

            if (metodoMuestraList.get(i).getMethod().getCodigoMetodo().equals("MET-NCP-006") && bandNCP == 0){
                bandNCP++;
                for (int j = 0; j<metodoMuestraList.size(); j++){
                    if (metodoMuestraList.get(j).getMethod().getCodigoMetodo().equals("MET-NCP-006")){
                        contador++;
                    }
                }

                XWPFStyles newStyles = doc.createStyles();
                newStyles.setStyles(plantilla.getStyle());
                XWPFParagraph para = doc.createParagraph();
                para.setStyle("Ttulo2");
                XWPFRun run = para.createRun();
                run.setText("Determinación del número de capas en películas");

                XWPFTable table = doc.createTable();
                table.removeRow(0); // El default row no es necesario
                XWPFTable tableDocumment = plantilla.getTables().get(15);
                CTTbl cTTblTemplate=tableDocumment.getCTTbl();
                table = new XWPFTable((CTTbl)cTTblTemplate.copy(), doc);
                doc.setTable(contTabla, table);
                XWPFParagraph para1 = doc.createParagraph();
                XWPFRun run1 = para1.createRun();
                run1.addBreak();
                contTabla++;

                XWPFParagraph para2 = doc.createParagraph();
                para2.setStyle("Tablas");
                XWPFRun run2 = para2.createRun();
                run2.setText("Resultados de la determinación de número de capas");

                XWPFTable table_2 = doc.createTable();
                table_2.removeRow(0); // El default row no es necesario
                XWPFTable tableDocumment_2 = plantilla.getTables().get(16);
                CTTbl cTTblTemplate_2=tableDocumment_2.getCTTbl();
                table_2 = new XWPFTable((CTTbl)cTTblTemplate_2.copy(), doc);
                table_2.removeRow(4);
                table_2.removeRow(3);
                table_2.removeRow(2);
                table_2.removeRow(1);
                for (int k=0; k<contador; k++){
                    table_2.addRow(tableDocumment_2.getRow(1));
                }
                table_2.addRow(tableDocumment_2.getRow(2));
                table_2.addRow(tableDocumment_2.getRow(3));
                table_2.addRow(tableDocumment_2.getRow(4));

                doc.setTable(contTabla, table_2);

                XWPFParagraph para3 = doc.createParagraph();
                XWPFRun run3 = para3.createRun();
                run3.addBreak();
                contTabla++;
                contador=0;

                //TODO: APARTADO DE IMAGEN
            }

            if (metodoMuestraList.get(i).getMethod().getCodigoMetodo().equals("MET-PPG-007") && bandPPG == 0){
                bandPPG++;
                for (int j = 0; j<metodoMuestraList.size(); j++){
                    if (metodoMuestraList.get(j).getMethod().getCodigoMetodo().equals("MET-PPG-007")){
                        contador++;
                    }
                }

                XWPFStyles newStyles = doc.createStyles();
                newStyles.setStyles(plantilla.getStyle());
                XWPFParagraph para = doc.createParagraph();
                para.setStyle("Ttulo2");
                XWPFRun run = para.createRun();
                run.setText("Determinación de pellets por gramo");

                XWPFTable table = doc.createTable();
                table.removeRow(0); // El default row no es necesario
                XWPFTable tableDocumment = plantilla.getTables().get(17);
                CTTbl cTTblTemplate=tableDocumment.getCTTbl();
                table = new XWPFTable((CTTbl)cTTblTemplate.copy(), doc);
                doc.setTable(contTabla, table);
                XWPFParagraph para1 = doc.createParagraph();
                XWPFRun run1 = para1.createRun();
                run1.addBreak();
                contTabla++;

                XWPFParagraph para2 = doc.createParagraph();
                para2.setStyle("Tablas");
                XWPFRun run2 = para2.createRun();
                run2.setText("Resultados de la determinación de pellets por gramo");

                XWPFTable table_2 = doc.createTable();
                table_2.removeRow(0); // El default row no es necesario
                XWPFTable tableDocumment_2 = plantilla.getTables().get(18);
                CTTbl cTTblTemplate_2=tableDocumment_2.getCTTbl();
                table_2 = new XWPFTable((CTTbl)cTTblTemplate_2.copy(), doc);
                table_2.removeRow(4);
                table_2.removeRow(3);
                table_2.removeRow(2);
                table_2.removeRow(1);
                for (int k=0; k<contador; k++){
                    table_2.addRow(tableDocumment_2.getRow(1));
                }
                table_2.addRow(tableDocumment_2.getRow(2));
                table_2.addRow(tableDocumment_2.getRow(3));
                table_2.addRow(tableDocumment_2.getRow(4));

                doc.setTable(contTabla, table_2);

                XWPFParagraph para3 = doc.createParagraph();
                XWPFRun run3 = para3.createRun();
                run3.addBreak();
                contTabla++;
                contador=0;
            }

            if (metodoMuestraList.get(i).getMethod().getCodigoMetodo().equals("MET-FTIR-008") && bandFTIR == 0){
                bandFTIR++;
                for (int j = 0; j<metodoMuestraList.size(); j++){
                    if (metodoMuestraList.get(j).getMethod().getCodigoMetodo().equals("MET-FTIR-008")){
                        contador++;
                    }
                }

                XWPFStyles newStyles = doc.createStyles();
                newStyles.setStyles(plantilla.getStyle());
                XWPFParagraph para = doc.createParagraph();
                para.setStyle("Ttulo2");
                XWPFRun run = para.createRun();
                run.setText("Determinación de compuestos por espectrometría infrarroja por transformada de Fourier FTIR");

                XWPFTable table = doc.createTable();
                table.removeRow(0); // El default row no es necesario
                XWPFTable tableDocumment = plantilla.getTables().get(19);
                CTTbl cTTblTemplate=tableDocumment.getCTTbl();
                table = new XWPFTable((CTTbl)cTTblTemplate.copy(), doc);
                doc.setTable(contTabla, table);
                XWPFParagraph para1 = doc.createParagraph();
                XWPFRun run1 = para1.createRun();
                run1.addBreak();
                contTabla++;

                XWPFParagraph para2 = doc.createParagraph();
                para2.setStyle("Tablas");
                XWPFRun run2 = para2.createRun();
                run2.setText("Resultados de la determinación de compuestos por espectrometría infrarroja por transformada de Fourier FTIR.");

                XWPFTable table_2 = doc.createTable();
                table_2.removeRow(0); // El default row no es necesario
                XWPFTable tableDocumment_2 = plantilla.getTables().get(20);
                CTTbl cTTblTemplate_2=tableDocumment_2.getCTTbl();
                table_2 = new XWPFTable((CTTbl)cTTblTemplate_2.copy(), doc);
                table_2.removeRow(4);
                table_2.removeRow(3);
                table_2.removeRow(2);
                table_2.removeRow(1);
                for (int k=0; k<contador; k++){
                    table_2.addRow(tableDocumment_2.getRow(1));
                }
                table_2.addRow(tableDocumment_2.getRow(2));
                table_2.addRow(tableDocumment_2.getRow(3));
                table_2.addRow(tableDocumment_2.getRow(4));

                doc.setTable(contTabla, table_2);

                XWPFParagraph para3 = doc.createParagraph();
                XWPFRun run3 = para3.createRun();
                run3.addBreak();
                run3.setText("Nota: Espectrometría infrarroja por transformada de Fourier.");
                run3.addBreak();
                contTabla++;
                contador=0;

                //TODO: APARTADO DE IMÁGEN
            }

            if (metodoMuestraList.get(i).getMethod().getCodigoMetodo().equals("MET-TGA-009") && bandTGA == 0){
                bandTGA++;
                for (int j = 0; j<metodoMuestraList.size(); j++){
                    if (metodoMuestraList.get(j).getMethod().getCodigoMetodo().equals("MET-TGA-009")){
                        contador++;
                    }
                }

                XWPFStyles newStyles = doc.createStyles();
                newStyles.setStyles(plantilla.getStyle());
                XWPFParagraph para = doc.createParagraph();
                para.setStyle("Ttulo2");
                XWPFRun run = para.createRun();
                run.setText("Determinación de compuestos por termogravimetría");

                XWPFTable table = doc.createTable();
                table.removeRow(0); // El default row no es necesario
                XWPFTable tableDocumment = plantilla.getTables().get(21);
                CTTbl cTTblTemplate=tableDocumment.getCTTbl();
                table = new XWPFTable((CTTbl)cTTblTemplate.copy(), doc);
                doc.setTable(contTabla, table);
                XWPFParagraph para1 = doc.createParagraph();
                XWPFRun run1 = para1.createRun();
                run1.addBreak();
                contTabla++;

                XWPFTable table_2 = doc.createTable();
                table_2.removeRow(0); // El default row no es necesario
                XWPFTable tableDocumment_2 = plantilla.getTables().get(22);
                CTTbl cTTblTemplate_2=tableDocumment_2.getCTTbl();
                table_2 = new XWPFTable((CTTbl)cTTblTemplate_2.copy(), doc);
                doc.setTable(contTabla, table_2);
                XWPFParagraph para2 = doc.createParagraph();
                XWPFRun run2 = para2.createRun();
                run2.addBreak();
                contTabla++;

                XWPFParagraph para3 = doc.createParagraph();
                para3.setStyle("Tablas");
                XWPFRun run3 = para3.createRun();
                run3.setText("Resultados de la determinación de compuestos por termogravimetría");

                XWPFTable table_3 = doc.createTable();
                table_3.removeRow(0); // El default row no es necesario
                XWPFTable tableDocumment_3 = plantilla.getTables().get(23);
                CTTbl cTTblTemplate_3=tableDocumment_3.getCTTbl();
                table_3 = new XWPFTable((CTTbl)cTTblTemplate_3.copy(), doc);
                table_3.removeRow(4);
                table_3.removeRow(3);
                table_3.removeRow(2);
                table_3.removeRow(1);
                for (int k=0; k<contador; k++){
                    table_3.addRow(tableDocumment_3.getRow(1));
                }
                table_3.addRow(tableDocumment_3.getRow(2));
                table_3.addRow(tableDocumment_3.getRow(3));
                table_3.addRow(tableDocumment_3.getRow(4));

                doc.setTable(contTabla, table_3);

                XWPFParagraph para4 = doc.createParagraph();
                XWPFRun run4 = para4.createRun();
                run4.addBreak();
                contTabla++;
                contador=0;

                //TODO: SECCIÓN DE IMÁGEN
            }

            if (metodoMuestraList.get(i).getMethod().getCodigoMetodo().equals("MET-ICI-010") && bandICI == 0){
                bandICI++;
                for (int j = 0; j<metodoMuestraList.size(); j++){
                    if (metodoMuestraList.get(j).getMethod().getCodigoMetodo().equals("MET-ICI-010")){
                        contador++;
                    }
                }

                XWPFStyles newStyles = doc.createStyles();
                newStyles.setStyles(plantilla.getStyle());
                XWPFParagraph para = doc.createParagraph();
                para.setStyle("Ttulo2");
                XWPFRun run = para.createRun();
                run.setText("Determinación del índice y densidad óptica de carbonilo por envejecimiento acelerado y espectroscopia");

                XWPFTable table = doc.createTable();
                table.removeRow(0); // El default row no es necesario
                XWPFTable tableDocumment = plantilla.getTables().get(24);
                CTTbl cTTblTemplate=tableDocumment.getCTTbl();
                table = new XWPFTable((CTTbl)cTTblTemplate.copy(), doc);
                doc.setTable(contTabla, table);
                XWPFParagraph para1 = doc.createParagraph();
                XWPFRun run1 = para1.createRun();
                run1.addBreak();
                contTabla++;

                XWPFTable table_2 = doc.createTable();
                table_2.removeRow(0); // El default row no es necesario
                XWPFTable tableDocumment_2 = plantilla.getTables().get(25);
                CTTbl cTTblTemplate_2=tableDocumment_2.getCTTbl();
                table_2 = new XWPFTable((CTTbl)cTTblTemplate_2.copy(), doc);
                doc.setTable(contTabla, table_2);
                XWPFParagraph para2 = doc.createParagraph();
                XWPFRun run2 = para2.createRun();
                run2.addBreak();
                contTabla++;

                XWPFParagraph para3 = doc.createParagraph();
                para3.setStyle("Tablas");
                XWPFRun run3 = para3.createRun();
                run3.setText("Resultados de la determinación del índice y densidad óptica de carbonilo por envejecimiento y espectroscopia.");

                XWPFTable table_3 = doc.createTable();
                table_3.removeRow(0); // El default row no es necesario
                XWPFTable tableDocumment_3 = plantilla.getTables().get(26);
                CTTbl cTTblTemplate_3=tableDocumment_3.getCTTbl();
                table_3 = new XWPFTable((CTTbl)cTTblTemplate_3.copy(), doc);
                table_3.removeRow(4);
                table_3.removeRow(3);
                table_3.removeRow(2);
                table_3.removeRow(1);
                for (int k=0; k<contador; k++){
                    table_3.addRow(tableDocumment_3.getRow(1));
                }
                table_3.addRow(tableDocumment_3.getRow(2));
                table_3.addRow(tableDocumment_3.getRow(3));
                table_3.addRow(tableDocumment_3.getRow(4));

                doc.setTable(contTabla, table_3);

                XWPFParagraph para4 = doc.createParagraph();
                XWPFRun run4 = para4.createRun();
                run4.addBreak();
                run4.setText("Nota: Índice o densidad óptica de carbonilos.");
                run4.addBreak();
                contTabla++;
                contador=0;
            }

            if (metodoMuestraList.get(i).getMethod().getCodigoMetodo().equals("MET-EAT-011") && bandEAT == 0){
                bandEAT++;
                for (int j = 0; j<metodoMuestraList.size(); j++){
                    if (metodoMuestraList.get(j).getMethod().getCodigoMetodo().equals("MET-EAT-011")){
                        contador++;
                    }
                }

                XWPFStyles newStyles = doc.createStyles();
                newStyles.setStyles(plantilla.getStyle());
                XWPFParagraph para = doc.createParagraph();
                para.setStyle("Ttulo2");
                XWPFRun run = para.createRun();
                run.setText("Envejecimiento acelerado térmico");

                XWPFTable table = doc.createTable();
                table.removeRow(0); // El default row no es necesario
                XWPFTable tableDocumment = plantilla.getTables().get(27);
                CTTbl cTTblTemplate=tableDocumment.getCTTbl();
                table = new XWPFTable((CTTbl)cTTblTemplate.copy(), doc);
                doc.setTable(contTabla, table);
                XWPFParagraph para1 = doc.createParagraph();
                XWPFRun run1 = para1.createRun();
                run1.addBreak();
                contTabla++;

                XWPFTable table_2 = doc.createTable();
                table_2.removeRow(0); // El default row no es necesario
                XWPFTable tableDocumment_2 = plantilla.getTables().get(28);
                CTTbl cTTblTemplate_2=tableDocumment_2.getCTTbl();
                table_2 = new XWPFTable((CTTbl)cTTblTemplate_2.copy(), doc);
                doc.setTable(contTabla, table_2);
                XWPFParagraph para2 = doc.createParagraph();
                XWPFRun run2 = para2.createRun();
                run2.addBreak();
                contTabla++;

                XWPFParagraph para3 = doc.createParagraph();
                para3.setStyle("Tablas");
                XWPFRun run3 = para3.createRun();
                run3.setText("Resultados del envejecimiento acelerado térmico");

                XWPFTable table_3 = doc.createTable();
                table_3.removeRow(0); // El default row no es necesario
                XWPFTable tableDocumment_3 = plantilla.getTables().get(29);
                CTTbl cTTblTemplate_3=tableDocumment_3.getCTTbl();
                table_3 = new XWPFTable((CTTbl)cTTblTemplate_3.copy(), doc);
                table_3.removeRow(4);
                table_3.removeRow(3);
                table_3.removeRow(2);
                table_3.removeRow(1);
                for (int k=0; k<contador; k++){
                    table_3.addRow(tableDocumment_3.getRow(1));
                }
                table_3.addRow(tableDocumment_3.getRow(2));
                table_3.addRow(tableDocumment_3.getRow(3));
                table_3.addRow(tableDocumment_3.getRow(4));

                doc.setTable(contTabla, table_3);

                XWPFParagraph para4 = doc.createParagraph();
                XWPFRun run4 = para4.createRun();
                run4.addBreak();
                contTabla++;
                contador=0;

                //TODO: APARTADO PARA IMAGEN
            }

            if (metodoMuestraList.get(i).getMethod().getCodigoMetodo().equals("MET-EAUV-012") && bandEAUV == 0){
                bandEAUV++;
                for (int j = 0; j<metodoMuestraList.size(); j++){
                    if (metodoMuestraList.get(j).getMethod().getCodigoMetodo().equals("MET-EAUV-012")){
                        contador++;
                    }
                }

                XWPFStyles newStyles = doc.createStyles();
                newStyles.setStyles(plantilla.getStyle());
                XWPFParagraph para = doc.createParagraph();
                para.setStyle("Ttulo2");
                XWPFRun run = para.createRun();
                run.setText("Envejecimiento acelerado por UV");

                XWPFTable table = doc.createTable();
                table.removeRow(0); // El default row no es necesario
                XWPFTable tableDocumment = plantilla.getTables().get(30);
                CTTbl cTTblTemplate=tableDocumment.getCTTbl();
                table = new XWPFTable((CTTbl)cTTblTemplate.copy(), doc);
                doc.setTable(contTabla, table);
                XWPFParagraph para1 = doc.createParagraph();
                XWPFRun run1 = para1.createRun();
                run1.addBreak();
                bandEAUV++;
                contTabla++;

                XWPFTable table_2 = doc.createTable();
                table_2.removeRow(0); // El default row no es necesario
                XWPFTable tableDocumment_2 = plantilla.getTables().get(31);
                CTTbl cTTblTemplate_2=tableDocumment_2.getCTTbl();
                table_2 = new XWPFTable((CTTbl)cTTblTemplate_2.copy(), doc);
                doc.setTable(contTabla, table_2);
                XWPFParagraph para2 = doc.createParagraph();
                XWPFRun run2 = para2.createRun();
                run2.addBreak();
                contTabla++;

                XWPFParagraph para3 = doc.createParagraph();
                para3.setStyle("Tablas");
                XWPFRun run3 = para3.createRun();
                run3.setText("Resultados del envejecimiento acelerado por UV");

                XWPFTable table_3 = doc.createTable();
                table_3.removeRow(0); // El default row no es necesario
                XWPFTable tableDocumment_3 = plantilla.getTables().get(32);
                CTTbl cTTblTemplate_3=tableDocumment_3.getCTTbl();
                table_3 = new XWPFTable((CTTbl)cTTblTemplate_3.copy(), doc);
                table_3.removeRow(4);
                table_3.removeRow(3);
                table_3.removeRow(2);
                table_3.removeRow(1);
                for (int k=0; k<contador; k++){
                    table_3.addRow(tableDocumment_3.getRow(1));
                }
                table_3.addRow(tableDocumment_3.getRow(2));
                table_3.addRow(tableDocumment_3.getRow(3));
                table_3.addRow(tableDocumment_3.getRow(4));

                doc.setTable(contTabla, table_3);

                XWPFParagraph para4 = doc.createParagraph();
                XWPFRun run4 = para4.createRun();
                run4.addBreak();

                contTabla++;
                contador=0;
            }

            if (metodoMuestraList.get(i).getMethod().getCodigoMetodo().equals("MET-AEXE-013") && bandAEXE == 0){
                bandAEXE++;
                for (int j = 0; j<metodoMuestraList.size(); j++){
                    if (metodoMuestraList.get(j).getMethod().getCodigoMetodo().equals("MET-AEXE-013")){
                        contador++;
                    }
                }

                XWPFStyles newStyles = doc.createStyles();
                newStyles.setStyles(plantilla.getStyle());
                XWPFParagraph para = doc.createParagraph();
                para.setStyle("Ttulo2");
                XWPFRun run = para.createRun();
                run.setText("Envejecimiento acelerado por Xenón");

                XWPFTable table = doc.createTable();
                table.removeRow(0); // El default row no es necesario
                XWPFTable tableDocumment = plantilla.getTables().get(33);
                CTTbl cTTblTemplate=tableDocumment.getCTTbl();
                table = new XWPFTable((CTTbl)cTTblTemplate.copy(), doc);
                doc.setTable(contTabla, table);
                XWPFParagraph para1 = doc.createParagraph();
                XWPFRun run1 = para1.createRun();
                run1.addBreak();
                bandAEXE++;
                contTabla++;

                XWPFTable table_2 = doc.createTable();
                table_2.removeRow(0); // El default row no es necesario
                XWPFTable tableDocumment_2 = plantilla.getTables().get(34);
                CTTbl cTTblTemplate_2=tableDocumment_2.getCTTbl();
                table_2 = new XWPFTable((CTTbl)cTTblTemplate_2.copy(), doc);
                doc.setTable(contTabla, table_2);
                XWPFParagraph para2 = doc.createParagraph();
                XWPFRun run2 = para2.createRun();
                run2.addBreak();
                contTabla++;

                XWPFParagraph para3 = doc.createParagraph();
                para3.setStyle("Tablas");
                XWPFRun run3 = para3.createRun();
                run3.setText("Resultados del envejecimiento acelerado por xenón");

                XWPFTable table_3 = doc.createTable();
                table_3.removeRow(0); // El default row no es necesario
                XWPFTable tableDocumment_3 = plantilla.getTables().get(35);
                CTTbl cTTblTemplate_3=tableDocumment_3.getCTTbl();
                table_3 = new XWPFTable((CTTbl)cTTblTemplate_3.copy(), doc);
                table_3.removeRow(4);
                table_3.removeRow(3);
                table_3.removeRow(2);
                table_3.removeRow(1);
                for (int k=0; k<contador; k++){
                    table_3.addRow(tableDocumment_3.getRow(1));
                }
                table_3.addRow(tableDocumment_3.getRow(2));
                table_3.addRow(tableDocumment_3.getRow(3));
                table_3.addRow(tableDocumment_3.getRow(4));

                doc.setTable(contTabla, table_3);

                XWPFParagraph para4 = doc.createParagraph();
                XWPFRun run4 = para4.createRun();
                run4.addBreak();

                contTabla++;
                contador=0;
            }

            if (metodoMuestraList.get(i).getMethod().getCodigoMetodo().equals("MET-OIT-014") && bandOIT == 0){
                bandOIT++;
                for (int j = 0; j<metodoMuestraList.size(); j++){
                    if (metodoMuestraList.get(j).getMethod().getCodigoMetodo().equals("MET-OIT-014")){
                        contador++;
                    }
                }

                XWPFStyles newStyles = doc.createStyles();
                newStyles.setStyles(plantilla.getStyle());
                XWPFParagraph para = doc.createParagraph();
                para.setStyle("Ttulo2");
                XWPFRun run = para.createRun();
                run.setText("Determinación del tiempo de inducción a la oxidación (OIT)");

                XWPFTable table = doc.createTable();
                table.removeRow(0); // El default row no es necesario
                XWPFTable tableDocumment = plantilla.getTables().get(36);
                CTTbl cTTblTemplate=tableDocumment.getCTTbl();
                table = new XWPFTable((CTTbl)cTTblTemplate.copy(), doc);
                doc.setTable(contTabla, table);
                XWPFParagraph para1 = doc.createParagraph();
                XWPFRun run1 = para1.createRun();
                run1.addBreak();
                bandOIT++;
                contTabla++;

                XWPFTable table_2 = doc.createTable();
                table_2.removeRow(0); // El default row no es necesario
                XWPFTable tableDocumment_2 = plantilla.getTables().get(37);
                CTTbl cTTblTemplate_2=tableDocumment_2.getCTTbl();
                table_2 = new XWPFTable((CTTbl)cTTblTemplate_2.copy(), doc);
                doc.setTable(contTabla, table_2);
                XWPFParagraph para2 = doc.createParagraph();
                XWPFRun run2 = para2.createRun();
                run2.addBreak();
                contTabla++;

                XWPFParagraph para3 = doc.createParagraph();
                para3.setStyle("Tablas");
                XWPFRun run3 = para3.createRun();
                run3.setText("Resultados de la determinación de la estabilidad oxidativa");

                XWPFTable table_3 = doc.createTable();
                table_3.removeRow(0); // El default row no es necesario
                XWPFTable tableDocumment_3 = plantilla.getTables().get(38);
                CTTbl cTTblTemplate_3=tableDocumment_3.getCTTbl();
                table_3 = new XWPFTable((CTTbl)cTTblTemplate_3.copy(), doc);
                table_3.removeRow(4);
                table_3.removeRow(3);
                table_3.removeRow(2);
                table_3.removeRow(1);
                for (int k=0; k<contador; k++){
                    table_3.addRow(tableDocumment_3.getRow(1));
                }
                table_3.addRow(tableDocumment_3.getRow(2));
                table_3.addRow(tableDocumment_3.getRow(3));
                table_3.addRow(tableDocumment_3.getRow(4));

                doc.setTable(contTabla, table_3);

                XWPFParagraph para4 = doc.createParagraph();
                XWPFRun run4 = para4.createRun();
                run4.addBreak();

                run4.setText("Nota: Tiempo de inducción a la oxidación");
                run4.addBreak();

                contTabla++;
                contador=0;

                //TODO: APARTADO PARA IMAGEN
            }

            if (metodoMuestraList.get(i).getMethod().getCodigoMetodo().equals("MET-DSC-015") && bandDSC == 0){
                bandDSC++;
                for (int j = 0; j<metodoMuestraList.size(); j++){
                    if (metodoMuestraList.get(j).getMethod().getCodigoMetodo().equals("MET-DSC-015")){
                        contador++;
                    }
                }

                XWPFStyles newStyles = doc.createStyles();
                newStyles.setStyles(plantilla.getStyle());
                XWPFParagraph para = doc.createParagraph();
                para.setStyle("Ttulo2");
                XWPFRun run = para.createRun();
                run.setText("Determinación de transiciones térmicas de polímeros");

                XWPFTable table = doc.createTable();
                table.removeRow(0); // El default row no es necesario
                XWPFTable tableDocumment = plantilla.getTables().get(39);
                CTTbl cTTblTemplate=tableDocumment.getCTTbl();
                table = new XWPFTable((CTTbl)cTTblTemplate.copy(), doc);
                doc.setTable(contTabla, table);
                XWPFParagraph para1 = doc.createParagraph();
                XWPFRun run1 = para1.createRun();
                run1.addBreak();
                bandDSC++;
                contTabla++;

                XWPFTable table_2 = doc.createTable();
                table_2.removeRow(0); // El default row no es necesario
                XWPFTable tableDocumment_2 = plantilla.getTables().get(40);
                CTTbl cTTblTemplate_2=tableDocumment_2.getCTTbl();
                table_2 = new XWPFTable((CTTbl)cTTblTemplate_2.copy(), doc);
                doc.setTable(contTabla, table_2);
                XWPFParagraph para2 = doc.createParagraph();
                XWPFRun run2 = para2.createRun();
                run2.addBreak();
                contTabla++;

                XWPFParagraph para3 = doc.createParagraph();
                para3.setStyle("Tablas");
                XWPFRun run3 = para3.createRun();
                run3.setText("Resultados de la determinación de transiciones térmicas de polímeros correspondientes al segundo calentamiento.");

                XWPFTable table_3 = doc.createTable();
                table_3.removeRow(0); // El default row no es necesario
                XWPFTable tableDocumment_3 = plantilla.getTables().get(41);
                CTTbl cTTblTemplate_3=tableDocumment_3.getCTTbl();
                table_3 = new XWPFTable((CTTbl)cTTblTemplate_3.copy(), doc);
                table_3.removeRow(4);
                table_3.removeRow(3);
                table_3.removeRow(2);
                table_3.removeRow(1);
                for (int k=0; k<contador; k++){
                    table_3.addRow(tableDocumment_3.getRow(1));
                }
                table_3.addRow(tableDocumment_3.getRow(2));
                table_3.addRow(tableDocumment_3.getRow(3));
                table_3.addRow(tableDocumment_3.getRow(4));

                doc.setTable(contTabla, table_3);

                XWPFParagraph para4 = doc.createParagraph();
                XWPFRun run4 = para4.createRun();
                run4.addBreak();

                contTabla++;
                contador=0;

                //TODO: APARTADO DE IMAGEN
            }

            if (metodoMuestraList.get(i).getMethod().getCodigoMetodo().equals("MET-CST-016") && bandCST == 0){
                bandCST++;
                for (int j = 0; j<metodoMuestraList.size(); j++){
                    if (metodoMuestraList.get(j).getMethod().getCodigoMetodo().equals("MET-CST-016")){
                        contador++;
                    }
                }

                XWPFStyles newStyles = doc.createStyles();
                newStyles.setStyles(plantilla.getStyle());
                XWPFParagraph para = doc.createParagraph();
                para.setStyle("Ttulo2");
                XWPFRun run = para.createRun();
                run.setText("Determinación de la curva de sellado en caliente");

                XWPFTable table = doc.createTable();
                table.removeRow(0); // El default row no es necesario
                XWPFTable tableDocumment = plantilla.getTables().get(42);
                CTTbl cTTblTemplate=tableDocumment.getCTTbl();
                table = new XWPFTable((CTTbl)cTTblTemplate.copy(), doc);
                doc.setTable(contTabla, table);
                XWPFParagraph para1 = doc.createParagraph();
                XWPFRun run1 = para1.createRun();
                run1.addBreak();
                bandCST++;
                contTabla++;

                XWPFTable table_2 = doc.createTable();
                table_2.removeRow(0); // El default row no es necesario
                XWPFTable tableDocumment_2 = plantilla.getTables().get(43);
                CTTbl cTTblTemplate_2=tableDocumment_2.getCTTbl();
                table_2 = new XWPFTable((CTTbl)cTTblTemplate_2.copy(), doc);
                doc.setTable(contTabla, table_2);
                XWPFParagraph para2 = doc.createParagraph();
                XWPFRun run2 = para2.createRun();
                run2.addBreak();
                contTabla++;

                XWPFParagraph para3 = doc.createParagraph();
                para3.setStyle("Tablas");
                XWPFRun run3 = para3.createRun();
                run3.setText("Resultados de la determinación de la curva de sellado en caliente");

                XWPFTable table_3 = doc.createTable();
                table_3.removeRow(0); // El default row no es necesario
                XWPFTable tableDocumment_3 = plantilla.getTables().get(44);
                CTTbl cTTblTemplate_3=tableDocumment_3.getCTTbl();
                table_3 = new XWPFTable((CTTbl)cTTblTemplate_3.copy(), doc);
                table_3.removeRow(4);
                table_3.removeRow(3);
                table_3.removeRow(2);
                table_3.removeRow(1);
                for (int k=0; k<contador; k++){
                    table_3.addRow(tableDocumment_3.getRow(1));
                }
                table_3.addRow(tableDocumment_3.getRow(2));
                table_3.addRow(tableDocumment_3.getRow(3));
                table_3.addRow(tableDocumment_3.getRow(4));

                doc.setTable(contTabla, table_3);

                XWPFParagraph para4 = doc.createParagraph();
                XWPFRun run4 = para4.createRun();
                run4.addBreak();

                contTabla++;
                contador=0;
            }

            if (metodoMuestraList.get(i).getMethod().getCodigoMetodo().equals("MET-IF-017") && bandIF == 0){
                bandIF++;
                for (int j = 0; j<metodoMuestraList.size(); j++){
                    if (metodoMuestraList.get(j).getMethod().getCodigoMetodo().equals("MET-IF-017")){
                        contador++;
                    }
                }

                XWPFStyles newStyles = doc.createStyles();
                newStyles.setStyles(plantilla.getStyle());
                XWPFParagraph para = doc.createParagraph();
                para.setStyle("Ttulo2");
                XWPFRun run = para.createRun();
                run.setText("Determinación de índice de fluidez");

                XWPFTable table = doc.createTable();
                table.removeRow(0); // El default row no es necesario
                XWPFTable tableDocumment = plantilla.getTables().get(45);
                CTTbl cTTblTemplate=tableDocumment.getCTTbl();
                table = new XWPFTable((CTTbl)cTTblTemplate.copy(), doc);
                doc.setTable(contTabla, table);
                XWPFParagraph para1 = doc.createParagraph();
                XWPFRun run1 = para1.createRun();
                run1.addBreak();
                bandIF++;
                contTabla++;

                XWPFTable table_2 = doc.createTable();
                table_2.removeRow(0); // El default row no es necesario
                XWPFTable tableDocumment_2 = plantilla.getTables().get(46);
                CTTbl cTTblTemplate_2=tableDocumment_2.getCTTbl();
                table_2 = new XWPFTable((CTTbl)cTTblTemplate_2.copy(), doc);
                doc.setTable(contTabla, table_2);
                XWPFParagraph para2 = doc.createParagraph();
                XWPFRun run2 = para2.createRun();
                run2.addBreak();
                contTabla++;

                XWPFParagraph para3 = doc.createParagraph();
                para3.setStyle("Tablas");
                XWPFRun run3 = para3.createRun();
                run3.setText("Resultados de la determinación del índice de fluidez");

                XWPFTable table_3 = doc.createTable();
                table_3.removeRow(0); // El default row no es necesario
                XWPFTable tableDocumment_3 = plantilla.getTables().get(47);
                CTTbl cTTblTemplate_3=tableDocumment_3.getCTTbl();
                table_3 = new XWPFTable((CTTbl)cTTblTemplate_3.copy(), doc);
                table_3.removeRow(4);
                table_3.removeRow(3);
                table_3.removeRow(2);
                table_3.removeRow(1);
                for (int k=0; k<contador; k++){
                    table_3.addRow(tableDocumment_3.getRow(1));
                }
                table_3.addRow(tableDocumment_3.getRow(2));
                table_3.addRow(tableDocumment_3.getRow(3));
                table_3.addRow(tableDocumment_3.getRow(4));

                doc.setTable(contTabla, table_3);

                XWPFParagraph para4 = doc.createParagraph();
                XWPFRun run4 = para4.createRun();
                run4.addBreak();

                contTabla++;
                contador=0;
            }

            if (metodoMuestraList.get(i).getMethod().getCodigoMetodo().equals("MET-PO-018") && bandPO == 0){
                bandPO++;
                for (int j = 0; j<metodoMuestraList.size(); j++){
                    if (metodoMuestraList.get(j).getMethod().getCodigoMetodo().equals("MET-PO-018")){
                        contador++;
                    }
                }

                XWPFStyles newStyles = doc.createStyles();
                newStyles.setStyles(plantilla.getStyle());
                XWPFParagraph para = doc.createParagraph();
                para.setStyle("Ttulo2");
                XWPFRun run = para.createRun();
                run.setText("Determinación de la permeabilidad al oxígeno");

                XWPFTable table = doc.createTable();
                table.removeRow(0); // El default row no es necesario
                XWPFTable tableDocumment = plantilla.getTables().get(48);
                CTTbl cTTblTemplate=tableDocumment.getCTTbl();
                table = new XWPFTable((CTTbl)cTTblTemplate.copy(), doc);
                doc.setTable(contTabla, table);
                XWPFParagraph para1 = doc.createParagraph();
                XWPFRun run1 = para1.createRun();
                run1.addBreak();
                bandPO++;
                contTabla++;

                XWPFTable table_2 = doc.createTable();
                table_2.removeRow(0); // El default row no es necesario
                XWPFTable tableDocumment_2 = plantilla.getTables().get(49);
                CTTbl cTTblTemplate_2=tableDocumment_2.getCTTbl();
                table_2 = new XWPFTable((CTTbl)cTTblTemplate_2.copy(), doc);
                doc.setTable(contTabla, table_2);
                XWPFParagraph para2 = doc.createParagraph();
                XWPFRun run2 = para2.createRun();
                run2.addBreak();
                contTabla++;

                XWPFParagraph para3 = doc.createParagraph();
                para3.setStyle("Tablas");
                XWPFRun run3 = para3.createRun();
                run3.setText("Resultados de la determinación de la permeabilidad al oxígeno");

                XWPFTable table_3 = doc.createTable();
                table_3.removeRow(0); // El default row no es necesario
                XWPFTable tableDocumment_3 = plantilla.getTables().get(50);
                CTTbl cTTblTemplate_3=tableDocumment_3.getCTTbl();
                table_3 = new XWPFTable((CTTbl)cTTblTemplate_3.copy(), doc);
                table_3.removeRow(3);
                table_3.removeRow(2);
                table_3.removeRow(1);
                for (int k=0; k<contador; k++){
                    table_3.addRow(tableDocumment_3.getRow(1));
                }
                table_3.addRow(tableDocumment_3.getRow(2));
                table_3.addRow(tableDocumment_3.getRow(3));

                doc.setTable(contTabla, table_3);

                XWPFParagraph para4 = doc.createParagraph();
                XWPFRun run4 = para4.createRun();
                run4.addBreak();

                contTabla++;
                contador=0;
            }

            if (metodoMuestraList.get(i).getMethod().getCodigoMetodo().equals("MET-PRR-019") && bandPRR == 0){
                bandPRR++;
                for (int j = 0; j<metodoMuestraList.size(); j++){
                    if (metodoMuestraList.get(j).getMethod().getCodigoMetodo().equals("MET-PRR-019")){
                        contador++;
                    }
                }

                XWPFStyles newStyles = doc.createStyles();
                newStyles.setStyles(plantilla.getStyle());
                XWPFParagraph para = doc.createParagraph();
                para.setStyle("Ttulo2");
                XWPFRun run = para.createRun();
                run.setText("Determinación de la resistencia al rasgado");

                XWPFTable table = doc.createTable();
                table.removeRow(0); // El default row no es necesario
                XWPFTable tableDocumment = plantilla.getTables().get(51);
                CTTbl cTTblTemplate=tableDocumment.getCTTbl();
                table = new XWPFTable((CTTbl)cTTblTemplate.copy(), doc);
                doc.setTable(contTabla, table);
                XWPFParagraph para1 = doc.createParagraph();
                XWPFRun run1 = para1.createRun();
                run1.addBreak();
                bandPRR++;
                contTabla++;

                XWPFTable table_2 = doc.createTable();
                table_2.removeRow(0); // El default row no es necesario
                XWPFTable tableDocumment_2 = plantilla.getTables().get(52);
                CTTbl cTTblTemplate_2=tableDocumment_2.getCTTbl();
                table_2 = new XWPFTable((CTTbl)cTTblTemplate_2.copy(), doc);
                doc.setTable(contTabla, table_2);
                XWPFParagraph para2 = doc.createParagraph();
                XWPFRun run2 = para2.createRun();
                run2.addBreak();
                contTabla++;

                XWPFParagraph para3 = doc.createParagraph();
                para3.setStyle("Tablas");
                XWPFRun run3 = para3.createRun();
                run3.setText("Resultados de la determinación de resistencia al rasgado");

                XWPFTable table_3 = doc.createTable();
                table_3.removeRow(0); // El default row no es necesario
                XWPFTable tableDocumment_3 = plantilla.getTables().get(53);
                CTTbl cTTblTemplate_3=tableDocumment_3.getCTTbl();
                table_3 = new XWPFTable((CTTbl)cTTblTemplate_3.copy(), doc);
                table_3.removeRow(7);
                table_3.removeRow(6);
                table_3.removeRow(5);
                table_3.removeRow(4);
                table_3.removeRow(3);
                table_3.removeRow(2);
                for (int k=0; k<contador; k++){
                    table_3.addRow(tableDocumment_3.getRow(2));
                }
                table_3.addRow(tableDocumment_3.getRow(3));
                for (int k=0; k<contador; k++){
                    table_3.addRow(tableDocumment_3.getRow(4));
                }
                table_3.addRow(tableDocumment_3.getRow(5));
                table_3.addRow(tableDocumment_3.getRow(6));
                table_3.addRow(tableDocumment_3.getRow(7));

                doc.setTable(contTabla, table_3);

                XWPFParagraph para4 = doc.createParagraph();
                XWPFRun run4 = para4.createRun();
                run4.addBreak();

                run4.setText("Nota: 1 Dirección máquina; 2 Dirección transversal.");
                run4.addBreak();

                contTabla++;
                contador=0;
            }

            if (metodoMuestraList.get(i).getMethod().getCodigoMetodo().equals("MET-RTER-020") && bandRTER == 0){
                bandRTER++;
                for (int j = 0; j<metodoMuestraList.size(); j++){
                    if (metodoMuestraList.get(j).getMethod().getCodigoMetodo().equals("MET-RTER-020")){
                        contador++;
                    }
                }

                XWPFStyles newStyles = doc.createStyles();
                newStyles.setStyles(plantilla.getStyle());
                XWPFParagraph para = doc.createParagraph();
                para.setStyle("Ttulo2");
                XWPFRun run = para.createRun();
                run.setText("Determinación de resistencia a la tensión y elongación a la ruptura");

                XWPFTable table = doc.createTable();
                table.removeRow(0); // El default row no es necesario
                XWPFTable tableDocumment = plantilla.getTables().get(54);
                CTTbl cTTblTemplate=tableDocumment.getCTTbl();
                table = new XWPFTable((CTTbl)cTTblTemplate.copy(), doc);
                doc.setTable(contTabla, table);
                XWPFParagraph para1 = doc.createParagraph();
                XWPFRun run1 = para1.createRun();
                run1.addBreak();
                contTabla++;

                XWPFTable table_2 = doc.createTable();
                table_2.removeRow(0); // El default row no es necesario
                XWPFTable tableDocumment_2 = plantilla.getTables().get(55);
                CTTbl cTTblTemplate_2=tableDocumment_2.getCTTbl();
                table_2 = new XWPFTable((CTTbl)cTTblTemplate_2.copy(), doc);
                doc.setTable(contTabla, table_2);
                XWPFParagraph para2 = doc.createParagraph();
                XWPFRun run2 = para2.createRun();
                run2.addBreak();
                contTabla++;

                XWPFParagraph para3 = doc.createParagraph();
                para3.setStyle("Tablas");
                XWPFRun run3 = para3.createRun();
                run3.setText("Resultados de la determinación de resistencia a la tensión y elongación a la ruptura");

                XWPFTable table_3 = doc.createTable();
                table_3.removeRow(0); // El default row no es necesario
                XWPFTable tableDocumment_3 = plantilla.getTables().get(56);
                CTTbl cTTblTemplate_3=tableDocumment_3.getCTTbl();
                table_3 = new XWPFTable((CTTbl)cTTblTemplate_3.copy(), doc);
                table_3.removeRow(7);
                table_3.removeRow(6);
                table_3.removeRow(5);
                table_3.removeRow(4);
                table_3.removeRow(3);
                table_3.removeRow(2);
                for (int k=0; k<contador; k++){
                    table_3.addRow(tableDocumment_3.getRow(2));
                }
                table_3.addRow(tableDocumment_3.getRow(3));
                for (int k=0; k<contador; k++){
                    table_3.addRow(tableDocumment_3.getRow(4));
                }
                table_3.addRow(tableDocumment_3.getRow(5));
                table_3.addRow(tableDocumment_3.getRow(6));
                table_3.addRow(tableDocumment_3.getRow(7));

                doc.setTable(contTabla, table_3);

                XWPFParagraph para4 = doc.createParagraph();
                XWPFRun run4 = para4.createRun();
                run4.addBreak();

                run4.setText("Notas: 1 Dirección máquina; 2 Dirección transversal.");
                run4.addBreak();

                contTabla++;
                contador=0;
            }

            if (metodoMuestraList.get(i).getMethod().getCodigoMetodo().equals("MET-RCD-021") && bandRCD == 0){
                bandRCD++;
                for (int j = 0; j<metodoMuestraList.size(); j++){
                    if (metodoMuestraList.get(j).getMethod().getCodigoMetodo().equals("MET-RCD-021")){
                        contador++;
                    }
                }

                XWPFStyles newStyles = doc.createStyles();
                newStyles.setStyles(plantilla.getStyle());
                XWPFParagraph para = doc.createParagraph();
                para.setStyle("Ttulo2");
                XWPFRun run = para.createRun();
                run.setText("Determinación de la resistencia a la carga dinámica");

                XWPFTable table = doc.createTable();
                table.removeRow(0); // El default row no es necesario
                XWPFTable tableDocumment = plantilla.getTables().get(57);
                CTTbl cTTblTemplate=tableDocumment.getCTTbl();
                table = new XWPFTable((CTTbl)cTTblTemplate.copy(), doc);
                doc.setTable(contTabla, table);
                XWPFParagraph para1 = doc.createParagraph();
                XWPFRun run1 = para1.createRun();
                run1.addBreak();
                contTabla++;

                XWPFTable table_2 = doc.createTable();
                table_2.removeRow(0); // El default row no es necesario
                XWPFTable tableDocumment_2 = plantilla.getTables().get(58);
                CTTbl cTTblTemplate_2=tableDocumment_2.getCTTbl();
                table_2 = new XWPFTable((CTTbl)cTTblTemplate_2.copy(), doc);
                doc.setTable(contTabla, table_2);
                XWPFParagraph para2 = doc.createParagraph();
                XWPFRun run2 = para2.createRun();
                run2.addBreak();
                contTabla++;

                XWPFParagraph para3 = doc.createParagraph();
                para3.setStyle("Tablas");
                XWPFRun run3 = para3.createRun();
                run3.setText("Resultados de la determinación de la resistencia a la carga dinámica");

                XWPFTable table_3 = doc.createTable();
                table_3.removeRow(0); // El default row no es necesario
                XWPFTable tableDocumment_3 = plantilla.getTables().get(59);
                CTTbl cTTblTemplate_3=tableDocumment_3.getCTTbl();
                table_3 = new XWPFTable((CTTbl)cTTblTemplate_3.copy(), doc);
                table_3.removeRow(4);
                table_3.removeRow(3);
                table_3.removeRow(2);
                table_3.removeRow(1);
                for (int k=0; k<contador; k++){
                    table_3.addRow(tableDocumment_3.getRow(1));
                }
                table_3.addRow(tableDocumment_3.getRow(2));
                table_3.addRow(tableDocumment_3.getRow(3));
                table_3.addRow(tableDocumment_3.getRow(4));

                doc.setTable(contTabla, table_3);

                XWPFParagraph para4 = doc.createParagraph();
                XWPFRun run4 = para4.createRun();
                run4.addBreak();

                contTabla++;
                contador=0;

                //TODO: APARTADO DE IMAGEN
            }

            if (metodoMuestraList.get(i).getMethod().getCodigoMetodo().equals("MET-RI-022") && bandRI == 0){
                bandRI++;
                for (int j = 0; j<metodoMuestraList.size(); j++){
                    if (metodoMuestraList.get(j).getMethod().getCodigoMetodo().equals("MET-RI-022")){
                        contador++;
                    }
                }

                XWPFStyles newStyles = doc.createStyles();
                newStyles.setStyles(plantilla.getStyle());
                XWPFParagraph para = doc.createParagraph();
                para.setStyle("Ttulo2");
                XWPFRun run = para.createRun();
                run.setText("Determinación de la resistencia al impacto");

                XWPFTable table = doc.createTable();
                table.removeRow(0); // El default row no es necesario
                XWPFTable tableDocumment = plantilla.getTables().get(60);
                CTTbl cTTblTemplate=tableDocumment.getCTTbl();
                table = new XWPFTable((CTTbl)cTTblTemplate.copy(), doc);
                doc.setTable(contTabla, table);
                XWPFParagraph para1 = doc.createParagraph();
                XWPFRun run1 = para1.createRun();
                run1.addBreak();
                contTabla++;

                XWPFParagraph para3 = doc.createParagraph();
                para3.setStyle("Tablas");
                XWPFRun run3 = para3.createRun();
                run3.setText("Resultados experimentales para la determinación de la resistencia al impacto");

                XWPFTable table_3 = doc.createTable();
                table_3.removeRow(0); // El default row no es necesario
                XWPFTable tableDocumment_3 = plantilla.getTables().get(61);
                CTTbl cTTblTemplate_3=tableDocumment_3.getCTTbl();
                table_3 = new XWPFTable((CTTbl)cTTblTemplate_3.copy(), doc);
                table_3.removeRow(4);
                table_3.removeRow(3);
                table_3.removeRow(2);
                table_3.removeRow(1);
                for (int k=0; k<contador; k++){
                    table_3.addRow(tableDocumment_3.getRow(1));
                }
                table_3.addRow(tableDocumment_3.getRow(2));
                table_3.addRow(tableDocumment_3.getRow(3));
                table_3.addRow(tableDocumment_3.getRow(4));

                doc.setTable(contTabla, table_3);

                XWPFParagraph para4 = doc.createParagraph();
                XWPFRun run4 = para4.createRun();
                run4.addBreak();

                contTabla++;
                contador=0;
            }

        }
        XWPFTable table = doc.createTable();
        table.removeRow(0); // El default row no es necesario
        XWPFTable tableDocumment = plantilla.getTables().get(62);
        CTTbl cTTblTemplate=tableDocumment.getCTTbl();
        table = new XWPFTable((CTTbl)cTTblTemplate.copy(), doc);
        doc.setTable(contTabla, table);
        XWPFParagraph para1 = doc.createParagraph();
        XWPFRun run1 = para1.createRun();
        run1.addBreak();
        run1.setText("Dirección donde se realizan las actividades del laboratorio: Calle 21 Este 205 Bodega F, Civac, 62578, Jiutepec, Morelos.");
        run1.setFontFamily("Century Gothic");
        run1.setFontSize(8);

        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Disposition", "inline; filename=FIR-ERAI-" + estructuraNombres.getNombre() + ".docx");
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

    private String getAttributeContacto(int bandera, JSONArray contactosAux, String attribute) throws JSONException {
        StringBuilder contacto = new StringBuilder();
        JSONObject jsonObject;
        for (int i = 0; i < contactosAux.length(); i++) {
            jsonObject = (JSONObject) contactosAux.get(i);
            if (attribute.equals("nombrePersonaContacto")) {
                if (bandera == i) {
                    return jsonObject.get("nombrePersonaContacto").toString();
                }
            }
            if (attribute.equals("cargo")) {
                if (bandera == i) {
                    return jsonObject.get("cargo").toString();
                }
            }
            if (attribute.equals("email")) {
                if (bandera == i) {
                    return jsonObject.get("email").toString();
                }
            }
            if (attribute.equals("telefonoOficina")) {
                if (bandera == i) {
                    return jsonObject.get("telefonoOficina").toString();
                }
            }
            if (attribute.equals("telefonoCelular")) {
                if (bandera == i) {
                    return jsonObject.get("telefonoCelular").toString();
                }
            }
        }
        return contacto.toString();
    }

    private static void addRow(XWPFTable table, XWPFTableRow row, String... colTexts){
        for (int col = 0; col < colTexts.length; col++) {
            row.getCell(col).getParagraphArray(0).getRuns().get(0).setText(colTexts[col], 0);
        }
        table.addRow(row);
    }
}
