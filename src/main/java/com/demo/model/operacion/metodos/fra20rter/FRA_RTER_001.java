package com.demo.model.operacion.metodos.fra20rter;

import com.demo.model.operacion.MetodoMuestra;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
public class FRA_RTER_001 {

    @Id
    @GeneratedValue
    @Column(nullable = false)
    private Long resistenciaTensionElongacionRupturaId;

    private String folioTecnica;
    private String folioSolicitudServicioInterno;
    private String idInternoMuestra;
    private String fechaInicioAnalisis;
    private String fechaFinalAnalisis;
    private String temperatura;
    private String humedadRelativa;

    private String codigoEquipoUniversal;
    private String codigoMicrometro;
    private String distanciaEntreMordazas;
    private String velocidadDeformacion;
    private String anchoProbeta;

    private String promedioEspesorPromedioMD;
    private String promedioFuerzaFluenciatensiónMD;
    private String promedioElongacionRupturaMD;
    private String promedioResistenciaTensionMD;
    private String promedioPendienteMD;
    private String promedioCoeficienteCorrelacionPearsonMD;
    private String promedioModuloElasticoMD;

    private String promedioEspesorPromedioTD;
    private String promedioFuerzaFluenciatensiónTD;
    private String promedioElongacionRupturaTD;
    private String promedioResistenciaTensionTD;
    private String promedioPendienteTD;
    private String promedioCoeficienteCorrelacionPearsonTD;
    private String promedioModuloElasticoTD;

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

    public FRA_RTER_001() {
    }

