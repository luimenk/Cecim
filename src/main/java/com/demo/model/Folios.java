package com.demo.model;

import javax.persistence.*;

@Entity
@Table()
public class Folios {

    @Id
    @GeneratedValue
    @Column(name = "Folio_Id", nullable = false)
    private Long folioId;

    @Column(name = "Nombre_Folio", length = 250, nullable = false)
    private String nombreFolio;

    @Column(name = "Consecutivo", length = 250, nullable = false)
    private String consecutivo;

    public Folios() {
    }

    public Folios(String nombreFolio, String consecutivo) {
        this.nombreFolio = nombreFolio;
        this.consecutivo = consecutivo;
    }

    public Long getFolioId() {
        return folioId;
    }

    public void setFolioId(Long folioId) {
        this.folioId = folioId;
    }

    public String getNombreFolio() {
        return nombreFolio;
    }

    public void setNombreFolio(String nombreFolio) {
        this.nombreFolio = nombreFolio;
    }

    public String getConsecutivo() {
        return consecutivo;
    }

    public void setConsecutivo(String consecutivo) {
        this.consecutivo = consecutivo;
    }
}
