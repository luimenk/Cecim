package com.demo.model.operacion.metodos.fra13eaxe.datas;

import com.demo.model.operacion.metodos.fra13eaxe.FRA_EAXE_013;
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

    private String tiempoExposicion;
    private String rubrica;
    private String nombreAnalista;
    private String pathImage;

    @CreationTimestamp
    private Timestamp createdAt;

    @UpdateTimestamp
    private Timestamp updatedAt;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "idfraeaxe")
    private FRA_EAXE_013 fra_eaxe_013;

    public FRA_EAXE_013_DATA() {
    }

    public FRA_EAXE_013_DATA(Long idFRAEAXEDATA, String tiempoExposicion, String rubrica, String nombreAnalista, String pathImage, Timestamp createdAt, Timestamp updatedAt, FRA_EAXE_013 fra_eaxe_013) {
        this.idFRAEAXEDATA = idFRAEAXEDATA;
        this.tiempoExposicion = tiempoExposicion;
        this.rubrica = rubrica;
        this.nombreAnalista = nombreAnalista;
        this.pathImage = pathImage;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
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

    public FRA_EAXE_013 getFra_eaxe_013() {
        return fra_eaxe_013;
    }

    public void setFra_eaxe_013(FRA_EAXE_013 fra_eaxe_013) {
        this.fra_eaxe_013 = fra_eaxe_013;
    }
}
