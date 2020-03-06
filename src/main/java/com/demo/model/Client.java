package com.demo.model;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonParser;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Map;

@Entity
public class Client {

    @Id
    @GeneratedValue
    @Column(name = "Client_Id", nullable = false)
    private Long clientId;

    @Column(name = "Folio_Cliente", length = 250, nullable = false)
    private String folioCliente;

    @Column(name = "Nombre_Razon_Social", length = 250, nullable = false)
    private String nombreRazonSocial;

    @Column(name = "Nombre_Comun_Empresa", length = 250, nullable = false)
    private String nombreComunEmpresa;

    @Column(name = "Calle", length = 250, nullable = false)
    private String calle;

    @Column(name = "Numero", length = 250, nullable = false)
    private String numero;

    @Column(name = "Colonia", length = 250, nullable = false)
    private String colonia;

    @Column(name = "Municipio", length = 250, nullable = false)
    private String municipio;

    @Column(name = "Estado", length = 250, nullable = false)
    private String estado;

    @Column(name = "Codigo_Postal", length = 250, nullable = false)
    private String codigoPostal;

    @Column(name = "rfc", length = 250, nullable = false)
    private String rfc;

    @Column(name = "contactos",nullable = false)
    private String contactosDatos;

    public Client() {
    }

    public Client(String folioCliente, String nombreRazonSocial, String nombreComunEmpresa, String calle, String numero, String colonia, String municipio, String estado, String codigoPostal, String rfc, String contactosDatos) {
        this.folioCliente = folioCliente;
        this.nombreRazonSocial = nombreRazonSocial;
        this.nombreComunEmpresa = nombreComunEmpresa;
        this.calle = calle;
        this.numero = numero;
        this.colonia = colonia;
        this.municipio = municipio;
        this.estado = estado;
        this.codigoPostal = codigoPostal;
        this.rfc = rfc;
        this.contactosDatos = contactosDatos;
    }

    public Long getClientId() {
        return clientId;
    }

    public void setClientId(Long clientId) {
        this.clientId = clientId;
    }

    public String getFolioCliente() {
        return folioCliente;
    }

    public void setFolioCliente(String folioCliente) {
        this.folioCliente = folioCliente;
    }

    public String getNombreRazonSocial() {
        return nombreRazonSocial;
    }

    public void setNombreRazonSocial(String nombreRazonSocial) {
        this.nombreRazonSocial = nombreRazonSocial;
    }

    public String getNombreComunEmpresa() {
        return nombreComunEmpresa;
    }

    public void setNombreComunEmpresa(String nombreComunEmpresa) {
        this.nombreComunEmpresa = nombreComunEmpresa;
    }

    public String getCalle() {
        return calle;
    }

    public void setCalle(String calle) {
        this.calle = calle;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getColonia() {
        return colonia;
    }

    public void setColonia(String colonia) {
        this.colonia = colonia;
    }

    public String getMunicipio() {
        return municipio;
    }

    public void setMunicipio(String municipio) {
        this.municipio = municipio;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getCodigoPostal() {
        return codigoPostal;
    }

    public void setCodigoPostal(String codigoPostal) {
        this.codigoPostal = codigoPostal;
    }

    public String getRfc() {
        return rfc;
    }

    public void setRfc(String rfc) {
        this.rfc = rfc;
    }

    public String getContactosDatos() {
        return contactosDatos;
    }

    public void setContactosDatos(String contactosDatos) {
        this.contactosDatos = contactosDatos;
    }
}
