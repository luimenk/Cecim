package com.demo.model.operacion.metodos;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
public class FRA_EAUV_001_DATA {

    @Id
    @GeneratedValue
    @Column(nullable = false)
    private Long idFRAEAUVDATA;

    @Column(length = 60, nullable = false)
    private String tiempoExposicion;

    @Column(length = 250, nullable = false)
    private String pathImage;

    @CreationTimestamp
    private Timestamp createdAt;

//    @UpdateTimestamp
//    private Timestamp updatedAt;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "idfraeauv")
    private FRA_EAUV_001 fra_eauv_001;

    public FRA_EAUV_001_DATA() {
    }

    public FRA_EAUV_001_DATA(Long idFRAEAUVDATA, String tiempoExposicion, String pathImage, Timestamp createdAt, FRA_EAUV_001 fra_eauv_001) {
        this.idFRAEAUVDATA = idFRAEAUVDATA;
        this.tiempoExposicion = tiempoExposicion;
        this.pathImage = pathImage;
        this.createdAt = createdAt;
        this.fra_eauv_001 = fra_eauv_001;
    }

    public Long getIdFRAEAUVDATA() {
        return idFRAEAUVDATA;
    }

    public void setIdFRAEAUVDATA(Long idFRAEAUVDATA) {
        this.idFRAEAUVDATA = idFRAEAUVDATA;
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

    public FRA_EAUV_001 getFra_eauv_001() {
        return fra_eauv_001;
    }

    public void setFra_eauv_001(FRA_EAUV_001 fra_eauv_001) {
        this.fra_eauv_001 = fra_eauv_001;
    }
}
