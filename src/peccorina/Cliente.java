/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package peccorina;

import java.io.FileInputStream;

/**
 *
 * @author alumno
 */
public class Cliente extends Usuario {

    private String sexoUsuario;
    private int edadUsuario;
    private String orientacionSexual;
    private String ciudadUsuario;
    private String telefonoUsuario;
    private FileInputStream fotoUsuario;
    private int darLikeContador;
    private int recibirLikeContador;

    public Cliente(String sexoUsuario, int edadUsuario, String orientacionSexual, String ciudadUsuario, String telefonoUsuario, int darLikeContador, int recibirLikeContador, String correoUsuario) {
        super(correoUsuario);
        this.sexoUsuario = sexoUsuario;
        this.edadUsuario = edadUsuario;
        this.orientacionSexual = orientacionSexual;
        this.ciudadUsuario = ciudadUsuario;
        this.telefonoUsuario = telefonoUsuario;
        this.darLikeContador = darLikeContador;
        this.recibirLikeContador = recibirLikeContador;
    }

    public Cliente(String correoUsuario) {
        super(correoUsuario);
    }

    public Cliente(int recibirLikeContador) {
        this.recibirLikeContador = recibirLikeContador;
    }
    

    public Cliente(String sexoUsuario, int edadUsuario, String orientacionSexual, String ciudadUsuario, String telefonoUsuario, FileInputStream fotoUsuario, int darLikeContador, int recibirLikeContador, String nombreUsuario, String correoUsuario, String contrasennaUsuario, String roll) {
        super(nombreUsuario, correoUsuario, contrasennaUsuario, roll);
        this.sexoUsuario = sexoUsuario;
        this.edadUsuario = edadUsuario;
        this.orientacionSexual = orientacionSexual;
        this.ciudadUsuario = ciudadUsuario;
        this.telefonoUsuario = telefonoUsuario;
        this.fotoUsuario = fotoUsuario;
        this.darLikeContador = darLikeContador;
        this.recibirLikeContador = recibirLikeContador;
    }

    public Cliente(String sexoUsuario, int edadUsuario, String orientacionSexual, String ciudadUsuario, String telefonoUsuario, int darLikeContador, int recibirLikeContador, String nombreUsuario, String correoUsuario, String contrasennaUsuario, String roll) {
        super(nombreUsuario, correoUsuario, contrasennaUsuario, roll);
        this.sexoUsuario = sexoUsuario;
        this.edadUsuario = edadUsuario;
        this.orientacionSexual = orientacionSexual;
        this.ciudadUsuario = ciudadUsuario;
        this.telefonoUsuario = telefonoUsuario;
        this.darLikeContador = darLikeContador;
        this.recibirLikeContador = recibirLikeContador;
    }

    public String getSexoUsuario() {
        return sexoUsuario;
    }

    public int getEdadUsuario() {
        return edadUsuario;
    }

    public String getOrientacionSexual() {
        return orientacionSexual;
    }

    public String getCiudadUsuario() {
        return ciudadUsuario;
    }

    public String getTelefonoUsuario() {
        return telefonoUsuario;
    }

    public FileInputStream getFotoUsuario() {
        return fotoUsuario;
    }

    public int getDarLikeContador() {
        return darLikeContador;
    }

    public int getRecibirLikeContador() {
        return recibirLikeContador;
    }

    public void setSexoUsuario(String sexoUsuario) {
        this.sexoUsuario = sexoUsuario;
    }

    public void setEdadUsuario(int edadUsuario) {
        this.edadUsuario = edadUsuario;
    }

    public void setOrientacionSexual(String orientacionSexual) {
        this.orientacionSexual = orientacionSexual;
    }

    public void setCiudadUsuario(String ciudadUsuario) {
        this.ciudadUsuario = ciudadUsuario;
    }

    public void setTelefonoUsuario(String telefonoUsuario) {
        this.telefonoUsuario = telefonoUsuario;
    }

    public void setFotoUsuario(FileInputStream fotoUsuario) {
        this.fotoUsuario = fotoUsuario;
    }

    public void setDarLikeContador(int darLikeContador) {
        this.darLikeContador = darLikeContador;
    }

    public void setRecibirLikeContador(int recibirLikeContador) {
        this.recibirLikeContador = recibirLikeContador;
    }

}
