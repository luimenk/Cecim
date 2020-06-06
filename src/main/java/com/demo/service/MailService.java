package com.demo.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;

@Service
public class MailService {

    private static final Logger LOGGER = LoggerFactory.getLogger(MailService.class);

    @Autowired
    private JavaMailSender mailSender;

    @Async
    public void codigoMail(String username, String text){
        /** ENVÍO DE CORREOS. FUNCIONAL, COMENTADO PARA EJECUCIÓN DE PRUEBAS **/
        SimpleMailMessage message = new SimpleMailMessage();

        message.setFrom("cecim.sistema@gmail.com"); //Se indica de donde (quién) saldrá el Correo
        message.setTo(username); //Se indica el destinatario
        message.setSubject("Recuperación de contraseña"); //Se indica el asunto del Correo
        String cuerpoMensaje = "Se ha generado el código para continuar con el proceso: "
                + "\n\nCódigo:  " + text + ""
                + "\n\nSi no lo ha hecho favor de hacer caso omiso."
                + "\n\nEs un placer atenderlo.";
        message.setText(cuerpoMensaje); //Se indica el detalle del mensaje

        try {
            mailSender.send(message);
        } catch (Exception e) {
            e.printStackTrace();
            //return "Error al enviar el mensaje";
        }
    }

    @Async
    public void recuperaContrasena(String username, String password){
        /** ENVÍO DE CORREOS. FUNCIONAL, COMENTADO PARA EJECUCIÓN DE PRUEBAS **/
        SimpleMailMessage message = new SimpleMailMessage();

        message.setFrom("cecim.sistema@gmail.com"); //Se indica de donde (quién) saldrá el Correo
        message.setTo(username); //Se indica el destinatario
        message.setSubject("Contraseña Cambiada"); //Se indica el asunto del Correo
        String cuerpoMensaje = "Se ha cambiado la contraseña. A continuación se describe el detalle de su cuenta: "
                + "\n\nUsuario: " + username + "\n\nContraseña: " + password + ""
                + "\n\nEs un placer atenderlo.";
        message.setText(cuerpoMensaje); //Se indica el detalle del mensaje

        try {
            mailSender.send(message);
            //return "";
        } catch (Exception e) {
            e.printStackTrace();
            //return "Error al enviar el mensaje";
        }
    }

}
