/*
 * MServidor.java
 *
 * Created on 1 de noviembre de 2007, 22:29
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */
package Chat;

import java.net.ServerSocket;
import java.net.Socket;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 *
 * @author Administrador
 */
public class MServidor extends Thread {

    private int port;
    private JFrame ventana;

    /**
     * Creates a new instance of MServidor
     */
    public MServidor(JFrame ventana, int port) {
        this.port = port;
        this.ventana = ventana;
    }

    public MServidor(int port) {
        this.port = port;
    }

    public int getPort() {
        return port;
    }

    public void run() {
        ServerSocket ss = null;
        try {
            ss = new ServerSocket(port);
            while (true) {
                Socket s = ss.accept();
                MSGestorConexiones.getInstance().conectaNuevo(new MSConexion(s));
            }
            //JOptionPane.showMessageDialog(ventana,"Se han conectado");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(ventana, "foro con gente.");
        }
        try {
            ss.close();
        } catch (Exception e) {
        }
    }

}
