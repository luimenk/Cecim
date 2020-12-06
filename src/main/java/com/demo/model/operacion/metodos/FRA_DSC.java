package com.demo.model.operacion.metodos;

import com.demo.model.operacion.MetodoMuestra;

import javax.persistence.*;

@Entity
public class FRA_DSC {

    @Id
    @GeneratedValue
    @Column(nullable = false)
    private Long idFRADSC;

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
    private String codigoDSC;

    @Column(length = 30, nullable = false)
    private String codigoBalanza;

    //RESULTADOS
    @Column(length = 15, nullable = false)
    private String espesor1;

    @Column(length = 15, nullable = false)
    private String peso1;

    @Column(length = 15, nullable = false)
    private String ppmDSC1;

    //CONDICIONES DE ENSAYO
    @Column(length = 15, nullable = false)
    private String temperatura1;

    @Column(length = 15, nullable = false)
    private String flujoOxigeno1;

    @Column(length = 15, nullable = false)
    private String fnatmosfera1;

    @Column(length = 15, nullable = false)
    private String fnproteccion1;

    @Column(length = 15, nullable = false)
    private String temperatura2;

    @Column(length = 15, nullable = false)
    private String flujoOxigeno2;

    @Column(length = 15, nullable = false)
    private String fnatmosfera2;

    @Column(length = 15, nullable = false)
    private String fnproteccion2;

    @Column(length = 15, nullable = false)
    private String temperatura3;

    @Column(length = 15, nullable = false)
    private String flujoOxigeno3;

    @Column(length = 15, nullable = false)
    private String fnatmosfera3;

    @Column(length = 15, nullable = false)
    private String fnproteccion3;

    @Column(length = 15, nullable = false)
    private String temperatura4;

    @Column(length = 15, nullable = false)
    private String flujoOxigeno4;

    @Column(length = 15, nullable = false)
    private String fnatmosfera4;

    @Column(length = 15, nullable = false)
    private String fnproteccion4;

    @Column(length = 15, nullable = false)
    private String temperatura5;

    @Column(length = 15, nullable = false)
    private String flujoOxigeno5;

    @Column(length = 15, nullable = false)
    private String fnatmosfera5;

    @Column(length = 15, nullable = false)
    private String fnproteccion5;

    @Column(length = 15, nullable = false)
    private String temperatura6;

    @Column(length = 15, nullable = false)
    private String flujoOxigeno6;

    @Column(length = 15, nullable = false)
    private String fnatmosfera6;

    @Column(length = 15, nullable = false)
    private String fnproteccion6;

    @Column(length = 15, nullable = false)
    private String temperatura7;

    @Column(length = 15, nullable = false)
    private String flujoOxigeno7;

    @Column(length = 15, nullable = false)
    private String fnatmosfera7;

    @Column(length = 15, nullable = false)
    private String fnproteccion7;

    @Column(length = 15, nullable = false)
    private String temperatura8;

    @Column(length = 15, nullable = false)
    private String flujoOxigeno8;

    @Column(length = 15, nullable = false)
    private String fnatmosfera8;

    @Column(length = 15, nullable = false)
    private String fnproteccion8;

    @Column(length = 15, nullable = false)
    private String tasaCalentamiento;

    @Column(length = 15, nullable = false)
    private String tasaEnfriamiento;

    //RESULTADOS
    @Column(length = 15, nullable = false)
    private String temperaturaFusion1;

    @Column(length = 15, nullable = false)
    private String calorFusion1;

    @Column(length = 15, nullable = false)
    private String temperaturaCristalizacion1;

    @Column(length = 15, nullable = false)
    private String calorCristalizacion1;

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

    public FRA_DSC() {
    }

