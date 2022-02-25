package com.demo.service.operacion;

import com.demo.model.Method;
import com.demo.model.operacion.MetodoMuestra;
import com.demo.model.operacion.RecepcionVerificacionRegistroCodificacion;
import com.demo.model.operacion.SolicitudServicioCliente;
import com.demo.model.operacion.SolicitudServicioClienteMuestras;
import com.demo.model.operacion.metodos.*;
import com.demo.model.operacion.metodos.fra01at.FRA_AT_001;
import com.demo.model.operacion.metodos.fra02di.FRA_DI_001;
import com.demo.model.operacion.metodos.fra03es.FRA_ES_001;
import com.demo.model.operacion.metodos.fra04gr.FRA_GR_001;
import com.demo.model.operacion.metodos.fra05hum.FRA_HUM_001;
import com.demo.model.operacion.metodos.fra06ncp.FRA_NCP_001;
import com.demo.model.operacion.metodos.fra07ppg.FRA_PPG_001;
import com.demo.model.operacion.metodos.fra08ftir.FRA_FTIR_001;
import com.demo.model.operacion.metodos.fra09tga.FRA_TGA_001;
import com.demo.model.operacion.metodos.fra10ico.FRA_ICO_001;
import com.demo.model.operacion.metodos.fra11eat.FRA_EAT_001;
import com.demo.model.operacion.metodos.fra12eauv.FRA_EAUV_001;
import com.demo.model.operacion.metodos.fra13eaxe.FRA_EAXE_013;
import com.demo.model.operacion.metodos.fra14oit.FRA_OIT_001;
import com.demo.model.operacion.metodos.fra15dsc.FRA_DSC;
import com.demo.model.operacion.metodos.fra16rsc.FRA_RSC_001;
import com.demo.model.operacion.metodos.fra17if.FRA_IF_001;
import com.demo.model.operacion.metodos.fra18tto.FRA_TTO_001;
import com.demo.model.operacion.metodos.fra19prr.FRA_PRR_001;
import com.demo.model.operacion.metodos.fra20rter.FRA_RTER_001;
import com.demo.repository.operacion.MetodoMuestraRepository;
import com.demo.repository.operacion.SolicitudServicioClienteMuestrasRepository;
import com.demo.repository.operacion.SolicitudServicioClienteRepository;

