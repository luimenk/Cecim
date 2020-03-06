package com.demo.controller;

import java.security.Principal;
import java.util.Calendar;
import java.util.Collection;
import java.util.GregorianCalendar;
import java.util.List;

import com.demo.model.*;
import com.demo.service.*;

import com.demo.utils.GenerateQR;
import com.demo.utils.WebUtils;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@CrossOrigin
public class InterfaceController {

    @Autowired
    private MachineService machineService;

    @Autowired
    private MethodService methodService;

    @Autowired
    private ClientService clientService;

    @Autowired
    private AppRoleService appRoleService;

    @Autowired
    private AppUserService appUserService;

    @Autowired
    private UserRoleService userRoleService;

    @Autowired
    private FoliosService foliosService;

    @RequestMapping(value = {"/"})
    public String index() {
        return "content/accesos/index";
    }

    @RequestMapping("/login")
    public String login() {
        return "content/accesos/index";
    }

    @RequestMapping("/nameCorreo")
    public String saberCorreo() {
        return "content/accesos/1escribeCorreo";
    }

    @RequestMapping("/nameCodigo")
    public String saberCodigo() {
        return "content/accesos/2escribeCodigo";
    }

    @RequestMapping("/nameContrasena")
    public String saberContrasena() {
        return "content/accesos/3escribeContrasena";
    }

    @RequestMapping("/403")
    public String errorLogueo() {
        return "content/accesos/error";
    }

    @RequestMapping(value = "/dashboard")
    public String userInfo(Model model, Principal principal) {

        // After user login successfully.
        String userName = principal.getName();

        System.out.println("User Name: " + userName);

        User loginedUser = (User) ((Authentication) principal).getPrincipal();

        String userInfo = WebUtils.toString(loginedUser);

        Collection<GrantedAuthority> review = loginedUser.getAuthorities();

        System.out.println("Authorities: " + review);
        System.out.println("UserInfo: " + userInfo);

        model.addAttribute("userName", userName);
        model.addAttribute("userInfo", userInfo);

        Long contarMetodo = methodService.contar();
        Long contarCliente = clientService.contar();
        Long contarMaquina = machineService.contar();
        Long contarUsuario = appUserService.contar();

        model.addAttribute("contarMetodo", contarMetodo);
        model.addAttribute("contarCliente", contarCliente);
        model.addAttribute("contarMaquina", contarMaquina);
        model.addAttribute("contarUsuario", contarUsuario);

        String rol = "";

        /*GenerateQR generateQR = new GenerateQR();
        generateQR.generate();*/

        /*System.out.println("AQUI EMPIEZA LA PRUEBA");
        //List<UserRole> algo = userRoleService.findBySomething("SUPERUSUARIO");
        List<UserRole> algo = userRoleService.findAll();
        for (int i = 0; i <algo.size(); i++) {
            if (algo.get(i).getAppRole().getRoleName().equals("LABORATORISTA")) {
                System.out.println(algo.get(i).getAppUser().getUserName());
                System.out.println(algo.get(i).getAppRole().getRoleName());
            }
        }
        System.out.println("AQUI TERMINA LA PRUEBA");*/

        //System.out.println("AQUI EMPIEZA LA PRUEBA");



        //System.out.println("AQUI TERMINA LA PRUEBA");

        for (GrantedAuthority a : review) {
            System.out.println(a.getAuthority());
            if (a.getAuthority().equals("SUPERUSUARIO") || a.getAuthority().equals("DIRECTOR GENERAL")) {
                rol = "content/dashboard/dashboard";
            }
            if (a.getAuthority().equals("GERENCIA TECNICA")) {
                rol = "content/dashboard/dashboardLabo";
            }
            if (a.getAuthority().equals("JEFE DE CONTROL DE CALIDAD")) {
                rol = "content/dashboard/dashboardAdmin";
            }
            if (a.getAuthority().equals("TECNICO")) {
                rol = "content/dashboard/dashboardEspe";
            }
            if (a.getAuthority().equals("JEFE DE LABORATORIO")) {
                rol = "content/dashboard/dashboardLabo";
            }
            if (a.getAuthority().equals("ANALISTA")) {
                rol = "content/dashboard/dashboardLabo";
            }


            model.addAttribute("role", a.getAuthority());
        }
        return rol;
    }

