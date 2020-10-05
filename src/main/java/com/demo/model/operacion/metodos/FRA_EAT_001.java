package com.demo.model.operacion.metodos;

import javax.persistence.*;

@Entity
public class FRA_EAT_001 {

    @Id
    @GeneratedValue
    @Column(nullable = false)
    private Long idFRAEAT;

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
    private String temperaturaEnsayo;

    @Column(length = 60, nullable = false)
    private String codigoHorno;

    @Column(length = 60)
    private String tiempoTotalExposicion;

    @Column(length = 250)
    private String observaciones;

    @Column(length = 250)
    private String realizo;

    @Column(length = 250)
    private String supervisor;

    private String estatus;

    public FRA_EAT_001() {
    }

    public FRA_EAT_001(Long idFRAEAT, String folioSolicitudServicioInterno, String idInternoMuestra, String fechaInicioAnalisis, String fechaFinalAnalisis, String temperatura, String humedadRelativa, String temperaturaEnsayo, String codigoHorno, String tiempoTotalExposicion, String observaciones, String realizo, String supervisor, String estatus) {
        this.idFRAEAT = idFRAEAT;
        this.folioSolicitudServicioInterno = folioSolicitudServicioInterno;
        this.idInternoMuestra = idInternoMuestra;
        this.fechaInicioAnalisis = fechaInicioAnalisis;
        this.fechaFinalAnalisis = fechaFinalAnalisis;
        this.temperatura = temperatura;
        this.humedadRelativa = humedadRelativa;
        this.temperaturaEnsayo = temperaturaEnsayo;
        this.codigoHorno = codigoHorno;
        this.tiempoTotalExposicion = tiempoTotalExposicion;
        this.observaciones = observaciones;
        this.realizo = realizo;
        this.supervisor = supervisor;
        this.estatus = estatus;
    }

    public Long getIdFRAEAT() {
        return idFRAEAT;
    }

    public void setIdFRAEAT(Long idFRAEAT) {
        this.idFRAEAT = idFRAEAT;
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

    public String getTemperaturaEnsayo() {
        return temperaturaEnsayo;
    }

    public void setTemperaturaEnsayo(String temperaturaEnsayo) {
        this.temperaturaEnsayo = temperaturaEnsayo;
    }

    public String getCodigoHorno() {
        return codigoHorno;
    }

    public void setCodigoHorno(String codigoHorno) {
        this.codigoHorno = codigoHorno;
    }

    public String getTiempoTotalExposicion() {
        return tiempoTotalExposicion;
    }

    public void setTiempoTotalExposicion(String tiempoTotalExposicion) {
        this.tiempoTotalExposicion = tiempoTotalExposicion;
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

    public String getEstatus() {
        return estatus;
    }

    public void setEstatus(String estatus) {
        this.estatus = estatus;
    }
}
