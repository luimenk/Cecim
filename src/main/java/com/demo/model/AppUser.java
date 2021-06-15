package com.demo.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class AppUser {

    @Id
    @GeneratedValue
    @Column(name = "User_Id", nullable = false)
    private Long userId;

    @Column(name = "User_Name", length = 36, nullable = false, unique = true)
    private String userName;

    @Column(name = "password", length = 128, nullable = false)
    private String password;

    @Column(name = "Enabled", length = 1, nullable = false)
    private boolean enabled;

    @Column(name = "Visible", length = 1, nullable = false)
    private int visible;

    @Column(name = "nombre_usuario", length = 200, nullable = false)
    private String nombreUsuario;

    @Column(name = "apellido_usuario", length = 200, nullable = false)
    private String apellidoUsuario;

    @Column(name = "nacimiento", length = 200, nullable = false)
    private String nacimiento;

    @Column(name = "puesto", length = 200, nullable = false)
    private String puesto;

    public AppUser() {
    }

    public AppUser(Long userId, String userName, String password, boolean enabled, int visible, String nombreUsuario, String apellidoUsuario, String nacimiento, String puesto) {
        this.userId = userId;
        this.userName = userName;
        this.password = password;
        this.enabled = enabled;
        this.visible = visible;
        this.nombreUsuario = nombreUsuario;
        this.apellidoUsuario = apellidoUsuario;
        this.nacimiento = nacimiento;
        this.puesto = puesto;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public int getVisible() {
        return visible;
    }

    public void setVisible(int visible) {
        this.visible = visible;
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public String getApellidoUsuario() {
        return apellidoUsuario;
    }

    public void setApellidoUsuario(String apellidoUsuario) {
        this.apellidoUsuario = apellidoUsuario;
    }

    public String getNacimiento() {
        return nacimiento;
    }

    public void setNacimiento(String nacimiento) {
        this.nacimiento = nacimiento;
    }

    public String getPuesto() {
        return puesto;
    }

    public void setPuesto(String puesto) {
        this.puesto = puesto;
    }
}
