package com.demo.model.operacion.metodos;

import com.demo.model.operacion.MetodoMuestra;

import javax.persistence.*;

@Entity
public class FRA_DI_001 {

    @Id
    @GeneratedValue
    @Column(nullable = false)
    private Long determinacionDimensionesId;

    @Column(length = 30, nullable = false)
    private String folioTecnica;

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
    private String codigoRegla;

    @Column(length = 250, nullable = false)
    private String largo1;

    @Column(length = 250, nullable = false)
    private String ancho1;

    @Column(length = 250, nullable = false)
    private String fuelle11;

    @Column(length = 250, nullable = false)
    private String fuelle21;

    @Column(length = 250, nullable = false)
    private String largo2;

    @Column(length = 250, nullable = false)
    private String ancho2;

    @Column(length = 250, nullable = false)
    private String fuelle12;

    @Column(length = 250, nullable = false)
    private String fuelle22;

    @Column(length = 250, nullable = false)
    private String largo3;

    @Column(length = 250, nullable = false)
    private String ancho3;

    @Column(length = 250, nullable = false)
    private String fuelle13;

    @Column(length = 250, nullable = false)
    private String fuelle23;

    @Column(length = 250, nullable = false)
    private String largo4;

    @Column(length = 250, nullable = false)
    private String ancho4;

    @Column(length = 250, nullable = false)
    private String fuelle14;

    @Column(length = 250, nullable = false)
    private String fuelle24;

    @Column(length = 250, nullable = false)
    private String largo5;

    @Column(length = 250, nullable = false)
    private String ancho5;

    @Column(length = 250, nullable = false)
    private String fuelle15;

    @Column(length = 250, nullable = false)
    private String fuelle25;

    @Column(length = 250, nullable = false)
    private String promedioLargo;

    @Column(length = 250, nullable = false)
    private String promedioAncho;

    @Column(length = 250, nullable = false)
    private String promedioFuelle1;

    @Column(length = 250, nullable = false)
    private String promedioFuelle2;

    @Column(length = 250, nullable = false)
    private String sumatoriaFuellePromedio;

    @Column(length = 250, nullable = false)
    private String observaciones;

    @Column(length = 250, nullable = false)
    private String realizo;

    @Column(length = 250, nullable = false)
    private String supervisor;

    @Column(length = 30)
    private String estatus;

