package DAO;

import Conexion.Conexion;
import Modelo.Empresa;
import Modelo.Oficina;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author lainc
 */
public class EmpresaDAO extends Conexion {

    public List<Empresa> listar() throws Exception {
        List<Empresa> empresas;
        Empresa emp;
        ResultSet rs = null;
        String sql = "SELECT C.RutEmpresa, C.RazonSocial, C.Direccion, C.Giro, C.Estado, O.Nombre AS Oficina FROM Empresa C INNER JOIN Oficina O ON O.IdOficina = C.IdOficina ORDER BY C.RutEmpresa";

        try {
            this.conectar(false);
            rs = this.ejecutarOrdenDatos(sql);
            empresas = new ArrayList<>();
            while (rs.next() == true) {
                emp = new Empresa();
                emp.setRutEmpresa(rs.getInt("RutEmpresa"));
                emp.setRazonSocial(rs.getString("RazonSocial"));
                emp.setDireccion(rs.getString("Direccion"));
                emp.setGiro(rs.getString("Giro"));
                emp.setEstado(rs.getBoolean("Estado"));
                emp.setOficinas(new Oficina());
                emp.getOficinas().setNombre(rs.getString("Oficina"));
                empresas.add(emp);
            }
        } catch (Exception e) {
            throw e;
        } finally {
            if (rs != null && rs.isClosed() == false) {
                rs.close();
            }
            rs = null;
        }
        return empresas;
    }

    public void registrar(Empresa empresa) throws Exception {
        String sql;
        sql = "insert into empresa (RutEmpresa, RazonSocial, Direccion, Giro, Estado, IdOficina)"
                + "values('" + empresa.getRutEmpresa() + "','" + empresa.getRazonSocial() + "','" + empresa.getDireccion() + "','" + empresa.getGiro() + "',"
                + (empresa.isEstado() == true ? "1" : "0") + ","
                + empresa.getOficinas().getIdOficina() + ")";
        try {
            this.conectar(false);
            this.ejecutarOrden(sql);
        } catch (Exception e) {
            throw e;
        } finally {
        }
    }

    public Empresa leer(Empresa empresa) throws Exception {
        Empresa emp = null;
        ResultSet rs = null;
        String sql = "select C.RutEmpresa, C.RazonSocial, C.Direccion, C.Giro, C.Estado, C.IdOficina from Empresa C where C.RutEmpresa = " + empresa.getRutEmpresa();

        try {
            this.conectar(false);
            rs = this.ejecutarOrdenDatos(sql);
            if (rs.next() == true) {
                emp = new Empresa();
                emp.setRutEmpresa(rs.getInt("RutEmpresa"));
                emp.setRazonSocial(rs.getString("RazonSocial"));
                emp.setDireccion(rs.getString("Direccion"));
                emp.setGiro(rs.getString("Giro"));
                emp.setEstado(rs.getBoolean("Estado"));
                emp.setOficinas(new Oficina());
                emp.getOficinas().setIdOficina(rs.getInt("IdOficina"));
            }
        } catch (Exception e) {
            throw e;
        } finally {
            if (rs != null && rs.isClosed() == false) {
                rs.close();
            }
            rs = null;
        }
        return emp;
    }

    public void actualizar(Empresa empresa) throws Exception {
        String sql;
        sql = "update empresa set RazonSocial = '" + empresa.getRazonSocial()
                + "', Direccion = '" + empresa.getDireccion()
                + "', Giro = '" + empresa.getGiro()
                + "', Estado = " + (empresa.isEstado() == true ? "1" : "0")
                + ", IdOficina = " + empresa.getOficinas().getIdOficina()
                + " where RutEmpresa = " + empresa.getRutEmpresa();
        try {
            this.conectar(false);
            this.ejecutarOrden(sql);
        } catch (Exception e) {
            throw e;
        } finally {
        }
    }

    public void eliminar(Empresa empresa) throws Exception {
        String sql = "delete from empresa where RutEmpresa = " + empresa.getRutEmpresa();
        try {
            this.conectar(false);
            this.ejecutarOrden(sql);
        } catch (Exception e) {
            throw e;
        } finally {

        }
    }
}
