package com.demo.model.operacion.metodos.fra14oit.datas;

import com.demo.model.operacion.metodos.fra14oit.FRA_OIT_001;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
public class FRA_OIT_001_CONDITION {

    @Id
    @GeneratedValue
    @Column(nullable = false)
    private Long idFRAOITCONDITION;

    private String pesom1;
    private String pesom2;
    private String posicionm1;
    private String posicionm2;

    private String tiempoPurga;
    private String temperatura2a;
    private String temperatura2b;
    private String temperatura3;
    private String tiempo3;
    private String temperatura4a;
    private String temperatura4b;
    private String tiempoIsotermico1;
    private String tiempoIsotermico2;
    private String temperatura9a;
    private String temperatura9b;
    private String tasaEnfriamiento;
    private String temperaturaEmergencia;

    @CreationTimestamp
    private Timestamp createdAt;

    @UpdateTimestamp
    private Timestamp updatedAt;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "idfraoit")
    private FRA_OIT_001 fra_oit_001;

    public FRA_OIT_001_CONDITION() {
    }

    public FRA_OIT_001_CONDITION(Long idFRAOITCONDITION, String pesom1, String pesom2, String posicionm1, String posicionm2, String tiempoPurga, String temperatura2a, String temperatura2b, String temperatura3, String tiempo3, String temperatura4a, String temperatura4b, String tiempoIsotermico1, String tiempoIsotermico2, String temperatura9a, String temperatura9b, String tasaEnfriamiento, String temperaturaEmergencia, Timestamp createdAt, Timestamp updatedAt, FRA_OIT_001 fra_oit_001) {
        this.idFRAOITCONDITION = idFRAOITCONDITION;
        this.pesom1 = pesom1;
        this.pesom2 = pesom2;
        this.posicionm1 = posicionm1;
        this.posicionm2 = posicionm2;
        this.tiempoPurga = tiempoPurga;
        this.temperatura2a = temperatura2a;
        this.temperatura2b = temperatura2b;
        this.temperatura3 = temperatura3;
        this.tiempo3 = tiempo3;
        this.temperatura4a = temperatura4a;
        this.temperatura4b = temperatura4b;
        this.tiempoIsotermico1 = tiempoIsotermico1;
        this.tiempoIsotermico2 = tiempoIsotermico2;
        this.temperatura9a = temperatura9a;
        this.temperatura9b = temperatura9b;
        this.tasaEnfriamiento = tasaEnfriamiento;
        this.temperaturaEmergencia = temperaturaEmergencia;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.fra_oit_001 = fra_oit_001;
    }

    public Long getIdFRAOITCONDITION() {
        return idFRAOITCONDITION;
    }

    public void setIdFRAOITCONDITION(Long idFRAOITCONDITION) {
        this.idFRAOITCONDITION = idFRAOITCONDITION;
    }

    public String getPesom1() {
        return pesom1;
    }

    public void setPesom1(String pesom1) {
        this.pesom1 = pesom1;
    }

    public String getPesom2() {
        return pesom2;
    }

    public void setPesom2(String pesom2) {
        this.pesom2 = pesom2;
    }

    public String getPosicionm1() {
        return posicionm1;
    }

    public void setPosicionm1(String posicionm1) {
        this.posicionm1 = posicionm1;
    }

    public String getPosicionm2() {
        return posicionm2;
    }

    public void setPosicionm2(String posicionm2) {
        this.posicionm2 = posicionm2;
    }

    public String getTiempoPurga() {
        return tiempoPurga;
    }

    public void setTiempoPurga(String tiempoPurga) {
        this.tiempoPurga = tiempoPurga;
    }

    public String getTemperatura2a() {
        return temperatura2a;
    }

    public void setTemperatura2a(String temperatura2a) {
        this.temperatura2a = temperatura2a;
    }

    public String getTemperatura2b() {
        return temperatura2b;
    }

    public void setTemperatura2b(String temperatura2b) {
        this.temperatura2b = temperatura2b;
    }

    public String getTemperatura3() {
        return temperatura3;
    }

    public void setTemperatura3(String temperatura3) {
        this.temperatura3 = temperatura3;
    }

    public String getTiempo3() {
        return tiempo3;
    }

    public void setTiempo3(String tiempo3) {
        this.tiempo3 = tiempo3;
    }

    public String getTemperatura4a() {
        return temperatura4a;
    }

    public void setTemperatura4a(String temperatura4a) {
        this.temperatura4a = temperatura4a;
    }

    public String getTemperatura4b() {
        return temperatura4b;
    }

    public void setTemperatura4b(String temperatura4b) {
        this.temperatura4b = temperatura4b;
    }

    public String getTiempoIsotermico1() {
        return tiempoIsotermico1;
    }

    public void setTiempoIsotermico1(String tiempoIsotermico1) {
        this.tiempoIsotermico1 = tiempoIsotermico1;
    }

    public String getTiempoIsotermico2() {
        return tiempoIsotermico2;
    }

    public void setTiempoIsotermico2(String tiempoIsotermico2) {
        this.tiempoIsotermico2 = tiempoIsotermico2;
    }

    public String getTemperatura9a() {
        return temperatura9a;
    }

    public void setTemperatura9a(String temperatura9a) {
        this.temperatura9a = temperatura9a;
    }

    public String getTemperatura9b() {
        return temperatura9b;
    }

    public void setTemperatura9b(String temperatura9b) {
        this.temperatura9b = temperatura9b;
    }

    public String getTasaEnfriamiento() {
        return tasaEnfriamiento;
    }

    public void setTasaEnfriamiento(String tasaEnfriamiento) {
        this.tasaEnfriamiento = tasaEnfriamiento;
    }

    public String getTemperaturaEmergencia() {
        return temperaturaEmergencia;
    }

    public void setTemperaturaEmergencia(String temperaturaEmergencia) {
        this.temperaturaEmergencia = temperaturaEmergencia;
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
