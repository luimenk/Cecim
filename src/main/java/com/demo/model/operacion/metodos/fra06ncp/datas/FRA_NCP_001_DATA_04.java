package com.demo.model.operacion.metodos.fra06ncp.datas;

import com.demo.model.operacion.metodos.fra06ncp.FRA_NCP_001;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
public class FRA_NCP_001_DATA_04 {

    @Id
    @GeneratedValue
    @Column(nullable = false)
    private Long idFRANCPDATA04;

    private String espesorPorMicroscopia;

    @CreationTimestamp
    private Timestamp createdAt;

    @UpdateTimestamp
    private Timestamp updatedAt;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "idfrancp")
    private FRA_NCP_001 fra_ncp_001;

    public FRA_NCP_001_DATA_04() {
    }

    public FRA_NCP_001_DATA_04(Long idFRANCPDATA04, String espesorPorMicroscopia, Timestamp createdAt, Timestamp updatedAt, FRA_NCP_001 fra_ncp_001) {
        this.idFRANCPDATA04 = idFRANCPDATA04;
        this.espesorPorMicroscopia = espesorPorMicroscopia;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.fra_ncp_001 = fra_ncp_001;
    }

    public Long getIdFRANCPDATA04() {
        return idFRANCPDATA04;
    }

    public void setIdFRANCPDATA04(Long idFRANCPDATA04) {
        this.idFRANCPDATA04 = idFRANCPDATA04;
    }

    public String getEspesorPorMicroscopia() {
        return espesorPorMicroscopia;
    }

    public void setEspesorPorMicroscopia(String espesorPorMicroscopia) {
        this.espesorPorMicroscopia = espesorPorMicroscopia;
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

    public FRA_NCP_001 getFra_ncp_001() {
        return fra_ncp_001;
    }

    public void setFra_ncp_001(FRA_NCP_001 fra_ncp_001) {
        this.fra_ncp_001 = fra_ncp_001;
    }
}
