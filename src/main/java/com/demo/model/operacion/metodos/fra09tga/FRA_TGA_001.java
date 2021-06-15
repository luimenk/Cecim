package com.demo.model.operacion.metodos.fra09tga;

import com.demo.model.operacion.MetodoMuestra;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
public class FRA_TGA_001 {

    @Id
    @GeneratedValue
    @Column(nullable = false)
    private Long idFRATGA;

    private String folioTecnica;
    private String folioSolicitudServicioInterno;
    private String idInternoMuestra;
    private String fechaInicioAnalisis;
    private String fechaFinalAnalisis;
    private String temperatura;
    private String humedadRelativa;

    private String codigoTGA;
    private String codigoBalanza;

    //Resultados
    private String avTemperatura;
    private String avPeso;
    private String mvTemperatura;
    private String mvPeso;
    private String combustibleTemperatura;
    private String combustiblePeso;
    private String cenizasTemperatura;
    private String cenizasPeso;

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

    public FRA_TGA_001() {
    }

    public FRA_TGA_001(Long idFRATGA, String folioTecnica, String folioSolicitudServicioInterno, String idInternoMuestra, String fechaInicioAnalisis, String fechaFinalAnalisis, String temperatura, String humedadRelativa, String codigoTGA, String codigoBalanza, String avTemperatura, String avPeso, String mvTemperatura, String mvPeso, String combustibleTemperatura, String combustiblePeso, String cenizasTemperatura, String cenizasPeso, String observaciones, String realizo, String rubricaRealizo, String supervisor, String estatus, String cantidadModificaciones, Timestamp createdAt, Timestamp updatedAt, MetodoMuestra metodoMuestra) {
        this.idFRATGA = idFRATGA;
        this.folioTecnica = folioTecnica;
        this.folioSolicitudServicioInterno = folioSolicitudServicioInterno;
        this.idInternoMuestra = idInternoMuestra;
        this.fechaInicioAnalisis = fechaInicioAnalisis;
        this.fechaFinalAnalisis = fechaFinalAnalisis;
        this.temperatura = temperatura;
        this.humedadRelativa = humedadRelativa;
        this.codigoTGA = codigoTGA;
        this.codigoBalanza = codigoBalanza;
        this.avTemperatura = avTemperatura;
        this.avPeso = avPeso;
        this.mvTemperatura = mvTemperatura;
        this.mvPeso = mvPeso;
        this.combustibleTemperatura = combustibleTemperatura;
        this.combustiblePeso = combustiblePeso;
        this.cenizasTemperatura = cenizasTemperatura;
        this.cenizasPeso = cenizasPeso;
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

    public Long getIdFRATGA() {
        return idFRATGA;
    }

    public void setIdFRATGA(Long idFRATGA) {
        this.idFRATGA = idFRATGA;
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

    public String getAvTemperatura() {
        return avTemperatura;
    }

    public void setAvTemperatura(String avTemperatura) {
        this.avTemperatura = avTemperatura;
    }

    public String getAvPeso() {
        return avPeso;
    }

    public void setAvPeso(String avPeso) {
        this.avPeso = avPeso;
    }

    public String getMvTemperatura() {
        return mvTemperatura;
    }

    public void setMvTemperatura(String mvTemperatura) {
        this.mvTemperatura = mvTemperatura;
    }

    public String getMvPeso() {
        return mvPeso;
    }

    public void setMvPeso(String mvPeso) {
        this.mvPeso = mvPeso;
    }

    public String getCombustibleTemperatura() {
        return combustibleTemperatura;
    }

    public void setCombustibleTemperatura(String combustibleTemperatura) {
        this.combustibleTemperatura = combustibleTemperatura;
    }

    public String getCombustiblePeso() {
        return combustiblePeso;
    }

    public void setCombustiblePeso(String combustiblePeso) {
        this.combustiblePeso = combustiblePeso;
    }

    public String getCenizasTemperatura() {
        return cenizasTemperatura;
    }

    public void setCenizasTemperatura(String cenizasTemperatura) {
        this.cenizasTemperatura = cenizasTemperatura;
    }

    public String getCenizasPeso() {
        return cenizasPeso;
    }

    public void setCenizasPeso(String cenizasPeso) {
        this.cenizasPeso = cenizasPeso;
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