package com.demo.model.operacion.metodos;

import com.demo.model.operacion.MetodoMuestra;
import com.demo.model.operacion.SolicitudServicioCliente;

import javax.persistence.*;

@Entity
public class FRA_AT_001 {

    @Id
    @GeneratedValue
    @Column(nullable = false)
    private Long idFRAAT;

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

    private String zona1;
    private String zona2;
    private String zona3;

    private String desprendimiento1;
    private String desprendimiento2;
    private String desprendimiento3;

    private String atp;

    @Column(length = 250, nullable = false)
    private String observaciones;

    @Column(length = 250, nullable = false)
    private String realizo;

    @Column(length = 250, nullable = false)
    private String supervisor;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "Metodo_Muestra_Id")
    private MetodoMuestra metodoMuestra;

    public FRA_AT_001() {
    }

    public FRA_AT_001(Long idFRAAT, String folioSolicitudServicioInterno, String idInternoMuestra, String fechaInicioAnalisis, String fechaFinalAnalisis, String temperatura, String humedadRelativa, String zona1, String zona2, String zona3, String desprendimiento1, String desprendimiento2, String desprendimiento3, String atp, String observaciones, String realizo, String supervisor, MetodoMuestra metodoMuestra) {
        this.idFRAAT = idFRAAT;
        this.folioSolicitudServicioInterno = folioSolicitudServicioInterno;
        this.idInternoMuestra = idInternoMuestra;
        this.fechaInicioAnalisis = fechaInicioAnalisis;
        this.fechaFinalAnalisis = fechaFinalAnalisis;
        this.temperatura = temperatura;
        this.humedadRelativa = humedadRelativa;
        this.zona1 = zona1;
        this.zona2 = zona2;
        this.zona3 = zona3;
        this.desprendimiento1 = desprendimiento1;
        this.desprendimiento2 = desprendimiento2;
        this.desprendimiento3 = desprendimiento3;
        this.atp = atp;
        this.observaciones = observaciones;
        this.realizo = realizo;
        this.supervisor = supervisor;
        this.metodoMuestra = metodoMuestra;
    }

    public Long getIdFRAAT() {
        return idFRAAT;
    }

    public void setIdFRAAT(Long idFRAAT) {
        this.idFRAAT = idFRAAT;
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

    public String getZona1() {
        return zona1;
    }

    public void setZona1(String zona1) {
        this.zona1 = zona1;
    }

    public String getZona2() {
        return zona2;
    }

    public void setZona2(String zona2) {
        this.zona2 = zona2;
    }

    public String getZona3() {
        return zona3;
    }

    public void setZona3(String zona3) {
        this.zona3 = zona3;
    }

    public String getDesprendimiento1() {
        return desprendimiento1;
    }

    public void setDesprendimiento1(String desprendimiento1) {
        this.desprendimiento1 = desprendimiento1;
    }

    public String getDesprendimiento2() {
        return desprendimiento2;
    }

    public void setDesprendimiento2(String desprendimiento2) {
        this.desprendimiento2 = desprendimiento2;
    }

    public String getDesprendimiento3() {
        return desprendimiento3;
    }

    public void setDesprendimiento3(String desprendimiento3) {
        this.desprendimiento3 = desprendimiento3;
    }

    public String getAtp() {
        return atp;
    }

    public void setAtp(String atp) {
        this.atp = atp;
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
