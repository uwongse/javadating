/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAOPeccorina;

import Disenno.VistaUsuario;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import peccorina.Admin;
import peccorina.Cliente;
import peccorina.Mensajes;
import peccorina.Usuario;
import peccorina.Vip;

/**
 *
 * @author alumno
 */
public class DAOPeccorina {

    static DAOPeccorina instancia = null;

    public static DAOPeccorina instancia() {
        if (instancia == null) {
            instancia = new DAOPeccorina();
        }
        return instancia;
    }

    public ArrayList<Cliente> buscarCliente(String buscar) throws SQLException {
        ArrayList Cliente = new ArrayList();
        String query = "SELECT  sexo, edad, orientacion, ciudad, telefono, likedado, likerecibo, correo FROM cliente WHERE sexo LIKE'%" + buscar + "%' OR edad LIKE'%" + buscar + "%' OR orientacion LIKE'%" + buscar + "%' OR ciudad LIKE'%" + buscar + "%' OR telefono LIKE '%" + buscar + "%' OR likedado LIKE'%" + buscar + "%' OR likerecibo LIKE'%" + buscar + "%' OR correo LIKE'%" + buscar + "%' ";

        PreparedStatement ps = ConexionDB.instancia().getConnection().prepareStatement(query);

        ResultSet resultado = ps.executeQuery();

        while (resultado.next()) {
            Cliente.add(new Cliente(resultado.getString(1), resultado.getInt(2), resultado.getString(3), resultado.getString(4),
                    resultado.getString(5), resultado.getInt(6), resultado.getInt(7), resultado.getString(8)));
        }

        return Cliente;
    }

    public ArrayList<Cliente> verClientes() throws SQLException {

        ArrayList Cliente = new ArrayList();

        String query = ("SELECT sexo, edad, orientacion, ciudad, telefono, likedado, likerecibo, correo FROM cliente");
        PreparedStatement ps = ConexionDB.instancia().getConnection().prepareStatement(query);

        ResultSet resultado = ps.executeQuery();
        while (resultado.next()) {
            Cliente.add(new Cliente(resultado.getString(1), resultado.getInt(2), resultado.getString(3), resultado.getString(4),
                    resultado.getString(5), resultado.getInt(6), resultado.getInt(7), resultado.getString(8)));

        }

        return Cliente;
    }

    public ArrayList<Mensajes> verMensajes(Usuario u) throws SQLException {

        ArrayList Mensajes = new ArrayList();
        //  
        String query = ("SELECT correoemisor,mensaje,correoreceptor FROM mensaje WHERE correoemisor= '" + u.getCorreoUsuario() + "' OR correoreceptor= '" + u.getCorreoUsuario() + "' ");
        PreparedStatement ps = ConexionDB.instancia().getConnection().prepareStatement(query);

        ResultSet resultado = ps.executeQuery();
        while (resultado.next()) {
            Mensajes.add(new Mensajes(resultado.getString(1), resultado.getString(2), resultado.getString(3)));

        }

        return Mensajes;
    }

    public ArrayList<Cliente> verlikes(Usuario u) throws SQLException {

        ArrayList Cliente = new ArrayList();
        //  
        String query = ("SELECT liker FROM fav WHERE liked= '" + u.getCorreoUsuario() + "' ");
        PreparedStatement ps = ConexionDB.instancia().getConnection().prepareStatement(query);

        ResultSet resultado = ps.executeQuery();
        while (resultado.next()) {
            Cliente.add(new Cliente(resultado.getString(1)));

        }

        return Cliente;
    }

