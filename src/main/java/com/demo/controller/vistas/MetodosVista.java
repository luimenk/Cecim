package com.demo.controller.vistas;

import com.demo.model.Client;
import com.demo.model.Method;
import com.demo.utils.WebUtils;
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
public class MetodosVista {

    @RequestMapping(value = "/registerFRAIF/{id}")
    public String registerFRAIF(Model model, Principal principal, @PathVariable("id") Long id) {
        User loginedUser = (User) ((Authentication) principal).getPrincipal();
        Collection<GrantedAuthority> review = loginedUser.getAuthorities();

        for (GrantedAuthority a : review) {
            model.addAttribute("role", a.getAuthority());
        }

        return "content/operacion/metodos/FRA_IF/formFRA_IF";
    }

    @RequestMapping("/listFRAIF")
    public String listFRAIF(Model model, Principal principal) {
        User loginedUser = (User) ((Authentication) principal).getPrincipal();
        Collection<GrantedAuthority> review = loginedUser.getAuthorities();

        for (GrantedAuthority a : review) {
            model.addAttribute("role", a.getAuthority());
        }

        return "content/operacion/metodos/FRA_IF/listFRA_IF";
    }

    @RequestMapping(value = "/registerFRAPO/{id}")
    public String registerFRAPO(Model model, Principal principal, @PathVariable("id") Long id) {
        User loginedUser = (User) ((Authentication) principal).getPrincipal();
        Collection<GrantedAuthority> review = loginedUser.getAuthorities();

        for (GrantedAuthority a : review) {
            model.addAttribute("role", a.getAuthority());
        }

        return "content/operacion/metodos/FRA_PO/formFRA_PO";
    }

    @RequestMapping("/listFRAPO")
    public String listFRAPO(Model model, Principal principal) {
        User loginedUser = (User) ((Authentication) principal).getPrincipal();
        Collection<GrantedAuthority> review = loginedUser.getAuthorities();

        for (GrantedAuthority a : review) {
            model.addAttribute("role", a.getAuthority());
        }

        return "content/operacion/metodos/FRA_PO/listFRA_PO";
    }
}
