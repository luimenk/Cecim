package com.demo.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Method {

    @Id
    @GeneratedValue
    @Column(name = "Method_Id", nullable = false)
    private Long methodId;

    @Column(name = "Codigo_Metodo", length = 250, nullable = false)
    private String codigoMetodo;

    @Column(name = "Nombre_Metodo", length = 250, nullable = false)
    private String nombreMetodo;

    @Column(name = "Norma_Referencia", length = 250, nullable = false)
    private String normaReferencia;

    @Column(name = "Cantidad_Muestra_Ensayo", length = 250, nullable = false)
    private String cantidadMuestraEnsayo;

    @Column(name = "Cantidad_Muestra_Retencion", length = 250, nullable = false)
    private String cantidadMuestraRetencion;

    @Column(name = "Cantidad_Total", length = 250, nullable = false)
    private String cantidadTotal;

    @Column(name = "Dimensiones_Corte_Probeta", length = 250, nullable = false)
    private String dimensionesCorteProbeta;

    @Column(name = "Numero_Probetas_Muestras", length = 250, nullable = false)
    private String numeroProbetasMuestras;

    @Column(name = "Condiciones_Especiales_Acondicionamiento", length = 250, nullable = false)
    private String condicionesEspecialesAcondicionamiento;

    @Column(name = "Ruta", length = 250, nullable = false)
    private String ruta;

    public Method() {
    }

    public Method(Long methodId, String codigoMetodo, String nombreMetodo, String normaReferencia, String cantidadMuestraEnsayo, String cantidadMuestraRetencion, String cantidadTotal, String dimensionesCorteProbeta, String numeroProbetasMuestras, String condicionesEspecialesAcondicionamiento, String ruta) {
        this.methodId = methodId;
        this.codigoMetodo = codigoMetodo;
        this.nombreMetodo = nombreMetodo;
        this.normaReferencia = normaReferencia;
        this.cantidadMuestraEnsayo = cantidadMuestraEnsayo;
        this.cantidadMuestraRetencion = cantidadMuestraRetencion;
        this.cantidadTotal = cantidadTotal;
        this.dimensionesCorteProbeta = dimensionesCorteProbeta;
        this.numeroProbetasMuestras = numeroProbetasMuestras;
        this.condicionesEspecialesAcondicionamiento = condicionesEspecialesAcondicionamiento;
        this.ruta = ruta;
    }

    public Long getMethodId() {
        return methodId;
    }

    public void setMethodId(Long methodId) {
        this.methodId = methodId;
    }

    public String getCodigoMetodo() {
        return codigoMetodo;
    }

    public void setCodigoMetodo(String codigoMetodo) {
        this.codigoMetodo = codigoMetodo;
    }

    public String getNombreMetodo() {
        return nombreMetodo;
    }

    public void setNombreMetodo(String nombreMetodo) {
        this.nombreMetodo = nombreMetodo;
    }

    public String getNormaReferencia() {
        return normaReferencia;
    }

    public void setNormaReferencia(String normaReferencia) {
        this.normaReferencia = normaReferencia;
    }

    public String getCantidadMuestraEnsayo() {
        return cantidadMuestraEnsayo;
    }

    public void setCantidadMuestraEnsayo(String cantidadMuestraEnsayo) {
        this.cantidadMuestraEnsayo = cantidadMuestraEnsayo;
    }

    public String getCantidadMuestraRetencion() {
        return cantidadMuestraRetencion;
    }

    public void setCantidadMuestraRetencion(String cantidadMuestraRetencion) {
        this.cantidadMuestraRetencion = cantidadMuestraRetencion;
    }

    public String getCantidadTotal() {
        return cantidadTotal;
    }

    public void setCantidadTotal(String cantidadTotal) {
        this.cantidadTotal = cantidadTotal;
    }

    public String getDimensionesCorteProbeta() {
        return dimensionesCorteProbeta;
    }

    public void setDimensionesCorteProbeta(String dimensionesCorteProbeta) {
        this.dimensionesCorteProbeta = dimensionesCorteProbeta;
    }

    public String getNumeroProbetasMuestras() {
        return numeroProbetasMuestras;
    }

    public void setNumeroProbetasMuestras(String numeroProbetasMuestras) {
        this.numeroProbetasMuestras = numeroProbetasMuestras;
    }

    public String getCondicionesEspecialesAcondicionamiento() {
        return condicionesEspecialesAcondicionamiento;
    }

    public void setCondicionesEspecialesAcondicionamiento(String condicionesEspecialesAcondicionamiento) {
        this.condicionesEspecialesAcondicionamiento = condicionesEspecialesAcondicionamiento;
    }

    public String getRuta() {
        return ruta;
    }

    public void setRuta(String ruta) {
        this.ruta = ruta;
    }
}