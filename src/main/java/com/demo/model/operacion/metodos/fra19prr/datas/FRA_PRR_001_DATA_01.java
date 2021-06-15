package com.demo.model.operacion.metodos.fra19prr.datas;

import com.demo.model.operacion.metodos.fra19prr.FRA_PRR_001;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
public class FRA_PRR_001_DATA_01 {

    @Id
    @GeneratedValue
    @Column(nullable = false)
    private Long idFRAPRRDATA01;

    //Valores definidos para "MD"
    private String espesor1;
    private String espesor2;
    private String espesor3;
    private String espesorPromedio;
    private String resistenciaRasgado;
    private String desgarreOblicuo;

    @CreationTimestamp
    private Timestamp createdAt;

    @UpdateTimestamp
    private Timestamp updatedAt;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "idfraprr")
    private FRA_PRR_001 fra_prr_001;

    public FRA_PRR_001_DATA_01() {
    }

    public FRA_PRR_001_DATA_01(Long idFRAPRRDATA01, String espesor1, String espesor2, String espesor3, String espesorPromedio, String resistenciaRasgado, String desgarreOblicuo, Timestamp createdAt, Timestamp updatedAt, FRA_PRR_001 fra_prr_001) {
        this.idFRAPRRDATA01 = idFRAPRRDATA01;
        this.espesor1 = espesor1;
        this.espesor2 = espesor2;
        this.espesor3 = espesor3;
        this.espesorPromedio = espesorPromedio;
        this.resistenciaRasgado = resistenciaRasgado;
        this.desgarreOblicuo = desgarreOblicuo;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.fra_prr_001 = fra_prr_001;
    }

    public Long getIdFRAPRRDATA01() {
        return idFRAPRRDATA01;
    }

    public void setIdFRAPRRDATA01(Long idFRAPRRDATA01) {
        this.idFRAPRRDATA01 = idFRAPRRDATA01;
    }

    public String getEspesor1() {
        return espesor1;
    }

    public void setEspesor1(String espesor1) {
        this.espesor1 = espesor1;
    }

    public String getEspesor2() {
        return espesor2;
    }

    public void setEspesor2(String espesor2) {
        this.espesor2 = espesor2;
    }

    public String getEspesor3() {
        return espesor3;
    }

    public void setEspesor3(String espesor3) {
        this.espesor3 = espesor3;
    }

    public String getEspesorPromedio() {
        return espesorPromedio;
    }

    public void setEspesorPromedio(String espesorPromedio) {
        this.espesorPromedio = espesorPromedio;
    }

    public String getResistenciaRasgado() {
        return resistenciaRasgado;
    }

    public void setResistenciaRasgado(String resistenciaRasgado) {
        this.resistenciaRasgado = resistenciaRasgado;
    }

    public String getDesgarreOblicuo() {
        return desgarreOblicuo;
    }

    public void setDesgarreOblicuo(String desgarreOblicuo) {
        this.desgarreOblicuo = desgarreOblicuo;
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

    public FRA_PRR_001 getFra_prr_001() {
        return fra_prr_001;
    }

    public void setFra_prr_001(FRA_PRR_001 fra_prr_001) {
        this.fra_prr_001 = fra_prr_001;
    }
}
