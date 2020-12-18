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
import org.springframework.security.web.authentication.rememberme.CookieTheftException;
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

    @Autowired
    private DocumentServiceOrdenServicio documentServiceOrdenServicio;

    @Autowired
    private EtiquetaService etiquetaService;

    /**
     ************************************************
     *      CARGA PRINCIPAL E INICIO DE SESIÓN      *
     ************************************************
     */
    @RequestMapping(value = {"/"})
    public String index() {
        return "content/accesos/index";
    }

    @RequestMapping("/login")
    public String login() {
        return "content/accesos/index";
    }

    @RequestMapping("/403")
    public String errorLogueo() {
        return "content/accesos/error";
    }

    /**
     ************************************************
     *      RECUPERACION DE CONTRASEÑA              *
     ************************************************
     */
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

    /**
     ************************************************
     *          CARGA DE DASHBOARD PRINCIPAL        *
     ************************************************
     */
    @RequestMapping(value = "/dashboard")
    public String userInfo(Model model, Principal principal) {
        try{
            // After user login successfully.
            String userName = principal.getName();

            User loginedUser = (User) ((Authentication) principal).getPrincipal();

            String userInfo = WebUtils.toString(loginedUser);

            Collection<GrantedAuthority> review = loginedUser.getAuthorities();

            //model.addAttribute("userName", userName);
            //model.addAttribute("userInfo", userInfo);

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

            //List<UserRole> algo = userRoleService.findBySomething("SUPERUSUARIO");
            List<UserRole> algo = userRoleService.findAll();
            for (int i = 0; i <algo.size(); i++) {
                if (algo.get(i).getAppRole().getRoleName().equals("LABORATORISTA")) {
                    System.out.println(algo.get(i).getAppUser().getUserName());
                    System.out.println(algo.get(i).getAppRole().getRoleName());
                }
            }

            for (GrantedAuthority a : review) {
                //System.out.println(a.getAuthority());
                if (a.getAuthority().equals("ROLE_SUPERUSUARIO") || a.getAuthority().equals("ROLE_DIRECTORGENERAL")) {
                    rol = "content/dashboard/dashboard";
                }
                if (a.getAuthority().equals("ROLE_GERENCIATECNICA")) {
                    rol = "content/dashboard/dashboardLabo";
                }
                if (a.getAuthority().equals("ROLE_CONTROLDECALIDAD")) {
                    rol = "content/dashboard/dashboardAdmin";
                }
                if (a.getAuthority().equals("ROLE_TECNICO")) {
                    rol = "content/dashboard/dashboardEspe";
                }
                if (a.getAuthority().equals("ROLE_JEFEDELABORATORIO")) {
                    rol = "content/dashboard/dashboardLabo";
                }
                if (a.getAuthority().equals("ROLE_ANALISTA")) {
                    rol = "content/dashboard/dashboardLabo";
                }
                model.addAttribute("role", a.getAuthority());
            }

            return rol;
        } catch (CookieTheftException ex){
            System.out.println("El token ha caducado");
            return "content/accesos/index";
        }
    }

    /**
     ************************************************
     *                  DOCUMENTOS                  *
     ************************************************
     */
    //MAQUINAS
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

    //CLIENTES
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

    //MÉTODOS
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

        List<Method> lista2 = methodService.findAll();
        model.addAttribute("metodos", lista2);

        for (GrantedAuthority a : review) {
            model.addAttribute("role", a.getAuthority());
        }
        return "content/usuarios/formUsuario";
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

        List<Machine> lista = machineService.findAll();
        model.addAttribute("maquinas", lista);

        for (GrantedAuthority a : review) {
            model.addAttribute("role", a.getAuthority());
        }

        return "content/usuarios/listUsuario";
    }

    //ORDEN DE SERVICIO DEL CLIENTE
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

        List<AppUser> lista3 = appUserService.findAll();
        model.addAttribute("personal", lista3);

        for (GrantedAuthority a : review) {
            model.addAttribute("role", a.getAuthority());
        }
        return "content/ordenServicio/formOrdenServicio";
    }

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

        List<DocumentOrdenServicio> lista2 = documentServiceOrdenServicio.findAll();
        model.addAttribute("ordenServicios", lista2);

        for (GrantedAuthority a : review) {
            model.addAttribute("role", a.getAuthority());
        }

        return "content/ordenServicio/listOrdenServicio";
    }

