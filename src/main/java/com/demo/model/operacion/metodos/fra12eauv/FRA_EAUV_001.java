package com.demo.model.operacion.metodos.fra12eauv;

import com.demo.model.operacion.MetodoMuestra;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
public class FRA_EAUV_001 {

    @Id
    @GeneratedValue
    @Column(nullable = false)
    private Long idFRAEAUV;

    private String folioTecnica;
    private String folioSolicitudServicioInterno;
    private String idInternoMuestra;
    private String fechaInicioAnalisis;
    private String fechaFinalAnalisis;
    private String temperatura;
    private String humedadRelativa;
    
    private String codigoCamaraUV;
    private String codigoRadiometro;
    private String irradiacion;
    private String temperaturaPanel;
    private String longitudOnda;
    private String tiempoCapturasFotograficas;
    private String cicloRadiacionUV;
    private String cicloAusenciaRadiacionUV;
    private String descripcionMuestra;
    private String tipoProducto;
    private String tipoMaterial;
    private String caraAnalisis;
    private String aditivoBiodegradable;
    private String porcentajeInclusion;
    private String tipoSuperficie;

    private String tiempoTotalExposicion;
    private String totalFotografiasCapturadas;

    private String observaciones;
    private String realizo;
    private String rubricaRealizo;
    private String supervisor;

    private String estatus;
    private String cantidadModificaciones;

    @CreationTimestamp
    private Timestamp createdAt;

