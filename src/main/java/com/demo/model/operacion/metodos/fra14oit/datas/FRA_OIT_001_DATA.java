package com.demo.model.operacion.metodos.fra14oit.datas;

import com.demo.model.operacion.metodos.fra14oit.FRA_OIT_001;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
public class FRA_OIT_001_DATA {

    @Id
    @GeneratedValue
    @Column(nullable = false)
    private Long idFRAOITDATA;

    private String noDobleces;
    private String e1;
    private String e2;
    private String e3;
    private String espesorPromedio;

    @CreationTimestamp
    private Timestamp createdAt;

    @UpdateTimestamp
    private Timestamp updatedAt;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "idfraoit")
    private FRA_OIT_001 fra_oit_001;

    public FRA_OIT_001_DATA() {
    }

    public FRA_OIT_001_DATA(Long idFRAOITDATA, String noDobleces, String e1, String e2, String e3, String espesorPromedio, Timestamp createdAt, Timestamp updatedAt, FRA_OIT_001 fra_oit_001) {
        this.idFRAOITDATA = idFRAOITDATA;
        this.noDobleces = noDobleces;
        this.e1 = e1;
        this.e2 = e2;
        this.e3 = e3;
        this.espesorPromedio = espesorPromedio;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.fra_oit_001 = fra_oit_001;
    }

    public Long getIdFRAOITDATA() {
        return idFRAOITDATA;
    }

    public void setIdFRAOITDATA(Long idFRAOITDATA) {
        this.idFRAOITDATA = idFRAOITDATA;
    }

    public String getNoDobleces() {
        return noDobleces;
    }

    public void setNoDobleces(String noDobleces) {
        this.noDobleces = noDobleces;
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

    public String getEspesorPromedio() {
        return espesorPromedio;
    }

    public void setEspesorPromedio(String espesorPromedio) {
        this.espesorPromedio = espesorPromedio;
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

    public FRA_OIT_001 getFra_oit_001() {
        return fra_oit_001;
    }

    public void setFra_oit_001(FRA_OIT_001 fra_oit_001) {
        this.fra_oit_001 = fra_oit_001;
    }
}
