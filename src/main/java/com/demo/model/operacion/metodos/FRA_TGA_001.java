package com.demo.model.operacion.metodos;

import com.demo.model.operacion.MetodoMuestra;

import javax.persistence.*;

@Entity
public class FRA_TGA_001 {

    @Id
    @GeneratedValue
    @Column(nullable = false)
    private Long idFRATGA;

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
    private String codigoTGA;

    @Column(length = 30, nullable = false)
    private String codigoBalanza;

    //Resultados
    @Column(length = 30, nullable = false)
    private String peso;

    @Column(length = 30, nullable = false)
    private String ppmTGA;

    //Condiciones de ensayo
    @Column(length = 30, nullable = false)
    private String temperatura1;

    @Column(length = 30, nullable = false)
    private String flujoOxigeno1;

    @Column(length = 30, nullable = false)
    private String fnpa1;

    @Column(length = 30, nullable = false)
    private String fnpp1;

    @Column(length = 30, nullable = false)
    private String temperatura2;

    @Column(length = 30, nullable = false)
    private String flujoOxigeno2;

    @Column(length = 30, nullable = false)
    private String fnpa2;

    @Column(length = 30, nullable = false)
    private String fnpp2;

    @Column(length = 30, nullable = false)
    private String temperatura3;

    @Column(length = 30, nullable = false)
    private String flujoOxigeno3;

    @Column(length = 30, nullable = false)
    private String fnpa3;

    @Column(length = 30, nullable = false)
    private String fnpp3;

    @Column(length = 30, nullable = false)
    private String temperatura4;

    @Column(length = 30, nullable = false)
    private String flujoOxigeno4;

    @Column(length = 30, nullable = false)
    private String fnpa4;

    @Column(length = 30, nullable = false)
    private String fnpp4;

    @Column(length = 30, nullable = false)
    private String temperatura5;

    @Column(length = 30, nullable = false)
    private String flujoOxigeno5;

    @Column(length = 30, nullable = false)
    private String fnpa5;

    @Column(length = 30, nullable = false)
    private String fnpp5;

    @Column(length = 30, nullable = false)
    private String tasaCalentamiento;

    @Column(length = 30, nullable = false)
    private String tasaEnfriamiento;

    //Resultados
    @Column(length = 30, nullable = false)
    private String rangoTemperatura1;

    @Column(length = 30, nullable = false)
    private String cambioMasa1;

    @Column(length = 30, nullable = false)
    private String rangoTemperatura2;

    @Column(length = 30, nullable = false)
    private String cambioMasa2;

    @Column(length = 30, nullable = false)
    private String rangoTemperatura3;

    @Column(length = 30, nullable = false)
    private String cambioMasa3;

    @Column(length = 30, nullable = false)
    private String rangoTemperatura4;

    @Column(length = 30, nullable = false)
    private String cambioMasa4;

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

    public FRA_TGA_001() {
    }

