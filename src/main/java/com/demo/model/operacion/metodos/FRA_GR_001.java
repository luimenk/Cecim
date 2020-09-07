package com.demo.model.operacion.metodos;

import javax.persistence.*;

@Entity
public class FRA_GR_001 {

    @Id
    @GeneratedValue
    @Column(nullable = false)
    private Long fragr001Id;

    @Column(length = 250, nullable = false)
    private String folioSolicitudServicioInterno;

    @Column(length = 250, nullable = false)
    private String idInternoMuestra;

    @Column(length = 250, nullable = false)
    private String fechaInicioAnalisis;

    @Column(length = 250, nullable = false)
    private String fechaFinalAnalisis;

    @Column(length = 250, nullable = false)
    private String temperatura;

    @Column(length = 250, nullable = false)
    private String humedadRelativa;

    @Column(length = 250, nullable = false)
    private String codigoBalanzaAnalitica;

    @Column(length = 250, nullable = false)
    private String peso1;

    @Column(length = 250, nullable = false)
    private String peso2;

    @Column(length = 250, nullable = false)
    private String peso3;

    @Column(length = 250, nullable = false)
    private String peso4;

    @Column(length = 250, nullable = false)
    private String peso5;

    @Column(length = 250, nullable = false)
    private String promedio;

    @Column(length = 250, nullable = false)
    private String areaProbeta;

    @Column(length = 250, nullable = false)
    private String gramaje;

    @Column(length = 250, nullable = false)
    private String observaciones;

    @Column(length = 250, nullable = false)
    private String realizo;

    @Column(length = 250, nullable = false)
    private String supervisor;

    public FRA_GR_001() {
    }

    public FRA_GR_001(Long fragr001Id, String folioSolicitudServicioInterno, String idInternoMuestra, String fechaInicioAnalisis, String fechaFinalAnalisis, String temperatura, String humedadRelativa, String codigoBalanzaAnalitica, String peso1, String peso2, String peso3, String peso4, String peso5, String promedio, String areaProbeta, String gramaje, String observaciones, String realizo, String supervisor) {
        this.fragr001Id = fragr001Id;
        this.folioSolicitudServicioInterno = folioSolicitudServicioInterno;
        this.idInternoMuestra = idInternoMuestra;
        this.fechaInicioAnalisis = fechaInicioAnalisis;
        this.fechaFinalAnalisis = fechaFinalAnalisis;
        this.temperatura = temperatura;
        this.humedadRelativa = humedadRelativa;
        this.codigoBalanzaAnalitica = codigoBalanzaAnalitica;
        this.peso1 = peso1;
        this.peso2 = peso2;
        this.peso3 = peso3;
        this.peso4 = peso4;
        this.peso5 = peso5;
        this.promedio = promedio;
        this.areaProbeta = areaProbeta;
        this.gramaje = gramaje;
        this.observaciones = observaciones;
        this.realizo = realizo;
        this.supervisor = supervisor;
    }

    public Long getFragr001Id() {
        return fragr001Id;
    }

    public void setFragr001Id(Long fragr001Id) {
        this.fragr001Id = fragr001Id;
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

    public String getPeso1() {
        return peso1;
    }

    public void setPeso1(String peso1) {
        this.peso1 = peso1;
    }

    public String getPeso2() {
        return peso2;
    }

    public void setPeso2(String peso2) {
        this.peso2 = peso2;
    }

    public String getPeso3() {
        return peso3;
    }

    public void setPeso3(String peso3) {
        this.peso3 = peso3;
    }

    public String getPeso4() {
        return peso4;
    }

    public void setPeso4(String peso4) {
        this.peso4 = peso4;
    }

    public String getPeso5() {
        return peso5;
    }

    public void setPeso5(String peso5) {
        this.peso5 = peso5;
    }

    public String getPromedio() {
        return promedio;
    }

    public void setPromedio(String promedio) {
        this.promedio = promedio;
    }

    public String getAreaProbeta() {
        return areaProbeta;
    }

    public void setAreaProbeta(String areaProbeta) {
        this.areaProbeta = areaProbeta;
    }

    public String getGramaje() {
        return gramaje;
    }

    public void setGramaje(String gramaje) {
        this.gramaje = gramaje;
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