    @UpdateTimestamp
    private Timestamp updatedAt;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "Metodo_Muestra_Id")
    private MetodoMuestra metodoMuestra;

    public FRA_EAUV_001() {
    }

    public FRA_EAUV_001(Long idFRAEAUV, String folioTecnica, String folioSolicitudServicioInterno, String idInternoMuestra, String fechaInicioAnalisis, String fechaFinalAnalisis, String temperatura, String humedadRelativa, String codigoCamaraUV, String codigoRadiometro, String irradiacion, String temperaturaPanel, String longitudOnda, String tiempoCapturasFotograficas, String cicloRadiacionUV, String cicloAusenciaRadiacionUV, String descripcionMuestra, String tipoProducto, String tipoMaterial, String caraAnalisis, String aditivoBiodegradable, String porcentajeInclusion, String tipoSuperficie, String tiempoTotalExposicion, String totalFotografiasCapturadas, String observaciones, String realizo, String rubricaRealizo, String supervisor, String estatus, String cantidadModificaciones, Timestamp createdAt, Timestamp updatedAt, MetodoMuestra metodoMuestra) {
        this.idFRAEAUV = idFRAEAUV;
        this.folioTecnica = folioTecnica;
        this.folioSolicitudServicioInterno = folioSolicitudServicioInterno;
        this.idInternoMuestra = idInternoMuestra;
        this.fechaInicioAnalisis = fechaInicioAnalisis;
        this.fechaFinalAnalisis = fechaFinalAnalisis;
        this.temperatura = temperatura;
        this.humedadRelativa = humedadRelativa;
        this.codigoCamaraUV = codigoCamaraUV;
        this.codigoRadiometro = codigoRadiometro;
        this.irradiacion = irradiacion;
        this.temperaturaPanel = temperaturaPanel;
        this.longitudOnda = longitudOnda;
        this.tiempoCapturasFotograficas = tiempoCapturasFotograficas;
        this.cicloRadiacionUV = cicloRadiacionUV;
        this.cicloAusenciaRadiacionUV = cicloAusenciaRadiacionUV;
        this.descripcionMuestra = descripcionMuestra;
        this.tipoProducto = tipoProducto;
        this.tipoMaterial = tipoMaterial;
        this.caraAnalisis = caraAnalisis;
        this.aditivoBiodegradable = aditivoBiodegradable;
        this.porcentajeInclusion = porcentajeInclusion;
        this.tipoSuperficie = tipoSuperficie;
        this.tiempoTotalExposicion = tiempoTotalExposicion;
        this.totalFotografiasCapturadas = totalFotografiasCapturadas;
        this.observaciones = observaciones;
        this.realizo = realizo;
        this.rubricaRealizo = rubricaRealizo;
        this.supervisor = supervisor;
        this.estatus = estatus;
        this.cantidadModificaciones = cantidadModificaciones;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.metodoMuestra = metodoMuestra;
    }

    public Long getIdFRAEAUV() {
        return idFRAEAUV;
    }

    public void setIdFRAEAUV(Long idFRAEAUV) {
        this.idFRAEAUV = idFRAEAUV;
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

    public String getCodigoCamaraUV() {
        return codigoCamaraUV;
    }

    public void setCodigoCamaraUV(String codigoCamaraUV) {
        this.codigoCamaraUV = codigoCamaraUV;
    }

    public String getCodigoRadiometro() {
        return codigoRadiometro;
    }

    public void setCodigoRadiometro(String codigoRadiometro) {
        this.codigoRadiometro = codigoRadiometro;
    }

    public String getIrradiacion() {
        return irradiacion;
    }

    public void setIrradiacion(String irradiacion) {
        this.irradiacion = irradiacion;
    }

    public String getTemperaturaPanel() {
        return temperaturaPanel;
    }

    public void setTemperaturaPanel(String temperaturaPanel) {
        this.temperaturaPanel = temperaturaPanel;
    }

    public String getLongitudOnda() {
        return longitudOnda;
    }

    public void setLongitudOnda(String longitudOnda) {
        this.longitudOnda = longitudOnda;
    }

    public String getTiempoCapturasFotograficas() {
        return tiempoCapturasFotograficas;
    }

    public void setTiempoCapturasFotograficas(String tiempoCapturasFotograficas) {
        this.tiempoCapturasFotograficas = tiempoCapturasFotograficas;
    }

    public String getCicloRadiacionUV() {
        return cicloRadiacionUV;
    }

    public void setCicloRadiacionUV(String cicloRadiacionUV) {
        this.cicloRadiacionUV = cicloRadiacionUV;
    }

    public String getCicloAusenciaRadiacionUV() {
        return cicloAusenciaRadiacionUV;
    }

    public void setCicloAusenciaRadiacionUV(String cicloAusenciaRadiacionUV) {
        this.cicloAusenciaRadiacionUV = cicloAusenciaRadiacionUV;
    }

    public String getDescripcionMuestra() {
        return descripcionMuestra;
    }

    public void setDescripcionMuestra(String descripcionMuestra) {
        this.descripcionMuestra = descripcionMuestra;
    }

    public String getTipoProducto() {
        return tipoProducto;
    }

    public void setTipoProducto(String tipoProducto) {
        this.tipoProducto = tipoProducto;
    }

    public String getTipoMaterial() {
        return tipoMaterial;
    }

    public void setTipoMaterial(String tipoMaterial) {
        this.tipoMaterial = tipoMaterial;
    }

    public String getCaraAnalisis() {
        return caraAnalisis;
    }

    public void setCaraAnalisis(String caraAnalisis) {
        this.caraAnalisis = caraAnalisis;
    }

    public String getAditivoBiodegradable() {
        return aditivoBiodegradable;
    }

    public void setAditivoBiodegradable(String aditivoBiodegradable) {
        this.aditivoBiodegradable = aditivoBiodegradable;
    }

    public String getPorcentajeInclusion() {
        return porcentajeInclusion;
    }

    public void setPorcentajeInclusion(String porcentajeInclusion) {
        this.porcentajeInclusion = porcentajeInclusion;
    }

    public String getTipoSuperficie() {
        return tipoSuperficie;
    }

    public void setTipoSuperficie(String tipoSuperficie) {
        this.tipoSuperficie = tipoSuperficie;
    }

    public String getTiempoTotalExposicion() {
        return tiempoTotalExposicion;
    }

    public void setTiempoTotalExposicion(String tiempoTotalExposicion) {
        this.tiempoTotalExposicion = tiempoTotalExposicion;
    }

    public String getTotalFotografiasCapturadas() {
        return totalFotografiasCapturadas;
    }

    public void setTotalFotografiasCapturadas(String totalFotografiasCapturadas) {
        this.totalFotografiasCapturadas = totalFotografiasCapturadas;
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

    public String getRubricaRealizo() {
        return rubricaRealizo;
    }

    public void setRubricaRealizo(String rubricaRealizo) {
        this.rubricaRealizo = rubricaRealizo;
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

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }

    public Timestamp getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Timestamp updatedAt) {
        this.updatedAt = updatedAt;
    }

    public MetodoMuestra getMetodoMuestra() {
        return metodoMuestra;
    }

    public void setMetodoMuestra(MetodoMuestra metodoMuestra) {
        this.metodoMuestra = metodoMuestra;
    }
}
