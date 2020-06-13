package com.demo.controller;

import com.demo.model.AppRole;
import com.demo.repository.AppRoleRepository;
import com.demo.repository.UserRoleRepository;
import com.demo.service.AppRoleService;
import com.demo.service.MailService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.demo.model.AppUser;
import com.demo.model.UserRole;
import com.demo.service.AppUserService;
import com.demo.utils.EncryptedPasswordUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Map;

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
    private MailService mailService;

    Calendar calendario = new GregorianCalendar();

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
    public ResponseEntity<?> create(@RequestBody Map<String, String> request) throws Exception {

        EncryptedPasswordUtils encryptedPasswordUtils = new EncryptedPasswordUtils();

        AppUser valida = appUserService.findByUserName(request.get("userName"));

        System.out.println(request.get("userId"));
        System.out.println(request.get("userName"));

        if (valida != null){
            if (request.get("userId") == ""){
                APP.debug("Intento de registro de correo existente" + calendario.getTime());
                return new ResponseEntity<>(HttpStatus.CONFLICT);
            } else {
                APP.debug("Apartado de modificación" + calendario.getTime());
                System.out.println("Contraseña encriptada: " + encryptedPasswordUtils.encryte(request.get("password")));
                AppUser appUser = appUserService.findById(Long.parseLong(request.get("userId")));
                appUser.setUserName(request.get("userName"));
                appUser.setPassword(encryptedPasswordUtils.encryte(request.get("password")));
                appUser.setEnabled(true);
                appUser.setNombreUsuario(request.get("nombreUsuario"));
                appUser.setApellidoUsuario(request.get("apellidoUsuario"));
                appUser.setNacimiento(request.get("nacimiento"));
                appUser.setPuesto(request.get("puesto"));
                appUserService.save(appUser);

                AppRole appRole = appRoleService.findById(Long.parseLong(request.get("rolUsuario")));
                UserRole userRole = userRoleRepository.findByAppUser_UserName(appUser.getUserName());

                userRole.setAppRole(appRole);
                userRole.setAppUser(appUser);

                userRoleRepository.save(userRole);

                mailService.modificacionUsuario(appUser.getUserName(), request.get("password"));
                return new ResponseEntity<>(HttpStatus.OK);
            }
        }

        APP.debug("Apartado de modificación" + calendario.getTime());

        AppUser appUser = new AppUser();

        System.out.println("Contraseña encriptada: " + encryptedPasswordUtils.encryte(request.get("password")));

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
        mailService.registroUsuario(nuevaUnion.getUserName(), request.get("password"));

        return new ResponseEntity<>(HttpStatus.OK);
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