    public FRA_DSC(Long idFRADSC, String folioSolicitudServicioInterno, String idInternoMuestra, String fechaInicioAnalisis, String fechaFinalAnalisis, String temperatura, String humedadRelativa, String codigoDSC, String codigoBalanza, String espesor1, String peso1, String ppmDSC1, String temperatura1, String flujoOxigeno1, String fnatmosfera1, String fnproteccion1, String temperatura2, String flujoOxigeno2, String fnatmosfera2, String fnproteccion2, String temperatura3, String flujoOxigeno3, String fnatmosfera3, String fnproteccion3, String temperatura4, String flujoOxigeno4, String fnatmosfera4, String fnproteccion4, String temperatura5, String flujoOxigeno5, String fnatmosfera5, String fnproteccion5, String temperatura6, String flujoOxigeno6, String fnatmosfera6, String fnproteccion6, String temperatura7, String flujoOxigeno7, String fnatmosfera7, String fnproteccion7, String temperatura8, String flujoOxigeno8, String fnatmosfera8, String fnproteccion8, String tasaCalentamiento, String tasaEnfriamiento, String temperaturaFusion1, String calorFusion1, String temperaturaCristalizacion1, String calorCristalizacion1, String observaciones, String realizo, String supervisor, MetodoMuestra metodoMuestra) {
        this.idFRADSC = idFRADSC;
        this.folioSolicitudServicioInterno = folioSolicitudServicioInterno;
        this.idInternoMuestra = idInternoMuestra;
        this.fechaInicioAnalisis = fechaInicioAnalisis;
        this.fechaFinalAnalisis = fechaFinalAnalisis;
        this.temperatura = temperatura;
        this.humedadRelativa = humedadRelativa;
        this.codigoDSC = codigoDSC;
        this.codigoBalanza = codigoBalanza;
        this.espesor1 = espesor1;
        this.peso1 = peso1;
        this.ppmDSC1 = ppmDSC1;
        this.temperatura1 = temperatura1;
        this.flujoOxigeno1 = flujoOxigeno1;
        this.fnatmosfera1 = fnatmosfera1;
        this.fnproteccion1 = fnproteccion1;
        this.temperatura2 = temperatura2;
        this.flujoOxigeno2 = flujoOxigeno2;
        this.fnatmosfera2 = fnatmosfera2;
        this.fnproteccion2 = fnproteccion2;
        this.temperatura3 = temperatura3;
        this.flujoOxigeno3 = flujoOxigeno3;
        this.fnatmosfera3 = fnatmosfera3;
        this.fnproteccion3 = fnproteccion3;
        this.temperatura4 = temperatura4;
        this.flujoOxigeno4 = flujoOxigeno4;
        this.fnatmosfera4 = fnatmosfera4;
        this.fnproteccion4 = fnproteccion4;
        this.temperatura5 = temperatura5;
        this.flujoOxigeno5 = flujoOxigeno5;
        this.fnatmosfera5 = fnatmosfera5;
        this.fnproteccion5 = fnproteccion5;
        this.temperatura6 = temperatura6;
        this.flujoOxigeno6 = flujoOxigeno6;
        this.fnatmosfera6 = fnatmosfera6;
        this.fnproteccion6 = fnproteccion6;
        this.temperatura7 = temperatura7;
        this.flujoOxigeno7 = flujoOxigeno7;
        this.fnatmosfera7 = fnatmosfera7;
        this.fnproteccion7 = fnproteccion7;
        this.temperatura8 = temperatura8;
        this.flujoOxigeno8 = flujoOxigeno8;
        this.fnatmosfera8 = fnatmosfera8;
        this.fnproteccion8 = fnproteccion8;
        this.tasaCalentamiento = tasaCalentamiento;
        this.tasaEnfriamiento = tasaEnfriamiento;
        this.temperaturaFusion1 = temperaturaFusion1;
        this.calorFusion1 = calorFusion1;
        this.temperaturaCristalizacion1 = temperaturaCristalizacion1;
        this.calorCristalizacion1 = calorCristalizacion1;
        this.observaciones = observaciones;
        this.realizo = realizo;
        this.supervisor = supervisor;
        this.metodoMuestra = metodoMuestra;
    }

    public Long getIdFRADSC() {
        return idFRADSC;
    }

