package com.demo.model.operacion.metodos.fra20rter;

import com.demo.model.operacion.MetodoMuestra;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Data
public class FRA_RTER_001 {

    @Id
    @GeneratedValue
    @Column(nullable = false)
    private Long idFRARTER;

    private String folioTecnica;
    private String folioSolicitudServicioInterno;
    private String idInternoMuestra;
    private String fechaInicioAnalisis;
    private String fechaFinalAnalisis;
    private String temperatura;
    private String humedadRelativa;

    private String codigoEquipoUniversal;
    private String codigoMicrometro;
    private String distanciaEntreMordazas;
    private String velocidadDeformacion;
    private String anchoProbeta;

    private String promedioEspesorPromedioMD;
    private String promedioFuerzaFluenciatensiónMD;
    private String promedioElongacionRupturaMD;
    private String promedioResistenciaTensionMD;
    private String promedioPendienteMD;
    private String promedioCoeficienteCorrelacionPearsonMD;
    private String promedioModuloElasticoMD;

    private String promedioEspesorPromedioTD;
    private String promedioFuerzaFluenciatensiónTD;
    private String promedioElongacionRupturaTD;
    private String promedioResistenciaTensionTD;
    private String promedioPendienteTD;
    private String promedioCoeficienteCorrelacionPearsonTD;
    private String promedioModuloElasticoTD;

    private String tasaDeformacion;

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
