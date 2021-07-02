package com.demo.model.operacion.metodos.fra13eaxe.datas;

import com.demo.model.operacion.metodos.fra13eaxe.FRA_EAXE_013;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Data
public class FRA_EAXE_013_DATA {

    @Id
    @GeneratedValue
    @Column(nullable = false)
    private Long idFRAEAXEDATA;

    private String tiempoExposicion;
    private String rubrica;
    private String nombreAnalista;
    private String pathImage;
    private String imagenSeleccionada;

    @CreationTimestamp
    private Timestamp createdAt;

    @UpdateTimestamp
    private Timestamp updatedAt;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "idfraeaxe")
    private FRA_EAXE_013 fra_eaxe_013;
}
