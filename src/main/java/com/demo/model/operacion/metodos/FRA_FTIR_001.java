package com.demo.model.operacion.metodos;

import com.demo.model.operacion.MetodoMuestra;

import javax.persistence.*;

@Entity
public class FRA_FTIR_001 {

    @Id
    @GeneratedValue
    @Column(nullable = false)
    private Long compuestosEspectrometriaInfrarrojaId;

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
    private String codigoEspectrometro;

    @Column(length = 250, nullable = false)
    private String compuesto1;

    @Column(length = 250, nullable = false)
    private String identidad1;

    @Column(length = 250, nullable = false)
    private String compuesto2;

    @Column(length = 250, nullable = false)
    private String identidad2;

    @Column(length = 250, nullable = false)
    private String observaciones;

    @Column(length = 250, nullable = false)
    private String realizo;

    @Column(length = 250, nullable = false)
    private String supervisor;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "Metodo_Muestra_Id")
    private MetodoMuestra metodoMuestra;


    public FRA_FTIR_001() {
    }

    public FRA_FTIR_001(Long compuestosEspectrometriaInfrarrojaId, String folioSolicitudServicioInterno, String idInternoMuestra, String fechaInicioAnalisis, String fechaFinalAnalisis, String temperatura, String humedadRelativa, String codigoEspectrometro, String compuesto1, String identidad1, String compuesto2, String identidad2, String observaciones, String realizo, String supervisor, MetodoMuestra metodoMuestra) {
        this.compuestosEspectrometriaInfrarrojaId = compuestosEspectrometriaInfrarrojaId;
        this.folioSolicitudServicioInterno = folioSolicitudServicioInterno;
        this.idInternoMuestra = idInternoMuestra;
        this.fechaInicioAnalisis = fechaInicioAnalisis;
        this.fechaFinalAnalisis = fechaFinalAnalisis;
        this.temperatura = temperatura;
        this.humedadRelativa = humedadRelativa;
        this.codigoEspectrometro = codigoEspectrometro;
        this.compuesto1 = compuesto1;
        this.identidad1 = identidad1;
        this.compuesto2 = compuesto2;
        this.identidad2 = identidad2;
        this.observaciones = observaciones;
        this.realizo = realizo;
        this.supervisor = supervisor;
        this.metodoMuestra = metodoMuestra;
    }

    public Long getCompuestosEspectrometriaInfrarrojaId() {
        return compuestosEspectrometriaInfrarrojaId;
    }

    public void setCompuestosEspectrometriaInfrarrojaId(Long compuestosEspectrometriaInfrarrojaId) {
        this.compuestosEspectrometriaInfrarrojaId = compuestosEspectrometriaInfrarrojaId;
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

    public String getCodigoEspectrometro() {
        return codigoEspectrometro;
    }

    public void setCodigoEspectrometro(String codigoEspectrometro) {
        this.codigoEspectrometro = codigoEspectrometro;
    }

    public String getCompuesto1() {
        return compuesto1;
    }

    public void setCompuesto1(String compuesto1) {
        this.compuesto1 = compuesto1;
    }

    public String getIdentidad1() {
        return identidad1;
    }

    public void setIdentidad1(String identidad1) {
        this.identidad1 = identidad1;
    }

    public String getCompuesto2() {
        return compuesto2;
    }

    public void setCompuesto2(String compuesto2) {
        this.compuesto2 = compuesto2;
    }

    public String getIdentidad2() {
        return identidad2;
    }

    public void setIdentidad2(String identidad2) {
        this.identidad2 = identidad2;
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
