package com.demo.model.operacion.metodos.fra19prr.datas;

import com.demo.model.operacion.metodos.fra19prr.FRA_PRR_001;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Data
public class FRA_PRR_001_DATA_01 {

    @Id
    @GeneratedValue
    @Column(nullable = false)
    private Long idFRAPRRDATA01;

    //Valores definidos para "MD"
    private String espesor1;
    private String espesor2;
    private String espesor3;
    private String espesorPromedio;
    private String resistenciaRasgado;
    private String desgarreOblicuo;

    @CreationTimestamp
    private Timestamp createdAt;

    @UpdateTimestamp
    private Timestamp updatedAt;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "idfraprr")
    private FRA_PRR_001 fra_prr_001;
}
