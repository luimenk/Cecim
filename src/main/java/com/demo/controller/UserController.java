package com.demo.controller;

//import com.pmisys.admin.api.service.TokenValidator;

import com.demo.model.AppRole;
import com.demo.repository.AppRoleRepository;
import com.demo.repository.UserRoleRepository;
import com.demo.service.AppRoleService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.demo.model.AppUser;
import com.demo.model.UserRole;
import com.demo.service.AppUserService;
import com.demo.utils.EncryptedPasswordUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.http.MediaType;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

import java.util.List;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping(path = "/user")
public class UserController {

    @Autowired
    private AppUserService appUserService;

    @Autowired
    private AppRoleService appRoleService;

    @Autowired
    private UserRoleRepository userRoleRepository;

    @Autowired
    private JavaMailSender mailSender;

    private static final Logger APP = LoggerFactory.getLogger("info");
    private static final ObjectMapper MAPPER = new ObjectMapper();

    //ListarTodo
    @RequestMapping(method = RequestMethod.GET)
    @CrossOrigin(origins = "*", methods = {RequestMethod.GET})
    public List<UserRole> getAll() {
        System.out.println("Entró");
        return userRoleRepository.findAll();
    }

    //GuardarElemento
    @RequestMapping(method = RequestMethod.POST)
    @CrossOrigin(origins = "*", methods = {RequestMethod.POST})
    @ResponseStatus(code = HttpStatus.CREATED)
    public AppUser create(@RequestBody Map<String, String> request) throws Exception {

        EncryptedPasswordUtils encryptedPasswordUtils = new EncryptedPasswordUtils();

        System.out.println(encryptedPasswordUtils.encryte(request.get("password")));

        AppUser appUser = new AppUser();
        appUser.setUserName(request.get("userName"));
        appUser.setPassword(encryptedPasswordUtils.encryte(request.get("password")));
        appUser.setEnabled(true);
        appUser.setNombreUsuario(request.get("nombreUsuario"));
        appUser.setApellidoUsuario(request.get("apellidoUsuario"));
        appUser.setNacimiento(request.get("nacimiento"));
        appUser.setPuesto(request.get("puesto"));

        appUserService.save(appUser);

        AppUser nuevaUnion = appUserService.findByUserName(request.get("userName"));
        AppRole appRole = appRoleService.findById(Long.parseLong(request.get("rolUsuario")));
        UserRole userRole = new UserRole();

        userRole.setAppRole(appRole);
        userRole.setAppUser(nuevaUnion);

        userRoleRepository.save(userRole);

        SimpleMailMessage message = new SimpleMailMessage();

        message.setFrom("soporte_datasix@hotmail.com"); //Se indica de donde (quién) saldrá el Correo
        message.setTo(nuevaUnion.getUserName()); //Se indica el destinatario
        message.setSubject("Detalles de su cuenta de la aplicación"); //Se indica el asunto del Correo
        String cuerpoMensaje = "A continuación se describe el detalle de su cuenta: "
                + "\n\nUsuario: " + nuevaUnion.getUserName() + "\n\nContraseña: " + request.get("password") + ""
                + "\n\nEs un placer atenderlo.";
        message.setText(cuerpoMensaje); //Se indica el detalle del mensaje

        try {
            mailSender.send(message);
            return appUser;
        } catch (Exception e) {
            e.printStackTrace();
            //return "Error al enviar el mensaje";
        }
        return appUser;
    }

    //EliminarElemento
    @RequestMapping(method = RequestMethod.DELETE, value = "/{userId}")
    @CrossOrigin(origins = "*", methods = {RequestMethod.DELETE})
    public void delete(@PathVariable("userId") Long userId){

        AppUser appUser = appUserService.findById(userId);
        UserRole userRole = userRoleRepository.findByAppUser_UserName(appUser.getUserName());

        userRoleRepository.delete(userRole);
        appUserService.delete(userId);
    }
}
