package com.demo.model.operacion.metodos.fra09tga.datas;

import com.demo.model.operacion.metodos.fra09tga.FRA_TGA_001;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
public class FRA_TGA_001_DATA {

    @Id
    @GeneratedValue
    @Column(nullable = false)
    private Long idFRATGADATA;

    private String avTemperatura;
    private String avPeso;
    private String mvTemperatura;
    private String mvPeso;
    private String combustibleTemperatura;
    private String combustiblePeso;
    private String cenizasTemperatura;
    private String cenizasPeso;

    @CreationTimestamp
    private Timestamp createdAt;

    @UpdateTimestamp
    private Timestamp updatedAt;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "idfratga")
    private FRA_TGA_001 fra_tga_001;

    public FRA_TGA_001_DATA() {
    }

    public FRA_TGA_001_DATA(Long idFRATGADATA, String avTemperatura, String avPeso, String mvTemperatura, String mvPeso, String combustibleTemperatura, String combustiblePeso, String cenizasTemperatura, String cenizasPeso, Timestamp createdAt, Timestamp updatedAt, FRA_TGA_001 fra_tga_001) {
        this.idFRATGADATA = idFRATGADATA;
        this.avTemperatura = avTemperatura;
        this.avPeso = avPeso;
        this.mvTemperatura = mvTemperatura;
        this.mvPeso = mvPeso;
        this.combustibleTemperatura = combustibleTemperatura;
        this.combustiblePeso = combustiblePeso;
        this.cenizasTemperatura = cenizasTemperatura;
        this.cenizasPeso = cenizasPeso;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.fra_tga_001 = fra_tga_001;
    }

    public Long getIdFRATGADATA() {
        return idFRATGADATA;
    }

    public void setIdFRATGADATA(Long idFRATGADATA) {
        this.idFRATGADATA = idFRATGADATA;
    }

    public String getAvTemperatura() {
        return avTemperatura;
    }

    public void setAvTemperatura(String avTemperatura) {
        this.avTemperatura = avTemperatura;
    }

    public String getAvPeso() {
        return avPeso;
    }

    public void setAvPeso(String avPeso) {
        this.avPeso = avPeso;
    }

    public String getMvTemperatura() {
        return mvTemperatura;
    }

    public void setMvTemperatura(String mvTemperatura) {
        this.mvTemperatura = mvTemperatura;
    }

    public String getMvPeso() {
        return mvPeso;
    }

    public void setMvPeso(String mvPeso) {
        this.mvPeso = mvPeso;
    }

    public String getCombustibleTemperatura() {
        return combustibleTemperatura;
    }

    public void setCombustibleTemperatura(String combustibleTemperatura) {
        this.combustibleTemperatura = combustibleTemperatura;
    }

    public String getCombustiblePeso() {
        return combustiblePeso;
    }

    public void setCombustiblePeso(String combustiblePeso) {
        this.combustiblePeso = combustiblePeso;
    }

    public String getCenizasTemperatura() {
        return cenizasTemperatura;
    }

    public void setCenizasTemperatura(String cenizasTemperatura) {
        this.cenizasTemperatura = cenizasTemperatura;
    }

    public String getCenizasPeso() {
        return cenizasPeso;
    }

    public void setCenizasPeso(String cenizasPeso) {
        this.cenizasPeso = cenizasPeso;
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

    public FRA_TGA_001 getFra_tga_001() {
        return fra_tga_001;
    }

    public void setFra_tga_001(FRA_TGA_001 fra_tga_001) {
        this.fra_tga_001 = fra_tga_001;
    }
}
