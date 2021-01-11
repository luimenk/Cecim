package com.demo.controller.vistas;

import com.demo.model.Client;
import com.demo.model.Method;
import com.demo.service.ClientService;
import com.demo.service.MethodService;
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
import java.util.Collection;
import java.util.List;

@Controller
@CrossOrigin
public class RecepcionValidacionRegistroCodificacionVista {

    @Autowired
    private MethodService methodService;

    @Autowired
    private ClientService clientService;

    @RequestMapping(value = "/registerValidacion")
    public String registerValidacion(Model model, Principal principal) {
        // After user login successfully.
        String userName = principal.getName();
        User loginedUser = (User) ((Authentication) principal).getPrincipal();
        String userInfo = WebUtils.toString(loginedUser);
        Collection<GrantedAuthority> review = loginedUser.getAuthorities();

        model.addAttribute("userName", userName);
        model.addAttribute("userInfo", userInfo);

        List<Client> lista = clientService.findAll();
        model.addAttribute("empresas", lista);

        List<Method> lista2 = methodService.findAll();
        model.addAttribute("metodos", lista2);

        for (GrantedAuthority a : review) {
            model.addAttribute("role", a.getAuthority());
        }
        return "content/operacion/recepcionValidacion/formRecepcionValidacion";
    }

    @RequestMapping(value = "/registerValidacionFolio")
    public String registerValidacionFolio(Model model, Principal principal) {
        // After user login successfully.
        String userName = principal.getName();
        User loginedUser = (User) ((Authentication) principal).getPrincipal();
        String userInfo = WebUtils.toString(loginedUser);
        Collection<GrantedAuthority> review = loginedUser.getAuthorities();

        model.addAttribute("userName", userName);
        model.addAttribute("userInfo", userInfo);

        List<Client> lista = clientService.findAll();
        model.addAttribute("empresas", lista);

        List<Method> lista2 = methodService.findAll();
        model.addAttribute("metodos", lista2);

        for (GrantedAuthority a : review) {
            model.addAttribute("role", a.getAuthority());
        }
        return "content/operacion/recepcionValidacion/formRecepcionValidacionFolio";
    }

    @RequestMapping(value = "/registerValidacionDirecto/{id}")
    public String registerValidacionDirecto(Model model, Principal principal, @PathVariable("id") Long id) {
        // After user login successfully.
        String userName = principal.getName();
        User loginedUser = (User) ((Authentication) principal).getPrincipal();
        String userInfo = WebUtils.toString(loginedUser);
        Collection<GrantedAuthority> review = loginedUser.getAuthorities();

        model.addAttribute("userName", userName);
        model.addAttribute("userInfo", userInfo);

        List<Client> lista = clientService.findAll();
        model.addAttribute("empresas", lista);

        List<Method> lista2 = methodService.findAll();
        model.addAttribute("metodos", lista2);

        for (GrantedAuthority a : review) {
            model.addAttribute("role", a.getAuthority());
        }
        return "content/operacion/recepcionValidacion/formRecepcionValidacionDirecto";
    }

    @RequestMapping("/listRecepcionValidacion")
    public String listRecepcionValidacion(Model model, Principal principal) {
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

        return "content/operacion/recepcionValidacion/listRecepcionValidacion";
    }
}
