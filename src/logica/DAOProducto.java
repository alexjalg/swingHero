/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logica;

import datos.Producto;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
/*
import datos.Habitacion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
*/
/**
 *
 * @author usuario
 */
public class DAOProducto {

    private Conexion mysql = new Conexion();
    private Connection cn = mysql.conectar();
    private int totalRegistros = 0;

    public boolean eliminar(Producto producto) {
        try {
            PreparedStatement ps = cn.prepareStatement("DELETE FROM producto WHERE idproducto=?");
            ps.setInt(1, producto.getIdProducto());
            if (ps.executeUpdate() == 0) {
                return false;
            } else {
                return true;
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
            return false;
        }
    }

    public boolean editar(Producto producto) {
        try {
            PreparedStatement ps = cn.prepareStatement("UPDATE producto SET nombre=?, descripcion=?, unidad_medida=?, precio_venta=? WHERE idproducto=?");
            ps.setString(1, producto.getNombre());
            ps.setString(2, producto.getDescripcion());
            ps.setString(3, producto.getUnidadMedida());
            ps.setString(4, producto.getPrecioVenta());
            ps.setInt(5, producto.getIdProducto());
            if (ps.executeUpdate() == 0) {
                return false;
            } else {
                return true;
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
            return false;
        }
    }

    public boolean insertar(Producto producto) {
        String SQL = "INSERT INTO producto(nombre, descripcion, unidad_medida, precio_venta) VALUES(?,?,?,?)";
        try {
            PreparedStatement ps = cn.prepareStatement(SQL);
            ps.setString(1, producto.getNombre());
            ps.setString(2, producto.getDescripcion());
            ps.setString(3, producto.getUnidadMedida());
            ps.setString(4, producto.getPrecioVenta());
            if (ps.executeUpdate() == 0) {
                return false;
            } else {
                return true;
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
            return false;
        }
    }

    public DefaultTableModel mostrar(String buscar) {
        String[] registros = {"ID", "Producto", "Descripción", "Unidad Medida", "Precio Venta"};
        DefaultTableModel dtm = new DefaultTableModel(null, registros);
        try {
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM producto WHERE nombre LIKE '%" + buscar + "%'  ORDER BY idproducto");
            while (rs.next()) {
                registros[0] = rs.getString("idproducto");
                registros[1] = rs.getString("nombre");
                registros[2] = rs.getString("descripcion");
                registros[3] = rs.getString("unidad_medida");
                registros[4] = rs.getString("precio_venta");
                dtm.addRow(registros);
                totalRegistros += 1;
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
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
