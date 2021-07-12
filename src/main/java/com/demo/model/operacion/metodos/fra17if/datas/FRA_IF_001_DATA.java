package com.demo.model.operacion.metodos.fra17if.datas;

import com.demo.model.operacion.metodos.fra17if.FRA_IF_001;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Data
public class FRA_IF_001_DATA {

    @Id
    @GeneratedValue
    @Column(nullable = false)
    private Long idFRAIFDATA;

    private String pesoFilamento;
    private String indiceFluidez;
    private String mfi;

    @CreationTimestamp
    private Timestamp createdAt;

    @UpdateTimestamp
    private Timestamp updatedAt;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "idfraif")
    private FRA_IF_001 fra_if_001;

}
