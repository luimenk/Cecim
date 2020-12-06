package com.demo.model.operacion.metodos;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
public class FRA_EAXE_013_DATA {

    @Id
    @GeneratedValue
    @Column(nullable = false)
    private Long idFRAEAXEDATA;

    @Column(length = 60, nullable = false)
    private String tiempoExposicion;

    @Column(length = 250, nullable = false)
    private String pathImage;

    @CreationTimestamp
    private Timestamp createdAt;

//    @UpdateTimestamp
//    private Timestamp updatedAt;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "idfraeaxe")
    private FRA_EAXE_013 fra_eaxe_013;

    public FRA_EAXE_013_DATA() {
    }

    public FRA_EAXE_013_DATA(Long idFRAEAXEDATA, String tiempoExposicion, String pathImage, Timestamp createdAt, FRA_EAXE_013 fra_eaxe_013) {
        this.idFRAEAXEDATA = idFRAEAXEDATA;
        this.tiempoExposicion = tiempoExposicion;
        this.pathImage = pathImage;
        this.createdAt = createdAt;
        this.fra_eaxe_013 = fra_eaxe_013;
    }

    public Long getIdFRAEAXEDATA() {
        return idFRAEAXEDATA;
    }

    public void setIdFRAEAXEDATA(Long idFRAEAXEDATA) {
        this.idFRAEAXEDATA = idFRAEAXEDATA;
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

    public FRA_EAXE_013 getFra_eaxe_013() {
        return fra_eaxe_013;
    }

    public void setFra_eaxe_013(FRA_EAXE_013 fra_eaxe_013) {
        this.fra_eaxe_013 = fra_eaxe_013;
    }
}
