package com.demo.model.operacion.metodos;

import com.demo.model.operacion.MetodoMuestra;

import javax.persistence.*;

@Entity
public class FRA_OIT_001 {

    @Id
    @GeneratedValue
    @Column(nullable = false)
    private Long idFRAOIT;

    @Column(length = 60, nullable = false)
    private String folioTecnica;

    @Column(length = 60, nullable = false)
    private String folioSolicitudServicioInterno;

    @Column(length = 60, nullable = false)
    private String idInternoMuestra;

    @Column(length = 60, nullable = false)
    private String fechaInicioAnalisis;

    @Column(length = 60, nullable = false)
    private String fechaFinalAnalisis;

    @Column(length = 60, nullable = false)
    private String temperatura;

    @Column(length = 60, nullable = false)
    private String humedadRelativa;

    @Column(length = 60, nullable = false)
    private String codigoDSC;

    @Column(length = 60, nullable = false)
    private String codigoBalanza;

    @Column(length = 60, nullable = false)
    private String tiempoIsoterma;

    @Column(length = 60, nullable = false)
    private String espesor1;

    @Column(length = 60, nullable = false)
    private String peso1;

    @Column(length = 60, nullable = false)
    private String ppmdsc1;

    @Column(length = 60, nullable = false)
    private String espesor2;

    @Column(length = 60, nullable = false)
    private String peso2;

    @Column(length = 60, nullable = false)
    private String ppmdsc2;

    @Column(length = 60, nullable = false)
    private String repeticion1OIT;

    @Column(length = 60, nullable = false)
    private String repeticion2OIT;

    @Column(length = 60, nullable = false)
    private String promedio;

    @Column(length = 250)
    private String observaciones;

    @Column(length = 250)
    private String realizo;

    @Column(length = 250)
    private String supervisor;

    @Column(length = 250, nullable = false)
    private String pathImage;

    @Column(length = 60, nullable = false)
    private String estatus;

    @Column(length = 60, nullable = false)
    private String numeroRepeticiones;

