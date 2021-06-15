package com.demo.model.operacion.metodos.fra15dsc.datas;

import com.demo.model.operacion.metodos.fra15dsc.FRA_DSC;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
public class FRA_DSC_001_DATA {

    @Id
    @GeneratedValue
    @Column(nullable = false)
    private Long idFRADSCDATA;

    private String etapa;
    private String temperaturaTransicion;
    private String temperaturaFusion;
    private String calorFusion;
    private String transicionTermica;
    private String temperaturaCristalizacion;
    private String calorCristalizacion;
    private String transmisionTermica;

    @CreationTimestamp
    private Timestamp createdAt;

    @UpdateTimestamp
    private Timestamp updatedAt;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "idfradsc")
    private FRA_DSC fra_dsc;

    public FRA_DSC_001_DATA() {
    }

    public FRA_DSC_001_DATA(Long idFRADSCDATA, String etapa, String temperaturaTransicion, String temperaturaFusion, String calorFusion, String transicionTermica, String temperaturaCristalizacion, String calorCristalizacion, String transmisionTermica, Timestamp createdAt, Timestamp updatedAt, FRA_DSC fra_dsc) {
        this.idFRADSCDATA = idFRADSCDATA;
        this.etapa = etapa;
        this.temperaturaTransicion = temperaturaTransicion;
        this.temperaturaFusion = temperaturaFusion;
        this.calorFusion = calorFusion;
        this.transicionTermica = transicionTermica;
        this.temperaturaCristalizacion = temperaturaCristalizacion;
        this.calorCristalizacion = calorCristalizacion;
        this.transmisionTermica = transmisionTermica;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.fra_dsc = fra_dsc;
    }

    public Long getIdFRADSCDATA() {
        return idFRADSCDATA;
    }

    public void setIdFRADSCDATA(Long idFRADSCDATA) {
        this.idFRADSCDATA = idFRADSCDATA;
    }

    public String getEtapa() {
        return etapa;
    }

    public void setEtapa(String etapa) {
        this.etapa = etapa;
    }

    public String getTemperaturaTransicion() {
        return temperaturaTransicion;
    }

    public void setTemperaturaTransicion(String temperaturaTransicion) {
        this.temperaturaTransicion = temperaturaTransicion;
    }

    public String getTemperaturaFusion() {
        return temperaturaFusion;
    }

    public void setTemperaturaFusion(String temperaturaFusion) {
        this.temperaturaFusion = temperaturaFusion;
    }

    public String getCalorFusion() {
        return calorFusion;
    }

    public void setCalorFusion(String calorFusion) {
        this.calorFusion = calorFusion;
    }

    public String getTransicionTermica() {
        return transicionTermica;
    }

    public void setTransicionTermica(String transicionTermica) {
        this.transicionTermica = transicionTermica;
    }

    public String getTemperaturaCristalizacion() {
        return temperaturaCristalizacion;
    }

    public void setTemperaturaCristalizacion(String temperaturaCristalizacion) {
        this.temperaturaCristalizacion = temperaturaCristalizacion;
    }

    public String getCalorCristalizacion() {
        return calorCristalizacion;
    }

    public void setCalorCristalizacion(String calorCristalizacion) {
        this.calorCristalizacion = calorCristalizacion;
    }

    public String getTransmisionTermica() {
        return transmisionTermica;
    }

    public void setTransmisionTermica(String transmisionTermica) {
        this.transmisionTermica = transmisionTermica;
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

    public FRA_DSC getFra_dsc() {
        return fra_dsc;
    }

    public void setFra_dsc(FRA_DSC fra_dsc) {
        this.fra_dsc = fra_dsc;
    }
}
