package com.demo.model.operacion.metodos.fra11eat;

import com.demo.model.operacion.MetodoMuestra;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Data
public class FRA_EAT_001 {

    @Id
    @GeneratedValue
    @Column(nullable = false)
    private Long idFRAEAT;

    private String folioTecnica;
    private String folioSolicitudServicioInterno;
    private String idInternoMuestra;
    private String fechaInicioAnalisis;
    private String fechaFinalAnalisis;
    private String temperatura;
    private String humedadRelativa;

    private String codigoHornoConveccion;
    private String temperaturaHorno;
    private String tiempoCapturaFotografica;
    private String tiempoCiclo;
    private String descripcionMuestra;
    private String tipoProducto;
    private String tipoMaterial;
    private String caraAnalisis;
    private String aditivoBiodegradable;
    private String porcentajeInclusion;
    private String tipoSuperficie;
    private String especifique;

    private String tiempoTotalExposicion;
    private String totalFotografiasCapturadas;

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

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "Metodo_Muestra_Id")
    private MetodoMuestra metodoMuestra;
}
