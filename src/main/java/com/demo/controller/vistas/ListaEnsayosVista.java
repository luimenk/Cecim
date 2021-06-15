package com.demo.controller.vistas;

import com.demo.model.Client;
import com.demo.model.Folios;
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
public class ListaEnsayosVista {

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

    @RequestMapping("/listEnsayos")
    public String listSolicitudServicioPagos(Model model, Principal principal) {
        // After user login successfully.
        String userName = principal.getName();

        User loginedUser = (User) ((Authentication) principal).getPrincipal();

        String userInfo = WebUtils.toString(loginedUser);

        Collection<GrantedAuthority> review = loginedUser.getAuthorities();

        model.addAttribute("userName", userName);
        model.addAttribute("userInfo", userInfo);

        List<Folios> folios = foliosService.findAll();

        model.addAttribute("folioAT", folios.get(5).getConsecutivo());
        model.addAttribute("folioDI", folios.get(6).getConsecutivo());
        model.addAttribute("folioES", folios.get(7).getConsecutivo());
        model.addAttribute("folioGR", folios.get(8).getConsecutivo());
        model.addAttribute("folioHUM", folios.get(9).getConsecutivo());
        model.addAttribute("folioNCP", folios.get(10).getConsecutivo());
        model.addAttribute("folioPPG", folios.get(11).getConsecutivo());
        model.addAttribute("folioFTIR", folios.get(12).getConsecutivo());
        model.addAttribute("folioTGA", folios.get(13).getConsecutivo());
        model.addAttribute("folioICO", folios.get(14).getConsecutivo());
        model.addAttribute("folioEAT", folios.get(15).getConsecutivo());
        model.addAttribute("folioEAUV", folios.get(16).getConsecutivo());
        model.addAttribute("folioEAXE", folios.get(17).getConsecutivo());
        model.addAttribute("folioOIT", folios.get(18).getConsecutivo());
        model.addAttribute("folioDSC", folios.get(19).getConsecutivo());
        model.addAttribute("folioCST", folios.get(20).getConsecutivo());
        model.addAttribute("folioIF", folios.get(21).getConsecutivo());
        model.addAttribute("folioPO", folios.get(22).getConsecutivo());
        model.addAttribute("folioPRR", folios.get(23).getConsecutivo());
        model.addAttribute("folioRTER", folios.get(24).getConsecutivo());

        List<Machine> lista = machineService.findAll();
        model.addAttribute("maquinas", lista);

        for (GrantedAuthority a : review) {
            model.addAttribute("role", a.getAuthority());
        }

        return "content/operacion/metodos/listaEnsayos";
    }
}
