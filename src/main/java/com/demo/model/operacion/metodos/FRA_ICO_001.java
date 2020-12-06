package com.demo.model.operacion.metodos;

import com.demo.model.operacion.MetodoMuestra;

import javax.persistence.*;

@Entity
public class FRA_ICO_001 {

    @Id
    @GeneratedValue
    @Column(nullable = false)
    private Long idFRAICO;

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
    private String codigoEspectrometro;

    @Column(length = 60, nullable = false)
    private String parrillaCalentamiento;

    @Column(length = 60)
    private String tiempoTotalExposicion;

    @Column(length = 60)
    private String valorFinalICO;

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

    public FRA_ICO_001() {
    }

    public FRA_ICO_001(Long idFRAICO, String folioSolicitudServicioInterno, String idInternoMuestra, String fechaInicioAnalisis, String fechaFinalAnalisis, String temperatura, String humedadRelativa, String codigoEspectrometro, String parrillaCalentamiento, String tiempoTotalExposicion, String valorFinalICO, String observaciones, String realizo, String supervisor, String estatus, MetodoMuestra metodoMuestra) {
        this.idFRAICO = idFRAICO;
        this.folioSolicitudServicioInterno = folioSolicitudServicioInterno;
        this.idInternoMuestra = idInternoMuestra;
        this.fechaInicioAnalisis = fechaInicioAnalisis;
        this.fechaFinalAnalisis = fechaFinalAnalisis;
        this.temperatura = temperatura;
        this.humedadRelativa = humedadRelativa;
        this.codigoEspectrometro = codigoEspectrometro;
        this.parrillaCalentamiento = parrillaCalentamiento;
        this.tiempoTotalExposicion = tiempoTotalExposicion;
        this.valorFinalICO = valorFinalICO;
        this.observaciones = observaciones;
        this.realizo = realizo;
        this.supervisor = supervisor;
        this.estatus = estatus;
        this.metodoMuestra = metodoMuestra;
    }

    public Long getIdFRAICO() {
        return idFRAICO;
    }

    public void setIdFRAICO(Long idFRAICO) {
        this.idFRAICO = idFRAICO;
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

    public String getParrillaCalentamiento() {
        return parrillaCalentamiento;
    }

    public void setParrillaCalentamiento(String parrillaCalentamiento) {
        this.parrillaCalentamiento = parrillaCalentamiento;
    }

    public String getTiempoTotalExposicion() {
        return tiempoTotalExposicion;
    }

    public void setTiempoTotalExposicion(String tiempoTotalExposicion) {
        this.tiempoTotalExposicion = tiempoTotalExposicion;
    }

    public String getValorFinalICO() {
        return valorFinalICO;
    }

    public void setValorFinalICO(String valorFinalICO) {
        this.valorFinalICO = valorFinalICO;
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
