package com.demo.model.operacion.metodos.fra04gr.datas;

import com.demo.model.operacion.metodos.fra04gr.FRA_GR_001;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
public class FRA_GR_001_DATA {

    @Id
    @GeneratedValue
    @Column(nullable = false)
    private Long idFRAGRDATA;

    private String peso;

    @CreationTimestamp
    private Timestamp createdAt;

    @UpdateTimestamp
    private Timestamp updatedAt;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "idfragr")
    private FRA_GR_001 fra_gr_001;

    public FRA_GR_001_DATA() {
    }

    public FRA_GR_001_DATA(Long idFRAGRDATA, String peso, Timestamp createdAt, Timestamp updatedAt, FRA_GR_001 fra_gr_001) {
        this.idFRAGRDATA = idFRAGRDATA;
        this.peso = peso;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.fra_gr_001 = fra_gr_001;
    }

    public Long getIdFRAGRDATA() {
        return idFRAGRDATA;
    }

    public void setIdFRAGRDATA(Long idFRAGRDATA) {
        this.idFRAGRDATA = idFRAGRDATA;
    }

    public String getPeso() {
        return peso;
    }

    public void setPeso(String peso) {
        this.peso = peso;
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

    public FRA_GR_001 getFra_gr_001() {
        return fra_gr_001;
    }

    public void setFra_gr_001(FRA_GR_001 fra_gr_001) {
        this.fra_gr_001 = fra_gr_001;
    }
}
