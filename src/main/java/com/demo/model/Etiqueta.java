package com.demo.model;

import javax.persistence.*;

@Entity
public class Etiqueta {

    @Id
    @GeneratedValue
    @Column(name = "Etiqueta_Id", nullable = false)
    private Long etiquetaId;

    @Column(name = "Fecha", length = 250, nullable = false)
    private String fecha;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "Client_Id")
    private Client client;

    @Column(name = "Descripcion_Muestra", length = 250, nullable = false)
    private String descripcionMuestra;

    @Column(name = "Tipo_Material", length = 250, nullable = false)
    private String tipoMaterial;

    @Column(name = "Cantidad_Muestra", length = 250, nullable = false)
    private String cantidadMuestra;

    @Column(name = "Lote", length = 250, nullable = false)
    private String lote;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "Method_Id")
    private Method method;

    @Column(name = "Observaciones", length = 250, nullable = false)
    private String observaciones;

    public Etiqueta() {
    }

    public Etiqueta(Long etiquetaId, String fecha, Client client, String descripcionMuestra, String tipoMaterial, String cantidadMuestra, String lote, Method method, String observaciones) {
        this.etiquetaId = etiquetaId;
        this.fecha = fecha;
        this.client = client;
        this.descripcionMuestra = descripcionMuestra;
        this.tipoMaterial = tipoMaterial;
        this.cantidadMuestra = cantidadMuestra;
        this.lote = lote;
        this.method = method;
        this.observaciones = observaciones;
    }

    public Long getEtiquetaId() {
        return etiquetaId;
    }

    public void setEtiquetaId(Long etiquetaId) {
        this.etiquetaId = etiquetaId;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public String getDescripcionMuestra() {
        return descripcionMuestra;
    }

    public void setDescripcionMuestra(String descripcionMuestra) {
        this.descripcionMuestra = descripcionMuestra;
    }

    public String getTipoMaterial() {
        return tipoMaterial;
    }

    public void setTipoMaterial(String tipoMaterial) {
        this.tipoMaterial = tipoMaterial;
    }

    public String getCantidadMuestra() {
        return cantidadMuestra;
    }

    public void setCantidadMuestra(String cantidadMuestra) {
        this.cantidadMuestra = cantidadMuestra;
    }

    public String getLote() {
        return lote;
    }

    public void setLote(String lote) {
        this.lote = lote;
    }

    public Method getMethod() {
        return method;
    }

    public void setMethod(Method method) {
        this.method = method;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }
}
