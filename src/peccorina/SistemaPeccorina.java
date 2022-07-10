/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package peccorina;

import DAOPeccorina.ConexionDB;
import DAOPeccorina.DAOPeccorina;
import Disenno.VistaUsuario;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.ImageIcon;

/**
 *
 * @author alumno
 */
public class SistemaPeccorina {

    public SistemaPeccorina() {
        ConexionDB.crearConexion();
    }

    public ArrayList VerLikes(Usuario u) {
        ArrayList<Cliente> cliente;

        try {
            cliente = DAOPeccorina.instancia().verlikes(u);
        } catch (SQLException ex) {
            cliente = null;
            System.out.println(ex.getSQLState());
            ex.getStackTrace();
        }

        return cliente;
    }
      public int getLike(Usuario u)  {

        return DAOPeccorina.instancia().ContadorUsuarios(u);

    }

    public ArrayList VerClientes() {
        ArrayList<Cliente> cliente;

        try {
            cliente = DAOPeccorina.instancia().verClientes();
        } catch (SQLException ex) {
            cliente = null;
            System.out.println(ex.getSQLState());
            ex.getStackTrace();
        }

        return cliente;
    }

    public ArrayList buscarClientes(String buscar) {
        ArrayList<Cliente> cliente;

        try {
            cliente = DAOPeccorina.instancia().buscarCliente(buscar);
        } catch (SQLException ex) {
            cliente = null;
            System.out.println(ex.getSQLState());
            ex.getStackTrace();
        }

        return cliente;
    }

    public ArrayList VerMensajes(Usuario c) {
        ArrayList<Mensajes> mensajes;

        try {
            mensajes = DAOPeccorina.instancia().verMensajes(c);
        } catch (SQLException ex) {
            mensajes = null;
            System.out.println(ex.getSQLState());
            ex.getStackTrace();
        }

        return mensajes;
    }

    public Usuario buscarUsuario(String correoUsuario, String contrasenna) {
        return DAOPeccorina.instancia().buscarUsuario(correoUsuario, contrasenna);
    }

    public void crearMensaje(Mensajes m) {
        DAOPeccorina.instancia().crearMensaje(m);
    }

    public void crearUsuario(Usuario u) {
        DAOPeccorina.instancia().crearUsuario(u);
    }

    public void modificarUsuario(Usuario u) {
        DAOPeccorina.instancia().modificarUsuario(u);
    }

    public void crearCliente(Usuario u, String url) {
        DAOPeccorina.instancia().crearCliente(u, url);
    }

    public void crearVip(Usuario u) {
        DAOPeccorina.instancia().crearVip(u);
    }

    public void eliminarUsuario(Usuario u) {
        DAOPeccorina.instancia().eliminarUsuario(u);
    }

    public ImageIcon buscarFoto(String c) {
        return DAOPeccorina.instancia().buscarFoto(c);
    }

    public void buscarPareja(Cliente c, VistaUsuario p) {
        DAOPeccorina.instancia().buscarPareja(c, p);
    }

    public void actualizarFoto(Cliente c) {
        DAOPeccorina.instancia().actualizarFoto(c);
    }

    public void enviarMensaje(String correoEmisor, String mensaje, String correoReceptor) {
        DAOPeccorina.instancia().enviarMensaje(correoEmisor, mensaje, correoReceptor);
    }

    public void actualizarCliente(Cliente c) {
        DAOPeccorina.instancia().actualizarCliente(c);
    }

    public void actualizarFav(Cliente c, Cliente c2) {
        DAOPeccorina.instancia().actualizarFav(c, c2);
    }

    public boolean comprobarLike(Cliente c, Cliente c2) {
        return DAOPeccorina.instancia().comprobarLike(c, c2);
    }
}