    public void setIdFRADSC(Long idFRADSC) {
        this.idFRADSC = idFRADSC;
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

    public String getPpmDSC1() {
        return ppmDSC1;
    }

    public void setPpmDSC1(String ppmDSC1) {
        this.ppmDSC1 = ppmDSC1;
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

    public String getFnatmosfera1() {
        return fnatmosfera1;
    }

    public void setFnatmosfera1(String fnatmosfera1) {
        this.fnatmosfera1 = fnatmosfera1;
    }

    public String getFnproteccion1() {
        return fnproteccion1;
    }

    public void setFnproteccion1(String fnproteccion1) {
        this.fnproteccion1 = fnproteccion1;
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

    public String getFnatmosfera2() {
        return fnatmosfera2;
    }

    public void setFnatmosfera2(String fnatmosfera2) {
        this.fnatmosfera2 = fnatmosfera2;
    }

    public String getFnproteccion2() {
        return fnproteccion2;
    }

    public void setFnproteccion2(String fnproteccion2) {
        this.fnproteccion2 = fnproteccion2;
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

    public String getFnatmosfera3() {
        return fnatmosfera3;
    }

    public void setFnatmosfera3(String fnatmosfera3) {
        this.fnatmosfera3 = fnatmosfera3;
    }

    public String getFnproteccion3() {
        return fnproteccion3;
    }

    public void setFnproteccion3(String fnproteccion3) {
        this.fnproteccion3 = fnproteccion3;
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

    public String getFnatmosfera4() {
        return fnatmosfera4;
    }

    public void setFnatmosfera4(String fnatmosfera4) {
        this.fnatmosfera4 = fnatmosfera4;
    }

    public String getFnproteccion4() {
        return fnproteccion4;
    }

    public void setFnproteccion4(String fnproteccion4) {
        this.fnproteccion4 = fnproteccion4;
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

    public String getFnatmosfera5() {
        return fnatmosfera5;
    }

    public void setFnatmosfera5(String fnatmosfera5) {
        this.fnatmosfera5 = fnatmosfera5;
    }

    public String getFnproteccion5() {
        return fnproteccion5;
    }

    public void setFnproteccion5(String fnproteccion5) {
        this.fnproteccion5 = fnproteccion5;
    }

    public String getTemperatura6() {
        return temperatura6;
    }

    public void setTemperatura6(String temperatura6) {
        this.temperatura6 = temperatura6;
    }

    public String getFlujoOxigeno6() {
        return flujoOxigeno6;
    }

    public void setFlujoOxigeno6(String flujoOxigeno6) {
        this.flujoOxigeno6 = flujoOxigeno6;
    }

    public String getFnatmosfera6() {
        return fnatmosfera6;
    }

    public void setFnatmosfera6(String fnatmosfera6) {
        this.fnatmosfera6 = fnatmosfera6;
    }

    public String getFnproteccion6() {
        return fnproteccion6;
    }

    public void setFnproteccion6(String fnproteccion6) {
        this.fnproteccion6 = fnproteccion6;
    }

    public String getTemperatura7() {
        return temperatura7;
    }

    public void setTemperatura7(String temperatura7) {
        this.temperatura7 = temperatura7;
    }

    public String getFlujoOxigeno7() {
        return flujoOxigeno7;
    }

    public void setFlujoOxigeno7(String flujoOxigeno7) {
        this.flujoOxigeno7 = flujoOxigeno7;
    }

    public String getFnatmosfera7() {
        return fnatmosfera7;
    }

    public void setFnatmosfera7(String fnatmosfera7) {
        this.fnatmosfera7 = fnatmosfera7;
    }

    public String getFnproteccion7() {
        return fnproteccion7;
    }

    public void setFnproteccion7(String fnproteccion7) {
        this.fnproteccion7 = fnproteccion7;
    }

    public String getTemperatura8() {
        return temperatura8;
    }

    public void setTemperatura8(String temperatura8) {
        this.temperatura8 = temperatura8;
    }

    public String getFlujoOxigeno8() {
        return flujoOxigeno8;
    }

    public void setFlujoOxigeno8(String flujoOxigeno8) {
        this.flujoOxigeno8 = flujoOxigeno8;
    }

    public String getFnatmosfera8() {
        return fnatmosfera8;
    }

    public void setFnatmosfera8(String fnatmosfera8) {
        this.fnatmosfera8 = fnatmosfera8;
    }

    public String getFnproteccion8() {
        return fnproteccion8;
    }

    public void setFnproteccion8(String fnproteccion8) {
        this.fnproteccion8 = fnproteccion8;
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

    public String getTemperaturaFusion1() {
        return temperaturaFusion1;
    }

    public void setTemperaturaFusion1(String temperaturaFusion1) {
        this.temperaturaFusion1 = temperaturaFusion1;
    }

    public String getCalorFusion1() {
        return calorFusion1;
    }

    public void setCalorFusion1(String calorFusion1) {
        this.calorFusion1 = calorFusion1;
    }

    public String getTemperaturaCristalizacion1() {
        return temperaturaCristalizacion1;
    }

    public void setTemperaturaCristalizacion1(String temperaturaCristalizacion1) {
        this.temperaturaCristalizacion1 = temperaturaCristalizacion1;
    }

    public String getCalorCristalizacion1() {
        return calorCristalizacion1;
    }

    public void setCalorCristalizacion1(String calorCristalizacion1) {
        this.calorCristalizacion1 = calorCristalizacion1;
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
