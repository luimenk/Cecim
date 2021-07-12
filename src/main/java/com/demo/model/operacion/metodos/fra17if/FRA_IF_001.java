package com.demo.model.operacion.metodos.fra17if;

import com.demo.model.operacion.MetodoMuestra;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Data
public class FRA_IF_001 {

    @Id
    @GeneratedValue
    @Column(nullable = false)
    private Long idFRAIF;

    private String folioTecnica;
    private String folioSolicitudServicioInterno;
    private String idInternoMuestra;
    private String fechaInicioAnalisis;
    private String fechaFinalAnalisis;
    private String temperatura;
    private String humedadRelativa;

    private String codigoBalanzaAnalitica;
    private String codigoPlastometro;
    private String temperaturaEnsayo;
    private String pesaEnsayo;
    private String tiempoCorte;

    private String tipoMaterial;
    private String formaFisicaMaterial;
    private String indiceFuidez;

    private String promedioPesoFilamento;
    private String promedioIndiceFluidez;
    private String promedioMFI;

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
