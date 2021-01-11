package com.demo.model.operacion;

import com.demo.model.Client;
import com.demo.model.Method;

import javax.persistence.*;

@Entity
public class SolicitudServicioClienteMuestras {

    @Id
    @GeneratedValue
    @Column(name = "Solicitud_Servicio_Cliente_Muestras_Id", nullable = false)
    private Long solicitudServicioClienteMuestrasId;

    @Column(name = "Id_Cliente_Muestra", length = 250, nullable = false)
    private String idClienteMuestra;

    @Column(name = "Tipo_Muestra", length = 250, nullable = false)
    private String tipoMuestra;

    @Column(name = "Descripcion_Muestra", length = 250, nullable = false)
    private String descripcionMuestra;

    @Column(name = "Lote", length = 250, nullable = false)
    private String lote;

    @Column(name = "Condiciones_Especiales", length = 250, nullable = false)
    private String condicionesEspeciales;

    @Column(name = "Observaciones", length = 250, nullable = false)
    private String observaciones;

    @Column(name = "Path_Qr_Identificacion", length = 250, nullable = false)
    private String pathQRIdentificacion;

    @Column(name = "Estatus", length = 250, nullable = false)
    private String estatus;

    @Column(name = "Ensayos", length = 50, nullable = false)
    private String totalEnsayos;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "Solicitud_Servicio_Cliente_Id")
    private SolicitudServicioCliente solicitudServicioCliente;

    public SolicitudServicioClienteMuestras() {
    }

    public SolicitudServicioClienteMuestras(Long solicitudServicioClienteMuestrasId, String idClienteMuestra, String tipoMuestra, String descripcionMuestra, String lote, String condicionesEspeciales, String observaciones, String pathQRIdentificacion, String estatus, SolicitudServicioCliente solicitudServicioCliente) {
        this.solicitudServicioClienteMuestrasId = solicitudServicioClienteMuestrasId;
        this.idClienteMuestra = idClienteMuestra;
        this.tipoMuestra = tipoMuestra;
        this.descripcionMuestra = descripcionMuestra;
        this.lote = lote;
        this.condicionesEspeciales = condicionesEspeciales;
        this.observaciones = observaciones;
        this.pathQRIdentificacion = pathQRIdentificacion;
        this.estatus = estatus;
        this.solicitudServicioCliente = solicitudServicioCliente;
    }

    public Long getSolicitudServicioClienteMuestrasId() {
        return solicitudServicioClienteMuestrasId;
    }

    public void setSolicitudServicioClienteMuestrasId(Long solicitudServicioClienteMuestrasId) {
        this.solicitudServicioClienteMuestrasId = solicitudServicioClienteMuestrasId;
    }

    public String getIdClienteMuestra() {
        return idClienteMuestra;
    }

    public void setIdClienteMuestra(String idClienteMuestra) {
        this.idClienteMuestra = idClienteMuestra;
    }

    public String getTipoMuestra() {
        return tipoMuestra;
    }

    public void setTipoMuestra(String tipoMuestra) {
        this.tipoMuestra = tipoMuestra;
    }

    public String getDescripcionMuestra() {
        return descripcionMuestra;
    }

    public void setDescripcionMuestra(String descripcionMuestra) {
        this.descripcionMuestra = descripcionMuestra;
    }

    public String getLote() {
        return lote;
    }

    public void setLote(String lote) {
        this.lote = lote;
    }

    public String getCondicionesEspeciales() {
        return condicionesEspeciales;
    }

    public void setCondicionesEspeciales(String condicionesEspeciales) {
        this.condicionesEspeciales = condicionesEspeciales;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    public String getPathQRIdentificacion() {
        return pathQRIdentificacion;
    }

    public void setPathQRIdentificacion(String pathQRIdentificacion) {
        this.pathQRIdentificacion = pathQRIdentificacion;
    }

    public String getEstatus() {
        return estatus;
    }

    public void setEstatus(String estatus) {
        this.estatus = estatus;
    }

    public SolicitudServicioCliente getSolicitudServicioCliente() {
        return solicitudServicioCliente;
    }

    public void setSolicitudServicioCliente(SolicitudServicioCliente solicitudServicioCliente) {
        this.solicitudServicioCliente = solicitudServicioCliente;
    }

    public String getTotalEnsayos() {
        return totalEnsayos;
    }

    public void setTotalEnsayos(String totalEnsayos) {
        this.totalEnsayos = totalEnsayos;
    }

    @Override
    public String toString() {
        return "SolicitudServicioClienteMuestras{" +
                "solicitudServicioClienteMuestrasId=" + solicitudServicioClienteMuestrasId +
                ", idClienteMuestra='" + idClienteMuestra + '\'' +
                ", tipoMuestra='" + tipoMuestra + '\'' +
                ", descripcionMuestra='" + descripcionMuestra + '\'' +
                ", lote='" + lote + '\'' +
                ", condicionesEspeciales='" + condicionesEspeciales + '\'' +
                ", observaciones='" + observaciones + '\'' +
                ", pathQRIdentificacion='" + pathQRIdentificacion + '\'' +
                ", estatus='" + estatus + '\'' +
                ", solicitudServicioCliente=" + solicitudServicioCliente +
                '}';
    }
}