    @RequestMapping(value = "/registroMaquina")
    public String RegistroMaquinas(Model model, Principal principal) {

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

        return "content/maquina/formMaquina";
    }

    //Listar
    @RequestMapping("/mostrarMaquinas")
    public String listStage(Model model, Principal principal) {
        // After user login successfully.
        String userName = principal.getName();

        User loginedUser = (User) ((Authentication) principal).getPrincipal();

        String userInfo = WebUtils.toString(loginedUser);

        Collection<GrantedAuthority> review = loginedUser.getAuthorities();

        model.addAttribute("userName", userName);
        model.addAttribute("userInfo", userInfo);

        List<Machine> lista = machineService.findAll();
        model.addAttribute("maquinas", lista);

        for (GrantedAuthority a : review) {
            model.addAttribute("role", a.getAuthority());
        }

        return "content/maquina/listMachine";
    }

    //Registrar
    @RequestMapping(value = "/registroCliente")
    public String RegistroClientes(Model model, Principal principal) {

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

        return "content/cliente/formCliente";
    }

    //Listar
    @RequestMapping("/mostrarClientes")
    public String listarClientes(Model model, Principal principal) {
        // After user login successfully.
        String userName = principal.getName();

        User loginedUser = (User) ((Authentication) principal).getPrincipal();

        String userInfo = WebUtils.toString(loginedUser);

        Collection<GrantedAuthority> review = loginedUser.getAuthorities();

        model.addAttribute("userName", userName);
        model.addAttribute("userInfo", userInfo);

        List<Client> lista = clientService.findAll();
        model.addAttribute("clientes", lista);

        for (GrantedAuthority a : review) {
            model.addAttribute("role", a.getAuthority());
        }

        return "content/cliente/listClient";
    }

    //Modificar
    @RequestMapping("/registroCliente/{id}")
    public String modificarCliente(@PathVariable Long id, Model model, Principal principal) {

        // After user login successfully.
        String userName = principal.getName();

        User loginedUser = (User) ((Authentication) principal).getPrincipal();

        String userInfo = WebUtils.toString(loginedUser);

        Collection<GrantedAuthority> review = loginedUser.getAuthorities();

        model.addAttribute("userName", userName);
        model.addAttribute("userInfo", userInfo);

        Client client = clientService.findById(id);
        model.addAttribute("clientId", client.getClientId());
        model.addAttribute("nombreRazonSocial", client.getNombreRazonSocial());
        model.addAttribute("nombreComunEmpresa", client.getNombreComunEmpresa());
        model.addAttribute("calle", client.getCalle());
        model.addAttribute("numero", client.getNumero());
        model.addAttribute("colonia", client.getColonia());
        model.addAttribute("municipio", client.getMunicipio());
        model.addAttribute("estado", client.getEstado());
        model.addAttribute("codigoPostal", client.getCodigoPostal());
        model.addAttribute("rfc", client.getRfc());
        /*model.addAttribute("nombrePersonaContacto", client.getNombrePersonaContacto());
        model.addAttribute("email", client.getEmail());
        model.addAttribute("telefono", client.getTelefono());*/

        for (GrantedAuthority a : review) {
            model.addAttribute("role", a.getAuthority());
        }

        return "content/cliente/formCliente";
    }

    @RequestMapping(value = "/registroMetodo")
    public String RegistroMetodos(Model model, Principal principal) {

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

        return "content/metodo/formMetodo";
    }

    //Listar
    @RequestMapping("/mostrarMetodos")
    public String listarMetodos(Model model, Principal principal) {
        // After user login successfully.
        String userName = principal.getName();

        User loginedUser = (User) ((Authentication) principal).getPrincipal();

        String userInfo = WebUtils.toString(loginedUser);

        Collection<GrantedAuthority> review = loginedUser.getAuthorities();

        model.addAttribute("userName", userName);
        model.addAttribute("userInfo", userInfo);

        /*List<Method> lista = methodService.findAll();
        model.addAttribute("metodos", lista);*/

        for (GrantedAuthority a : review) {
            model.addAttribute("role", a.getAuthority());
        }

        return "content/metodo/listMethod";
    }

    @RequestMapping(value = "/registroSolicitudMuestra")
    public String registroSolicitud(Model model, Principal principal) {

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

        return "content/muestras/solicitudMuestras";
    }

