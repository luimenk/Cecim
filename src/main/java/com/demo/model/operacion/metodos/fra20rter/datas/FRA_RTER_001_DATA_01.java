package com.demo.model.operacion.metodos.fra20rter.datas;

import com.demo.model.operacion.metodos.fra20rter.FRA_RTER_001;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
public class FRA_RTER_001_DATA_01 {

    @Id
    @GeneratedValue
    @Column(nullable = false)
    private Long idFRARTERDATA01;

    //Valores definidos para "MD"
    private String medicion;
    private String e1;
    private String e2;
    private String e3;
    private String promedioEspesor;
    private String fuerzaFluenciaTension;
    private String elongacionRuptura;
    private String resistenciaTension;
    private String pendiente;
    private String coeficienteCorrelacionPearson;
    private String moduloElastico;

    @CreationTimestamp
    private Timestamp createdAt;

    @UpdateTimestamp
    private Timestamp updatedAt;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "idfrarter")
    private FRA_RTER_001 fra_rter_001;

    public FRA_RTER_001_DATA_01() {
    }

    public FRA_RTER_001_DATA_01(Long idFRARTERDATA01, String medicion, String e1, String e2, String e3, String promedioEspesor, String fuerzaFluenciaTension, String elongacionRuptura, String resistenciaTension, String pendiente, String coeficienteCorrelacionPearson, String moduloElastico, Timestamp createdAt, Timestamp updatedAt, FRA_RTER_001 fra_rter_001) {
        this.idFRARTERDATA01 = idFRARTERDATA01;
        this.medicion = medicion;
        this.e1 = e1;
        this.e2 = e2;
        this.e3 = e3;
        this.promedioEspesor = promedioEspesor;
        this.fuerzaFluenciaTension = fuerzaFluenciaTension;
        this.elongacionRuptura = elongacionRuptura;
        this.resistenciaTension = resistenciaTension;
        this.pendiente = pendiente;
        this.coeficienteCorrelacionPearson = coeficienteCorrelacionPearson;
        this.moduloElastico = moduloElastico;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.fra_rter_001 = fra_rter_001;
    }

    public Long getIdFRARTERDATA01() {
        return idFRARTERDATA01;
    }

    public void setIdFRARTERDATA01(Long idFRARTERDATA01) {
        this.idFRARTERDATA01 = idFRARTERDATA01;
    }

    public String getMedicion() {
        return medicion;
    }

    public void setMedicion(String medicion) {
        this.medicion = medicion;
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

    public String getPromedioEspesor() {
        return promedioEspesor;
    }

    public void setPromedioEspesor(String promedioEspesor) {
        this.promedioEspesor = promedioEspesor;
    }

    public String getFuerzaFluenciaTension() {
        return fuerzaFluenciaTension;
    }

    public void setFuerzaFluenciaTension(String fuerzaFluenciaTension) {
        this.fuerzaFluenciaTension = fuerzaFluenciaTension;
    }

    public String getElongacionRuptura() {
        return elongacionRuptura;
    }

    public void setElongacionRuptura(String elongacionRuptura) {
        this.elongacionRuptura = elongacionRuptura;
    }

    public String getResistenciaTension() {
        return resistenciaTension;
    }

    public void setResistenciaTension(String resistenciaTension) {
        this.resistenciaTension = resistenciaTension;
    }

    public String getPendiente() {
        return pendiente;
    }

    public void setPendiente(String pendiente) {
        this.pendiente = pendiente;
    }

    public String getCoeficienteCorrelacionPearson() {
        return coeficienteCorrelacionPearson;
    }

    public void setCoeficienteCorrelacionPearson(String coeficienteCorrelacionPearson) {
        this.coeficienteCorrelacionPearson = coeficienteCorrelacionPearson;
    }

    public String getModuloElastico() {
        return moduloElastico;
    }

    public void setModuloElastico(String moduloElastico) {
        this.moduloElastico = moduloElastico;
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

    public FRA_RTER_001 getFra_rter_001() {
        return fra_rter_001;
    }

    public void setFra_rter_001(FRA_RTER_001 fra_rter_001) {
        this.fra_rter_001 = fra_rter_001;
    }
}