    public FRA_TGA_001(Long idFRATGA, String folioSolicitudServicioInterno, String idInternoMuestra, String fechaInicioAnalisis, String fechaFinalAnalisis, String temperatura, String humedadRelativa, String codigoTGA, String codigoBalanza, String peso, String ppmTGA, String temperatura1, String flujoOxigeno1, String fnpa1, String fnpp1, String temperatura2, String flujoOxigeno2, String fnpa2, String fnpp2, String temperatura3, String flujoOxigeno3, String fnpa3, String fnpp3, String temperatura4, String flujoOxigeno4, String fnpa4, String fnpp4, String temperatura5, String flujoOxigeno5, String fnpa5, String fnpp5, String tasaCalentamiento, String tasaEnfriamiento, String rangoTemperatura1, String cambioMasa1, String rangoTemperatura2, String cambioMasa2, String rangoTemperatura3, String cambioMasa3, String rangoTemperatura4, String cambioMasa4, String observaciones, String realizo, String supervisor, String pathImagen, MetodoMuestra metodoMuestra) {
        this.idFRATGA = idFRATGA;
        this.folioSolicitudServicioInterno = folioSolicitudServicioInterno;
        this.idInternoMuestra = idInternoMuestra;
        this.fechaInicioAnalisis = fechaInicioAnalisis;
        this.fechaFinalAnalisis = fechaFinalAnalisis;
        this.temperatura = temperatura;
        this.humedadRelativa = humedadRelativa;
        this.codigoTGA = codigoTGA;
        this.codigoBalanza = codigoBalanza;
        this.peso = peso;
        this.ppmTGA = ppmTGA;
        this.temperatura1 = temperatura1;
        this.flujoOxigeno1 = flujoOxigeno1;
        this.fnpa1 = fnpa1;
        this.fnpp1 = fnpp1;
        this.temperatura2 = temperatura2;
        this.flujoOxigeno2 = flujoOxigeno2;
        this.fnpa2 = fnpa2;
        this.fnpp2 = fnpp2;
        this.temperatura3 = temperatura3;
        this.flujoOxigeno3 = flujoOxigeno3;
        this.fnpa3 = fnpa3;
        this.fnpp3 = fnpp3;
        this.temperatura4 = temperatura4;
        this.flujoOxigeno4 = flujoOxigeno4;
        this.fnpa4 = fnpa4;
        this.fnpp4 = fnpp4;
        this.temperatura5 = temperatura5;
        this.flujoOxigeno5 = flujoOxigeno5;
        this.fnpa5 = fnpa5;
        this.fnpp5 = fnpp5;
        this.tasaCalentamiento = tasaCalentamiento;
        this.tasaEnfriamiento = tasaEnfriamiento;
        this.rangoTemperatura1 = rangoTemperatura1;
        this.cambioMasa1 = cambioMasa1;
        this.rangoTemperatura2 = rangoTemperatura2;
        this.cambioMasa2 = cambioMasa2;
        this.rangoTemperatura3 = rangoTemperatura3;
        this.cambioMasa3 = cambioMasa3;
        this.rangoTemperatura4 = rangoTemperatura4;
        this.cambioMasa4 = cambioMasa4;
        this.observaciones = observaciones;
        this.realizo = realizo;
        this.supervisor = supervisor;
        this.pathImagen = pathImagen;
        this.metodoMuestra = metodoMuestra;
    }

    public Long getIdFRATGA() {
        return idFRATGA;
    }

