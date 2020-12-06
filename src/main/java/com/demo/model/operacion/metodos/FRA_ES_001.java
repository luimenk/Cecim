package com.demo.model.operacion.metodos;

import com.demo.model.operacion.MetodoMuestra;

import javax.persistence.*;

@Entity
public class FRA_ES_001 {

    @Id
    @GeneratedValue
    @Column(nullable = false)
    private Long determinacionEspesorId;

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
    private String codigoMicrometro;

    @Column(length = 250, nullable = false)
    private String humedadRelativa;

    @Column(length = 250, nullable = false)
    private String metodo;

    /** Resultados **/

    @Column(length = 250, nullable = false)
    private String largo1;

    @Column(length = 250, nullable = false)
    private String ancho1;

    @Column(length = 250, nullable = false)
    private String largo2;

    @Column(length = 250, nullable = false)
    private String ancho2;

    @Column(length = 250, nullable = false)
    private String largo3;

    @Column(length = 250, nullable = false)
    private String ancho3;

    @Column(length = 250, nullable = false)
    private String largo4;

    @Column(length = 250, nullable = false)
    private String ancho4;

    @Column(length = 250, nullable = false)
    private String promedioLargo;

    @Column(length = 250, nullable = false)
    private String promedioAncho;

    @Column(length = 250, nullable = false)
    private String desvEstandarLargo;

    @Column(length = 250, nullable = false)
    private String desvEstandarAncho;

    @Column(length = 50, nullable = false)
    private String minLargo;

    @Column(length = 50, nullable = false)
    private String maxLargo;

    @Column(length = 50, nullable = false)
    private String minAncho;

    @Column(length = 50, nullable = false)
    private String maxAncho;

    @Column(length = 250, nullable = false)
    private String observaciones;

    @Column(length = 250, nullable = false)
    private String realizo;

    @Column(length = 250, nullable = false)
    private String supervisor;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "Metodo_Muestra_Id")
    private MetodoMuestra metodoMuestra;

    public FRA_ES_001() {
    }

    public FRA_ES_001(Long determinacionEspesorId, String folioSolicitudServicioInterno, String idInternoMuestra, String fechaInicioAnalisis, String fechaFinalAnalisis, String temperatura, String codigoMicrometro, String humedadRelativa, String metodo, String largo1, String ancho1, String largo2, String ancho2, String largo3, String ancho3, String largo4, String ancho4, String promedioLargo, String promedioAncho, String desvEstandarLargo, String desvEstandarAncho, String minLargo, String maxLargo, String minAncho, String maxAncho, String observaciones, String realizo, String supervisor, MetodoMuestra metodoMuestra) {
        this.determinacionEspesorId = determinacionEspesorId;
        this.folioSolicitudServicioInterno = folioSolicitudServicioInterno;
        this.idInternoMuestra = idInternoMuestra;
        this.fechaInicioAnalisis = fechaInicioAnalisis;
        this.fechaFinalAnalisis = fechaFinalAnalisis;
        this.temperatura = temperatura;
        this.codigoMicrometro = codigoMicrometro;
        this.humedadRelativa = humedadRelativa;
        this.metodo = metodo;
        this.largo1 = largo1;
        this.ancho1 = ancho1;
        this.largo2 = largo2;
        this.ancho2 = ancho2;
        this.largo3 = largo3;
        this.ancho3 = ancho3;
        this.largo4 = largo4;
        this.ancho4 = ancho4;
        this.promedioLargo = promedioLargo;
        this.promedioAncho = promedioAncho;
        this.desvEstandarLargo = desvEstandarLargo;
        this.desvEstandarAncho = desvEstandarAncho;
        this.minLargo = minLargo;
        this.maxLargo = maxLargo;
        this.minAncho = minAncho;
        this.maxAncho = maxAncho;
        this.observaciones = observaciones;
        this.realizo = realizo;
        this.supervisor = supervisor;
        this.metodoMuestra = metodoMuestra;
    }

    public Long getDeterminacionEspesorId() {
        return determinacionEspesorId;
    }

    public void setDeterminacionEspesorId(Long determinacionEspesorId) {
        this.determinacionEspesorId = determinacionEspesorId;
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

    public String getCodigoMicrometro() {
        return codigoMicrometro;
    }

    public void setCodigoMicrometro(String codigoMicrometro) {
        this.codigoMicrometro = codigoMicrometro;
    }

    public String getHumedadRelativa() {
        return humedadRelativa;
    }

    public void setHumedadRelativa(String humedadRelativa) {
        this.humedadRelativa = humedadRelativa;
    }

    public String getMetodo() {
        return metodo;
    }

    public void setMetodo(String metodo) {
        this.metodo = metodo;
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

    public String getDesvEstandarLargo() {
        return desvEstandarLargo;
    }

    public void setDesvEstandarLargo(String desvEstandarLargo) {
        this.desvEstandarLargo = desvEstandarLargo;
    }

    public String getDesvEstandarAncho() {
        return desvEstandarAncho;
    }

    public void setDesvEstandarAncho(String desvEstandarAncho) {
        this.desvEstandarAncho = desvEstandarAncho;
    }

    public String getMinLargo() {
        return minLargo;
    }

    public void setMinLargo(String minLargo) {
        this.minLargo = minLargo;
    }

    public String getMaxLargo() {
        return maxLargo;
    }

    public void setMaxLargo(String maxLargo) {
        this.maxLargo = maxLargo;
    }

    public String getMinAncho() {
        return minAncho;
    }

    public void setMinAncho(String minAncho) {
        this.minAncho = minAncho;
    }

    public String getMaxAncho() {
        return maxAncho;
    }

    public void setMaxAncho(String maxAncho) {
        this.maxAncho = maxAncho;
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
