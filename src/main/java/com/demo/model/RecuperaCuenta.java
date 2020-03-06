package com.demo.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.sql.Timestamp;

@Entity
public class RecuperaCuenta {

    @Id
    @GeneratedValue
    @Column(name = "Recupera_Id", nullable = false)
    private Long recuperaId;

    @Column(name = "correo", length = 250, nullable = false)
    private String correo;

    @Column(name = "codigo", length = 250, nullable = false)
    private String codigo;

    @Column(name = "created_at", length = 250, nullable = false)
    private Timestamp createdAt;

    public RecuperaCuenta() {
    }

    public RecuperaCuenta(String correo, String codigo, Timestamp createdAt) {
        this.correo = correo;
        this.codigo = codigo;
        this.createdAt = createdAt;
    }

    public Long getRecuperaId() {
        return recuperaId;
    }

    public void setRecuperaId(Long recuperaId) {
        this.recuperaId = recuperaId;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }
}
