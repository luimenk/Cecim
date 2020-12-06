package com.demo.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;

@Entity
public class Machine {

    @Id
    @GeneratedValue
    @Column(name = "Machine_Id", nullable = false)
    private Long machineId;

    //*** DATOS DEL EQUIPO ***
    @Column(name = "Codigo_Interno", length = 250, nullable = false)
    private String codigoInterno;

    @Column(name = "Area_Responsable", length = 250, nullable = false)
    private String areaResponsable;

    @Column(name = "Nombre_Equipo_Instrumento", length = 250, nullable = false)
    @NotBlank(message = "Debe ingresar el nombre del instrumento")
    private String nombreEquipoInstrumento;

    @Column(name = "Tipo_Equipo", length = 250, nullable = false)
    private String tipoEquipo;

    @Column(name = "Registro", length = 250, nullable = false)
    private String registro;

    @Column(name = "Tipo_Servicio_Requerido", length = 250, nullable = false)
    private String tipoServicioRequerido;

    @Column(name = "Condicion_Equipo", length = 250, nullable = false)
    private String condicionEquipo;

    @Column(name = "Numero_Serie", length = 250, nullable = false)
    private String numeroSerie;

    @Column(name = "Modelo", length = 250, nullable = false)
    private String modelo;

    @Column(name = "Marca", length = 250, nullable = false)
    private String marca;

    @Column(name = "Garantia", length = 250, nullable = false)
    private String garantia;

    @Column(name = "Fabricante", length = 250, nullable = false)
    private String fabricante;

    //dimensiones

    @Column(name = "Largo", length = 250, nullable = false)
    private String largo;

    @Column(name = "Ancho", length = 250, nullable = false)
    private String ancho;

    @Column(name = "Alto", length = 250, nullable = false)
    private String alto;

    @Column(name = "Peso", length = 250, nullable = false)
    private String peso;

    @Column(name = "Zona_Ubicacion", length = 250, nullable = false)
    private String zonaUbicacion;

    @Column(name = "Plano_Anexo", length = 250, nullable = false)
    private String planoAnexo;

    // *** DATOS DEL PROVEEDOR ***

    @Column(name = "Nombre_Proveedor", length = 250, nullable = false)
    private String proveedor;

    @Column(name = "Direccion_Proveedor", length = 250, nullable = false)
    private String direccionProveedor;

    @Column(name = "Persona_Contacto", length = 250, nullable = false)
    private String personaContacto;

    @Column(name = "Telefono_Proveedor", length = 250, nullable = false)
    private String telefonoProveedor;

    @Column(name = "Correo_Proveedor", length = 250, nullable = false)
    private String correoProveedor;

    @Column(name = "Fecha_Adquisicion", length = 250, nullable = false)
    private String fechaAdquisicion;

    @Column(name = "Fecha_Instalacion", length = 250, nullable = false)
    private String fechaInstalacion;

    // *** Requerimientos de instalacion ***

    @Column(name = "Agua", length = 250, nullable = false)
    private String agua;

    @Column(name = "Aire_Comprimido", length = 250, nullable = false)
    private String aireComprimido;

    @Column(name = "Gas", length = 250, nullable = false)
    private String gas;

    @Column(name = "Voltaje_Alimentacion", length = 250, nullable = false)
    private String voltajeAlimentacion;

    @Column(name = "Consumo_Electrico", length = 250, nullable = false)
    private String consumoElectrico;

    @Column(name = "Otro_Especifique", length = 250, nullable = false)
    private String otroEspecifique;

    // *** PROGRAMAS Y APLICACIONES ***

    @Column(name = "Nombre_Version", length = 250, nullable = false)
    private String nombreVersion;

    @Column(name = "Aplicacion", length = 250, nullable = false)
    private String aplicacion;

    @Column(name = "Accesorios", length = 250, nullable = false)
    private String accesorios;

    @Column(name = "Usuarios", length = 250, nullable = false)
    private String usuarios;

    // *** FACILIDADES DEL PROVEEDOR

    @Column(name = "IdiomaManual", length = 250, nullable = false)
    private String idiomaManual;

    @Column(name = "Entrega_Planos", length = 250, nullable = false)
    private String entregaPlanos;

    @Column(name = "Certificado_Calibracion", length = 250, nullable = false)
    private String certificadoCalibracion;

    @Column(name = "Certificado_Materiales_Accesorios", length = 250, nullable = false)
    private String certificadoMaterialesAccesorios;

    @Column(name = "Instalacion_Capacitacion", length = 250, nullable = false)
    private String instalacionCapacitacion;

    @Column(name = "Mantenimiento_Periodico", length = 250, nullable = false)
    private String mantenimientoPeriodico;

    public Machine() {
    }

