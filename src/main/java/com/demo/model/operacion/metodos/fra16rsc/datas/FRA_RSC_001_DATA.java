package com.demo.model.operacion.metodos.fra16rsc.datas;

import com.demo.model.operacion.metodos.fra16rsc.FRA_RSC_001;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Data
public class FRA_RSC_001_DATA {

    @Id
    @GeneratedValue
    @Column(nullable = false)
    private Long idFRARSCDATA;

    private String temperatura;

    private String fuerzaSello1;
    private String desviacionEstandar1;
    private String modoFalla1;

    private String fuerzaSello2;
    private String desviacionEstandar2;
    private String modoFalla2;

    private String fuerzaSello3;
    private String desviacionEstandar3;
    private String modoFalla3;

    @CreationTimestamp
    private Timestamp createdAt;

    @UpdateTimestamp
    private Timestamp updatedAt;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "idfrarsc")
    private FRA_RSC_001 fra_rsc_001;
}
