package com.demo.model.operacion;

import com.demo.model.Client;
import javax.persistence.*;

@Entity
public class SolicitudServicioCliente {

    @Id
    @GeneratedValue
    @Column(name = "Solicitud_Servicio_Cliente_Id", nullable = false)
    private Long solicitudServicioClienteId;

    @Column(name = "Folio_Solicitud_Servicio_Cliente", length = 250, nullable = false)
    private String folioSolitudServicioCliente;

    @Column(name = "Fecha_Envio_Muestras", length = 250, nullable = false)
    private String fechaEnvioMuestras;

    @Column(name = "Fecha_Pago", length = 250, nullable = false)
    private String fechaPago;

    @Column(name = "Nombre_Firma_Emisor", length = 250, nullable = false)
    private String nombreFirmaEmisor;

    @Column(name = "Almacenamiento_Especial", length = 250, nullable = false)
    private boolean almacenamientoEspecial;

    @Column(name = "Fecha_Recepcion_Muestras", length = 250, nullable = false)
    private String fechaRecepcionMuestras;

    @Column(name = "Fecha_Compromiso_Entrega", length = 250, nullable = false)
    private String fechaCompromisoEntrega;

    @Column(name = "Nombre_Firma_Receptor", length = 250, nullable = false)
    private String nombreFirmaReceptor;

    @Column(name = "Nombre_Firma_Calidad", length = 250, nullable = false)
    private String nombreFirmaCalidad;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "Client_Id")
    private Client client;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "Solicitud_Servicio_Cliente_Muestras_Id")
    private SolicitudServicioClienteMuestras solicitudServicioClienteMuestras;

    public SolicitudServicioCliente() {
    }

    public SolicitudServicioCliente(Long solicitudServicioClienteId, String folioSolitudServicioCliente, String fechaEnvioMuestras, String fechaPago, String nombreFirmaEmisor, boolean almacenamientoEspecial, String fechaRecepcionMuestras, String fechaCompromisoEntrega, String nombreFirmaReceptor, String nombreFirmaCalidad, Client client, SolicitudServicioClienteMuestras solicitudServicioClienteMuestras) {
        this.solicitudServicioClienteId = solicitudServicioClienteId;
        this.folioSolitudServicioCliente = folioSolitudServicioCliente;
        this.fechaEnvioMuestras = fechaEnvioMuestras;
        this.fechaPago = fechaPago;
        this.nombreFirmaEmisor = nombreFirmaEmisor;
        this.almacenamientoEspecial = almacenamientoEspecial;
        this.fechaRecepcionMuestras = fechaRecepcionMuestras;
        this.fechaCompromisoEntrega = fechaCompromisoEntrega;
        this.nombreFirmaReceptor = nombreFirmaReceptor;
        this.nombreFirmaCalidad = nombreFirmaCalidad;
        this.client = client;
        this.solicitudServicioClienteMuestras = solicitudServicioClienteMuestras;
    }

    public Long getSolicitudServicioClienteId() {
        return solicitudServicioClienteId;
    }

    public void setSolicitudServicioClienteId(Long solicitudServicioClienteId) {
        this.solicitudServicioClienteId = solicitudServicioClienteId;
    }

    public String getFolioSolitudServicioCliente() {
        return folioSolitudServicioCliente;
    }

    public void setFolioSolitudServicioCliente(String folioSolitudServicioCliente) {
        this.folioSolitudServicioCliente = folioSolitudServicioCliente;
    }

    public String getFechaEnvioMuestras() {
        return fechaEnvioMuestras;
    }

    public void setFechaEnvioMuestras(String fechaEnvioMuestras) {
        this.fechaEnvioMuestras = fechaEnvioMuestras;
    }

    public String getFechaPago() {
        return fechaPago;
    }

    public void setFechaPago(String fechaPago) {
        this.fechaPago = fechaPago;
    }

    public String getNombreFirmaEmisor() {
        return nombreFirmaEmisor;
    }

    public void setNombreFirmaEmisor(String nombreFirmaEmisor) {
        this.nombreFirmaEmisor = nombreFirmaEmisor;
    }

    public boolean isAlmacenamientoEspecial() {
        return almacenamientoEspecial;
    }

    public void setAlmacenamientoEspecial(boolean almacenamientoEspecial) {
        this.almacenamientoEspecial = almacenamientoEspecial;
    }

    public String getFechaRecepcionMuestras() {
        return fechaRecepcionMuestras;
    }

    public void setFechaRecepcionMuestras(String fechaRecepcionMuestras) {
        this.fechaRecepcionMuestras = fechaRecepcionMuestras;
    }

    public String getFechaCompromisoEntrega() {
        return fechaCompromisoEntrega;
    }

    public void setFechaCompromisoEntrega(String fechaCompromisoEntrega) {
        this.fechaCompromisoEntrega = fechaCompromisoEntrega;
    }

    public String getNombreFirmaReceptor() {
        return nombreFirmaReceptor;
    }

    public void setNombreFirmaReceptor(String nombreFirmaReceptor) {
        this.nombreFirmaReceptor = nombreFirmaReceptor;
    }

    public String getNombreFirmaCalidad() {
        return nombreFirmaCalidad;
    }

    public void setNombreFirmaCalidad(String nombreFirmaCalidad) {
        this.nombreFirmaCalidad = nombreFirmaCalidad;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public SolicitudServicioClienteMuestras getSolicitudServicioClienteMuestras() {
        return solicitudServicioClienteMuestras;
    }

    public void setSolicitudServicioClienteMuestras(SolicitudServicioClienteMuestras solicitudServicioClienteMuestras) {
        this.solicitudServicioClienteMuestras = solicitudServicioClienteMuestras;
    }

    @Override
    public String toString() {
        return "SolicitudServicioCliente{" +
                "solicitudServicioClienteId=" + solicitudServicioClienteId +
                ", folioSolitudServicioCliente='" + folioSolitudServicioCliente + '\'' +
                ", fechaEnvioMuestras='" + fechaEnvioMuestras + '\'' +
                ", fechaPago='" + fechaPago + '\'' +
                ", nombreFirmaEmisor='" + nombreFirmaEmisor + '\'' +
                ", almacenamientoEspecial=" + almacenamientoEspecial +
                ", fechaRecepcionMuestras='" + fechaRecepcionMuestras + '\'' +
                ", fechaCompromisoEntrega='" + fechaCompromisoEntrega + '\'' +
                ", nombreFirmaReceptor='" + nombreFirmaReceptor + '\'' +
                ", nombreFirmaCalidad='" + nombreFirmaCalidad + '\'' +
                ", client=" + client +
                ", solicitudServicioClienteMuestras=" + solicitudServicioClienteMuestras +
                '}';
    }
}