    public Machine(String codigoInterno, String areaResponsable, String nombreEquipoInstrumento, String tipoEquipo, String registro, String tipoServicioRequerido, String condicionEquipo, String numeroSerie, String modelo, String marca, String garantia, String fabricante, String largo, String ancho, String alto, String peso, String zonaUbicacion, String planoAnexo, String proveedor, String direccionProveedor, String personaContacto, String telefonoProveedor, String correoProveedor, String fechaAdquisicion, String fechaInstalacion, String agua, String aireComprimido, String gas, String voltajeAlimentacion, String consumoElectrico, String otroEspecifique, String nombreVersion, String aplicacion, String accesorios, String usuarios, String idiomaManual, String entregaPlanos, String certificadoCalibracion, String certificadoMaterialesAccesorios, String instalacionCapacitacion, String mantenimientoPeriodico) {
        this.codigoInterno = codigoInterno;
        this.areaResponsable = areaResponsable;
        this.nombreEquipoInstrumento = nombreEquipoInstrumento;
        this.tipoEquipo = tipoEquipo;
        this.registro = registro;
        this.tipoServicioRequerido = tipoServicioRequerido;
        this.condicionEquipo = condicionEquipo;
        this.numeroSerie = numeroSerie;
        this.modelo = modelo;
        this.marca = marca;
        this.garantia = garantia;
        this.fabricante = fabricante;
        this.largo = largo;
        this.ancho = ancho;
        this.alto = alto;
        this.peso = peso;
        this.zonaUbicacion = zonaUbicacion;
        this.planoAnexo = planoAnexo;
        this.proveedor = proveedor;
        this.direccionProveedor = direccionProveedor;
        this.personaContacto = personaContacto;
        this.telefonoProveedor = telefonoProveedor;
        this.correoProveedor = correoProveedor;
        this.fechaAdquisicion = fechaAdquisicion;
        this.fechaInstalacion = fechaInstalacion;
        this.agua = agua;
        this.aireComprimido = aireComprimido;
        this.gas = gas;
        this.voltajeAlimentacion = voltajeAlimentacion;
        this.consumoElectrico = consumoElectrico;
        this.otroEspecifique = otroEspecifique;
        this.nombreVersion = nombreVersion;
        this.aplicacion = aplicacion;
        this.accesorios = accesorios;
        this.usuarios = usuarios;
        this.idiomaManual = idiomaManual;
        this.entregaPlanos = entregaPlanos;
        this.certificadoCalibracion = certificadoCalibracion;
        this.certificadoMaterialesAccesorios = certificadoMaterialesAccesorios;
        this.instalacionCapacitacion = instalacionCapacitacion;
        this.mantenimientoPeriodico = mantenimientoPeriodico;
    }

    public Long getMachineId() {
        return machineId;
    }

    public void setMachineId(Long machineId) {
        this.machineId = machineId;
    }

    public String getCodigoInterno() {
        return codigoInterno;
    }

    public void setCodigoInterno(String codigoInterno) {
        this.codigoInterno = codigoInterno;
    }

    public String getAreaResponsable() {
        return areaResponsable;
    }

    public void setAreaResponsable(String areaResponsable) {
        this.areaResponsable = areaResponsable;
    }

    public String getNombreEquipoInstrumento() {
        return nombreEquipoInstrumento;
    }

    public void setNombreEquipoInstrumento(String nombreEquipoInstrumento) {
        this.nombreEquipoInstrumento = nombreEquipoInstrumento;
    }

    public String getTipoEquipo() {
        return tipoEquipo;
    }

    public void setTipoEquipo(String tipoEquipo) {
        this.tipoEquipo = tipoEquipo;
    }

    public String getRegistro() {
        return registro;
    }

    public void setRegistro(String registro) {
        this.registro = registro;
    }

    public String getTipoServicioRequerido() {
        return tipoServicioRequerido;
    }

    public void setTipoServicioRequerido(String tipoServicioRequerido) {
        this.tipoServicioRequerido = tipoServicioRequerido;
    }

    public String getCondicionEquipo() {
        return condicionEquipo;
    }

    public void setCondicionEquipo(String condicionEquipo) {
        this.condicionEquipo = condicionEquipo;
    }

    public String getNumeroSerie() {
        return numeroSerie;
    }

