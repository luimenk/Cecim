package com.demo.model.operacion.metodos.fra06ncp;

import com.demo.model.operacion.MetodoMuestra;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Data
public class FRA_NCP_001 {

    @Id
    @GeneratedValue
    @Column(nullable = false)
    private Long idFRANCP;

    private String folioTecnica;
    private String folioSolicitudServicioInterno;
    private String idInternoMuestra;
    private String fechaInicioAnalisis;
    private String fechaFinalAnalisis;
    private String temperatura;
    private String humedadRelativa;

    private String codigoMicrometro;
    private String codigoParrillaCalentamiento;
    private String codigoMicroscopio;
    private String lente;

    private String espesorTotalPromedioMM1;
    private String espesorTotalPromedioMM2;

    private String espesorTotalPromedioUM1;
    private String espesorTotalPromedioUM2;

    private String numeroTotalCapas1;
    private String numeroTotalCapas2;

    private String espesorTotalMicroscopia1;
    private String espesorTotalMicroscopia2;

    private String observaciones;
    private String realizo;
    private String rubricaRealizo;
    private String supervisor;

    private String estatus;
    private String cantidadModificaciones;

    private String muestraEnReporte;

    @CreationTimestamp
    private Timestamp createdAt;

    @UpdateTimestamp
    private Timestamp updatedAt;

    private String pathImagen;
    private String pathImagen2;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "Metodo_Muestra_Id")
    private MetodoMuestra metodoMuestra;
}
