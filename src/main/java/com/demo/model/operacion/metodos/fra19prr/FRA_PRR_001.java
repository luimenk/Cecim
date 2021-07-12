package com.demo.model.operacion.metodos.fra19prr;

import com.demo.model.operacion.MetodoMuestra;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Data
public class FRA_PRR_001 {

    @Id
    @GeneratedValue
    @Column(nullable = false)
    private Long idFRAPRR;

    private String folioTecnica;
    private String folioSolicitudServicioInterno;
    private String idInternoMuestra;
    private String fechaInicioAnalisis;
    private String fechaFinalAnalisis;
    private String temperatura;
    private String humedadRelativa;

    private String codigoPendulo;
    private String codigoManometro;
    private String capacidadEquipo;
    private String prensaEnsayo;
    private String pesaCalibracion;
    
    private String promedioResistenciaRasgadoMD;
    private String promedioResistenciaRasgadoTD;

    private String desgarreOblicuioMD;
    private String desgarreOblicuioTD;

    private String minMD;
    private String minTD;
    private String maxMD;
    private String maxTD;
    private String desvEstandarMD;
    private String desvEstandarTD;
    private String espesorPromedioMD;
    private String espesorPromedioTD;

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