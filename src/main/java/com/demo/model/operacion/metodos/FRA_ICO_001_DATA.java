package com.demo.model.operacion.metodos;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
public class FRA_ICO_001_DATA {

    @Id
    @GeneratedValue
    @Column(nullable = false)
    private Long idFRAICODATA;

    @Column(length = 60, nullable = false)
    private String tiempoExposicion;

    @Column(length = 60, nullable = false)
    private String ico;

    @Column(length = 250, nullable = false)
    private String pathImage;

    @CreationTimestamp
    private Timestamp createdAt;

//    @UpdateTimestamp
//    private Timestamp updatedAt;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "idfraico")
    private FRA_ICO_001 fra_ico_001;

    public FRA_ICO_001_DATA() {
    }

    public FRA_ICO_001_DATA(Long idFRAICODATA, String tiempoExposicion, String ico, String pathImage, Timestamp createdAt, FRA_ICO_001 fra_ico_001) {
        this.idFRAICODATA = idFRAICODATA;
        this.tiempoExposicion = tiempoExposicion;
        this.ico = ico;
        this.pathImage = pathImage;
        this.createdAt = createdAt;
        this.fra_ico_001 = fra_ico_001;
    }

    public Long getIdFRAICODATA() {
        return idFRAICODATA;
    }

    public void setIdFRAICODATA(Long idFRAICODATA) {
        this.idFRAICODATA = idFRAICODATA;
    }

    public String getTiempoExposicion() {
        return tiempoExposicion;
    }

    public void setTiempoExposicion(String tiempoExposicion) {
        this.tiempoExposicion = tiempoExposicion;
    }

    public String getIco() {
        return ico;
    }

    public void setIco(String ico) {
        this.ico = ico;
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

    public FRA_ICO_001 getFra_ico_001() {
        return fra_ico_001;
    }

    public void setFra_ico_001(FRA_ICO_001 fra_ico_001) {
        this.fra_ico_001 = fra_ico_001;
    }
}
