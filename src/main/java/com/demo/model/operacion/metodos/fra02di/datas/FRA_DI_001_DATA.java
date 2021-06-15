package com.demo.model.operacion.metodos.fra02di.datas;

import com.demo.model.operacion.metodos.fra02di.FRA_DI_001;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
public class FRA_DI_001_DATA {

    @Id
    @GeneratedValue
    @Column(nullable = false)
    private Long idFRADIDATA;

    private String largo;
    private String ancho;
    private String fuelleDerecho;
    private String fuelleIzquierdo;

    @CreationTimestamp
    private Timestamp createdAt;

    @UpdateTimestamp
    private Timestamp updatedAt;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "idfradi")
    private FRA_DI_001 fra_di_001;

    public FRA_DI_001_DATA() {
    }

    public FRA_DI_001_DATA(Long idFRADIDATA, String largo, String ancho, String fuelleDerecho, String fuelleIzquierdo, Timestamp createdAt, Timestamp updatedAt, FRA_DI_001 fra_di_001) {
        this.idFRADIDATA = idFRADIDATA;
        this.largo = largo;
        this.ancho = ancho;
        this.fuelleDerecho = fuelleDerecho;
        this.fuelleIzquierdo = fuelleIzquierdo;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.fra_di_001 = fra_di_001;
    }

    public Long getIdFRADIDATA() {
        return idFRADIDATA;
    }

    public void setIdFRADIDATA(Long idFRADIDATA) {
        this.idFRADIDATA = idFRADIDATA;
    }

    public String getLargo() {
        return largo;
    }

    public void setLargo(String largo) {
        this.largo = largo;
    }

    public String getAncho() {
        return ancho;
    }

    public void setAncho(String ancho) {
        this.ancho = ancho;
    }

    public String getFuelleDerecho() {
        return fuelleDerecho;
    }

    public void setFuelleDerecho(String fuelleDerecho) {
        this.fuelleDerecho = fuelleDerecho;
    }

    public String getFuelleIzquierdo() {
        return fuelleIzquierdo;
    }

    public void setFuelleIzquierdo(String fuelleIzquierdo) {
        this.fuelleIzquierdo = fuelleIzquierdo;
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

    public FRA_DI_001 getFra_di_001() {
        return fra_di_001;
    }

    public void setFra_di_001(FRA_DI_001 fra_di_001) {
        this.fra_di_001 = fra_di_001;
    }
}
