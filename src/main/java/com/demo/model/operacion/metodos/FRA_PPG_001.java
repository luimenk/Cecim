package com.demo.model.operacion.metodos;

import com.demo.model.operacion.MetodoMuestra;

import javax.persistence.*;

@Entity
public class FRA_PPG_001 {

    @Id
    @GeneratedValue
    @Column(nullable = false)
    private Long idFRAPPG;

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
    private String peso1;

    @Column(length = 30, nullable = false)
    private String peso2;

    @Column(length = 30, nullable = false)
    private String peso3;

    @Column(length = 30, nullable = false)
    private String peso4;

    @Column(length = 30, nullable = false)
    private String peso5;

    @Column(length = 30, nullable = false)
    private String pellet1;

    @Column(length = 30, nullable = false)
    private String pellet2;

    @Column(length = 30, nullable = false)
    private String pellet3;

    @Column(length = 30, nullable = false)
    private String pellet4;

    @Column(length = 30, nullable = false)
    private String pellet5;

    @Column(length = 30, nullable = false)
    private String promedioPeso;

    @Column(length = 30, nullable = false)
    private String promedioPellet;

    @Column(length = 30, nullable = false)
    private String pelletXGramo;

    //GENERALES
    @Column(length = 250, nullable = false)
    private String observaciones;

    @Column(length = 250, nullable = false)
    private String realizo;

    @Column(length = 250, nullable = false)
    private String supervisor;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "Metodo_Muestra_Id")
    private MetodoMuestra metodoMuestra;

    public FRA_PPG_001() {
    }

    public FRA_PPG_001(Long idFRAPPG, String folioSolicitudServicioInterno, String idInternoMuestra, String fechaInicioAnalisis, String fechaFinalAnalisis, String temperatura, String humedadRelativa, String codigoBalanzaAnalitica, String peso1, String peso2, String peso3, String peso4, String peso5, String pellet1, String pellet2, String pellet3, String pellet4, String pellet5, String promedioPeso, String promedioPellet, String pelletXGramo, String observaciones, String realizo, String supervisor, MetodoMuestra metodoMuestra) {
        this.idFRAPPG = idFRAPPG;
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
        this.pellet1 = pellet1;
        this.pellet2 = pellet2;
        this.pellet3 = pellet3;
        this.pellet4 = pellet4;
        this.pellet5 = pellet5;
        this.promedioPeso = promedioPeso;
        this.promedioPellet = promedioPellet;
        this.pelletXGramo = pelletXGramo;
        this.observaciones = observaciones;
        this.realizo = realizo;
        this.supervisor = supervisor;
        this.metodoMuestra = metodoMuestra;
    }

    public Long getIdFRAPPG() {
        return idFRAPPG;
    }

    public void setIdFRAPPG(Long idFRAPPG) {
        this.idFRAPPG = idFRAPPG;
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

    public String getPellet1() {
        return pellet1;
    }

    public void setPellet1(String pellet1) {
        this.pellet1 = pellet1;
    }

    public String getPellet2() {
        return pellet2;
    }

    public void setPellet2(String pellet2) {
        this.pellet2 = pellet2;
    }

    public String getPellet3() {
        return pellet3;
    }

    public void setPellet3(String pellet3) {
        this.pellet3 = pellet3;
    }

    public String getPellet4() {
        return pellet4;
    }

    public void setPellet4(String pellet4) {
        this.pellet4 = pellet4;
    }

    public String getPellet5() {
        return pellet5;
    }

    public void setPellet5(String pellet5) {
        this.pellet5 = pellet5;
    }

    public String getPromedioPeso() {
        return promedioPeso;
    }

    public void setPromedioPeso(String promedioPeso) {
        this.promedioPeso = promedioPeso;
    }

    public String getPromedioPellet() {
        return promedioPellet;
    }

    public void setPromedioPellet(String promedioPellet) {
        this.promedioPellet = promedioPellet;
    }

    public String getPelletXGramo() {
        return pelletXGramo;
    }

    public void setPelletXGramo(String pelletXGramo) {
        this.pelletXGramo = pelletXGramo;
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

    public MetodoMuestra getMetodoMuestra() {
        return metodoMuestra;
    }

    public void setMetodoMuestra(MetodoMuestra metodoMuestra) {
        this.metodoMuestra = metodoMuestra;
    }
}
