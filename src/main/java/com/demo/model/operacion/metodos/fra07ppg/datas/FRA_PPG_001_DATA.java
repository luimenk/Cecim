package com.demo.model.operacion.metodos.fra07ppg.datas;

import com.demo.model.operacion.metodos.fra07ppg.FRA_PPG_001;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
public class FRA_PPG_001_DATA {

    @Id
    @GeneratedValue
    @Column(nullable = false)
    private Long idFRAPPGDATA;

    private String peso;
    private String numeroPellets;
    private String pg;

    @CreationTimestamp
    private Timestamp createdAt;

    @UpdateTimestamp
    private Timestamp updatedAt;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "idfrappg")
    private FRA_PPG_001 fra_ppg_001;

    public FRA_PPG_001_DATA() {
    }

    public FRA_PPG_001_DATA(Long idFRAPPGDATA, String peso, String numeroPellets, String pg, Timestamp createdAt, Timestamp updatedAt, FRA_PPG_001 fra_ppg_001) {
        this.idFRAPPGDATA = idFRAPPGDATA;
        this.peso = peso;
        this.numeroPellets = numeroPellets;
        this.pg = pg;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.fra_ppg_001 = fra_ppg_001;
    }

    public Long getIdFRAPPGDATA() {
        return idFRAPPGDATA;
    }

    public void setIdFRAPPGDATA(Long idFRAPPGDATA) {
        this.idFRAPPGDATA = idFRAPPGDATA;
    }

    public String getPeso() {
        return peso;
    }

    public void setPeso(String peso) {
        this.peso = peso;
    }

    public String getNumeroPellets() {
        return numeroPellets;
    }

    public void setNumeroPellets(String numeroPellets) {
        this.numeroPellets = numeroPellets;
    }

    public String getPg() {
        return pg;
    }

    public void setPg(String pg) {
        this.pg = pg;
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

    public FRA_PPG_001 getFra_ppg_001() {
        return fra_ppg_001;
    }

    public void setFra_ppg_001(FRA_PPG_001 fra_ppg_001) {
        this.fra_ppg_001 = fra_ppg_001;
    }
}