    public FRA_RTER_001(Long resistenciaTensionElongacionRupturaId, String folioTecnica, String folioSolicitudServicioInterno, String idInternoMuestra, String fechaInicioAnalisis, String fechaFinalAnalisis, String temperatura, String humedadRelativa, String codigoEquipoUniversal, String codigoMicrometro, String distanciaEntreMordazas, String velocidadDeformacion, String anchoProbeta, String promedioEspesorPromedioMD, String promedioFuerzaFluenciatensiónMD, String promedioElongacionRupturaMD, String promedioResistenciaTensionMD, String promedioPendienteMD, String promedioCoeficienteCorrelacionPearsonMD, String promedioModuloElasticoMD, String promedioEspesorPromedioTD, String promedioFuerzaFluenciatensiónTD, String promedioElongacionRupturaTD, String promedioResistenciaTensionTD, String promedioPendienteTD, String promedioCoeficienteCorrelacionPearsonTD, String promedioModuloElasticoTD, String observaciones, String realizo, String rubricaRealizo, String supervisor, String estatus, String cantidadModificaciones, Timestamp createdAt, Timestamp updatedAt, MetodoMuestra metodoMuestra) {
        this.resistenciaTensionElongacionRupturaId = resistenciaTensionElongacionRupturaId;
        this.folioTecnica = folioTecnica;
        this.folioSolicitudServicioInterno = folioSolicitudServicioInterno;
        this.idInternoMuestra = idInternoMuestra;
        this.fechaInicioAnalisis = fechaInicioAnalisis;
        this.fechaFinalAnalisis = fechaFinalAnalisis;
        this.temperatura = temperatura;
        this.humedadRelativa = humedadRelativa;
        this.codigoEquipoUniversal = codigoEquipoUniversal;
        this.codigoMicrometro = codigoMicrometro;
        this.distanciaEntreMordazas = distanciaEntreMordazas;
        this.velocidadDeformacion = velocidadDeformacion;
        this.anchoProbeta = anchoProbeta;
        this.promedioEspesorPromedioMD = promedioEspesorPromedioMD;
        this.promedioFuerzaFluenciatensiónMD = promedioFuerzaFluenciatensiónMD;
        this.promedioElongacionRupturaMD = promedioElongacionRupturaMD;
        this.promedioResistenciaTensionMD = promedioResistenciaTensionMD;
        this.promedioPendienteMD = promedioPendienteMD;
        this.promedioCoeficienteCorrelacionPearsonMD = promedioCoeficienteCorrelacionPearsonMD;
        this.promedioModuloElasticoMD = promedioModuloElasticoMD;
        this.promedioEspesorPromedioTD = promedioEspesorPromedioTD;
        this.promedioFuerzaFluenciatensiónTD = promedioFuerzaFluenciatensiónTD;
        this.promedioElongacionRupturaTD = promedioElongacionRupturaTD;
        this.promedioResistenciaTensionTD = promedioResistenciaTensionTD;
        this.promedioPendienteTD = promedioPendienteTD;
        this.promedioCoeficienteCorrelacionPearsonTD = promedioCoeficienteCorrelacionPearsonTD;
        this.promedioModuloElasticoTD = promedioModuloElasticoTD;
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

    public Long getResistenciaTensionElongacionRupturaId() {
        return resistenciaTensionElongacionRupturaId;
    }

    public void setResistenciaTensionElongacionRupturaId(Long resistenciaTensionElongacionRupturaId) {
        this.resistenciaTensionElongacionRupturaId = resistenciaTensionElongacionRupturaId;
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

    public String getCodigoEquipoUniversal() {
        return codigoEquipoUniversal;
    }

    public void setCodigoEquipoUniversal(String codigoEquipoUniversal) {
        this.codigoEquipoUniversal = codigoEquipoUniversal;
    }

    public String getCodigoMicrometro() {
        return codigoMicrometro;
    }

    public void setCodigoMicrometro(String codigoMicrometro) {
        this.codigoMicrometro = codigoMicrometro;
    }

    public String getDistanciaEntreMordazas() {
        return distanciaEntreMordazas;
    }

    public void setDistanciaEntreMordazas(String distanciaEntreMordazas) {
        this.distanciaEntreMordazas = distanciaEntreMordazas;
    }

    public String getVelocidadDeformacion() {
        return velocidadDeformacion;
    }

    public void setVelocidadDeformacion(String velocidadDeformacion) {
        this.velocidadDeformacion = velocidadDeformacion;
    }

    public String getAnchoProbeta() {
        return anchoProbeta;
    }

    public void setAnchoProbeta(String anchoProbeta) {
        this.anchoProbeta = anchoProbeta;
    }

    public String getPromedioEspesorPromedioMD() {
        return promedioEspesorPromedioMD;
    }

    public void setPromedioEspesorPromedioMD(String promedioEspesorPromedioMD) {
        this.promedioEspesorPromedioMD = promedioEspesorPromedioMD;
    }

    public String getPromedioFuerzaFluenciatensiónMD() {
        return promedioFuerzaFluenciatensiónMD;
    }

    public void setPromedioFuerzaFluenciatensiónMD(String promedioFuerzaFluenciatensiónMD) {
        this.promedioFuerzaFluenciatensiónMD = promedioFuerzaFluenciatensiónMD;
    }

    public String getPromedioElongacionRupturaMD() {
        return promedioElongacionRupturaMD;
    }

    public void setPromedioElongacionRupturaMD(String promedioElongacionRupturaMD) {
        this.promedioElongacionRupturaMD = promedioElongacionRupturaMD;
    }

    public String getPromedioResistenciaTensionMD() {
        return promedioResistenciaTensionMD;
    }

    public void setPromedioResistenciaTensionMD(String promedioResistenciaTensionMD) {
        this.promedioResistenciaTensionMD = promedioResistenciaTensionMD;
    }

    public String getPromedioPendienteMD() {
        return promedioPendienteMD;
    }

    public void setPromedioPendienteMD(String promedioPendienteMD) {
        this.promedioPendienteMD = promedioPendienteMD;
    }

    public String getPromedioCoeficienteCorrelacionPearsonMD() {
        return promedioCoeficienteCorrelacionPearsonMD;
    }

    public void setPromedioCoeficienteCorrelacionPearsonMD(String promedioCoeficienteCorrelacionPearsonMD) {
        this.promedioCoeficienteCorrelacionPearsonMD = promedioCoeficienteCorrelacionPearsonMD;
    }

    public String getPromedioModuloElasticoMD() {
        return promedioModuloElasticoMD;
    }

    public void setPromedioModuloElasticoMD(String promedioModuloElasticoMD) {
        this.promedioModuloElasticoMD = promedioModuloElasticoMD;
    }

    public String getPromedioEspesorPromedioTD() {
        return promedioEspesorPromedioTD;
    }

    public void setPromedioEspesorPromedioTD(String promedioEspesorPromedioTD) {
        this.promedioEspesorPromedioTD = promedioEspesorPromedioTD;
    }

    public String getPromedioFuerzaFluenciatensiónTD() {
        return promedioFuerzaFluenciatensiónTD;
    }

    public void setPromedioFuerzaFluenciatensiónTD(String promedioFuerzaFluenciatensiónTD) {
        this.promedioFuerzaFluenciatensiónTD = promedioFuerzaFluenciatensiónTD;
    }

    public String getPromedioElongacionRupturaTD() {
        return promedioElongacionRupturaTD;
    }

    public void setPromedioElongacionRupturaTD(String promedioElongacionRupturaTD) {
        this.promedioElongacionRupturaTD = promedioElongacionRupturaTD;
    }

    public String getPromedioResistenciaTensionTD() {
        return promedioResistenciaTensionTD;
    }

    public void setPromedioResistenciaTensionTD(String promedioResistenciaTensionTD) {
        this.promedioResistenciaTensionTD = promedioResistenciaTensionTD;
    }

    public String getPromedioPendienteTD() {
        return promedioPendienteTD;
    }

    public void setPromedioPendienteTD(String promedioPendienteTD) {
        this.promedioPendienteTD = promedioPendienteTD;
    }

    public String getPromedioCoeficienteCorrelacionPearsonTD() {
        return promedioCoeficienteCorrelacionPearsonTD;
    }

    public void setPromedioCoeficienteCorrelacionPearsonTD(String promedioCoeficienteCorrelacionPearsonTD) {
        this.promedioCoeficienteCorrelacionPearsonTD = promedioCoeficienteCorrelacionPearsonTD;
    }

    public String getPromedioModuloElasticoTD() {
        return promedioModuloElasticoTD;
    }

    public void setPromedioModuloElasticoTD(String promedioModuloElasticoTD) {
        this.promedioModuloElasticoTD = promedioModuloElasticoTD;
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