    public void crearMensaje(Mensajes m) {
        try {

            String insertInto = "INSERT INTO mensaje"
                    + "(correoemisor,mensaje,correoreceptor) VALUES (?,?,?)";

            PreparedStatement ps1 = ConexionDB.instancia().getConnection().prepareStatement(insertInto);
            ps1.setString(1, m.getCorreoEmisor());
            ps1.setString(2, m.getMensaje());
            ps1.setString(3, m.getCorreReceptor());

            ps1.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void crearUsuario(Usuario u) {
        try {
            if (u instanceof Cliente) {
                Cliente c = (Cliente) u;

                String insertInto = "INSERT INTO usuario"
                        + "(nombre,correo,contrasenna,roll) VALUES (?,?,?,?)";

                PreparedStatement ps1 = ConexionDB.instancia().getConnection().prepareStatement(insertInto);
                ps1.setString(1, c.getNombreUsuario());
                ps1.setString(2, c.getCorreoUsuario());
                ps1.setString(3, c.getContrasennaUsuario());
                ps1.setString(4, "cliente");

                ps1.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void modificarUsuario(Usuario u) {
        try {
            String insertInto = "UPDATE usuario SET nombre = ?, contrasenna = ?, roll = ? WHERE correo = '" + u.getCorreoUsuario() + "'";
            PreparedStatement ps1 = ConexionDB.instancia().getConnection().prepareStatement(insertInto);
            ps1.setString(1, u.getNombreUsuario());
            ps1.setString(2, u.getContrasennaUsuario());
            ps1.setString(3, u.getRoll());

            ps1.executeUpdate();
            ps1.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void actualizarFoto(Cliente u) {
        try {
            String insertInto = "UPDATE cliente"
                    + " SET imagen = ?"
                    + " WHERE correo = '" + u.getCorreoUsuario() + "'";

            PreparedStatement ps1 = ConexionDB.instancia().getConnection().prepareStatement(insertInto);
            ps1.setBinaryStream(1, u.getFotoUsuario());

            ps1.executeUpdate();
            ps1.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void crearCliente(Usuario u, String url) {
        try {

            if (u instanceof Cliente) {
                Cliente c = (Cliente) u;
                File f = new File(url);
                FileInputStream fis = new FileInputStream(f);

                String insertInto = "INSERT INTO cliente"
                        + " VALUES (?,?,?,?,?,?,?,?,?)";
                PreparedStatement ps2 = ConexionDB.instancia().getConnection().prepareStatement(insertInto);
                ps2.setString(1, c.getSexoUsuario());
                ps2.setInt(2, c.getEdadUsuario());
                ps2.setString(3, c.getOrientacionSexual());
                ps2.setString(4, c.getCiudadUsuario());
                ps2.setString(5, c.getTelefonoUsuario());
                ps2.setBinaryStream(6, fis, (int) f.length());
                ps2.setInt(7, c.getDarLikeContador());
                ps2.setInt(8, c.getRecibirLikeContador());
                ps2.setString(9, c.getCorreoUsuario());

                ps2.executeUpdate();
                ps2.close();
                fis.close();
            }
        } catch (SQLException | FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException ex) {
            Logger.getLogger(DAOPeccorina.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void crearVip(Usuario u) {
        try {
            String update = "update usuario set roll = 'vip' where correo ='" + u.getCorreoUsuario() + "'";
            PreparedStatement ps = ConexionDB.instancia().getConnection().prepareStatement(update);

            ps.executeUpdate();
            ps.close();

            //u.setRoll("vip");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public Usuario buscarUsuario(String correoUsuario, String contrasenna) {
        Usuario u = null;
        String nombre, roll;
        try {
            ResultSet rs = ConexionDB.instancia().getStatement().executeQuery(
                    "select nombre, correo, contrasenna, roll "
                    + "from usuario "
                    + "where correo= '" + correoUsuario + "' and contrasenna = '" + contrasenna + "'");
            if (rs.next()) {
                nombre = rs.getString(1);
                roll = rs.getString(4);

                //USUARIO CLIENTE
                if (rs.getString(4).equals("cliente")) {

                    ResultSet rsi = ConexionDB.instancia().getStatement().executeQuery(
                            "select sexo, edad, orientacion, ciudad, telefono, likedado, likerecibo "
                            + "from cliente "
                            + "where correo='" + correoUsuario + "'");

                    while (rsi.next()) {

                        u = new Cliente(rsi.getString(1), rsi.getInt(2), rsi.getString(3),
                                rsi.getString(4), rsi.getString(5), rsi.getInt(6),
                                rsi.getInt(7), nombre, correoUsuario, contrasenna, roll);
                        return u;
                    }
                }

                //USUARIO VIP
                if (rs.getString(4).equals("vip")) {
                    nombre = rs.getString(1);
                    roll = rs.getString(4);
                    ResultSet rs2 = ConexionDB.instancia().getStatement().executeQuery(
                            "select sexo, edad, orientacion, ciudad, telefono, likedado, likerecibo "
                            + "from cliente "
                            + "where correo= '" + correoUsuario + "'");

                    while (rs2.next()) {

                        u = new Vip(rs2.getString(1), rs2.getInt(2), rs2.getString(3),
                                rs2.getString(4), rs2.getString(5), rs2.getInt(6),
                                rs2.getInt(7), nombre, correoUsuario, contrasenna, roll);
                        return u;
                    }
                }

                //USUARIO ADMINISTRADOR
                if (rs.getString(4).equals("admin")) {
                    u = new Admin(nombre, correoUsuario, rs.getString(3), rs.getString(4));
                    return u;
                }

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return u;

    }

    public void eliminarUsuario(Usuario u) {

        try {
            ConexionDB.instancia().getStatement().execute(
                    "delete from cliente where "
                    + "correo = '" + u.getCorreoUsuario() + "'"
            );

            ConexionDB.instancia().getStatement().execute(
                    "delete from usuario where "
                    + "correo = '" + u.getCorreoUsuario() + "'"
            );
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public ImageIcon buscarFoto(String c) {
        ImageIcon ii = null;
        InputStream is = null;
        try {
            ResultSet rs = ConexionDB.instancia().getStatement().executeQuery(
                    "select imagen from cliente where correo = '" + c + "'"
            );

            if (rs.next()) {
                is = rs.getBinaryStream(1);
                if (is != null) {
                    BufferedImage bi = ImageIO.read(is);
                    ii = new ImageIcon(bi);
                }
            }
        } catch (SQLException | IOException e) {
        }
        return ii;
    }

    public void buscarPareja(Cliente c, VistaUsuario p) {
        Usuario u;
        String sexo, telefono, correo, orientacion;
        int edad, likedado, likerecibo;
        try {

            //ORIENTACION HETEROSEXUAL
            if (c.getOrientacionSexual().equals("heterosexual")) {
                ResultSet rs = ConexionDB.instancia().getStatement().executeQuery(
                        "select c.sexo, c.edad, c.orientacion, c.telefono, c.likedado, c.likerecibo, c.correo, u.nombre, u.contrasenna, u.roll "
                        + "from cliente c, usuario u "
                        + "where ciudad = '" + c.getCiudadUsuario() + "' and "
                        + "orientacion != 'homosexual' and "
                        + "sexo != '" + c.getSexoUsuario() + "' and "
                        + "c.correo = u.correo"
                );
                while (rs.next()) {
                    sexo = rs.getString(1);
                    edad = rs.getInt(2);
                    orientacion = rs.getString(3);
                    telefono = rs.getString(4);
                    likedado = rs.getInt(5);
                    likerecibo = rs.getInt(6);
                    correo = rs.getString(7);

                    //USUARIO CLIENTE
                    if (!correo.equals(c.getCorreoUsuario())) {
                        if (rs.getString(10).equals("cliente")) {

                            u = new Cliente(sexo, edad, orientacion, c.getCiudadUsuario(),
                                    telefono, likedado, likerecibo, rs.getString(8),
                                    correo, rs.getString(9), "cliente");

                            p.annadirPareja((Cliente) u);
                        }

                        //USUARIO VIP
                        if (rs.getString(10).equals("vip")) {

                            u = new Vip(sexo, edad, orientacion, c.getCiudadUsuario(),
                                    telefono, likedado, likerecibo, rs.getString(8),
                                    correo, rs.getString(9), "vip");

                            p.annadirPareja((Vip) u);
                        }
                    }

                }
            }

            //ORIENTACION HOMOSEUAL
            if (c.getOrientacionSexual().equals("homosexual")) {
                ResultSet rs = ConexionDB.instancia().getStatement().executeQuery(
                        "select c.sexo, c.edad, c.orientacion, c.telefono, c.likedado, c.likerecibo, c.correo, u.nombre, u.contrasenna, u.roll "
                        + "from cliente c, usuario u "
                        + "where c.ciudad = '" + c.getCiudadUsuario() + "' and "
                        + "c.orientacion != 'heterosexual' and "
                        + "c.sexo = '" + c.getSexoUsuario() + "' and "
                        + "c.correo = u.correo"
                );

                while (rs.next()) {
                    sexo = rs.getString(1);
                    edad = rs.getInt(2);
                    orientacion = rs.getString(3);
                    telefono = rs.getString(4);
                    likedado = rs.getInt(5);
                    likerecibo = rs.getInt(6);
                    correo = rs.getString(7);

                    //USUARIO CLIENTE
                    if (!correo.equals(c.getCorreoUsuario())) {

                        if (rs.getString(10).equals("cliente")) {

                            u = new Cliente(sexo, edad, orientacion, c.getCiudadUsuario(),
                                    telefono, likedado, likerecibo, rs.getString(8),
                                    correo, rs.getString(9), "cliente");

                            p.annadirPareja((Cliente) u);

                        }
                        //USUARIO VIP
                        if (rs.getString(10).equals("vip")) {

                            u = new Vip(sexo, edad, orientacion, c.getCiudadUsuario(),
                                    telefono, likedado, likerecibo, rs.getString(8),
                                    correo, rs.getString(9), "vip");

                            p.annadirPareja((Vip) u);
                        }

                    }
                }
            }

            //ORIENTACION BISEXUAL HOMBRE
            if (c.getOrientacionSexual().equals("bisexual") && c.getSexoUsuario().equals("hombre")) {
                ResultSet rs = ConexionDB.instancia().getStatement().executeQuery(
                        "select c.sexo, c.edad, c.orientacion, c.telefono, c.likedado, c.likerecibo, c.correo, u.nombre, u.contrasenna, u.roll "
                        + "from cliente c, usuario u "
                        + "where c.ciudad = '" + c.getCiudadUsuario() + "' and "
                        + "c.correo = u.correo"
                );

                while (rs.next()) {

                    sexo = rs.getString(1);
                    edad = rs.getInt(2);
                    orientacion = rs.getString(3);
                    telefono = rs.getString(4);
                    likedado = rs.getInt(5);
                    likerecibo = rs.getInt(6);
                    correo = rs.getString(7);

                    //USUARIO CLIENTE
                    if (!correo.equals(c.getCorreoUsuario())) {

                        if (rs.getString(10).equals("cliente")) {

                            if (rs.getString(1).equals("mujer") && rs.getString(3).equals("heterosexual")) {

                                u = new Cliente(sexo, edad, orientacion, c.getCiudadUsuario(),
                                        telefono, likedado, likerecibo, rs.getString(8),
                                        correo, rs.getString(9), "cliente");

                                p.annadirPareja((Cliente) u);
                            }

                            if (rs.getString(1).equals("hombre") && rs.getString(3).equals("homosexual")) {

                                u = new Cliente(sexo, edad, orientacion, c.getCiudadUsuario(),
                                        telefono, likedado, likerecibo, rs.getString(8),
                                        correo, rs.getString(9), "cliente");

                                p.annadirPareja((Cliente) u);
                            }

                            if (orientacion.equals("bisexual")) {
                                u = new Cliente(sexo, edad, orientacion, c.getCiudadUsuario(),
                                        telefono, likedado, likerecibo, rs.getString(8),
                                        correo, rs.getString(9), "cliente");

                                p.annadirPareja((Cliente) u);
                            }
                        }

                        //USUARIO VIP
                        if (rs.getString(10).equals("vip")) {
                            if (rs.getString(1).equals("mujer") && rs.getString(3).equals("heterosexual")) {

                                u = new Vip(sexo, edad, orientacion, c.getCiudadUsuario(),
                                        telefono, likedado, likerecibo, rs.getString(8),
                                        correo, rs.getString(9), "vip");

                                p.annadirPareja((Cliente) u);
                            }

                            if (rs.getString(1).equals("hombre") && rs.getString(3).equals("homosexual")) {

                                u = new Vip(sexo, edad, orientacion, c.getCiudadUsuario(),
                                        telefono, likedado, likerecibo, rs.getString(8),
                                        correo, rs.getString(9), "vip");

                                p.annadirPareja((Cliente) u);
                            }

                            if (orientacion.equals("bisexual")) {
                                u = new Vip(sexo, edad, orientacion, c.getCiudadUsuario(),
                                        telefono, likedado, likerecibo, rs.getString(8),
                                        correo, rs.getString(9), "cliente");

                                p.annadirPareja((Vip) u);

                            }
                        }

                    }
                }
            }

            //ORIENTACION BISEXUAL MUJER
            if (c.getOrientacionSexual().equals("bisexual") && c.getSexoUsuario().equals("mujer")) {
                ResultSet rs = ConexionDB.instancia().getStatement().executeQuery(
                        "select c.sexo, c.edad, c.orientacion, c.telefono, c.likedado, c.likerecibo, c.correo, u.nombre, u.contrasenna, u.roll "
                        + "from cliente c, usuario u "
                        + "where c.ciudad = '" + c.getCiudadUsuario() + "' and "
                        + "c.correo = u.correo"
                );

                while (rs.next()) {
                    sexo = rs.getString(1);
                    edad = rs.getInt(2);
                    orientacion = rs.getString(3);
                    telefono = rs.getString(4);
                    likedado = rs.getInt(5);
                    likerecibo = rs.getInt(6);
                    correo = rs.getString(7);

                    //USUARIO CLIENTE
                    if (!correo.equals(c.getCorreoUsuario())) {

                        if (rs.getString(10).equals("cliente")) {

                            if (rs.getString(1).equals("mujer") && rs.getString(3).equals("homosexual")) {
                                u = new Cliente(sexo, edad, orientacion, c.getCiudadUsuario(),
                                        telefono, likedado, likerecibo, rs.getString(8),
                                        correo, rs.getString(9), "cliente");

                                p.annadirPareja((Cliente) u);
                            }

                            if (rs.getString(1).equals("hombre") && rs.getString(3).equals("heterosexual")) {
                                u = new Cliente(sexo, edad, orientacion, c.getCiudadUsuario(),
                                        telefono, likedado, likerecibo, rs.getString(8),
                                        correo, rs.getString(9), "cliente");

                                p.annadirPareja((Cliente) u);
                            }

                            if (rs.getString(3).equals("bisexual")) {
                                u = new Cliente(sexo, edad, orientacion, c.getCiudadUsuario(),
                                        telefono, likedado, likerecibo, rs.getString(8),
                                        correo, rs.getString(9), "cliente");

                                p.annadirPareja((Cliente) u);
                            }
                        }

                        //USUARIO VIP
                        if (rs.getString(10).equals("vip")) {
                            if (rs.getString(1).equals("mujer") && rs.getString(3).equals("homosexual")) {

                                u = new Vip(sexo, edad, orientacion, c.getCiudadUsuario(),
                                        telefono, likedado, likerecibo, rs.getString(8),
                                        correo, rs.getString(9), "vip");

                                p.annadirPareja((Cliente) u);
                            }

                            if (rs.getString(1).equals("hombre") && rs.getString(3).equals("heterosexual")) {

                                u = new Vip(sexo, edad, orientacion, c.getCiudadUsuario(),
                                        telefono, likedado, likerecibo, rs.getString(8),
                                        correo, rs.getString(9), "vip");

                                p.annadirPareja((Cliente) u);
                            }

                            if (rs.getString(3).equals("bisexual")) {
                                u = new Vip(sexo, edad, orientacion, c.getCiudadUsuario(),
                                        telefono, likedado, likerecibo, rs.getString(8),
                                        correo, rs.getString(9), "cliente");

                                p.annadirPareja((Vip) u);

                            }
                        }
                    }

                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public int ContadorUsuarios(Usuario u){
        int i=0;
        try {
              ResultSet rs = ConexionDB.instancia().getStatement().executeQuery("select likerecibo from cliente where correo= '" + u.getCorreoUsuario() + "' ");
               while (rs.next()) {
                  int likerecibo = rs.getInt(1);
                   i=likerecibo;
               }
            
         } catch (SQLException e) {
            e.printStackTrace();
        }
        return i;
    }
    

    public Usuario todoUsuarios() {
        Usuario u = null;
        String nombre, correo, contrasenna, roll, sexo, orientacion, ciudad, telefono;
        int edad, likedado, likerecibo;
        try {
            ResultSet rs = ConexionDB.instancia().getStatement().executeQuery(
                    "select u.nombre, u.correo, u.contrasenna, u.roll, c.sexo, c.edad, c.orientacion, c.ciudad, c.telefono, c.likedado, c.likerecibo"
                    + "cliente c");
            while (rs.next()) {
                nombre = rs.getString(1);
                correo = rs.getString(2);
                contrasenna = rs.getString(3);
                roll = rs.getString(4);
                sexo = rs.getString(5);
                edad = rs.getInt(6);
                orientacion = rs.getString(7);
                ciudad = rs.getString(8);
                telefono = rs.getString(9);
                likedado = rs.getInt(10);
                likerecibo = rs.getInt(11);

                if (roll.equals("cliente")) {
                    u = new Cliente(sexo, edad, orientacion, ciudad, telefono, likedado, likerecibo, nombre, correo, contrasenna, roll);

                }

                if (roll.equals("vip")) {
                    u = new Vip(sexo, edad, orientacion, ciudad, telefono, likedado, likerecibo, nombre, correo, contrasenna, roll);

                }

                if (roll.equals("admin")) {
                    u = new Admin(nombre, correo, contrasenna, roll);

                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return u;
    }

    public void enviarMensaje(String correoE, String mensaje, String correoR) {
        try {
            String insertInto = "INSERT INTO mensaje"
                    + " VALUES (?,?,?)";

            PreparedStatement ps1 = ConexionDB.instancia().getConnection().prepareStatement(insertInto);
            ps1.setString(1, correoE);
            ps1.setString(2, mensaje);
            ps1.setString(3, correoR);

            ps1.executeUpdate();
            ps1.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void actualizarCliente(Cliente c) {
        try {
            String update = "UPDATE cliente"
                    + " SET sexo = ?, edad = ?, orientacion = ?, ciudad = ?, telefono = ?, likedado = ?, likerecibo = ?"
                    + " WHERE correo = '" + c.getCorreoUsuario() + "'";

            PreparedStatement ps = ConexionDB.instancia().getConnection().prepareStatement(update);
            ps.setString(1, c.getSexoUsuario());
            ps.setInt(2, c.getEdadUsuario());
            ps.setString(3, c.getOrientacionSexual());
            ps.setString(4, c.getCiudadUsuario());
            ps.setString(5, c.getTelefonoUsuario());
            ps.setInt(6, c.getDarLikeContador());
            ps.setInt(7, c.getRecibirLikeContador());

            ps.executeUpdate();
            ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void actualizarFav(Cliente c, Cliente c2) {
        try {
            String insertInto = "INSERT INTO fav VALUES(?,?)";
            PreparedStatement ps = ConexionDB.instancia().getConnection().prepareStatement(insertInto);
            ps.setString(1, c.getCorreoUsuario());
            ps.setString(2, c2.getCorreoUsuario());

            ps.executeUpdate();
            ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public boolean comprobarLike(Cliente c, Cliente c2) {
        boolean fav = false;
        try {
            ResultSet rs = ConexionDB.instancia().getStatement().executeQuery(
                    "select liked "
                    + "from fav "
                    + "where liker = '" + c.getCorreoUsuario() + "' and liked = '" + c2.getCorreoUsuario() + "'"
            );

            if (rs.next()) {
                fav = true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return fav;
    }
}
