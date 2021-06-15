package com.demo.model.operacion.metodos.fra09tga.datas;

import com.demo.model.operacion.metodos.fra09tga.FRA_TGA_001;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
public class FRA_TGA_001_CONDITION {

    @Id
    @GeneratedValue
    @Column(nullable = false)
    private Long idFRATGACONDITION;

    private String peso;
    private String posicionPortadorMuestra;
    private String tipoMaterial;

    //purga
    private String temperatura1;
    private String tiempo1;
    //inicio-dinamica
    private String temperatura2a;
    private String temperatura2b;
    private String tipoAtmosfera2;
    //dinamica-dinamica
    private String temperatura3a;
    private String temperatura3b;
    private String tipoAtmosfera3;
    //tasa de calentamiento
    private String tasaCalentamiento;
    //dinamica-dinamica
    private String temperatura5a;
    private String temperatura5b;
    private String tiempo2;
    //tasa de enfriamiento
    private String tasaEnfriamiento;
    //temperatura de emergencia
    private String temperaturaEmergencia;

    @CreationTimestamp
    private Timestamp createdAt;

    @UpdateTimestamp
    private Timestamp updatedAt;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "idfratga")
    private FRA_TGA_001 fra_tga_001;

    public FRA_TGA_001_CONDITION() {
    }

    public FRA_TGA_001_CONDITION(Long idFRATGACONDITION, String peso, String posicionPortadorMuestra, String tipoMaterial, String temperatura1, String tiempo1, String temperatura2a, String temperatura2b, String tipoAtmosfera2, String temperatura3a, String temperatura3b, String tipoAtmosfera3, String tasaCalentamiento, String temperatura5a, String temperatura5b, String tiempo2, String tasaEnfriamiento, String temperaturaEmergencia, Timestamp createdAt, Timestamp updatedAt, FRA_TGA_001 fra_tga_001) {
        this.idFRATGACONDITION = idFRATGACONDITION;
        this.peso = peso;
        this.posicionPortadorMuestra = posicionPortadorMuestra;
        this.tipoMaterial = tipoMaterial;
        this.temperatura1 = temperatura1;
        this.tiempo1 = tiempo1;
        this.temperatura2a = temperatura2a;
        this.temperatura2b = temperatura2b;
        this.tipoAtmosfera2 = tipoAtmosfera2;
        this.temperatura3a = temperatura3a;
        this.temperatura3b = temperatura3b;
        this.tipoAtmosfera3 = tipoAtmosfera3;
        this.tasaCalentamiento = tasaCalentamiento;
        this.temperatura5a = temperatura5a;
        this.temperatura5b = temperatura5b;
        this.tiempo2 = tiempo2;
        this.tasaEnfriamiento = tasaEnfriamiento;
        this.temperaturaEmergencia = temperaturaEmergencia;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.fra_tga_001 = fra_tga_001;
    }

    public Long getIdFRATGACONDITION() {
        return idFRATGACONDITION;
    }

    public void setIdFRATGACONDITION(Long idFRATGACONDITION) {
        this.idFRATGACONDITION = idFRATGACONDITION;
    }

    public String getPeso() {
        return peso;
    }

    public void setPeso(String peso) {
        this.peso = peso;
    }

    public String getPosicionPortadorMuestra() {
        return posicionPortadorMuestra;
    }

    public void setPosicionPortadorMuestra(String posicionPortadorMuestra) {
        this.posicionPortadorMuestra = posicionPortadorMuestra;
    }

    public String getTipoMaterial() {
        return tipoMaterial;
    }

    public void setTipoMaterial(String tipoMaterial) {
        this.tipoMaterial = tipoMaterial;
    }

    public String getTemperatura1() {
        return temperatura1;
    }

    public void setTemperatura1(String temperatura1) {
        this.temperatura1 = temperatura1;
    }

    public String getTiempo1() {
        return tiempo1;
    }

    public void setTiempo1(String tiempo1) {
        this.tiempo1 = tiempo1;
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

    public String getTipoAtmosfera2() {
        return tipoAtmosfera2;
    }

    public void setTipoAtmosfera2(String tipoAtmosfera2) {
        this.tipoAtmosfera2 = tipoAtmosfera2;
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

    public String getTipoAtmosfera3() {
        return tipoAtmosfera3;
    }

    public void setTipoAtmosfera3(String tipoAtmosfera3) {
        this.tipoAtmosfera3 = tipoAtmosfera3;
    }

    public String getTasaCalentamiento() {
        return tasaCalentamiento;
    }

    public void setTasaCalentamiento(String tasaCalentamiento) {
        this.tasaCalentamiento = tasaCalentamiento;
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

    public String getTiempo2() {
        return tiempo2;
    }

    public void setTiempo2(String tiempo2) {
        this.tiempo2 = tiempo2;
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

    public FRA_TGA_001 getFra_tga_001() {
        return fra_tga_001;
    }

    public void setFra_tga_001(FRA_TGA_001 fra_tga_001) {
        this.fra_tga_001 = fra_tga_001;
    }
}
