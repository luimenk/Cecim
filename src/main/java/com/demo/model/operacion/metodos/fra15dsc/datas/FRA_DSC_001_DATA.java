package com.demo.model.operacion.metodos.fra15dsc.datas;

import com.demo.model.operacion.metodos.fra15dsc.FRA_DSC;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Data
public class FRA_DSC_001_DATA {

    @Id
    @GeneratedValue
    @Column(nullable = false)
    private Long idFRADSCDATA;

    private String etapa;
    private String temperaturaTransicion;
    private String temperaturaFusion;
    private String calorFusion;
    private String transicionTermica;
    private String temperaturaCristalizacion;
    private String calorCristalizacion;
    private String transmisionTermica;

    @CreationTimestamp
    private Timestamp createdAt;

    @UpdateTimestamp
    private Timestamp updatedAt;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "idfradsc")
    private FRA_DSC fra_dsc;

}
