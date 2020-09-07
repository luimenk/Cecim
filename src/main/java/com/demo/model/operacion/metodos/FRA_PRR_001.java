package com.demo.model.operacion.metodos;

import javax.persistence.*;

@Entity
public class FRA_PRR_001 {

    @Id
    @GeneratedValue
    @Column(nullable = false)
    private Long idFRAPRR;

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
    private String codigoPendulo;

    @Column(length = 30, nullable = false)
    private String codigoManometro;

    @Column(length = 30, nullable = false)
    private String prensaEnsayo;

    /**
     * Resultados dirección corte (MD)
     **/

    @Column(length = 15, nullable = false)
    private String MDespesor11;

    @Column(length = 15, nullable = false)
    private String MDespesor12;

    @Column(length = 15, nullable = false)
    private String MDespesor13;

    @Column(length = 15, nullable = false)
    private String MDEspesorPromedio1;

    @Column(length = 15, nullable = false)
    private String MDResistenciaRasgado1;

    @Column(length = 15, nullable = false)
    private String MDespesor21;

    @Column(length = 15, nullable = false)
    private String MDespesor22;

    @Column(length = 15, nullable = false)
    private String MDespesor23;

    @Column(length = 15, nullable = false)
    private String MDEspesorPromedio2;

    @Column(length = 15, nullable = false)
    private String MDResistenciaRasgado2;

    @Column(length = 15, nullable = false)
    private String MDespesor31;

    @Column(length = 15, nullable = false)
    private String MDespesor32;

    @Column(length = 15, nullable = false)
    private String MDespesor33;

    @Column(length = 15, nullable = false)
    private String MDEspesorPromedio3;

    @Column(length = 15, nullable = false)
    private String MDResistenciaRasgado3;

    @Column(length = 15, nullable = false)
    private String MDespesor41;

    @Column(length = 15, nullable = false)
    private String MDespesor42;

    @Column(length = 15, nullable = false)
    private String MDespesor43;

    @Column(length = 15, nullable = false)
    private String MDEspesorPromedio4;

    @Column(length = 15, nullable = false)
    private String MDResistenciaRasgado4;

    @Column(length = 15, nullable = false)
    private String MDespesor51;

    @Column(length = 15, nullable = false)
    private String MDespesor52;

    @Column(length = 15, nullable = false)
    private String MDespesor53;

    @Column(length = 15, nullable = false)
    private String MDEspesorPromedio5;

    @Column(length = 15, nullable = false)
    private String MDResistenciaRasgado5;

    @Column(length = 15, nullable = false)
    private String MDespesor61;

    @Column(length = 15, nullable = false)
    private String MDespesor62;

    @Column(length = 15, nullable = false)
    private String MDespesor63;

    @Column(length = 15, nullable = false)
    private String MDEspesorPromedio6;

    @Column(length = 15, nullable = false)
    private String MDResistenciaRasgado6;

    @Column(length = 15, nullable = false)
    private String MDespesor71;

    @Column(length = 15, nullable = false)
    private String MDespesor72;

    @Column(length = 15, nullable = false)
    private String MDespesor73;

    @Column(length = 15, nullable = false)
    private String MDEspesorPromedio7;

    @Column(length = 15, nullable = false)
    private String MDResistenciaRasgado7;

    @Column(length = 15, nullable = false)
    private String MDespesor81;

    @Column(length = 15, nullable = false)
    private String MDespesor82;

    @Column(length = 15, nullable = false)
    private String MDespesor83;

    @Column(length = 15, nullable = false)
    private String MDEspesorPromedio8;

    @Column(length = 15, nullable = false)
    private String MDResistenciaRasgado8;

    @Column(length = 15, nullable = false)
    private String MDespesor91;

    @Column(length = 15, nullable = false)
    private String MDespesor92;

    @Column(length = 15, nullable = false)
    private String MDespesor93;

    @Column(length = 15, nullable = false)
    private String MDEspesorPromedio9;

    @Column(length = 15, nullable = false)
    private String MDResistenciaRasgado9;

    @Column(length = 15, nullable = false)
    private String MDespesor101;

    @Column(length = 15, nullable = false)
    private String MDespesor102;

    @Column(length = 15, nullable = false)
    private String MDespesor103;

    @Column(length = 15, nullable = false)
    private String MDEspesorPromedio10;

    @Column(length = 15, nullable = false)
    private String MDResistenciaRasgado10;

    @Column(length = 15, nullable = false)
    private String MDPromedio;

    /**
     * Resultados dirección corte (MD)
     **/

    @Column(length = 15, nullable = false)
    private String TDespesor11;

    @Column(length = 15, nullable = false)
    private String TDespesor12;

    @Column(length = 15, nullable = false)
    private String TDespesor13;

    @Column(length = 15, nullable = false)
    private String TDEspesorPromedio1;

    @Column(length = 15, nullable = false)
    private String TDResistenciaRasgado1;

    @Column(length = 15, nullable = false)
    private String TDespesor21;

