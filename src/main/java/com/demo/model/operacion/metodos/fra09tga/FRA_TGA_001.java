package com.demo.model.operacion.metodos.fra09tga;

import com.demo.model.operacion.MetodoMuestra;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Data
public class FRA_TGA_001 {

    @Id
    @GeneratedValue
    @Column(nullable = false)
    private Long idFRATGA;

    private String folioTecnica;
    private String folioSolicitudServicioInterno;
    private String idInternoMuestra;
    private String fechaInicioAnalisis;
    private String fechaFinalAnalisis;
    private String temperatura;
    private String humedadRelativa;

    private String codigoTGA;
    private String codigoBalanza;
    private String gasPrueba;
    private String gasProteccion;
    private String peso;
    private String posicionPortadorMuestra;
    private String tipoMaterial;

    //Condiciones
    private String purga;
    private String inicio;
    private String dinamicaCal1;
    private String dinamicaCal2;
    private String tasaCalentamiento;
    private String dinamicaEnf1;
    private String tasaEnfriamiento;
    private String temperaturaEmergencia;
    private String tipoAtmosfera1;
    private String tipoAtmosfera2;
    private String tiempoPurga;
    private String dinamica;

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