    public void setIdFRATGA(Long idFRATGA) {
        this.idFRATGA = idFRATGA;
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

    public String getCodigoTGA() {
        return codigoTGA;
    }

    public void setCodigoTGA(String codigoTGA) {
        this.codigoTGA = codigoTGA;
    }

    public String getCodigoBalanza() {
        return codigoBalanza;
    }

    public void setCodigoBalanza(String codigoBalanza) {
        this.codigoBalanza = codigoBalanza;
    }

    public String getPeso() {
        return peso;
    }

    public void setPeso(String peso) {
        this.peso = peso;
    }

    public String getPpmTGA() {
        return ppmTGA;
    }

    public void setPpmTGA(String ppmTGA) {
        this.ppmTGA = ppmTGA;
    }

    public String getTemperatura1() {
        return temperatura1;
    }

    public void setTemperatura1(String temperatura1) {
        this.temperatura1 = temperatura1;
    }

    public String getFlujoOxigeno1() {
        return flujoOxigeno1;
    }

    public void setFlujoOxigeno1(String flujoOxigeno1) {
        this.flujoOxigeno1 = flujoOxigeno1;
    }

    public String getFnpa1() {
        return fnpa1;
    }

    public void setFnpa1(String fnpa1) {
        this.fnpa1 = fnpa1;
    }

    public String getFnpp1() {
        return fnpp1;
    }

    public void setFnpp1(String fnpp1) {
        this.fnpp1 = fnpp1;
    }

    public String getTemperatura2() {
        return temperatura2;
    }

    public void setTemperatura2(String temperatura2) {
        this.temperatura2 = temperatura2;
    }

    public String getFlujoOxigeno2() {
        return flujoOxigeno2;
    }

    public void setFlujoOxigeno2(String flujoOxigeno2) {
        this.flujoOxigeno2 = flujoOxigeno2;
    }

    public String getFnpa2() {
        return fnpa2;
    }

    public void setFnpa2(String fnpa2) {
        this.fnpa2 = fnpa2;
    }

    public String getFnpp2() {
        return fnpp2;
    }

    public void setFnpp2(String fnpp2) {
        this.fnpp2 = fnpp2;
    }

    public String getTemperatura3() {
        return temperatura3;
    }

    public void setTemperatura3(String temperatura3) {
        this.temperatura3 = temperatura3;
    }

    public String getFlujoOxigeno3() {
        return flujoOxigeno3;
    }

    public void setFlujoOxigeno3(String flujoOxigeno3) {
        this.flujoOxigeno3 = flujoOxigeno3;
    }

    public String getFnpa3() {
        return fnpa3;
    }

    public void setFnpa3(String fnpa3) {
        this.fnpa3 = fnpa3;
    }

    public String getFnpp3() {
        return fnpp3;
    }

    public void setFnpp3(String fnpp3) {
        this.fnpp3 = fnpp3;
    }

    public String getTemperatura4() {
        return temperatura4;
    }

    public void setTemperatura4(String temperatura4) {
        this.temperatura4 = temperatura4;
    }

    public String getFlujoOxigeno4() {
        return flujoOxigeno4;
    }

    public void setFlujoOxigeno4(String flujoOxigeno4) {
        this.flujoOxigeno4 = flujoOxigeno4;
    }

    public String getFnpa4() {
        return fnpa4;
    }

    public void setFnpa4(String fnpa4) {
        this.fnpa4 = fnpa4;
    }

    public String getFnpp4() {
        return fnpp4;
    }

    public void setFnpp4(String fnpp4) {
        this.fnpp4 = fnpp4;
    }

    public String getTemperatura5() {
        return temperatura5;
    }

    public void setTemperatura5(String temperatura5) {
        this.temperatura5 = temperatura5;
    }

    public String getFlujoOxigeno5() {
        return flujoOxigeno5;
    }

    public void setFlujoOxigeno5(String flujoOxigeno5) {
        this.flujoOxigeno5 = flujoOxigeno5;
    }

    public String getFnpa5() {
        return fnpa5;
    }

    public void setFnpa5(String fnpa5) {
        this.fnpa5 = fnpa5;
    }

    public String getFnpp5() {
        return fnpp5;
    }

    public void setFnpp5(String fnpp5) {
        this.fnpp5 = fnpp5;
    }

    public String getTasaCalentamiento() {
        return tasaCalentamiento;
    }

    public void setTasaCalentamiento(String tasaCalentamiento) {
        this.tasaCalentamiento = tasaCalentamiento;
    }

    public String getTasaEnfriamiento() {
        return tasaEnfriamiento;
    }

    public void setTasaEnfriamiento(String tasaEnfriamiento) {
        this.tasaEnfriamiento = tasaEnfriamiento;
    }

    public String getRangoTemperatura1() {
        return rangoTemperatura1;
    }

    public void setRangoTemperatura1(String rangoTemperatura1) {
        this.rangoTemperatura1 = rangoTemperatura1;
    }

    public String getCambioMasa1() {
        return cambioMasa1;
    }

    public void setCambioMasa1(String cambioMasa1) {
        this.cambioMasa1 = cambioMasa1;
    }

    public String getRangoTemperatura2() {
        return rangoTemperatura2;
    }

    public void setRangoTemperatura2(String rangoTemperatura2) {
        this.rangoTemperatura2 = rangoTemperatura2;
    }

    public String getCambioMasa2() {
        return cambioMasa2;
    }

    public void setCambioMasa2(String cambioMasa2) {
        this.cambioMasa2 = cambioMasa2;
    }

    public String getRangoTemperatura3() {
        return rangoTemperatura3;
    }

    public void setRangoTemperatura3(String rangoTemperatura3) {
        this.rangoTemperatura3 = rangoTemperatura3;
    }

    public String getCambioMasa3() {
        return cambioMasa3;
    }

    public void setCambioMasa3(String cambioMasa3) {
        this.cambioMasa3 = cambioMasa3;
    }

    public String getRangoTemperatura4() {
        return rangoTemperatura4;
    }

    public void setRangoTemperatura4(String rangoTemperatura4) {
        this.rangoTemperatura4 = rangoTemperatura4;
    }

    public String getCambioMasa4() {
        return cambioMasa4;
    }

    public void setCambioMasa4(String cambioMasa4) {
        this.cambioMasa4 = cambioMasa4;
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