    public void setNumeroSerie(String numeroSerie) {
        this.numeroSerie = numeroSerie;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getGarantia() {
        return garantia;
    }

    public void setGarantia(String garantia) {
        this.garantia = garantia;
    }

    public String getFabricante() {
        return fabricante;
    }

    public void setFabricante(String fabricante) {
        this.fabricante = fabricante;
    }

    public String getLargo() {
        return largo;
    }

    public void setLargo(String largo) {
        this.largo = largo;
    }

    public String getAncho() {
        return ancho;
    }

    public void setAncho(String ancho) {
        this.ancho = ancho;
    }

    public String getAlto() {
        return alto;
    }

    public void setAlto(String alto) {
        this.alto = alto;
    }

    public String getPeso() {
        return peso;
    }

    public void setPeso(String peso) {
        this.peso = peso;
    }

    public String getZonaUbicacion() {
        return zonaUbicacion;
    }

    public void setZonaUbicacion(String zonaUbicacion) {
        this.zonaUbicacion = zonaUbicacion;
    }

    public String getPlanoAnexo() {
        return planoAnexo;
    }

    public void setPlanoAnexo(String planoAnexo) {
        this.planoAnexo = planoAnexo;
    }

    public String getProveedor() {
        return proveedor;
    }

    public void setProveedor(String proveedor) {
        this.proveedor = proveedor;
    }

    public String getDireccionProveedor() {
        return direccionProveedor;
    }

    public void setDireccionProveedor(String direccionProveedor) {
        this.direccionProveedor = direccionProveedor;
    }

    public String getPersonaContacto() {
        return personaContacto;
    }

    public void setPersonaContacto(String personaContacto) {
        this.personaContacto = personaContacto;
    }

    public String getTelefonoProveedor() {
        return telefonoProveedor;
    }

    public void setTelefonoProveedor(String telefonoProveedor) {
        this.telefonoProveedor = telefonoProveedor;
    }

    public String getCorreoProveedor() {
        return correoProveedor;
    }

    public void setCorreoProveedor(String correoProveedor) {
        this.correoProveedor = correoProveedor;
    }

    public String getFechaAdquisicion() {
        return fechaAdquisicion;
    }

    public void setFechaAdquisicion(String fechaAdquisicion) {
        this.fechaAdquisicion = fechaAdquisicion;
    }

    public String getFechaInstalacion() {
        return fechaInstalacion;
    }

    public void setFechaInstalacion(String fechaInstalacion) {
        this.fechaInstalacion = fechaInstalacion;
    }

    public String getAgua() {
        return agua;
    }

    public void setAgua(String agua) {
        this.agua = agua;
    }

    public String getAireComprimido() {
        return aireComprimido;
    }

    public void setAireComprimido(String aireComprimido) {
        this.aireComprimido = aireComprimido;
    }

    public String getGas() {
        return gas;
    }

    public void setGas(String gas) {
        this.gas = gas;
    }

    public String getVoltajeAlimentacion() {
        return voltajeAlimentacion;
    }

    public void setVoltajeAlimentacion(String voltajeAlimentacion) {
        this.voltajeAlimentacion = voltajeAlimentacion;
    }

    public String getConsumoElectrico() {
        return consumoElectrico;
    }

    public void setConsumoElectrico(String consumoElectrico) {
        this.consumoElectrico = consumoElectrico;
    }

    public String getOtroEspecifique() {
        return otroEspecifique;
    }

    public void setOtroEspecifique(String otroEspecifique) {
        this.otroEspecifique = otroEspecifique;
    }

    public String getNombreVersion() {
        return nombreVersion;
    }

    public void setNombreVersion(String nombreVersion) {
        this.nombreVersion = nombreVersion;
    }

    public String getAplicacion() {
        return aplicacion;
    }

    public void setAplicacion(String aplicacion) {
        this.aplicacion = aplicacion;
    }

    public String getAccesorios() {
        return accesorios;
    }

    public void setAccesorios(String accesorios) {
        this.accesorios = accesorios;
    }

    public String getUsuarios() {
        return usuarios;
    }

    public void setUsuarios(String usuarios) {
        this.usuarios = usuarios;
    }

    public String getIdiomaManual() {
        return idiomaManual;
    }

    public void setIdiomaManual(String idiomaManual) {
        this.idiomaManual = idiomaManual;
    }

    public String getEntregaPlanos() {
        return entregaPlanos;
    }

    public void setEntregaPlanos(String entregaPlanos) {
        this.entregaPlanos = entregaPlanos;
    }

    public String getCertificadoCalibracion() {
        return certificadoCalibracion;
    }

    public void setCertificadoCalibracion(String certificadoCalibracion) {
        this.certificadoCalibracion = certificadoCalibracion;
    }

    public String getCertificadoMaterialesAccesorios() {
        return certificadoMaterialesAccesorios;
    }

    public void setCertificadoMaterialesAccesorios(String certificadoMaterialesAccesorios) {
        this.certificadoMaterialesAccesorios = certificadoMaterialesAccesorios;
    }

    public String getInstalacionCapacitacion() {
        return instalacionCapacitacion;
    }

    public void setInstalacionCapacitacion(String instalacionCapacitacion) {
        this.instalacionCapacitacion = instalacionCapacitacion;
    }

    public String getMantenimientoPeriodico() {
        return mantenimientoPeriodico;
    }

    public void setMantenimientoPeriodico(String mantenimientoPeriodico) {
        this.mantenimientoPeriodico = mantenimientoPeriodico;
    }
}
