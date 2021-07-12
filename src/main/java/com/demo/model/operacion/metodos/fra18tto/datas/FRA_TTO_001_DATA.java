package com.demo.model.operacion.metodos.fra18tto.datas;

import com.demo.model.operacion.metodos.fra18tto.FRA_TTO_001;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Data
public class FRA_TTO_001_DATA {

    @Id
    @GeneratedValue
    @Column(nullable = false)
    private Long idFRATTODATA;

    private String e1;
    private String e2;
    private String e3;
    private String e4;
    private String e5;
    private String promedio;

    private String cc;
    private String mol;

    @CreationTimestamp
    private Timestamp createdAt;

    @UpdateTimestamp
    private Timestamp updatedAt;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "idfratto")
    private FRA_TTO_001 fra_tto_001;


}
