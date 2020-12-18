package com.demo.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class FormatoFechas {

    SimpleDateFormat formateador = new SimpleDateFormat("MMMdd-yyyy", new Locale("ES"));
    SimpleDateFormat formatoSQL = new SimpleDateFormat("yyyy-MM-dd", new Locale("ES"));

    public String formateadorFechas(String fecha){
        Date date = null;
        try {
            date = formatoSQL.parse(fecha);
        } catch (ParseException ex){
            System.out.println();
        }

        if ("Linux".equals(System.getProperty("os.name"))){
            return formateador.format(date).toUpperCase().replace(".", "");
        } else {
            return formateador.format(date).toUpperCase();
        }
    }

    public String fechaActual(){
        Date ahora = new Date();

        if ("Linux".equals(System.getProperty("os.name"))){
            return formateador.format(ahora).toUpperCase().replace(".", "");
        } else {
            return formateador.format(ahora).toUpperCase();
        }
    }
}
