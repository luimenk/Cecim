package com.demo.model.operacion.metodos.fra11eat.datas;

import com.demo.model.operacion.metodos.fra11eat.FRA_EAT_001;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Data
public class FRA_EAT_001_DATA {

    @Id
    @GeneratedValue
    @Column(nullable = false)
    private Long idFRAEATDATA;

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
    @JoinColumn(name = "idfraeat")
    private FRA_EAT_001 fra_eat_001;
}
