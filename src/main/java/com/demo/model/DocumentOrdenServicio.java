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

    @Column(name = "fecha_orden_servicio", nullable = false)
    private String fechaOrdenServicio;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "Client_Id")
    private Client client;

    @Column(name = "asesoria", nullable = true)
    private boolean asesoria;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "User_Id")
    private AppUser appUser;

    public DocumentOrdenServicio() {
    }

    public DocumentOrdenServicio(Long ordenServicioId, String folioOrden, String fechaOrdenServicio, Client client, boolean asesoria, AppUser appUser) {
        this.ordenServicioId = ordenServicioId;
        this.folioOrden = folioOrden;
        this.fechaOrdenServicio = fechaOrdenServicio;
        this.client = client;
        this.asesoria = asesoria;
        this.appUser = appUser;
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

    public String getFechaOrdenServicio() {
        return fechaOrdenServicio;
    }

    public void setFechaOrdenServicio(String fechaOrdenServicio) {
        this.fechaOrdenServicio = fechaOrdenServicio;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public boolean isAsesoria() {
        return asesoria;
    }

    public void setAsesoria(boolean asesoria) {
        this.asesoria = asesoria;
    }

    public AppUser getAppUser() {
        return appUser;
    }

    public void setAppUser(AppUser appUser) {
        this.appUser = appUser;
    }
}
