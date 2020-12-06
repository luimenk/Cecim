package com.demo.model.operacion.metodos;

import com.demo.model.operacion.MetodoMuestra;

import javax.persistence.*;

@Entity
public class FRA_CST_001 {

    @Id
    @GeneratedValue
    @Column(nullable = false)
    private Long curvaSelladoId;

    @Column(length = 250, nullable = false)
    private String folioSolicitudServicioInterno;

    @Column(length = 250, nullable = false)
    private String idInternoMuestra;

    @Column(length = 250, nullable = false)
    private String fechaInicioAnalisis;

    @Column(length = 250, nullable = false)
    private String fechaFinalAnalisis;

    @Column(length = 250, nullable = false)
    private String temperatura;

    @Column(length = 250, nullable = false)
    private String codigoMicrometro;

    @Column(length = 250, nullable = false)
    private String humedadRelativa;

    @Column(length = 250, nullable = false)
    private String codigoLaboratorioSello;

    @Column(length = 250, nullable = false)
    private String largoMuestra;

    @Column(length = 250, nullable = false)
    private String anchoMuestra;

    @Column(length = 250, nullable = false)
    private String numeroRepeticionesMuestra;

    @Column(length = 250, nullable = false)
    private String rangoTemperatura;

    @Column(length = 250, nullable = false)
    private String tasaCalentamiento;

    @Column(length = 250, nullable = false)
    private String tempoSellado;

    @Column(length = 250, nullable = false)
    private String tiempoRetraso;

    @Column(length = 250, nullable = false)
    private String presion;

    @Column(length = 250, nullable = false)
    private String tipoMordazas;

    @Column(length = 250, nullable = false)
    private String velocidadEnsayo;

    @Column(length = 250, nullable = false)
    private String temperaturaOptima1;

    @Column(length = 250, nullable = false)
    private String fuerzaSello;

    @Column(length = 250, nullable = false)
    private String desviacionEstandar;

    @Column(length = 250, nullable = false)
    private String observaciones;

    @Column(length = 250, nullable = false)
    private String realizo;

