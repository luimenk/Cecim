package com.demo.service;

import com.demo.model.Folios;
import com.demo.repository.FoliosRepository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

@Service
public class FoliosService {

    @Autowired
    private FoliosRepository foliosRepository;

    private static final Logger LOGGER = LoggerFactory.getLogger("info");

    private static final Logger APP = LoggerFactory.getLogger("info");

    public Folios save(Folios folios) {
        return foliosRepository.save(folios);
    }

    public List<Folios> findAll() {
        return foliosRepository.findAll();
    }

    public Folios findByFolioId(Long foliosId) {
        return foliosRepository.findByFolioId(foliosId);
    }

    public Folios findByNombreFolio(String nombre) {
        return foliosRepository.findByNombreFolio(nombre);
    }

    public String folioSolicitudServicioCliente() {
        Folios folio = foliosRepository.findByNombreFolio("SolicitudServicioCliente");

        return estructuraFolio(folio);
    }

    public String estructuraFolio(Folios folio){
        String structureFolio, dia, mes, nombre, consecutivo;
        int cont, anio, me, di;
        Calendar calendario = new GregorianCalendar();

        if (folio.getConsecutivo().length() == 1) {
            consecutivo = "000"+folio.getConsecutivo();
        } else if (folio.getConsecutivo().length() == 2) {
            consecutivo = "00"+folio.getConsecutivo();
        } else if (folio.getConsecutivo().length() == 3) {
            consecutivo = "0"+folio.getConsecutivo();
        } else {
            consecutivo = folio.getConsecutivo();
        }

        anio = calendario.get(Calendar.YEAR);
        me = calendario.get(Calendar.MONTH);
        di = calendario.get(Calendar.DAY_OF_MONTH);
        me++;
        if (di < 10) {
            dia = "0" + di;
        } else {
            dia = "" + di;
        }
        if (me < 10) {
            mes = "0" + me;
        } else {
            mes = "" + me;
        }

        nombre = "" + mes + dia + anio + "_";

        structureFolio = nombre+consecutivo;

        cont = Integer.parseInt(folio.getConsecutivo());
        cont++;
        folio.setConsecutivo(cont+"");
        foliosRepository.save(folio);

        return structureFolio;
    }
}
