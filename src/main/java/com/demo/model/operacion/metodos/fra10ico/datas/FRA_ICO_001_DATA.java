package com.demo.model.operacion.metodos.fra10ico.datas;

import com.demo.model.operacion.metodos.fra10ico.FRA_ICO_001;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
public class FRA_ICO_001_DATA {

    @Id
    @GeneratedValue
    @Column(nullable = false)
    private Long idFRAICODATA;

    private String tiempoExposicion;
    private String E1;
    private String E2;
    private String E3;
    private String promedioEspesor;
    private String medicion1;
    private String medicion2;
    private String promedioCarbonillo;

    @CreationTimestamp
    private Timestamp createdAt;

    @UpdateTimestamp
    private Timestamp updatedAt;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "idfraico")
    private FRA_ICO_001 fra_ico_001;

    public FRA_ICO_001_DATA() {
    }

    public FRA_ICO_001_DATA(Long idFRAICODATA, String tiempoExposicion, String e1, String e2, String e3, String promedioEspesor, String medicion1, String medicion2, String promedioCarbonillo, Timestamp createdAt, Timestamp updatedAt, FRA_ICO_001 fra_ico_001) {
        this.idFRAICODATA = idFRAICODATA;
        this.tiempoExposicion = tiempoExposicion;
        E1 = e1;
        E2 = e2;
        E3 = e3;
        this.promedioEspesor = promedioEspesor;
        this.medicion1 = medicion1;
        this.medicion2 = medicion2;
        this.promedioCarbonillo = promedioCarbonillo;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.fra_ico_001 = fra_ico_001;
    }

    public Long getIdFRAICODATA() {
        return idFRAICODATA;
    }

    public void setIdFRAICODATA(Long idFRAICODATA) {
        this.idFRAICODATA = idFRAICODATA;
    }

    public String getTiempoExposicion() {
        return tiempoExposicion;
    }

    public void setTiempoExposicion(String tiempoExposicion) {
        this.tiempoExposicion = tiempoExposicion;
    }

    public String getE1() {
        return E1;
    }

    public void setE1(String e1) {
        E1 = e1;
    }

    public String getE2() {
        return E2;
    }

    public void setE2(String e2) {
        E2 = e2;
    }

    public String getE3() {
        return E3;
    }

    public void setE3(String e3) {
        E3 = e3;
    }

    public String getPromedioEspesor() {
        return promedioEspesor;
    }

    public void setPromedioEspesor(String promedioEspesor) {
        this.promedioEspesor = promedioEspesor;
    }

    public String getMedicion1() {
        return medicion1;
    }

    public void setMedicion1(String medicion1) {
        this.medicion1 = medicion1;
    }

    public String getMedicion2() {
        return medicion2;
    }

    public void setMedicion2(String medicion2) {
        this.medicion2 = medicion2;
    }

    public String getPromedioCarbonillo() {
        return promedioCarbonillo;
    }

    public void setPromedioCarbonillo(String promedioCarbonillo) {
        this.promedioCarbonillo = promedioCarbonillo;
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

    public FRA_ICO_001 getFra_ico_001() {
        return fra_ico_001;
    }

    public void setFra_ico_001(FRA_ICO_001 fra_ico_001) {
        this.fra_ico_001 = fra_ico_001;
    }
}