    @Column(length = 5)
    private String cantidadModificaciones;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "Metodo_Muestra_Id")
    private MetodoMuestra metodoMuestra;

    public FRA_DI_001() {
    }

    public FRA_DI_001(Long determinacionDimensionesId, String folioTecnica, String folioSolicitudServicioInterno, String idInternoMuestra, String fechaInicioAnalisis, String fechaFinalAnalisis, String temperatura, String humedadRelativa, String codigoRegla, String largo1, String ancho1, String fuelle11, String fuelle21, String largo2, String ancho2, String fuelle12, String fuelle22, String largo3, String ancho3, String fuelle13, String fuelle23, String largo4, String ancho4, String fuelle14, String fuelle24, String largo5, String ancho5, String fuelle15, String fuelle25, String promedioLargo, String promedioAncho, String promedioFuelle1, String promedioFuelle2, String sumatoriaFuellePromedio, String observaciones, String realizo, String supervisor, String estatus, String cantidadModificaciones, MetodoMuestra metodoMuestra) {
        this.determinacionDimensionesId = determinacionDimensionesId;
        this.folioTecnica = folioTecnica;
        this.folioSolicitudServicioInterno = folioSolicitudServicioInterno;
        this.idInternoMuestra = idInternoMuestra;
        this.fechaInicioAnalisis = fechaInicioAnalisis;
        this.fechaFinalAnalisis = fechaFinalAnalisis;
        this.temperatura = temperatura;
        this.humedadRelativa = humedadRelativa;
        this.codigoRegla = codigoRegla;
        this.largo1 = largo1;
        this.ancho1 = ancho1;
        this.fuelle11 = fuelle11;
        this.fuelle21 = fuelle21;
        this.largo2 = largo2;
        this.ancho2 = ancho2;
        this.fuelle12 = fuelle12;
        this.fuelle22 = fuelle22;
        this.largo3 = largo3;
        this.ancho3 = ancho3;
        this.fuelle13 = fuelle13;
        this.fuelle23 = fuelle23;
        this.largo4 = largo4;
        this.ancho4 = ancho4;
        this.fuelle14 = fuelle14;
        this.fuelle24 = fuelle24;
        this.largo5 = largo5;
        this.ancho5 = ancho5;
        this.fuelle15 = fuelle15;
        this.fuelle25 = fuelle25;
        this.promedioLargo = promedioLargo;
        this.promedioAncho = promedioAncho;
        this.promedioFuelle1 = promedioFuelle1;
        this.promedioFuelle2 = promedioFuelle2;
        this.sumatoriaFuellePromedio = sumatoriaFuellePromedio;
        this.observaciones = observaciones;
        this.realizo = realizo;
        this.supervisor = supervisor;
        this.estatus = estatus;
        this.cantidadModificaciones = cantidadModificaciones;
        this.metodoMuestra = metodoMuestra;
    }

    public Long getDeterminacionDimensionesId() {
        return determinacionDimensionesId;
    }

    public void setDeterminacionDimensionesId(Long determinacionDimensionesId) {
        this.determinacionDimensionesId = determinacionDimensionesId;
    }

    public String getFolioTecnica() {
        return folioTecnica;
    }

    public void setFolioTecnica(String folioTecnica) {
        this.folioTecnica = folioTecnica;
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

    public String getCodigoRegla() {
        return codigoRegla;
    }

    public void setCodigoRegla(String codigoRegla) {
        this.codigoRegla = codigoRegla;
    }

    public String getLargo1() {
        return largo1;
    }

    public void setLargo1(String largo1) {
        this.largo1 = largo1;
    }

    public String getAncho1() {
        return ancho1;
    }

    public void setAncho1(String ancho1) {
        this.ancho1 = ancho1;
    }

    public String getFuelle11() {
        return fuelle11;
    }

    public void setFuelle11(String fuelle11) {
        this.fuelle11 = fuelle11;
    }

    public String getFuelle21() {
        return fuelle21;
    }

    public void setFuelle21(String fuelle21) {
        this.fuelle21 = fuelle21;
    }

    public String getLargo2() {
        return largo2;
    }

    public void setLargo2(String largo2) {
        this.largo2 = largo2;
    }

    public String getAncho2() {
        return ancho2;
    }

    public void setAncho2(String ancho2) {
        this.ancho2 = ancho2;
    }

    public String getFuelle12() {
        return fuelle12;
    }

    public void setFuelle12(String fuelle12) {
        this.fuelle12 = fuelle12;
    }

    public String getFuelle22() {
        return fuelle22;
    }

    public void setFuelle22(String fuelle22) {
        this.fuelle22 = fuelle22;
    }

    public String getLargo3() {
        return largo3;
    }

    public void setLargo3(String largo3) {
        this.largo3 = largo3;
    }

    public String getAncho3() {
        return ancho3;
    }

    public void setAncho3(String ancho3) {
        this.ancho3 = ancho3;
    }

    public String getFuelle13() {
        return fuelle13;
    }

    public void setFuelle13(String fuelle13) {
        this.fuelle13 = fuelle13;
    }

    public String getFuelle23() {
        return fuelle23;
    }

    public void setFuelle23(String fuelle23) {
        this.fuelle23 = fuelle23;
    }

    public String getLargo4() {
        return largo4;
    }

    public void setLargo4(String largo4) {
        this.largo4 = largo4;
    }

    public String getAncho4() {
        return ancho4;
    }

    public void setAncho4(String ancho4) {
        this.ancho4 = ancho4;
    }

    public String getFuelle14() {
        return fuelle14;
    }

    public void setFuelle14(String fuelle14) {
        this.fuelle14 = fuelle14;
    }

    public String getFuelle24() {
        return fuelle24;
    }

    public void setFuelle24(String fuelle24) {
        this.fuelle24 = fuelle24;
    }

    public String getLargo5() {
        return largo5;
    }

    public void setLargo5(String largo5) {
        this.largo5 = largo5;
    }

    public String getAncho5() {
        return ancho5;
    }

    public void setAncho5(String ancho5) {
        this.ancho5 = ancho5;
    }

    public String getFuelle15() {
        return fuelle15;
    }

    public void setFuelle15(String fuelle15) {
        this.fuelle15 = fuelle15;
    }

    public String getFuelle25() {
        return fuelle25;
    }

    public void setFuelle25(String fuelle25) {
        this.fuelle25 = fuelle25;
    }

    public String getPromedioLargo() {
        return promedioLargo;
    }

    public void setPromedioLargo(String promedioLargo) {
        this.promedioLargo = promedioLargo;
    }

    public String getPromedioAncho() {
        return promedioAncho;
    }

    public void setPromedioAncho(String promedioAncho) {
        this.promedioAncho = promedioAncho;
    }

    public String getPromedioFuelle1() {
        return promedioFuelle1;
    }

    public void setPromedioFuelle1(String promedioFuelle1) {
        this.promedioFuelle1 = promedioFuelle1;
    }

    public String getPromedioFuelle2() {
        return promedioFuelle2;
    }

    public void setPromedioFuelle2(String promedioFuelle2) {
        this.promedioFuelle2 = promedioFuelle2;
    }

    public String getSumatoriaFuellePromedio() {
        return sumatoriaFuellePromedio;
    }

    public void setSumatoriaFuellePromedio(String sumatoriaFuellePromedio) {
        this.sumatoriaFuellePromedio = sumatoriaFuellePromedio;
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

    public String getCantidadModificaciones() {
        return cantidadModificaciones;
    }

    public void setCantidadModificaciones(String cantidadModificaciones) {
        this.cantidadModificaciones = cantidadModificaciones;
    }

    public MetodoMuestra getMetodoMuestra() {
        return metodoMuestra;
    }

    public void setMetodoMuestra(MetodoMuestra metodoMuestra) {
        this.metodoMuestra = metodoMuestra;
    }
}
