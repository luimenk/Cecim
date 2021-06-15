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
import org.apache.catalina.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

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

//    //ListarTodo
//    @RequestMapping(method = RequestMethod.GET)
//    @CrossOrigin(origins = "*", methods = {RequestMethod.GET})
//    public List<UserRole> getAll() {
//        System.out.println("Entró");
//        return userRoleRepository.findAll();
//    }

    //ListarTodo
    @RequestMapping(method = RequestMethod.GET)
    @CrossOrigin(origins = "*", methods = {RequestMethod.GET})
    public List<AppUser> getAllFiltered() {
        System.out.println("Entró");
        return appUserService.findAllFiltered(1);
    }


    //Listar los roles por usuername
    @RequestMapping(method = RequestMethod.GET, value = "/user/{username}")
    @CrossOrigin(origins = "*", methods = {RequestMethod.GET})
    public List<UserRole> getAllByUsername(@PathVariable("username") String username) {
        return userRoleRepository.findAllByAppUser_UserName(username);
    }

    //GuardarElemento
    @RequestMapping(method = RequestMethod.POST)
    @CrossOrigin(origins = "*", methods = {RequestMethod.POST})
    @ResponseStatus(code = HttpStatus.CREATED)
    public ResponseEntity<?> create(@RequestBody Map<String, Object> request) throws Exception {

        List<String> lista = new ArrayList<String>((ArrayList<String>) request.get("permisos"));

        for (int i = 0; i < lista.size(); i++) {
            System.out.println(lista.get(i));
        }

        EncryptedPasswordUtils encryptedPasswordUtils = new EncryptedPasswordUtils();
        AppUser appUser = new AppUser();

        appUser.setUserName(request.get("userName").toString());
        appUser.setPassword(encryptedPasswordUtils.encryte(request.get("password1").toString()));
        appUser.setEnabled(true);
        appUser.setNombreUsuario(request.get("nombreUsuario").toString());
        appUser.setApellidoUsuario(request.get("apellidoUsuario").toString());
        appUser.setNacimiento(request.get("nacimiento").toString());
        appUser.setPuesto(request.get("puesto").toString());
        appUser.setVisible(1);

        appUserService.save(appUser);

        for (int i = 0; i < lista.size(); i++) {
            UserRole userRole = new UserRole();
            AppRole appRole = appRoleService.findById(Long.parseLong(lista.get(i)));
            userRole.setAppRole(appRole);
            userRole.setAppUser(appUser);
            userRoleRepository.save(userRole);
        }

        mailService.registroUsuario(appUser.getUserName(), request.get("password1").toString());

        /*

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
*/
        return new ResponseEntity<>(HttpStatus.OK);
    }

    //Modificar
    @RequestMapping(method = RequestMethod.POST, path = "/modificar")
    @CrossOrigin(origins = "*", methods = {RequestMethod.POST})
    @ResponseStatus(code = HttpStatus.CREATED)
    public ResponseEntity<?> modificar(@RequestBody Map<String, Object> request) throws Exception {

        List<String> lista = new ArrayList<String>((ArrayList<String>) request.get("permisos"));

        EncryptedPasswordUtils encryptedPasswordUtils = new EncryptedPasswordUtils();
        AppUser appUser = appUserService.findById(Long.parseLong(request.get("userId").toString()));

        appUser.setUserName(request.get("userName").toString());
        if (request.get("password1").toString().equals("")) {
            System.out.println("La contraseña no cambiará");
        } else {
            appUser.setPassword(encryptedPasswordUtils.encryte(request.get("password1").toString()));
        }
        appUser.setEnabled(true);
        appUser.setNombreUsuario(request.get("nombreUsuario").toString());
        appUser.setApellidoUsuario(request.get("apellidoUsuario").toString());
        appUser.setNacimiento(request.get("nacimiento").toString());
        appUser.setPuesto(request.get("puesto").toString());
        appUser.setVisible(1);

        appUserService.save(appUser);

        List<UserRole> userRoles = userRoleRepository.findAllByAppUser_UserId(appUser.getUserId());

        for (int j = 0; j<userRoles.size(); j++) {
            userRoleRepository.delete(userRoles.get(j));
        }

        for (int i = 0; i < lista.size(); i++) {
            UserRole userRole = new UserRole();
            AppRole appRole = appRoleService.findById(Long.parseLong(lista.get(i)));
            userRole.setAppRole(appRole);
            userRole.setAppUser(appUser);
            userRoleRepository.save(userRole);
        }

        mailService.registroUsuario(appUser.getUserName(), request.get("password1").toString());

        return new ResponseEntity<>(HttpStatus.OK);
    }

    //EliminarElemento
    @RequestMapping(method = RequestMethod.DELETE, value = "/{userId}")
    @CrossOrigin(origins = "*", methods = {RequestMethod.DELETE})
    public void delete(@PathVariable("userId") Long userId){

        AppUser appUser = appUserService.findById(userId);
//        UserRole userRole = userRoleRepository.findByAppUser_UserName(appUser.getUserName());
        List<UserRole> userRoles = userRoleRepository.findAllByAppUser_UserId(appUser.getUserId());

        for (int j = 0; j<userRoles.size(); j++) {
            userRoleRepository.delete(userRoles.get(j));
        }

        appUserService.delete(userId);
    }
}
