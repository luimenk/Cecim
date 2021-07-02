package com.demo.model.operacion.metodos.fra16rsc.datas;

import com.demo.model.operacion.metodos.fra16rsc.FRA_RSC_001;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
public class FRA_RSC_001_DATA {

    @Id
    @GeneratedValue
    @Column(nullable = false)
    private Long idFRARSCDATA;

    private String fuerzaSello;
    private String desviacionEstandar;
    private String modoFalla;

    @CreationTimestamp
    private Timestamp createdAt;

    @UpdateTimestamp
    private Timestamp updatedAt;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "idfrarsc")
    private FRA_RSC_001 fra_rsc_001;

    public FRA_RSC_001_DATA() {
    }

    public FRA_RSC_001_DATA(Long idFRARSCDATA, String fuerzaSello, String desviacionEstandar, String modoFalla, Timestamp createdAt, Timestamp updatedAt, FRA_RSC_001 fra_rsc_001) {
        this.idFRARSCDATA = idFRARSCDATA;
        this.fuerzaSello = fuerzaSello;
        this.desviacionEstandar = desviacionEstandar;
        this.modoFalla = modoFalla;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.fra_rsc_001 = fra_rsc_001;
    }

    public Long getIdFRARSCDATA() {
        return idFRARSCDATA;
    }

    public void setIdFRARSCDATA(Long idFRARSCDATA) {
        this.idFRARSCDATA = idFRARSCDATA;
    }

    public String getFuerzaSello() {
        return fuerzaSello;
    }

    public void setFuerzaSello(String fuerzaSello) {
        this.fuerzaSello = fuerzaSello;
    }

    public String getDesviacionEstandar() {
        return desviacionEstandar;
    }

    public void setDesviacionEstandar(String desviacionEstandar) {
        this.desviacionEstandar = desviacionEstandar;
    }

    public String getModoFalla() {
        return modoFalla;
    }

    public void setModoFalla(String modoFalla) {
        this.modoFalla = modoFalla;
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
