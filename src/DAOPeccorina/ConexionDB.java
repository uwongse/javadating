/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAOPeccorina;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author alumno
 */
public class ConexionDB {

    Connection conn;
    Statement stmt;

    static ConexionDB instancia = null;

    private ConexionDB() {
        try {
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/peccorina", "root", "");
            stmt = conn.createStatement();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Connection getConnection() {
        return conn; 
    }

    public Statement getStatement() {
        return stmt;
    }

    public static void crearConexion() {
        if (instancia == null) {
            instancia = new ConexionDB();
        }
    }

    public static ConexionDB instancia() {
        return instancia;
    }

    public static void desconectar() {
        if (instancia != null) {
            try {
                //instancia.stmt.execute("shutdown");
                instancia.stmt.close();
                instancia.conn.close();
                instancia = null;
            } catch (SQLException e) {
            }
        }
    }
}