    @Column(length = 15, nullable = false)
    private String TDespesor22;

    @Column(length = 15, nullable = false)
    private String TDespesor23;

    @Column(length = 15, nullable = false)
    private String TDEspesorPromedio2;

    @Column(length = 15, nullable = false)
    private String TDResistenciaRasgado2;

    @Column(length = 15, nullable = false)
    private String TDespesor31;

    @Column(length = 15, nullable = false)
    private String TDespesor32;

    @Column(length = 15, nullable = false)
    private String TDespesor33;

    @Column(length = 15, nullable = false)
    private String TDEspesorPromedio3;

    @Column(length = 15, nullable = false)
    private String TDResistenciaRasgado3;

    @Column(length = 15, nullable = false)
    private String TDespesor41;

    @Column(length = 15, nullable = false)
    private String TDespesor42;

    @Column(length = 15, nullable = false)
    private String TDespesor43;

    @Column(length = 15, nullable = false)
    private String TDEspesorPromedio4;

    @Column(length = 15, nullable = false)
    private String TDResistenciaRasgado4;

    @Column(length = 15, nullable = false)
    private String TDespesor51;

    @Column(length = 15, nullable = false)
    private String TDespesor52;

    @Column(length = 15, nullable = false)
    private String TDespesor53;

    @Column(length = 15, nullable = false)
    private String TDEspesorPromedio5;

    @Column(length = 15, nullable = false)
    private String TDResistenciaRasgado5;

    @Column(length = 15, nullable = false)
    private String TDespesor61;

    @Column(length = 15, nullable = false)
    private String TDespesor62;

    @Column(length = 15, nullable = false)
    private String TDespesor63;

    @Column(length = 15, nullable = false)
    private String TDEspesorPromedio6;

    @Column(length = 15, nullable = false)
    private String TDResistenciaRasgado6;

    @Column(length = 15, nullable = false)
    private String TDespesor71;

    @Column(length = 15, nullable = false)
    private String TDespesor72;

    @Column(length = 15, nullable = false)
    private String TDespesor73;

    @Column(length = 15, nullable = false)
    private String TDEspesorPromedio7;

    @Column(length = 15, nullable = false)
    private String TDResistenciaRasgado7;

    @Column(length = 15, nullable = false)
    private String TDespesor81;

    @Column(length = 15, nullable = false)
    private String TDespesor82;

    @Column(length = 15, nullable = false)
    private String TDespesor83;

    @Column(length = 15, nullable = false)
    private String TDEspesorPromedio8;

    @Column(length = 15, nullable = false)
    private String TDResistenciaRasgado8;

    @Column(length = 15, nullable = false)
    private String TDespesor91;

    @Column(length = 15, nullable = false)
    private String TDespesor92;

    @Column(length = 15, nullable = false)
    private String TDespesor93;

    @Column(length = 15, nullable = false)
    private String TDEspesorPromedio9;

    @Column(length = 15, nullable = false)
    private String TDResistenciaRasgado9;

    @Column(length = 15, nullable = false)
    private String TDespesor101;

    @Column(length = 15, nullable = false)
    private String TDespesor102;

    @Column(length = 15, nullable = false)
    private String TDespesor103;

    @Column(length = 15, nullable = false)
    private String TDEspesorPromedio10;

    @Column(length = 15, nullable = false)
    private String TDResistenciaRasgado10;

    @Column(length = 15, nullable = false)
    private String TDPromedio;

    /** Generales **/
    @Column(length = 250, nullable = false)
    private String observaciones;

    @Column(length = 250, nullable = false)
    private String realizo;

    @Column(length = 250, nullable = false)
    private String supervisor;

    public FRA_PRR_001() {
    }

