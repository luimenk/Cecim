package com.demo.model.operacion.metodos;

import com.demo.model.operacion.MetodoMuestra;

import javax.persistence.*;

@Entity
public class FRA_RTER_001 {

    @Id
    @GeneratedValue
    @Column(nullable = false)
    private Long resistenciaTensionElongacionRupturaId;

    @Column(length = 30, nullable = false)
    private String folioSolicitudServicioInterno;

    @Column(length = 30, nullable = false)
    private String idInternoMuestra;

    @Column(length = 30, nullable = false)
    private String fechaInicioAnalisis;

    @Column(length = 30, nullable = false)
    private String fechaFinalAnalisis;

    @Column(length = 30, nullable = false)
    private String temperatura;

    @Column(length = 30, nullable = false)
    private String humedadRelativa;

    @Column(length = 30, nullable = false)
    private String codigoEquipoUniversal;

    @Column(length = 30, nullable = false)
    private String codigoMicrometro;

    @Column(length = 30, nullable = false)
    private String velocidadMordazas;

    @Column(length = 30, nullable = false)
    private String distanciaEntreMordazas;

    /**
     * Resultados dirección máquina (MD)
     **/

    @Column(length = 15, nullable = false)
    private String MDespesor1;

    @Column(length = 15, nullable = false)
    private String MDfuerzaFluencia1;

    @Column(length = 15, nullable = false)
    private String MDelongacionRuptura1;

    @Column(length = 15, nullable = false)
    private String MDresistenciaTension1;

    @Column(length = 15, nullable = false)
    private String MDmoduloElastico1;

    @Column(length = 15, nullable = false)
    private String MDespesor2;

    @Column(length = 15, nullable = false)
    private String MDfuerzaFluencia2;

    @Column(length = 15, nullable = false)
    private String MDelongacionRuptura2;

    @Column(length = 15, nullable = false)
    private String MDresistenciaTension2;

    @Column(length = 15, nullable = false)
    private String MDmoduloElastico2;

    @Column(length = 15, nullable = false)
    private String MDespesor3;

    @Column(length = 15, nullable = false)
    private String MDfuerzaFluencia3;

    @Column(length = 15, nullable = false)
    private String MDelongacionRuptura3;

    @Column(length = 15, nullable = false)
    private String MDresistenciaTension3;

    @Column(length = 15, nullable = false)
    private String MDmoduloElastico3;

    @Column(length = 15, nullable = false)
    private String MDespesor4;

    @Column(length = 15, nullable = false)
    private String MDfuerzaFluencia4;

    @Column(length = 15, nullable = false)
    private String MDelongacionRuptura4;

    @Column(length = 15, nullable = false)
    private String MDresistenciaTension4;

    @Column(length = 15, nullable = false)
    private String MDmoduloElastico4;

    @Column(length = 15, nullable = false)
    private String MDespesor5;

    @Column(length = 15, nullable = false)
    private String MDfuerzaFluencia5;

    @Column(length = 15, nullable = false)
    private String MDelongacionRuptura5;

    @Column(length = 15, nullable = false)
    private String MDresistenciaTension5;

    @Column(length = 15, nullable = false)
    private String MDmoduloElastico5;

    @Column(length = 15, nullable = false)
    private String MDpromedioEspesor;

    @Column(length = 15, nullable = false)
    private String MDpromedioFuerzaFluencia;

    @Column(length = 15, nullable = false)
    private String MDpromedioElongacionRuptura;

    @Column(length = 15, nullable = false)
    private String MDpromedioResistenciaTension;

    @Column(length = 15, nullable = false)
    private String MDpromedioModuloElastico;

    /**
     * Resultados dirección máquina (TD)
     **/

    @Column(length = 15, nullable = false)
    private String TDespesor1;

    @Column(length = 15, nullable = false)
    private String TDfuerzaFluencia1;

    @Column(length = 15, nullable = false)
    private String TDelongacionRuptura1;

