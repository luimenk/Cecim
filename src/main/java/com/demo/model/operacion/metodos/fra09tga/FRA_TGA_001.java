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
    private String peso;
    private String posicionPortadorMuestra;
    private String tipoMaterial;

    //Condiciones
    private String purga;
    private String inicio;
    private String dinamicaCal1;
    private String dinamicaCal2;
    private String tasaCalentamiento;
    private String dinamicaEnf1;
    private String tasaEnfriamiento;
    private String temperaturaEmergencia;
    private String tipoAtmosfera1;
    private String tipoAtmosfera2;
    private String tiempoPurga;
    private String dinamica;

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

    private String pathImagen;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "Metodo_Muestra_Id")
    private MetodoMuestra metodoMuestra;

    public FRA_TGA_001() {
    }

    public FRA_TGA_001(Long idFRATGA, String folioTecnica, String folioSolicitudServicioInterno, String idInternoMuestra, String fechaInicioAnalisis, String fechaFinalAnalisis, String temperatura, String humedadRelativa, String codigoTGA, String codigoBalanza, String peso, String posicionPortadorMuestra, String tipoMaterial, String purga, String inicio, String dinamicaCal1, String dinamicaCal2, String tasaCalentamiento, String dinamicaEnf1, String tasaEnfriamiento, String temperaturaEmergencia, String tipoAtmosfera1, String tipoAtmosfera2, String tiempoPurga, String dinamica, String observaciones, String realizo, String rubricaRealizo, String supervisor, String estatus, String cantidadModificaciones, Timestamp createdAt, Timestamp updatedAt, String pathImagen, MetodoMuestra metodoMuestra) {
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
        this.peso = peso;
        this.posicionPortadorMuestra = posicionPortadorMuestra;
        this.tipoMaterial = tipoMaterial;
        this.purga = purga;
        this.inicio = inicio;
        this.dinamicaCal1 = dinamicaCal1;
        this.dinamicaCal2 = dinamicaCal2;
        this.tasaCalentamiento = tasaCalentamiento;
        this.dinamicaEnf1 = dinamicaEnf1;
        this.tasaEnfriamiento = tasaEnfriamiento;
        this.temperaturaEmergencia = temperaturaEmergencia;
        this.tipoAtmosfera1 = tipoAtmosfera1;
        this.tipoAtmosfera2 = tipoAtmosfera2;
        this.tiempoPurga = tiempoPurga;
        this.dinamica = dinamica;
        this.observaciones = observaciones;
        this.realizo = realizo;
        this.rubricaRealizo = rubricaRealizo;
        this.supervisor = supervisor;
        this.estatus = estatus;
        this.cantidadModificaciones = cantidadModificaciones;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.pathImagen = pathImagen;
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

    public String getPeso() {
        return peso;
    }

    public void setPeso(String peso) {
        this.peso = peso;
    }

    public String getPosicionPortadorMuestra() {
        return posicionPortadorMuestra;
    }

    public void setPosicionPortadorMuestra(String posicionPortadorMuestra) {
        this.posicionPortadorMuestra = posicionPortadorMuestra;
    }

    public String getTipoMaterial() {
        return tipoMaterial;
    }

    public void setTipoMaterial(String tipoMaterial) {
        this.tipoMaterial = tipoMaterial;
    }

    public String getPurga() {
        return purga;
    }

    public void setPurga(String purga) {
        this.purga = purga;
    }

    public String getInicio() {
        return inicio;
    }

    public void setInicio(String inicio) {
        this.inicio = inicio;
    }

    public String getDinamicaCal1() {
        return dinamicaCal1;
    }

    public void setDinamicaCal1(String dinamicaCal1) {
        this.dinamicaCal1 = dinamicaCal1;
    }

    public String getDinamicaCal2() {
        return dinamicaCal2;
    }

    public void setDinamicaCal2(String dinamicaCal2) {
        this.dinamicaCal2 = dinamicaCal2;
    }

    public String getTasaCalentamiento() {
        return tasaCalentamiento;
    }

    public void setTasaCalentamiento(String tasaCalentamiento) {
        this.tasaCalentamiento = tasaCalentamiento;
    }

    public String getDinamicaEnf1() {
        return dinamicaEnf1;
    }

    public void setDinamicaEnf1(String dinamicaEnf1) {
        this.dinamicaEnf1 = dinamicaEnf1;
    }

    public String getTasaEnfriamiento() {
        return tasaEnfriamiento;
    }

    public void setTasaEnfriamiento(String tasaEnfriamiento) {
        this.tasaEnfriamiento = tasaEnfriamiento;
    }

    public String getTemperaturaEmergencia() {
        return temperaturaEmergencia;
    }

    public void setTemperaturaEmergencia(String temperaturaEmergencia) {
        this.temperaturaEmergencia = temperaturaEmergencia;
    }

    public String getTipoAtmosfera1() {
        return tipoAtmosfera1;
    }

    public void setTipoAtmosfera1(String tipoAtmosfera1) {
        this.tipoAtmosfera1 = tipoAtmosfera1;
    }

    public String getTipoAtmosfera2() {
        return tipoAtmosfera2;
    }

    public void setTipoAtmosfera2(String tipoAtmosfera2) {
        this.tipoAtmosfera2 = tipoAtmosfera2;
    }

    public String getTiempoPurga() {
        return tiempoPurga;
    }

    public void setTiempoPurga(String tiempoPurga) {
        this.tiempoPurga = tiempoPurga;
    }

    public String getDinamica() {
        return dinamica;
    }

    public void setDinamica(String dinamica) {
        this.dinamica = dinamica;
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

    public String getPathImagen() {
        return pathImagen;
    }

    public void setPathImagen(String pathImagen) {
        this.pathImagen = pathImagen;
    }
}