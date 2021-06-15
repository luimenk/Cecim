package com.demo.controller.vistas.metodos;

import com.demo.model.operacion.MetodoMuestra;
import com.demo.model.operacion.RecepcionVerificacionRegistroCodificacion;
import com.demo.model.operacion.metodos.fra10ico.FRA_ICO_001;
import com.demo.model.operacion.metodos.fra10ico.datas.FRA_ICO_001_DATA;
import com.demo.service.operacion.MetodoMuestraService;
import com.demo.service.operacion.RecepcionVerificacionRegistroCodificacionService;
import com.demo.service.operacion.metodos.FRA_ICO_001_DATA_Service;
import com.demo.service.operacion.metodos.FRA_ICO_001_Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Locale;

@Controller
@CrossOrigin
public class FRA_10_ICO_Vista {

    @Autowired
    private FRA_ICO_001_Service fra_ico_001_service;

    @Autowired
    private FRA_ICO_001_DATA_Service fra_ico_001_data_service;

    @Autowired
    private MetodoMuestraService metodoMuestraService;

    @Autowired
    private RecepcionVerificacionRegistroCodificacionService recepcionVerificacionRegistroCodificacionService;

    //Muestra formulario de registro inicial
    @RequestMapping(value = "/registerFRAICO/{id}")
    public String registerFRAICO(Model model, Principal principal, @PathVariable("id") Long id) {
        User loginedUser = (User) ((Authentication) principal).getPrincipal();
        Collection<GrantedAuthority> review = loginedUser.getAuthorities();

        for (GrantedAuthority a : review) {
            model.addAttribute("role", a.getAuthority());
        }

        MetodoMuestra metodoMuestra = metodoMuestraService.findById(id);
        RecepcionVerificacionRegistroCodificacion recepcionVerificacionRegistroCodificacion = recepcionVerificacionRegistroCodificacionService.findBySolicitudServicioClienteMuestrasId(metodoMuestra.getSolicitudServicioClienteMuestras().getSolicitudServicioClienteMuestrasId());
        Date ahora = new Date();
        SimpleDateFormat ahoraFormato = new SimpleDateFormat("yyyy-MM-dd", new Locale("ES"));
        String fechaHoy = ahoraFormato.format(ahora);

        model.addAttribute("folioSolicitudServicioInterno", metodoMuestra.getSolicitudServicioClienteMuestras().getSolicitudServicioCliente().getFolioSolitudServicioCliente());
        model.addAttribute("idInternoMuestra", recepcionVerificacionRegistroCodificacion.getIdInternoMuestra1());
        model.addAttribute("folioTecnica", metodoMuestra.getFolioTecnica());
        model.addAttribute("fechaHoy", fechaHoy);

        return "content/operacion/metodos/FRA_10_ICO/formFRA_ICO";
    }

    //Muestra formulario de registro de numero e imagen
    @RequestMapping(value = "/agregarFRAICO/{id}")
    public String agregarFRAICO(Model model, Principal principal, @PathVariable("id") Long id) {
        User loginedUser = (User) ((Authentication) principal).getPrincipal();
        Collection<GrantedAuthority> review = loginedUser.getAuthorities();

        for (GrantedAuthority a : review) {
            model.addAttribute("role", a.getAuthority());
        }

        return "content/operacion/metodos/FRA_10_ICO/formFRA_ICO2";
    }

    //Muestra formulario de registro para terminar
    @RequestMapping(value = "/terminarFRAICO/{id}")
    public String terminarFRAICO(Model model, Principal principal, @PathVariable("id") Long id) {
        User loginedUser = (User) ((Authentication) principal).getPrincipal();
        Collection<GrantedAuthority> review = loginedUser.getAuthorities();

        for (GrantedAuthority a : review) {
            model.addAttribute("role", a.getAuthority());
        }

        return "content/operacion/metodos/FRA_10_ICO/formFRA_ICO3";
    }

    //Muestra la lista principal
    @RequestMapping("/listFRAICO")
    public String listFRAICO(Model model, Principal principal) {
        User loginedUser = (User) ((Authentication) principal).getPrincipal();
        Collection<GrantedAuthority> review = loginedUser.getAuthorities();

        for (GrantedAuthority a : review) {
            model.addAttribute("role", a.getAuthority());
        }

        List<FRA_ICO_001> lista = fra_ico_001_service.findAll();
        model.addAttribute("fraico", lista);

        return "content/operacion/metodos/FRA_10_ICO/listFRA_ICO";
    }

    //Muestra Lo que lleva cada uno
    @RequestMapping(value = "/verFRAICO/{id}")
    public String verFRAICO(Model model, Principal principal, @PathVariable("id") Long id) {
        User loginedUser = (User) ((Authentication) principal).getPrincipal();
        Collection<GrantedAuthority> review = loginedUser.getAuthorities();

        for (GrantedAuthority a : review) {
            model.addAttribute("role", a.getAuthority());
        }

        FRA_ICO_001 ico = fra_ico_001_service.findById(id);
        //List<FRA_ICO_001_DATA> lista = fra_ico_001_data_service.findAllBy(ico);
        List<FRA_ICO_001_DATA> lista = fra_ico_001_data_service.findRandPreguntas(id);

        model.addAttribute("ico", ico);
        model.addAttribute("lista", lista);

        return "content/operacion/metodos/FRA_10_ICO/verFRA_ICO";
    }
}
