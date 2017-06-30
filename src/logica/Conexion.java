/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logica;

import com.mysql.jdbc.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.swing.JOptionPane;


/**
 *
 * @author usuario
 */
public class Conexion {

    public String db = "runakuna_dev";
    public String url = "jdbc:mysql://127.0.0.1/" + db;
    public String pas = "123456";
    public String user = "root";

    public Conexion() {
    }

    public Connection conectar() {
        Connection link = null;
        try {
            Class.forName("org.gjt.mm.mysql.Driver");
            link = (Connection) DriverManager.getConnection(url, user, pas);
        } catch ( ClassNotFoundException e) {
            JOptionPane.showConfirmDialog(null, e);        
        } catch ( SQLException e) {
            JOptionPane.showConfirmDialog(null, e);
        }
        return link;
    } 
//    public static void main(String[] args) {
//        Conexion c  = new Conexion();
//        c.conectar();
//    }
}
