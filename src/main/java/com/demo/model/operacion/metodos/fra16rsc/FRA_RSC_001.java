package com.demo.model.operacion.metodos.fra16rsc;

import com.demo.model.operacion.MetodoMuestra;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Data
public class FRA_RSC_001 {

    @Id
    @GeneratedValue
    @Column(nullable = false)
    private Long idFRARSC;

    private String folioTecnica;
    private String folioSolicitudServicioInterno;
    private String idInternoMuestra;
    private String fechaInicioAnalisis;
    private String fechaFinalAnalisis;
    private String temperatura;
    private String humedadRelativa;

    private String codigoLaboratorio;
    private String codigoMicrometro;

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

    private String promedioFuerzaSello1;
    private String promedioDesvEstandar1;
    private String promedioFuerzaSello2;
    private String promedioDesvEstandar2;
    private String promedioFuerzaSello3;
    private String promedioDesvEstandar3;
    private String promedioFuerzaSello4;
    private String promedioDesvEstandar4;
    private String promedioFuerzaSello5;
    private String promedioDesvEstandar5;

    private String observaciones;
    private String realizo;
    private String rubricaRealizo;
    private String supervisor;

    private String estatus;
    private String cantidadModificaciones;

    @CreationTimestamp
    private Timestamp createdAt;

    @UpdateTimestamp
    private Timestamp updatedAt;

    private String pathImagen;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "Metodo_Muestra_Id")
    private MetodoMuestra metodoMuestra;
}