    @Column(length = 15, nullable = false)
    private String TDresistenciaTension1;

    @Column(length = 15, nullable = false)
    private String TDmoduloElastico1;

    @Column(length = 15, nullable = false)
    private String TDespesor2;

    @Column(length = 15, nullable = false)
    private String TDfuerzaFluencia2;

    @Column(length = 15, nullable = false)
    private String TDelongacionRuptura2;

    @Column(length = 15, nullable = false)
    private String TDresistenciaTension2;

    @Column(length = 15, nullable = false)
    private String TDmoduloElastico2;

    @Column(length = 15, nullable = false)
    private String TDespesor3;

    @Column(length = 15, nullable = false)
    private String TDfuerzaFluencia3;

    @Column(length = 15, nullable = false)
    private String TDelongacionRuptura3;

    @Column(length = 15, nullable = false)
    private String TDresistenciaTension3;

    @Column(length = 15, nullable = false)
    private String TDmoduloElastico3;

    @Column(length = 15, nullable = false)
    private String TDespesor4;

    @Column(length = 15, nullable = false)
    private String TDfuerzaFluencia4;

    @Column(length = 15, nullable = false)
    private String TDelongacionRuptura4;

    @Column(length = 15, nullable = false)
    private String TDresistenciaTension4;

    @Column(length = 15, nullable = false)
    private String TDmoduloElastico4;

    @Column(length = 15, nullable = false)
    private String TDespesor5;

    @Column(length = 15, nullable = false)
    private String TDfuerzaFluencia5;

    @Column(length = 15, nullable = false)
    private String TDelongacionRuptura5;

    @Column(length = 15, nullable = false)
    private String TDresistenciaTension5;

    @Column(length = 15, nullable = false)
    private String TDmoduloElastico5;

    @Column(length = 15, nullable = false)
    private String TDpromedioEspesor;

    @Column(length = 15, nullable = false)
    private String TDpromedioFuerzaFluencia;

    @Column(length = 15, nullable = false)
    private String TDpromedioElongacionRuptura;

    @Column(length = 15, nullable = false)
    private String TDpromedioResistenciaTension;

    @Column(length = 15, nullable = false)
    private String TDpromedioModuloElastico;

    @Column(length = 250, nullable = false)
    private String observaciones;

    @Column(length = 250, nullable = false)
    private String realizo;

