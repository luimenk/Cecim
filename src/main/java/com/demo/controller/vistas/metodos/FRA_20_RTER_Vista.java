package com.demo.controller.vistas.metodos;

import com.demo.model.operacion.MetodoMuestra;
import com.demo.model.operacion.RecepcionVerificacionRegistroCodificacion;
import com.demo.model.operacion.metodos.fra20rter.FRA_RTER_001;
import com.demo.model.operacion.metodos.fra20rter.datas.FRA_RTER_001_DATA_01;
import com.demo.model.operacion.metodos.fra20rter.datas.FRA_RTER_001_DATA_02;
import com.demo.repository.operacion.metodos.fra20rter.datas.FRA_RTER_001_DATA_01_Repository;
import com.demo.repository.operacion.metodos.fra20rter.datas.FRA_RTER_001_DATA_02_Repository;
import com.demo.service.operacion.MetodoMuestraService;
import com.demo.service.operacion.RecepcionVerificacionRegistroCodificacionService;
import com.demo.service.operacion.metodos.FRA_RTER_001_Service;
import com.demo.utils.WebUtils;
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
public class FRA_20_RTER_Vista {

    @Autowired
    private FRA_RTER_001_Service fra_rter_001_service;

    @Autowired
    private FRA_RTER_001_DATA_01_Repository fra_rter_001_data_01_repository;

    @Autowired
    private FRA_RTER_001_DATA_02_Repository fra_rter_001_data_02_repository;

    @Autowired
    private MetodoMuestraService metodoMuestraService;

    @Autowired
    private RecepcionVerificacionRegistroCodificacionService recepcionVerificacionRegistroCodificacionService;

    @RequestMapping(value = "/registerFRARTER/{id}")
    public String registerFRARTER(Model model, Principal principal, @PathVariable("id") Long id) {

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

        return "content/operacion/metodos/FRA_20_RTER/formFRA_RTER";
    }

    //Muestra formulario de registro de numero e imagen
    @RequestMapping(value = "/agregarFRARTER/{id}")
    public String agregarFRAEAT(Model model, Principal principal, @PathVariable("id") Long id) {
        User loginedUser = (User) ((Authentication) principal).getPrincipal();
        Collection<GrantedAuthority> review = loginedUser.getAuthorities();

        for (GrantedAuthority a : review) {
            model.addAttribute("role", a.getAuthority());
        }

        return "content/operacion/metodos/FRA_20_RTER/formFRA_RTER2";
    }

    //Muestra formulario de registro para terminar
    @RequestMapping(value = "/terminarFRARTER/{id}")
    public String terminarFRAEAT(Model model, Principal principal, @PathVariable("id") Long id) {
        User loginedUser = (User) ((Authentication) principal).getPrincipal();
        Collection<GrantedAuthority> review = loginedUser.getAuthorities();

        for (GrantedAuthority a : review) {
            model.addAttribute("role", a.getAuthority());
        }

        return "content/operacion/metodos/FRA_20_RTER/formFRA_RTER3";
    }

    @RequestMapping("/listFRARTER")
    public String listFRAGR(Model model, Principal principal) {
        // After user login successfully.
        String userName = principal.getName();

        User loginedUser = (User) ((Authentication) principal).getPrincipal();

        String userInfo = WebUtils.toString(loginedUser);

        Collection<GrantedAuthority> review = loginedUser.getAuthorities();

        model.addAttribute("userName", userName);
        model.addAttribute("userInfo", userInfo);

        for (GrantedAuthority a : review) {
            model.addAttribute("role", a.getAuthority());
        }

        return "content/operacion/metodos/FRA_20_RTER/listFRA_RTER";
    }

    //Muestra Lo que lleva cada uno
    @RequestMapping(value = "/verFRARTER/{id}")
    public String verFRAEAT(Model model, Principal principal, @PathVariable("id") Long id) {
        User loginedUser = (User) ((Authentication) principal).getPrincipal();
        Collection<GrantedAuthority> review = loginedUser.getAuthorities();

        for (GrantedAuthority a : review) {
            model.addAttribute("role", a.getAuthority());
        }

        FRA_RTER_001 rter = fra_rter_001_service.findById(id);
        List<FRA_RTER_001_DATA_01> lista = fra_rter_001_data_01_repository.buscarTodosPorEnsayo(id);
        List<FRA_RTER_001_DATA_02> lista2 = fra_rter_001_data_02_repository.buscarTodosPorEnsayo(id);

        model.addAttribute("rter", rter);
        model.addAttribute("lista", lista);
        model.addAttribute("lista2", lista2);

        return "content/operacion/metodos/FRA_20_RTER/verFRA_RTER";
    }
}
