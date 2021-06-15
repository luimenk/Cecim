package com.demo.model.operacion.metodos.fra06ncp.datas;

import com.demo.model.operacion.metodos.fra06ncp.FRA_NCP_001;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
public class FRA_NCP_001_DATA_02 {

    @Id
    @GeneratedValue
    @Column(nullable = false)
    private Long idFRANCPDATA02;

    private String espesorTotalMicrometro;

    @CreationTimestamp
    private Timestamp createdAt;

    @UpdateTimestamp
    private Timestamp updatedAt;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "idfrancp")
    private FRA_NCP_001 fra_ncp_001;

    public FRA_NCP_001_DATA_02() {
    }

    public FRA_NCP_001_DATA_02(Long idFRANCPDATA02, String espesorTotalMicrometro, Timestamp createdAt, Timestamp updatedAt, FRA_NCP_001 fra_ncp_001) {
        this.idFRANCPDATA02 = idFRANCPDATA02;
        this.espesorTotalMicrometro = espesorTotalMicrometro;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.fra_ncp_001 = fra_ncp_001;
    }

    public Long getIdFRANCPDATA02() {
        return idFRANCPDATA02;
    }

    public void setIdFRANCPDATA02(Long idFRANCPDATA02) {
        this.idFRANCPDATA02 = idFRANCPDATA02;
    }

    public String getEspesorTotalMicrometro() {
        return espesorTotalMicrometro;
    }

    public void setEspesorTotalMicrometro(String espesorTotalMicrometro) {
        this.espesorTotalMicrometro = espesorTotalMicrometro;
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
