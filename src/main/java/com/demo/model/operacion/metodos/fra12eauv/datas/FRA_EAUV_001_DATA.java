package com.demo.model.operacion.metodos.fra12eauv.datas;

import com.demo.model.operacion.metodos.fra12eauv.FRA_EAUV_001;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Data
public class FRA_EAUV_001_DATA {

    @Id
    @GeneratedValue
    @Column(nullable = false)
    private Long idFRAEAUVDATA;

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
    @JoinColumn(name = "idfraeauv")
    private FRA_EAUV_001 fra_eauv_001;
}
