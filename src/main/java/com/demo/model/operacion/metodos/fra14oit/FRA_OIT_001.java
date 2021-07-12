package com.demo.model.operacion.metodos.fra14oit;

import com.demo.model.operacion.MetodoMuestra;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Data
public class FRA_OIT_001 {

    @Id
    @GeneratedValue
    @Column(nullable = false)
    private Long idFRAOIT;

    private String folioTecnica;
    private String folioSolicitudServicioInterno;
    private String idInternoMuestra;
    private String fechaInicioAnalisis;
    private String fechaFinalAnalisis;
    private String temperatura;
    private String humedadRelativa;

    private String codigoDSC;
    private String codigoBalanza;
    private String pesom1;
    private String pesom2;
    private String posicionm1;
    private String posicionm2;

    private String purga;
    private String tpurga;
    private String tequilibrio;
    private String tiempoEquilibrio;
    private String dinamicaCal1;
    private String tasaCalentamiento;
    private String tiempoIsotermico1;
    private String tiempoIsotermico2;
    private String dinamicaEnf1;
    private String tasaEnfriamiento;
    private String temperaturaEmergencia;

    private String m1OIT;
    private String m2OIT;
    private String promedioOIT;

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
    private String numeroRepeticiones;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "Metodo_Muestra_Id")
    private MetodoMuestra metodoMuestra;

}
