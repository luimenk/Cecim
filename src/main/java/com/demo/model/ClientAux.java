package com.demo.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ClientAux extends Client {

    private List<Map<String, String>> contactos;

    public List<Map<String, String>> getContactos() {

        return contactos;
    }

    public void setContactos(List<Map<String, String>> contactos) {
        List<Map<String,String>>contactosAux=new ArrayList<>();
        Map<String, String> mapaAux;
        String key;
        String valor;
        for (Map<String, String> contacto : contactos) {
            mapaAux = new HashMap<>();
            for (Map.Entry<String, String> entry : contacto.entrySet()) {
                key = entry.getKey();
                valor=entry.getValue();
                valor="\""+valor+"\"";
                key=key.replaceAll("^\\d+|\\d+$", "");
                key="\""+key+"\"";
                mapaAux.put(key, valor);
            }
            contactosAux.add(mapaAux);
        }
        this.contactos = contactosAux;
    }

    @Override
    public String toString() {

        return  "ClientAux{" +
                "contactos=" + contactos +
                '}';
    }
}