    public FRA_PRR_001(Long idFRAPRR, String folioSolicitudServicioInterno, String idInternoMuestra, String fechaInicioAnalisis, String fechaFinalAnalisis, String temperatura, String humedadRelativa, String codigoPendulo, String codigoManometro, String prensaEnsayo, String MDespesor11, String MDespesor12, String MDespesor13, String MDEspesorPromedio1, String MDResistenciaRasgado1, String MDespesor21, String MDespesor22, String MDespesor23, String MDEspesorPromedio2, String MDResistenciaRasgado2, String MDespesor31, String MDespesor32, String MDespesor33, String MDEspesorPromedio3, String MDResistenciaRasgado3, String MDespesor41, String MDespesor42, String MDespesor43, String MDEspesorPromedio4, String MDResistenciaRasgado4, String MDespesor51, String MDespesor52, String MDespesor53, String MDEspesorPromedio5, String MDResistenciaRasgado5, String MDespesor61, String MDespesor62, String MDespesor63, String MDEspesorPromedio6, String MDResistenciaRasgado6, String MDespesor71, String MDespesor72, String MDespesor73, String MDEspesorPromedio7, String MDResistenciaRasgado7, String MDespesor81, String MDespesor82, String MDespesor83, String MDEspesorPromedio8, String MDResistenciaRasgado8, String MDespesor91, String MDespesor92, String MDespesor93, String MDEspesorPromedio9, String MDResistenciaRasgado9, String MDespesor101, String MDespesor102, String MDespesor103, String MDEspesorPromedio10, String MDResistenciaRasgado10, String MDPromedio, String TDespesor11, String TDespesor12, String TDespesor13, String TDEspesorPromedio1, String TDResistenciaRasgado1, String TDespesor21, String TDespesor22, String TDespesor23, String TDEspesorPromedio2, String TDResistenciaRasgado2, String TDespesor31, String TDespesor32, String TDespesor33, String TDEspesorPromedio3, String TDResistenciaRasgado3, String TDespesor41, String TDespesor42, String TDespesor43, String TDEspesorPromedio4, String TDResistenciaRasgado4, String TDespesor51, String TDespesor52, String TDespesor53, String TDEspesorPromedio5, String TDResistenciaRasgado5, String TDespesor61, String TDespesor62, String TDespesor63, String TDEspesorPromedio6, String TDResistenciaRasgado6, String TDespesor71, String TDespesor72, String TDespesor73, String TDEspesorPromedio7, String TDResistenciaRasgado7, String TDespesor81, String TDespesor82, String TDespesor83, String TDEspesorPromedio8, String TDResistenciaRasgado8, String TDespesor91, String TDespesor92, String TDespesor93, String TDEspesorPromedio9, String TDResistenciaRasgado9, String TDespesor101, String TDespesor102, String TDespesor103, String TDEspesorPromedio10, String TDResistenciaRasgado10, String TDPromedio, String observaciones, String realizo, String supervisor) {
        this.idFRAPRR = idFRAPRR;
        this.folioSolicitudServicioInterno = folioSolicitudServicioInterno;
        this.idInternoMuestra = idInternoMuestra;
        this.fechaInicioAnalisis = fechaInicioAnalisis;
        this.fechaFinalAnalisis = fechaFinalAnalisis;
        this.temperatura = temperatura;
        this.humedadRelativa = humedadRelativa;
        this.codigoPendulo = codigoPendulo;
        this.codigoManometro = codigoManometro;
        this.prensaEnsayo = prensaEnsayo;
        this.MDespesor11 = MDespesor11;
        this.MDespesor12 = MDespesor12;
        this.MDespesor13 = MDespesor13;
        this.MDEspesorPromedio1 = MDEspesorPromedio1;
        this.MDResistenciaRasgado1 = MDResistenciaRasgado1;
        this.MDespesor21 = MDespesor21;
        this.MDespesor22 = MDespesor22;
        this.MDespesor23 = MDespesor23;
        this.MDEspesorPromedio2 = MDEspesorPromedio2;
        this.MDResistenciaRasgado2 = MDResistenciaRasgado2;
        this.MDespesor31 = MDespesor31;
        this.MDespesor32 = MDespesor32;
        this.MDespesor33 = MDespesor33;
        this.MDEspesorPromedio3 = MDEspesorPromedio3;
        this.MDResistenciaRasgado3 = MDResistenciaRasgado3;
        this.MDespesor41 = MDespesor41;
        this.MDespesor42 = MDespesor42;
        this.MDespesor43 = MDespesor43;
        this.MDEspesorPromedio4 = MDEspesorPromedio4;
        this.MDResistenciaRasgado4 = MDResistenciaRasgado4;
        this.MDespesor51 = MDespesor51;
        this.MDespesor52 = MDespesor52;
        this.MDespesor53 = MDespesor53;
        this.MDEspesorPromedio5 = MDEspesorPromedio5;
        this.MDResistenciaRasgado5 = MDResistenciaRasgado5;
        this.MDespesor61 = MDespesor61;
        this.MDespesor62 = MDespesor62;
        this.MDespesor63 = MDespesor63;
        this.MDEspesorPromedio6 = MDEspesorPromedio6;
        this.MDResistenciaRasgado6 = MDResistenciaRasgado6;
        this.MDespesor71 = MDespesor71;
        this.MDespesor72 = MDespesor72;
        this.MDespesor73 = MDespesor73;
        this.MDEspesorPromedio7 = MDEspesorPromedio7;
        this.MDResistenciaRasgado7 = MDResistenciaRasgado7;
        this.MDespesor81 = MDespesor81;
        this.MDespesor82 = MDespesor82;
        this.MDespesor83 = MDespesor83;
        this.MDEspesorPromedio8 = MDEspesorPromedio8;
        this.MDResistenciaRasgado8 = MDResistenciaRasgado8;
        this.MDespesor91 = MDespesor91;
        this.MDespesor92 = MDespesor92;
        this.MDespesor93 = MDespesor93;
        this.MDEspesorPromedio9 = MDEspesorPromedio9;
        this.MDResistenciaRasgado9 = MDResistenciaRasgado9;
        this.MDespesor101 = MDespesor101;
        this.MDespesor102 = MDespesor102;
        this.MDespesor103 = MDespesor103;
        this.MDEspesorPromedio10 = MDEspesorPromedio10;
        this.MDResistenciaRasgado10 = MDResistenciaRasgado10;
        this.MDPromedio = MDPromedio;
        this.TDespesor11 = TDespesor11;
        this.TDespesor12 = TDespesor12;
        this.TDespesor13 = TDespesor13;
        this.TDEspesorPromedio1 = TDEspesorPromedio1;
        this.TDResistenciaRasgado1 = TDResistenciaRasgado1;
        this.TDespesor21 = TDespesor21;
        this.TDespesor22 = TDespesor22;
        this.TDespesor23 = TDespesor23;
        this.TDEspesorPromedio2 = TDEspesorPromedio2;
        this.TDResistenciaRasgado2 = TDResistenciaRasgado2;
        this.TDespesor31 = TDespesor31;
        this.TDespesor32 = TDespesor32;
        this.TDespesor33 = TDespesor33;
        this.TDEspesorPromedio3 = TDEspesorPromedio3;
        this.TDResistenciaRasgado3 = TDResistenciaRasgado3;
        this.TDespesor41 = TDespesor41;
        this.TDespesor42 = TDespesor42;
        this.TDespesor43 = TDespesor43;
        this.TDEspesorPromedio4 = TDEspesorPromedio4;
        this.TDResistenciaRasgado4 = TDResistenciaRasgado4;
        this.TDespesor51 = TDespesor51;
        this.TDespesor52 = TDespesor52;
        this.TDespesor53 = TDespesor53;
        this.TDEspesorPromedio5 = TDEspesorPromedio5;
        this.TDResistenciaRasgado5 = TDResistenciaRasgado5;
        this.TDespesor61 = TDespesor61;
        this.TDespesor62 = TDespesor62;
        this.TDespesor63 = TDespesor63;
        this.TDEspesorPromedio6 = TDEspesorPromedio6;
        this.TDResistenciaRasgado6 = TDResistenciaRasgado6;
        this.TDespesor71 = TDespesor71;
        this.TDespesor72 = TDespesor72;
        this.TDespesor73 = TDespesor73;
        this.TDEspesorPromedio7 = TDEspesorPromedio7;
        this.TDResistenciaRasgado7 = TDResistenciaRasgado7;
        this.TDespesor81 = TDespesor81;
        this.TDespesor82 = TDespesor82;
        this.TDespesor83 = TDespesor83;
        this.TDEspesorPromedio8 = TDEspesorPromedio8;
        this.TDResistenciaRasgado8 = TDResistenciaRasgado8;
        this.TDespesor91 = TDespesor91;
        this.TDespesor92 = TDespesor92;
        this.TDespesor93 = TDespesor93;
        this.TDEspesorPromedio9 = TDEspesorPromedio9;
        this.TDResistenciaRasgado9 = TDResistenciaRasgado9;
        this.TDespesor101 = TDespesor101;
        this.TDespesor102 = TDespesor102;
        this.TDespesor103 = TDespesor103;
        this.TDEspesorPromedio10 = TDEspesorPromedio10;
        this.TDResistenciaRasgado10 = TDResistenciaRasgado10;
        this.TDPromedio = TDPromedio;
        this.observaciones = observaciones;
        this.realizo = realizo;
        this.supervisor = supervisor;
    }

