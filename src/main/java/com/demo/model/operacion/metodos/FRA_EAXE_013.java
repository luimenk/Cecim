package com.demo.model.operacion.metodos;

import com.demo.model.operacion.MetodoMuestra;

import javax.persistence.*;

@Entity
public class FRA_EAXE_013 {

    @Id
    @GeneratedValue
    @Column(nullable = false)
    private Long idFRAEAXE;

    @Column(length = 60, nullable = false)
    private String folioSolicitudServicioInterno;

    @Column(length = 60, nullable = false)
    private String idInternoMuestra;

    @Column(length = 60, nullable = false)
    private String fechaInicioAnalisis;

    @Column(length = 60, nullable = false)
    private String fechaFinalAnalisis;

    @Column(length = 60, nullable = false)
    private String temperatura;

    @Column(length = 60, nullable = false)
    private String humedadRelativa;

    @Column(length = 60, nullable = false)
    private String codigoCamaraXE;

    @Column(length = 60)
    private String tiempoTotalExposicion;

    @Column(length = 250)
    private String observaciones;

    @Column(length = 250)
    private String realizo;

    @Column(length = 250)
    private String supervisor;

    private String estatus;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "Metodo_Muestra_Id")
    private MetodoMuestra metodoMuestra;

    public FRA_EAXE_013() {
    }

    public FRA_EAXE_013(Long idFRAEAXE, String folioSolicitudServicioInterno, String idInternoMuestra, String fechaInicioAnalisis, String fechaFinalAnalisis, String temperatura, String humedadRelativa, String codigoCamaraXE, String tiempoTotalExposicion, String observaciones, String realizo, String supervisor, String estatus, MetodoMuestra metodoMuestra) {
        this.idFRAEAXE = idFRAEAXE;
        this.folioSolicitudServicioInterno = folioSolicitudServicioInterno;
        this.idInternoMuestra = idInternoMuestra;
        this.fechaInicioAnalisis = fechaInicioAnalisis;
        this.fechaFinalAnalisis = fechaFinalAnalisis;
        this.temperatura = temperatura;
        this.humedadRelativa = humedadRelativa;
        this.codigoCamaraXE = codigoCamaraXE;
        this.tiempoTotalExposicion = tiempoTotalExposicion;
        this.observaciones = observaciones;
        this.realizo = realizo;
        this.supervisor = supervisor;
        this.estatus = estatus;
        this.metodoMuestra = metodoMuestra;
    }

    public Long getIdFRAEAXE() {
        return idFRAEAXE;
    }

    public void setIdFRAEAXE(Long idFRAEAXE) {
        this.idFRAEAXE = idFRAEAXE;
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

    public String getCodigoCamaraXE() {
        return codigoCamaraXE;
    }

    public void setCodigoCamaraXE(String codigoCamaraXE) {
        this.codigoCamaraXE = codigoCamaraXE;
    }

    public String getTiempoTotalExposicion() {
        return tiempoTotalExposicion;
    }

    public void setTiempoTotalExposicion(String tiempoTotalExposicion) {
        this.tiempoTotalExposicion = tiempoTotalExposicion;
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

    public MetodoMuestra getMetodoMuestra() {
        return metodoMuestra;
    }

    public void setMetodoMuestra(MetodoMuestra metodoMuestra) {
        this.metodoMuestra = metodoMuestra;
    }
}
