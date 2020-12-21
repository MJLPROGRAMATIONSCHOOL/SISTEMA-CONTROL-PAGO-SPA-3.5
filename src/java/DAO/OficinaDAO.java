package DAO;

import Conexion.Conexion;
import Modelo.Oficina;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author lainc
 */
public class OficinaDAO extends Conexion {

    public List<Oficina> listar() throws Exception {
        List<Oficina> oficinas;
        Oficina ofi;
        ResultSet rs = null;
        String sql = "select * from oficina";
        try {
            this.conectar(false);
            rs = this.ejecutarOrdenDatos(sql);
            oficinas = new ArrayList<>();
            while (rs.next() == true) {
                ofi = new Oficina();
                ofi.setIdOficina(rs.getInt(1));
                ofi.setNombre(rs.getString(2));
                ofi.setDireccion(rs.getString(3));
                ofi.setEstado(rs.getBoolean(4));
                oficinas.add(ofi);
            }
        } catch (Exception e) {
            throw e;
        } finally {
            if (rs != null && rs.isClosed() == false) {
                rs.close();
            }
            rs = null;
        }
        return oficinas;
    }

    public void registrar(Oficina oficina) throws Exception {
        String sql;
        sql = "insert into oficina (Nombre, Direccion, Estado)"
                + "values('" + oficina.getNombre() + "','"
                + oficina.getDireccion() + "',"
                + (oficina.isEstado() == true ? "1" : "0") + ")";
        try {
            this.conectar(false);
            this.ejecutarOrden(sql);
        } catch (Exception e) {
            throw e;
        } finally {
        }
    }

    public Oficina leer(Oficina oficina) throws Exception {
        Oficina ofc = null;
        ResultSet rs = null;
        String sql = "select O.Nombre, O.Direccion, O.Estado from oficina O where O.IdOficina = " + oficina.getIdOficina();

        try {
            this.conectar(false);
            rs = this.ejecutarOrdenDatos(sql);
            if (rs.next() == true) {
                ofc = new Oficina();
                ofc.setIdOficina(oficina.getIdOficina());
                ofc.setNombre(rs.getString("Nombre"));
                ofc.setDireccion(rs.getString("Direccion"));
                ofc.setEstado(rs.getBoolean("estado"));
            }
        } catch (Exception e) {
            throw e;
        } finally {
            if (rs != null && rs.isClosed() == false) {
                rs.close();
            }
            rs = null;
        }
        return ofc;
    }

    public void actualizar(Oficina oficina) throws Exception {
        String sql;
        sql = "UPDATE oficina SET Nombre = '" + oficina.getNombre()
                + "', Direccion = '" + oficina.getDireccion()
                + "', Estado = " + (oficina.isEstado() == true ? "1" : "0")
                + " WHERE IdOficina = " + oficina.getIdOficina();
        try {
            this.conectar(false);
            this.ejecutarOrden(sql);
        } catch (Exception e) {
            throw e;
        } finally {
        }
    }

    public void eliminar(Oficina oficina) throws Exception {
        String sql = "delete from oficina where IdOficina = " + oficina.getIdOficina();
        try {
            this.conectar(false);
            this.ejecutarOrden(sql);
        } catch (Exception e) {
            throw e;
        } finally {

        }
    }
}