    @RequestMapping(value = "/generadorReporte")
    public String generarReporte(Model model, Principal principal) {

        // After user login successfully.
        String userName = principal.getName();

        User loginedUser = (User) ((Authentication) principal).getPrincipal();

        String userInfo = WebUtils.toString(loginedUser);

        Collection<GrantedAuthority> review = loginedUser.getAuthorities();

        model.addAttribute("userName", userName);
        model.addAttribute("userInfo", userInfo);

        List<Machine> lista3 = machineService.findAll();
        model.addAttribute("maquinas", lista3);

        List<Method> lista2 = methodService.findAll();
        model.addAttribute("metodos", lista2);

        for (GrantedAuthority a : review) {
            model.addAttribute("role", a.getAuthority());
        }

        return "content/reportes/reporteValidacionMetodo";
    }

    @RequestMapping(value = "/grafica")
    public String graficas(Model model, Principal principal) {

        // After user login successfully.
        String userName = principal.getName();

        User loginedUser = (User) ((Authentication) principal).getPrincipal();

        String userInfo = WebUtils.toString(loginedUser);

        Collection<GrantedAuthority> review = loginedUser.getAuthorities();

        model.addAttribute("userName", userName);
        model.addAttribute("userInfo", userInfo);

        for (GrantedAuthority a : review) {
            System.out.println(a.getAuthority());
            model.addAttribute("role", a.getAuthority());
        }

        return "content/grafica";
    }

    //Registrar
    @RequestMapping(value = "/registerOrdenServicio")
    public String registerOrdenServicio(Model model, Principal principal) {
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
        return "content/ordenServicio/formOrdenServicio";
    }

    //Listar
    @RequestMapping("/listOrdenServicio")
    public String listOrdenServicio(Model model, Principal principal) {
        // After user login successfully.
        String userName = principal.getName();

        User loginedUser = (User) ((Authentication) principal).getPrincipal();

        String userInfo = WebUtils.toString(loginedUser);

        Collection<GrantedAuthority> review = loginedUser.getAuthorities();

        model.addAttribute("userName", userName);
        model.addAttribute("userInfo", userInfo);

        List<Machine> lista = machineService.findAll();
        model.addAttribute("maquinas", lista);

        for (GrantedAuthority a : review) {
            model.addAttribute("role", a.getAuthority());
        }

        return "content/odernServicio/listOrdenServicio";
    }

    //Registrar
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

        List<Method> lista2 = methodService.findAll();
        model.addAttribute("metodos", lista2);

        for (GrantedAuthority a : review) {
            model.addAttribute("role", a.getAuthority());
        }
        return "content/usuarios/formUsuario";
    }

    //Listar
    @RequestMapping("/listUsuario")
    public String listUsuario(Model model, Principal principal) {
        // After user login successfully.
        String userName = principal.getName();

        User loginedUser = (User) ((Authentication) principal).getPrincipal();

        String userInfo = WebUtils.toString(loginedUser);

        Collection<GrantedAuthority> review = loginedUser.getAuthorities();

        model.addAttribute("userName", userName);
        model.addAttribute("userInfo", userInfo);

        List<Machine> lista = machineService.findAll();
        model.addAttribute("maquinas", lista);

        for (GrantedAuthority a : review) {
            model.addAttribute("role", a.getAuthority());
        }

        return "content/usuarios/listUsuario";
    }

    //Registrar
    @RequestMapping(value = "/registerSolicitudServicio")
    public String registerSolicitudServicio(Model model, Principal principal) {
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
        return "content/solicitudServicioCliente/formSolicitudServicio";
    }

    //Listar
    @RequestMapping("/listSolicitudServicio")
    public String listSolicitudServicio(Model model, Principal principal) {
        // After user login successfully.
        String userName = principal.getName();

        User loginedUser = (User) ((Authentication) principal).getPrincipal();

        String userInfo = WebUtils.toString(loginedUser);

        Collection<GrantedAuthority> review = loginedUser.getAuthorities();

        model.addAttribute("userName", userName);
        model.addAttribute("userInfo", userInfo);

        List<Machine> lista = machineService.findAll();
        model.addAttribute("maquinas", lista);

        for (GrantedAuthority a : review) {
            model.addAttribute("role", a.getAuthority());
        }

        return "content/solicitudServicioCliente/listSolicitudServicio";
    }
}