    @Column(length = 60, nullable = false)
    private String cantidadModificaciones;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "Metodo_Muestra_Id")
    private MetodoMuestra metodoMuestra;

    public FRA_OIT_001() {
    }

    public FRA_OIT_001(Long idFRAOIT, String folioTecnica, String folioSolicitudServicioInterno, String idInternoMuestra, String fechaInicioAnalisis, String fechaFinalAnalisis, String temperatura, String humedadRelativa, String codigoDSC, String codigoBalanza, String tiempoIsoterma, String espesor1, String peso1, String ppmdsc1, String espesor2, String peso2, String ppmdsc2, String repeticion1OIT, String repeticion2OIT, String promedio, String observaciones, String realizo, String supervisor, String pathImage, String estatus, MetodoMuestra metodoMuestra) {
        this.idFRAOIT = idFRAOIT;
        this.folioTecnica = folioTecnica;
        this.folioSolicitudServicioInterno = folioSolicitudServicioInterno;
        this.idInternoMuestra = idInternoMuestra;
        this.fechaInicioAnalisis = fechaInicioAnalisis;
        this.fechaFinalAnalisis = fechaFinalAnalisis;
        this.temperatura = temperatura;
        this.humedadRelativa = humedadRelativa;
        this.codigoDSC = codigoDSC;
        this.codigoBalanza = codigoBalanza;
        this.tiempoIsoterma = tiempoIsoterma;
        this.espesor1 = espesor1;
        this.peso1 = peso1;
        this.ppmdsc1 = ppmdsc1;
        this.espesor2 = espesor2;
        this.peso2 = peso2;
        this.ppmdsc2 = ppmdsc2;
        this.repeticion1OIT = repeticion1OIT;
        this.repeticion2OIT = repeticion2OIT;
        this.promedio = promedio;
        this.observaciones = observaciones;
        this.realizo = realizo;
        this.supervisor = supervisor;
        this.pathImage = pathImage;
        this.estatus = estatus;
        this.metodoMuestra = metodoMuestra;
    }

    public Long getIdFRAOIT() {
        return idFRAOIT;
    }

    public void setIdFRAOIT(Long idFRAOIT) {
        this.idFRAOIT = idFRAOIT;
    }

    public String getFolioTecnica() {
        return folioTecnica;
    }

    public void setFolioTecnica(String folioTecnica) {
        this.folioTecnica = folioTecnica;
    }

    public String getFolioSolicitudServicioInterno() {
        return folioSolicitudServicioInterno;
    }

    public void setFolioSolicitudServicioInterno(String folioSolicitudServicioInterno) {
        this.folioSolicitudServicioInterno = folioSolicitudServicioInterno;
    }

    public String getIdInternoMuestra() {
        return idInternoMuestra;
    }

    public void setIdInternoMuestra(String idInternoMuestra) {
        this.idInternoMuestra = idInternoMuestra;
    }

    public String getFechaInicioAnalisis() {
        return fechaInicioAnalisis;
    }

    public void setFechaInicioAnalisis(String fechaInicioAnalisis) {
        this.fechaInicioAnalisis = fechaInicioAnalisis;
    }

    public String getFechaFinalAnalisis() {
        return fechaFinalAnalisis;
    }

    public void setFechaFinalAnalisis(String fechaFinalAnalisis) {
        this.fechaFinalAnalisis = fechaFinalAnalisis;
    }

    public String getTemperatura() {
        return temperatura;
    }

    public void setTemperatura(String temperatura) {
        this.temperatura = temperatura;
    }

    public String getHumedadRelativa() {
        return humedadRelativa;
    }

    public void setHumedadRelativa(String humedadRelativa) {
        this.humedadRelativa = humedadRelativa;
    }

    public String getCodigoDSC() {
        return codigoDSC;
    }

    public void setCodigoDSC(String codigoDSC) {
        this.codigoDSC = codigoDSC;
    }

    public String getCodigoBalanza() {
        return codigoBalanza;
    }

    public void setCodigoBalanza(String codigoBalanza) {
        this.codigoBalanza = codigoBalanza;
    }

    public String getTiempoIsoterma() {
        return tiempoIsoterma;
    }

    public void setTiempoIsoterma(String tiempoIsoterma) {
        this.tiempoIsoterma = tiempoIsoterma;
    }

    public String getEspesor1() {
        return espesor1;
    }

    public void setEspesor1(String espesor1) {
        this.espesor1 = espesor1;
    }

    public String getPeso1() {
        return peso1;
    }

    public void setPeso1(String peso1) {
        this.peso1 = peso1;
    }

    public String getPpmdsc1() {
        return ppmdsc1;
    }

    public void setPpmdsc1(String ppmdsc1) {
        this.ppmdsc1 = ppmdsc1;
    }

    public String getEspesor2() {
        return espesor2;
    }

    public void setEspesor2(String espesor2) {
        this.espesor2 = espesor2;
    }

    public String getPeso2() {
        return peso2;
    }

    public void setPeso2(String peso2) {
        this.peso2 = peso2;
    }

    public String getPpmdsc2() {
        return ppmdsc2;
    }

    public void setPpmdsc2(String ppmdsc2) {
        this.ppmdsc2 = ppmdsc2;
    }

    public String getRepeticion1OIT() {
        return repeticion1OIT;
    }

    public void setRepeticion1OIT(String repeticion1OIT) {
        this.repeticion1OIT = repeticion1OIT;
    }

    public String getRepeticion2OIT() {
        return repeticion2OIT;
    }

    public void setRepeticion2OIT(String repeticion2OIT) {
        this.repeticion2OIT = repeticion2OIT;
    }

    public String getPromedio() {
        return promedio;
    }

    public void setPromedio(String promedio) {
        this.promedio = promedio;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    public String getRealizo() {
        return realizo;
    }

    public void setRealizo(String realizo) {
        this.realizo = realizo;
    }

    public String getSupervisor() {
        return supervisor;
    }

    public void setSupervisor(String supervisor) {
        this.supervisor = supervisor;
    }

    public String getPathImage() {
        return pathImage;
    }

    public void setPathImage(String pathImage) {
        this.pathImage = pathImage;
    }

    public MetodoMuestra getMetodoMuestra() {
        return metodoMuestra;
    }

    public void setMetodoMuestra(MetodoMuestra metodoMuestra) {
        this.metodoMuestra = metodoMuestra;
    }

    public String getEstatus() {
        return estatus;
    }

    public void setEstatus(String estatus) {
        this.estatus = estatus;
    }

    public String getNumeroRepeticiones() {
        return numeroRepeticiones;
    }

    public void setNumeroRepeticiones(String numeroRepeticiones) {
        this.numeroRepeticiones = numeroRepeticiones;
    }

    public String getCantidadModificaciones() {
        return cantidadModificaciones;
    }

    public void setCantidadModificaciones(String cantidadModificaciones) {
        this.cantidadModificaciones = cantidadModificaciones;
    }
}
