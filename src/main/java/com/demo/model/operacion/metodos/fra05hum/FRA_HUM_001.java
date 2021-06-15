package com.demo.model.operacion.metodos.fra05hum;

import com.demo.model.operacion.MetodoMuestra;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
public class FRA_HUM_001 {

    @Id
    @GeneratedValue
    @Column(nullable = false)
    private Long idFRAHUM;

    private String folioTecnica;
    private String folioSolicitudServicioInterno;
    private String idInternoMuestra;
    private String fechaInicioAnalisis;
    private String fechaFinalAnalisis;
    private String temperatura;
    private String humedadRelativa;

    private String codigoBalanza;
    private String codigoHorno;
    private String temperaturaSecado;
    private String tipoMaterial;

    private String humedadPromedio0;
    private String humedadPromedio4;
    private String humedadPromedio8;

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

    public FRA_HUM_001() {
    }

    public FRA_HUM_001(Long idFRAHUM, String folioTecnica, String folioSolicitudServicioInterno, String idInternoMuestra, String fechaInicioAnalisis, String fechaFinalAnalisis, String temperatura, String humedadRelativa, String codigoBalanza, String codigoHorno, String temperaturaSecado, String tipoMaterial, String humedadPromedio0, String humedadPromedio4, String humedadPromedio8, String observaciones, String realizo, String rubricaRealizo, String supervisor, String estatus, String cantidadModificaciones, Timestamp createdAt, Timestamp updatedAt, MetodoMuestra metodoMuestra) {
        this.idFRAHUM = idFRAHUM;
        this.folioTecnica = folioTecnica;
        this.folioSolicitudServicioInterno = folioSolicitudServicioInterno;
        this.idInternoMuestra = idInternoMuestra;
        this.fechaInicioAnalisis = fechaInicioAnalisis;
        this.fechaFinalAnalisis = fechaFinalAnalisis;
        this.temperatura = temperatura;
        this.humedadRelativa = humedadRelativa;
        this.codigoBalanza = codigoBalanza;
        this.codigoHorno = codigoHorno;
        this.temperaturaSecado = temperaturaSecado;
        this.tipoMaterial = tipoMaterial;
        this.humedadPromedio0 = humedadPromedio0;
        this.humedadPromedio4 = humedadPromedio4;
        this.humedadPromedio8 = humedadPromedio8;
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

    public Long getIdFRAHUM() {
        return idFRAHUM;
    }

    public void setIdFRAHUM(Long idFRAHUM) {
        this.idFRAHUM = idFRAHUM;
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

    public String getCodigoBalanza() {
        return codigoBalanza;
    }

    public void setCodigoBalanza(String codigoBalanza) {
        this.codigoBalanza = codigoBalanza;
    }

    public String getCodigoHorno() {
        return codigoHorno;
    }

    public void setCodigoHorno(String codigoHorno) {
        this.codigoHorno = codigoHorno;
    }

    public String getTemperaturaSecado() {
        return temperaturaSecado;
    }

    public void setTemperaturaSecado(String temperaturaSecado) {
        this.temperaturaSecado = temperaturaSecado;
    }

    public String getTipoMaterial() {
        return tipoMaterial;
    }

    public void setTipoMaterial(String tipoMaterial) {
        this.tipoMaterial = tipoMaterial;
    }

    public String getHumedadPromedio0() {
        return humedadPromedio0;
    }

    public void setHumedadPromedio0(String humedadPromedio0) {
        this.humedadPromedio0 = humedadPromedio0;
    }

    public String getHumedadPromedio4() {
        return humedadPromedio4;
    }

    public void setHumedadPromedio4(String humedadPromedio4) {
        this.humedadPromedio4 = humedadPromedio4;
    }

    public String getHumedadPromedio8() {
        return humedadPromedio8;
    }

    public void setHumedadPromedio8(String humedadPromedio8) {
        this.humedadPromedio8 = humedadPromedio8;
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

    public String getRubricaRealizo() {
        return rubricaRealizo;
    }

    public void setRubricaRealizo(String rubricaRealizo) {
        this.rubricaRealizo = rubricaRealizo;
    }
}
