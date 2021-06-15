package com.demo.model.operacion.metodos.fra12eauv.datas;

import com.demo.model.operacion.metodos.fra12eauv.FRA_EAUV_001;
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

    private String tiempoExposicion;
    private String rubrica;
    private String nombreAnalista;
    private String pathImage;

    @CreationTimestamp
    private Timestamp createdAt;

    @UpdateTimestamp
    private Timestamp updatedAt;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "idfraeauv")
    private FRA_EAUV_001 fra_eauv_001;

    public FRA_EAUV_001_DATA() {
    }

    public FRA_EAUV_001_DATA(Long idFRAEAUVDATA, String tiempoExposicion, String rubrica, String nombreAnalista, String pathImage, Timestamp createdAt, Timestamp updatedAt, FRA_EAUV_001 fra_eauv_001) {
        this.idFRAEAUVDATA = idFRAEAUVDATA;
        this.tiempoExposicion = tiempoExposicion;
        this.rubrica = rubrica;
        this.nombreAnalista = nombreAnalista;
        this.pathImage = pathImage;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
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

    public String getRubrica() {
        return rubrica;
    }

    public void setRubrica(String rubrica) {
        this.rubrica = rubrica;
    }

    public String getNombreAnalista() {
        return nombreAnalista;
    }

    public void setNombreAnalista(String nombreAnalista) {
        this.nombreAnalista = nombreAnalista;
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

    public Timestamp getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Timestamp updatedAt) {
        this.updatedAt = updatedAt;
    }

    public FRA_EAUV_001 getFra_eauv_001() {
        return fra_eauv_001;
    }

    public void setFra_eauv_001(FRA_EAUV_001 fra_eauv_001) {
        this.fra_eauv_001 = fra_eauv_001;
    }
}
