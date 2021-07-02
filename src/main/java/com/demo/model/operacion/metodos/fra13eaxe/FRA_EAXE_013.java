package com.demo.model.operacion.metodos.fra13eaxe;

import com.demo.model.operacion.MetodoMuestra;
import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class FRA_EAXE_013 {

    @Id
    @GeneratedValue
    @Column(nullable = false)
    private Long idFRAEAXE;

    private String folioTecnica;
    private String folioSolicitudServicioInterno;
    private String idInternoMuestra;
    private String fechaInicioAnalisis;
    private String fechaFinalAnalisis;
    private String temperatura;
    private String humedadRelativa;

    private String codigoCamaraXE;
    private String codigoRadiometro;
    private String irradiacion;
    private String temperaturaPanel;
    private String longitudOnda;
    private String cicloCondensacion;
    private String tiempoCiclo;
    private String cicloTomaFotografica;
    private String descripcionMuestra;
    private String tipoProducto;
    private String tipoMaterial;
    private String caraAnalisis;
    private String aditivoBiodegradable;
    private String porcentajeInclusion;
    private String tipoSuperficie;
    private String especifique;

    private String tiempoTotalExposicion;
    //private String totalFotografiasCapturadas;

    private String observaciones;
    private String realizo;
    private String rubricaRealizo;
    private String supervisor;

    private String estatus;
    private String cantidadModificaciones;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "Metodo_Muestra_Id")
    private MetodoMuestra metodoMuestra;
}
