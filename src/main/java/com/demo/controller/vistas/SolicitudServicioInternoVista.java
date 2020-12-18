package com.demo.controller.vistas;

import com.demo.model.Client;
import com.demo.model.Machine;
import com.demo.model.Method;
import com.demo.model.auxiliares.MetodosSeleccionados;
import com.demo.model.operacion.MetodoMuestra;
import com.demo.model.operacion.RecepcionVerificacionRegistroCodificacion;
import com.demo.model.operacion.SolicitudServicioCliente;
import com.demo.model.operacion.SolicitudServicioClienteMuestras;
import com.demo.service.*;
import com.demo.service.operacion.MetodoMuestraService;
import com.demo.service.operacion.RecepcionVerificacionRegistroCodificacionService;
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
public class SolicitudServicioInternoVista {

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

    @Autowired
    private RecepcionVerificacionRegistroCodificacionService recepcionVerificacionRegistroCodificacionService;

    @RequestMapping("/listSolicitudServicioInterno")
    public String listSolicitudServicioInterno(Model model, Principal principal) {
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

        return "content/operacion/solicitudServicioInterno/listSolicitudServicioInterno";
    }

    @RequestMapping("/detalleSolicitudServicioInterno/{id}")
    public String detalleSolicitudServicioInterno(Model model, Principal principal, @PathVariable("id") Long id) {
        // After user login successfully.
        String userName = principal.getName();

        User loginedUser = (User) ((Authentication) principal).getPrincipal();

        String userInfo = WebUtils.toString(loginedUser);

        Collection<GrantedAuthority> review = loginedUser.getAuthorities();

        model.addAttribute("userName", userName);
        model.addAttribute("userInfo", userInfo);
        int botonSolicitud = 0;

        SolicitudServicioCliente solicitudServicioCliente = solicitudServicioClienteService.findById(id);

        List<SolicitudServicioClienteMuestras> lista = solicitudServicioClienteMuestrasService.findAllBySolicitud(id);
        for (SolicitudServicioClienteMuestras solicitudServicioClienteMuestras : lista) {
            if (!solicitudServicioClienteMuestras.getEstatus().equals("SI")) {
                botonSolicitud++;
                break;
            }
        }

        List<RecepcionVerificacionRegistroCodificacion> listaRecepciones = new ArrayList<>();
        for (int i = 0; i<lista.size();i++){
            listaRecepciones.add(i, recepcionVerificacionRegistroCodificacionService.findBySolicitudServicioClienteMuestrasId(lista.get(i).getSolicitudServicioClienteMuestrasId()));
        }

        model.addAttribute("listaInterna", listaRecepciones);

        if (botonSolicitud!=0){
            model.addAttribute("botonSolicitud", false);
        } else {
            model.addAttribute("botonSolicitud", true);
        }

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

        for (GrantedAuthority a : review) {
            model.addAttribute("role", a.getAuthority());
        }

        return "content/operacion/solicitudServicioInterno/detalleSolicitudServicioInterno";
    }
}
