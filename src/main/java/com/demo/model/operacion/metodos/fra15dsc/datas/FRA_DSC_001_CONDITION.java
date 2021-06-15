package com.demo.model.operacion.metodos.fra15dsc.datas;

import com.demo.model.operacion.metodos.fra15dsc.FRA_DSC;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
public class FRA_DSC_001_CONDITION {

    @Id
    @GeneratedValue
    @Column(nullable = false)
    private Long idFRADSCCONDITION;

    //CONDICIONES DE ENSAYO
    private String temperatura1a;
    private String temperatura1b;
    private String tiempo2;
    private String temperatura3a;
    private String temperatura3b;
    private String tiempo4;
    private String temperatura5a;
    private String temperatura5b;
    private String tiempo6;
    private String temperatura7a;
    private String temperatura7b;
    private String temperatura8;
    private String tasaCalentamiento;
    private String tasaEnfriamiento;

    @CreationTimestamp
    private Timestamp createdAt;

    @UpdateTimestamp
    private Timestamp updatedAt;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "idfradsc")
    private FRA_DSC fra_dsc;

    public FRA_DSC_001_CONDITION() {
    }

    public FRA_DSC_001_CONDITION(Long idFRADSCCONDITION, String temperatura1a, String temperatura1b, String tiempo2, String temperatura3a, String temperatura3b, String tiempo4, String temperatura5a, String temperatura5b, String tiempo6, String temperatura7a, String temperatura7b, String temperatura8, String tasaCalentamiento, String tasaEnfriamiento, Timestamp createdAt, Timestamp updatedAt, FRA_DSC fra_dsc) {
        this.idFRADSCCONDITION = idFRADSCCONDITION;
        this.temperatura1a = temperatura1a;
        this.temperatura1b = temperatura1b;
        this.tiempo2 = tiempo2;
        this.temperatura3a = temperatura3a;
        this.temperatura3b = temperatura3b;
        this.tiempo4 = tiempo4;
        this.temperatura5a = temperatura5a;
        this.temperatura5b = temperatura5b;
        this.tiempo6 = tiempo6;
        this.temperatura7a = temperatura7a;
        this.temperatura7b = temperatura7b;
        this.temperatura8 = temperatura8;
        this.tasaCalentamiento = tasaCalentamiento;
        this.tasaEnfriamiento = tasaEnfriamiento;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.fra_dsc = fra_dsc;
    }

    public Long getIdFRADSCCONDITION() {
        return idFRADSCCONDITION;
    }

    public void setIdFRADSCCONDITION(Long idFRADSCCONDITION) {
        this.idFRADSCCONDITION = idFRADSCCONDITION;
    }

    public String getTemperatura1a() {
        return temperatura1a;
    }

    public void setTemperatura1a(String temperatura1a) {
        this.temperatura1a = temperatura1a;
    }

    public String getTemperatura1b() {
        return temperatura1b;
    }

    public void setTemperatura1b(String temperatura1b) {
        this.temperatura1b = temperatura1b;
    }

    public String getTiempo2() {
        return tiempo2;
    }

    public void setTiempo2(String tiempo2) {
        this.tiempo2 = tiempo2;
    }

    public String getTemperatura3a() {
        return temperatura3a;
    }

    public void setTemperatura3a(String temperatura3a) {
        this.temperatura3a = temperatura3a;
    }

    public String getTemperatura3b() {
        return temperatura3b;
    }

    public void setTemperatura3b(String temperatura3b) {
        this.temperatura3b = temperatura3b;
    }

    public String getTiempo4() {
        return tiempo4;
    }

    public void setTiempo4(String tiempo4) {
        this.tiempo4 = tiempo4;
    }

    public String getTemperatura5a() {
        return temperatura5a;
    }

    public void setTemperatura5a(String temperatura5a) {
        this.temperatura5a = temperatura5a;
    }

    public String getTemperatura5b() {
        return temperatura5b;
    }

    public void setTemperatura5b(String temperatura5b) {
        this.temperatura5b = temperatura5b;
    }

    public String getTiempo6() {
        return tiempo6;
    }

    public void setTiempo6(String tiempo6) {
        this.tiempo6 = tiempo6;
    }

    public String getTemperatura7a() {
        return temperatura7a;
    }

    public void setTemperatura7a(String temperatura7a) {
        this.temperatura7a = temperatura7a;
    }

    public String getTemperatura7b() {
        return temperatura7b;
    }

    public void setTemperatura7b(String temperatura7b) {
        this.temperatura7b = temperatura7b;
    }

    public String getTemperatura8() {
        return temperatura8;
    }

    public void setTemperatura8(String temperatura8) {
        this.temperatura8 = temperatura8;
    }

    public String getTasaCalentamiento() {
        return tasaCalentamiento;
    }

    public void setTasaCalentamiento(String tasaCalentamiento) {
        this.tasaCalentamiento = tasaCalentamiento;
    }

    public String getTasaEnfriamiento() {
        return tasaEnfriamiento;
    }

    public void setTasaEnfriamiento(String tasaEnfriamiento) {
        this.tasaEnfriamiento = tasaEnfriamiento;
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

    public FRA_DSC getFra_dsc() {
        return fra_dsc;
    }

    public void setFra_dsc(FRA_DSC fra_dsc) {
        this.fra_dsc = fra_dsc;
    }
}
