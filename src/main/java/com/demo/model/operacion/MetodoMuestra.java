package com.demo.model.operacion;

import com.demo.model.Client;
import com.demo.model.Method;

import javax.persistence.*;

@Entity
public class MetodoMuestra {

    @Id
    @GeneratedValue
    @Column(nullable = false)
    private Long metodoMuestraId;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "method_id")
    private Method method;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "solicitud_servicio_cliente_muestras_id")
    private SolicitudServicioClienteMuestras solicitudServicioClienteMuestras;

    @Column(name = "Path_Qr_Lab", length = 250, nullable = false)
    private String pathQRLab;

    public MetodoMuestra() {
    }

    public MetodoMuestra(Long metodoMuestraId, Method method, SolicitudServicioClienteMuestras solicitudServicioClienteMuestras, String pathQRLab) {
        this.metodoMuestraId = metodoMuestraId;
        this.method = method;
        this.solicitudServicioClienteMuestras = solicitudServicioClienteMuestras;
        this.pathQRLab = pathQRLab;
    }

    public Long getMetodoMuestraId() {
        return metodoMuestraId;
    }

    public void setMetodoMuestraId(Long metodoMuestraId) {
        this.metodoMuestraId = metodoMuestraId;
    }

    public Method getMethod() {
        return method;
    }

    public void setMethod(Method method) {
        this.method = method;
    }

    public SolicitudServicioClienteMuestras getSolicitudServicioClienteMuestras() {
        return solicitudServicioClienteMuestras;
    }

    public void setSolicitudServicioClienteMuestras(SolicitudServicioClienteMuestras solicitudServicioClienteMuestras) {
        this.solicitudServicioClienteMuestras = solicitudServicioClienteMuestras;
    }

    public String getPathQRLab() {
        return pathQRLab;
    }

    public void setPathQRLab(String pathQRLab) {
        this.pathQRLab = pathQRLab;
    }
}
