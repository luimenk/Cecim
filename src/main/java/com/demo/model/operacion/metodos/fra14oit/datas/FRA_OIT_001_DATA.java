package com.demo.model.operacion.metodos.fra14oit.datas;

import com.demo.model.operacion.metodos.fra14oit.FRA_OIT_001;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Data
public class FRA_OIT_001_DATA {

    @Id
    @GeneratedValue
    @Column(nullable = false)
    private Long idFRAOITDATA;

    private String noDobleces;
    private String e1;
    private String e2;
    private String e3;
    private String espesorPromedio;

    @CreationTimestamp
    private Timestamp createdAt;

    @UpdateTimestamp
    private Timestamp updatedAt;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "idfraoit")
    private FRA_OIT_001 fra_oit_001;

}