//    //SOLICITUD DE SERVICIO DEL CLIENTE
//    @RequestMapping(value = "/registerSolicituedServicio")
//    public String registerSolicitudServicio(Model model, Principal principal) {
//        // After user login successfully.
//        String userName = principal.getName();
//        User loginedUser = (User) ((Authentication) principal).getPrincipal();
//        String userInfo = WebUtils.toString(loginedUser);
//        Collection<GrantedAuthority> review = loginedUser.getAuthorities();
//
//        model.addAttribute("userName", userName);
//        model.addAttribute("userInfo", userInfo);
//
//        List<Client> lista = clientService.findAll();
//        model.addAttribute("empresas", lista);
//
//        List<Method> lista2 = methodService.findAll();
//        model.addAttribute("metodos", lista2);
//
//        for (GrantedAuthority a : review) {
//            model.addAttribute("role", a.getAuthority());
//        }
//        return "content/operacion/solicitudServicioCliente/formSolicitudServicio";
//    }
//
//    @RequestMapping("/listSolicitudServicio")
//    public String listSolicitudServicio(Model model, Principal principal) {
//        // After user login successfully.
//        String userName = principal.getName();
//
//        User loginedUser = (User) ((Authentication) principal).getPrincipal();
//
//        String userInfo = WebUtils.toString(loginedUser);
//
//        Collection<GrantedAuthority> review = loginedUser.getAuthorities();
//
//        model.addAttribute("userName", userName);
//        model.addAttribute("userInfo", userInfo);
//
//        List<Machine> lista = machineService.findAll();
//        model.addAttribute("maquinas", lista);
//
//        for (GrantedAuthority a : review) {
//            model.addAttribute("role", a.getAuthority());
//        }
//
//        return "content/operacion/solicitudServicioCliente/listSolicitudServicio";
//    }

    //COTIZACION Y CONTRATO
    @RequestMapping(value = "/registerCotizacionContrato")
    public String registerCotizacionContrato(Model model, Principal principal) {
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
        return "content/cotizacionContrato/formCotizacionContrato";
    }

    @RequestMapping("/listCotizacionContrato")
    public String listCotizacionContrato(Model model, Principal principal) {
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

        return "content/cotizacionContrato/listCotizacionContrato";
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

        /*// After user login successfully.
        String userName = principal.getName();

        User loginedUser = (User) ((Authentication) principal).getPrincipal();

        String userInfo = WebUtils.toString(loginedUser);

        Collection<GrantedAuthority> review = loginedUser.getAuthorities();

        model.addAttribute("userName", userName);
        model.addAttribute("userInfo", userInfo);

        for (GrantedAuthority a : review) {
            System.out.println(a.getAuthority());
            model.addAttribute("role", a.getAuthority());
        }*/

        return "content/grafica";
    }





    //Registrar
    /*@RequestMapping(value = "/registerSolicitudServicio")
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
    }*/

    //Listar
    /*@RequestMapping("/listSolicitudServicio")
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
    }*/

    @RequestMapping("/registroUsuario/{id}")
    public String modificarUsuario(@PathVariable Long id, Model model, Principal principal) {

        // After user login successfully.
        String userName = principal.getName();

        User loginedUser = (User) ((Authentication) principal).getPrincipal();

        String userInfo = WebUtils.toString(loginedUser);

        Collection<GrantedAuthority> review = loginedUser.getAuthorities();

        model.addAttribute("userName", userName);
        model.addAttribute("userInfo", userInfo);

        UserRole userRole = userRoleService.findByIdUser(id);

        //AppUser appUser = appUserService.findById(id);

        model.addAttribute("userId", userRole.getAppUser().getUserId());
        model.addAttribute("userName", userRole.getAppUser().getUserName());
        //System.out.println(appUser.getUserName());
        //model.addAttribute("password", appUser.getPassword());
        model.addAttribute("nombreUsuario", userRole.getAppUser().getNombreUsuario());
        model.addAttribute("apellidoUsuario", userRole.getAppUser().getApellidoUsuario());
        model.addAttribute("nacimiento", userRole.getAppUser().getNacimiento());
        //System.out.println(appUser.getNacimiento());
        model.addAttribute("puesto", userRole.getAppUser().getPuesto());

        model.addAttribute("rolSeleccionado", userRole.getAppRole().getRoleId());

        List<AppRole> lista = appRoleService.findAll();
        model.addAttribute("roles", lista);

        for (GrantedAuthority a : review) {
            model.addAttribute("role", a.getAuthority());
        }

        return "content/usuarios/formUsuario";
    }

    @RequestMapping("/registroEtiqueta/{id}")
    public String modificarEtiqueta(@PathVariable Long id, Model model, Principal principal) {

        // After user login successfully.
        String userName = principal.getName();

        User loginedUser = (User) ((Authentication) principal).getPrincipal();

        String userInfo = WebUtils.toString(loginedUser);

        Collection<GrantedAuthority> review = loginedUser.getAuthorities();

        model.addAttribute("userName", userName);
        model.addAttribute("userInfo", userInfo);

        Etiqueta etiqueta = etiquetaService.findById(id);

        List<Client> lista = clientService.findAll();
        model.addAttribute("empresas", lista);

        List<Method> lista2 = methodService.findAll();
        model.addAttribute("metodos", lista2);

        model.addAttribute("etiquetaId", etiqueta.getEtiquetaId());
        model.addAttribute("fecha", etiqueta.getFecha());
//        System.out.println(appUser.getUserName());
        //model.addAttribute("password", appUser.getPassword());
        model.addAttribute("descripcionMuestra", etiqueta.getDescripcionMuestra());
        model.addAttribute("tipoMaterial", etiqueta.getTipoMaterial());
        model.addAttribute("cantidadMuestra", etiqueta.getCantidadMuestra());
        model.addAttribute("lote", etiqueta.getLote());
        model.addAttribute("observaciones", etiqueta.getObservaciones());

        for (GrantedAuthority a : review) {
            model.addAttribute("role", a.getAuthority());
        }

        return "content/etiquetas/formEtiqueta";
    }

    //Etiquetas
    @RequestMapping(value = "/registerEtiquetas")
    public String registerEtiquetas(Model model, Principal principal) {
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
        return "content/etiquetas/formEtiqueta";
    }

    @RequestMapping("/listEtiquetas")
    public String listEtiquetas(Model model, Principal principal) {
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

        return "content/etiquetas/listEtiqueta";
    }

    //VideoQR
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

    //lectura de qr
    @RequestMapping(value = "/lecturaQR")
    public String lecturaQR(Model model, Principal principal) {
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
        return "content/operacion/lectorQR/formLectorQR";
    }

    //METODOS
    @RequestMapping(value = "/registerEspesor/{id}")
    public String registerEspesor(Model model, Principal principal, @PathVariable("id") Long id) {
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
        return "content/operacion/metodos/determinacionEspesor/formDeterminacionEspesor";
    }

    @RequestMapping("/listEspesor")
    public String listEspesor(Model model, Principal principal) {
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

        return "content/operacion/metodos/determinacionEspesor/listDeterminacionEspesor";
    }

    @RequestMapping(value = "/registerDimension/{id}")
    public String registerDimension(Model model, Principal principal, @PathVariable("id") Long id) {
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
        return "content/operacion/metodos/determinacionDimension/formDeterminacionDimension";
    }

    @RequestMapping("/listDimension")
    public String listDimension(Model model, Principal principal) {
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

        return "content/operacion/metodos/determinacionDimension/listDeterminacionDimension";
    }

    @RequestMapping(value = "/registerSelladoTemperatura/{id}")
    public String registerSelladoTemperatura(Model model, Principal principal, @PathVariable("id") Long id) {
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
        return "content/operacion/metodos/selladoTemperatura/formSelladoTemperatura";
    }

    @RequestMapping("/listSelladoTemperatura")
    public String listSelladoTemperatura(Model model, Principal principal) {
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

        return "content/operacion/metodos/selladoTemperatura/listSelladoTemperatura";
    }

    @RequestMapping(value = "/registerEspectrometriaInfrarroja/{id}")
    public String registerEspectrometriaInfrarroja(Model model, Principal principal, @PathVariable("id") Long id) {
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
        return "content/operacion/metodos/espectrometriaInfrarroja/formEspectrometriaInfrarroja";
    }

    @RequestMapping("/listEspectrometriaInfrarroja")
    public String listEspectrometriaInfrarroja(Model model, Principal principal) {
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

        return "content/operacion/metodos/espectrometriaInfrarroja/listEspectrometriaInfrarroja";
    }

    @RequestMapping(value = "/registerElongacionRuptura/{id}")
    public String registerElongacionRuptura(Model model, Principal principal, @PathVariable("id") Long id) {
        // After user login successfully.
        //String userName = principal.getName();
        //User loginedUser = (User) ((Authentication) principal).getPrincipal();
        //String userInfo = WebUtils.toString(loginedUser);
        //Collection<GrantedAuthority> review = loginedUser.getAuthorities();

        //model.addAttribute("userName", userName);
        //model.addAttribute("userInfo", userInfo);

        List<Client> lista = clientService.findAll();
        model.addAttribute("empresas", lista);

        List<Method> lista2 = methodService.findAll();
        model.addAttribute("metodos", lista2);

        //for (GrantedAuthority a : review) {
        //    model.addAttribute("role", a.getAuthority());
        //}
        return "content/operacion/metodos/elongacionRuptura/formElongacionRuptura";
    }

    @RequestMapping("/listElongacionRuptura")
    public String listElongacionRuptura(Model model, Principal principal) {
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

        return "content/operacion/metodos/elongacionRuptura/listElongacionRuptura";
    }

    @RequestMapping(value = "/registerFRAPRR/{id}")
    public String registerFRAPRR(Model model, Principal principal, @PathVariable("id") Long id) {

        List<Client> lista = clientService.findAll();
        model.addAttribute("empresas", lista);

        List<Method> lista2 = methodService.findAll();
        model.addAttribute("metodos", lista2);

        return "content/operacion/metodos/FRA_PRR/formFRA_PRR";
    }

    @RequestMapping("/listFRAPRR")
    public String listFRAPRR(Model model, Principal principal) {
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

        return "content/operacion/metodos/FRA_PRR/listFRA_PRR";
    }

    @RequestMapping(value = "/registerFRADSC/{id}")
    public String registerFRADSC(Model model, Principal principal, @PathVariable("id") Long id) {

        List<Client> lista = clientService.findAll();
        model.addAttribute("empresas", lista);

        List<Method> lista2 = methodService.findAll();
        model.addAttribute("metodos", lista2);

        return "content/operacion/metodos/FRA_DSC/formFRA_DSC";
    }

    @RequestMapping("/listFRADSC")
    public String listFRADSC(Model model, Principal principal) {
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

        return "content/operacion/metodos/FRA_DSC/listFRA_DSC";
    }

    @RequestMapping(value = "/registerFRAGR/{id}")
    public String registerFRAGR(Model model, Principal principal, @PathVariable("id") Long id) {

        List<Client> lista = clientService.findAll();
        model.addAttribute("empresas", lista);

        List<Method> lista2 = methodService.findAll();
        model.addAttribute("metodos", lista2);

        return "content/operacion/metodos/FRA_GR/formFRA_GR";
    }

    @RequestMapping("/listFRAGR")
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

        return "content/operacion/metodos/FRA_GR/listFRA_GR";
    }

}
