package com.demo.component;

import com.demo.model.RecuperaCuenta;
import com.demo.service.RecuperaCuentaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

@Component
public class SchedulingTasksComponent {

    private static final Logger LOGGER = LoggerFactory.getLogger(SchedulingTasksComponent.class);
    private static final SimpleDateFormat SIMPLE_DATE_FORMAT = new SimpleDateFormat("HH:mm:ss");

    /*@Autowired
    private RecuperaCuentaService recuperaCuentaService;

    @Scheduled(fixedRate = 5000)
    public void reportCurrentTime() {
        //LOGGER.info("The time is now {}", SIMPLE_DATE_FORMAT.format(new Date()));
        RecuperaCuenta recuperaCuenta = recuperaCuentaService.findByCorreo("luimenk@gmail.com");

        Timestamp timestamp3 = new Timestamp(System.currentTimeMillis());
        Timestamp timestamp1 = recuperaCuenta.getCreatedAt();
        System.out.println("******************* ESTA PRUEBA ES MIA ****************************");
        long resultante = timestamp3.getTime() - timestamp1.getTime();
        System.out.println("Milisegundos: " + resultante);

        int segundos = (int) resultante/1000;
        System.out.println("Segundos: " + segundos);

        int horas = segundos / 3600;
        int minutos = (segundos % 3600) / 60;
        segundos = (segundos % 3600) % 60;

        System.out.println("Horas: " + horas);
        System.out.println("Minutos: " + minutos);
        System.out.println("Segundos: " + segundos);
    }*/
}
