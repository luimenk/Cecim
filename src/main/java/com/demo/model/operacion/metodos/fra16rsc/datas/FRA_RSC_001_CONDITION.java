package com.demo.model.operacion.metodos.fra16rsc.datas;

import com.demo.model.operacion.metodos.fra16rsc.FRA_RSC_001;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
public class FRA_RSC_001_CONDITION {

    @Id
    @GeneratedValue
    @Column(nullable = false)
    private Long idFRARSCCONDITION;

    private String anchoMuestra;
    private String anchoMordazas;
    private String tipoMordazas;
    private String presion;

    private String tiempoSellado;
    private String tiempoRetardo;
    private String velocidadDesprendimiento;

    private String temperaturaSellado1;
    private String temperaturaSellado2;
    private String temperaturaSellado3;
    private String temperaturaSellado4;
    private String temperaturaSellado5;
    private String temperaturaMordazaSuperior1;
    private String temperaturaMordazaSuperior2;
    private String temperaturaMordazaSuperior3;
    private String temperaturaMordazaSuperior4;
    private String temperaturaMordazaSuperior5;
    private String temperaturaMordazaInferior1;
    private String temperaturaMordazaInferior2;
    private String temperaturaMordazaInferior3;
    private String temperaturaMordazaInferior4;
    private String temperaturaMordazaInferior5;
    private String tasaIncrementoTemperaturaMordaza;

    private String ejeAnalisis;
    private String caraAnalisis;
    private String tipoMaterial;

    @CreationTimestamp
    private Timestamp createdAt;

