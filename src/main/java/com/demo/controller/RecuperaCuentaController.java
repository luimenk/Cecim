package com.demo.controller;

//import com.pmisys.admin.api.service.TokenValidator;

import com.demo.model.AppUser;
import com.demo.model.UserRole;
import com.demo.model.RecuperaCuenta;
import com.demo.service.AppUserService;
import com.demo.service.MailService;
import com.demo.service.UserRoleService;
import com.demo.service.RecuperaCuentaService;
import com.demo.utils.EncryptedPasswordUtils;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.sql.Timestamp;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.security.SecureRandom;
import java.math.BigInteger;

@RestController
@RequestMapping(path = "/recuperaCuenta")
public class RecuperaCuentaController {

    @Autowired
    private MailService mailService;

    @Autowired
    private UserRoleService userRoleService;

    @Autowired
    private AppUserService appUserService;

    @Autowired
    private RecuperaCuentaService recuperaCuentaService;

    @Autowired
    private JavaMailSender mailSender;

    String correoArrastrado;

    private static final Logger APP = LoggerFactory.getLogger("info");
    private static final ObjectMapper MAPPER = new ObjectMapper();

    //Genera Código y envia el correo
    @RequestMapping(path = "/correo",method = RequestMethod.POST)
    @CrossOrigin(origins = "*", methods = {RequestMethod.POST})
    @ResponseStatus(code = HttpStatus.CREATED)
    public ResponseEntity<?> create(@RequestBody Map<String, String> request) throws Exception {

        System.out.println(request.get("username"));

        SecureRandom random = new SecureRandom();
        String text = new BigInteger(30, random).toString(32);
        System.out.println(text);

        UserRole userRole = userRoleService.findByAppUserUserName(request.get("username"));

        if (userRole == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        System.out.println(userRole.getAppUser().getNombreUsuario());
        System.out.println(userRole.getAppUser().getUserId());

        RecuperaCuenta recuperaCuenta = new RecuperaCuenta();
        recuperaCuenta.setCorreo(request.get("username"));
        recuperaCuenta.setCodigo(text);
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        recuperaCuenta.setCreatedAt(timestamp);
        recuperaCuentaService.save(recuperaCuenta);
        mailService.codigoMail(request.get("username"), text);
        return new ResponseEntity<>(HttpStatus.OK);
        //return new ResponseEntity<>(HttpStatus.OK);
    }

    //Valida el código y envía a nueva contraseña
    @RequestMapping(path = "/codigo",method = RequestMethod.POST)
    @CrossOrigin(origins = "*", methods = {RequestMethod.POST})
    @ResponseStatus(code = HttpStatus.CREATED)
    public String create2(@RequestBody Map<String, String> request) throws Exception {

        System.out.println(request.get("codigo"));

        RecuperaCuenta recuperaCuenta = new RecuperaCuenta();
        recuperaCuenta = recuperaCuentaService.findByCodigo(request.get("codigo"));

        if (recuperaCuenta.getCodigo().equals(request.get("codigo"))){
            EncryptedPasswordUtils encryptedPasswordUtils = new EncryptedPasswordUtils();
            AppUser appUser = new AppUser();
            appUser = appUserService.findByUserName(recuperaCuenta.getCorreo());
            appUser.setPassword(encryptedPasswordUtils.encryte(request.get("password")));
            appUserService.save(appUser);

            mailService.recuperaContrasena(recuperaCuenta.getCorreo(), request.get("password"));

            recuperaCuentaService.delete(recuperaCuenta.getRecuperaId());

        } else {
            System.out.println("Ocurrió un error.");
        }
        return "";
    }

    /*@RequestMapping(method = RequestMethod.PUT, value = "/{blockId}")
    @CrossOrigin(origins = "*", methods = {RequestMethod.PUT})
    public Document update(@RequestHeader("Authorization") String auth,
                           @PathVariable("blockId") Integer id,
                           @RequestBody Map<String, String> request) throws Exception {
        tokenValidator.validateToken(auth);

        Document data = blockService.findFirstById(id);

        if (data == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }

        data.setName(request.get("name"));
        data.setBody(request.get("body"));
        blockService.save(data);

        return data;
    }*/

    /*@RequestMapping(method = RequestMethod.GET, value = "/{blockId}")
    @CrossOrigin(origins = "*", methods = {RequestMethod.GET})
    public Document get(@RequestHeader("Authorization") String auth,
                        @PathVariable("blockId") Integer id) {
        APP.info("GET AUTH = " + auth);
        tokenValidator.validateToken(auth);

        Document data = (Document) blockService.findFirstById(id);

        if (data == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }

        if (data.getBody() == null) {
            data.setBody("");
        }

        return data;
    }*/
}
