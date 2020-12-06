package com.demo.model.operacion.metodos;

import com.demo.model.operacion.MetodoMuestra;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
public class FRA_NCP_001 {

    @Id
    @GeneratedValue
    @Column(nullable = false)
    private Long idFRANCP;

    @Column(length = 30, nullable = false)
    private String folioSolicitudServicioInterno;

    @Column(length = 30, nullable = false)
    private String idInternoMuestra;

    @Column(length = 30, nullable = false)
    private String fechaInicioAnalisis;

    @Column(length = 30, nullable = false)
    private String fechaFinalAnalisis;

    @Column(length = 30, nullable = false)
    private String temperatura;

    @Column(length = 30, nullable = false)
    private String humedadRelativa;

    @Column(length = 30, nullable = false)
    private String codigoMicroscopio;

    @Column(length = 30, nullable = false)
    private String cpc;

    @Column(length = 30, nullable = false)
    private String codigoMicrometro;

    //Resultados
    @Column(length = 30, nullable = false)
    private String espesorTotalMuestra;

    @Column(length = 30, nullable = false)
    private String espesor1;

    @Column(length = 30, nullable = false)
    private String espesor2;

    @Column(length = 30, nullable = false)
    private String espesor3;

    @Column(length = 30, nullable = false)
    private String espesor4;

    @Column(length = 30, nullable = false)
    private String espesor5;

    @Column(length = 30, nullable = false)
    private String espesor6;

    @Column(length = 30, nullable = false)
    private String total;

    //GENERALES
    @Column(length = 250, nullable = false)
    private String observaciones;

    @Column(length = 250, nullable = false)
    private String realizo;

    @Column(length = 250, nullable = false)
    private String supervisor;

    //Path de Imagen
    private String pathImagen;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "Metodo_Muestra_Id")
    private MetodoMuestra metodoMuestra;

    public FRA_NCP_001() {
    }

    public FRA_NCP_001(Long idFRANCP, String folioSolicitudServicioInterno, String idInternoMuestra, String fechaInicioAnalisis, String fechaFinalAnalisis, String temperatura, String humedadRelativa, String codigoMicroscopio, String cpc, String codigoMicrometro, String espesorTotalMuestra, String espesor1, String espesor2, String espesor3, String espesor4, String espesor5, String espesor6, String total, String observaciones, String realizo, String supervisor, String pathImagen, MetodoMuestra metodoMuestra) {
        this.idFRANCP = idFRANCP;
        this.folioSolicitudServicioInterno = folioSolicitudServicioInterno;
        this.idInternoMuestra = idInternoMuestra;
        this.fechaInicioAnalisis = fechaInicioAnalisis;
        this.fechaFinalAnalisis = fechaFinalAnalisis;
        this.temperatura = temperatura;
        this.humedadRelativa = humedadRelativa;
        this.codigoMicroscopio = codigoMicroscopio;
        this.cpc = cpc;
        this.codigoMicrometro = codigoMicrometro;
        this.espesorTotalMuestra = espesorTotalMuestra;
        this.espesor1 = espesor1;
        this.espesor2 = espesor2;
        this.espesor3 = espesor3;
        this.espesor4 = espesor4;
        this.espesor5 = espesor5;
        this.espesor6 = espesor6;
        this.total = total;
        this.observaciones = observaciones;
        this.realizo = realizo;
        this.supervisor = supervisor;
        this.pathImagen = pathImagen;
        this.metodoMuestra = metodoMuestra;
    }

    public Long getIdFRANCP() {
        return idFRANCP;
    }

    public void setIdFRANCP(Long idFRANCP) {
        this.idFRANCP = idFRANCP;
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

    public String getCodigoMicroscopio() {
        return codigoMicroscopio;
    }

    public void setCodigoMicroscopio(String codigoMicroscopio) {
        this.codigoMicroscopio = codigoMicroscopio;
    }

    public String getCpc() {
        return cpc;
    }

    public void setCpc(String cpc) {
        this.cpc = cpc;
    }

    public String getCodigoMicrometro() {
        return codigoMicrometro;
    }

    public void setCodigoMicrometro(String codigoMicrometro) {
        this.codigoMicrometro = codigoMicrometro;
    }

    public String getEspesorTotalMuestra() {
        return espesorTotalMuestra;
    }

    public void setEspesorTotalMuestra(String espesorTotalMuestra) {
        this.espesorTotalMuestra = espesorTotalMuestra;
    }

    public String getEspesor1() {
        return espesor1;
    }

    public void setEspesor1(String espesor1) {
        this.espesor1 = espesor1;
    }

    public String getEspesor2() {
        return espesor2;
    }

    public void setEspesor2(String espesor2) {
        this.espesor2 = espesor2;
    }

    public String getEspesor3() {
        return espesor3;
    }

    public void setEspesor3(String espesor3) {
        this.espesor3 = espesor3;
    }

    public String getEspesor4() {
        return espesor4;
    }

    public void setEspesor4(String espesor4) {
        this.espesor4 = espesor4;
    }

    public String getEspesor5() {
        return espesor5;
    }

    public void setEspesor5(String espesor5) {
        this.espesor5 = espesor5;
    }

    public String getEspesor6() {
        return espesor6;
    }

    public void setEspesor6(String espesor6) {
        this.espesor6 = espesor6;
    }

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
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
}
