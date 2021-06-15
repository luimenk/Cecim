package com.demo.model.operacion.metodos.fra13eaxe;

import com.demo.model.operacion.MetodoMuestra;

import javax.persistence.*;

@Entity
public class FRA_EAXE_013 {

    @Id
    @GeneratedValue
    @Column(nullable = false)
    private Long idFRAEAXE;

    private String folioTecnica;
    private String folioSolicitudServicioInterno;
    private String idInternoMuestra;
    private String fechaInicioAnalisis;
    private String fechaFinalAnalisis;
    private String temperatura;
    private String humedadRelativa;

    private String codigoCamaraXE;
    private String codigoRadiometro;
    private String irradiacion;
    private String temperaturaPanel;
    private String longitudOnda;
    private String cicloCondensacion;
    private String tiempoCiclo;
    private String cicloTomaFotografica;
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

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "Metodo_Muestra_Id")
    private MetodoMuestra metodoMuestra;

    public FRA_EAXE_013() {
    }

    public FRA_EAXE_013(Long idFRAEAXE, String folioTecnica, String folioSolicitudServicioInterno, String idInternoMuestra, String fechaInicioAnalisis, String fechaFinalAnalisis, String temperatura, String humedadRelativa, String codigoCamaraXE, String codigoRadiometro, String irradiacion, String temperaturaPanel, String longitudOnda, String cicloCondensacion, String tiempoCiclo, String cicloTomaFotografica, String descripcionMuestra, String tipoProducto, String tipoMaterial, String caraAnalisis, String aditivoBiodegradable, String porcentajeInclusion, String tipoSuperficie, String tiempoTotalExposicion, String totalFotografiasCapturadas, String observaciones, String realizo, String rubricaRealizo, String supervisor, String estatus, String cantidadModificaciones, MetodoMuestra metodoMuestra) {
        this.idFRAEAXE = idFRAEAXE;
        this.folioTecnica = folioTecnica;
        this.folioSolicitudServicioInterno = folioSolicitudServicioInterno;
        this.idInternoMuestra = idInternoMuestra;
        this.fechaInicioAnalisis = fechaInicioAnalisis;
        this.fechaFinalAnalisis = fechaFinalAnalisis;
        this.temperatura = temperatura;
        this.humedadRelativa = humedadRelativa;
        this.codigoCamaraXE = codigoCamaraXE;
        this.codigoRadiometro = codigoRadiometro;
        this.irradiacion = irradiacion;
        this.temperaturaPanel = temperaturaPanel;
        this.longitudOnda = longitudOnda;
        this.cicloCondensacion = cicloCondensacion;
        this.tiempoCiclo = tiempoCiclo;
        this.cicloTomaFotografica = cicloTomaFotografica;
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
        this.metodoMuestra = metodoMuestra;
    }

    public Long getIdFRAEAXE() {
        return idFRAEAXE;
    }

    public void setIdFRAEAXE(Long idFRAEAXE) {
        this.idFRAEAXE = idFRAEAXE;
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

    public String getCodigoCamaraXE() {
        return codigoCamaraXE;
    }

    public void setCodigoCamaraXE(String codigoCamaraXE) {
        this.codigoCamaraXE = codigoCamaraXE;
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

    public String getCicloCondensacion() {
        return cicloCondensacion;
    }

    public void setCicloCondensacion(String cicloCondensacion) {
        this.cicloCondensacion = cicloCondensacion;
    }

    public String getTiempoCiclo() {
        return tiempoCiclo;
    }

    public void setTiempoCiclo(String tiempoCiclo) {
        this.tiempoCiclo = tiempoCiclo;
    }

    public String getCicloTomaFotografica() {
        return cicloTomaFotografica;
    }

    public void setCicloTomaFotografica(String cicloTomaFotografica) {
        this.cicloTomaFotografica = cicloTomaFotografica;
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

    public MetodoMuestra getMetodoMuestra() {
        return metodoMuestra;
    }

    public void setMetodoMuestra(MetodoMuestra metodoMuestra) {
        this.metodoMuestra = metodoMuestra;
    }
}
