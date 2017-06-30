/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logica;

import datos.Habitacion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author usuario
 */
public class DAOHabitacion {

    private Conexion mysql = new Conexion();
    private Connection cn = mysql.conectar();
    private String sql = "";
    private int totalRegistros = 0;

    public boolean eliminar(Habitacion habitacion) {
        sql = "DELETE FROM habitacion WHERE idhabitacion=?";
        try {
            PreparedStatement ps = cn.prepareStatement(sql);
            ps.setInt(1, habitacion.getIdHabitacion());
            if (ps.executeUpdate() == 0) {
                return false;
            } else {
                return true;
            }
        } catch (Exception e) {
            JOptionPane.showConfirmDialog(null, e);
            return false;
        }
    }

    public boolean editar(Habitacion habitacion) {
        sql = "UPDATE habitacion "
                + " SET numero=?, piso=?, descripcion=?, caracteristicas=?, precio_diario=?, estado=?, tipo_habitacion=?"
                + " WHERE idhabitacion=? ";
        try {
            PreparedStatement ps = cn.prepareStatement(sql);
            ps.setString(1, habitacion.getNumero());
            ps.setString(2, habitacion.getPiso());
            ps.setString(3, habitacion.getDescripcion());
            ps.setString(4, habitacion.getCaracteristicas());
            ps.setString(5, habitacion.getPrecio_diario());
            ps.setString(6, habitacion.getEstado());
            ps.setString(7, habitacion.getTipo_habitacion());
            ps.setInt(8, habitacion.getIdHabitacion());
            if (ps.executeUpdate() == 0) {
                return false;
            } else {
                return true;
            }
        } catch (Exception e) {
            JOptionPane.showConfirmDialog(null, e);
            return false;
        }
    }

    public boolean insertar(Habitacion habitacion) {
        sql = "INSERT INTO "
                + "habitacion(numero,piso,descripcion,caracteristicas,precio_diario,estado,tipo_habitacion) "
                + "VALUES(?,?,?,?,?,?,?)";
        try {
            PreparedStatement ps = cn.prepareStatement(sql);
            ps.setString(1, habitacion.getNumero());
            ps.setString(2, habitacion.getPiso());
            ps.setString(3, habitacion.getDescripcion());
            ps.setString(4, habitacion.getCaracteristicas());
            ps.setString(5, habitacion.getPrecio_diario());
            ps.setString(6, habitacion.getEstado());
            ps.setString(7, habitacion.getTipo_habitacion());
            int n = ps.executeUpdate();
            if (n == 0) {
                return false;
            } else {
                return true;
            }
        } catch (Exception e) {
            JOptionPane.showConfirmDialog(null, e);
            return false;
        }
    }

    public DefaultTableModel mostrar(String buscar) {
        String[] registros = {"ID", "Número", "Piso", "Descripción", "Caracteristicas", "Precio", "Estado", "Tipo habitación"};
        DefaultTableModel dtm = new DefaultTableModel(null, registros);
        sql = "SELECT * FROM habitacion WHERE piso LIKE '%" + buscar + "%' ORDER BY idhabitacion";
        try {
            Statement s = cn.createStatement();
            ResultSet rs = s.executeQuery(sql);
            while (rs.next()) {
                registros[0] = rs.getString("idhabitacion");
                registros[1] = rs.getString("numero");
                registros[2] = rs.getString("piso");
                registros[3] = rs.getString("descripcion");
                registros[4] = rs.getString("caracteristicas");
                registros[5] = rs.getString("precio_diario");
                registros[6] = rs.getString("estado");
                registros[7] = rs.getString("tipo_habitacion");
                dtm.addRow(registros);
                totalRegistros += 1;
            }
        } catch (Exception e) {
            JOptionPane.showConfirmDialog(null, e);
        }
        return dtm;
    }

    public int getTotalRegistros() {
        return totalRegistros;
    }

    public void setTotalRegistros(int totalRegistros) {
        this.totalRegistros = totalRegistros;
    }

}
