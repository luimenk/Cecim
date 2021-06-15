package com.demo.model.operacion.metodos.fra08ftir.datas;

import com.demo.model.operacion.metodos.fra08ftir.FRA_FTIR_001;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
public class FRA_FTIR_001_DATA {

    @Id
    @GeneratedValue
    @Column(nullable = false)
    private Long idFRAFTIRDATA;

    private String tipoCompuesto;
    private String identidad;

    @CreationTimestamp
    private Timestamp createdAt;

    @UpdateTimestamp
    private Timestamp updatedAt;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "idfraftir")
    private FRA_FTIR_001 fra_ftir_001;

    public FRA_FTIR_001_DATA() {
    }

    public FRA_FTIR_001_DATA(Long idFRAFTIRDATA, String tipoCompuesto, String identidad, Timestamp createdAt, Timestamp updatedAt, FRA_FTIR_001 fra_ftir_001) {
        this.idFRAFTIRDATA = idFRAFTIRDATA;
        this.tipoCompuesto = tipoCompuesto;
        this.identidad = identidad;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.fra_ftir_001 = fra_ftir_001;
    }

    public Long getIdFRAFTIRDATA() {
        return idFRAFTIRDATA;
    }

    public void setIdFRAFTIRDATA(Long idFRAFTIRDATA) {
        this.idFRAFTIRDATA = idFRAFTIRDATA;
    }

    public String getTipoCompuesto() {
        return tipoCompuesto;
    }

    public void setTipoCompuesto(String tipoCompuesto) {
        this.tipoCompuesto = tipoCompuesto;
    }

    public String getIdentidad() {
        return identidad;
    }

    public void setIdentidad(String identidad) {
        this.identidad = identidad;
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

    public FRA_FTIR_001 getFra_ftir_001() {
        return fra_ftir_001;
    }

    public void setFra_ftir_001(FRA_FTIR_001 fra_ftir_001) {
        this.fra_ftir_001 = fra_ftir_001;
    }
}
