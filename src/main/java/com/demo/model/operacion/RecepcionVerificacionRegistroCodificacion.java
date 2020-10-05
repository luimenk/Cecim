package com.demo.model.operacion;

import com.demo.model.Client;
import com.demo.model.Method;
import lombok.*;

import javax.persistence.*;

@Entity
public class RecepcionVerificacionRegistroCodificacion {

    @Id
    @GeneratedValue
    @Column(name = "Recepcion_Verificacion_Registro_Codificacion_Id", nullable = false)
    private Long recepcionVerificacionRegistroCodificacionId;

    /** Verificación de etiqueta de identificación de muestra del cliente **/
    @Column(name = "Cuenta_Con_Etiqueta", length = 250, nullable = false)
    private String cuentaConEtiqueta;

    @Column(name = "Utilizo_Feim", length = 250, nullable = false)
    private String utilizoFeim;

    @Column(name = "Cantidad_Muestra_Entregada", length = 250, nullable = false)
    private String cantidadMuestraEntregada;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "Solicitud_Servicio_Cliente_Muestras_Id")
    private SolicitudServicioClienteMuestras solicitudServicioClienteMuestras;

    /** Recepción y verificación registro y codificación de la muestra **/
    @Column(name = "Fecha_Recepcion", length = 250, nullable = false)
    private String fechaRecepcion;

    @Column(name = "Folio_Recepcion_Verificacion", length = 250, nullable = false)
    private String folioRecepcionVerificacion;

    @Column(name = "Nombre_Persona_Recibe", length = 250, nullable = false)
    private String nombrePersonaRecibe;

    @Column(name = "Nombre_Persona_Entrega", length = 250, nullable = false)
    private String nombrePersonaEntrega;

    @Column(name = "Medio_Recepcion", length = 250, nullable = false)
    private String medioRecepcion;

    @Column(name = "Id_Interno_Muestra1", length = 250, nullable = false)
    private String idInternoMuestra1;

    @Column(name = "Id_Interno_Muestra2", length = 250, nullable = false)
    private String idInternoMuestra2;

    @Column(name = "Condiciones_Muestra1", length = 250, nullable = false)
    private String condicionesMuestra1;

    @Column(name = "Condiciones_Muestra2", length = 250, nullable = false)
    private String condicionesMuestra2;

    @Column(name = "Cumple_Cantidad", length = 250, nullable = false)
    private String cumpleCantidad;

    @Column(name = "Sino_Especifique_Cantidad", length = 250)
    private String sinoEspecifiqueCantidad;

    @Column(name = "Cantidad_Muestra_Analisis", length = 250, nullable = false)
    private String cantidadMuestraAnalisis;

    @Column(name = "Cantidad_Muestra_Retencion", length = 250, nullable = false)
    private String cantidadMuestraRetencion;

    @Column(name = "Nombre_Persona_Acondicionara", length = 250, nullable = false)
    private String nombrePersonaAcondicionara;

    @Column(name = "Ubicacion_Muestra_Retencion", length = 250, nullable = false)
    private String ubicacionMuestraRetencion;

    public RecepcionVerificacionRegistroCodificacion() {
    }

    public RecepcionVerificacionRegistroCodificacion(Long recepcionVerificacionRegistroCodificacionId, String cuentaConEtiqueta, String utilizoFeim, String cantidadMuestraEntregada, SolicitudServicioClienteMuestras solicitudServicioClienteMuestras, String fechaRecepcion, String folioRecepcionVerificacion, String nombrePersonaRecibe, String nombrePersonaEntrega, String medioRecepcion, String idInternoMuestra1, String idInternoMuestra2, String condicionesMuestra1, String condicionesMuestra2, String cumpleCantidad, String sinoEspecifiqueCantidad, String cantidadMuestraAnalisis, String cantidadMuestraRetencion, String nombrePersonaAcondicionara, String ubicacionMuestraRetencion) {
        this.recepcionVerificacionRegistroCodificacionId = recepcionVerificacionRegistroCodificacionId;
        this.cuentaConEtiqueta = cuentaConEtiqueta;
        this.utilizoFeim = utilizoFeim;
        this.cantidadMuestraEntregada = cantidadMuestraEntregada;
        this.solicitudServicioClienteMuestras = solicitudServicioClienteMuestras;
        this.fechaRecepcion = fechaRecepcion;
        this.folioRecepcionVerificacion = folioRecepcionVerificacion;
        this.nombrePersonaRecibe = nombrePersonaRecibe;
        this.nombrePersonaEntrega = nombrePersonaEntrega;
        this.medioRecepcion = medioRecepcion;
        this.idInternoMuestra1 = idInternoMuestra1;
        this.idInternoMuestra2 = idInternoMuestra2;
        this.condicionesMuestra1 = condicionesMuestra1;
        this.condicionesMuestra2 = condicionesMuestra2;
        this.cumpleCantidad = cumpleCantidad;
        this.sinoEspecifiqueCantidad = sinoEspecifiqueCantidad;
        this.cantidadMuestraAnalisis = cantidadMuestraAnalisis;
        this.cantidadMuestraRetencion = cantidadMuestraRetencion;
        this.nombrePersonaAcondicionara = nombrePersonaAcondicionara;
        this.ubicacionMuestraRetencion = ubicacionMuestraRetencion;
    }

    public Long getRecepcionVerificacionRegistroCodificacionId() {
        return recepcionVerificacionRegistroCodificacionId;
    }

    public void setRecepcionVerificacionRegistroCodificacionId(Long recepcionVerificacionRegistroCodificacionId) {
        this.recepcionVerificacionRegistroCodificacionId = recepcionVerificacionRegistroCodificacionId;
    }

    public String getCuentaConEtiqueta() {
        return cuentaConEtiqueta;
    }

    public void setCuentaConEtiqueta(String cuentaConEtiqueta) {
        this.cuentaConEtiqueta = cuentaConEtiqueta;
    }

    public String getUtilizoFeim() {
        return utilizoFeim;
    }

    public void setUtilizoFeim(String utilizoFeim) {
        this.utilizoFeim = utilizoFeim;
    }

    public String getCantidadMuestraEntregada() {
        return cantidadMuestraEntregada;
    }

    public void setCantidadMuestraEntregada(String cantidadMuestraEntregada) {
        this.cantidadMuestraEntregada = cantidadMuestraEntregada;
    }

    public SolicitudServicioClienteMuestras getSolicitudServicioClienteMuestras() {
        return solicitudServicioClienteMuestras;
    }

    public void setSolicitudServicioClienteMuestras(SolicitudServicioClienteMuestras solicitudServicioClienteMuestras) {
        this.solicitudServicioClienteMuestras = solicitudServicioClienteMuestras;
    }

    public String getFechaRecepcion() {
        return fechaRecepcion;
    }

    public void setFechaRecepcion(String fechaRecepcion) {
        this.fechaRecepcion = fechaRecepcion;
    }

    public String getFolioRecepcionVerificacion() {
        return folioRecepcionVerificacion;
    }

    public void setFolioRecepcionVerificacion(String folioRecepcionVerificacion) {
        this.folioRecepcionVerificacion = folioRecepcionVerificacion;
    }

    public String getNombrePersonaRecibe() {
        return nombrePersonaRecibe;
    }

    public void setNombrePersonaRecibe(String nombrePersonaRecibe) {
        this.nombrePersonaRecibe = nombrePersonaRecibe;
    }

    public String getNombrePersonaEntrega() {
        return nombrePersonaEntrega;
    }

    public void setNombrePersonaEntrega(String nombrePersonaEntrega) {
        this.nombrePersonaEntrega = nombrePersonaEntrega;
    }

    public String getMedioRecepcion() {
        return medioRecepcion;
    }

    public void setMedioRecepcion(String medioRecepcion) {
        this.medioRecepcion = medioRecepcion;
    }

    public String getIdInternoMuestra1() {
        return idInternoMuestra1;
    }

    public void setIdInternoMuestra1(String idInternoMuestra1) {
        this.idInternoMuestra1 = idInternoMuestra1;
    }

    public String getIdInternoMuestra2() {
        return idInternoMuestra2;
    }

    public void setIdInternoMuestra2(String idInternoMuestra2) {
        this.idInternoMuestra2 = idInternoMuestra2;
    }

    public String getCondicionesMuestra1() {
        return condicionesMuestra1;
    }

    public void setCondicionesMuestra1(String condicionesMuestra1) {
        this.condicionesMuestra1 = condicionesMuestra1;
    }

    public String getCondicionesMuestra2() {
        return condicionesMuestra2;
    }

    public void setCondicionesMuestra2(String condicionesMuestra2) {
        this.condicionesMuestra2 = condicionesMuestra2;
    }

    public String getCumpleCantidad() {
        return cumpleCantidad;
    }

    public void setCumpleCantidad(String cumpleCantidad) {
        this.cumpleCantidad = cumpleCantidad;
    }

    public String getSinoEspecifiqueCantidad() {
        return sinoEspecifiqueCantidad;
    }

    public void setSinoEspecifiqueCantidad(String sinoEspecifiqueCantidad) {
        this.sinoEspecifiqueCantidad = sinoEspecifiqueCantidad;
    }

    public String getCantidadMuestraAnalisis() {
        return cantidadMuestraAnalisis;
    }

    public void setCantidadMuestraAnalisis(String cantidadMuestraAnalisis) {
        this.cantidadMuestraAnalisis = cantidadMuestraAnalisis;
    }

    public String getCantidadMuestraRetencion() {
        return cantidadMuestraRetencion;
    }

    public void setCantidadMuestraRetencion(String cantidadMuestraRetencion) {
        this.cantidadMuestraRetencion = cantidadMuestraRetencion;
    }

    public String getNombrePersonaAcondicionara() {
        return nombrePersonaAcondicionara;
    }

    public void setNombrePersonaAcondicionara(String nombrePersonaAcondicionara) {
        this.nombrePersonaAcondicionara = nombrePersonaAcondicionara;
    }

    public String getUbicacionMuestraRetencion() {
        return ubicacionMuestraRetencion;
    }

    public void setUbicacionMuestraRetencion(String ubicacionMuestraRetencion) {
        this.ubicacionMuestraRetencion = ubicacionMuestraRetencion;
    }
}