    @UpdateTimestamp
    private Timestamp updatedAt;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "idfrarsc")
    private FRA_RSC_001 fra_rsc_001;

    public FRA_RSC_001_CONDITION() {
    }

    public FRA_RSC_001_CONDITION(Long idFRARSCCONDITION, String anchoMuestra, String anchoMordazas, String tipoMordazas, String presion, String tiempoSellado, String tiempoRetardo, String velocidadDesprendimiento, String temperaturaSellado1, String temperaturaSellado2, String temperaturaSellado3, String temperaturaSellado4, String temperaturaSellado5, String temperaturaMordazaSuperior1, String temperaturaMordazaSuperior2, String temperaturaMordazaSuperior3, String temperaturaMordazaSuperior4, String temperaturaMordazaSuperior5, String temperaturaMordazaInferior1, String temperaturaMordazaInferior2, String temperaturaMordazaInferior3, String temperaturaMordazaInferior4, String temperaturaMordazaInferior5, String tasaIncrementoTemperaturaMordaza, String ejeAnalisis, String caraAnalisis, String tipoMaterial, Timestamp createdAt, Timestamp updatedAt, FRA_RSC_001 fra_rsc_001) {
        this.idFRARSCCONDITION = idFRARSCCONDITION;
        this.anchoMuestra = anchoMuestra;
        this.anchoMordazas = anchoMordazas;
        this.tipoMordazas = tipoMordazas;
        this.presion = presion;
        this.tiempoSellado = tiempoSellado;
        this.tiempoRetardo = tiempoRetardo;
        this.velocidadDesprendimiento = velocidadDesprendimiento;
        this.temperaturaSellado1 = temperaturaSellado1;
        this.temperaturaSellado2 = temperaturaSellado2;
        this.temperaturaSellado3 = temperaturaSellado3;
        this.temperaturaSellado4 = temperaturaSellado4;
        this.temperaturaSellado5 = temperaturaSellado5;
        this.temperaturaMordazaSuperior1 = temperaturaMordazaSuperior1;
        this.temperaturaMordazaSuperior2 = temperaturaMordazaSuperior2;
        this.temperaturaMordazaSuperior3 = temperaturaMordazaSuperior3;
        this.temperaturaMordazaSuperior4 = temperaturaMordazaSuperior4;
        this.temperaturaMordazaSuperior5 = temperaturaMordazaSuperior5;
        this.temperaturaMordazaInferior1 = temperaturaMordazaInferior1;
        this.temperaturaMordazaInferior2 = temperaturaMordazaInferior2;
        this.temperaturaMordazaInferior3 = temperaturaMordazaInferior3;
        this.temperaturaMordazaInferior4 = temperaturaMordazaInferior4;
        this.temperaturaMordazaInferior5 = temperaturaMordazaInferior5;
        this.tasaIncrementoTemperaturaMordaza = tasaIncrementoTemperaturaMordaza;
        this.ejeAnalisis = ejeAnalisis;
        this.caraAnalisis = caraAnalisis;
        this.tipoMaterial = tipoMaterial;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.fra_rsc_001 = fra_rsc_001;
    }

    public Long getIdFRARSCCONDITION() {
        return idFRARSCCONDITION;
    }

    public void setIdFRARSCCONDITION(Long idFRARSCCONDITION) {
        this.idFRARSCCONDITION = idFRARSCCONDITION;
    }

    public String getAnchoMuestra() {
        return anchoMuestra;
    }

    public void setAnchoMuestra(String anchoMuestra) {
        this.anchoMuestra = anchoMuestra;
    }

    public String getAnchoMordazas() {
        return anchoMordazas;
    }

    public void setAnchoMordazas(String anchoMordazas) {
        this.anchoMordazas = anchoMordazas;
    }

    public String getTipoMordazas() {
        return tipoMordazas;
    }

    public void setTipoMordazas(String tipoMordazas) {
        this.tipoMordazas = tipoMordazas;
    }

    public String getPresion() {
        return presion;
    }

    public void setPresion(String presion) {
        this.presion = presion;
    }

    public String getTiempoSellado() {
        return tiempoSellado;
    }

    public void setTiempoSellado(String tiempoSellado) {
        this.tiempoSellado = tiempoSellado;
    }

    public String getTiempoRetardo() {
        return tiempoRetardo;
    }

    public void setTiempoRetardo(String tiempoRetardo) {
        this.tiempoRetardo = tiempoRetardo;
    }

    public String getVelocidadDesprendimiento() {
        return velocidadDesprendimiento;
    }

    public void setVelocidadDesprendimiento(String velocidadDesprendimiento) {
        this.velocidadDesprendimiento = velocidadDesprendimiento;
    }

    public String getTemperaturaSellado1() {
        return temperaturaSellado1;
    }

    public void setTemperaturaSellado1(String temperaturaSellado1) {
        this.temperaturaSellado1 = temperaturaSellado1;
    }

    public String getTemperaturaSellado2() {
        return temperaturaSellado2;
    }

    public void setTemperaturaSellado2(String temperaturaSellado2) {
        this.temperaturaSellado2 = temperaturaSellado2;
    }

    public String getTemperaturaSellado3() {
        return temperaturaSellado3;
    }

    public void setTemperaturaSellado3(String temperaturaSellado3) {
        this.temperaturaSellado3 = temperaturaSellado3;
    }

    public String getTemperaturaSellado4() {
        return temperaturaSellado4;
    }

    public void setTemperaturaSellado4(String temperaturaSellado4) {
        this.temperaturaSellado4 = temperaturaSellado4;
    }

    public String getTemperaturaSellado5() {
        return temperaturaSellado5;
    }

    public void setTemperaturaSellado5(String temperaturaSellado5) {
        this.temperaturaSellado5 = temperaturaSellado5;
    }

    public String getTemperaturaMordazaSuperior1() {
        return temperaturaMordazaSuperior1;
    }

    public void setTemperaturaMordazaSuperior1(String temperaturaMordazaSuperior1) {
        this.temperaturaMordazaSuperior1 = temperaturaMordazaSuperior1;
    }

    public String getTemperaturaMordazaSuperior2() {
        return temperaturaMordazaSuperior2;
    }

    public void setTemperaturaMordazaSuperior2(String temperaturaMordazaSuperior2) {
        this.temperaturaMordazaSuperior2 = temperaturaMordazaSuperior2;
    }

    public String getTemperaturaMordazaSuperior3() {
        return temperaturaMordazaSuperior3;
    }

    public void setTemperaturaMordazaSuperior3(String temperaturaMordazaSuperior3) {
        this.temperaturaMordazaSuperior3 = temperaturaMordazaSuperior3;
    }

    public String getTemperaturaMordazaSuperior4() {
        return temperaturaMordazaSuperior4;
    }

    public void setTemperaturaMordazaSuperior4(String temperaturaMordazaSuperior4) {
        this.temperaturaMordazaSuperior4 = temperaturaMordazaSuperior4;
    }

    public String getTemperaturaMordazaSuperior5() {
        return temperaturaMordazaSuperior5;
    }

    public void setTemperaturaMordazaSuperior5(String temperaturaMordazaSuperior5) {
        this.temperaturaMordazaSuperior5 = temperaturaMordazaSuperior5;
    }

    public String getTemperaturaMordazaInferior1() {
        return temperaturaMordazaInferior1;
    }

    public void setTemperaturaMordazaInferior1(String temperaturaMordazaInferior1) {
        this.temperaturaMordazaInferior1 = temperaturaMordazaInferior1;
    }

    public String getTemperaturaMordazaInferior2() {
        return temperaturaMordazaInferior2;
    }

    public void setTemperaturaMordazaInferior2(String temperaturaMordazaInferior2) {
        this.temperaturaMordazaInferior2 = temperaturaMordazaInferior2;
    }

    public String getTemperaturaMordazaInferior3() {
        return temperaturaMordazaInferior3;
    }

    public void setTemperaturaMordazaInferior3(String temperaturaMordazaInferior3) {
        this.temperaturaMordazaInferior3 = temperaturaMordazaInferior3;
    }

    public String getTemperaturaMordazaInferior4() {
        return temperaturaMordazaInferior4;
    }

    public void setTemperaturaMordazaInferior4(String temperaturaMordazaInferior4) {
        this.temperaturaMordazaInferior4 = temperaturaMordazaInferior4;
    }

    public String getTemperaturaMordazaInferior5() {
        return temperaturaMordazaInferior5;
    }

    public void setTemperaturaMordazaInferior5(String temperaturaMordazaInferior5) {
        this.temperaturaMordazaInferior5 = temperaturaMordazaInferior5;
    }

    public String getTasaIncrementoTemperaturaMordaza() {
        return tasaIncrementoTemperaturaMordaza;
    }

    public void setTasaIncrementoTemperaturaMordaza(String tasaIncrementoTemperaturaMordaza) {
        this.tasaIncrementoTemperaturaMordaza = tasaIncrementoTemperaturaMordaza;
    }

    public String getEjeAnalisis() {
        return ejeAnalisis;
    }

    public void setEjeAnalisis(String ejeAnalisis) {
        this.ejeAnalisis = ejeAnalisis;
    }

    public String getCaraAnalisis() {
        return caraAnalisis;
    }

    public void setCaraAnalisis(String caraAnalisis) {
        this.caraAnalisis = caraAnalisis;
    }

    public String getTipoMaterial() {
        return tipoMaterial;
    }

    public void setTipoMaterial(String tipoMaterial) {
        this.tipoMaterial = tipoMaterial;
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
