package com.demo.model.operacion.metodos.fra18tto.datas;

import com.demo.model.operacion.metodos.fra18tto.FRA_TTO_001;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
public class FRA_TTO_001_DATA {

    @Id
    @GeneratedValue
    @Column(nullable = false)
    private Long idFRATTODATA;

    private String e1;
    private String e2;
    private String e3;
    private String e4;
    private String e5;
    private String promedio;

    private String cc;
    private String mol;

    @CreationTimestamp
    private Timestamp createdAt;

    @UpdateTimestamp
    private Timestamp updatedAt;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "idfratto")
    private FRA_TTO_001 fra_tto_001;

    public FRA_TTO_001_DATA() {
    }

    public FRA_TTO_001_DATA(Long idFRATTODATA, String e1, String e2, String e3, String e4, String e5, String promedio, String cc, String mol, Timestamp createdAt, Timestamp updatedAt, FRA_TTO_001 fra_tto_001) {
        this.idFRATTODATA = idFRATTODATA;
        this.e1 = e1;
        this.e2 = e2;
        this.e3 = e3;
        this.e4 = e4;
        this.e5 = e5;
        this.promedio = promedio;
        this.cc = cc;
        this.mol = mol;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.fra_tto_001 = fra_tto_001;
    }

    public Long getIdFRATTODATA() {
        return idFRATTODATA;
    }

    public void setIdFRATTODATA(Long idFRATTODATA) {
        this.idFRATTODATA = idFRATTODATA;
    }

    public String getE1() {
        return e1;
    }

    public void setE1(String e1) {
        this.e1 = e1;
    }

    public String getE2() {
        return e2;
    }

    public void setE2(String e2) {
        this.e2 = e2;
    }

    public String getE3() {
        return e3;
    }

    public void setE3(String e3) {
        this.e3 = e3;
    }

    public String getE4() {
        return e4;
    }

    public void setE4(String e4) {
        this.e4 = e4;
    }

    public String getE5() {
        return e5;
    }

    public void setE5(String e5) {
        this.e5 = e5;
    }

    public String getPromedio() {
        return promedio;
    }

    public void setPromedio(String promedio) {
        this.promedio = promedio;
    }

    public String getCc() {
        return cc;
    }

    public void setCc(String cc) {
        this.cc = cc;
    }

    public String getMol() {
        return mol;
    }

    public void setMol(String mol) {
        this.mol = mol;
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

    public FRA_TTO_001 getFra_tto_001() {
        return fra_tto_001;
    }

    public void setFra_tto_001(FRA_TTO_001 fra_tto_001) {
        this.fra_tto_001 = fra_tto_001;
    }
}
