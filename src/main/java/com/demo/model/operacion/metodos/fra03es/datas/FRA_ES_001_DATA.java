package com.demo.model.operacion.metodos.fra03es.datas;

import com.demo.model.operacion.metodos.fra03es.FRA_ES_001;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
public class FRA_ES_001_DATA {

    @Id
    @GeneratedValue
    @Column(nullable = false)
    private Long idFRAESDATA;

    private String largo;
    private String ancho;

    @CreationTimestamp
    private Timestamp createdAt;

    @UpdateTimestamp
    private Timestamp updatedAt;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "idfraes")
    private FRA_ES_001 fra_es_001;

    public FRA_ES_001_DATA() {
    }

    public FRA_ES_001_DATA(Long idFRAESDATA, String largo, String ancho, Timestamp createdAt, Timestamp updatedAt, FRA_ES_001 fra_es_001) {
        this.idFRAESDATA = idFRAESDATA;
        this.largo = largo;
        this.ancho = ancho;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.fra_es_001 = fra_es_001;
    }

    public Long getIdFRAESDATA() {
        return idFRAESDATA;
    }

    public void setIdFRAESDATA(Long idFRAESDATA) {
        this.idFRAESDATA = idFRAESDATA;
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

    public FRA_ES_001 getFra_es_001() {
        return fra_es_001;
    }

    public void setFra_es_001(FRA_ES_001 fra_es_001) {
        this.fra_es_001 = fra_es_001;
    }
}
