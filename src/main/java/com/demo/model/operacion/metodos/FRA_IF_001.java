package com.demo.model.operacion.metodos;

import javax.persistence.*;

@Entity
public class FRA_IF_001 {

    @Id
    @GeneratedValue
    @Column(nullable = false)
    private Long idFRAIF;

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
    private String codigoBalanzaAnalitica;

    @Column(length = 30, nullable = false)
    private String codigoPlastometro;

    @Column(length = 30, nullable = false)
    private String temperaturaEnsayo;

    @Column(length = 30, nullable = false)
    private String pesaEnsayo;

    @Column(length = 30, nullable = false)
    private String tiempoCorte;

    //Resultados

    @Column(length = 30, nullable = false)
    private String peso;

    @Column(length = 30, nullable = false)
    private String indiceFluidez;

    //GENERALES
    @Column(length = 250, nullable = false)
    private String observaciones;

    @Column(length = 250, nullable = false)
    private String realizo;

    @Column(length = 250, nullable = false)
    private String supervisor;

    public FRA_IF_001() {
    }

    public FRA_IF_001(Long idFRAIF, String folioSolicitudServicioInterno, String idInternoMuestra, String fechaInicioAnalisis, String fechaFinalAnalisis, String temperatura, String humedadRelativa, String codigoBalanzaAnalitica, String codigoPlastometro, String temperaturaEnsayo, String pesaEnsayo, String tiempoCorte, String peso, String indiceFluidez, String observaciones, String realizo, String supervisor) {
        this.idFRAIF = idFRAIF;
        this.folioSolicitudServicioInterno = folioSolicitudServicioInterno;
        this.idInternoMuestra = idInternoMuestra;
        this.fechaInicioAnalisis = fechaInicioAnalisis;
        this.fechaFinalAnalisis = fechaFinalAnalisis;
        this.temperatura = temperatura;
        this.humedadRelativa = humedadRelativa;
        this.codigoBalanzaAnalitica = codigoBalanzaAnalitica;
        this.codigoPlastometro = codigoPlastometro;
        this.temperaturaEnsayo = temperaturaEnsayo;
        this.pesaEnsayo = pesaEnsayo;
        this.tiempoCorte = tiempoCorte;
        this.peso = peso;
        this.indiceFluidez = indiceFluidez;
        this.observaciones = observaciones;
        this.realizo = realizo;
        this.supervisor = supervisor;
    }

    public Long getIdFRAIF() {
        return idFRAIF;
    }

    public void setIdFRAIF(Long idFRAIF) {
        this.idFRAIF = idFRAIF;
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

    public String getCodigoBalanzaAnalitica() {
        return codigoBalanzaAnalitica;
    }

    public void setCodigoBalanzaAnalitica(String codigoBalanzaAnalitica) {
        this.codigoBalanzaAnalitica = codigoBalanzaAnalitica;
    }

    public String getCodigoPlastometro() {
        return codigoPlastometro;
    }

    public void setCodigoPlastometro(String codigoPlastometro) {
        this.codigoPlastometro = codigoPlastometro;
    }

    public String getTemperaturaEnsayo() {
        return temperaturaEnsayo;
    }

    public void setTemperaturaEnsayo(String temperaturaEnsayo) {
        this.temperaturaEnsayo = temperaturaEnsayo;
    }

    public String getPesaEnsayo() {
        return pesaEnsayo;
    }

    public void setPesaEnsayo(String pesaEnsayo) {
        this.pesaEnsayo = pesaEnsayo;
    }

    public String getTiempoCorte() {
        return tiempoCorte;
    }

    public void setTiempoCorte(String tiempoCorte) {
        this.tiempoCorte = tiempoCorte;
    }

    public String getPeso() {
        return peso;
    }

    public void setPeso(String peso) {
        this.peso = peso;
    }

    public String getIndiceFluidez() {
        return indiceFluidez;
    }

    public void setIndiceFluidez(String indiceFluidez) {
        this.indiceFluidez = indiceFluidez;
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
