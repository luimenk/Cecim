package com.demo.utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FixContactsJson {

    public static List<Map<String, String>> fixJsonContacts(List<Map<String, String>> contactos){
        List<Map<String,String>>contactosAux=new ArrayList<>();
        Map<String, String> mapaAux;
        String key;
        String valor;
        for (Map<String, String> contacto : contactos) {
            mapaAux = new HashMap<>();
            for (Map.Entry<String, String> entry : contacto.entrySet()) {
                key = entry.getKey();
                valor=entry.getValue();
                key=key.replaceAll("^\\d+|\\d+$", "");
                key="\""+key+"\"";
                valor="\""+valor+"\"";
                mapaAux.put(key, valor);
            }
            contactosAux.add(mapaAux);
        }
        return contactosAux;
    }
}

