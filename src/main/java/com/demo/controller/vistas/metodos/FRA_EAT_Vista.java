package com.demo.controller.vistas.metodos;

import com.demo.model.operacion.metodos.FRA_EAT_001;
import com.demo.model.operacion.metodos.FRA_EAT_001_DATA;
import com.demo.service.operacion.metodos.FRA_EAT_001_DATA_Service;
import com.demo.service.operacion.metodos.FRA_EAT_001_Service;
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
public class FRA_EAT_Vista {

    @Autowired
    private FRA_EAT_001_Service fra_eat_001_service;

    @Autowired
    private FRA_EAT_001_DATA_Service fra_eat_001_data_service;

    //Muestra formulario de registro inicial
    @RequestMapping(value = "/registerFRAEAT/{id}")
    public String registerFRAEAT(Model model, Principal principal, @PathVariable("id") Long id) {
        User loginedUser = (User) ((Authentication) principal).getPrincipal();
        Collection<GrantedAuthority> review = loginedUser.getAuthorities();

        for (GrantedAuthority a : review) {
            model.addAttribute("role", a.getAuthority());
        }

        return "content/operacion/metodos/FRA_EAT/formFRA_EAT";
    }

    //Muestra formulario de registro de numero e imagen
    @RequestMapping(value = "/agregarFRAEAT/{id}")
    public String agregarFRAEAT(Model model, Principal principal, @PathVariable("id") Long id) {
        User loginedUser = (User) ((Authentication) principal).getPrincipal();
        Collection<GrantedAuthority> review = loginedUser.getAuthorities();

        for (GrantedAuthority a : review) {
            model.addAttribute("role", a.getAuthority());
        }

        return "content/operacion/metodos/FRA_EAT/formFRA_EAT2";
    }

    //Muestra formulario de registro para terminar
    @RequestMapping(value = "/terminarFRAEAT/{id}")
    public String terminarFRAEAT(Model model, Principal principal, @PathVariable("id") Long id) {
        User loginedUser = (User) ((Authentication) principal).getPrincipal();
        Collection<GrantedAuthority> review = loginedUser.getAuthorities();

        for (GrantedAuthority a : review) {
            model.addAttribute("role", a.getAuthority());
        }

        return "content/operacion/metodos/FRA_EAT/formFRA_EAT3";
    }

    //Muestra la lista principal
    @RequestMapping("/listFRAEAT")
    public String listFRAEAT(Model model, Principal principal) {
        User loginedUser = (User) ((Authentication) principal).getPrincipal();
        Collection<GrantedAuthority> review = loginedUser.getAuthorities();

        for (GrantedAuthority a : review) {
            model.addAttribute("role", a.getAuthority());
        }

        List<FRA_EAT_001> lista = fra_eat_001_service.findAll();
        model.addAttribute("fraeat", lista);

        return "content/operacion/metodos/FRA_EAT/listFRA_EAT";
    }

    //Muestra Lo que lleva cada uno
    @RequestMapping(value = "/verFRAEAT/{id}")
    public String verFRAEAT(Model model, Principal principal, @PathVariable("id") Long id) {
        User loginedUser = (User) ((Authentication) principal).getPrincipal();
        Collection<GrantedAuthority> review = loginedUser.getAuthorities();

        for (GrantedAuthority a : review) {
            model.addAttribute("role", a.getAuthority());
        }

        FRA_EAT_001 eat = fra_eat_001_service.findById(id);
        //List<FRA_EAT_001_DATA> lista = fra_eat_001_data_service.findAllBy(eat);
        List<FRA_EAT_001_DATA> lista = fra_eat_001_data_service.findRandPreguntas(id);

        model.addAttribute("eat", eat);
        model.addAttribute("lista", lista);

        return "content/operacion/metodos/FRA_EAT/verFRA_EAT";
    }
}
