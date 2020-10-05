package com.demo.model.operacion.metodos;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
public class FRA_EAT_001_DATA {

    @Id
    @GeneratedValue
    @Column(nullable = false)
    private Long idFRAEATDATA;

    @Column(length = 60, nullable = false)
    private String tiempoExposicion;

    @Column(length = 250, nullable = false)
    private String pathImage;

    @CreationTimestamp
    private Timestamp createdAt;

//    @UpdateTimestamp
//    private Timestamp updatedAt;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "idfraeat")
    private FRA_EAT_001 fra_eat_001;

    public FRA_EAT_001_DATA() {
    }

    public FRA_EAT_001_DATA(Long idFRAEATDATA, String tiempoExposicion, String pathImage, Timestamp createdAt, FRA_EAT_001 fra_eat_001) {
        this.idFRAEATDATA = idFRAEATDATA;
        this.tiempoExposicion = tiempoExposicion;
        this.pathImage = pathImage;
        this.createdAt = createdAt;
        this.fra_eat_001 = fra_eat_001;
    }

    public Long getIdFRAEATDATA() {
        return idFRAEATDATA;
    }

    public void setIdFRAEATDATA(Long idFRAEATDATA) {
        this.idFRAEATDATA = idFRAEATDATA;
    }

    public String getTiempoExposicion() {
        return tiempoExposicion;
    }

    public void setTiempoExposicion(String tiempoExposicion) {
        this.tiempoExposicion = tiempoExposicion;
    }

    public String getPathImage() {
        return pathImage;
    }

    public void setPathImage(String pathImage) {
        this.pathImage = pathImage;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }

    public FRA_EAT_001 getFra_eat_001() {
        return fra_eat_001;
    }

    public void setFra_eat_001(FRA_EAT_001 fra_eat_001) {
        this.fra_eat_001 = fra_eat_001;
    }
}
