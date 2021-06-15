package com.demo.model.operacion.metodos.fra10ico;

import com.demo.model.operacion.MetodoMuestra;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
public class FRA_ICO_001 {

    @Id
    @GeneratedValue
    @Column(nullable = false)
    private Long idFRAICO;

    private String folioTecnica;
    private String folioSolicitudServicioInterno;
    private String idInternoMuestra;
    private String fechaInicioAnalisis;
    private String fechaFinalAnalisis;
    private String temperatura;
    private String humedadRelativa;

    private String tipoMaterial;
    private String caraAnalisis;
    private String tipoSuperficie;
    private String especifiqueTipoSuperficie;
    private String tipoProducto;
    private String geometria;
    private String aditivoBiodegradable;
    private String gradoAditivo;
    private String porcentajeInclusión;
    private String tipoEnvejecimiento;
    private String tiempoEnvejecimiento;
    private String codigoMicrometro;
    private String normaReferencia;
    private String codigoEspectrometro;
    private String metodoAnalisis;
    private String numeroBarridos;
    private String resolucion;
    private String numeroOnda;
    private String lineaBase;
    private String grupoCarbonillo;
    private String grupoAlifatico;

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

    public FRA_ICO_001() {
    }

    public FRA_ICO_001(Long idFRAICO, String folioTecnica, String folioSolicitudServicioInterno, String idInternoMuestra, String fechaInicioAnalisis, String fechaFinalAnalisis, String temperatura, String humedadRelativa, String tipoMaterial, String caraAnalisis, String tipoSuperficie, String especifiqueTipoSuperficie, String tipoProducto, String geometria, String aditivoBiodegradable, String gradoAditivo, String porcentajeInclusión, String tipoEnvejecimiento, String tiempoEnvejecimiento, String codigoMicrometro, String normaReferencia, String codigoEspectrometro, String metodoAnalisis, String numeroBarridos, String resolucion, String numeroOnda, String lineaBase, String grupoCarbonillo, String grupoAlifatico, String observaciones, String realizo, String rubricaRealizo, String supervisor, String estatus, String cantidadModificaciones, Timestamp createdAt, Timestamp updatedAt, MetodoMuestra metodoMuestra) {
        this.idFRAICO = idFRAICO;
        this.folioTecnica = folioTecnica;
        this.folioSolicitudServicioInterno = folioSolicitudServicioInterno;
        this.idInternoMuestra = idInternoMuestra;
        this.fechaInicioAnalisis = fechaInicioAnalisis;
        this.fechaFinalAnalisis = fechaFinalAnalisis;
        this.temperatura = temperatura;
        this.humedadRelativa = humedadRelativa;
        this.tipoMaterial = tipoMaterial;
        this.caraAnalisis = caraAnalisis;
        this.tipoSuperficie = tipoSuperficie;
        this.especifiqueTipoSuperficie = especifiqueTipoSuperficie;
        this.tipoProducto = tipoProducto;
        this.geometria = geometria;
        this.aditivoBiodegradable = aditivoBiodegradable;
        this.gradoAditivo = gradoAditivo;
        this.porcentajeInclusión = porcentajeInclusión;
        this.tipoEnvejecimiento = tipoEnvejecimiento;
        this.tiempoEnvejecimiento = tiempoEnvejecimiento;
        this.codigoMicrometro = codigoMicrometro;
        this.normaReferencia = normaReferencia;
        this.codigoEspectrometro = codigoEspectrometro;
        this.metodoAnalisis = metodoAnalisis;
        this.numeroBarridos = numeroBarridos;
        this.resolucion = resolucion;
        this.numeroOnda = numeroOnda;
        this.lineaBase = lineaBase;
        this.grupoCarbonillo = grupoCarbonillo;
        this.grupoAlifatico = grupoAlifatico;
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

    public Long getIdFRAICO() {
        return idFRAICO;
    }

    public void setIdFRAICO(Long idFRAICO) {
        this.idFRAICO = idFRAICO;
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

    public String getTipoSuperficie() {
        return tipoSuperficie;
    }

    public void setTipoSuperficie(String tipoSuperficie) {
        this.tipoSuperficie = tipoSuperficie;
    }

    public String getEspecifiqueTipoSuperficie() {
        return especifiqueTipoSuperficie;
    }

    public void setEspecifiqueTipoSuperficie(String especifiqueTipoSuperficie) {
        this.especifiqueTipoSuperficie = especifiqueTipoSuperficie;
    }

    public String getTipoProducto() {
        return tipoProducto;
    }

    public void setTipoProducto(String tipoProducto) {
        this.tipoProducto = tipoProducto;
    }

    public String getGeometria() {
        return geometria;
    }

    public void setGeometria(String geometria) {
        this.geometria = geometria;
    }

    public String getAditivoBiodegradable() {
        return aditivoBiodegradable;
    }

    public void setAditivoBiodegradable(String aditivoBiodegradable) {
        this.aditivoBiodegradable = aditivoBiodegradable;
    }

    public String getGradoAditivo() {
        return gradoAditivo;
    }

    public void setGradoAditivo(String gradoAditivo) {
        this.gradoAditivo = gradoAditivo;
    }

    public String getPorcentajeInclusión() {
        return porcentajeInclusión;
    }

    public void setPorcentajeInclusión(String porcentajeInclusión) {
        this.porcentajeInclusión = porcentajeInclusión;
    }

    public String getTipoEnvejecimiento() {
        return tipoEnvejecimiento;
    }

    public void setTipoEnvejecimiento(String tipoEnvejecimiento) {
        this.tipoEnvejecimiento = tipoEnvejecimiento;
    }

    public String getTiempoEnvejecimiento() {
        return tiempoEnvejecimiento;
    }

    public void setTiempoEnvejecimiento(String tiempoEnvejecimiento) {
        this.tiempoEnvejecimiento = tiempoEnvejecimiento;
    }

    public String getCodigoMicrometro() {
        return codigoMicrometro;
    }

    public void setCodigoMicrometro(String codigoMicrometro) {
        this.codigoMicrometro = codigoMicrometro;
    }

    public String getNormaReferencia() {
        return normaReferencia;
    }

    public void setNormaReferencia(String normaReferencia) {
        this.normaReferencia = normaReferencia;
    }

    public String getCodigoEspectrometro() {
        return codigoEspectrometro;
    }

    public void setCodigoEspectrometro(String codigoEspectrometro) {
        this.codigoEspectrometro = codigoEspectrometro;
    }

    public String getMetodoAnalisis() {
        return metodoAnalisis;
    }

    public void setMetodoAnalisis(String metodoAnalisis) {
        this.metodoAnalisis = metodoAnalisis;
    }

    public String getNumeroBarridos() {
        return numeroBarridos;
    }

    public void setNumeroBarridos(String numeroBarridos) {
        this.numeroBarridos = numeroBarridos;
    }

    public String getResolucion() {
        return resolucion;
    }

    public void setResolucion(String resolucion) {
        this.resolucion = resolucion;
    }

    public String getNumeroOnda() {
        return numeroOnda;
    }

    public void setNumeroOnda(String numeroOnda) {
        this.numeroOnda = numeroOnda;
    }

    public String getLineaBase() {
        return lineaBase;
    }

    public void setLineaBase(String lineaBase) {
        this.lineaBase = lineaBase;
    }

    public String getGrupoCarbonillo() {
        return grupoCarbonillo;
    }

    public void setGrupoCarbonillo(String grupoCarbonillo) {
        this.grupoCarbonillo = grupoCarbonillo;
    }

    public String getGrupoAlifatico() {
        return grupoAlifatico;
    }

    public void setGrupoAlifatico(String grupoAlifatico) {
        this.grupoAlifatico = grupoAlifatico;
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