    @Column(length = 250, nullable = false)
    private String supervisor;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "Metodo_Muestra_Id")
    private MetodoMuestra metodoMuestra;

    public FRA_RTER_001() {
    }

    public FRA_RTER_001(Long resistenciaTensionElongacionRupturaId, String folioSolicitudServicioInterno, String idInternoMuestra, String fechaInicioAnalisis, String fechaFinalAnalisis, String temperatura, String humedadRelativa, String codigoEquipoUniversal, String codigoMicrometro, String velocidadMordazas, String distanciaEntreMordazas, String MDespesor1, String MDfuerzaFluencia1, String MDelongacionRuptura1, String MDresistenciaTension1, String MDmoduloElastico1, String MDespesor2, String MDfuerzaFluencia2, String MDelongacionRuptura2, String MDresistenciaTension2, String MDmoduloElastico2, String MDespesor3, String MDfuerzaFluencia3, String MDelongacionRuptura3, String MDresistenciaTension3, String MDmoduloElastico3, String MDespesor4, String MDfuerzaFluencia4, String MDelongacionRuptura4, String MDresistenciaTension4, String MDmoduloElastico4, String MDespesor5, String MDfuerzaFluencia5, String MDelongacionRuptura5, String MDresistenciaTension5, String MDmoduloElastico5, String MDpromedioEspesor, String MDpromedioFuerzaFluencia, String MDpromedioElongacionRuptura, String MDpromedioResistenciaTension, String MDpromedioModuloElastico, String TDespesor1, String TDfuerzaFluencia1, String TDelongacionRuptura1, String TDresistenciaTension1, String TDmoduloElastico1, String TDespesor2, String TDfuerzaFluencia2, String TDelongacionRuptura2, String TDresistenciaTension2, String TDmoduloElastico2, String TDespesor3, String TDfuerzaFluencia3, String TDelongacionRuptura3, String TDresistenciaTension3, String TDmoduloElastico3, String TDespesor4, String TDfuerzaFluencia4, String TDelongacionRuptura4, String TDresistenciaTension4, String TDmoduloElastico4, String TDespesor5, String TDfuerzaFluencia5, String TDelongacionRuptura5, String TDresistenciaTension5, String TDmoduloElastico5, String TDpromedioEspesor, String TDpromedioFuerzaFluencia, String TDpromedioElongacionRuptura, String TDpromedioResistenciaTension, String TDpromedioModuloElastico, String observaciones, String realizo, String supervisor, MetodoMuestra metodoMuestra) {
        this.resistenciaTensionElongacionRupturaId = resistenciaTensionElongacionRupturaId;
        this.folioSolicitudServicioInterno = folioSolicitudServicioInterno;
        this.idInternoMuestra = idInternoMuestra;
        this.fechaInicioAnalisis = fechaInicioAnalisis;
        this.fechaFinalAnalisis = fechaFinalAnalisis;
        this.temperatura = temperatura;
        this.humedadRelativa = humedadRelativa;
        this.codigoEquipoUniversal = codigoEquipoUniversal;
        this.codigoMicrometro = codigoMicrometro;
        this.velocidadMordazas = velocidadMordazas;
        this.distanciaEntreMordazas = distanciaEntreMordazas;
        this.MDespesor1 = MDespesor1;
        this.MDfuerzaFluencia1 = MDfuerzaFluencia1;
        this.MDelongacionRuptura1 = MDelongacionRuptura1;
        this.MDresistenciaTension1 = MDresistenciaTension1;
        this.MDmoduloElastico1 = MDmoduloElastico1;
        this.MDespesor2 = MDespesor2;
        this.MDfuerzaFluencia2 = MDfuerzaFluencia2;
        this.MDelongacionRuptura2 = MDelongacionRuptura2;
        this.MDresistenciaTension2 = MDresistenciaTension2;
        this.MDmoduloElastico2 = MDmoduloElastico2;
        this.MDespesor3 = MDespesor3;
        this.MDfuerzaFluencia3 = MDfuerzaFluencia3;
        this.MDelongacionRuptura3 = MDelongacionRuptura3;
        this.MDresistenciaTension3 = MDresistenciaTension3;
        this.MDmoduloElastico3 = MDmoduloElastico3;
        this.MDespesor4 = MDespesor4;
        this.MDfuerzaFluencia4 = MDfuerzaFluencia4;
        this.MDelongacionRuptura4 = MDelongacionRuptura4;
        this.MDresistenciaTension4 = MDresistenciaTension4;
        this.MDmoduloElastico4 = MDmoduloElastico4;
        this.MDespesor5 = MDespesor5;
        this.MDfuerzaFluencia5 = MDfuerzaFluencia5;
        this.MDelongacionRuptura5 = MDelongacionRuptura5;
        this.MDresistenciaTension5 = MDresistenciaTension5;
        this.MDmoduloElastico5 = MDmoduloElastico5;
        this.MDpromedioEspesor = MDpromedioEspesor;
        this.MDpromedioFuerzaFluencia = MDpromedioFuerzaFluencia;
        this.MDpromedioElongacionRuptura = MDpromedioElongacionRuptura;
        this.MDpromedioResistenciaTension = MDpromedioResistenciaTension;
        this.MDpromedioModuloElastico = MDpromedioModuloElastico;
        this.TDespesor1 = TDespesor1;
        this.TDfuerzaFluencia1 = TDfuerzaFluencia1;
        this.TDelongacionRuptura1 = TDelongacionRuptura1;
        this.TDresistenciaTension1 = TDresistenciaTension1;
        this.TDmoduloElastico1 = TDmoduloElastico1;
        this.TDespesor2 = TDespesor2;
        this.TDfuerzaFluencia2 = TDfuerzaFluencia2;
        this.TDelongacionRuptura2 = TDelongacionRuptura2;
        this.TDresistenciaTension2 = TDresistenciaTension2;
        this.TDmoduloElastico2 = TDmoduloElastico2;
        this.TDespesor3 = TDespesor3;
        this.TDfuerzaFluencia3 = TDfuerzaFluencia3;
        this.TDelongacionRuptura3 = TDelongacionRuptura3;
        this.TDresistenciaTension3 = TDresistenciaTension3;
        this.TDmoduloElastico3 = TDmoduloElastico3;
        this.TDespesor4 = TDespesor4;
        this.TDfuerzaFluencia4 = TDfuerzaFluencia4;
        this.TDelongacionRuptura4 = TDelongacionRuptura4;
        this.TDresistenciaTension4 = TDresistenciaTension4;
        this.TDmoduloElastico4 = TDmoduloElastico4;
        this.TDespesor5 = TDespesor5;
        this.TDfuerzaFluencia5 = TDfuerzaFluencia5;
        this.TDelongacionRuptura5 = TDelongacionRuptura5;
        this.TDresistenciaTension5 = TDresistenciaTension5;
        this.TDmoduloElastico5 = TDmoduloElastico5;
        this.TDpromedioEspesor = TDpromedioEspesor;
        this.TDpromedioFuerzaFluencia = TDpromedioFuerzaFluencia;
        this.TDpromedioElongacionRuptura = TDpromedioElongacionRuptura;
        this.TDpromedioResistenciaTension = TDpromedioResistenciaTension;
        this.TDpromedioModuloElastico = TDpromedioModuloElastico;
        this.observaciones = observaciones;
        this.realizo = realizo;
        this.supervisor = supervisor;
        this.metodoMuestra = metodoMuestra;
    }

    public Long getResistenciaTensionElongacionRupturaId() {
        return resistenciaTensionElongacionRupturaId;
    }

    public void setResistenciaTensionElongacionRupturaId(Long resistenciaTensionElongacionRupturaId) {
        this.resistenciaTensionElongacionRupturaId = resistenciaTensionElongacionRupturaId;
    }

    public String getFolioSolicitudServicioInterno() {
        return folioSolicitudServicioInterno;
    }

    public void setFolioSolicitudServicioInterno(String folioSolicitudServicioInterno) {
        this.folioSolicitudServicioInterno = folioSolicitudServicioInterno;
    }

    public String getIdInternoMuestra() {
        return idInternoMuestra;
    }

    public void setIdInternoMuestra(String idInternoMuestra) {
        this.idInternoMuestra = idInternoMuestra;
    }

    public String getFechaInicioAnalisis() {
        return fechaInicioAnalisis;
    }

    public void setFechaInicioAnalisis(String fechaInicioAnalisis) {
        this.fechaInicioAnalisis = fechaInicioAnalisis;
    }

    public String getFechaFinalAnalisis() {
        return fechaFinalAnalisis;
    }

    public void setFechaFinalAnalisis(String fechaFinalAnalisis) {
        this.fechaFinalAnalisis = fechaFinalAnalisis;
    }

    public String getTemperatura() {
        return temperatura;
    }

    public void setTemperatura(String temperatura) {
        this.temperatura = temperatura;
    }

    public String getHumedadRelativa() {
        return humedadRelativa;
    }

    public void setHumedadRelativa(String humedadRelativa) {
        this.humedadRelativa = humedadRelativa;
    }

    public String getCodigoEquipoUniversal() {
        return codigoEquipoUniversal;
    }

    public void setCodigoEquipoUniversal(String codigoEquipoUniversal) {
        this.codigoEquipoUniversal = codigoEquipoUniversal;
    }

    public String getCodigoMicrometro() {
        return codigoMicrometro;
    }

    public void setCodigoMicrometro(String codigoMicrometro) {
        this.codigoMicrometro = codigoMicrometro;
    }

    public String getVelocidadMordazas() {
        return velocidadMordazas;
    }

    public void setVelocidadMordazas(String velocidadMordazas) {
        this.velocidadMordazas = velocidadMordazas;
    }

    public String getDistanciaEntreMordazas() {
        return distanciaEntreMordazas;
    }

    public void setDistanciaEntreMordazas(String distanciaEntreMordazas) {
        this.distanciaEntreMordazas = distanciaEntreMordazas;
    }

    public String getMDespesor1() {
        return MDespesor1;
    }

    public void setMDespesor1(String MDespesor1) {
        this.MDespesor1 = MDespesor1;
    }

    public String getMDfuerzaFluencia1() {
        return MDfuerzaFluencia1;
    }

    public void setMDfuerzaFluencia1(String MDfuerzaFluencia1) {
        this.MDfuerzaFluencia1 = MDfuerzaFluencia1;
    }

    public String getMDelongacionRuptura1() {
        return MDelongacionRuptura1;
    }

    public void setMDelongacionRuptura1(String MDelongacionRuptura1) {
        this.MDelongacionRuptura1 = MDelongacionRuptura1;
    }

    public String getMDresistenciaTension1() {
        return MDresistenciaTension1;
    }

    public void setMDresistenciaTension1(String MDresistenciaTension1) {
        this.MDresistenciaTension1 = MDresistenciaTension1;
    }

    public String getMDmoduloElastico1() {
        return MDmoduloElastico1;
    }

    public void setMDmoduloElastico1(String MDmoduloElastico1) {
        this.MDmoduloElastico1 = MDmoduloElastico1;
    }

    public String getMDespesor2() {
        return MDespesor2;
    }

    public void setMDespesor2(String MDespesor2) {
        this.MDespesor2 = MDespesor2;
    }

    public String getMDfuerzaFluencia2() {
        return MDfuerzaFluencia2;
    }

    public void setMDfuerzaFluencia2(String MDfuerzaFluencia2) {
        this.MDfuerzaFluencia2 = MDfuerzaFluencia2;
    }

    public String getMDelongacionRuptura2() {
        return MDelongacionRuptura2;
    }

    public void setMDelongacionRuptura2(String MDelongacionRuptura2) {
        this.MDelongacionRuptura2 = MDelongacionRuptura2;
    }

    public String getMDresistenciaTension2() {
        return MDresistenciaTension2;
    }

    public void setMDresistenciaTension2(String MDresistenciaTension2) {
        this.MDresistenciaTension2 = MDresistenciaTension2;
    }

    public String getMDmoduloElastico2() {
        return MDmoduloElastico2;
    }

    public void setMDmoduloElastico2(String MDmoduloElastico2) {
        this.MDmoduloElastico2 = MDmoduloElastico2;
    }

    public String getMDespesor3() {
        return MDespesor3;
    }

    public void setMDespesor3(String MDespesor3) {
        this.MDespesor3 = MDespesor3;
    }

    public String getMDfuerzaFluencia3() {
        return MDfuerzaFluencia3;
    }

    public void setMDfuerzaFluencia3(String MDfuerzaFluencia3) {
        this.MDfuerzaFluencia3 = MDfuerzaFluencia3;
    }

    public String getMDelongacionRuptura3() {
        return MDelongacionRuptura3;
    }

    public void setMDelongacionRuptura3(String MDelongacionRuptura3) {
        this.MDelongacionRuptura3 = MDelongacionRuptura3;
    }

    public String getMDresistenciaTension3() {
        return MDresistenciaTension3;
    }

    public void setMDresistenciaTension3(String MDresistenciaTension3) {
        this.MDresistenciaTension3 = MDresistenciaTension3;
    }

    public String getMDmoduloElastico3() {
        return MDmoduloElastico3;
    }

    public void setMDmoduloElastico3(String MDmoduloElastico3) {
        this.MDmoduloElastico3 = MDmoduloElastico3;
    }

    public String getMDespesor4() {
        return MDespesor4;
    }

    public void setMDespesor4(String MDespesor4) {
        this.MDespesor4 = MDespesor4;
    }

    public String getMDfuerzaFluencia4() {
        return MDfuerzaFluencia4;
    }

    public void setMDfuerzaFluencia4(String MDfuerzaFluencia4) {
        this.MDfuerzaFluencia4 = MDfuerzaFluencia4;
    }

    public String getMDelongacionRuptura4() {
        return MDelongacionRuptura4;
    }

    public void setMDelongacionRuptura4(String MDelongacionRuptura4) {
        this.MDelongacionRuptura4 = MDelongacionRuptura4;
    }

    public String getMDresistenciaTension4() {
        return MDresistenciaTension4;
    }

    public void setMDresistenciaTension4(String MDresistenciaTension4) {
        this.MDresistenciaTension4 = MDresistenciaTension4;
    }

    public String getMDmoduloElastico4() {
        return MDmoduloElastico4;
    }

    public void setMDmoduloElastico4(String MDmoduloElastico4) {
        this.MDmoduloElastico4 = MDmoduloElastico4;
    }

    public String getMDespesor5() {
        return MDespesor5;
    }

    public void setMDespesor5(String MDespesor5) {
        this.MDespesor5 = MDespesor5;
    }

    public String getMDfuerzaFluencia5() {
        return MDfuerzaFluencia5;
    }

    public void setMDfuerzaFluencia5(String MDfuerzaFluencia5) {
        this.MDfuerzaFluencia5 = MDfuerzaFluencia5;
    }

    public String getMDelongacionRuptura5() {
        return MDelongacionRuptura5;
    }

    public void setMDelongacionRuptura5(String MDelongacionRuptura5) {
        this.MDelongacionRuptura5 = MDelongacionRuptura5;
    }

    public String getMDresistenciaTension5() {
        return MDresistenciaTension5;
    }

    public void setMDresistenciaTension5(String MDresistenciaTension5) {
        this.MDresistenciaTension5 = MDresistenciaTension5;
    }

    public String getMDmoduloElastico5() {
        return MDmoduloElastico5;
    }

    public void setMDmoduloElastico5(String MDmoduloElastico5) {
        this.MDmoduloElastico5 = MDmoduloElastico5;
    }

    public String getMDpromedioEspesor() {
        return MDpromedioEspesor;
    }

    public void setMDpromedioEspesor(String MDpromedioEspesor) {
        this.MDpromedioEspesor = MDpromedioEspesor;
    }

    public String getMDpromedioFuerzaFluencia() {
        return MDpromedioFuerzaFluencia;
    }

    public void setMDpromedioFuerzaFluencia(String MDpromedioFuerzaFluencia) {
        this.MDpromedioFuerzaFluencia = MDpromedioFuerzaFluencia;
    }

    public String getMDpromedioElongacionRuptura() {
        return MDpromedioElongacionRuptura;
    }

    public void setMDpromedioElongacionRuptura(String MDpromedioElongacionRuptura) {
        this.MDpromedioElongacionRuptura = MDpromedioElongacionRuptura;
    }

    public String getMDpromedioResistenciaTension() {
        return MDpromedioResistenciaTension;
    }

    public void setMDpromedioResistenciaTension(String MDpromedioResistenciaTension) {
        this.MDpromedioResistenciaTension = MDpromedioResistenciaTension;
    }

    public String getMDpromedioModuloElastico() {
        return MDpromedioModuloElastico;
    }

    public void setMDpromedioModuloElastico(String MDpromedioModuloElastico) {
        this.MDpromedioModuloElastico = MDpromedioModuloElastico;
    }

    public String getTDespesor1() {
        return TDespesor1;
    }

    public void setTDespesor1(String TDespesor1) {
        this.TDespesor1 = TDespesor1;
    }

    public String getTDfuerzaFluencia1() {
        return TDfuerzaFluencia1;
    }

    public void setTDfuerzaFluencia1(String TDfuerzaFluencia1) {
        this.TDfuerzaFluencia1 = TDfuerzaFluencia1;
    }

    public String getTDelongacionRuptura1() {
        return TDelongacionRuptura1;
    }

    public void setTDelongacionRuptura1(String TDelongacionRuptura1) {
        this.TDelongacionRuptura1 = TDelongacionRuptura1;
    }

    public String getTDresistenciaTension1() {
        return TDresistenciaTension1;
    }

    public void setTDresistenciaTension1(String TDresistenciaTension1) {
        this.TDresistenciaTension1 = TDresistenciaTension1;
    }

    public String getTDmoduloElastico1() {
        return TDmoduloElastico1;
    }

    public void setTDmoduloElastico1(String TDmoduloElastico1) {
        this.TDmoduloElastico1 = TDmoduloElastico1;
    }

    public String getTDespesor2() {
        return TDespesor2;
    }

    public void setTDespesor2(String TDespesor2) {
        this.TDespesor2 = TDespesor2;
    }

    public String getTDfuerzaFluencia2() {
        return TDfuerzaFluencia2;
    }

    public void setTDfuerzaFluencia2(String TDfuerzaFluencia2) {
        this.TDfuerzaFluencia2 = TDfuerzaFluencia2;
    }

    public String getTDelongacionRuptura2() {
        return TDelongacionRuptura2;
    }

    public void setTDelongacionRuptura2(String TDelongacionRuptura2) {
        this.TDelongacionRuptura2 = TDelongacionRuptura2;
    }

    public String getTDresistenciaTension2() {
        return TDresistenciaTension2;
    }

    public void setTDresistenciaTension2(String TDresistenciaTension2) {
        this.TDresistenciaTension2 = TDresistenciaTension2;
    }

    public String getTDmoduloElastico2() {
        return TDmoduloElastico2;
    }

    public void setTDmoduloElastico2(String TDmoduloElastico2) {
        this.TDmoduloElastico2 = TDmoduloElastico2;
    }

    public String getTDespesor3() {
        return TDespesor3;
    }

    public void setTDespesor3(String TDespesor3) {
        this.TDespesor3 = TDespesor3;
    }

    public String getTDfuerzaFluencia3() {
        return TDfuerzaFluencia3;
    }

    public void setTDfuerzaFluencia3(String TDfuerzaFluencia3) {
        this.TDfuerzaFluencia3 = TDfuerzaFluencia3;
    }

    public String getTDelongacionRuptura3() {
        return TDelongacionRuptura3;
    }

    public void setTDelongacionRuptura3(String TDelongacionRuptura3) {
        this.TDelongacionRuptura3 = TDelongacionRuptura3;
    }

    public String getTDresistenciaTension3() {
        return TDresistenciaTension3;
    }

    public void setTDresistenciaTension3(String TDresistenciaTension3) {
        this.TDresistenciaTension3 = TDresistenciaTension3;
    }

    public String getTDmoduloElastico3() {
        return TDmoduloElastico3;
    }

    public void setTDmoduloElastico3(String TDmoduloElastico3) {
        this.TDmoduloElastico3 = TDmoduloElastico3;
    }

    public String getTDespesor4() {
        return TDespesor4;
    }

    public void setTDespesor4(String TDespesor4) {
        this.TDespesor4 = TDespesor4;
    }

    public String getTDfuerzaFluencia4() {
        return TDfuerzaFluencia4;
    }

    public void setTDfuerzaFluencia4(String TDfuerzaFluencia4) {
        this.TDfuerzaFluencia4 = TDfuerzaFluencia4;
    }

    public String getTDelongacionRuptura4() {
        return TDelongacionRuptura4;
    }

    public void setTDelongacionRuptura4(String TDelongacionRuptura4) {
        this.TDelongacionRuptura4 = TDelongacionRuptura4;
    }

    public String getTDresistenciaTension4() {
        return TDresistenciaTension4;
    }

    public void setTDresistenciaTension4(String TDresistenciaTension4) {
        this.TDresistenciaTension4 = TDresistenciaTension4;
    }

    public String getTDmoduloElastico4() {
        return TDmoduloElastico4;
    }

    public void setTDmoduloElastico4(String TDmoduloElastico4) {
        this.TDmoduloElastico4 = TDmoduloElastico4;
    }

    public String getTDespesor5() {
        return TDespesor5;
    }

    public void setTDespesor5(String TDespesor5) {
        this.TDespesor5 = TDespesor5;
    }

    public String getTDfuerzaFluencia5() {
        return TDfuerzaFluencia5;
    }

    public void setTDfuerzaFluencia5(String TDfuerzaFluencia5) {
        this.TDfuerzaFluencia5 = TDfuerzaFluencia5;
    }

    public String getTDelongacionRuptura5() {
        return TDelongacionRuptura5;
    }

    public void setTDelongacionRuptura5(String TDelongacionRuptura5) {
        this.TDelongacionRuptura5 = TDelongacionRuptura5;
    }

    public String getTDresistenciaTension5() {
        return TDresistenciaTension5;
    }

    public void setTDresistenciaTension5(String TDresistenciaTension5) {
        this.TDresistenciaTension5 = TDresistenciaTension5;
    }

    public String getTDmoduloElastico5() {
        return TDmoduloElastico5;
    }

    public void setTDmoduloElastico5(String TDmoduloElastico5) {
        this.TDmoduloElastico5 = TDmoduloElastico5;
    }

    public String getTDpromedioEspesor() {
        return TDpromedioEspesor;
    }

    public void setTDpromedioEspesor(String TDpromedioEspesor) {
        this.TDpromedioEspesor = TDpromedioEspesor;
    }

    public String getTDpromedioFuerzaFluencia() {
        return TDpromedioFuerzaFluencia;
    }

    public void setTDpromedioFuerzaFluencia(String TDpromedioFuerzaFluencia) {
        this.TDpromedioFuerzaFluencia = TDpromedioFuerzaFluencia;
    }

    public String getTDpromedioElongacionRuptura() {
        return TDpromedioElongacionRuptura;
    }

    public void setTDpromedioElongacionRuptura(String TDpromedioElongacionRuptura) {
        this.TDpromedioElongacionRuptura = TDpromedioElongacionRuptura;
    }

    public String getTDpromedioResistenciaTension() {
        return TDpromedioResistenciaTension;
    }

    public void setTDpromedioResistenciaTension(String TDpromedioResistenciaTension) {
        this.TDpromedioResistenciaTension = TDpromedioResistenciaTension;
    }

    public String getTDpromedioModuloElastico() {
        return TDpromedioModuloElastico;
    }

    public void setTDpromedioModuloElastico(String TDpromedioModuloElastico) {
        this.TDpromedioModuloElastico = TDpromedioModuloElastico;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    public String getRealizo() {
        return realizo;
    }

    public void setRealizo(String realizo) {
        this.realizo = realizo;
    }

    public String getSupervisor() {
        return supervisor;
    }

    public void setSupervisor(String supervisor) {
        this.supervisor = supervisor;
    }

    public MetodoMuestra getMetodoMuestra() {
        return metodoMuestra;
    }

    public void setMetodoMuestra(MetodoMuestra metodoMuestra) {
        this.metodoMuestra = metodoMuestra;
    }
}
