package com.demo.model;

import javax.persistence.*;

@Entity
public class InformacionMuestrasOSC {

    @Id
    @GeneratedValue
    @Column(name = "Informacion_Muestras_OSC_Id", nullable = false)
    private Long informacionMuestrasOSCId;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "Method_Id")
    private Method method;

    @Column(name = "numero_ensayos", nullable = false)
    private String numeroEnsayos;

    @Column(name = "descripcion_muestra", nullable = false)
    private String descripcionMuestra;

    @Column(name = "observaciones_condiciones", nullable = false)
    private String observacionesCondiciones;

    public InformacionMuestrasOSC() {
    }

    public InformacionMuestrasOSC(Long informacionMuestrasOSCId, Method method, String numeroEnsayos, String descripcionMuestra, String observacionesCondiciones) {
        this.informacionMuestrasOSCId = informacionMuestrasOSCId;
        this.method = method;
        this.numeroEnsayos = numeroEnsayos;
        this.descripcionMuestra = descripcionMuestra;
        this.observacionesCondiciones = observacionesCondiciones;
    }

    public Long getInformacionMuestrasOSCId() {
        return informacionMuestrasOSCId;
    }

    public void setInformacionMuestrasOSCId(Long informacionMuestrasOSCId) {
        this.informacionMuestrasOSCId = informacionMuestrasOSCId;
    }

    public Method getMethod() {
        return method;
    }

    public void setMethod(Method method) {
        this.method = method;
    }

    public String getNumeroEnsayos() {
        return numeroEnsayos;
    }

    public void setNumeroEnsayos(String numeroEnsayos) {
        this.numeroEnsayos = numeroEnsayos;
    }

    public String getDescripcionMuestra() {
        return descripcionMuestra;
    }

    public void setDescripcionMuestra(String descripcionMuestra) {
        this.descripcionMuestra = descripcionMuestra;
    }

    public String getObservacionesCondiciones() {
        return observacionesCondiciones;
    }

    public void setObservacionesCondiciones(String observacionesCondiciones) {
        this.observacionesCondiciones = observacionesCondiciones;
    }
}
