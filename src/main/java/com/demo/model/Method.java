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

    @Column(name = "Cantidad_Muestra_Ensayo", length = 250, nullable = false)
    private String cantidadMuestraEnsayo;

    @Column(name = "Cantidad_Muestra_Retencion", length = 250, nullable = false)
    private String cantidadMuestraRetencion;

    @Column(name = "Dimensiones_Corte_Probeta", length = 250, nullable = false)
    private String dimensionesCorteProbeta;

    @Column(name = "Numero_Probetas_Muestras", length = 250, nullable = false)
    private String numeroProbetasMuestras;

    @Column(name = "Condiciones_Especiales_Acondicionamiento", length = 250, nullable = false)
    private String condicionesEspecialesAcondicionamiento;

    public Method() {
    }

    public Method(String codigoMetodo, String cantidadMuestraEnsayo, String cantidadMuestraRetencion, String dimensionesCorteProbeta, String numeroProbetasMuestras, String condicionesEspecialesAcondicionamiento) {
        this.codigoMetodo = codigoMetodo;
        this.cantidadMuestraEnsayo = cantidadMuestraEnsayo;
        this.cantidadMuestraRetencion = cantidadMuestraRetencion;
        this.dimensionesCorteProbeta = dimensionesCorteProbeta;
        this.numeroProbetasMuestras = numeroProbetasMuestras;
        this.condicionesEspecialesAcondicionamiento = condicionesEspecialesAcondicionamiento;
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
}
