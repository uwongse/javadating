/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package peccorina;

/**
 *
 * @author alumno
 */
public abstract class Usuario {

    private String nombreUsuario;
    private String correoUsuario;
    private String contrasennaUsuario;
    private String roll;

    public Usuario() {
    }

    public Usuario(String nombreUsuario, String correoUsuario, String contrasennaUsuario, String roll) {
        this.nombreUsuario = nombreUsuario;
        this.correoUsuario = correoUsuario;
        this.contrasennaUsuario = contrasennaUsuario;
        this.roll = roll;
    }

    public Usuario(String correoUsuario) {
        this.correoUsuario = correoUsuario;
    }

    public Usuario(String nombreUsuario, String correoUsuario) {
        this.nombreUsuario = nombreUsuario;
        this.correoUsuario = correoUsuario;
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public String getCorreoUsuario() {
        return correoUsuario;
    }

    public String getContrasennaUsuario() {
        return contrasennaUsuario;
    }

    public String getRoll() {
        return roll;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public void setCorreoUsuario(String correoUsuario) {
        this.correoUsuario = correoUsuario;
    }

    public void setContrasennaUsuario(String contrasennaUsuario) {
        this.contrasennaUsuario = contrasennaUsuario;
    }

    public void setRoll(String roll) {
        this.roll = roll;
    }

}
