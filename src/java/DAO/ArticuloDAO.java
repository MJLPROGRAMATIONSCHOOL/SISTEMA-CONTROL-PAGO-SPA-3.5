package DAO;

import Conexion.Conexion;
import Modelo.Articulo;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author lainc
 */
public class ArticuloDAO extends Conexion {

    public List<Articulo> listar() throws Exception {
        List<Articulo> articulos;
        Articulo arc;
        ResultSet rs = null;
        String sql = "select * from articulo";
        try {
            this.conectar(false);
            rs = this.ejecutarOrdenDatos(sql);
            articulos = new ArrayList<>();
            while (rs.next() == true) {
                arc = new Articulo();
                arc.setIdArticulo(rs.getInt(1));
                arc.setServicio(rs.getString(2));
                arc.setDescripcion(rs.getString(3));
                arc.setPrecio(rs.getDouble(4));
                arc.setEstado(rs.getBoolean(5));
                articulos.add(arc);
            }
        } catch (Exception e) {
            throw e;
        } finally {
            if (rs != null && rs.isClosed() == false) {
                rs.close();
            }
            rs = null;
        }
        return articulos;
    }

    public Articulo listarId(int id) {
        Articulo a = new Articulo();
        String sql = "select * from articulo where IdArticulo=" + id;
        ResultSet rs = null;
        try {
            this.conectar(false);
            rs = this.ejecutarOrdenDatos(sql);
            while (rs.next()) {
                a.setIdArticulo(rs.getInt(1));
                a.setServicio(rs.getString(2));
                a.setDescripcion(rs.getString(4));
                a.setPrecio(rs.getDouble(5));
            }
        } catch (Exception e) {
        }
        return a;
    }
}
