package com.demo.model.operacion.metodos;

import com.demo.model.operacion.MetodoMuestra;

import javax.persistence.*;

@Entity
public class FRA_PO_001 {

    @Id
    @GeneratedValue
    @Column(nullable = false)
    private Long idFRAPO;

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
    private String codigoOXTRAN;

    @Column(length = 30, nullable = false)
    private String codigoMicrometro;

    //Condiciones
    @Column(length = 30, nullable = false)
    private String espesor1;

    @Column(length = 30, nullable = false)
    private String tiempoPurga1;

    @Column(length = 30, nullable = false)
    private String tipoMascarilla1;

    @Column(length = 30, nullable = false)
    private String espesor2;

    @Column(length = 30, nullable = false)
    private String tiempoPurga2;

    @Column(length = 30, nullable = false)
    private String tipoMascarilla2;

    @Column(length = 30, nullable = false)
    private String numeroCiclos;

    @Column(length = 30, nullable = false)
    private String tiempoCiclo;

    @Column(length = 30, nullable = false)
    private String convergencia;

    //Resultados
    @Column(length = 30, nullable = false)
    private String permeabilidadOxigeno1;

    @Column(length = 30, nullable = false)
    private String permeabilidadOxigeno2;

    //GENERALES
    @Column(length = 250, nullable = false)
    private String observaciones;

    @Column(length = 250, nullable = false)
    private String realizo;

    @Column(length = 250, nullable = false)
    private String supervisor;

    //Path de Imagen
    private String pathImagen;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "Metodo_Muestra_Id")
    private MetodoMuestra metodoMuestra;

    public FRA_PO_001() {
    }

    public FRA_PO_001(Long idFRAPO, String folioSolicitudServicioInterno, String idInternoMuestra, String fechaInicioAnalisis, String fechaFinalAnalisis, String temperatura, String humedadRelativa, String codigoOXTRAN, String codigoMicrometro, String espesor1, String tiempoPurga1, String tipoMascarilla1, String espesor2, String tiempoPurga2, String tipoMascarilla2, String numeroCiclos, String tiempoCiclo, String convergencia, String permeabilidadOxigeno1, String permeabilidadOxigeno2, String observaciones, String realizo, String supervisor, String pathImagen, MetodoMuestra metodoMuestra) {
        this.idFRAPO = idFRAPO;
        this.folioSolicitudServicioInterno = folioSolicitudServicioInterno;
        this.idInternoMuestra = idInternoMuestra;
        this.fechaInicioAnalisis = fechaInicioAnalisis;
        this.fechaFinalAnalisis = fechaFinalAnalisis;
        this.temperatura = temperatura;
        this.humedadRelativa = humedadRelativa;
        this.codigoOXTRAN = codigoOXTRAN;
        this.codigoMicrometro = codigoMicrometro;
        this.espesor1 = espesor1;
        this.tiempoPurga1 = tiempoPurga1;
        this.tipoMascarilla1 = tipoMascarilla1;
        this.espesor2 = espesor2;
        this.tiempoPurga2 = tiempoPurga2;
        this.tipoMascarilla2 = tipoMascarilla2;
        this.numeroCiclos = numeroCiclos;
        this.tiempoCiclo = tiempoCiclo;
        this.convergencia = convergencia;
        this.permeabilidadOxigeno1 = permeabilidadOxigeno1;
        this.permeabilidadOxigeno2 = permeabilidadOxigeno2;
        this.observaciones = observaciones;
        this.realizo = realizo;
        this.supervisor = supervisor;
        this.pathImagen = pathImagen;
        this.metodoMuestra = metodoMuestra;
    }

    public Long getIdFRAPO() {
        return idFRAPO;
    }

    public void setIdFRAPO(Long idFRAPO) {
        this.idFRAPO = idFRAPO;
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

    public String getCodigoOXTRAN() {
        return codigoOXTRAN;
    }

    public void setCodigoOXTRAN(String codigoOXTRAN) {
        this.codigoOXTRAN = codigoOXTRAN;
    }

    public String getCodigoMicrometro() {
        return codigoMicrometro;
    }

    public void setCodigoMicrometro(String codigoMicrometro) {
        this.codigoMicrometro = codigoMicrometro;
    }

    public String getEspesor1() {
        return espesor1;
    }

    public void setEspesor1(String espesor1) {
        this.espesor1 = espesor1;
    }

    public String getTiempoPurga1() {
        return tiempoPurga1;
    }

    public void setTiempoPurga1(String tiempoPurga1) {
        this.tiempoPurga1 = tiempoPurga1;
    }

    public String getTipoMascarilla1() {
        return tipoMascarilla1;
    }

    public void setTipoMascarilla1(String tipoMascarilla1) {
        this.tipoMascarilla1 = tipoMascarilla1;
    }

    public String getEspesor2() {
        return espesor2;
    }

    public void setEspesor2(String espesor2) {
        this.espesor2 = espesor2;
    }

    public String getTiempoPurga2() {
        return tiempoPurga2;
    }

    public void setTiempoPurga2(String tiempoPurga2) {
        this.tiempoPurga2 = tiempoPurga2;
    }

    public String getTipoMascarilla2() {
        return tipoMascarilla2;
    }

    public void setTipoMascarilla2(String tipoMascarilla2) {
        this.tipoMascarilla2 = tipoMascarilla2;
    }

    public String getNumeroCiclos() {
        return numeroCiclos;
    }

    public void setNumeroCiclos(String numeroCiclos) {
        this.numeroCiclos = numeroCiclos;
    }

    public String getTiempoCiclo() {
        return tiempoCiclo;
    }

    public void setTiempoCiclo(String tiempoCiclo) {
        this.tiempoCiclo = tiempoCiclo;
    }

    public String getConvergencia() {
        return convergencia;
    }

    public void setConvergencia(String convergencia) {
        this.convergencia = convergencia;
    }

    public String getPermeabilidadOxigeno1() {
        return permeabilidadOxigeno1;
    }

    public void setPermeabilidadOxigeno1(String permeabilidadOxigeno1) {
        this.permeabilidadOxigeno1 = permeabilidadOxigeno1;
    }

    public String getPermeabilidadOxigeno2() {
        return permeabilidadOxigeno2;
    }

    public void setPermeabilidadOxigeno2(String permeabilidadOxigeno2) {
        this.permeabilidadOxigeno2 = permeabilidadOxigeno2;
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

    public String getPathImagen() {
        return pathImagen;
    }

    public void setPathImagen(String pathImagen) {
        this.pathImagen = pathImagen;
    }

    public MetodoMuestra getMetodoMuestra() {
        return metodoMuestra;
    }

    public void setMetodoMuestra(MetodoMuestra metodoMuestra) {
        this.metodoMuestra = metodoMuestra;
    }
}
