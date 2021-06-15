package com.demo.model.operacion.metodos.fra19prr;

import com.demo.model.operacion.MetodoMuestra;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
public class FRA_PRR_001 {

    @Id
    @GeneratedValue
    @Column(nullable = false)
    private Long idFRAPRR;

    private String folioTecnica;
    private String folioSolicitudServicioInterno;
    private String idInternoMuestra;
    private String fechaInicioAnalisis;
    private String fechaFinalAnalisis;
    private String temperatura;
    private String humedadRelativa;

    private String codigoPendulo;
    private String codigoManometro;
    private String capacidadEquipo;
    private String prensaEnsayo;
    private String pesaCalibracion;
    
    private String promedioResistenciaRasgadoMD;
    private String promedioResistenciaRasgadoTD;
    private String desgarreOblicuioMD;
    private String desgarreOblicuioTD;
    private String minMD;
    private String minTD;
    private String maxMD;
    private String maxTD;
    private String desvEstandarMD;
    private String desvEstandarTD;
    private String espesorPromedioMD;
    private String espesorPromedioTD;

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

    public FRA_PRR_001() {
    }

    public FRA_PRR_001(Long idFRAPRR, String folioTecnica, String folioSolicitudServicioInterno, String idInternoMuestra, String fechaInicioAnalisis, String fechaFinalAnalisis, String temperatura, String humedadRelativa, String codigoPendulo, String codigoManometro, String capacidadEquipo, String prensaEnsayo, String pesaCalibracion, String promedioResistenciaRasgadoMD, String promedioResistenciaRasgadoTD, String desgarreOblicuioMD, String desgarreOblicuioTD, String minMD, String minTD, String maxMD, String maxTD, String desvEstandarMD, String desvEstandarTD, String espesorPromedioMD, String espesorPromedioTD, String observaciones, String realizo, String rubricaRealizo, String supervisor, String estatus, String cantidadModificaciones, Timestamp createdAt, Timestamp updatedAt, MetodoMuestra metodoMuestra) {
        this.idFRAPRR = idFRAPRR;
        this.folioTecnica = folioTecnica;
        this.folioSolicitudServicioInterno = folioSolicitudServicioInterno;
        this.idInternoMuestra = idInternoMuestra;
        this.fechaInicioAnalisis = fechaInicioAnalisis;
        this.fechaFinalAnalisis = fechaFinalAnalisis;
        this.temperatura = temperatura;
        this.humedadRelativa = humedadRelativa;
        this.codigoPendulo = codigoPendulo;
        this.codigoManometro = codigoManometro;
        this.capacidadEquipo = capacidadEquipo;
        this.prensaEnsayo = prensaEnsayo;
        this.pesaCalibracion = pesaCalibracion;
        this.promedioResistenciaRasgadoMD = promedioResistenciaRasgadoMD;
        this.promedioResistenciaRasgadoTD = promedioResistenciaRasgadoTD;
        this.desgarreOblicuioMD = desgarreOblicuioMD;
        this.desgarreOblicuioTD = desgarreOblicuioTD;
        this.minMD = minMD;
        this.minTD = minTD;
        this.maxMD = maxMD;
        this.maxTD = maxTD;
        this.desvEstandarMD = desvEstandarMD;
        this.desvEstandarTD = desvEstandarTD;
        this.espesorPromedioMD = espesorPromedioMD;
        this.espesorPromedioTD = espesorPromedioTD;
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

    public Long getIdFRAPRR() {
        return idFRAPRR;
    }

    public void setIdFRAPRR(Long idFRAPRR) {
        this.idFRAPRR = idFRAPRR;
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

    public String getCodigoPendulo() {
        return codigoPendulo;
    }

    public void setCodigoPendulo(String codigoPendulo) {
        this.codigoPendulo = codigoPendulo;
    }

    public String getCodigoManometro() {
        return codigoManometro;
    }

    public void setCodigoManometro(String codigoManometro) {
        this.codigoManometro = codigoManometro;
    }

    public String getCapacidadEquipo() {
        return capacidadEquipo;
    }

    public void setCapacidadEquipo(String capacidadEquipo) {
        this.capacidadEquipo = capacidadEquipo;
    }

    public String getPrensaEnsayo() {
        return prensaEnsayo;
    }

    public void setPrensaEnsayo(String prensaEnsayo) {
        this.prensaEnsayo = prensaEnsayo;
    }

    public String getPesaCalibracion() {
        return pesaCalibracion;
    }

    public void setPesaCalibracion(String pesaCalibracion) {
        this.pesaCalibracion = pesaCalibracion;
    }

    public String getPromedioResistenciaRasgadoMD() {
        return promedioResistenciaRasgadoMD;
    }

    public void setPromedioResistenciaRasgadoMD(String promedioResistenciaRasgadoMD) {
        this.promedioResistenciaRasgadoMD = promedioResistenciaRasgadoMD;
    }

    public String getPromedioResistenciaRasgadoTD() {
        return promedioResistenciaRasgadoTD;
    }

    public void setPromedioResistenciaRasgadoTD(String promedioResistenciaRasgadoTD) {
        this.promedioResistenciaRasgadoTD = promedioResistenciaRasgadoTD;
    }

    public String getDesgarreOblicuioMD() {
        return desgarreOblicuioMD;
    }

    public void setDesgarreOblicuioMD(String desgarreOblicuioMD) {
        this.desgarreOblicuioMD = desgarreOblicuioMD;
    }

    public String getDesgarreOblicuioTD() {
        return desgarreOblicuioTD;
    }

    public void setDesgarreOblicuioTD(String desgarreOblicuioTD) {
        this.desgarreOblicuioTD = desgarreOblicuioTD;
    }

    public String getMinMD() {
        return minMD;
    }

    public void setMinMD(String minMD) {
        this.minMD = minMD;
    }

    public String getMinTD() {
        return minTD;
    }

    public void setMinTD(String minTD) {
        this.minTD = minTD;
    }

    public String getMaxMD() {
        return maxMD;
    }

    public void setMaxMD(String maxMD) {
        this.maxMD = maxMD;
    }

    public String getMaxTD() {
        return maxTD;
    }

    public void setMaxTD(String maxTD) {
        this.maxTD = maxTD;
    }

    public String getDesvEstandarMD() {
        return desvEstandarMD;
    }

    public void setDesvEstandarMD(String desvEstandarMD) {
        this.desvEstandarMD = desvEstandarMD;
    }

    public String getDesvEstandarTD() {
        return desvEstandarTD;
    }

    public void setDesvEstandarTD(String desvEstandarTD) {
        this.desvEstandarTD = desvEstandarTD;
    }

    public String getEspesorPromedioMD() {
        return espesorPromedioMD;
    }

    public void setEspesorPromedioMD(String espesorPromedioMD) {
        this.espesorPromedioMD = espesorPromedioMD;
    }

    public String getEspesorPromedioTD() {
        return espesorPromedioTD;
    }

    public void setEspesorPromedioTD(String espesorPromedioTD) {
        this.espesorPromedioTD = espesorPromedioTD;
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