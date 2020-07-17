package com.demo.utils;

import java.util.Calendar;
import java.util.GregorianCalendar;

public class EstructuraNombres {

    public String getNombre(){
        String dia, mes, sec, nombre;
        int anio, me, di, ho, mi, se, mil;
        Calendar calendario = new GregorianCalendar();

        anio = calendario.get(Calendar.YEAR);
        me = calendario.get(Calendar.MONTH);
        di = calendario.get(Calendar.DAY_OF_MONTH);
        ho = calendario.get(Calendar.HOUR_OF_DAY);
        mi = calendario.get(Calendar.MINUTE);
        se = calendario.get(Calendar.SECOND);

        me++;

        if (di < 10) {
            dia = "0" + di;
        } else {
            dia = "" + di;
        }
        if (me < 10) {
            mes = "0" + me;
        } else {
            mes = "" + me;
        }
        if (se < 10) {
            sec = "0" + se;
        } else {
            sec = "" + se;
        }

        return "" + anio + mes + dia + "_" + ho + mi + sec;
    }

    public String getNombreQR(){
        String dia, mes, sec, nombre;
        int anio, me, di, ho, mi, se, mil;
        Calendar calendario = new GregorianCalendar();

        anio = calendario.get(Calendar.YEAR);
        me = calendario.get(Calendar.MONTH);
        di = calendario.get(Calendar.DAY_OF_MONTH);
        ho = calendario.get(Calendar.HOUR_OF_DAY);
        mi = calendario.get(Calendar.MINUTE);
        se = calendario.get(Calendar.SECOND);
        mil = calendario.get(Calendar.MILLISECOND);

        me++;

        if (di < 10) {
            dia = "0" + di;
        } else {
            dia = "" + di;
        }
        if (me < 10) {
            mes = "0" + me;
        } else {
            mes = "" + me;
        }
        if (se < 10) {
            sec = "0" + se;
        } else {
            sec = "" + se;
        }

        return "" + anio + mes + dia + "_" + ho + mi + sec + mil;
    }
}
