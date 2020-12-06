package com.demo.controller.vistas.metodos;

import com.demo.model.operacion.metodos.FRA_EAUV_001;
import com.demo.model.operacion.metodos.FRA_EAUV_001_DATA;
import com.demo.service.operacion.metodos.FRA_EAUV_001_DATA_Service;
import com.demo.service.operacion.metodos.FRA_EAUV_001_Service;
import com.demo.service.operacion.metodos.FRA_EAXE_013_DATA_Service;
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
import java.util.Collection;
import java.util.List;

@Controller
@CrossOrigin
public class FRA_EAUV_Vista {

    @Autowired
    private FRA_EAUV_001_Service fra_eauv_001_service;

    @Autowired
    private FRA_EAUV_001_DATA_Service fra_eauv_001_data_service;

    //Muestra formulario de registro inicial
    @RequestMapping(value = "/registerFRAEAUV/{id}")
    public String registerFRAEAUV(Model model, Principal principal, @PathVariable("id") Long id) {
        User loginedUser = (User) ((Authentication) principal).getPrincipal();
        Collection<GrantedAuthority> review = loginedUser.getAuthorities();

        for (GrantedAuthority a : review) {
            model.addAttribute("role", a.getAuthority());
        }

        return "content/operacion/metodos/FRA_EAUV/formFRA_EAUV";
    }

    //Muestra formulario de registro de numero e imagen
    @RequestMapping(value = "/agregarFRAEAUV/{id}")
    public String agregarFRAEAUV(Model model, Principal principal, @PathVariable("id") Long id) {
        User loginedUser = (User) ((Authentication) principal).getPrincipal();
        Collection<GrantedAuthority> review = loginedUser.getAuthorities();

        for (GrantedAuthority a : review) {
            model.addAttribute("role", a.getAuthority());
        }

        return "content/operacion/metodos/FRA_EAUV/formFRA_EAUV2";
    }

    //Muestra formulario de registro para terminar
    @RequestMapping(value = "/terminarFRAEAUV/{id}")
    public String terminarFRAEAUV(Model model, Principal principal, @PathVariable("id") Long id) {
        User loginedUser = (User) ((Authentication) principal).getPrincipal();
        Collection<GrantedAuthority> review = loginedUser.getAuthorities();

        for (GrantedAuthority a : review) {
            model.addAttribute("role", a.getAuthority());
        }

        return "content/operacion/metodos/FRA_EAUV/formFRA_EAUV3";
    }

    //Muestra la lista principal
    @RequestMapping("/listFRAEAUV")
    public String listFRAEAUV(Model model, Principal principal) {
        User loginedUser = (User) ((Authentication) principal).getPrincipal();
        Collection<GrantedAuthority> review = loginedUser.getAuthorities();

        for (GrantedAuthority a : review) {
            model.addAttribute("role", a.getAuthority());
        }

        List<FRA_EAUV_001> lista = fra_eauv_001_service.findAll();
        model.addAttribute("fraeauv", lista);

        return "content/operacion/metodos/FRA_EAUV/listFRA_EAUV";
    }

    //Muestra Lo que lleva cada uno
    @RequestMapping(value = "/verFRAEAUV/{id}")
    public String verFRAEAUV(Model model, Principal principal, @PathVariable("id") Long id) {
        User loginedUser = (User) ((Authentication) principal).getPrincipal();
        Collection<GrantedAuthority> review = loginedUser.getAuthorities();

        for (GrantedAuthority a : review) {
            model.addAttribute("role", a.getAuthority());
        }

        FRA_EAUV_001 eauv = fra_eauv_001_service.findById(id);
        //List<FRA_EAUV_001_DATA> lista = fra_eauv_001_data_service.findAllBy(eat);
        List<FRA_EAUV_001_DATA> lista = fra_eauv_001_data_service.buscarPorEnsayo(id);

        model.addAttribute("eauv", eauv);
        model.addAttribute("lista", lista);

        return "content/operacion/metodos/FRA_EAUV/verFRA_EAUV";
    }
}