    @Column(length = 250, nullable = false)
    private String supervisor;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "Metodo_Muestra_Id")
    private MetodoMuestra metodoMuestra;

    public FRA_CST_001() {
    }

    public FRA_CST_001(Long curvaSelladoId, String folioSolicitudServicioInterno, String idInternoMuestra, String fechaInicioAnalisis, String fechaFinalAnalisis, String temperatura, String codigoMicrometro, String humedadRelativa, String codigoLaboratorioSello, String largoMuestra, String anchoMuestra, String numeroRepeticionesMuestra, String rangoTemperatura, String tasaCalentamiento, String tempoSellado, String tiempoRetraso, String presion, String tipoMordazas, String velocidadEnsayo, String temperaturaOptima1, String fuerzaSello, String desviacionEstandar, String observaciones, String realizo, String supervisor, MetodoMuestra metodoMuestra) {
        this.curvaSelladoId = curvaSelladoId;
        this.folioSolicitudServicioInterno = folioSolicitudServicioInterno;
        this.idInternoMuestra = idInternoMuestra;
        this.fechaInicioAnalisis = fechaInicioAnalisis;
        this.fechaFinalAnalisis = fechaFinalAnalisis;
        this.temperatura = temperatura;
        this.codigoMicrometro = codigoMicrometro;
        this.humedadRelativa = humedadRelativa;
        this.codigoLaboratorioSello = codigoLaboratorioSello;
        this.largoMuestra = largoMuestra;
        this.anchoMuestra = anchoMuestra;
        this.numeroRepeticionesMuestra = numeroRepeticionesMuestra;
        this.rangoTemperatura = rangoTemperatura;
        this.tasaCalentamiento = tasaCalentamiento;
        this.tempoSellado = tempoSellado;
        this.tiempoRetraso = tiempoRetraso;
        this.presion = presion;
        this.tipoMordazas = tipoMordazas;
        this.velocidadEnsayo = velocidadEnsayo;
        this.temperaturaOptima1 = temperaturaOptima1;
        this.fuerzaSello = fuerzaSello;
        this.desviacionEstandar = desviacionEstandar;
        this.observaciones = observaciones;
        this.realizo = realizo;
        this.supervisor = supervisor;
        this.metodoMuestra = metodoMuestra;
    }

    public Long getCurvaSelladoId() {
        return curvaSelladoId;
    }

    public void setCurvaSelladoId(Long curvaSelladoId) {
        this.curvaSelladoId = curvaSelladoId;
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

    public String getCodigoMicrometro() {
        return codigoMicrometro;
    }

    public void setCodigoMicrometro(String codigoMicrometro) {
        this.codigoMicrometro = codigoMicrometro;
    }

    public String getHumedadRelativa() {
        return humedadRelativa;
    }

    public void setHumedadRelativa(String humedadRelativa) {
        this.humedadRelativa = humedadRelativa;
    }

    public String getCodigoLaboratorioSello() {
        return codigoLaboratorioSello;
    }

    public void setCodigoLaboratorioSello(String codigoLaboratorioSello) {
        this.codigoLaboratorioSello = codigoLaboratorioSello;
    }

    public String getLargoMuestra() {
        return largoMuestra;
    }

    public void setLargoMuestra(String largoMuestra) {
        this.largoMuestra = largoMuestra;
    }

    public String getAnchoMuestra() {
        return anchoMuestra;
    }

    public void setAnchoMuestra(String anchoMuestra) {
        this.anchoMuestra = anchoMuestra;
    }

    public String getNumeroRepeticionesMuestra() {
        return numeroRepeticionesMuestra;
    }

    public void setNumeroRepeticionesMuestra(String numeroRepeticionesMuestra) {
        this.numeroRepeticionesMuestra = numeroRepeticionesMuestra;
    }

    public String getRangoTemperatura() {
        return rangoTemperatura;
    }

    public void setRangoTemperatura(String rangoTemperatura) {
        this.rangoTemperatura = rangoTemperatura;
    }

    public String getTasaCalentamiento() {
        return tasaCalentamiento;
    }

    public void setTasaCalentamiento(String tasaCalentamiento) {
        this.tasaCalentamiento = tasaCalentamiento;
    }

    public String getTempoSellado() {
        return tempoSellado;
    }

    public void setTempoSellado(String tempoSellado) {
        this.tempoSellado = tempoSellado;
    }

    public String getTiempoRetraso() {
        return tiempoRetraso;
    }

    public void setTiempoRetraso(String tiempoRetraso) {
        this.tiempoRetraso = tiempoRetraso;
    }

    public String getPresion() {
        return presion;
    }

    public void setPresion(String presion) {
        this.presion = presion;
    }

    public String getTipoMordazas() {
        return tipoMordazas;
    }

    public void setTipoMordazas(String tipoMordazas) {
        this.tipoMordazas = tipoMordazas;
    }

    public String getVelocidadEnsayo() {
        return velocidadEnsayo;
    }

    public void setVelocidadEnsayo(String velocidadEnsayo) {
        this.velocidadEnsayo = velocidadEnsayo;
    }

    public String getTemperaturaOptima1() {
        return temperaturaOptima1;
    }

    public void setTemperaturaOptima1(String temperaturaOptima1) {
        this.temperaturaOptima1 = temperaturaOptima1;
    }

    public String getFuerzaSello() {
        return fuerzaSello;
    }

    public void setFuerzaSello(String fuerzaSello) {
        this.fuerzaSello = fuerzaSello;
    }

    public String getDesviacionEstandar() {
        return desviacionEstandar;
    }

    public void setDesviacionEstandar(String desviacionEstandar) {
        this.desviacionEstandar = desviacionEstandar;
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

    public MetodoMuestra getMetodoMuestra() {
        return metodoMuestra;
    }

    public void setMetodoMuestra(MetodoMuestra metodoMuestra) {
        this.metodoMuestra = metodoMuestra;
    }
}
