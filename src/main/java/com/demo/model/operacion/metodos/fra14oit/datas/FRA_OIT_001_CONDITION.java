package com.demo.model.operacion.metodos.fra14oit.datas;

import com.demo.model.operacion.metodos.fra14oit.FRA_OIT_001;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Data
public class FRA_OIT_001_CONDITION {

    @Id
    @GeneratedValue
    @Column(nullable = false)
    private Long idFRAOITCONDITION;



    private String tiempoPurga;
    private String temperatura2a;
    private String temperatura2b;
    private String temperatura3;
    private String tiempo3;
    private String temperatura4a;
    private String temperatura4b;
    private String tiempoIsotermico1;
    private String tiempoIsotermico2;
    private String temperatura9a;
    private String temperatura9b;
    private String tasaEnfriamiento;
    private String temperaturaEmergencia;

    @CreationTimestamp
    private Timestamp createdAt;

    @UpdateTimestamp
    private Timestamp updatedAt;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "idfraoit")
    private FRA_OIT_001 fra_oit_001;
}