import com.demo.repository.operacion.metodos.*;
import com.demo.repository.operacion.metodos.fra01at.FRA_AT_001_Repository;
import com.demo.repository.operacion.metodos.fra02di.FRA_DI_001_Repository;
import com.demo.repository.operacion.metodos.fra03es.FRA_ES_001_Repository;
import com.demo.repository.operacion.metodos.fra04gr.FRA_GR_001_Repository;
import com.demo.repository.operacion.metodos.fra05hum.FRA_HUM_001_Repository;
import com.demo.repository.operacion.metodos.fra06ncp.FRA_NCP_001_Repository;
import com.demo.repository.operacion.metodos.fra07ppg.FRA_PPG_001_Repository;
import com.demo.repository.operacion.metodos.fra08ftir.FRA_FTIR_001_Repository;
import com.demo.repository.operacion.metodos.fra09tga.FRA_TGA_001_Repository;
import com.demo.repository.operacion.metodos.fra10ico.FRA_ICO_001_Repository;
import com.demo.repository.operacion.metodos.fra11eat.FRA_EAT_001_Repository;
import com.demo.repository.operacion.metodos.fra12eauv.FRA_EAUV_001_Repository;
import com.demo.repository.operacion.metodos.fra13eaxe.FRA_EAXE_013_Repository;
import com.demo.repository.operacion.metodos.fra14oit.FRA_OIT_001_Repository;
import com.demo.repository.operacion.metodos.fra15dsc.FRA_DSC_Repository;
import com.demo.repository.operacion.metodos.fra16rsc.FRA_RSC_001_Repository;
import com.demo.repository.operacion.metodos.fra17if.FRA_IF_001_Repository;
import com.demo.repository.operacion.metodos.fra18tto.FRA_TTO_001_Repository;
import com.demo.repository.operacion.metodos.fra19prr.FRA_PRR_001_Repository;
import com.demo.repository.operacion.metodos.fra20rter.FRA_RTER_001_Repository;
import com.demo.service.MethodService;
import com.demo.service.formatos.metodos.*;
import com.demo.utils.EstructuraNombres;
import com.demo.utils.FormatoFechas;
import com.demo.utils.Numero_Letras;
import com.demo.utils.WordCopyTableAfterTable;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.util.Units;
import org.apache.poi.xwpf.model.XWPFHeaderFooterPolicy;
import org.apache.poi.xwpf.usermodel.*;
import org.apache.xmlbeans.XmlException;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSectPr;
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
import java.net.URL;
import java.text.DecimalFormat;
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
    private FRA_RSC_001_Repository fra_rsc_001_repository;

    @Autowired
    private FRA_IF_001_Repository fra_if_001_repository;

    @Autowired
    private FRA_TTO_001_Repository fra_tto_001_repository;

    @Autowired
    private FRA_PRR_001_Repository fra_prr_001_repository;

    @Autowired
    private FRA_RTER_001_Repository fra_rter_001_repository;

    @Autowired
    private FRA_01_AT_Print fra_01_at_print;

    @Autowired
    private FRA_02_DI_Print fra_02_di_print;

    @Autowired
    private FRA_03_ES_Print fra_03_es_print;

    @Autowired
    private FRA_04_GR_Print fra_04_gr_print;

    @Autowired
    private FRA_05_HUM_Print fra_05_hum_print;

    @Autowired
    private FRA_06_NCP_Print fra_06_ncp_print;

    @Autowired
    private FRA_07_PPG_Print fra_07_ppg_print;

    @Autowired
    private FRA_08_FTIR_Print fra_08_ftir_print;

    @Autowired
    private FRA_09_TGA_Print fra_09_tga_print;

    @Autowired
    private FRA_10_ICO_Print fra_10_ico_print;

    @Autowired
    private FRA_11_EAT_Print fra_11_eat_print;

    @Autowired
    private FRA_12_EAUV_Print fra_12_eauv_print;

    @Autowired
    private FRA_13_EAXE_Print fra_13_eaxe_print;

    @Autowired
    private FRA_14_OIT_Print fra_14_oit_print;

    @Autowired
    private FRA_15_DSC_Print fra_15_dsc_print;

    @Autowired
    private FRA_16_RSC_Print fra_16_rsc_print;

    @Autowired
    private FRA_17_IF_Print fra_17_if_print;

    @Autowired
    private FRA_18_TTO_Print fra_18_tto_print;

    @Autowired
    private FRA_19_PRR_Print fra_19_prr_print;

    @Autowired
    private FRA_20_RTER_Print fra_20_rter_print;

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

    public List<SolicitudServicioCliente> findAllByEstatusPago(String estatus) {
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

    public ResponseEntity<InputStreamResource> generarListaSolicitud(String filtro) throws InvalidFormatException, IOException {
        ClassPathResource resource = new ClassPathResource("/documentos/LFS-SOC-001.docx");
        XWPFDocument doc = new XWPFDocument(resource.getInputStream());

        List<SolicitudServicioCliente> lista;
        if (filtro.equals("todos")) {
            lista = solicitudServicioClienteRepository.findAll();
        } else {
            lista = solicitudServicioClienteRepository.findAllByEstatusPago(filtro);
        }

        List<String> contactosAux;
        int bandera = 0;

        XWPFTable table = doc.getTables().get(0);
        table.getRow(0).setRepeatHeader(true);
        for (int i = 0; i < lista.size(); i++) {
            XWPFTableRow tableRow = table.createRow();
            tableRow.getCell(0).setText((i + 1) + "");
            try {
                tableRow.getCell(1).setText(formatoFechas.formateadorFechas(lista.get(i).getFechaRecepcionMuestras()));
            } catch (NullPointerException e) {
                System.out.println("No se ha establecido fecha en la solicitud");
            }
            tableRow.getCell(2).setText(lista.get(i).getFolioSolitudServicioCliente());
            tableRow.getCell(3).setText(lista.get(i).getClient().getNombreRazonSocial());
            List<SolicitudServicioClienteMuestras> listaMuestras = solicitudServicioClienteMuestrasRepository.findAllBySolicitudServicioCliente_SolicitudServicioClienteId(lista.get(i).getSolicitudServicioClienteId());
            XWPFParagraph paragraph = tableRow.getCell(4).addParagraph();
            paragraph.setAlignment(ParagraphAlignment.LEFT);
            XWPFRun run = paragraph.createRun();
            for (int j = 0; j < listaMuestras.size(); j++) {
                run.setText(listaMuestras.get(j).getIdClienteMuestra());
                run.addBreak();
            }
            List<MetodoMuestra> metodoMuestraList = metodoMuestraRepository.findAllBySolicitudServicioClienteMuestras_SolicitudServicioCliente(lista.get(i));
            XWPFParagraph paragraph2 = tableRow.getCell(5).addParagraph();
            paragraph2.setAlignment(ParagraphAlignment.LEFT);
            XWPFRun run2 = paragraph2.createRun();
            for (int j = 0; j < metodoMuestraList.size(); j++) {
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
        headers.add("Content-Disposition", "inline; filename=LFS_SOC_" + estructuraNombres.getNombre() + ".docx");
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

    public ResponseEntity<InputStreamResource> generarListaCotizacion() throws InvalidFormatException, IOException {
        ClassPathResource resource = new ClassPathResource("/documentos/LFC-SOC-002.docx");
        XWPFDocument doc = new XWPFDocument(resource.getInputStream());
        List<SolicitudServicioCliente> lista = solicitudServicioClienteRepository.findAll();

        List<String> contactosAux;
        int bandera = 0;

        XWPFTable table = doc.getTables().get(0);
        for (int i = 0; i < lista.size(); i++) {
            XWPFTableRow tableRow = table.createRow();
            tableRow.getCell(0).setText((i + 1) + "");
            tableRow.getCell(1).setText(formatoFechas.formateadorFechas(lista.get(i).getFechaRecepcionMuestras()));
            tableRow.getCell(2).setText(lista.get(i).getFolioSolitudServicioCliente());
            tableRow.getCell(3).setText(lista.get(i).getFolioSolitudServicioCliente());
            tableRow.getCell(4).setText(lista.get(i).getNombreFirmaReceptor());
            tableRow.getCell(5).setText("");
        }

        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Disposition", "inline; filename=LFC-SOC-" + estructuraNombres.getNombre() + ".docx");
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
        for (int i = 0; i < metodos.size(); i++) {
            for (int j = 0; j < metodoMuestraList.size(); j++) {
                if (metodoMuestraList.get(j).getMethod().getCodigoMetodo().equals(metodos.get(i).getCodigoMetodo())) {
                    contadorUnidades++;
                    if (metodoMuestraList.get(j).getSolicitudServicioClienteMuestras().getSolicitudServicioCliente().getServicioUrgente().equals("Si")) {
                        servicioUrgente++;
                        importeIndividual = (importeIndividual + metodoMuestraList.get(j).getMethod().getCostos());
                    } else {
                        importeIndividual = importeIndividual + metodoMuestraList.get(j).getMethod().getCostos();
                    }
                }
            }
            if (contadorUnidades != 0) {
                XWPFTableRow tableRow = table4.createRow();
                tableRow.getCell(0).setText((contIndices) + "");
                tableRow.getCell(1).setText(metodos.get(i).getNombreMetodo());
                tableRow.getCell(2).setText(metodos.get(i).getCodigoMetodo());
                tableRow.getCell(3).setText("$ " + String.format("%.2f", metodos.get(i).getCostos()));
                tableRow.getCell(4).setText(contadorUnidades + "");
                tableRow.getCell(5).setText("$ " + String.format("%.2f", importeIndividual));
                importeTotal = importeTotal + importeIndividual;
                contadorUnidades = 0;
                importeIndividual = 0;
                contIndices++;
            }
        }
        DecimalFormat decimalFormat = new DecimalFormat("#.##");

        XWPFTable tableDocumment_2 = doc2.getTables().get(4);
        if (servicioUrgente != 0) {
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

        URL url = new URL("https://resources.adpmx.com/cecim/laboratorio/doc/register/operacion/FSI-SOC-006.docx");
        XWPFDocument doc = new XWPFDocument(url.openStream());

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
        table2.getRow(0).getCell(1).setText(solicitudServicioCliente.getNombreFirmaReceptor());
        table2.getRow(0).getCell(3).setText("J. Romero");

        XWPFTable table = doc.getTables().get(3);
        for (int i = 0; i < solicitudServicioClienteMuestrasList.size(); i++) {
            XWPFTableRow tableRow = table.createRow();
            tableRow.getCell(0).setText((i + 1) + "");
            RecepcionVerificacionRegistroCodificacion recepcionVerificacionRegistroCodificacion = recepcionVerificacionRegistroCodificacionService.findBySolicitudServicioClienteMuestrasId(solicitudServicioClienteMuestrasList.get(i).getSolicitudServicioClienteMuestrasId());
            tableRow.getCell(1).setText(recepcionVerificacionRegistroCodificacion.getIdInternoMuestra1());
            List<MetodoMuestra> metodoMuestraList1 = metodoMuestraRepository.findAllBySolicitudServicioClienteMuestras_SolicitudServicioClienteMuestrasId(solicitudServicioClienteMuestrasList.get(i).getSolicitudServicioClienteMuestrasId());
            for (int j = 0; j < metodoMuestraList1.size(); j++) {
                if (metodoMuestraList1.get(j).getMethod().getCodigoMetodo().equals("MET-AT-001")) {
                    tableRow.getCell(2).setText(metodoMuestraList1.get(j).getFolioTecnica());
                }
                if (metodoMuestraList1.get(j).getMethod().getCodigoMetodo().equals("MET-DI-002")) {
                    tableRow.getCell(3).setText(metodoMuestraList1.get(j).getFolioTecnica());
                }
                if (metodoMuestraList1.get(j).getMethod().getCodigoMetodo().equals("MET-ES-003")) {
                    tableRow.getCell(4).setText(metodoMuestraList1.get(j).getFolioTecnica());
                }
                if (metodoMuestraList1.get(j).getMethod().getCodigoMetodo().equals("MET-GR-004")) {
                    tableRow.getCell(5).setText(metodoMuestraList1.get(j).getFolioTecnica());
                }
                if (metodoMuestraList1.get(j).getMethod().getCodigoMetodo().equals("MET-HUM-005")) {
                    tableRow.getCell(6).setText(metodoMuestraList1.get(j).getFolioTecnica());
                }
                if (metodoMuestraList1.get(j).getMethod().getCodigoMetodo().equals("MET-NCP-006")) {
                    tableRow.getCell(7).setText(metodoMuestraList1.get(j).getFolioTecnica());
                }
                if (metodoMuestraList1.get(j).getMethod().getCodigoMetodo().equals("MET-PPG-007")) {
                    tableRow.getCell(8).setText(metodoMuestraList1.get(j).getFolioTecnica());
                }
                if (metodoMuestraList1.get(j).getMethod().getCodigoMetodo().equals("MET-FTIR-008")) {
                    tableRow.getCell(9).setText(metodoMuestraList1.get(j).getFolioTecnica());
                }
                if (metodoMuestraList1.get(j).getMethod().getCodigoMetodo().equals("MET-TGA-009")) {
                    tableRow.getCell(10).setText(metodoMuestraList1.get(j).getFolioTecnica());
                }
                if (metodoMuestraList1.get(j).getMethod().getCodigoMetodo().equals("MET-ICO-010")) {
                    tableRow.getCell(11).setText(metodoMuestraList1.get(j).getFolioTecnica());
                }
                if (metodoMuestraList1.get(j).getMethod().getCodigoMetodo().equals("MET-EAT-011")) {
                    tableRow.getCell(12).setText(metodoMuestraList1.get(j).getFolioTecnica());
                }
                if (metodoMuestraList1.get(j).getMethod().getCodigoMetodo().equals("MET-EAUV-012")) {
                    tableRow.getCell(13).setText(metodoMuestraList1.get(j).getFolioTecnica());
                }
                if (metodoMuestraList1.get(j).getMethod().getCodigoMetodo().equals("MET-EAXE-013")) {
                    tableRow.getCell(14).setText(metodoMuestraList1.get(j).getFolioTecnica());
                }
                if (metodoMuestraList1.get(j).getMethod().getCodigoMetodo().equals("MET-OIT-014")) {
                    tableRow.getCell(15).setText(metodoMuestraList1.get(j).getFolioTecnica());
                }
                if (metodoMuestraList1.get(j).getMethod().getCodigoMetodo().equals("MET-DSC-015")) {
                    tableRow.getCell(16).setText(metodoMuestraList1.get(j).getFolioTecnica());
                }
                if (metodoMuestraList1.get(j).getMethod().getCodigoMetodo().equals("MET-RSC-016")) {
                    tableRow.getCell(17).setText(metodoMuestraList1.get(j).getFolioTecnica());
                }
                if (metodoMuestraList1.get(j).getMethod().getCodigoMetodo().equals("MET-IF-017")) {
                    tableRow.getCell(18).setText(metodoMuestraList1.get(j).getFolioTecnica());
                }
                if (metodoMuestraList1.get(j).getMethod().getCodigoMetodo().equals("MET-TTO-018")) {
                    tableRow.getCell(19).setText(metodoMuestraList1.get(j).getFolioTecnica());
                }
                if (metodoMuestraList1.get(j).getMethod().getCodigoMetodo().equals("MET-PRR-019")) {
                    tableRow.getCell(20).setText(metodoMuestraList1.get(j).getFolioTecnica());
                }
                if (metodoMuestraList1.get(j).getMethod().getCodigoMetodo().equals("MET-RTER-020")) {
                    tableRow.getCell(21).setText(metodoMuestraList1.get(j).getFolioTecnica());
                }
                if (metodoMuestraList1.get(j).getMethod().getCodigoMetodo().equals("MET-RCD-021")) {
                    tableRow.getCell(22).setText(metodoMuestraList1.get(j).getFolioTecnica());
                }
                if (metodoMuestraList1.get(j).getMethod().getCodigoMetodo().equals("MET-RI-022")) {
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

    public ResponseEntity<InputStreamResource> crearFormato(Long id) throws InvalidFormatException, IOException, XmlException, IndexOutOfBoundsException {
        URL url = new URL("https://resources.adpmx.com/cecim/laboratorio/doc/register/operacion/FIR-ERAI-003.docx");
        URL url2 = new URL("https://resources.adpmx.com/cecim/laboratorio/doc/register/operacion/FIR-ERAI-002.docx");
        XWPFDocument doc = new XWPFDocument(url.openStream());
        XWPFDocument plantilla = new XWPFDocument(url2.openStream());

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

        /********** INICIO DE CONTROL DE LA TABLA DE LA CABECERA **********/
        CTSectPr sectPr = doc.getDocument().getBody().getSectPr();
        XWPFHeaderFooterPolicy xwpfHeaderFooterPolicy = new XWPFHeaderFooterPolicy(doc, sectPr);

        XWPFHeader xwpfHeader = xwpfHeaderFooterPolicy.getHeader(XWPFHeaderFooterPolicy.DEFAULT);

        XWPFTable tableHeader = xwpfHeader.getTableArray(0);
        tableHeader.getRow(4).getCell(4).setText(solicitudServicioCliente.getFolioSolitudServicioCliente());
        /********** FIN DE CONTROL DE LA TABLA DE LA CABECERA **********/

        XWPFTable table0 = doc.getTables().get(0);
        table0.getRow(0).getCell(1).setText(formatoFechas.formateadorFechas(solicitudServicioCliente.getFechaRecepcionMuestras()));
        table0.getRow(1).getCell(1).setText(formatoFechas.fechaActual());
        table0.getRow(2).getCell(1).setText(solicitudServicioCliente.getFolioSolitudServicioCliente());

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
        XWPFTable tableDocumment_a = plantilla.getTables().get(2);
        table2.getRow(0).setRepeatHeader(true);
        table2.removeRow(3);
        table2.removeRow(2);
        table2.removeRow(1);
        int band = 0;
        String resul = "";
        for (int i = 0; i < solicitudServicioClienteMuestrasLista.size(); i++) {
            XWPFTableRow tableRow = table2.createRow();
            tableRow.getCell(0).setText((i + 1) + "");
            RecepcionVerificacionRegistroCodificacion recepcionVerificacionRegistroCodificacion = recepcionVerificacionRegistroCodificacionService.findBySolicitudServicioClienteMuestrasId(solicitudServicioClienteMuestrasLista.get(i).getSolicitudServicioClienteMuestrasId());
            try {
                tableRow.getCell(1).setText(recepcionVerificacionRegistroCodificacion.getIdInternoMuestra1());
                if ((recepcionVerificacionRegistroCodificacion.getMedioRecepcion().equals("Personal del cliente") || recepcionVerificacionRegistroCodificacion.getMedioRecepcion().equals("Paquetería")) && band == 0) {
                    resul = "Si";
                } else {
                    resul = "No";
                    band = 1;
                }
            } catch (NullPointerException e) {
                tableRow.getCell(1).setText("Falta hacer recepción");
                resul = "No";
            }
            tableRow.getCell(2).setText(solicitudServicioClienteMuestrasLista.get(i).getTipoMuestra());
            tableRow.getCell(3).setText(solicitudServicioClienteMuestrasLista.get(i).getIdClienteMuestra());
            tableRow.getCell(4).setText(solicitudServicioClienteMuestrasLista.get(i).getDescripcionMuestra());
            tableRow.getCell(5).setText(solicitudServicioClienteMuestrasLista.get(i).getLote());
        }

        XWPFTableRow rowa = tableDocumment_a.getRow(2);
        rowa.getCell(2).setText(resul);
        table2.addRow(rowa);

        try {
            XWPFTableRow rowb = tableDocumment_a.getRow(3);
            rowb.getCell(1).setText(solicitudServicioClienteMuestrasLista.get(0).getObservaciones());
            table2.addRow(rowb);
        } catch (NullPointerException e) {
            XWPFTableRow row3 = tableDocumment_a.getRow(3);
            row3.getCell(1).setText("N/A");
            table2.addRow(row3);
        }


        XWPFTable table3 = doc.getTables().get(3);
        table3.getRow(0).setRepeatHeader(true);
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
        List<FRA_RSC_001> listaRSC = new ArrayList<FRA_RSC_001>();
        List<FRA_IF_001> listaIF = new ArrayList<FRA_IF_001>();
        List<FRA_TTO_001> listaTTO = new ArrayList<FRA_TTO_001>();
        List<FRA_PRR_001> listaPRR = new ArrayList<FRA_PRR_001>();
        List<FRA_RTER_001> listaRTER = new ArrayList<FRA_RTER_001>();
//        List<FRA_RCD_001> listaRCD = new ArrayList<FRA_RCD_001>();
//        List<FRA_RI_001> listaRI = new ArrayList<FRA_RI_001>();
        for (int i = 0; i < metodoMuestraList.size(); i++) {

            if (metodoMuestraList.get(i).getMethod().getCodigoMetodo().equals("MET-AT-001") && bandAT == 0) {
                bandAT++;
                for (int j = 0; j < metodoMuestraList.size(); j++) {
                    if (metodoMuestraList.get(j).getMethod().getCodigoMetodo().equals("MET-AT-001")) {
                        listaAT.add(fra_at_001_repository.findByMetodoMuestra_MetodoMuestraId(metodoMuestraList.get(j).getMetodoMuestraId()));
                        contador++;
                    }
                }

                contTabla = fra_01_at_print.fragmentoReporte(doc, plantilla, contTabla, contador, listaAT);
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

                contTabla = fra_02_di_print.fragmentoReporte(doc, plantilla, contTabla, contador, listaDI);
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

                contTabla = fra_03_es_print.fragmentoReporte(doc, plantilla, contTabla, contador, listaES);
                contador = 0;
            }

            if (metodoMuestraList.get(i).getMethod().getCodigoMetodo().equals("MET-GR-004") && bandGR == 0) {
                bandGR++;
                for (int j = 0; j < metodoMuestraList.size(); j++) {
                    if (metodoMuestraList.get(j).getMethod().getCodigoMetodo().equals("MET-GR-004")) {
                        listaGR.add(fra_gr_001_repository.findByMetodoMuestra_MetodoMuestraId(metodoMuestraList.get(j).getMetodoMuestraId()));
                        contador++;
                    }
                }

                contTabla = fra_04_gr_print.fragmentoReporte(doc, plantilla, contTabla, contador, listaGR);
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

                contTabla = fra_05_hum_print.fragmentoReporte(doc, plantilla, contTabla, contador, listaHUM);
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

                contTabla = fra_06_ncp_print.fragmentoReporte(doc, plantilla, contTabla, contador, listaNCP);
                contador = 0;
            }

            if (metodoMuestraList.get(i).getMethod().getCodigoMetodo().equals("MET-PPG-007") && bandPPG == 0) {
                bandPPG++;
                for (int j = 0; j < metodoMuestraList.size(); j++) {
                    if (metodoMuestraList.get(j).getMethod().getCodigoMetodo().equals("MET-PPG-007")) {
                        listaPPG.add(fra_ppg_001_repository.findByMetodoMuestra_MetodoMuestraId(metodoMuestraList.get(j).getMetodoMuestraId()));
                        contador++;
                    }
                }

                contTabla = fra_07_ppg_print.fragmentoReporte(doc, plantilla, contTabla, contador, listaPPG);
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

                contTabla = fra_08_ftir_print.fragmentoReporte(doc, plantilla, contTabla, contador, listaFTIR);
                contador = 0;
            }

            if (metodoMuestraList.get(i).getMethod().getCodigoMetodo().equals("MET-TGA-009") && bandTGA == 0) {
                bandTGA++;
                for (int j = 0; j < metodoMuestraList.size(); j++) {
                    if (metodoMuestraList.get(j).getMethod().getCodigoMetodo().equals("MET-TGA-009")) {
                        listaTGA.add(fra_tga_001_repository.findByMetodoMuestra_MetodoMuestraId(metodoMuestraList.get(j).getMetodoMuestraId()));
                        contador++;
                    }
                }

                contTabla = fra_09_tga_print.fragmentoReporte(doc, plantilla, contTabla, contador, listaTGA);
                contador = 0;
            }

            if (metodoMuestraList.get(i).getMethod().getCodigoMetodo().equals("MET-ICO-010") && bandICI == 0) {
                bandICI++;
                for (int j = 0; j < metodoMuestraList.size(); j++) {
                    if (metodoMuestraList.get(j).getMethod().getCodigoMetodo().equals("MET-ICO-010")) {
                        listaICO.add(fra_ico_001_repository.findByMetodoMuestra_MetodoMuestraId(metodoMuestraList.get(j).getMetodoMuestraId()));
                        contador++;
                    }
                }

                contTabla = fra_10_ico_print.fragmentoReporte(doc, plantilla, contTabla, contador, listaICO);
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

                contTabla = fra_11_eat_print.fragmentoReporte(doc, plantilla, contTabla, contador, listaEAT);
                contador = 0;
            }

            if (metodoMuestraList.get(i).getMethod().getCodigoMetodo().equals("MET-EAUV-012") && bandEAUV == 0) {
                bandEAUV++;
                for (int j = 0; j < metodoMuestraList.size(); j++) {
                    if (metodoMuestraList.get(j).getMethod().getCodigoMetodo().equals("MET-EAUV-012")) {
                        listaEAUV.add(fra_eauv_001_repository.findByMetodoMuestra_MetodoMuestraId(metodoMuestraList.get(j).getMetodoMuestraId()));
                        contador++;
                    }
                }

                contTabla = fra_12_eauv_print.fragmentoReporte(doc, plantilla, contTabla, contador, listaEAUV);
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

                contTabla = fra_13_eaxe_print.fragmentoReporte(doc, plantilla, contTabla, contador, listaEAXE);
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

                contTabla = fra_14_oit_print.fragmentoReporte(doc, plantilla, contTabla, contador, listaOIT);
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

                contTabla = fra_15_dsc_print.fragmentoReporte(doc, plantilla, contTabla, contador, listaDSC);
                contador = 0;

            }

            if (metodoMuestraList.get(i).getMethod().getCodigoMetodo().equals("MET-RSC-016") && bandCST == 0) {
                bandCST++;
                for (int j = 0; j < metodoMuestraList.size(); j++) {
                    if (metodoMuestraList.get(j).getMethod().getCodigoMetodo().equals("MET-RSC-016")) {
                        listaRSC.add(fra_rsc_001_repository.findByMetodoMuestra_MetodoMuestraId(metodoMuestraList.get(j).getMetodoMuestraId()));
                        contador++;
                    }
                }

                contTabla = fra_16_rsc_print.fragmentoReporte(doc, plantilla, contTabla, contador, listaRSC);
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

                contTabla = fra_17_if_print.fragmentoReporte(doc, plantilla, contTabla, contador, listaIF);
                contador = 0;
            }

            if (metodoMuestraList.get(i).getMethod().getCodigoMetodo().equals("MET-TTO-018") && bandPO == 0) {
                bandPO++;
                for (int j = 0; j < metodoMuestraList.size(); j++) {
                    if (metodoMuestraList.get(j).getMethod().getCodigoMetodo().equals("MET-TTO-018")) {
                        listaTTO.add(fra_tto_001_repository.findByMetodoMuestra_MetodoMuestraId(metodoMuestraList.get(j).getMetodoMuestraId()));
                        contador++;
                    }
                }

                contTabla = fra_18_tto_print.fragmentoReporte(doc, plantilla, contTabla, contador, listaTTO);
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

                contTabla = fra_19_prr_print.fragmentoReporte(doc, plantilla, contTabla, contador, listaPRR);
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

                contTabla = fra_20_rter_print.fragmentoReporte(doc, plantilla, contTabla, contador, listaRTER);
                contador = 0;
            }
        }

//Tablas, Grfica


        XWPFTable table = doc.createTable();
        table.removeRow(0); // El default row no es necesario
        XWPFTable tableDocumment = plantilla.getTables().get(89);
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
