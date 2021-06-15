package com.demo.controller.vistas;

import com.demo.model.*;
import com.demo.service.AppRoleService;
import com.demo.service.AppUserService;
import com.demo.service.UserRoleService;
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
public class UsuariosVista {

    @Autowired
    private UserRoleService userRoleService;

    @Autowired
    private AppRoleService appRoleService;

    @Autowired
    private AppUserService appUserService;

    //USUARIOS
    @RequestMapping(value = "/registerUsuario")
    public String altaUsuarios(Model model, Principal principal) {
        // After user login successfully.
        String userName = principal.getName();
        User loginedUser = (User) ((Authentication) principal).getPrincipal();
        String userInfo = WebUtils.toString(loginedUser);
        Collection<GrantedAuthority> review = loginedUser.getAuthorities();

        model.addAttribute("userName", userName);
        model.addAttribute("userInfo", userInfo);

        List<AppRole> lista = appRoleService.findAll();
        model.addAttribute("roles", lista);

        for (GrantedAuthority a : review) {
            model.addAttribute("role", a.getAuthority());
        }
        return "content/usuarios/formUsuario";
    }

    //MODIFICAR USUARIOS
    @RequestMapping("/registroUsuario/{id}")
    public String modificarUsuario(@PathVariable Long id, Model model, Principal principal) {

        // After user login successfully.
        String userName = principal.getName();
        User loginedUser = (User) ((Authentication) principal).getPrincipal();
        String userInfo = WebUtils.toString(loginedUser);
        Collection<GrantedAuthority> review = loginedUser.getAuthorities();
        model.addAttribute("userName", userName);
        model.addAttribute("userInfo", userInfo);

        AppUser appUser = appUserService.findById(id);
        model.addAttribute("appUser", appUser);

        List<UserRole> userRoles = userRoleService.findAllByUserId(appUser.getUserId());
        for (int i=0; i<userRoles.size(); i++) {
            if (userRoles.get(i).getAppRole().getRoleId() == 9) {
                model.addAttribute("checked9", true);
            }
            if (userRoles.get(i).getAppRole().getRoleId() == 10) {
                model.addAttribute("checked10", true);
            }
            if (userRoles.get(i).getAppRole().getRoleId() == 11) {
                model.addAttribute("checked11", true);
            }
            if (userRoles.get(i).getAppRole().getRoleId() == 12) {
                model.addAttribute("checked12", true);
            }
            if (userRoles.get(i).getAppRole().getRoleId() == 13) {
                model.addAttribute("checked13", true);
            }
            if (userRoles.get(i).getAppRole().getRoleId() == 14) {
                model.addAttribute("checked14", true);
            }
            if (userRoles.get(i).getAppRole().getRoleId() == 15) {
                model.addAttribute("checked15", true);
            }
            if (userRoles.get(i).getAppRole().getRoleId() == 16) {
                model.addAttribute("checked16", true);
            }
            if (userRoles.get(i).getAppRole().getRoleId() == 17) {
                model.addAttribute("checked17", true);
            }
            if (userRoles.get(i).getAppRole().getRoleId() == 18) {
                model.addAttribute("checked18", true);
            }
            if (userRoles.get(i).getAppRole().getRoleId() == 19) {
                model.addAttribute("checked19", true);
            }
            if (userRoles.get(i).getAppRole().getRoleId() == 20) {
                model.addAttribute("checked20", true);
            }
            if (userRoles.get(i).getAppRole().getRoleId() == 21) {
                model.addAttribute("checked21", true);
            }
            if (userRoles.get(i).getAppRole().getRoleId() == 22) {
                model.addAttribute("checked22", true);
            }
            if (userRoles.get(i).getAppRole().getRoleId() == 23) {
                model.addAttribute("checked23", true);
            }
            if (userRoles.get(i).getAppRole().getRoleId() == 24) {
                model.addAttribute("checked24", true);
            }
            if (userRoles.get(i).getAppRole().getRoleId() == 25) {
                model.addAttribute("checked25", true);
            }
        }

        List<AppRole> lista = appRoleService.findAll();
        model.addAttribute("roles", lista);

        for (GrantedAuthority a : review) {
            model.addAttribute("role", a.getAuthority());
        }

        return "content/usuarios/formUsuario2";
    }

    @RequestMapping("/listUsuario")
    public String listUsuario(Model model, Principal principal) {
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

        return "content/usuarios/listUsuario";
    }
}
