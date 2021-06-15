package com.demo.model.operacion.metodos.fra17if.datas;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
public class FRA_IF_001_DATA {

    @Id
    @GeneratedValue
    @Column(nullable = false)
    private Long idFRAIFDATA;

    private String pesoFilamento;
    private String indiceFluidez;
    private String mfi;

    @CreationTimestamp
    private Timestamp createdAt;

    @UpdateTimestamp
    private Timestamp updatedAt;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "idfraif")
    private FRA_IF_001_DATA fra_if_001_data;

    public FRA_IF_001_DATA() {
    }

    public FRA_IF_001_DATA(Long idFRAIFDATA, String pesoFilamento, String indiceFluidez, String mfi, Timestamp createdAt, Timestamp updatedAt, FRA_IF_001_DATA fra_if_001_data) {
        this.idFRAIFDATA = idFRAIFDATA;
        this.pesoFilamento = pesoFilamento;
        this.indiceFluidez = indiceFluidez;
        this.mfi = mfi;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.fra_if_001_data = fra_if_001_data;
    }

    public Long getIdFRAIFDATA() {
        return idFRAIFDATA;
    }

    public void setIdFRAIFDATA(Long idFRAIFDATA) {
        this.idFRAIFDATA = idFRAIFDATA;
    }

    public String getPesoFilamento() {
        return pesoFilamento;
    }

    public void setPesoFilamento(String pesoFilamento) {
        this.pesoFilamento = pesoFilamento;
    }

    public String getIndiceFluidez() {
        return indiceFluidez;
    }

    public void setIndiceFluidez(String indiceFluidez) {
        this.indiceFluidez = indiceFluidez;
    }

    public String getMfi() {
        return mfi;
    }

    public void setMfi(String mfi) {
        this.mfi = mfi;
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

    public FRA_IF_001_DATA getFra_if_001_data() {
        return fra_if_001_data;
    }

    public void setFra_if_001_data(FRA_IF_001_DATA fra_if_001_data) {
        this.fra_if_001_data = fra_if_001_data;
    }
}
