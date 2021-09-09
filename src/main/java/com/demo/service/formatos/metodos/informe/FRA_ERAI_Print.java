package com.demo.service.formatos.metodos.informe;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

@Service
public class FRA_ERAI_Print {

    private String getAttributeContacto(int bandera, JSONArray contactosAux, String attribute) throws JSONException {
        StringBuilder contacto = new StringBuilder();
        JSONObject jsonObject;
        for (int i = 0; i < contactosAux.length(); i++) {
            jsonObject = (JSONObject) contactosAux.get(i);
            if (attribute.equals("nombrePersonaContacto")) {
                if (bandera == i) {
                    return jsonObject.get("nombrePersonaContacto").toString();
                }
            }
            if (attribute.equals("cargo")) {
                if (bandera == i) {
                    return jsonObject.get("cargo").toString();
                }
            }
            if (attribute.equals("email")) {
                if (bandera == i) {
                    return jsonObject.get("email").toString();
                }
            }
            if (attribute.equals("telefonoOficina")) {
                if (bandera == i) {
                    return jsonObject.get("telefonoOficina").toString();
                }
            }
            if (attribute.equals("telefonoCelular")) {
                if (bandera == i) {
                    return jsonObject.get("telefonoCelular").toString();
                }
            }
        }
        return contacto.toString();
    }
}
