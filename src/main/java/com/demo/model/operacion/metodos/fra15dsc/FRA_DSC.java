package com.demo.model.operacion.metodos.fra15dsc;

import com.demo.model.operacion.MetodoMuestra;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Data
public class FRA_DSC {

    @Id
    @GeneratedValue
    @Column(nullable = false)
    private Long idFRADSC;

    private String folioTecnica;
    private String folioSolicitudServicioInterno;
    private String idInternoMuestra;
    private String fechaInicioAnalisis;
    private String fechaFinalAnalisis;
    private String temperatura;
    private String humedadRelativa;
    
    private String codigoDSC;
    private String codigoBalanza;
    private String peso;
    private String posicionPortadorMuestra;

    private String inicio;
    private String dinamicaCal1;
    private String tiempoIsotermico1;
    private String dinamicaEnf1;
    private String tiempoIsotermico2;
    private String dinamicaCal2;
    private String tiempoIsotermico3;
    private String dinamicaEnf2;
    private String temperaturaEmergencia;

    private String tasaCalentamiento;
    private String tasaEnfriamiento;

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
