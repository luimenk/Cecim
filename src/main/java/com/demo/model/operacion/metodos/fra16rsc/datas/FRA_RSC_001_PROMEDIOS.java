package com.demo.model.operacion.metodos.fra16rsc.datas;

import com.demo.model.operacion.metodos.fra16rsc.FRA_RSC_001;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;

public class FRA_RSC_001_PROMEDIOS {

    @Id
    @GeneratedValue
    @Column(nullable = false)
    private Long idFRARSCPROMEDIOS;

    private String temperatura;
    private String promedioFuerzaSello;
    private String promedioDesviacionEstandar;

    @CreationTimestamp
    private Timestamp createdAt;

    @UpdateTimestamp
    private Timestamp updatedAt;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "idfrarsc")
    private FRA_RSC_001 fra_rsc_001;

    public FRA_RSC_001_PROMEDIOS() {
    }

    public FRA_RSC_001_PROMEDIOS(Long idFRARSCPROMEDIOS, String temperatura, String promedioFuerzaSello, String promedioDesviacionEstandar, Timestamp createdAt, Timestamp updatedAt, FRA_RSC_001 fra_rsc_001) {
        this.idFRARSCPROMEDIOS = idFRARSCPROMEDIOS;
        this.temperatura = temperatura;
        this.promedioFuerzaSello = promedioFuerzaSello;
        this.promedioDesviacionEstandar = promedioDesviacionEstandar;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.fra_rsc_001 = fra_rsc_001;
    }

    public Long getIdFRARSCPROMEDIOS() {
        return idFRARSCPROMEDIOS;
    }

    public void setIdFRARSCPROMEDIOS(Long idFRARSCPROMEDIOS) {
        this.idFRARSCPROMEDIOS = idFRARSCPROMEDIOS;
    }

    public String getTemperatura() {
        return temperatura;
    }

    public void setTemperatura(String temperatura) {
        this.temperatura = temperatura;
    }

    public String getPromedioFuerzaSello() {
        return promedioFuerzaSello;
    }

    public void setPromedioFuerzaSello(String promedioFuerzaSello) {
        this.promedioFuerzaSello = promedioFuerzaSello;
    }

    public String getPromedioDesviacionEstandar() {
        return promedioDesviacionEstandar;
    }

    public void setPromedioDesviacionEstandar(String promedioDesviacionEstandar) {
        this.promedioDesviacionEstandar = promedioDesviacionEstandar;
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

    public FRA_RSC_001 getFra_rsc_001() {
        return fra_rsc_001;
    }

    public void setFra_rsc_001(FRA_RSC_001 fra_rsc_001) {
        this.fra_rsc_001 = fra_rsc_001;
    }
}
