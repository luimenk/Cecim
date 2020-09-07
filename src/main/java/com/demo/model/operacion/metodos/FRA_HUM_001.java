package com.demo.model.operacion.metodos;

import javax.persistence.*;

@Entity
public class FRA_HUM_001 {

    @Id
    @GeneratedValue
    @Column(nullable = false)
    private Long idFRAHUM;

    @Column(length = 30, nullable = false)
    private String folioSolicitudServicioInterno;

    @Column(length = 30, nullable = false)
    private String idInternoMuestra;

    @Column(length = 30, nullable = false)
    private String fechaInicioAnalisis;

    @Column(length = 30, nullable = false)
    private String fechaFinalAnalisis;

    @Column(length = 30, nullable = false)
    private String temperatura;

    @Column(length = 30, nullable = false)
    private String humedadRelativa;

    @Column(length = 30, nullable = false)
    private String codigoBalanza;

    @Column(length = 30, nullable = false)
    private String codigoHomo;

    @Column(length = 30, nullable = false)
    private String temperaturaEnsayo;

    @Column(length = 30, nullable = false)
    private String noCharola;

    @Column(length = 30, nullable = false)
    private String pesoCharola;

    @Column(length = 30, nullable = false)
    private String pesoMuestra;

    @Column(length = 30, nullable = false)
    private String pds1;

    @Column(length = 30, nullable = false)
    private String pds2;

    @Column(length = 30, nullable = false)
    private String pds3;

    @Column(length = 30, nullable = false)
    private String promedio;

    @Column(length = 30, nullable = false)
    private String humedad;

    //GENERALES
    @Column(length = 250, nullable = false)
    private String observaciones;

    @Column(length = 250, nullable = false)
    private String realizo;

    @Column(length = 250, nullable = false)
    private String supervisor;

    public FRA_HUM_001() {
    }

    public FRA_HUM_001(Long idFRAHUM, String folioSolicitudServicioInterno, String idInternoMuestra, String fechaInicioAnalisis, String fechaFinalAnalisis, String temperatura, String humedadRelativa, String codigoBalanza, String codigoHomo, String temperaturaEnsayo, String noCharola, String pesoCharola, String pesoMuestra, String pds1, String pds2, String pds3, String promedio, String humedad, String observaciones, String realizo, String supervisor) {
        this.idFRAHUM = idFRAHUM;
        this.folioSolicitudServicioInterno = folioSolicitudServicioInterno;
        this.idInternoMuestra = idInternoMuestra;
        this.fechaInicioAnalisis = fechaInicioAnalisis;
        this.fechaFinalAnalisis = fechaFinalAnalisis;
        this.temperatura = temperatura;
        this.humedadRelativa = humedadRelativa;
        this.codigoBalanza = codigoBalanza;
        this.codigoHomo = codigoHomo;
        this.temperaturaEnsayo = temperaturaEnsayo;
        this.noCharola = noCharola;
        this.pesoCharola = pesoCharola;
        this.pesoMuestra = pesoMuestra;
        this.pds1 = pds1;
        this.pds2 = pds2;
        this.pds3 = pds3;
        this.promedio = promedio;
        this.humedad = humedad;
        this.observaciones = observaciones;
        this.realizo = realizo;
        this.supervisor = supervisor;
    }

    public Long getIdFRAHUM() {
        return idFRAHUM;
    }

    public void setIdFRAHUM(Long idFRAHUM) {
        this.idFRAHUM = idFRAHUM;
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

    public String getCodigoBalanza() {
        return codigoBalanza;
    }

    public void setCodigoBalanza(String codigoBalanza) {
        this.codigoBalanza = codigoBalanza;
    }

    public String getCodigoHomo() {
        return codigoHomo;
    }

    public void setCodigoHomo(String codigoHomo) {
        this.codigoHomo = codigoHomo;
    }

    public String getTemperaturaEnsayo() {
        return temperaturaEnsayo;
    }

    public void setTemperaturaEnsayo(String temperaturaEnsayo) {
        this.temperaturaEnsayo = temperaturaEnsayo;
    }

    public String getNoCharola() {
        return noCharola;
    }

    public void setNoCharola(String noCharola) {
        this.noCharola = noCharola;
    }

    public String getPesoCharola() {
        return pesoCharola;
    }

    public void setPesoCharola(String pesoCharola) {
        this.pesoCharola = pesoCharola;
    }

    public String getPesoMuestra() {
        return pesoMuestra;
    }

    public void setPesoMuestra(String pesoMuestra) {
        this.pesoMuestra = pesoMuestra;
    }

    public String getPds1() {
        return pds1;
    }

    public void setPds1(String pds1) {
        this.pds1 = pds1;
    }

    public String getPds2() {
        return pds2;
    }

    public void setPds2(String pds2) {
        this.pds2 = pds2;
    }

    public String getPds3() {
        return pds3;
    }

    public void setPds3(String pds3) {
        this.pds3 = pds3;
    }

    public String getPromedio() {
        return promedio;
    }

    public void setPromedio(String promedio) {
        this.promedio = promedio;
    }

    public String getHumedad() {
        return humedad;
    }

    public void setHumedad(String humedad) {
        this.humedad = humedad;
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
}
