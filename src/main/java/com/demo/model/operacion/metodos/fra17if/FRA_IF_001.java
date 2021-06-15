package com.demo.model.operacion.metodos.fra17if;

import com.demo.model.operacion.MetodoMuestra;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
public class FRA_IF_001 {

    @Id
    @GeneratedValue
    @Column(nullable = false)
    private Long idFRAIF;

    private String folioTecnica;
    private String folioSolicitudServicioInterno;
    private String idInternoMuestra;
    private String fechaInicioAnalisis;
    private String fechaFinalAnalisis;
    private String temperatura;
    private String humedadRelativa;

    private String codigoBalanzaAnalitica;
    private String codigoPlastometro;
    private String temperaturaEnsayo;
    private String pesaEnsayo;
    private String tiempoCorte;

    private String tipoMaterial;
    private String formaFisicaMaterial;
    private String indiceFuidez;

    private String promedioPesoFilamento;
    private String promedioIndiceFluidez;
    private String promedioMFI;

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

    public FRA_IF_001() {
    }

    public FRA_IF_001(Long idFRAIF, String folioTecnica, String folioSolicitudServicioInterno, String idInternoMuestra, String fechaInicioAnalisis, String fechaFinalAnalisis, String temperatura, String humedadRelativa, String codigoBalanzaAnalitica, String codigoPlastometro, String temperaturaEnsayo, String pesaEnsayo, String tiempoCorte, String tipoMaterial, String formaFisicaMaterial, String indiceFuidez, String promedioPesoFilamento, String promedioIndiceFluidez, String promedioMFI, String observaciones, String realizo, String rubricaRealizo, String supervisor, String estatus, String cantidadModificaciones, Timestamp createdAt, Timestamp updatedAt, MetodoMuestra metodoMuestra) {
        this.idFRAIF = idFRAIF;
        this.folioTecnica = folioTecnica;
        this.folioSolicitudServicioInterno = folioSolicitudServicioInterno;
        this.idInternoMuestra = idInternoMuestra;
        this.fechaInicioAnalisis = fechaInicioAnalisis;
        this.fechaFinalAnalisis = fechaFinalAnalisis;
        this.temperatura = temperatura;
        this.humedadRelativa = humedadRelativa;
        this.codigoBalanzaAnalitica = codigoBalanzaAnalitica;
        this.codigoPlastometro = codigoPlastometro;
        this.temperaturaEnsayo = temperaturaEnsayo;
        this.pesaEnsayo = pesaEnsayo;
        this.tiempoCorte = tiempoCorte;
        this.tipoMaterial = tipoMaterial;
        this.formaFisicaMaterial = formaFisicaMaterial;
        this.indiceFuidez = indiceFuidez;
        this.promedioPesoFilamento = promedioPesoFilamento;
        this.promedioIndiceFluidez = promedioIndiceFluidez;
        this.promedioMFI = promedioMFI;
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

    public Long getIdFRAIF() {
        return idFRAIF;
    }

    public void setIdFRAIF(Long idFRAIF) {
        this.idFRAIF = idFRAIF;
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

    public String getCodigoBalanzaAnalitica() {
        return codigoBalanzaAnalitica;
    }

    public void setCodigoBalanzaAnalitica(String codigoBalanzaAnalitica) {
        this.codigoBalanzaAnalitica = codigoBalanzaAnalitica;
    }

    public String getCodigoPlastometro() {
        return codigoPlastometro;
    }

    public void setCodigoPlastometro(String codigoPlastometro) {
        this.codigoPlastometro = codigoPlastometro;
    }

    public String getTemperaturaEnsayo() {
        return temperaturaEnsayo;
    }

    public void setTemperaturaEnsayo(String temperaturaEnsayo) {
        this.temperaturaEnsayo = temperaturaEnsayo;
    }

    public String getPesaEnsayo() {
        return pesaEnsayo;
    }

    public void setPesaEnsayo(String pesaEnsayo) {
        this.pesaEnsayo = pesaEnsayo;
    }

    public String getTiempoCorte() {
        return tiempoCorte;
    }

    public void setTiempoCorte(String tiempoCorte) {
        this.tiempoCorte = tiempoCorte;
    }

    public String getTipoMaterial() {
        return tipoMaterial;
    }

    public void setTipoMaterial(String tipoMaterial) {
        this.tipoMaterial = tipoMaterial;
    }

    public String getFormaFisicaMaterial() {
        return formaFisicaMaterial;
    }

    public void setFormaFisicaMaterial(String formaFisicaMaterial) {
        this.formaFisicaMaterial = formaFisicaMaterial;
    }

    public String getIndiceFuidez() {
        return indiceFuidez;
    }

    public void setIndiceFuidez(String indiceFuidez) {
        this.indiceFuidez = indiceFuidez;
    }

    public String getPromedioPesoFilamento() {
        return promedioPesoFilamento;
    }

    public void setPromedioPesoFilamento(String promedioPesoFilamento) {
        this.promedioPesoFilamento = promedioPesoFilamento;
    }

    public String getPromedioIndiceFluidez() {
        return promedioIndiceFluidez;
    }

    public void setPromedioIndiceFluidez(String promedioIndiceFluidez) {
        this.promedioIndiceFluidez = promedioIndiceFluidez;
    }

    public String getPromedioMFI() {
        return promedioMFI;
    }

    public void setPromedioMFI(String promedioMFI) {
        this.promedioMFI = promedioMFI;
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
