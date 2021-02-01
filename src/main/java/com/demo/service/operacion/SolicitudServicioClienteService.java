package com.demo.service.operacion;

import com.demo.model.Method;
import com.demo.model.operacion.MetodoMuestra;
import com.demo.model.operacion.RecepcionVerificacionRegistroCodificacion;
import com.demo.model.operacion.SolicitudServicioCliente;
import com.demo.model.operacion.SolicitudServicioClienteMuestras;
import com.demo.model.operacion.metodos.*;
import com.demo.repository.operacion.MetodoMuestraRepository;
import com.demo.repository.operacion.SolicitudServicioClienteMuestrasRepository;
import com.demo.repository.operacion.SolicitudServicioClienteRepository;

import com.demo.repository.operacion.metodos.*;
import com.demo.service.MethodService;
import com.demo.utils.EstructuraNombres;
import com.demo.utils.FormatoFechas;
import com.demo.utils.Numero_Letras;
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

import java.io.*;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
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

    @Autowired
    private MethodService methodService;

    @Autowired
    private RecepcionVerificacionRegistroCodificacionService recepcionVerificacionRegistroCodificacionService;

    @Autowired
    private FRA_AT_001_Repository fra_at_001_repository;

    @Autowired
    private FRA_DI_001_Repository fra_di_001_repository;

    @Autowired
    private FRA_ES_001_Repository fra_es_001_repository;

    @Autowired
    private FRA_GR_001_Repository fra_gr_001_repository;

    @Autowired
    private FRA_HUM_001_Repository fra_hum_001_repository;

    @Autowired
    private FRA_NCP_001_Repository fra_ncp_001_repository;

    @Autowired
    private FRA_PPG_001_Repository fra_ppg_001_repository;

    @Autowired
    private FRA_FTIR_001_Repository fra_ftir_001_repository;

    @Autowired
    private FRA_TGA_001_Repository fra_tga_001_repository;

    @Autowired
    private FRA_ICO_001_Repository fra_ico_001_repository;

    @Autowired
    private FRA_EAT_001_Repository fra_eat_001_repository;

    @Autowired
    private FRA_EAUV_001_Repository fra_eauv_001_repository;

    @Autowired
    private FRA_EAXE_013_Repository fra_eaxe_013_repository;

    @Autowired
    private FRA_OIT_001_Repository fra_oit_001_repository;

    @Autowired
    private FRA_DSC_Repository fra_dsc_repository;

    @Autowired
    private FRA_CST_001_Repository fra_cst_001_repository;

    @Autowired
    private FRA_IF_001_Repository fra_if_001_repository;

    @Autowired
    private FRA_PO_001_Repository fra_po_001_repository;

    @Autowired
    private FRA_PRR_001_Repository fra_prr_001_repository;

    @Autowired
    private FRA_RTER_001_Repository fra_rter_001_repository;

    //@Autowired
    //private FRA_RCD_001_Repository fra_rcd_001_repository;

    //@Autowired
    //private FRA_RI_001_Repository fra_ri_001_repository;

    EstructuraNombres estructuraNombres = new EstructuraNombres();
    Numero_Letras numeroLetras = new Numero_Letras();
    FormatoFechas formatoFechas = new FormatoFechas();
    WordCopyTableAfterTable wordCopyTableAfterTable = new WordCopyTableAfterTable();

    private static final Logger LOGGER = LoggerFactory.getLogger("info");

    private static final Logger APP = LoggerFactory.getLogger("info");

    public SolicitudServicioCliente save(SolicitudServicioCliente solicitudServicioCliente) {
        return solicitudServicioClienteRepository.save(solicitudServicioCliente);
    }

    public List<SolicitudServicioCliente> findAll() {
        return solicitudServicioClienteRepository.findAll();
    }

    public List<SolicitudServicioCliente> findAllByEstatusPago(String estatus){
        return solicitudServicioClienteRepository.findAllByEstatusPago(estatus);
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

    public ResponseEntity<InputStreamResource> generarListaSolicitud(String filtro) throws InvalidFormatException, IOException{
        ClassPathResource resource = new ClassPathResource("/documentos/LFS-SOC-001.docx");
        XWPFDocument doc = new XWPFDocument(resource.getInputStream());

        List<SolicitudServicioCliente> lista;
        if (filtro.equals("todos")){
            lista = solicitudServicioClienteRepository.findAll();
        } else {
            lista = solicitudServicioClienteRepository.findAllByEstatusPago(filtro);
        }

        List<String>contactosAux;
        int bandera=0;

        XWPFTable table = doc.getTables().get(0);
        table.getRow(0).setRepeatHeader(true);
        for (int i = 0; i<lista.size(); i++) {
            XWPFTableRow tableRow = table.createRow();
            tableRow.getCell(0).setText((i+1)+"");
            try{
                tableRow.getCell(1).setText(formatoFechas.formateadorFechas(lista.get(i).getFechaRecepcionMuestras()));
            } catch (NullPointerException e){
                System.out.println("No se ha establecido fecha en la solicitud");
            }
            tableRow.getCell(2).setText(lista.get(i).getFolioSolitudServicioCliente());
            tableRow.getCell(3).setText(lista.get(i).getClient().getNombreRazonSocial());
            List<SolicitudServicioClienteMuestras> listaMuestras = solicitudServicioClienteMuestrasRepository.findAllBySolicitudServicioCliente_SolicitudServicioClienteId(lista.get(i).getSolicitudServicioClienteId());
            XWPFParagraph paragraph = tableRow.getCell(4).addParagraph();
            paragraph.setAlignment(ParagraphAlignment.LEFT);
            XWPFRun run = paragraph.createRun();
            for (int j=0; j<listaMuestras.size(); j++){
                run.setText(listaMuestras.get(j).getIdClienteMuestra());
                run.addBreak();
            }
            List<MetodoMuestra> metodoMuestraList = metodoMuestraRepository.findAllBySolicitudServicioClienteMuestras_SolicitudServicioCliente(lista.get(i));
            XWPFParagraph paragraph2 = tableRow.getCell(5).addParagraph();
            paragraph2.setAlignment(ParagraphAlignment.LEFT);
            XWPFRun run2 = paragraph2.createRun();
            for (int j = 0; j<metodoMuestraList.size(); j++){
                run2.setText(metodoMuestraList.get(j).getMethod().getCodigoMetodo());
                run2.addBreak();
            }
            tableRow.getCell(6).setText(lista.get(i).getEstatusPago());
            try {
                JSONArray jsonArray = new JSONArray(lista.get(i).getClient().getContactosDatos());
                tableRow.getCell(7).setText(getAttributeContacto(bandera, jsonArray, "nombrePersonaContacto"));
            } catch (JSONException e) {
                System.out.println("e: " + e);
            }
            tableRow.getCell(8).setText(lista.get(i).getNombreFirmaReceptor());
        }

/** ESTO FUNCIONA PARA FORMATO WORD (DOCX) */
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Disposition", "inline; filename=LFS_SOC_"+estructuraNombres.getNombre()+".docx");
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

    public ResponseEntity<InputStreamResource> generarListaCotizacion() throws InvalidFormatException, IOException{
        ClassPathResource resource = new ClassPathResource("/documentos/LFC-SOC-002.docx");
        XWPFDocument doc = new XWPFDocument(resource.getInputStream());
        List<SolicitudServicioCliente> lista = solicitudServicioClienteRepository.findAll();

        List<String>contactosAux;
        int bandera=0;

        XWPFTable table = doc.getTables().get(0);
        for (int i = 0; i<lista.size(); i++) {
            XWPFTableRow tableRow = table.createRow();
            tableRow.getCell(0).setText((i+1)+"");
            tableRow.getCell(1).setText(formatoFechas.formateadorFechas(lista.get(i).getFechaRecepcionMuestras()));
            tableRow.getCell(2).setText(lista.get(i).getFolioSolitudServicioCliente());
            tableRow.getCell(3).setText(lista.get(i).getFolioSolitudServicioCliente());
            tableRow.getCell(4).setText(lista.get(i).getNombreFirmaReceptor());
            tableRow.getCell(5).setText("");
        }

        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Disposition", "inline; filename=LFC-SOC-"+estructuraNombres.getNombre()+".docx");
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

    public ResponseEntity<InputStreamResource> crearCotizacion(Long id) throws InvalidFormatException, IOException, XmlException {
        ClassPathResource resource = new ClassPathResource("/documentos/FCO-SOC-003.docx");
        ClassPathResource resource2 = new ClassPathResource("/documentos/plantillaCotizacion.docx");
        XWPFDocument doc = new XWPFDocument(resource.getInputStream());
        XWPFDocument doc2 = new XWPFDocument(resource2.getInputStream());
        SolicitudServicioCliente solicitudServicioCliente = solicitudServicioClienteRepository.findBySolicitudServicioClienteId(id);
        List<SolicitudServicioClienteMuestras> solicitudServicioClienteMuestrasLista = solicitudServicioClienteMuestrasRepository.findAllBySolicitudServicioCliente_SolicitudServicioClienteId(id);
        List<MetodoMuestra> metodoMuestraList = metodoMuestraRepository.findAllBySolicitudServicioClienteMuestras_SolicitudServicioCliente(solicitudServicioCliente);
        List<String> contactosAux;
        int bandera = 0;

        //LISTA ORDENADA OK
        Collections.sort(metodoMuestraList, new Comparator<MetodoMuestra>() {
            public int compare(MetodoMuestra metodoMuestra1, MetodoMuestra metodoMuestra2) {
                return metodoMuestra1.getMethod().getBanderaOrden().compareTo(metodoMuestra2.getMethod().getBanderaOrden());
            }
        });

        XWPFTable table0 = doc.getTables().get(0);
        table0.getRow(0).getCell(1).setText(solicitudServicioCliente.getFolioSolitudServicioCliente());
        table0.getRow(0).getCell(3).setText(solicitudServicioCliente.getFolioSolitudServicioCliente());
        //table0.getRow(1).getCell(1).setText(solicitudServicioCliente.getFolioSolitudServicioCliente());
        table0.getRow(1).getCell(3).setText(formatoFechas.formateadorFechas(solicitudServicioCliente.getFechaRecepcionMuestras()));

        XWPFTable table1 = doc.getTables().get(1);
        table1.getRow(0).getCell(1).setText(solicitudServicioCliente.getClient().getNombreComunEmpresa());
        try {
            JSONArray jsonArray = new JSONArray(solicitudServicioCliente.getClient().getContactosDatos());
            table1.getRow(1).getCell(1).setText(getAttributeContacto(bandera, jsonArray, "nombrePersonaContacto"));
            String telefonos = "" + getAttributeContacto(bandera, jsonArray, "telefonoOficina") + ", " + getAttributeContacto(bandera, jsonArray, "telefonoCelular");
            table1.getRow(2).getCell(1).setText(telefonos);
            table1.getRow(3).getCell(1).setText(getAttributeContacto(bandera, jsonArray, "email"));
        } catch (JSONException e) {
            System.out.println("e: " + e);
        }

        XWPFTable table2 = doc.getTables().get(2);
        table2.getRow(0).getCell(1).setText(solicitudServicioCliente.getClient().getNombreRazonSocial());
        table2.getRow(1).getCell(1).setText(solicitudServicioCliente.getClient().getRfc());
        String direccion = solicitudServicioCliente.getClient().getCalle() + " " +
                solicitudServicioCliente.getClient().getNumero() + " " +
                solicitudServicioCliente.getClient().getColonia() + " " +
                solicitudServicioCliente.getClient().getMunicipio() + " " +
                solicitudServicioCliente.getClient().getEstado() + " " +
                solicitudServicioCliente.getClient().getCodigoPostal();
        table2.getRow(2).getCell(1).setText(direccion);
        table2.getRow(3).getCell(1).setText("DEFINIR");
        table2.getRow(4).getCell(1).setText("Gastos en general");
        try {
            JSONArray jsonArray = new JSONArray(solicitudServicioCliente.getClient().getContactosDatos());
            table2.getRow(5).getCell(1).setText(getAttributeContacto(bandera, jsonArray, "email"));
        } catch (JSONException e) {
            System.out.println("e: " + e);
        }

        XWPFTable table3 = doc.getTables().get(3);
        table3.getRow(0).getCell(1).setText(solicitudServicioCliente.getFolioSolitudServicioCliente());

        List<Method> metodos = methodService.findAll();
        int contadorUnidades = 0;
        double importeIndividual = 0;
        int contIndices = 1;
        double urgente = 0.10;
        int servicioUrgente = 0;

        double urgenteTotal = 0;
        double importeTotal = 0;
        double iva = 0;
        double total = 0;
        XWPFTable table4 = doc.getTables().get(4);
        for (int i = 0; i<metodos.size(); i++){
            for (int j = 0; j<metodoMuestraList.size(); j++){
                if (metodoMuestraList.get(j).getMethod().getCodigoMetodo().equals(metodos.get(i).getCodigoMetodo())){
                    contadorUnidades++;
                    if (metodoMuestraList.get(j).getSolicitudServicioClienteMuestras().getSolicitudServicioCliente().getServicioUrgente().equals("Si")){
                        servicioUrgente++;
                        importeIndividual = (importeIndividual + metodoMuestraList.get(j).getMethod().getCostos());
                    } else {
                        importeIndividual = importeIndividual + metodoMuestraList.get(j).getMethod().getCostos();
                    }
                }
            }
            if (contadorUnidades != 0){
                XWPFTableRow tableRow = table4.createRow();
                tableRow.getCell(0).setText((contIndices) + "");
                tableRow.getCell(1).setText(metodos.get(i).getNombreMetodo());
                tableRow.getCell(2).setText(metodos.get(i).getCodigoMetodo());
                tableRow.getCell(3).setText("$ " + String.format("%.2f", metodos.get(i).getCostos()));
                tableRow.getCell(4).setText(contadorUnidades+"");
                tableRow.getCell(5).setText("$ " + String.format("%.2f", importeIndividual));
                importeTotal = importeTotal + importeIndividual;
                contadorUnidades = 0;
                importeIndividual = 0;
                contIndices++;
            }
        }
        DecimalFormat decimalFormat = new DecimalFormat("#.##");

        XWPFTable tableDocumment_2 = doc2.getTables().get(4);
        if (servicioUrgente != 0){
            urgenteTotal = importeTotal * 0.10;
            iva = (importeTotal + urgenteTotal) * 0.16;
            total = importeTotal + urgenteTotal + iva;
            tableDocumment_2.getRow(8).getCell(5).setText("$ " + String.format("%.2f", urgenteTotal));
        } else {
            iva = importeTotal * 0.16;
            total = importeTotal + iva;
        }
        String numeroLetr = decimalFormat.format(total);
        tableDocumment_2.getRow(9).getCell(5).setText("$ " + String.format("%.2f", importeTotal));
        tableDocumment_2.getRow(10).getCell(5).setText("$ " + String.format("%.2f", iva));
        tableDocumment_2.getRow(11).getCell(5).setText("$ " + String.format("%.2f", total));
        CTTbl cTTblTemplate_2 = tableDocumment_2.getCTTbl();
        if (servicioUrgente != 0) {
            table4.addRow(tableDocumment_2.getRow(8));
        }
        table4.addRow(tableDocumment_2.getRow(9));
        table4.addRow(tableDocumment_2.getRow(10));
        table4.addRow(tableDocumment_2.getRow(11));

        XWPFTable table5 = doc.getTables().get(5);
        table5.getRow(0).getCell(0).setText(numeroLetras.Convertir(numeroLetr, true));

        XWPFTable table6 = doc.getTables().get(6);
        table6.getRow(0).getCell(1).setText("60");

        XWPFTable table7 = doc.getTables().get(7);
        table7.getRow(0).getCell(1).setText(solicitudServicioCliente.getFolioSolitudServicioCliente());

        XWPFTable table8 = doc.getTables().get(8);
        table8.getRow(0).getCell(1).setText(solicitudServicioCliente.getFolioSolitudServicioCliente());

        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Disposition", "inline; filename=FCO-SOC-" + solicitudServicioCliente.getFolioSolitudServicioCliente() + ".docx");
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

    public ResponseEntity<InputStreamResource> crearSolicitudServicioInterno(Long id) throws InvalidFormatException, IOException, XmlException {
        ClassPathResource resource = new ClassPathResource("/documentos/FSI-SOC-006.docx");
        ClassPathResource resource2 = new ClassPathResource("/documentos/plantillaFSI.docx");
        XWPFDocument doc = new XWPFDocument(resource.getInputStream());
        XWPFDocument doc2 = new XWPFDocument(resource2.getInputStream());

        SolicitudServicioCliente solicitudServicioCliente = solicitudServicioClienteRepository.findBySolicitudServicioClienteId(id);
        List<SolicitudServicioClienteMuestras> solicitudServicioClienteMuestrasList = solicitudServicioClienteMuestrasRepository.findAllBySolicitudServicioCliente_SolicitudServicioClienteId(id);
        List<MetodoMuestra> metodoMuestraList = metodoMuestraRepository.findAllBySolicitudServicioClienteMuestras_SolicitudServicioCliente(solicitudServicioCliente);

        //LISTA ORDENADA OK
        Collections.sort(metodoMuestraList, new Comparator<MetodoMuestra>() {
            public int compare(MetodoMuestra metodoMuestra1, MetodoMuestra metodoMuestra2) {
                return metodoMuestra1.getMethod().getBanderaOrden().compareTo(metodoMuestra2.getMethod().getBanderaOrden());
            }
        });

        List<String> sinRepetirCodigo = metodoMuestraList.stream()
                .map(item -> item.getMethod().getCodigoMetodo())
                .distinct()
                .collect(Collectors.toList());

        XWPFTable table0 = doc.getTables().get(0);
        table0.getRow(0).getCell(1).setText(solicitudServicioCliente.getServicioUrgente());
        table0.getRow(0).getCell(3).setText(solicitudServicioCliente.getFolioSolitudServicioCliente());

        XWPFTable table1 = doc.getTables().get(1);
        table1.getRow(0).getCell(1).setText(formatoFechas.formateadorFechas(solicitudServicioCliente.getFechaRecepcionMuestras()));
        table1.getRow(0).getCell(3).setText(formatoFechas.formateadorFechas(solicitudServicioCliente.getFechaCompromisoEntrega()));
        table1.getRow(0).getCell(5).setText(formatoFechas.fechaActual());

        XWPFTable table2 = doc.getTables().get(2);
        table2.getRow(0).getCell(1).setText(solicitudServicioCliente.getNombreFirmaEmisor());
        table2.getRow(0).getCell(3).setText(solicitudServicioCliente.getNombreFirmaReceptor());

        XWPFTable table = doc.getTables().get(3);
        for (int i = 0; i<solicitudServicioClienteMuestrasList.size(); i++) {
            XWPFTableRow tableRow = table.createRow();
            tableRow.getCell(0).setText((i+1)+"");
            RecepcionVerificacionRegistroCodificacion recepcionVerificacionRegistroCodificacion = recepcionVerificacionRegistroCodificacionService.findBySolicitudServicioClienteMuestrasId(solicitudServicioClienteMuestrasList.get(i).getSolicitudServicioClienteMuestrasId());
            tableRow.getCell(1).setText(recepcionVerificacionRegistroCodificacion.getIdInternoMuestra1());
            List<MetodoMuestra> metodoMuestraList1 = metodoMuestraRepository.findAllBySolicitudServicioClienteMuestras_SolicitudServicioClienteMuestrasId(solicitudServicioClienteMuestrasList.get(i).getSolicitudServicioClienteMuestrasId());
            for (int j = 0; j<metodoMuestraList1.size(); j++){
                if (metodoMuestraList1.get(j).getMethod().getCodigoMetodo().equals("MET-AT-001")){
                    tableRow.getCell(2).setText(metodoMuestraList1.get(j).getFolioTecnica());
                }
                if (metodoMuestraList1.get(j).getMethod().getCodigoMetodo().equals("MET-DI-002")){
                    tableRow.getCell(3).setText(metodoMuestraList1.get(j).getFolioTecnica());
                }
                if (metodoMuestraList1.get(j).getMethod().getCodigoMetodo().equals("MET-ES-003")){
                    tableRow.getCell(4).setText(metodoMuestraList1.get(j).getFolioTecnica());
                }
                if (metodoMuestraList1.get(j).getMethod().getCodigoMetodo().equals("MET-GR-004")){
                    tableRow.getCell(5).setText(metodoMuestraList1.get(j).getFolioTecnica());
                }
                if (metodoMuestraList1.get(j).getMethod().getCodigoMetodo().equals("MET-HUM-005")){
                    tableRow.getCell(6).setText(metodoMuestraList1.get(j).getFolioTecnica());
                }
                if (metodoMuestraList1.get(j).getMethod().getCodigoMetodo().equals("MET-NCP-006")){
                    tableRow.getCell(7).setText(metodoMuestraList1.get(j).getFolioTecnica());
                }
                if (metodoMuestraList1.get(j).getMethod().getCodigoMetodo().equals("MET-PPG-007")){
                    tableRow.getCell(8).setText(metodoMuestraList1.get(j).getFolioTecnica());
                }
                if (metodoMuestraList1.get(j).getMethod().getCodigoMetodo().equals("MET-FTIR-008")){
                    tableRow.getCell(9).setText(metodoMuestraList1.get(j).getFolioTecnica());
                }
                if (metodoMuestraList1.get(j).getMethod().getCodigoMetodo().equals("MET-TGA-009")){
                    tableRow.getCell(10).setText(metodoMuestraList1.get(j).getFolioTecnica());
                }
                if (metodoMuestraList1.get(j).getMethod().getCodigoMetodo().equals("MET-ICO-010")){
                    tableRow.getCell(11).setText(metodoMuestraList1.get(j).getFolioTecnica());
                }
                if (metodoMuestraList1.get(j).getMethod().getCodigoMetodo().equals("MET-EAT-011")){
                    tableRow.getCell(12).setText(metodoMuestraList1.get(j).getFolioTecnica());
                }
                if (metodoMuestraList1.get(j).getMethod().getCodigoMetodo().equals("MET-EAUV-012")){
                    tableRow.getCell(13).setText(metodoMuestraList1.get(j).getFolioTecnica());
                }
                if (metodoMuestraList1.get(j).getMethod().getCodigoMetodo().equals("MET-EAXE-013")){
                    tableRow.getCell(14).setText(metodoMuestraList1.get(j).getFolioTecnica());
                }
                if (metodoMuestraList1.get(j).getMethod().getCodigoMetodo().equals("MET-OIT-014")){
                    tableRow.getCell(15).setText(metodoMuestraList1.get(j).getFolioTecnica());
                }
                if (metodoMuestraList1.get(j).getMethod().getCodigoMetodo().equals("MET-DSC-015")){
                    tableRow.getCell(16).setText(metodoMuestraList1.get(j).getFolioTecnica());
                }
                if (metodoMuestraList1.get(j).getMethod().getCodigoMetodo().equals("MET-CST-016")){
                    tableRow.getCell(17).setText(metodoMuestraList1.get(j).getFolioTecnica());
                }
                if (metodoMuestraList1.get(j).getMethod().getCodigoMetodo().equals("MET-IF-017")){
                    tableRow.getCell(18).setText(metodoMuestraList1.get(j).getFolioTecnica());
                }
                if (metodoMuestraList1.get(j).getMethod().getCodigoMetodo().equals("MET-PO-018")){
                    tableRow.getCell(19).setText(metodoMuestraList1.get(j).getFolioTecnica());
                }
                if (metodoMuestraList1.get(j).getMethod().getCodigoMetodo().equals("MET-PRR-019")){
                    tableRow.getCell(20).setText(metodoMuestraList1.get(j).getFolioTecnica());
                }
                if (metodoMuestraList1.get(j).getMethod().getCodigoMetodo().equals("MET-RTER-020")){
                    tableRow.getCell(21).setText(metodoMuestraList1.get(j).getFolioTecnica());
                }
                if (metodoMuestraList1.get(j).getMethod().getCodigoMetodo().equals("MET-RCD-021")){
                    tableRow.getCell(22).setText(metodoMuestraList1.get(j).getFolioTecnica());
                }
                if (metodoMuestraList1.get(j).getMethod().getCodigoMetodo().equals("MET-RI-022")){
                    tableRow.getCell(23).setText(metodoMuestraList1.get(j).getFolioTecnica());
                }
            }
            tableRow.getCell(24).setText(metodoMuestraList.get(i).getSolicitudServicioClienteMuestras().getCondicionesEspeciales());
        }

        XWPFTable table4 = doc.getTables().get(4);
        table4.getRow(0).getCell(1).setText(solicitudServicioCliente.getDevolucionMuestras());

        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Disposition", "inline; filename=FSI_SOC_" + solicitudServicioCliente.getFolioSolitudServicioCliente() + ".docx");
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

    public ResponseEntity<InputStreamResource> crearFormato(Long id) throws InvalidFormatException, IOException, XmlException , IndexOutOfBoundsException{
        ClassPathResource resource2 = new ClassPathResource("/documentos/FIR-ERAI-002.docx");
        ClassPathResource resource = new ClassPathResource("/documentos/FIR-ERAI-003.docx");
        ClassPathResource resource3 = new ClassPathResource("/documentos/test.docx");
        XWPFDocument plantilla = new XWPFDocument(resource2.getInputStream());
        XWPFDocument doc = new XWPFDocument(resource.getInputStream());
        XWPFDocument test = new XWPFDocument(resource3.getInputStream());

        SolicitudServicioCliente solicitudServicioCliente = solicitudServicioClienteRepository.findBySolicitudServicioClienteId(id);
        List<SolicitudServicioClienteMuestras> solicitudServicioClienteMuestrasLista = solicitudServicioClienteMuestrasRepository.findAllBySolicitudServicioCliente_SolicitudServicioClienteId(id);
        List<MetodoMuestra> metodoMuestraList = metodoMuestraRepository.findAllBySolicitudServicioClienteMuestras_SolicitudServicioCliente(solicitudServicioCliente);

        //LISTA ORDENADA OK
        Collections.sort(metodoMuestraList, new Comparator<MetodoMuestra>() {
            public int compare(MetodoMuestra metodoMuestra1, MetodoMuestra metodoMuestra2) {
                return metodoMuestra1.getMethod().getBanderaOrden().compareTo(metodoMuestra2.getMethod().getBanderaOrden());
            }
        });

        List<String> sinRepetirCodigo = metodoMuestraList.stream()
                .map(item -> item.getMethod().getCodigoMetodo())
                .distinct()
                .collect(Collectors.toList());

        List<String> sinRepetirNombre = metodoMuestraList.stream()
                .map(item -> item.getMethod().getNombreMetodo())
                .distinct()
                .collect(Collectors.toList());


        Date fecha1 = null;

        List<String> contactosAux;
        int bandera = 0;
//        try {
//            fecha1 = formatoSQL.parse(solicitudServicioCliente.getFechaRecepcionMuestras());
//        } catch (ParseException ex){
//            System.out.println();
//        }

        XWPFTable table0 = doc.getTables().get(0);
        table0.getRow(0).getCell(1).setText(formatoFechas.formateadorFechas(solicitudServicioCliente.getFechaRecepcionMuestras()));

        table0.getRow(1).getCell(1).setText(formatoFechas.fechaActual());
        table0.getRow(2).getCell(1).setText(solicitudServicioCliente.getFolioSolitudServicioCliente());
        table0.getRow(3).getCell(1).setText(solicitudServicioCliente.getFolioSolitudServicioCliente());


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
            String telefonos = "" + getAttributeContacto(bandera, jsonArray, "telefonoOficina") + ", " + getAttributeContacto(bandera, jsonArray, "telefonoCelular");
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
            RecepcionVerificacionRegistroCodificacion recepcionVerificacionRegistroCodificacion = recepcionVerificacionRegistroCodificacionService.findBySolicitudServicioClienteMuestrasId(solicitudServicioClienteMuestrasLista.get(i).getSolicitudServicioClienteMuestrasId());
            try{
                tableRow.getCell(1).setText(recepcionVerificacionRegistroCodificacion.getIdInternoMuestra1());
            } catch (NullPointerException e){
                tableRow.getCell(1).setText("Falta hacer recepción");
            }
            tableRow.getCell(2).setText(solicitudServicioClienteMuestrasLista.get(i).getTipoMuestra());
            tableRow.getCell(3).setText(solicitudServicioClienteMuestrasLista.get(i).getIdClienteMuestra());
            tableRow.getCell(4).setText(solicitudServicioClienteMuestrasLista.get(i).getDescripcionMuestra());
            tableRow.getCell(5).setText(solicitudServicioClienteMuestrasLista.get(i).getLote());
        }

        XWPFTable table3 = doc.getTables().get(3);
        for (int i = 0; i < sinRepetirCodigo.size(); i++) {
            XWPFTableRow tableRow = table3.createRow();
            tableRow.getCell(0).setText(sinRepetirNombre.get(i));
            tableRow.getCell(1).setText(sinRepetirCodigo.get(i));
            Method method = methodService.findByCodigo(sinRepetirCodigo.get(i));
            tableRow.getCell(2).setText(method.getNormaReferencia());
        }

        int bandAT = 0, bandDI = 0, bandES = 0, bandGR = 0, bandHUM = 0, bandNCP = 0, bandPPG = 0, bandFTIR = 0, bandTGA = 0, bandICI = 0;
        int bandEAT = 0, bandEAUV = 0, bandAEXE = 0, bandOIT = 0, bandDSC = 0, bandCST = 0, bandIF = 0, bandPO = 0, bandPRR = 0;
        int bandRTER = 0, bandRCD = 0, bandRI = 0;
        int contTabla = 4;
        int contador = 0;
        List<FRA_AT_001> listaAT = new ArrayList<FRA_AT_001>();
        List<FRA_DI_001> listaDI = new ArrayList<FRA_DI_001>();
        List<FRA_ES_001> listaES = new ArrayList<FRA_ES_001>();
        List<FRA_GR_001> listaGR = new ArrayList<FRA_GR_001>();
        List<FRA_HUM_001> listaHUM = new ArrayList<FRA_HUM_001>();
        List<FRA_NCP_001> listaNCP = new ArrayList<FRA_NCP_001>();
        List<FRA_PPG_001> listaPPG = new ArrayList<FRA_PPG_001>();
        List<FRA_FTIR_001> listaFTIR = new ArrayList<FRA_FTIR_001>();
        List<FRA_TGA_001> listaTGA = new ArrayList<FRA_TGA_001>();
        List<FRA_ICO_001> listaICO = new ArrayList<FRA_ICO_001>();
        List<FRA_EAT_001> listaEAT = new ArrayList<FRA_EAT_001>();
        List<FRA_EAUV_001> listaEAUV = new ArrayList<FRA_EAUV_001>();
        List<FRA_EAXE_013> listaEAXE = new ArrayList<FRA_EAXE_013>();
        List<FRA_OIT_001> listaOIT = new ArrayList<FRA_OIT_001>();
        List<FRA_DSC> listaDSC = new ArrayList<FRA_DSC>();
        List<FRA_CST_001> listaCST = new ArrayList<FRA_CST_001>();
        List<FRA_IF_001> listaIF = new ArrayList<FRA_IF_001>();
        List<FRA_PO_001> listaPO = new ArrayList<FRA_PO_001>();
        List<FRA_PRR_001> listaPRR = new ArrayList<FRA_PRR_001>();
        List<FRA_RTER_001> listaRTER = new ArrayList<FRA_RTER_001>();
//        List<FRA_RCD_001> listaRCD = new ArrayList<FRA_RCD_001>();
//        List<FRA_RI_001> listaRI = new ArrayList<FRA_RI_001>();


//Tablas, Grfica

        for (int i = 0; i < metodoMuestraList.size(); i++) {
            if (metodoMuestraList.get(i).getMethod().getCodigoMetodo().equals("MET-AT-001") && bandAT == 0) {
                bandAT++;
                for (int j = 0; j < metodoMuestraList.size(); j++) {
                    if (metodoMuestraList.get(j).getMethod().getCodigoMetodo().equals("MET-AT-001")) {
                        listaAT.add(fra_at_001_repository.findByMetodoMuestra_MetodoMuestraId(metodoMuestraList.get(j).getMetodoMuestraId()));
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
                try {
                    tableDocumment.getRow(1).getCell(1).setText(formatoFechas.formateadorFechas(listaAT.get(0).getFechaInicioAnalisis()) + " al " + formatoFechas.formateadorFechas(listaAT.get(0).getFechaFinalAnalisis()));
                    tableDocumment.getRow(1).getCell(3).setText(listaAT.get(0).getTemperatura() + " °C");
                    tableDocumment.getRow(1).getCell(5).setText(listaAT.get(0).getHumedadRelativa() + " %");
                } catch (NullPointerException e) {
                    System.out.println("El ensayo aún no ha sido desarrollado");
                } catch (IndexOutOfBoundsException ex){
                    System.out.println("El ensayo no ha sido desarrollado");
                }
                CTTbl cTTblTemplate = tableDocumment.getCTTbl();
                table = new XWPFTable((CTTbl) cTTblTemplate.copy(), doc);
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
                CTTbl cTTblTemplate_2 = tableDocumment_2.getCTTbl();
                table_2 = new XWPFTable((CTTbl) cTTblTemplate_2.copy(), doc);
                table_2.removeRow(4);
                table_2.removeRow(3);
                table_2.removeRow(2);
                table_2.removeRow(1);
             for (int k = 0; k < contador; k++) {
                    try {
                        XWPFTableRow row = tableDocumment_2.getRow(1);
                        row.getCell(0).setText(listaAT.get(k).getMetodoMuestra().getSolicitudServicioClienteMuestras().getIdClienteMuestra());
                        row.getCell(1).setText(listaAT.get(k).getAtp());
                        table_2.addRow(row);
                    } catch (NullPointerException e) {
                        table_2.addRow(tableDocumment_2.getRow(1));
                    }
                }
                try {
                    XWPFTableRow row2 = tableDocumment_2.getRow(2);
                    row2.getCell(1).setText("¿Qué es la especificación del cliente?");
                    table_2.addRow(row2);

                    XWPFTableRow row3 = tableDocumment_2.getRow(3);
                    row3.getCell(1).setText("¿Qué es la declaración de conformidad?");
                    table_2.addRow(row3);

                    XWPFTableRow row4 = tableDocumment_2.getRow(4);
                    row4.getCell(1).setText(listaAT.get(0).getObservaciones());
                    table_2.addRow(row4);
                } catch (NullPointerException e) {
                    //table_2.addRow(tableDocumment_2.getRow(2));
                    //table_2.addRow(tableDocumment_2.getRow(3));
                    table_2.addRow(tableDocumment_2.getRow(4));
                }


                doc.setTable(contTabla, table_2);

                XWPFParagraph para3 = doc.createParagraph();
                XWPFRun run3 = para3.createRun();
                run3.addBreak();
                contTabla++;
                contador = 0;
            }

            if (metodoMuestraList.get(i).getMethod().getCodigoMetodo().equals("MET-DI-002") && bandDI == 0) {
                bandDI++;
                for (int j = 0; j < metodoMuestraList.size(); j++) {
                    if (metodoMuestraList.get(j).getMethod().getCodigoMetodo().equals("MET-DI-002")) {
                        listaDI.add(fra_di_001_repository.findByMetodoMuestra_MetodoMuestraId(metodoMuestraList.get(j).getMetodoMuestraId()));
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
                try {
                    tableDocumment.getRow(1).getCell(1).setText(formatoFechas.formateadorFechas(listaDI.get(0).getFechaInicioAnalisis()) + " al " + formatoFechas.formateadorFechas(listaDI.get(0).getFechaFinalAnalisis()));
                    tableDocumment.getRow(1).getCell(3).setText("5");
                    tableDocumment.getRow(1).getCell(5).setText(listaDI.get(0).getTemperatura() + " °C");
                    tableDocumment.getRow(1).getCell(7).setText(listaDI.get(0).getHumedadRelativa() + " %");
                } catch (NullPointerException e) {
                    System.out.println("El método no ha sido desarrollado");
                } catch (IndexOutOfBoundsException ex){
                    System.out.println("El ensayo no ha sido desarrollado");
                }

                CTTbl cTTblTemplate = tableDocumment.getCTTbl();
                table = new XWPFTable((CTTbl) cTTblTemplate.copy(), doc);
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
                CTTbl cTTblTemplate_2 = tableDocumment_2.getCTTbl();
                table_2 = new XWPFTable((CTTbl) cTTblTemplate_2.copy(), doc);
                table_2.removeRow(5);
                table_2.removeRow(4);
                table_2.removeRow(3);
                table_2.removeRow(2);
                for (int k = 0; k < contador; k++) {
                    try {
                        XWPFTableRow row = tableDocumment_2.getRow(2);
                        row.getCell(0).setText(listaDI.get(k).getMetodoMuestra().getSolicitudServicioClienteMuestras().getIdClienteMuestra());
                        row.getCell(1).setText(listaDI.get(k).getPromedioLargo());
                        row.getCell(2).setText(listaDI.get(k).getPromedioAncho());
                        row.getCell(3).setText(listaDI.get(k).getSumatoriaFuellePromedio());
                        table_2.addRow(row);
                    } catch (NullPointerException e) {
                        table_2.addRow(tableDocumment_2.getRow(2));
                    }
                }
                try {
                    XWPFTableRow row2 = tableDocumment_2.getRow(3);
                    row2.getCell(1).setText("¿Qué es la especificación del cliente?");
                    table_2.addRow(row2);

                    XWPFTableRow row3 = tableDocumment_2.getRow(4);
                    row3.getCell(1).setText("¿Qué es la declaración de conformidad?");
                    table_2.addRow(row3);

                    XWPFTableRow row4 = tableDocumment_2.getRow(5);
                    row4.getCell(1).setText(listaDI.get(0).getObservaciones());
                    table_2.addRow(row4);
                } catch (NullPointerException e) {
                    //table_2.addRow(tableDocumment_2.getRow(3));
                    //table_2.addRow(tableDocumment_2.getRow(4));
                    table_2.addRow(tableDocumment_2.getRow(5));
                }

                doc.setTable(contTabla, table_2);

                XWPFParagraph para3 = doc.createParagraph();
                XWPFRun run3 = para3.createRun();
                run3.addBreak();
                contTabla++;
                contador = 0;
            }

            if (metodoMuestraList.get(i).getMethod().getCodigoMetodo().equals("MET-ES-003") && bandES == 0) {
                bandES++;
                for (int j = 0; j < metodoMuestraList.size(); j++) {
                    if (metodoMuestraList.get(j).getMethod().getCodigoMetodo().equals("MET-ES-003")) {
                        listaES.add(fra_es_001_repository.findByMetodoMuestra_MetodoMuestraId(metodoMuestraList.get(j).getMetodoMuestraId()));
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
                try {
                    tableDocumment.getRow(1).getCell(1).setText(formatoFechas.formateadorFechas(listaES.get(0).getFechaInicioAnalisis()) + " al " + formatoFechas.formateadorFechas(listaES.get(0).getFechaFinalAnalisis()));
                    tableDocumment.getRow(2).getCell(1).setText("4");
                    tableDocumment.getRow(3).getCell(1).setText("4");
                } catch (NullPointerException e) {
                    System.out.println("El ensayo no ha sido desarrollado");
                } catch (IndexOutOfBoundsException ex){
                    System.out.println("El ensayo no ha sido desarrollado");
                }
                CTTbl cTTblTemplate = tableDocumment.getCTTbl();
                table = new XWPFTable((CTTbl) cTTblTemplate.copy(), doc);
                doc.setTable(contTabla, table);
                XWPFParagraph para1 = doc.createParagraph();
                XWPFRun run1 = para1.createRun();
                run1.addBreak();
                contTabla++;

                XWPFTable table_2 = doc.createTable();
                table_2.removeRow(0); // El default row no es necesario
                XWPFTable tableDocumment_2 = plantilla.getTables().get(9);
                try {
                    tableDocumment_2.getRow(1).getCell(1).setText(listaES.get(0).getTemperatura());
                    tableDocumment_2.getRow(1).getCell(3).setText(listaES.get(0).getHumedadRelativa());
                } catch (NullPointerException e) {
                    System.out.println("El ensayo no ha sido desarrollado");
                } catch (IndexOutOfBoundsException ex){
                    System.out.println("El ensayo no ha sido desarrollado");
                }
                CTTbl cTTblTemplate_2 = tableDocumment_2.getCTTbl();
                table_2 = new XWPFTable((CTTbl) cTTblTemplate_2.copy(), doc);
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
                CTTbl cTTblTemplate_3 = tableDocumment_3.getCTTbl();
                table_3 = new XWPFTable((CTTbl) cTTblTemplate_3.copy(), doc);
                table_3.removeRow(10);
                table_3.removeRow(9);
                table_3.removeRow(8);
                table_3.removeRow(7);
                table_3.removeRow(6);
                table_3.removeRow(5);
                table_3.removeRow(4);
                table_3.removeRow(3);
                table_3.removeRow(2);
                for (int k = 0; k < contador; k++) {
                    try {
                        XWPFTableRow row = tableDocumment_3.getRow(2);
                        row.getCell(0).setText(listaES.get(k).getMetodoMuestra().getSolicitudServicioClienteMuestras().getIdClienteMuestra());
                        row.getCell(1).setText(((Double.parseDouble(listaES.get(k).getPromedioLargo()) * 1000) * 3.937) + "");
                        row.getCell(2).setText((Double.parseDouble(listaES.get(k).getPromedioLargo()) * 1000) + "");
                        row.getCell(3).setText(listaES.get(k).getPromedioLargo());
                        row.getCell(4).setText(listaES.get(k).getMinLargo());
                        row.getCell(5).setText(listaES.get(k).getMaxLargo());
                        row.getCell(6).setText(listaES.get(k).getDesvEstandarLargo());
                        table_3.addRow(row);
                    } catch (NullPointerException e) {
                        table_3.addRow(tableDocumment_3.getRow(2));
                    }
                }
                table_3.addRow(tableDocumment_3.getRow(3));
                table_3.addRow(tableDocumment_3.getRow(4));
                for (int k = 0; k < contador; k++) {
                    try {
                        XWPFTableRow row = tableDocumment_3.getRow(5);
                        row.getCell(0).setText(listaES.get(k).getMetodoMuestra().getSolicitudServicioClienteMuestras().getIdClienteMuestra());
                        row.getCell(1).setText(((Double.parseDouble(listaES.get(k).getPromedioAncho()) * 1000) * 3.937) + "");
                        row.getCell(2).setText((Double.parseDouble(listaES.get(k).getPromedioAncho()) * 1000) + "");
                        row.getCell(3).setText(listaES.get(k).getPromedioAncho());
                        row.getCell(4).setText(listaES.get(k).getMinAncho());
                        row.getCell(5).setText(listaES.get(k).getMaxAncho());
                        row.getCell(6).setText(listaES.get(k).getDesvEstandarAncho());
                        table_3.addRow(row);
                    } catch (NullPointerException e) {
                        table_3.addRow(tableDocumment_3.getRow(5));
                    }
                }
                try {
                    XWPFTableRow row2 = tableDocumment_3.getRow(6);
                    row2.getCell(1).setText("");
                    table_3.addRow(row2);

                    XWPFTableRow row3 = tableDocumment_3.getRow(7);
                    row3.getCell(1).setText("");
                    table_3.addRow(row3);

                    XWPFTableRow row4 = tableDocumment_3.getRow(8);
                    row4.getCell(1).setText("La desviación estandar total del largo es la suma o el promedio?");
                    table_3.addRow(row4);

                    XWPFTableRow row5 = tableDocumment_3.getRow(9);
                    row5.getCell(1).setText("¿La desviación estandar total del ancho es la suma o el promedio??");
                    table_3.addRow(row5);

                    XWPFTableRow row6 = tableDocumment_2.getRow(10);
                    row6.getCell(1).setText("");
                    table_3.addRow(row6);

                } catch (NullPointerException e) {
                    //table_3.addRow(tableDocumment_3.getRow(6));
                    //table_3.addRow(tableDocumment_3.getRow(7));
                    //table_3.addRow(tableDocumment_3.getRow(8));
                    //table_3.addRow(tableDocumment_3.getRow(9));
                    //table_3.addRow(tableDocumment_3.getRow(10));
                }

                doc.setTable(contTabla, table_3);

                XWPFParagraph para4 = doc.createParagraph();
                XWPFRun run4 = para4.createRun();
                run4.addBreak();
                contTabla++;
                contador = 0;

                //TODO: SECCIÓN DE IMAGEN MET-ES-003
            }

            if (metodoMuestraList.get(i).getMethod().getCodigoMetodo().equals("MET-GR-004") && bandGR == 0) {
                bandGR++;
                for (int j = 0; j < metodoMuestraList.size(); j++) {
                    if (metodoMuestraList.get(j).getMethod().getCodigoMetodo().equals("MET-GR-004")) {
                        listaGR.add(fra_gr_001_repository.findByMetodoMuestra_MetodoMuestraId(metodoMuestraList.get(j).getMetodoMuestraId()));
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
                try {
                    tableDocumment.getRow(1).getCell(1).setText(formatoFechas.formateadorFechas(listaGR.get(0).getFechaInicioAnalisis()) + " al " + formatoFechas.formateadorFechas(listaGR.get(0).getFechaFinalAnalisis()));
                    tableDocumment.getRow(1).getCell(3).setText("5");
                    tableDocumment.getRow(1).getCell(5).setText(listaGR.get(0).getTemperatura() + " °C");
                    tableDocumment.getRow(1).getCell(7).setText(listaGR.get(0).getHumedadRelativa() + " %");
                } catch (NullPointerException e) {
                    System.out.println("El método no ha sido desarrollado");
                } catch (IndexOutOfBoundsException ex){
                    System.out.println("El ensayo no ha sido desarrollado");
                }
                CTTbl cTTblTemplate = tableDocumment.getCTTbl();
                table = new XWPFTable((CTTbl) cTTblTemplate.copy(), doc);
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
                CTTbl cTTblTemplate_2 = tableDocumment_2.getCTTbl();
                table_2 = new XWPFTable((CTTbl) cTTblTemplate_2.copy(), doc);
                table_2.removeRow(4);
                table_2.removeRow(3);
                table_2.removeRow(2);
                table_2.removeRow(1);
                for (int k = 0; k < contador; k++) {
                    try {
                        XWPFTableRow row = tableDocumment_2.getRow(1);
                        row.getCell(0).setText(listaGR.get(k).getMetodoMuestra().getSolicitudServicioClienteMuestras().getIdClienteMuestra());
                        row.getCell(1).setText(listaGR.get(k).getGramaje());
                        table_2.addRow(row);
                    } catch (NullPointerException e) {
                        table_2.addRow(tableDocumment_2.getRow(1));
                    }
                }
                try {
                    XWPFTableRow row2 = tableDocumment_2.getRow(2);
                    row2.getCell(1).setText("¿Qué es la especificación del cliente?");
                    table_2.addRow(row2);

                    XWPFTableRow row3 = tableDocumment_2.getRow(3);
                    row3.getCell(1).setText("¿Qué es la declaración de conformidad?");
                    table_2.addRow(row3);

                    XWPFTableRow row4 = tableDocumment_2.getRow(4);
                    row4.getCell(1).setText(listaGR.get(0).getObservaciones());
                    table_2.addRow(row4);

                } catch (NullPointerException e) {
                    //table_2.addRow(tableDocumment_2.getRow(2));
                    //table_2.addRow(tableDocumment_2.getRow(3));
                    table_2.addRow(tableDocumment_2.getRow(4));
                }

                doc.setTable(contTabla, table_2);

                XWPFParagraph para3 = doc.createParagraph();
                XWPFRun run3 = para3.createRun();
                run3.addBreak();
                contTabla++;
                contador = 0;
            }

            if (metodoMuestraList.get(i).getMethod().getCodigoMetodo().equals("MET-HUM-005") && bandHUM == 0) {
                bandHUM++;
                for (int j = 0; j < metodoMuestraList.size(); j++) {
                    if (metodoMuestraList.get(j).getMethod().getCodigoMetodo().equals("MET-HUM-005")) {
                        listaHUM.add(fra_hum_001_repository.findByMetodoMuestra_MetodoMuestraId(metodoMuestraList.get(j).getMetodoMuestraId()));
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
                try {
                    tableDocumment.getRow(1).getCell(1).setText(formatoFechas.formateadorFechas(listaHUM.get(0).getFechaInicioAnalisis()) + " al " + formatoFechas.formateadorFechas(listaHUM.get(0).getFechaFinalAnalisis()));
                    //tableDocumment.getRow(1).getCell(3).setText("5");
                    tableDocumment.getRow(1).getCell(5).setText(listaHUM.get(0).getTemperatura() + " °C");
                    tableDocumment.getRow(1).getCell(7).setText(listaHUM.get(0).getHumedadRelativa() + " %");
                } catch (NullPointerException e) {
                    System.out.println("El ensayo no ha sido desarrollado");
                } catch (IndexOutOfBoundsException ex){
                    System.out.println("El ensayo no ha sido desarrollado");
                }
                CTTbl cTTblTemplate = tableDocumment.getCTTbl();
                table = new XWPFTable((CTTbl) cTTblTemplate.copy(), doc);
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
                CTTbl cTTblTemplate_2 = tableDocumment_2.getCTTbl();
                table_2 = new XWPFTable((CTTbl) cTTblTemplate_2.copy(), doc);
                table_2.removeRow(4);
                table_2.removeRow(3);
                table_2.removeRow(2);
                table_2.removeRow(1);
                for (int k = 0; k < contador; k++) {
                    try {
                        XWPFTableRow row = tableDocumment_2.getRow(1);
                        row.getCell(0).setText(listaHUM.get(k).getMetodoMuestra().getSolicitudServicioClienteMuestras().getIdClienteMuestra());
                        row.getCell(1).setText(listaHUM.get(k).getHumedad());
                    } catch (NullPointerException e) {
                        table_2.addRow(tableDocumment_2.getRow(1));
                    }
                }

                try {
                    XWPFTableRow row2 = tableDocumment_2.getRow(2);
                    row2.getCell(1).setText("¿Qué es la especificación del cliente?");
                    table_2.addRow(row2);

                    XWPFTableRow row3 = tableDocumment_2.getRow(3);
                    row3.getCell(1).setText("¿Qué es la declaración de conformidad?");
                    table_2.addRow(row3);

                    XWPFTableRow row4 = tableDocumment_2.getRow(4);
                    row4.getCell(1).setText(listaHUM.get(0).getObservaciones());
                    table_2.addRow(row4);
                } catch (NullPointerException e) {
                    //table_2.addRow(tableDocumment_2.getRow(2));
                    //table_2.addRow(tableDocumment_2.getRow(3));
                    table_2.addRow(tableDocumment_2.getRow(4));
                }

                doc.setTable(contTabla, table_2);

                XWPFParagraph para3 = doc.createParagraph();
                XWPFRun run3 = para3.createRun();
                run3.addBreak();
                contTabla++;
                contador = 0;
            }

            if (metodoMuestraList.get(i).getMethod().getCodigoMetodo().equals("MET-NCP-006") && bandNCP == 0) {
                bandNCP++;
                for (int j = 0; j < metodoMuestraList.size(); j++) {
                    if (metodoMuestraList.get(j).getMethod().getCodigoMetodo().equals("MET-NCP-006")) {
                        listaNCP.add(fra_ncp_001_repository.findByMetodoMuestra_MetodoMuestraId(metodoMuestraList.get(j).getMetodoMuestraId()));
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
                try {
                    tableDocumment.getRow(1).getCell(1).setText(formatoFechas.formateadorFechas(listaNCP.get(0).getFechaInicioAnalisis()) + " al " + formatoFechas.formateadorFechas(listaNCP.get(0).getFechaFinalAnalisis()));
                    tableDocumment.getRow(1).getCell(3).setText("?");
                    tableDocumment.getRow(1).getCell(5).setText(listaNCP.get(0).getTemperatura() + " °C");
                    tableDocumment.getRow(1).getCell(7).setText(listaNCP.get(0).getHumedadRelativa() + " %");
                } catch (NullPointerException e) {
                    System.out.println("El ensayo no ha sido desarrollado");
                } catch (IndexOutOfBoundsException ex){
                    System.out.println("El ensayo no ha sido desarrollado");
                }
                CTTbl cTTblTemplate = tableDocumment.getCTTbl();
                table = new XWPFTable((CTTbl) cTTblTemplate.copy(), doc);
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
                CTTbl cTTblTemplate_2 = tableDocumment_2.getCTTbl();
                table_2 = new XWPFTable((CTTbl) cTTblTemplate_2.copy(), doc);
                table_2.removeRow(4);
                table_2.removeRow(3);
                table_2.removeRow(2);
                table_2.removeRow(1);
                for (int k = 0; k < contador; k++) {
                    try {
                        XWPFTableRow row = tableDocumment_2.getRow(1);
                        row.getCell(0).setText(listaNCP.get(k).getMetodoMuestra().getSolicitudServicioClienteMuestras().getIdClienteMuestra());
                        row.getCell(1).setText("6");
                        row.getCell(2).setText(listaNCP.get(k).getEspesorTotalMuestra());
                        row.getCell(3).setText(listaNCP.get(k).getTotal());
                    } catch (NullPointerException e) {
                        table_2.addRow(tableDocumment_2.getRow(1));
                    }
                }
                try {
                    XWPFTableRow row2 = tableDocumment_2.getRow(2);
                    row2.getCell(1).setText("¿Qué es la especificación del cliente?");
                    table_2.addRow(row2);

                    XWPFTableRow row3 = tableDocumment_2.getRow(3);
                    row3.getCell(1).setText("¿Qué es la declaración de conformidad?");
                    table_2.addRow(row3);

                    XWPFTableRow row4 = tableDocumment_2.getRow(4);
                    row4.getCell(1).setText(listaNCP.get(0).getObservaciones());
                    table_2.addRow(row4);
                } catch (NullPointerException e) {
                    //table_2.addRow(tableDocumment_2.getRow(2));
                    //table_2.addRow(tableDocumment_2.getRow(3));
                    table_2.addRow(tableDocumment_2.getRow(4));
                }

                doc.setTable(contTabla, table_2);

                XWPFParagraph para3 = doc.createParagraph();
                XWPFRun run3 = para3.createRun();
                run3.addBreak();
                contTabla++;
                contador = 0;

                //TODO: APARTADO DE IMAGEN
            }

            if (metodoMuestraList.get(i).getMethod().getCodigoMetodo().equals("MET-PPG-007") && bandPPG == 0) {
                bandPPG++;
                for (int j = 0; j < metodoMuestraList.size(); j++) {
                    if (metodoMuestraList.get(j).getMethod().getCodigoMetodo().equals("MET-PPG-007")) {
                        listaPPG.add(fra_ppg_001_repository.findByMetodoMuestra_MetodoMuestraId(metodoMuestraList.get(j).getMetodoMuestraId()));
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
                try {
                    tableDocumment.getRow(1).getCell(1).setText(formatoFechas.formateadorFechas(listaPPG.get(0).getFechaInicioAnalisis()) + " al " + formatoFechas.formateadorFechas(listaPPG.get(0).getFechaFinalAnalisis()));
                    tableDocumment.getRow(1).getCell(3).setText(listaPPG.get(0).getTemperatura() + " °C");
                    tableDocumment.getRow(1).getCell(5).setText(listaPPG.get(0).getHumedadRelativa() + " %");
                } catch (NullPointerException e) {
                    System.out.println("El ensayo no ha sido elaborado");
                } catch (IndexOutOfBoundsException ex){
                    System.out.println("El ensayo no ha sido desarrollado");
                }
                CTTbl cTTblTemplate = tableDocumment.getCTTbl();
                table = new XWPFTable((CTTbl) cTTblTemplate.copy(), doc);
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
                CTTbl cTTblTemplate_2 = tableDocumment_2.getCTTbl();
                table_2 = new XWPFTable((CTTbl) cTTblTemplate_2.copy(), doc);
                table_2.removeRow(4);
                table_2.removeRow(3);
                table_2.removeRow(2);
                table_2.removeRow(1);
                for (int k = 0; k < contador; k++) {
                    try {
                        XWPFTableRow row = tableDocumment_2.getRow(1);
                        row.getCell(0).setText(listaPPG.get(k).getMetodoMuestra().getSolicitudServicioClienteMuestras().getIdClienteMuestra());
                        row.getCell(1).setText(listaPPG.get(k).getPelletXGramo());
                    } catch (NullPointerException e) {
                        table_2.addRow(tableDocumment_2.getRow(1));
                    }

                }
                try {
                    XWPFTableRow row2 = tableDocumment_2.getRow(2);
                    row2.getCell(1).setText("¿Qué es la especificación del cliente?");
                    table_2.addRow(row2);

                    XWPFTableRow row3 = tableDocumment_2.getRow(3);
                    row3.getCell(1).setText("¿Qué es la declaración de conformidad?");
                    table_2.addRow(row3);

                    XWPFTableRow row4 = tableDocumment_2.getRow(4);
                    row4.getCell(1).setText(listaPPG.get(0).getObservaciones());
                    table_2.addRow(row4);
                } catch (NullPointerException e) {
                    //table_2.addRow(tableDocumment_2.getRow(2));
                    //table_2.addRow(tableDocumment_2.getRow(3));
                    table_2.addRow(tableDocumment_2.getRow(4));
                }

                doc.setTable(contTabla, table_2);

                XWPFParagraph para3 = doc.createParagraph();
                XWPFRun run3 = para3.createRun();
                run3.addBreak();
                contTabla++;
                contador = 0;
            }

            if (metodoMuestraList.get(i).getMethod().getCodigoMetodo().equals("MET-FTIR-008") && bandFTIR == 0) {
                bandFTIR++;
                for (int j = 0; j < metodoMuestraList.size(); j++) {
                    if (metodoMuestraList.get(j).getMethod().getCodigoMetodo().equals("MET-FTIR-008")) {
                        listaFTIR.add(fra_ftir_001_repository.findByMetodoMuestra_MetodoMuestraId(metodoMuestraList.get(j).getMetodoMuestraId()));
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
                try {
                    tableDocumment.getRow(1).getCell(1).setText(formatoFechas.formateadorFechas(listaFTIR.get(0).getFechaInicioAnalisis()) + " al " + formatoFechas.formateadorFechas(listaFTIR.get(0).getFechaFinalAnalisis()));
                    tableDocumment.getRow(1).getCell(3).setText(listaFTIR.get(0).getTemperatura() + " °C");
                    tableDocumment.getRow(1).getCell(5).setText(listaFTIR.get(0).getHumedadRelativa() + " %");
                } catch (NullPointerException e) {
                    System.out.println("El ensayo no ha sido desarrollado");
                } catch (IndexOutOfBoundsException ex){
                    System.out.println("El ensayo no ha sido desarrollado");
                }
                CTTbl cTTblTemplate = tableDocumment.getCTTbl();
                table = new XWPFTable((CTTbl) cTTblTemplate.copy(), doc);
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
                CTTbl cTTblTemplate_2 = tableDocumment_2.getCTTbl();
                table_2 = new XWPFTable((CTTbl) cTTblTemplate_2.copy(), doc);
                table_2.removeRow(5);
                table_2.removeRow(4);
                table_2.removeRow(3);
                table_2.removeRow(2);
                for (int k = 0; k < contador; k++) {
                    try {
                        XWPFTableRow row = tableDocumment_2.getRow(2);
                        row.getCell(0).setText(listaFTIR.get(k).getMetodoMuestra().getSolicitudServicioClienteMuestras().getIdClienteMuestra());
                        row.getCell(1).setText(listaFTIR.get(k).getCompuesto1());
                        row.getCell(2).setText(listaFTIR.get(k).getIdentidad1());
                        row.getCell(3).setText(listaFTIR.get(k).getCompuesto2());
                        row.getCell(4).setText(listaFTIR.get(k).getIdentidad2());
                    } catch (NullPointerException e) {
                        table_2.addRow(tableDocumment_2.getRow(2));
                    }
                }
                try {
                    XWPFTableRow row2 = tableDocumment_2.getRow(3);
                    row2.getCell(1).setText("¿Qué es la especificación del cliente?");
                    table_2.addRow(row2);

                    XWPFTableRow row3 = tableDocumment_2.getRow(4);
                    row3.getCell(1).setText("¿Qué es la declaración de conformidad?");
                    table_2.addRow(row3);

                    XWPFTableRow row4 = tableDocumment_2.getRow(5);
                    row4.getCell(1).setText(listaFTIR.get(0).getObservaciones());
                    table_2.addRow(row4);
                } catch (NullPointerException e) {
                    //table_2.addRow(tableDocumment_2.getRow(2));
                    //table_2.addRow(tableDocumment_2.getRow(3));
                    table_2.addRow(tableDocumment_2.getRow(4));
                } catch (IndexOutOfBoundsException ex){
                    System.out.println("El ensayo no ha sido desarrollado");
                }

                doc.setTable(contTabla, table_2);

                XWPFParagraph para3 = doc.createParagraph();
                XWPFRun run3 = para3.createRun();
                run3.addBreak();
                run3.setText("Nota: Espectrometría infrarroja por transformada de Fourier.");
                run3.addBreak();
                contTabla++;
                contador = 0;

                //TODO: APARTADO DE IMÁGEN
            }

            if (metodoMuestraList.get(i).getMethod().getCodigoMetodo().equals("MET-TGA-009") && bandTGA == 0) {
                bandTGA++;
                for (int j = 0; j < metodoMuestraList.size(); j++) {
                    if (metodoMuestraList.get(j).getMethod().getCodigoMetodo().equals("MET-TGA-009")) {
                        listaTGA.add(fra_tga_001_repository.findByMetodoMuestra_MetodoMuestraId(metodoMuestraList.get(j).getMetodoMuestraId()));
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
                try {
                    tableDocumment.getRow(1).getCell(1).setText(formatoFechas.formateadorFechas(listaTGA.get(0).getFechaInicioAnalisis()) + " al " + formatoFechas.formateadorFechas(listaTGA.get(0).getFechaFinalAnalisis()));
                } catch (NullPointerException e) {
                    System.out.println("El ensayo no ha sido desarrollado");
                } catch (IndexOutOfBoundsException ex){
                    System.out.println("El ensayo no ha sido desarrollado");
                }
                CTTbl cTTblTemplate = tableDocumment.getCTTbl();
                table = new XWPFTable((CTTbl) cTTblTemplate.copy(), doc);
                doc.setTable(contTabla, table);
                XWPFParagraph para1 = doc.createParagraph();
                XWPFRun run1 = para1.createRun();
                run1.addBreak();
                contTabla++;

                XWPFTable table_2 = doc.createTable();
                table_2.removeRow(0); // El default row no es necesario
                XWPFTable tableDocumment_2 = plantilla.getTables().get(22);
                try {
                    tableDocumment_2.getRow(1).getCell(1).setText(listaTGA.get(0).getTemperatura());
                    tableDocumment_2.getRow(1).getCell(3).setText(listaTGA.get(0).getHumedadRelativa());
                } catch (NullPointerException e) {
                    System.out.println("El ensayo no ha sido desarrollado");
                } catch (IndexOutOfBoundsException ex){
                    System.out.println("El ensayo no ha sido desarrollado");
                }
                CTTbl cTTblTemplate_2 = tableDocumment_2.getCTTbl();
                table_2 = new XWPFTable((CTTbl) cTTblTemplate_2.copy(), doc);
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
                CTTbl cTTblTemplate_3 = tableDocumment_3.getCTTbl();
                table_3 = new XWPFTable((CTTbl) cTTblTemplate_3.copy(), doc);
                table_3.removeRow(4);
                table_3.removeRow(3);
                table_3.removeRow(2);
                table_3.removeRow(1);
                for (int k = 0; k < contador; k++) {
                    try {
                        XWPFTableRow row = tableDocumment_3.getRow(2);
                        row.getCell(0).setText(listaTGA.get(k).getMetodoMuestra().getSolicitudServicioClienteMuestras().getIdClienteMuestra());

                        XWPFParagraph para9 = doc.createParagraph();
                        XWPFRun run9 = para9.createRun();
                        run9.setText("Material altamente volátil: " + listaTGA.get(k).getRangoTemperatura1() + "%");
                        run9.addBreak();
                        run9.setText("Material volátil: " + listaTGA.get(k).getRangoTemperatura2() + "%");
                        run9.addBreak();
                        run9.setText("Material combustible: " + listaTGA.get(k).getRangoTemperatura3() + "%");
                        run9.addBreak();
                        row.getCell(1).setText(para9.getText());

                        XWPFParagraph para8 = doc.createParagraph();
                        XWPFRun run8 = para8.createRun();
                        run8.setText("Material altamente volátil: " + listaTGA.get(k).getCambioMasa1() + "°C");
                        run8.addBreak();
                        run8.setText("Material volátil: " + listaTGA.get(k).getCambioMasa2() + "°C");
                        run8.addBreak();
                        run8.setText("Material combustible: " + listaTGA.get(k).getCambioMasa3() + "°C");
                        run8.addBreak();
                        row.getCell(2).setText(para8.getText());

                        row.getCell(3).setText(listaTGA.get(k).getCambioMasa4());

                        table_3.addRow(row);
                    } catch (NullPointerException e) {
                        table_3.addRow(tableDocumment_3.getRow(1));
                    }
                }
                table_3.addRow(tableDocumment_3.getRow(2));
                table_3.addRow(tableDocumment_3.getRow(3));
                table_3.addRow(tableDocumment_3.getRow(4));

                doc.setTable(contTabla, table_3);

                XWPFParagraph para4 = doc.createParagraph();
                XWPFRun run4 = para4.createRun();
                run4.addBreak();
                contTabla++;
                contador = 0;

                //TODO: SECCIÓN DE IMÁGEN
            }

            if (metodoMuestraList.get(i).getMethod().getCodigoMetodo().equals("MET-ICO-010") && bandICI == 0) {
                bandICI++;
                for (int j = 0; j < metodoMuestraList.size(); j++) {
                    if (metodoMuestraList.get(j).getMethod().getCodigoMetodo().equals("MET-ICI-010")) {
                        listaICO.add(fra_ico_001_repository.findByMetodoMuestra_MetodoMuestraId(metodoMuestraList.get(j).getMetodoMuestraId()));
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
                try{
                    tableDocumment.getRow(1).getCell(1).setText(formatoFechas.formateadorFechas(listaICO.get(0).getFechaInicioAnalisis()) + " al " + formatoFechas.formateadorFechas(listaICO.get(0).getFechaFinalAnalisis()));
                } catch (NullPointerException e){
                    System.out.println("El ensayo no ha sido desarrollado");
                } catch (IndexOutOfBoundsException ex){
                    System.out.println("El ensayo no ha sido desarrollado");
                }
                CTTbl cTTblTemplate = tableDocumment.getCTTbl();
                table = new XWPFTable((CTTbl) cTTblTemplate.copy(), doc);
                doc.setTable(contTabla, table);
                XWPFParagraph para1 = doc.createParagraph();
                XWPFRun run1 = para1.createRun();
                run1.addBreak();
                contTabla++;

                XWPFTable table_2 = doc.createTable();
                table_2.removeRow(0); // El default row no es necesario
                XWPFTable tableDocumment_2 = plantilla.getTables().get(25);
                try{
                    tableDocumment_2.getRow(1).getCell(1).setText(listaICO.get(0).getTemperatura() + "°C");
                    tableDocumment_2.getRow(1).getCell(3).setText(listaICO.get(0).getHumedadRelativa() + "%");
                } catch (NullPointerException e){
                    System.out.println("El ensayo no ha sido desarrollado");
                } catch (IndexOutOfBoundsException ex){
                    System.out.println("El ensayo no ha sido desarrollado");
                }
                CTTbl cTTblTemplate_2 = tableDocumment_2.getCTTbl();
                table_2 = new XWPFTable((CTTbl) cTTblTemplate_2.copy(), doc);
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
                CTTbl cTTblTemplate_3 = tableDocumment_3.getCTTbl();
                table_3 = new XWPFTable((CTTbl) cTTblTemplate_3.copy(), doc);
                table_3.removeRow(4);
                table_3.removeRow(3);
                table_3.removeRow(2);
                table_3.removeRow(1);
                for (int k = 0; k < contador; k++) {
                    try{
                        XWPFTableRow row = tableDocumment_3.getRow(1);
                        row.getCell(0).setText(listaICO.get(k).getMetodoMuestra().getSolicitudServicioClienteMuestras().getIdClienteMuestra());
                        row.getCell(1).setText("De dónde se obtiene el color?");
                        table_3.addRow(row);
                    } catch (NullPointerException e){
                        table_3.addRow(tableDocumment_3.getRow(1));
                    }
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
                contador = 0;
            }

            if (metodoMuestraList.get(i).getMethod().getCodigoMetodo().equals("MET-EAT-011") && bandEAT == 0) {
                bandEAT++;
                for (int j = 0; j < metodoMuestraList.size(); j++) {
                    if (metodoMuestraList.get(j).getMethod().getCodigoMetodo().equals("MET-EAT-011")) {
                        listaEAT.add(fra_eat_001_repository.findByMetodoMuestra_MetodoMuestraId(metodoMuestraList.get(j).getMetodoMuestraId()));
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
                try {
                    tableDocumment.getRow(1).getCell(1).setText(formatoFechas.formateadorFechas(listaEAT.get(0).getFechaInicioAnalisis()) + " al " + formatoFechas.formateadorFechas(listaEAT.get(0).getFechaFinalAnalisis()));
                    tableDocumment.getRow(2).getCell(1).setText(listaEAT.get(0).getTemperaturaEnsayo());
                    tableDocumment.getRow(3).getCell(1).setText("Cantidad muestras");
                } catch (NullPointerException e) {
                    System.out.println("El ensayo no ha sido desarrollado");
                }
                CTTbl cTTblTemplate = tableDocumment.getCTTbl();
                table = new XWPFTable((CTTbl) cTTblTemplate.copy(), doc);
                doc.setTable(contTabla, table);
                XWPFParagraph para1 = doc.createParagraph();
                XWPFRun run1 = para1.createRun();
                run1.addBreak();
                contTabla++;

                XWPFTable table_2 = doc.createTable();
                table_2.removeRow(0); // El default row no es necesario
                XWPFTable tableDocumment_2 = plantilla.getTables().get(28);
                try {
                    tableDocumment_2.getRow(1).getCell(1).setText(listaEAT.get(0).getTemperatura());
                    tableDocumment_2.getRow(1).getCell(3).setText(listaEAT.get(0).getHumedadRelativa());
                } catch (NullPointerException e) {
                    System.out.println("El ensayo no ha sido desarrollado");
                }
                CTTbl cTTblTemplate_2 = tableDocumment_2.getCTTbl();
                table_2 = new XWPFTable((CTTbl) cTTblTemplate_2.copy(), doc);
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
                CTTbl cTTblTemplate_3 = tableDocumment_3.getCTTbl();
                table_3 = new XWPFTable((CTTbl) cTTblTemplate_3.copy(), doc);
                table_3.removeRow(4);
                table_3.removeRow(3);
                table_3.removeRow(2);
                table_3.removeRow(1);
                for (int k = 0; k < contador; k++) {
                    try {
                        XWPFTableRow row = tableDocumment_3.getRow(1);
                        row.getCell(0).setText(listaEAT.get(k).getMetodoMuestra().getSolicitudServicioClienteMuestras().getIdClienteMuestra());
                        row.getCell(1).setText(listaEAT.get(k).getTiempoTotalExposicion());
                        table_3.addRow(row);
                    } catch (NullPointerException e) {
                        table_3.addRow(tableDocumment_3.getRow(1));
                    }
                }
                table_3.addRow(tableDocumment_3.getRow(2));
                table_3.addRow(tableDocumment_3.getRow(3));
                table_3.addRow(tableDocumment_3.getRow(4));

                doc.setTable(contTabla, table_3);

                XWPFParagraph para4 = doc.createParagraph();
                XWPFRun run4 = para4.createRun();
                run4.addBreak();
                contTabla++;
                contador = 0;

                //TODO: APARTADO PARA IMAGEN
            }

            if (metodoMuestraList.get(i).getMethod().getCodigoMetodo().equals("MET-EAUV-012") && bandEAUV == 0) {
                bandEAUV++;
                for (int j = 0; j < metodoMuestraList.size(); j++) {
                    if (metodoMuestraList.get(j).getMethod().getCodigoMetodo().equals("MET-EAUV-012")) {
                        listaEAUV.add(fra_eauv_001_repository.findByMetodoMuestra_MetodoMuestraId(metodoMuestraList.get(j).getMetodoMuestraId()));
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
                try{
                    tableDocumment.getRow(1).getCell(1).setText(formatoFechas.formateadorFechas(listaEAUV.get(0).getFechaInicioAnalisis()) + " al " + formatoFechas.formateadorFechas(listaEAUV.get(0).getFechaFinalAnalisis()));
                } catch (NullPointerException e){
                    System.out.println("El ensayo no ha sido desarrollado");
                }
                CTTbl cTTblTemplate = tableDocumment.getCTTbl();
                table = new XWPFTable((CTTbl) cTTblTemplate.copy(), doc);
                doc.setTable(contTabla, table);
                XWPFParagraph para1 = doc.createParagraph();
                XWPFRun run1 = para1.createRun();
                run1.addBreak();
                bandEAUV++;
                contTabla++;

                XWPFTable table_2 = doc.createTable();
                table_2.removeRow(0); // El default row no es necesario
                XWPFTable tableDocumment_2 = plantilla.getTables().get(31);
                try{
                    tableDocumment_2.getRow(1).getCell(1).setText(listaEAUV.get(0).getTemperatura() + "°C");
                    tableDocumment_2.getRow(1).getCell(3).setText(listaEAUV.get(0).getHumedadRelativa() + "%");
                } catch (NullPointerException e){
                    System.out.println("El ensayo no ha sido desarrollado");
                }
                CTTbl cTTblTemplate_2 = tableDocumment_2.getCTTbl();
                table_2 = new XWPFTable((CTTbl) cTTblTemplate_2.copy(), doc);
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
                CTTbl cTTblTemplate_3 = tableDocumment_3.getCTTbl();
                table_3 = new XWPFTable((CTTbl) cTTblTemplate_3.copy(), doc);
                table_3.removeRow(4);
                table_3.removeRow(3);
                table_3.removeRow(2);
                table_3.removeRow(1);
                for (int k = 0; k < contador; k++) {
                    try{
                        XWPFTableRow row = tableDocumment_3.getRow(1);
                        row.getCell(0).setText(listaEAUV.get(k).getMetodoMuestra().getSolicitudServicioClienteMuestras().getIdClienteMuestra());
                        row.getCell(1).setText(listaEAUV.get(k).getTiempoTotalExposicion());
                        table_3.addRow(row);
                    } catch (NullPointerException e){
                        table_3.addRow(tableDocumment_3.getRow(1));
                    }
                }
                table_3.addRow(tableDocumment_3.getRow(2));
                table_3.addRow(tableDocumment_3.getRow(3));
                table_3.addRow(tableDocumment_3.getRow(4));

                doc.setTable(contTabla, table_3);

                XWPFParagraph para4 = doc.createParagraph();
                XWPFRun run4 = para4.createRun();
                run4.addBreak();

                contTabla++;
                contador = 0;
            }

            if (metodoMuestraList.get(i).getMethod().getCodigoMetodo().equals("MET-EAXE-013") && bandAEXE == 0) {
                bandAEXE++;
                for (int j = 0; j < metodoMuestraList.size(); j++) {
                    if (metodoMuestraList.get(j).getMethod().getCodigoMetodo().equals("MET-AEXE-013")) {
                        listaEAXE.add(fra_eaxe_013_repository.findByMetodoMuestra_MetodoMuestraId(metodoMuestraList.get(j).getMetodoMuestraId()));
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
                try{
                    tableDocumment.getRow(1).getCell(1).setText(formatoFechas.formateadorFechas(listaEAXE.get(0).getFechaInicioAnalisis()) + " al " + formatoFechas.formateadorFechas(listaEAXE.get(0).getFechaFinalAnalisis()));
                } catch (NullPointerException e){
                    System.out.println("El ensayo no ha sido desarrollado");
                } catch (IndexOutOfBoundsException e){
                    System.out.println("El ensayo no ha sido desarrollado");
                }
                CTTbl cTTblTemplate = tableDocumment.getCTTbl();
                table = new XWPFTable((CTTbl) cTTblTemplate.copy(), doc);
                doc.setTable(contTabla, table);
                XWPFParagraph para1 = doc.createParagraph();
                XWPFRun run1 = para1.createRun();
                run1.addBreak();
                bandAEXE++;
                contTabla++;

                XWPFTable table_2 = doc.createTable();
                table_2.removeRow(0); // El default row no es necesario
                XWPFTable tableDocumment_2 = plantilla.getTables().get(34);
                try{
                    tableDocumment_2.getRow(1).getCell(1).setText(listaEAXE.get(0).getTemperatura() + "°C");
                    tableDocumment_2.getRow(1).getCell(3).setText(listaEAXE.get(0).getHumedadRelativa() + "%");
                } catch (NullPointerException e){
                    System.out.println("El ensayo no ha sido desarrollado");
                }
                CTTbl cTTblTemplate_2 = tableDocumment_2.getCTTbl();
                table_2 = new XWPFTable((CTTbl) cTTblTemplate_2.copy(), doc);
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
                CTTbl cTTblTemplate_3 = tableDocumment_3.getCTTbl();
                table_3 = new XWPFTable((CTTbl) cTTblTemplate_3.copy(), doc);
                table_3.removeRow(4);
                table_3.removeRow(3);
                table_3.removeRow(2);
                table_3.removeRow(1);
                for (int k = 0; k < contador; k++) {
                    try{
                        XWPFTableRow row = tableDocumment_3.getRow(1);
                        row.getCell(0).setText(listaEAXE.get(k).getMetodoMuestra().getSolicitudServicioClienteMuestras().getIdClienteMuestra());
                        row.getCell(1).setText(listaEAXE.get(k).getTiempoTotalExposicion());
                        table_3.addRow(row);
                    } catch (NullPointerException e){
                        table_3.addRow(tableDocumment_3.getRow(1));
                    }
                }
                table_3.addRow(tableDocumment_3.getRow(2));
                table_3.addRow(tableDocumment_3.getRow(3));
                table_3.addRow(tableDocumment_3.getRow(4));

                doc.setTable(contTabla, table_3);

                XWPFParagraph para4 = doc.createParagraph();
                XWPFRun run4 = para4.createRun();
                run4.addBreak();

                contTabla++;
                contador = 0;
            }

            if (metodoMuestraList.get(i).getMethod().getCodigoMetodo().equals("MET-OIT-014") && bandOIT == 0) {
                bandOIT++;
                for (int j = 0; j < metodoMuestraList.size(); j++) {
                    if (metodoMuestraList.get(j).getMethod().getCodigoMetodo().equals("MET-OIT-014")) {
                        listaOIT.add(fra_oit_001_repository.findByMetodoMuestra_MetodoMuestraId(metodoMuestraList.get(j).getMetodoMuestraId()));
                        contador++;
                    }
                }

                XWPFStyles newStyles = doc.createStyles();
                newStyles.setStyles(plantilla.getStyle());
                //System.out.println(plantilla.getStyle());
                XWPFParagraph para = doc.createParagraph();
                para.setStyle("Ttulo2");
                XWPFRun run = para.createRun();
                run.setText("Determinación del tiempo de inducción a la oxidación (OIT)");

                XWPFTable table = doc.createTable();
                table.removeRow(0); // El default row no es necesario
                XWPFTable tableDocumment = plantilla.getTables().get(36);
                try{
                    tableDocumment.getRow(1).getCell(1).setText(formatoFechas.formateadorFechas(listaOIT.get(0).getFechaInicioAnalisis()) + " al " + formatoFechas.formateadorFechas(listaOIT.get(0).getFechaFinalAnalisis()));
                } catch (NullPointerException e){
                    System.out.println("El ensayo no ha sido desarrollado");
                }

                CTTbl cTTblTemplate = tableDocumment.getCTTbl();
                table = new XWPFTable((CTTbl) cTTblTemplate.copy(), doc);
                doc.setTable(contTabla, table);
                XWPFParagraph para1 = doc.createParagraph();
                XWPFRun run1 = para1.createRun();
                run1.addBreak();
                bandOIT++;
                contTabla++;

                XWPFTable table_2 = doc.createTable();
                table_2.removeRow(0); // El default row no es necesario
                XWPFTable tableDocumment_2 = plantilla.getTables().get(37);
                try{
                    tableDocumment_2.getRow(1).getCell(1).setText(listaOIT.get(0).getTemperatura() + "°C");
                    tableDocumment_2.getRow(1).getCell(3).setText(listaOIT.get(0).getHumedadRelativa() + "%");
                } catch (NullPointerException e){
                    System.out.println("El ensayo no ha sido desarrollado");
                }
                CTTbl cTTblTemplate_2 = tableDocumment_2.getCTTbl();
                table_2 = new XWPFTable((CTTbl) cTTblTemplate_2.copy(), doc);
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
                CTTbl cTTblTemplate_3 = tableDocumment_3.getCTTbl();
                table_3 = new XWPFTable((CTTbl) cTTblTemplate_3.copy(), doc);
                for (int k = 0; k < contador; k++) {
                    try{
                        XWPFTableRow row = table_3.createRow();
                        row.getCell(0).setText(listaOIT.get(k).getMetodoMuestra().getSolicitudServicioClienteMuestras().getIdClienteMuestra());
                        row.getCell(1).setText(listaOIT.get(k).getPromedio());
                    } catch (NullPointerException e){
                        System.out.println("entró al catch");
                    }
                }
                XWPFTable tableDocumment_4 = plantilla.getTables().get(39);
                table_3.addRow(tableDocumment_4.getRow(0));
                table_3.addRow(tableDocumment_4.getRow(1));
                table_3.addRow(tableDocumment_4.getRow(2));

                doc.setTable(contTabla, table_3);

                XWPFParagraph para4 = doc.createParagraph();
                para4.setAlignment(ParagraphAlignment.LEFT);
                XWPFRun run4 = para4.createRun();
                run4.setFontSize(7);
                run4.setText("Nota: Tiempo de inducción a la oxidación");
                run4.addBreak();

                List<XWPFParagraph> listaParagraph = new ArrayList<>();
                List<XWPFRun> listaRun = new ArrayList<>();
                List<FileInputStream> listaFis = new ArrayList<>();
                List<XWPFPicture> listaPircures = new ArrayList<>();
                List<XWPFParagraph> listaParagraph2 = new ArrayList<>();
                List<XWPFRun> listaRun2 = new ArrayList<>();

                for (int k = 0; k < contador; k++) {
                    try{
                        listaParagraph.add(k, doc.createParagraph());
                        listaParagraph.get(k).setAlignment(ParagraphAlignment.CENTER);
                        listaRun.add(k, listaParagraph.get(k).createRun());
                        listaFis.add(k, new FileInputStream(listaOIT.get(k).getPathImage()));
                        listaPircures.add(k, listaRun.get(k).addPicture(listaFis.get(k), XWPFDocument.PICTURE_TYPE_PNG, "Name", Units.pixelToEMU(430), Units.pixelToEMU(430)));
                        listaParagraph2.add(k, doc.createParagraph());
                        listaParagraph2.get(k).setAlignment(ParagraphAlignment.CENTER);
                        listaParagraph2.get(k).setStyle("Termograma");
                        listaRun2.add(k, listaParagraph2.get(k).createRun());
                        listaRun2.get(k).setText("Indica el tiempo de inducción a la oxidación de la muestra \"" + listaOIT.get(k).getMetodoMuestra().getSolicitudServicioClienteMuestras().getIdClienteMuestra() + "\".");
                    } catch (NullPointerException e){
                        System.out.println("Valida el error");
                    }
                }
                contTabla++;
                contador = 0;
            }

            if (metodoMuestraList.get(i).getMethod().getCodigoMetodo().equals("MET-DSC-015") && bandDSC == 0) {
                bandDSC++;
                for (int j = 0; j < metodoMuestraList.size(); j++) {
                    if (metodoMuestraList.get(j).getMethod().getCodigoMetodo().equals("MET-DSC-015")) {
                        listaDSC.add(fra_dsc_repository.findByMetodoMuestra_MetodoMuestraId(metodoMuestraList.get(j).getMetodoMuestraId()));
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
                try{
                    tableDocumment.getRow(1).getCell(1).setText(formatoFechas.formateadorFechas(listaDSC.get(0).getFechaInicioAnalisis()) + " al " + formatoFechas.formateadorFechas(listaDSC.get(0).getFechaFinalAnalisis()));
                } catch (NullPointerException e){
                    System.out.println("El ensayo no ha sido desarrollado");
                }
                CTTbl cTTblTemplate = tableDocumment.getCTTbl();
                table = new XWPFTable((CTTbl) cTTblTemplate.copy(), doc);
                doc.setTable(contTabla, table);
                XWPFParagraph para1 = doc.createParagraph();
                XWPFRun run1 = para1.createRun();
                run1.addBreak();
                bandDSC++;
                contTabla++;

                XWPFTable table_2 = doc.createTable();
                table_2.removeRow(0); // El default row no es necesario
                XWPFTable tableDocumment_2 = plantilla.getTables().get(40);
                try{
                    tableDocumment_2.getRow(1).getCell(1).setText(listaCST.get(0).getTemperatura() + "°C");
                    tableDocumment_2.getRow(1).getCell(3).setText(listaCST.get(0).getHumedadRelativa() + "%");
                } catch (NullPointerException e){
                    System.out.println("El ensayo no ha sido desarrollado");
                } catch (Exception e){
                    System.out.println("Entró en el exception");
                }
                CTTbl cTTblTemplate_2 = tableDocumment_2.getCTTbl();
                table_2 = new XWPFTable((CTTbl) cTTblTemplate_2.copy(), doc);
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
                CTTbl cTTblTemplate_3 = tableDocumment_3.getCTTbl();
                table_3 = new XWPFTable((CTTbl) cTTblTemplate_3.copy(), doc);
                table_3.removeRow(4);
                table_3.removeRow(3);
                table_3.removeRow(2);
                table_3.removeRow(1);
                for (int k = 0; k < contador; k++) {
                    try{
                        XWPFTableRow row = tableDocumment_3.getRow(1);
                        row.getCell(0).setText(listaDSC.get(k).getMetodoMuestra().getSolicitudServicioClienteMuestras().getIdClienteMuestra());
                        row.getCell(1).setText(listaDSC.get(k).getTemperaturaFusion1());
                        row.getCell(2).setText(listaDSC.get(k).getCalorFusion1());
                        row.getCell(3).setText("?");
                        row.getCell(4).setText(listaDSC.get(k).getTemperaturaCristalizacion1());
                        row.getCell(5).setText(listaDSC.get(k).getCalorCristalizacion1());
                        table_3.addRow(row);
                    } catch (NullPointerException e){
                        table_3.addRow(tableDocumment_3.getRow(1));
                    }
                }
                table_3.addRow(tableDocumment_3.getRow(2));
                table_3.addRow(tableDocumment_3.getRow(3));
                table_3.addRow(tableDocumment_3.getRow(4));

                doc.setTable(contTabla, table_3);

                XWPFParagraph para4 = doc.createParagraph();
                XWPFRun run4 = para4.createRun();
                run4.addBreak();

                contTabla++;
                contador = 0;

                //TODO: APARTADO DE IMAGEN
            }

            if (metodoMuestraList.get(i).getMethod().getCodigoMetodo().equals("MET-CST-016") && bandCST == 0) {
                //TODO: Revisar si este metodo está bien desarrollado
                bandCST++;
                for (int j = 0; j < metodoMuestraList.size(); j++) {
                    if (metodoMuestraList.get(j).getMethod().getCodigoMetodo().equals("MET-CST-016")) {
                        listaCST.add(fra_cst_001_repository.findByMetodoMuestra_MetodoMuestraId(metodoMuestraList.get(j).getMetodoMuestraId()));
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
                try {
                    tableDocumment.getRow(1).getCell(1).setText(formatoFechas.formateadorFechas(listaCST.get(0).getFechaInicioAnalisis()) + " al " + formatoFechas.formateadorFechas(listaCST.get(0).getFechaFinalAnalisis()));
                } catch (NullPointerException e) {
                    System.out.println("El ensayo no ha sido desarrollado");
                }
                CTTbl cTTblTemplate = tableDocumment.getCTTbl();
                table = new XWPFTable((CTTbl) cTTblTemplate.copy(), doc);
                doc.setTable(contTabla, table);
                XWPFParagraph para1 = doc.createParagraph();
                XWPFRun run1 = para1.createRun();
                run1.addBreak();
                bandCST++;
                contTabla++;

                XWPFTable table_2 = doc.createTable();
                table_2.removeRow(0); // El default row no es necesario
                XWPFTable tableDocumment_2 = plantilla.getTables().get(43);
                try {
                    tableDocumment_2.getRow(1).getCell(1).setText(listaCST.get(0).getTemperatura() + "°C");
                    tableDocumment_2.getRow(1).getCell(3).setText(listaCST.get(0).getHumedadRelativa() + "%");
                } catch (NullPointerException e) {
                    System.out.println("El ensayo no ha sido desarrollado");
                }
                CTTbl cTTblTemplate_2 = tableDocumment_2.getCTTbl();
                table_2 = new XWPFTable((CTTbl) cTTblTemplate_2.copy(), doc);
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
                CTTbl cTTblTemplate_3 = tableDocumment_3.getCTTbl();
                table_3 = new XWPFTable((CTTbl) cTTblTemplate_3.copy(), doc);
                table_3.removeRow(4);
                table_3.removeRow(3);
                table_3.removeRow(2);
                table_3.removeRow(1);
                for (int k = 0; k < contador; k++) {
                    try {
                        XWPFTableRow row = tableDocumment_3.getRow(1);
                        row.getCell(0).setText(listaCST.get(k).getMetodoMuestra().getSolicitudServicioClienteMuestras().getIdClienteMuestra());
                        row.getCell(1).setText(listaCST.get(k).getTemperaturaOptima1());
                        row.getCell(2).setText(listaCST.get(k).getFuerzaSello());
                        row.getCell(3).setText(listaCST.get(k).getDesviacionEstandar());
                        table_3.addRow(row);
                    } catch (NullPointerException e) {
                        table_3.addRow(tableDocumment_3.getRow(1));
                    }
                }
                table_3.addRow(tableDocumment_3.getRow(2));
                table_3.addRow(tableDocumment_3.getRow(3));
                table_3.addRow(tableDocumment_3.getRow(4));

                doc.setTable(contTabla, table_3);

                XWPFParagraph para4 = doc.createParagraph();
                XWPFRun run4 = para4.createRun();
                run4.addBreak();

                contTabla++;
                contador = 0;
            }

            if (metodoMuestraList.get(i).getMethod().getCodigoMetodo().equals("MET-IF-017") && bandIF == 0) {
                bandIF++;
                for (int j = 0; j < metodoMuestraList.size(); j++) {
                    if (metodoMuestraList.get(j).getMethod().getCodigoMetodo().equals("MET-IF-017")) {
                        listaIF.add(fra_if_001_repository.findByMetodoMuestra_MetodoMuestraId(metodoMuestraList.get(j).getMetodoMuestraId()));
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
                try {
                    tableDocumment.getRow(1).getCell(1).setText(formatoFechas.formateadorFechas(listaIF.get(0).getFechaInicioAnalisis()) + " al " + formatoFechas.formateadorFechas(listaIF.get(0).getFechaFinalAnalisis()));
                } catch (NullPointerException e) {
                    System.out.println("El ensayo no ha sido desarrollado");
                }
                CTTbl cTTblTemplate = tableDocumment.getCTTbl();
                table = new XWPFTable((CTTbl) cTTblTemplate.copy(), doc);
                doc.setTable(contTabla, table);
                XWPFParagraph para1 = doc.createParagraph();
                XWPFRun run1 = para1.createRun();
                run1.addBreak();
                bandIF++;
                contTabla++;

                XWPFTable table_2 = doc.createTable();
                table_2.removeRow(0); // El default row no es necesario
                XWPFTable tableDocumment_2 = plantilla.getTables().get(46);
                try {
                    tableDocumment_2.getRow(1).getCell(1).setText(listaIF.get(0).getTemperatura() + "°C");
                    tableDocumment_2.getRow(1).getCell(3).setText(listaIF.get(0).getHumedadRelativa() + "%");
                } catch (NullPointerException e) {
                    System.out.println("El ensayo no ha sido desarrollado");
                }
                CTTbl cTTblTemplate_2 = tableDocumment_2.getCTTbl();
                table_2 = new XWPFTable((CTTbl) cTTblTemplate_2.copy(), doc);
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
                CTTbl cTTblTemplate_3 = tableDocumment_3.getCTTbl();
                table_3 = new XWPFTable((CTTbl) cTTblTemplate_3.copy(), doc);
                table_3.removeRow(4);
                table_3.removeRow(3);
                table_3.removeRow(2);
                table_3.removeRow(1);
                for (int k = 0; k < contador; k++) {
                    try {
                        XWPFTableRow row = tableDocumment_3.getRow(1);
                        row.getCell(0).setText(listaIF.get(k).getMetodoMuestra().getSolicitudServicioClienteMuestras().getIdClienteMuestra());
                        row.getCell(1).setText(listaIF.get(k).getIndiceFluidez());
                        row.getCell(1).setText("¿De dónde se obtiene este valor?");
                        table_3.addRow(row);
                    } catch (NullPointerException e) {
                        table_3.addRow(tableDocumment_3.getRow(1));
                    }
                }
                table_3.addRow(tableDocumment_3.getRow(2));
                table_3.addRow(tableDocumment_3.getRow(3));
                table_3.addRow(tableDocumment_3.getRow(4));

                doc.setTable(contTabla, table_3);

                XWPFParagraph para4 = doc.createParagraph();
                XWPFRun run4 = para4.createRun();
                run4.addBreak();

                contTabla++;
                contador = 0;
            }

            if (metodoMuestraList.get(i).getMethod().getCodigoMetodo().equals("MET-PO-018") && bandPO == 0) {
                bandPO++;
                for (int j = 0; j < metodoMuestraList.size(); j++) {
                    if (metodoMuestraList.get(j).getMethod().getCodigoMetodo().equals("MET-PO-018")) {
                        listaPO.add(fra_po_001_repository.findByMetodoMuestra_MetodoMuestraId(metodoMuestraList.get(j).getMetodoMuestraId()));
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
                try {
                    tableDocumment.getRow(1).getCell(1).setText(formatoFechas.formateadorFechas(listaPO.get(0).getFechaInicioAnalisis()) + " al " + formatoFechas.formateadorFechas(listaPO.get(0).getFechaFinalAnalisis()));
                    tableDocumment.getRow(2).getCell(1).setText("?");
                    tableDocumment.getRow(3).getCell(1).setText("?");
                    tableDocumment.getRow(4).getCell(1).setText("?");
                } catch (NullPointerException e) {
                    System.out.println("El ensayo no ha sido desarrollado");
                }
                CTTbl cTTblTemplate = tableDocumment.getCTTbl();
                table = new XWPFTable((CTTbl) cTTblTemplate.copy(), doc);
                doc.setTable(contTabla, table);
                XWPFParagraph para1 = doc.createParagraph();
                XWPFRun run1 = para1.createRun();
                run1.addBreak();
                bandPO++;
                contTabla++;

                XWPFTable table_2 = doc.createTable();
                table_2.removeRow(0); // El default row no es necesario
                XWPFTable tableDocumment_2 = plantilla.getTables().get(49);
                try {
                    tableDocumment_2.getRow(1).getCell(1).setText(listaPO.get(0).getTemperatura() + "°C");
                    tableDocumment_2.getRow(1).getCell(3).setText(listaPO.get(0).getHumedadRelativa() + "%");
                } catch (NullPointerException e) {
                    System.out.println("El ensayo no ha sido desarrollado");
                }
                CTTbl cTTblTemplate_2 = tableDocumment_2.getCTTbl();
                table_2 = new XWPFTable((CTTbl) cTTblTemplate_2.copy(), doc);
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
                CTTbl cTTblTemplate_3 = tableDocumment_3.getCTTbl();
                table_3 = new XWPFTable((CTTbl) cTTblTemplate_3.copy(), doc);
                table_3.removeRow(3);
                table_3.removeRow(2);
                table_3.removeRow(1);
                for (int k = 0; k < contador; k++) {
                    try {
                        XWPFTableRow row = tableDocumment_3.getRow(1);
                        row.getCell(0).setText(listaPO.get(k).getMetodoMuestra().getSolicitudServicioClienteMuestras().getIdClienteMuestra());

                        XWPFParagraph para9 = doc.createParagraph();
                        XWPFRun run9 = para9.createRun();
                        run9.setText("Repetición 1: " + listaPO.get(k).getPermeabilidadOxigeno1());
                        run9.addBreak();
                        run9.setText("Repetición 2: " + listaPO.get(k).getPermeabilidadOxigeno2());

                        row.getCell(1).setText(para9.getText());

                        table_3.addRow(row);
                    } catch (NullPointerException e) {
                        table_3.addRow(tableDocumment_3.getRow(1));
                    }
                }
                table_3.addRow(tableDocumment_3.getRow(2));
                table_3.addRow(tableDocumment_3.getRow(3));

                doc.setTable(contTabla, table_3);

                XWPFParagraph para4 = doc.createParagraph();
                XWPFRun run4 = para4.createRun();
                run4.addBreak();

                contTabla++;
                contador = 0;
            }

            if (metodoMuestraList.get(i).getMethod().getCodigoMetodo().equals("MET-PRR-019") && bandPRR == 0) {
                bandPRR++;
                for (int j = 0; j < metodoMuestraList.size(); j++) {
                    if (metodoMuestraList.get(j).getMethod().getCodigoMetodo().equals("MET-PRR-019")) {
                        listaPRR.add(fra_prr_001_repository.findByMetodoMuestra_MetodoMuestraId(metodoMuestraList.get(j).getMetodoMuestraId()));
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
                try{
                    tableDocumment.getRow(1).getCell(1).setText(formatoFechas.formateadorFechas(listaPRR.get(0).getFechaInicioAnalisis()) + " al " + formatoFechas.formateadorFechas(listaPRR.get(0).getFechaFinalAnalisis()));
                    tableDocumment.getRow(2).getCell(1).setText("?");
                    tableDocumment.getRow(3).getCell(1).setText("3");
                } catch (NullPointerException e){
                    System.out.println("El ensayo no ha sido desarrollado");
                }
                CTTbl cTTblTemplate = tableDocumment.getCTTbl();
                table = new XWPFTable((CTTbl) cTTblTemplate.copy(), doc);
                doc.setTable(contTabla, table);
                XWPFParagraph para1 = doc.createParagraph();
                XWPFRun run1 = para1.createRun();
                run1.addBreak();
                bandPRR++;
                contTabla++;

                XWPFTable table_2 = doc.createTable();
                table_2.removeRow(0); // El default row no es necesario
                XWPFTable tableDocumment_2 = plantilla.getTables().get(52);
                try{
                    tableDocumment_2.getRow(1).getCell(1).setText(listaPRR.get(0).getTemperatura() + "°C");
                    tableDocumment_2.getRow(1).getCell(3).setText(listaPRR.get(0).getHumedadRelativa() + "%");
                } catch (NullPointerException e){
                    System.out.println("El ensayo no ha sido desarrollado");
                }
                CTTbl cTTblTemplate_2 = tableDocumment_2.getCTTbl();
                table_2 = new XWPFTable((CTTbl) cTTblTemplate_2.copy(), doc);
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
                CTTbl cTTblTemplate_3 = tableDocumment_3.getCTTbl();
                table_3 = new XWPFTable((CTTbl) cTTblTemplate_3.copy(), doc);
                table_3.removeRow(7);
                table_3.removeRow(6);
                table_3.removeRow(5);
                table_3.removeRow(4);
                table_3.removeRow(3);
                table_3.removeRow(2);
                for (int k = 0; k < contador; k++) {
                    try{
                        XWPFTableRow row = tableDocumment_3.getRow(2);
                        row.getCell(0).setText(listaPRR.get(k).getMetodoMuestra().getSolicitudServicioClienteMuestras().getIdClienteMuestra());
                        row.getCell(1).setText(listaPRR.get(k).getMDPromedio());
                        row.getCell(2).setText(listaPRR.get(k).getMinMD());
                        row.getCell(3).setText(listaPRR.get(k).getMaxMD());
                        row.getCell(4).setText(listaPRR.get(k).getDesvEstandarMD());
                        row.getCell(5).setText("?");
                        table_3.addRow(row);
                    } catch (NullPointerException e){
                        table_3.addRow(tableDocumment_3.getRow(2));
                    }
                }
                table_3.addRow(tableDocumment_3.getRow(3));
                for (int k = 0; k < contador; k++) {
                    try{
                        XWPFTableRow row = tableDocumment_3.getRow(4);
                        row.getCell(0).setText(listaPRR.get(k).getMetodoMuestra().getSolicitudServicioClienteMuestras().getIdClienteMuestra());
                        row.getCell(1).setText(listaPRR.get(k).getTDPromedio());
                        row.getCell(2).setText(listaPRR.get(k).getMinTD());
                        row.getCell(3).setText(listaPRR.get(k).getMaxTD());
                        row.getCell(4).setText(listaPRR.get(k).getDesvEstandarTD());
                        row.getCell(5).setText("?");
                        table_3.addRow(row);
                    } catch (NullPointerException e){
                        table_3.addRow(tableDocumment_3.getRow(4));
                    }
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
                contador = 0;
            }

            if (metodoMuestraList.get(i).getMethod().getCodigoMetodo().equals("MET-RTER-020") && bandRTER == 0) {
                bandRTER++;
                for (int j = 0; j < metodoMuestraList.size(); j++) {
                    if (metodoMuestraList.get(j).getMethod().getCodigoMetodo().equals("MET-RTER-020")) {
                        listaRTER.add(fra_rter_001_repository.findByMetodoMuestra_MetodoMuestraId(metodoMuestraList.get(j).getMetodoMuestraId()));
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
                try {
                    tableDocumment.getRow(1).getCell(1).setText(formatoFechas.formateadorFechas(listaRTER.get(0).getFechaInicioAnalisis()) + " al " + formatoFechas.formateadorFechas(listaRTER.get(0).getFechaFinalAnalisis()));
                } catch (NullPointerException e) {
                    System.out.println("El ensayo no ha sido desarrollado");
                }
                CTTbl cTTblTemplate = tableDocumment.getCTTbl();
                table = new XWPFTable((CTTbl) cTTblTemplate.copy(), doc);
                doc.setTable(contTabla, table);
                XWPFParagraph para1 = doc.createParagraph();
                XWPFRun run1 = para1.createRun();
                run1.addBreak();
                contTabla++;

                XWPFTable table_2 = doc.createTable();
                table_2.removeRow(0); // El default row no es necesario
                XWPFTable tableDocumment_2 = plantilla.getTables().get(55);
                try {
                    tableDocumment_2.getRow(1).getCell(1).setText(listaRTER.get(0).getTemperatura() + "°C");
                    tableDocumment_2.getRow(1).getCell(3).setText(listaRTER.get(0).getHumedadRelativa() + "%");
                } catch (NullPointerException e) {
                    System.out.println("El ensayo no ha sido desarrollado");
                }
                CTTbl cTTblTemplate_2 = tableDocumment_2.getCTTbl();
                table_2 = new XWPFTable((CTTbl) cTTblTemplate_2.copy(), doc);
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
                CTTbl cTTblTemplate_3 = tableDocumment_3.getCTTbl();
                table_3 = new XWPFTable((CTTbl) cTTblTemplate_3.copy(), doc);
                table_3.removeRow(7);
                table_3.removeRow(6);
                table_3.removeRow(5);
                table_3.removeRow(4);
                table_3.removeRow(3);
                table_3.removeRow(2);
                for (int k = 0; k < contador; k++) {
                    try {
                        XWPFTableRow row = tableDocumment_3.getRow(2);
                        row.getCell(0).setText(listaRTER.get(k).getMetodoMuestra().getSolicitudServicioClienteMuestras().getIdClienteMuestra());
                        row.getCell(1).setText(listaRTER.get(k).getMDpromedioFuerzaFluencia());
                        row.getCell(2).setText(listaRTER.get(k).getMDpromedioElongacionRuptura());
                        row.getCell(3).setText(listaRTER.get(k).getMDpromedioResistenciaTension());
                        row.getCell(4).setText(listaRTER.get(k).getMDpromedioModuloElastico());
                        row.getCell(5).setText(listaRTER.get(k).getMDpromedioEspesor());
                        table_3.addRow(row);
                    } catch (NullPointerException e) {
                        table_3.addRow(tableDocumment_3.getRow(2));
                    }
                }
                table_3.addRow(tableDocumment_3.getRow(3));
                for (int k = 0; k < contador; k++) {
                    try {
                        XWPFTableRow row = tableDocumment_3.getRow(4);
                        row.getCell(0).setText(listaRTER.get(k).getMetodoMuestra().getSolicitudServicioClienteMuestras().getIdClienteMuestra());
                        row.getCell(1).setText(listaRTER.get(k).getTDpromedioFuerzaFluencia());
                        row.getCell(2).setText(listaRTER.get(k).getTDpromedioElongacionRuptura());
                        row.getCell(3).setText(listaRTER.get(k).getTDpromedioResistenciaTension());
                        row.getCell(4).setText(listaRTER.get(k).getTDpromedioModuloElastico());
                        row.getCell(5).setText(listaRTER.get(k).getTDpromedioEspesor());
                        table_3.addRow(row);
                    } catch (NullPointerException e) {
                        table_3.addRow(tableDocumment_3.getRow(4));
                    }
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
                contador = 0;
            }

            if (metodoMuestraList.get(i).getMethod().getCodigoMetodo().equals("MET-RCD-021") && bandRCD == 0) {
                bandRCD++;
                for (int j = 0; j < metodoMuestraList.size(); j++) {
                    if (metodoMuestraList.get(j).getMethod().getCodigoMetodo().equals("MET-RCD-021")) {
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
                CTTbl cTTblTemplate = tableDocumment.getCTTbl();
                table = new XWPFTable((CTTbl) cTTblTemplate.copy(), doc);
                doc.setTable(contTabla, table);
                XWPFParagraph para1 = doc.createParagraph();
                XWPFRun run1 = para1.createRun();
                run1.addBreak();
                contTabla++;

                XWPFTable table_2 = doc.createTable();
                table_2.removeRow(0); // El default row no es necesario
                XWPFTable tableDocumment_2 = plantilla.getTables().get(58);
                CTTbl cTTblTemplate_2 = tableDocumment_2.getCTTbl();
                table_2 = new XWPFTable((CTTbl) cTTblTemplate_2.copy(), doc);
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
                CTTbl cTTblTemplate_3 = tableDocumment_3.getCTTbl();
                table_3 = new XWPFTable((CTTbl) cTTblTemplate_3.copy(), doc);
                table_3.removeRow(4);
                table_3.removeRow(3);
                table_3.removeRow(2);
                table_3.removeRow(1);
                for (int k = 0; k < contador; k++) {
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
                contador = 0;

                //TODO: APARTADO DE IMAGEN
            }

            if (metodoMuestraList.get(i).getMethod().getCodigoMetodo().equals("MET-RI-022") && bandRI == 0) {
                bandRI++;
                for (int j = 0; j < metodoMuestraList.size(); j++) {
                    if (metodoMuestraList.get(j).getMethod().getCodigoMetodo().equals("MET-RI-022")) {
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
                CTTbl cTTblTemplate = tableDocumment.getCTTbl();
                table = new XWPFTable((CTTbl) cTTblTemplate.copy(), doc);
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
                CTTbl cTTblTemplate_3 = tableDocumment_3.getCTTbl();
                table_3 = new XWPFTable((CTTbl) cTTblTemplate_3.copy(), doc);
                table_3.removeRow(4);
                table_3.removeRow(3);
                table_3.removeRow(2);
                table_3.removeRow(1);
                for (int k = 0; k < contador; k++) {
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
                contador = 0;
            }

        }
        XWPFTable table = doc.createTable();
        table.removeRow(0); // El default row no es necesario
        XWPFTable tableDocumment = plantilla.getTables().get(63);
        CTTbl cTTblTemplate = tableDocumment.getCTTbl();
        table = new XWPFTable((CTTbl) cTTblTemplate.copy(), doc);
        doc.setTable(contTabla, table);
        XWPFParagraph para1 = doc.createParagraph();
        XWPFRun run1 = para1.createRun();
        run1.addBreak();
        run1.setText("Dirección donde se realizan las actividades del laboratorio: Calle 21 Este 205 Bodega F, Civac, 62578, Jiutepec, Morelos.");
        run1.setFontFamily("Century Gothic");
        run1.setFontSize(8);

        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Disposition", "inline; filename=FIR_ERAI_" + solicitudServicioCliente.getFolioSolitudServicioCliente() + ".docx");
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

}