    public Long getIdFRAPRR() {
        return idFRAPRR;
    }

    public void setIdFRAPRR(Long idFRAPRR) {
        this.idFRAPRR = idFRAPRR;
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

    public String getCodigoPendulo() {
        return codigoPendulo;
    }

    public void setCodigoPendulo(String codigoPendulo) {
        this.codigoPendulo = codigoPendulo;
    }

    public String getCodigoManometro() {
        return codigoManometro;
    }

    public void setCodigoManometro(String codigoManometro) {
        this.codigoManometro = codigoManometro;
    }

    public String getPrensaEnsayo() {
        return prensaEnsayo;
    }

    public void setPrensaEnsayo(String prensaEnsayo) {
        this.prensaEnsayo = prensaEnsayo;
    }

    public String getMDespesor11() {
        return MDespesor11;
    }

    public void setMDespesor11(String MDespesor11) {
        this.MDespesor11 = MDespesor11;
    }

    public String getMDespesor12() {
        return MDespesor12;
    }

    public void setMDespesor12(String MDespesor12) {
        this.MDespesor12 = MDespesor12;
    }

    public String getMDespesor13() {
        return MDespesor13;
    }

    public void setMDespesor13(String MDespesor13) {
        this.MDespesor13 = MDespesor13;
    }

    public String getMDEspesorPromedio1() {
        return MDEspesorPromedio1;
    }

    public void setMDEspesorPromedio1(String MDEspesorPromedio1) {
        this.MDEspesorPromedio1 = MDEspesorPromedio1;
    }

    public String getMDResistenciaRasgado1() {
        return MDResistenciaRasgado1;
    }

    public void setMDResistenciaRasgado1(String MDResistenciaRasgado1) {
        this.MDResistenciaRasgado1 = MDResistenciaRasgado1;
    }

    public String getMDespesor21() {
        return MDespesor21;
    }

    public void setMDespesor21(String MDespesor21) {
        this.MDespesor21 = MDespesor21;
    }

    public String getMDespesor22() {
        return MDespesor22;
    }

    public void setMDespesor22(String MDespesor22) {
        this.MDespesor22 = MDespesor22;
    }

    public String getMDespesor23() {
        return MDespesor23;
    }

    public void setMDespesor23(String MDespesor23) {
        this.MDespesor23 = MDespesor23;
    }

    public String getMDEspesorPromedio2() {
        return MDEspesorPromedio2;
    }

    public void setMDEspesorPromedio2(String MDEspesorPromedio2) {
        this.MDEspesorPromedio2 = MDEspesorPromedio2;
    }

    public String getMDResistenciaRasgado2() {
        return MDResistenciaRasgado2;
    }

    public void setMDResistenciaRasgado2(String MDResistenciaRasgado2) {
        this.MDResistenciaRasgado2 = MDResistenciaRasgado2;
    }

    public String getMDespesor31() {
        return MDespesor31;
    }

    public void setMDespesor31(String MDespesor31) {
        this.MDespesor31 = MDespesor31;
    }

    public String getMDespesor32() {
        return MDespesor32;
    }

    public void setMDespesor32(String MDespesor32) {
        this.MDespesor32 = MDespesor32;
    }

    public String getMDespesor33() {
        return MDespesor33;
    }

    public void setMDespesor33(String MDespesor33) {
        this.MDespesor33 = MDespesor33;
    }

    public String getMDEspesorPromedio3() {
        return MDEspesorPromedio3;
    }

    public void setMDEspesorPromedio3(String MDEspesorPromedio3) {
        this.MDEspesorPromedio3 = MDEspesorPromedio3;
    }

    public String getMDResistenciaRasgado3() {
        return MDResistenciaRasgado3;
    }

    public void setMDResistenciaRasgado3(String MDResistenciaRasgado3) {
        this.MDResistenciaRasgado3 = MDResistenciaRasgado3;
    }

    public String getMDespesor41() {
        return MDespesor41;
    }

    public void setMDespesor41(String MDespesor41) {
        this.MDespesor41 = MDespesor41;
    }

    public String getMDespesor42() {
        return MDespesor42;
    }

    public void setMDespesor42(String MDespesor42) {
        this.MDespesor42 = MDespesor42;
    }

    public String getMDespesor43() {
        return MDespesor43;
    }

    public void setMDespesor43(String MDespesor43) {
        this.MDespesor43 = MDespesor43;
    }

    public String getMDEspesorPromedio4() {
        return MDEspesorPromedio4;
    }

    public void setMDEspesorPromedio4(String MDEspesorPromedio4) {
        this.MDEspesorPromedio4 = MDEspesorPromedio4;
    }

    public String getMDResistenciaRasgado4() {
        return MDResistenciaRasgado4;
    }

    public void setMDResistenciaRasgado4(String MDResistenciaRasgado4) {
        this.MDResistenciaRasgado4 = MDResistenciaRasgado4;
    }

    public String getMDespesor51() {
        return MDespesor51;
    }

    public void setMDespesor51(String MDespesor51) {
        this.MDespesor51 = MDespesor51;
    }

    public String getMDespesor52() {
        return MDespesor52;
    }

    public void setMDespesor52(String MDespesor52) {
        this.MDespesor52 = MDespesor52;
    }

    public String getMDespesor53() {
        return MDespesor53;
    }

    public void setMDespesor53(String MDespesor53) {
        this.MDespesor53 = MDespesor53;
    }

    public String getMDEspesorPromedio5() {
        return MDEspesorPromedio5;
    }

    public void setMDEspesorPromedio5(String MDEspesorPromedio5) {
        this.MDEspesorPromedio5 = MDEspesorPromedio5;
    }

    public String getMDResistenciaRasgado5() {
        return MDResistenciaRasgado5;
    }

    public void setMDResistenciaRasgado5(String MDResistenciaRasgado5) {
        this.MDResistenciaRasgado5 = MDResistenciaRasgado5;
    }

    public String getMDespesor61() {
        return MDespesor61;
    }

    public void setMDespesor61(String MDespesor61) {
        this.MDespesor61 = MDespesor61;
    }

    public String getMDespesor62() {
        return MDespesor62;
    }

    public void setMDespesor62(String MDespesor62) {
        this.MDespesor62 = MDespesor62;
    }

    public String getMDespesor63() {
        return MDespesor63;
    }

    public void setMDespesor63(String MDespesor63) {
        this.MDespesor63 = MDespesor63;
    }

    public String getMDEspesorPromedio6() {
        return MDEspesorPromedio6;
    }

    public void setMDEspesorPromedio6(String MDEspesorPromedio6) {
        this.MDEspesorPromedio6 = MDEspesorPromedio6;
    }

    public String getMDResistenciaRasgado6() {
        return MDResistenciaRasgado6;
    }

    public void setMDResistenciaRasgado6(String MDResistenciaRasgado6) {
        this.MDResistenciaRasgado6 = MDResistenciaRasgado6;
    }

    public String getMDespesor71() {
        return MDespesor71;
    }

    public void setMDespesor71(String MDespesor71) {
        this.MDespesor71 = MDespesor71;
    }

    public String getMDespesor72() {
        return MDespesor72;
    }

    public void setMDespesor72(String MDespesor72) {
        this.MDespesor72 = MDespesor72;
    }

    public String getMDespesor73() {
        return MDespesor73;
    }

    public void setMDespesor73(String MDespesor73) {
        this.MDespesor73 = MDespesor73;
    }

    public String getMDEspesorPromedio7() {
        return MDEspesorPromedio7;
    }

    public void setMDEspesorPromedio7(String MDEspesorPromedio7) {
        this.MDEspesorPromedio7 = MDEspesorPromedio7;
    }

    public String getMDResistenciaRasgado7() {
        return MDResistenciaRasgado7;
    }

    public void setMDResistenciaRasgado7(String MDResistenciaRasgado7) {
        this.MDResistenciaRasgado7 = MDResistenciaRasgado7;
    }

    public String getMDespesor81() {
        return MDespesor81;
    }

    public void setMDespesor81(String MDespesor81) {
        this.MDespesor81 = MDespesor81;
    }

    public String getMDespesor82() {
        return MDespesor82;
    }

    public void setMDespesor82(String MDespesor82) {
        this.MDespesor82 = MDespesor82;
    }

    public String getMDespesor83() {
        return MDespesor83;
    }

    public void setMDespesor83(String MDespesor83) {
        this.MDespesor83 = MDespesor83;
    }

    public String getMDEspesorPromedio8() {
        return MDEspesorPromedio8;
    }

    public void setMDEspesorPromedio8(String MDEspesorPromedio8) {
        this.MDEspesorPromedio8 = MDEspesorPromedio8;
    }

    public String getMDResistenciaRasgado8() {
        return MDResistenciaRasgado8;
    }

    public void setMDResistenciaRasgado8(String MDResistenciaRasgado8) {
        this.MDResistenciaRasgado8 = MDResistenciaRasgado8;
    }

    public String getMDespesor91() {
        return MDespesor91;
    }

    public void setMDespesor91(String MDespesor91) {
        this.MDespesor91 = MDespesor91;
    }

    public String getMDespesor92() {
        return MDespesor92;
    }

    public void setMDespesor92(String MDespesor92) {
        this.MDespesor92 = MDespesor92;
    }

    public String getMDespesor93() {
        return MDespesor93;
    }

    public void setMDespesor93(String MDespesor93) {
        this.MDespesor93 = MDespesor93;
    }

    public String getMDEspesorPromedio9() {
        return MDEspesorPromedio9;
    }

    public void setMDEspesorPromedio9(String MDEspesorPromedio9) {
        this.MDEspesorPromedio9 = MDEspesorPromedio9;
    }

    public String getMDResistenciaRasgado9() {
        return MDResistenciaRasgado9;
    }

    public void setMDResistenciaRasgado9(String MDResistenciaRasgado9) {
        this.MDResistenciaRasgado9 = MDResistenciaRasgado9;
    }

    public String getMDespesor101() {
        return MDespesor101;
    }

    public void setMDespesor101(String MDespesor101) {
        this.MDespesor101 = MDespesor101;
    }

    public String getMDespesor102() {
        return MDespesor102;
    }

    public void setMDespesor102(String MDespesor102) {
        this.MDespesor102 = MDespesor102;
    }

    public String getMDespesor103() {
        return MDespesor103;
    }

    public void setMDespesor103(String MDespesor103) {
        this.MDespesor103 = MDespesor103;
    }

    public String getMDEspesorPromedio10() {
        return MDEspesorPromedio10;
    }

    public void setMDEspesorPromedio10(String MDEspesorPromedio10) {
        this.MDEspesorPromedio10 = MDEspesorPromedio10;
    }

    public String getMDResistenciaRasgado10() {
        return MDResistenciaRasgado10;
    }

    public void setMDResistenciaRasgado10(String MDResistenciaRasgado10) {
        this.MDResistenciaRasgado10 = MDResistenciaRasgado10;
    }

    public String getMDPromedio() {
        return MDPromedio;
    }

    public void setMDPromedio(String MDPromedio) {
        this.MDPromedio = MDPromedio;
    }

    public String getTDespesor11() {
        return TDespesor11;
    }

    public void setTDespesor11(String TDespesor11) {
        this.TDespesor11 = TDespesor11;
    }

    public String getTDespesor12() {
        return TDespesor12;
    }

    public void setTDespesor12(String TDespesor12) {
        this.TDespesor12 = TDespesor12;
    }

    public String getTDespesor13() {
        return TDespesor13;
    }

    public void setTDespesor13(String TDespesor13) {
        this.TDespesor13 = TDespesor13;
    }

    public String getTDEspesorPromedio1() {
        return TDEspesorPromedio1;
    }

    public void setTDEspesorPromedio1(String TDEspesorPromedio1) {
        this.TDEspesorPromedio1 = TDEspesorPromedio1;
    }

    public String getTDResistenciaRasgado1() {
        return TDResistenciaRasgado1;
    }

    public void setTDResistenciaRasgado1(String TDResistenciaRasgado1) {
        this.TDResistenciaRasgado1 = TDResistenciaRasgado1;
    }

    public String getTDespesor21() {
        return TDespesor21;
    }

    public void setTDespesor21(String TDespesor21) {
        this.TDespesor21 = TDespesor21;
    }

    public String getTDespesor22() {
        return TDespesor22;
    }

    public void setTDespesor22(String TDespesor22) {
        this.TDespesor22 = TDespesor22;
    }

    public String getTDespesor23() {
        return TDespesor23;
    }

    public void setTDespesor23(String TDespesor23) {
        this.TDespesor23 = TDespesor23;
    }

    public String getTDEspesorPromedio2() {
        return TDEspesorPromedio2;
    }

    public void setTDEspesorPromedio2(String TDEspesorPromedio2) {
        this.TDEspesorPromedio2 = TDEspesorPromedio2;
    }

    public String getTDResistenciaRasgado2() {
        return TDResistenciaRasgado2;
    }

    public void setTDResistenciaRasgado2(String TDResistenciaRasgado2) {
        this.TDResistenciaRasgado2 = TDResistenciaRasgado2;
    }

    public String getTDespesor31() {
        return TDespesor31;
    }

    public void setTDespesor31(String TDespesor31) {
        this.TDespesor31 = TDespesor31;
    }

    public String getTDespesor32() {
        return TDespesor32;
    }

    public void setTDespesor32(String TDespesor32) {
        this.TDespesor32 = TDespesor32;
    }

    public String getTDespesor33() {
        return TDespesor33;
    }

    public void setTDespesor33(String TDespesor33) {
        this.TDespesor33 = TDespesor33;
    }

    public String getTDEspesorPromedio3() {
        return TDEspesorPromedio3;
    }

    public void setTDEspesorPromedio3(String TDEspesorPromedio3) {
        this.TDEspesorPromedio3 = TDEspesorPromedio3;
    }

    public String getTDResistenciaRasgado3() {
        return TDResistenciaRasgado3;
    }

    public void setTDResistenciaRasgado3(String TDResistenciaRasgado3) {
        this.TDResistenciaRasgado3 = TDResistenciaRasgado3;
    }

    public String getTDespesor41() {
        return TDespesor41;
    }

    public void setTDespesor41(String TDespesor41) {
        this.TDespesor41 = TDespesor41;
    }

    public String getTDespesor42() {
        return TDespesor42;
    }

    public void setTDespesor42(String TDespesor42) {
        this.TDespesor42 = TDespesor42;
    }

    public String getTDespesor43() {
        return TDespesor43;
    }

    public void setTDespesor43(String TDespesor43) {
        this.TDespesor43 = TDespesor43;
    }

    public String getTDEspesorPromedio4() {
        return TDEspesorPromedio4;
    }

    public void setTDEspesorPromedio4(String TDEspesorPromedio4) {
        this.TDEspesorPromedio4 = TDEspesorPromedio4;
    }

    public String getTDResistenciaRasgado4() {
        return TDResistenciaRasgado4;
    }

    public void setTDResistenciaRasgado4(String TDResistenciaRasgado4) {
        this.TDResistenciaRasgado4 = TDResistenciaRasgado4;
    }

    public String getTDespesor51() {
        return TDespesor51;
    }

    public void setTDespesor51(String TDespesor51) {
        this.TDespesor51 = TDespesor51;
    }

    public String getTDespesor52() {
        return TDespesor52;
    }

    public void setTDespesor52(String TDespesor52) {
        this.TDespesor52 = TDespesor52;
    }

    public String getTDespesor53() {
        return TDespesor53;
    }

    public void setTDespesor53(String TDespesor53) {
        this.TDespesor53 = TDespesor53;
    }

    public String getTDEspesorPromedio5() {
        return TDEspesorPromedio5;
    }

    public void setTDEspesorPromedio5(String TDEspesorPromedio5) {
        this.TDEspesorPromedio5 = TDEspesorPromedio5;
    }

    public String getTDResistenciaRasgado5() {
        return TDResistenciaRasgado5;
    }

    public void setTDResistenciaRasgado5(String TDResistenciaRasgado5) {
        this.TDResistenciaRasgado5 = TDResistenciaRasgado5;
    }

    public String getTDespesor61() {
        return TDespesor61;
    }

    public void setTDespesor61(String TDespesor61) {
        this.TDespesor61 = TDespesor61;
    }

    public String getTDespesor62() {
        return TDespesor62;
    }

    public void setTDespesor62(String TDespesor62) {
        this.TDespesor62 = TDespesor62;
    }

    public String getTDespesor63() {
        return TDespesor63;
    }

    public void setTDespesor63(String TDespesor63) {
        this.TDespesor63 = TDespesor63;
    }

    public String getTDEspesorPromedio6() {
        return TDEspesorPromedio6;
    }

    public void setTDEspesorPromedio6(String TDEspesorPromedio6) {
        this.TDEspesorPromedio6 = TDEspesorPromedio6;
    }

    public String getTDResistenciaRasgado6() {
        return TDResistenciaRasgado6;
    }

    public void setTDResistenciaRasgado6(String TDResistenciaRasgado6) {
        this.TDResistenciaRasgado6 = TDResistenciaRasgado6;
    }

    public String getTDespesor71() {
        return TDespesor71;
    }

    public void setTDespesor71(String TDespesor71) {
        this.TDespesor71 = TDespesor71;
    }

    public String getTDespesor72() {
        return TDespesor72;
    }

    public void setTDespesor72(String TDespesor72) {
        this.TDespesor72 = TDespesor72;
    }

    public String getTDespesor73() {
        return TDespesor73;
    }

    public void setTDespesor73(String TDespesor73) {
        this.TDespesor73 = TDespesor73;
    }

    public String getTDEspesorPromedio7() {
        return TDEspesorPromedio7;
    }

    public void setTDEspesorPromedio7(String TDEspesorPromedio7) {
        this.TDEspesorPromedio7 = TDEspesorPromedio7;
    }

    public String getTDResistenciaRasgado7() {
        return TDResistenciaRasgado7;
    }

    public void setTDResistenciaRasgado7(String TDResistenciaRasgado7) {
        this.TDResistenciaRasgado7 = TDResistenciaRasgado7;
    }

    public String getTDespesor81() {
        return TDespesor81;
    }

    public void setTDespesor81(String TDespesor81) {
        this.TDespesor81 = TDespesor81;
    }

    public String getTDespesor82() {
        return TDespesor82;
    }

    public void setTDespesor82(String TDespesor82) {
        this.TDespesor82 = TDespesor82;
    }

    public String getTDespesor83() {
        return TDespesor83;
    }

    public void setTDespesor83(String TDespesor83) {
        this.TDespesor83 = TDespesor83;
    }

    public String getTDEspesorPromedio8() {
        return TDEspesorPromedio8;
    }

    public void setTDEspesorPromedio8(String TDEspesorPromedio8) {
        this.TDEspesorPromedio8 = TDEspesorPromedio8;
    }

    public String getTDResistenciaRasgado8() {
        return TDResistenciaRasgado8;
    }

    public void setTDResistenciaRasgado8(String TDResistenciaRasgado8) {
        this.TDResistenciaRasgado8 = TDResistenciaRasgado8;
    }

    public String getTDespesor91() {
        return TDespesor91;
    }

    public void setTDespesor91(String TDespesor91) {
        this.TDespesor91 = TDespesor91;
    }

    public String getTDespesor92() {
        return TDespesor92;
    }

    public void setTDespesor92(String TDespesor92) {
        this.TDespesor92 = TDespesor92;
    }

    public String getTDespesor93() {
        return TDespesor93;
    }

    public void setTDespesor93(String TDespesor93) {
        this.TDespesor93 = TDespesor93;
    }

    public String getTDEspesorPromedio9() {
        return TDEspesorPromedio9;
    }

    public void setTDEspesorPromedio9(String TDEspesorPromedio9) {
        this.TDEspesorPromedio9 = TDEspesorPromedio9;
    }

    public String getTDResistenciaRasgado9() {
        return TDResistenciaRasgado9;
    }

    public void setTDResistenciaRasgado9(String TDResistenciaRasgado9) {
        this.TDResistenciaRasgado9 = TDResistenciaRasgado9;
    }

    public String getTDespesor101() {
        return TDespesor101;
    }

    public void setTDespesor101(String TDespesor101) {
        this.TDespesor101 = TDespesor101;
    }

    public String getTDespesor102() {
        return TDespesor102;
    }

    public void setTDespesor102(String TDespesor102) {
        this.TDespesor102 = TDespesor102;
    }

    public String getTDespesor103() {
        return TDespesor103;
    }

    public void setTDespesor103(String TDespesor103) {
        this.TDespesor103 = TDespesor103;
    }

    public String getTDEspesorPromedio10() {
        return TDEspesorPromedio10;
    }

    public void setTDEspesorPromedio10(String TDEspesorPromedio10) {
        this.TDEspesorPromedio10 = TDEspesorPromedio10;
    }

    public String getTDResistenciaRasgado10() {
        return TDResistenciaRasgado10;
    }

    public void setTDResistenciaRasgado10(String TDResistenciaRasgado10) {
        this.TDResistenciaRasgado10 = TDResistenciaRasgado10;
    }

    public String getTDPromedio() {
        return TDPromedio;
    }

    public void setTDPromedio(String TDPromedio) {
        this.TDPromedio = TDPromedio;
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
}