package com.demo.model;

import javax.persistence.*;

@Entity
public class DocumentOrdenServicio {
    @Id
    @GeneratedValue
    @Column(name = "DocumentOrdenServicio_Id", nullable = false)
    private Long ordenServicioId;

    @Column(name = "folioOrden", nullable = false)
    private String folioOrden;


    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.DETACH)
    @JoinColumn(name = "Client_Id")
    private Client client;

    @Column(name = "listaMuestras", nullable = false)
    private String listaMuestras;

    public DocumentOrdenServicio() {
    }

    public DocumentOrdenServicio(String folioOrden, Client client, String listaMuestras) {
        this.folioOrden = folioOrden;
        this.client = client;
        this.listaMuestras = listaMuestras;
    }

    public Long getOrdenServicioId() {
        return ordenServicioId;
    }

    public void setOrdenServicioId(Long ordenServicioId) {
        this.ordenServicioId = ordenServicioId;
    }

    public String getFolioOrden() {
        return folioOrden;
    }


    public void setFolioOrden(String folioOrden) {
        this.folioOrden = folioOrden;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public String getListaMuestras() {
        return listaMuestras;
    }

    public void setListaMuestras(String listaMuestras) {
        this.listaMuestras = listaMuestras;
    }

    @Override
    public String toString() {
        return "DocumentOrdenServicio{" +
                "ordenServicioId=" + ordenServicioId +
                ", folioOrden='" + folioOrden + '\'' +
                ", client=" + client +
                ", listaMuestras='" + listaMuestras + '\'' +
                '}';
    }
}
