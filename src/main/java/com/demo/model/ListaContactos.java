package com.demo.model;

import java.util.List;
import java.util.Map;

public class ListaContactos {

    private Map<String, String> contactos;


    public Map<String, String> getContactos() {
        return contactos;
    }

    public void setContactos(Map<String, String> contactos) {
        this.contactos = contactos;
    }

    @Override
    public String toString() {
        return "ListaContactos{" +
                "contactos=" + contactos +
                '}';
    }
}
