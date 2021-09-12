package com.demo.controller.vistas;

import com.demo.model.Client;
import com.demo.model.Machine;
import com.demo.model.Method;
import com.demo.model.auxiliares.MetodosSeleccionados;
import com.demo.model.operacion.MetodoMuestra;
import com.demo.model.operacion.SolicitudServicioCliente;
import com.demo.model.operacion.SolicitudServicioClienteMuestras;
import com.demo.service.*;
import com.demo.service.operacion.MetodoMuestraService;
import com.demo.service.operacion.SolicitudServicioClienteMuestrasService;
import com.demo.service.operacion.SolicitudServicioClienteService;
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
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Controller
@CrossOrigin
public class SolicitudServicioClienteVista {

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

    @Autowired
    private SolicitudServicioClienteService solicitudServicioClienteService;

    @Autowired
    private SolicitudServicioClienteMuestrasService solicitudServicioClienteMuestrasService;

    @Autowired
    private MetodoMuestraService metodoMuestraService;

    //SOLICITUD DE SERVICIO DEL CLIENTE
    @RequestMapping(value = "/registerSolicituedServicio")
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
        return "content/operacion/solicitudServicioCliente/formSolicitudServicio";
    }

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

        return "content/operacion/solicitudServicioCliente/listSolicitudServicio";
    }

    @RequestMapping("/listSolicitudServicioPagos")
    public String listSolicitudServicioPagos(Model model, Principal principal) {
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

        return "content/operacion/solicitudServicioCliente/listSolicitudServicioPagos";
    }

    @RequestMapping("/detalleSolicitudServicio/{id}")
    public String detalleSolicitudServicio(Model model, Principal principal, @PathVariable("id") Long id) {
        // After user login successfully.
        String userName = principal.getName();

        User loginedUser = (User) ((Authentication) principal).getPrincipal();

        String userInfo = WebUtils.toString(loginedUser);

        Collection<GrantedAuthority> review = loginedUser.getAuthorities();

        model.addAttribute("userName", userName);
        model.addAttribute("userInfo", userInfo);

        SolicitudServicioCliente solicitudServicioCliente = solicitudServicioClienteService.findById(id);

        List<SolicitudServicioClienteMuestras> lista = solicitudServicioClienteMuestrasService.findAllBySolicitud(id);
        List<MetodoMuestra> lista2 = metodoMuestraService.findAllBySolicitud(solicitudServicioCliente);
        //List<MetodoMuestra> lista3 = metodoMuestraService.contarRecepciones("OK");
        long cantAcondicionadas = solicitudServicioClienteMuestrasService.contarAcondicionadas(id,"SI");
        long cantMetodos = metodoMuestraService.contarPorEstatus(solicitudServicioCliente, "OK");

        model.addAttribute("muestrasAcondicionadas", cantAcondicionadas);
        model.addAttribute("metodosRealizados", cantMetodos);
        model.addAttribute("totalMuestras", lista.size());
        model.addAttribute("totalMetodos", lista2.size());
        model.addAttribute("listaMuestras", lista);
        model.addAttribute("listaMetodos", lista2);
        model.addAttribute("folio", solicitudServicioCliente.getFolioSolitudServicioCliente());

        lista2.get(0).getMetodoMuestraId();

        for (GrantedAuthority a : review) {
            model.addAttribute("role", a.getAuthority());
        }

        return "content/operacion/solicitudServicioCliente/detalleSolicitudServicio";
    }

    //MODIFICAR SOLICITUD
    @RequestMapping(value = "/registerSolicituedServicio/{id}")
    public String modificarSolicitudServicio(Model model, Principal principal, @PathVariable("id") Long id) {
        // After user login successfully.
        String userName = principal.getName();
        User loginedUser = (User) ((Authentication) principal).getPrincipal();
        String userInfo = WebUtils.toString(loginedUser);
        Collection<GrantedAuthority> review = loginedUser.getAuthorities();

        model.addAttribute("userName", userName);
        model.addAttribute("userInfo", userInfo);

        SolicitudServicioCliente solicitudServicioCliente = solicitudServicioClienteService.findById(id);
        List<Client> lista = clientService.findAll();
        model.addAttribute("empresas", lista);
        model.addAttribute("empresaSeleccionada", solicitudServicioCliente.getClient().getClientId());

        model.addAttribute("solicitudServicioClienteId", solicitudServicioCliente.getSolicitudServicioClienteId());
        //model.addAttribute("folioSolitudServicioCliente", solicitudServicioCliente.getFolioSolitudServicioCliente());
        if (solicitudServicioCliente.getServicioUrgente().equals("Si")){
            model.addAttribute("checked1", true);
            model.addAttribute("checked2", false);
        } else {
            model.addAttribute("checked1", false);
            model.addAttribute("checked2", true);
        }
        model.addAttribute("servicioUrgente", solicitudServicioCliente.getServicioUrgente());

        model.addAttribute("fechaEnvioMuestras", solicitudServicioCliente.getFechaEnvioMuestras());
        model.addAttribute("fechaPago", solicitudServicioCliente.getFechaPago());
        model.addAttribute("nombreFirmaEmisor", solicitudServicioCliente.getNombreFirmaEmisor());
        if (solicitudServicioCliente.getAlmacenamientoEspecial().equals("Si")){
            model.addAttribute("checked3", true);
            model.addAttribute("checked4", false);
            model.addAttribute("especifiqueStatus", false);
        } else {
            model.addAttribute("checked3", false);
            model.addAttribute("checked4", true);
            model.addAttribute("especifiqueStatus", true);
        }
        model.addAttribute("almacenamientoEspecial", solicitudServicioCliente.getAlmacenamientoEspecial());
        model.addAttribute("especifique", solicitudServicioCliente.getEspecifique());
        model.addAttribute("fechaRecepcionMuestras", solicitudServicioCliente.getFechaRecepcionMuestras());
        model.addAttribute("fechaCompromisoEntrega", solicitudServicioCliente.getFechaCompromisoEntrega());
        model.addAttribute("nombreFirmaReceptor", solicitudServicioCliente.getNombreFirmaReceptor());
        model.addAttribute("nombreFirmaCalidad", solicitudServicioCliente.getNombreFirmaCalidad());
        model.addAttribute("devolucionMuestras", solicitudServicioCliente.getDevolucionMuestras());
        if (solicitudServicioCliente.getDevolucionMuestras().equals("Si")){
            model.addAttribute("checked5", true);
            model.addAttribute("checked6", false);
        } else {
            model.addAttribute("checked5", false);
            model.addAttribute("checked6", true);
        }

        List<SolicitudServicioClienteMuestras> muestrasList = solicitudServicioClienteMuestrasService.findAllBySolicitud(solicitudServicioCliente.getSolicitudServicioClienteId());
        model.addAttribute("muestrasList", muestrasList);
        model.addAttribute("cantidadDeMuestras", muestrasList.size());

        List<Method> lista2 = methodService.findAll();
        model.addAttribute("metodos", lista2);

        List<List<MetodosSeleccionados>> listToRender = new ArrayList<>();
        List<MetodosSeleccionados> aux;
        for (SolicitudServicioClienteMuestras solicitudServicioClienteMuestras : muestrasList) {
            List<MetodoMuestra> metodoMuestras = metodoMuestraService.
                    findAllByMuestra(solicitudServicioClienteMuestras.getSolicitudServicioClienteMuestrasId());
            aux = new ArrayList<>();
            for (Method method : lista2) {
                MetodosSeleccionados metodoMuestraPojo = new MetodosSeleccionados();
                metodoMuestraPojo.setMethod(method);
                if (containsMethod(metodoMuestras,method)) {
                    metodoMuestraPojo.setSelected(true);
                }
                aux.add(metodoMuestraPojo);
            }
            listToRender.add(aux);
        }
        model.addAttribute("listToRender", listToRender);

        for (GrantedAuthority a : review) {
            model.addAttribute("role", a.getAuthority());
        }
        return "content/operacion/solicitudServicioCliente/formSolicitudServicio2";
    }


    @RequestMapping(value = "/registerSolicituedServicioFechas/{id}")
    public String fechasSolicitudServicio(Model model, Principal principal, @PathVariable("id") Long id) {
        // After user login successfully.
        String userName = principal.getName();
        User loginedUser = (User) ((Authentication) principal).getPrincipal();
        String userInfo = WebUtils.toString(loginedUser);
        Collection<GrantedAuthority> review = loginedUser.getAuthorities();

        model.addAttribute("userName", userName);
        model.addAttribute("userInfo", userInfo);

        SolicitudServicioCliente solicitudServicioCliente = solicitudServicioClienteService.findById(id);

        model.addAttribute("fechaEnvioMuestras", solicitudServicioCliente.getFechaEnvioMuestras());
        model.addAttribute("fechaPago", solicitudServicioCliente.getFechaPago());
        model.addAttribute("fechaRecepcionMuestras", solicitudServicioCliente.getFechaRecepcionMuestras());
        model.addAttribute("fechaCompromisoEntrega", solicitudServicioCliente.getFechaCompromisoEntrega());

        for (GrantedAuthority a : review) {
            model.addAttribute("role", a.getAuthority());
        }
        return "content/operacion/solicitudServicioCliente/formSolicitudServicioFechas";
    }

    @RequestMapping(value = "/registerSolicituedServicioFechaPagoAnticipo/{id}")
    public String fechaPagoAnticipo(Model model, Principal principal, @PathVariable("id") Long id) {
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
        return "content/operacion/solicitudServicioCliente/fechaPagoAnticipo";
    }

    @RequestMapping(value = "/registerSolicituedServicioFechaPagoFinal/{id}")
    public String fechaPagoFinal(Model model, Principal principal, @PathVariable("id") Long id) {
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
        return "content/operacion/solicitudServicioCliente/fechaPagoFinal";
    }

    @RequestMapping(value = "/registerSolicituedServicioFechaCompromisoEntrega/{id}")
    public String fechaCompromisoEntrega(Model model, Principal principal, @PathVariable("id") Long id) {
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
        return "content/operacion/solicitudServicioCliente/fechaCompromisoEntrega";
    }

    @RequestMapping(value = "/registerSolicituedServicioFechaRecepcionMuestras/{id}")
    public String fechaRecepcionMuestras(Model model, Principal principal, @PathVariable("id") Long id) {
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
        return "content/operacion/solicitudServicioCliente/fechaRecepcionMuestras";
    }

    private boolean containsMethod(List<MetodoMuestra> list, Method method) {
        for (MetodoMuestra metodoMuestra : list) {
            if (metodoMuestra.getMethod().equals(method)) {
                return true;
            }
        }
        return false;
    }
}
