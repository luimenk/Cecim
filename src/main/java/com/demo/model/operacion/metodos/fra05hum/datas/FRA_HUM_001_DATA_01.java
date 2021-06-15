package com.demo.model.operacion.metodos.fra05hum.datas;

import com.demo.model.operacion.metodos.fra05hum.FRA_HUM_001;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
public class FRA_HUM_001_DATA_01 {

    @Id
    @GeneratedValue
    @Column(nullable = false)
    private Long idFRAHUMDATA01;

    private String pesoCharola;
    private String pesoInicial;
    private String tiempoReposo;
    private String pesoFinal;
    private String pesoSeco;
    private String pips;
    private String porcentajeHumedad;

    @CreationTimestamp
    private Timestamp createdAt;

    @UpdateTimestamp
    private Timestamp updatedAt;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "idfrahum")
    private FRA_HUM_001 fra_hum_001;

    public FRA_HUM_001_DATA_01() {
    }

    public FRA_HUM_001_DATA_01(Long idFRAHUMDATA01, String pesoCharola, String pesoInicial, String tiempoReposo, String pesoFinal, String pesoSeco, String pips, String porcentajeHumedad, Timestamp createdAt, Timestamp updatedAt, FRA_HUM_001 fra_hum_001) {
        this.idFRAHUMDATA01 = idFRAHUMDATA01;
        this.pesoCharola = pesoCharola;
        this.pesoInicial = pesoInicial;
        this.tiempoReposo = tiempoReposo;
        this.pesoFinal = pesoFinal;
        this.pesoSeco = pesoSeco;
        this.pips = pips;
        this.porcentajeHumedad = porcentajeHumedad;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.fra_hum_001 = fra_hum_001;
    }

    public Long getIdFRAHUMDATA01() {
        return idFRAHUMDATA01;
    }

    public void setIdFRAHUMDATA01(Long idFRAHUMDATA01) {
        this.idFRAHUMDATA01 = idFRAHUMDATA01;
    }

    public String getPesoCharola() {
        return pesoCharola;
    }

    public void setPesoCharola(String pesoCharola) {
        this.pesoCharola = pesoCharola;
    }

    public String getPesoInicial() {
        return pesoInicial;
    }

    public void setPesoInicial(String pesoInicial) {
        this.pesoInicial = pesoInicial;
    }

    public String getTiempoReposo() {
        return tiempoReposo;
    }

    public void setTiempoReposo(String tiempoReposo) {
        this.tiempoReposo = tiempoReposo;
    }

    public String getPesoSeco() {
        return pesoSeco;
    }

    public void setPesoSeco(String pesoSeco) {
        this.pesoSeco = pesoSeco;
    }

    public String getPips() {
        return pips;
    }

    public void setPips(String pips) {
        this.pips = pips;
    }

    public String getPorcentajeHumedad() {
        return porcentajeHumedad;
    }

    public void setPorcentajeHumedad(String porcentajeHumedad) {
        this.porcentajeHumedad = porcentajeHumedad;
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

    public FRA_HUM_001 getFra_hum_001() {
        return fra_hum_001;
    }

    public void setFra_hum_001(FRA_HUM_001 fra_hum_001) {
        this.fra_hum_001 = fra_hum_001;
    }

    public String getPesoFinal() {
        return pesoFinal;
    }

    public void setPesoFinal(String pesoFinal) {
        this.pesoFinal = pesoFinal;
    }
}
