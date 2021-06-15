package com.demo.model.operacion.metodos.fra06ncp;

import com.demo.model.operacion.MetodoMuestra;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
public class FRA_NCP_001 {

    @Id
    @GeneratedValue
    @Column(nullable = false)
    private Long idFRANCP;

    private String folioTecnica;
    private String folioSolicitudServicioInterno;
    private String idInternoMuestra;
    private String fechaInicioAnalisis;
    private String fechaFinalAnalisis;
    private String temperatura;
    private String humedadRelativa;

    private String codigoMicrometro;
    private String codigoParrillaCalentamiento;
    private String codigoMicroscopio;
    private String lente;

    private String espesorTotalPromedioMM1;
    private String espesorTotalPromedioMM2;

    private String espesorTotalPromedioUM1;
    private String espesorTotalPromedioUM2;

    private String numeroTotalCapas1;
    private String numeroTotalCapas2;

    private String espesorTotalMicroscopia1;
    private String espesorTotalMicroscopia2;

    private String observaciones;
    private String realizo;
    private String rubricaRealizo;
    private String supervisor;

    private String estatus;
    private String cantidadModificaciones;

    private String muestraEnReporte;

    @CreationTimestamp
    private Timestamp createdAt;

    @UpdateTimestamp
    private Timestamp updatedAt;

    private String pathImagen;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "Metodo_Muestra_Id")
    private MetodoMuestra metodoMuestra;

    public FRA_NCP_001() {
    }

    public FRA_NCP_001(Long idFRANCP, String folioTecnica, String folioSolicitudServicioInterno, String idInternoMuestra, String fechaInicioAnalisis, String fechaFinalAnalisis, String temperatura, String humedadRelativa, String codigoMicrometro, String codigoParrillaCalentamiento, String codigoMicroscopio, String lente, String espesorTotalPromedioMM1, String espesorTotalPromedioMM2, String espesorTotalPromedioUM1, String espesorTotalPromedioUM2, String numeroTotalCapas1, String numeroTotalCapas2, String espesorTotalMicroscopia1, String espesorTotalMicroscopia2, String observaciones, String realizo, String rubricaRealizo, String supervisor, String estatus, String cantidadModificaciones, String muestraEnReporte, Timestamp createdAt, Timestamp updatedAt, String pathImagen, MetodoMuestra metodoMuestra) {
        this.idFRANCP = idFRANCP;
        this.folioTecnica = folioTecnica;
        this.folioSolicitudServicioInterno = folioSolicitudServicioInterno;
        this.idInternoMuestra = idInternoMuestra;
        this.fechaInicioAnalisis = fechaInicioAnalisis;
        this.fechaFinalAnalisis = fechaFinalAnalisis;
        this.temperatura = temperatura;
        this.humedadRelativa = humedadRelativa;
        this.codigoMicrometro = codigoMicrometro;
        this.codigoParrillaCalentamiento = codigoParrillaCalentamiento;
        this.codigoMicroscopio = codigoMicroscopio;
        this.lente = lente;
        this.espesorTotalPromedioMM1 = espesorTotalPromedioMM1;
        this.espesorTotalPromedioMM2 = espesorTotalPromedioMM2;
        this.espesorTotalPromedioUM1 = espesorTotalPromedioUM1;
        this.espesorTotalPromedioUM2 = espesorTotalPromedioUM2;
        this.numeroTotalCapas1 = numeroTotalCapas1;
        this.numeroTotalCapas2 = numeroTotalCapas2;
        this.espesorTotalMicroscopia1 = espesorTotalMicroscopia1;
        this.espesorTotalMicroscopia2 = espesorTotalMicroscopia2;
        this.observaciones = observaciones;
        this.realizo = realizo;
        this.rubricaRealizo = rubricaRealizo;
        this.supervisor = supervisor;
        this.estatus = estatus;
        this.cantidadModificaciones = cantidadModificaciones;
        this.muestraEnReporte = muestraEnReporte;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.pathImagen = pathImagen;
        this.metodoMuestra = metodoMuestra;
    }

    public Long getIdFRANCP() {
        return idFRANCP;
    }

    public void setIdFRANCP(Long idFRANCP) {
        this.idFRANCP = idFRANCP;
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

    public String getCodigoMicrometro() {
        return codigoMicrometro;
    }

    public void setCodigoMicrometro(String codigoMicrometro) {
        this.codigoMicrometro = codigoMicrometro;
    }

    public String getCodigoParrillaCalentamiento() {
        return codigoParrillaCalentamiento;
    }

    public void setCodigoParrillaCalentamiento(String codigoParrillaCalentamiento) {
        this.codigoParrillaCalentamiento = codigoParrillaCalentamiento;
    }

    public String getCodigoMicroscopio() {
        return codigoMicroscopio;
    }

    public void setCodigoMicroscopio(String codigoMicroscopio) {
        this.codigoMicroscopio = codigoMicroscopio;
    }

    public String getLente() {
        return lente;
    }

    public void setLente(String lente) {
        this.lente = lente;
    }

    public String getEspesorTotalPromedioMM1() {
        return espesorTotalPromedioMM1;
    }

    public void setEspesorTotalPromedioMM1(String espesorTotalPromedioMM1) {
        this.espesorTotalPromedioMM1 = espesorTotalPromedioMM1;
    }

    public String getEspesorTotalPromedioMM2() {
        return espesorTotalPromedioMM2;
    }

    public void setEspesorTotalPromedioMM2(String espesorTotalPromedioMM2) {
        this.espesorTotalPromedioMM2 = espesorTotalPromedioMM2;
    }

    public String getEspesorTotalPromedioUM1() {
        return espesorTotalPromedioUM1;
    }

    public void setEspesorTotalPromedioUM1(String espesorTotalPromedioUM1) {
        this.espesorTotalPromedioUM1 = espesorTotalPromedioUM1;
    }

    public String getEspesorTotalPromedioUM2() {
        return espesorTotalPromedioUM2;
    }

    public void setEspesorTotalPromedioUM2(String espesorTotalPromedioUM2) {
        this.espesorTotalPromedioUM2 = espesorTotalPromedioUM2;
    }

    public String getNumeroTotalCapas1() {
        return numeroTotalCapas1;
    }

    public void setNumeroTotalCapas1(String numeroTotalCapas1) {
        this.numeroTotalCapas1 = numeroTotalCapas1;
    }

    public String getNumeroTotalCapas2() {
        return numeroTotalCapas2;
    }

    public void setNumeroTotalCapas2(String numeroTotalCapas2) {
        this.numeroTotalCapas2 = numeroTotalCapas2;
    }

    public String getEspesorTotalMicroscopia1() {
        return espesorTotalMicroscopia1;
    }

    public void setEspesorTotalMicroscopia1(String espesorTotalMicroscopia1) {
        this.espesorTotalMicroscopia1 = espesorTotalMicroscopia1;
    }

    public String getEspesorTotalMicroscopia2() {
        return espesorTotalMicroscopia2;
    }

    public void setEspesorTotalMicroscopia2(String espesorTotalMicroscopia2) {
        this.espesorTotalMicroscopia2 = espesorTotalMicroscopia2;
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

    public String getPathImagen() {
        return pathImagen;
    }

    public void setPathImagen(String pathImagen) {
        this.pathImagen = pathImagen;
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

    public String getMuestraEnReporte() {
        return muestraEnReporte;
    }

    public void setMuestraEnReporte(String muestraEnReporte) {
        this.muestraEnReporte = muestraEnReporte;
    }
}
