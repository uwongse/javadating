/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package peccorina;

/**
 *
 * @author mario
 */
public class Mensajes {
    private String CorreoEmisor;
    private String CorreReceptor;
    private String mensaje;

    public Mensajes(String CorreoEmisor, String mensaje, String CorreReceptor) {
        this.CorreoEmisor = CorreoEmisor;
        this.CorreReceptor = CorreReceptor;
        this.mensaje = mensaje;
    }

    public String getCorreoEmisor() {
        return CorreoEmisor;
    }

    public String getCorreReceptor() {
        return CorreReceptor;
    }

    public String getMensaje() {
        return mensaje;
    }
    
    
}
