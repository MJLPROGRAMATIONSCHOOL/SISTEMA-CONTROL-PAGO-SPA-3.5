package DAO;

import Conexion.Conexion;
import Modelo.Cargo;
import Modelo.Colaborador;
import Modelo.Oficina;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author lainc
 */
public class ColaboradorDAO extends Conexion {

    public Colaborador identificar(Colaborador colaborador) throws Exception {
        Colaborador col = null;
        ResultSet rs = null;
        String sql = "SELECT U.RutColaborador, U.Nombre, U.Apellido, U.Estado, C.NombreCargo FROM colaborador U "
                + "INNER JOIN cargo C ON C.Codigo = U.Codigo "
                + "WHERE U.Usuario = '" + colaborador.getUsuario() + "' AND U.Password= '" + colaborador.getPassword() + "'";

        try {
            this.conectar(false);
            rs = this.ejecutarOrdenDatos(sql);
            if (rs.next() == true) {
                col = new Colaborador();
                col.setRutColaborador(rs.getInt("RutColaborador"));
                col.setNombre(rs.getString("Nombre") + " " + rs.getString("Apellido"));
                col.setCargos(new Cargo());
                col.getCargos().setNombreCargo(rs.getString("NombreCargo"));
                col.setEstado(rs.getBoolean("Estado"));
            }
        } catch (Exception e) {
            throw e;
        } finally {
            if (rs != null && rs.isClosed() == false) {
                rs.close();
            }
            rs = null;
        }
        return col;
    }

    public List<Colaborador> listar() throws Exception {
        List<Colaborador> colaboradores;
        Colaborador col;
        ResultSet rs = null;
        String sql = "SELECT C.RutColaborador, C.Nombre, C.Apellido, C.Usuario, C.Direccion, C.Telefono, C.Correo, C.Password, C.Estado, E.Nombre AS Surcusal, O.NombreCargo AS Cargo FROM Colaborador C "
                + "INNER JOIN Oficina E ON E.IdOficina = C.IdOficina "
                + "INNER JOIN Cargo O ON O.Codigo = C.Codigo ORDER BY C.RutColaborador";

        try {
            this.conectar(false);
            rs = this.ejecutarOrdenDatos(sql);
            colaboradores = new ArrayList<>();
            while (rs.next() == true) {
                col = new Colaborador();
                col.setRutColaborador(rs.getInt("RutColaborador"));
                col.setNombre(rs.getString("Nombre"));
                col.setApellido(rs.getString("Apellido"));
                col.setUsuario(rs.getString("Usuario"));
                col.setDireccion(rs.getString("Direccion"));
                col.setTelefono(rs.getInt("Telefono"));
                col.setCorreo(rs.getString("Correo"));
                col.setPassword(rs.getString("Password"));
                col.setEstado(rs.getBoolean("Estado"));
                col.setCargos(new Cargo());
                col.getCargos().setNombreCargo(rs.getString("Cargo"));
                col.setOficinas(new Oficina());
                col.getOficinas().setNombre(rs.getString("Surcusal"));
                colaboradores.add(col);
            }
        } catch (Exception e) {
            throw e;
        } finally {
            if (rs != null && rs.isClosed() == false) {
                rs.close();
            }
            rs = null;
        }
        return colaboradores;
    }

    public void registrar(Colaborador colaborador) throws Exception {
        String sql;
        sql = "insert into colaborador (RutColaborador, Nombre, Apellido, Usuario, Direccion, Telefono, Correo, Password, Estado, IdOficina, Codigo)"
                + "values(" + colaborador.getRutColaborador() + ", '"
                + colaborador.getNombre() + "', '"
                + colaborador.getApellido() + "','"
                + colaborador.getUsuario() + "','"
                + colaborador.getDireccion() + "',"
                + colaborador.getTelefono() + ",'"
                + colaborador.getCorreo() + "','"
                + colaborador.getPassword() + "',"
                + (colaborador.isEstado() == true ? "1" : "0")+ ","
                + colaborador.getOficinas().getIdOficina() + ","
                + colaborador.getCargos().getIdCargo() + ")";
        try {
            this.conectar(false);
            this.ejecutarOrden(sql);
        } catch (Exception e) {
            throw e;
        } finally {
        }
    }

    public Colaborador leer(Colaborador colaborador) throws Exception {
        Colaborador co = null;
        ResultSet rs = null;
        String sql = "select C.RutColaborador, C.Nombre, C.Apellido, C.Usuario, C.Direccion, C.Telefono, C.Correo, C.Password, C.Estado, C.IdOficina, C.Codigo from colaborador C where C.RutColaborador=" + colaborador.getRutColaborador();

        try {
            this.conectar(false);
            rs = this.ejecutarOrdenDatos(sql);
            if (rs.next() == true) {
                co = new Colaborador();
                co.setRutColaborador(rs.getInt("RutColaborador"));
                co.setNombre(rs.getString("Nombre"));
                co.setApellido(rs.getString("Apellido"));
                co.setUsuario(rs.getString("Usuario"));
                co.setDireccion(rs.getString("Direccion"));
                co.setTelefono(rs.getInt("Telefono"));
                co.setCorreo(rs.getString("Correo"));
                co.setPassword(rs.getString("Password"));
                co.setEstado(rs.getBoolean("Estado"));
                co.setOficinas(new Oficina());
                co.getOficinas().setIdOficina(rs.getInt("IdOficina"));
                co.setCargos(new Cargo());
                co.getCargos().setIdCargo(rs.getInt("Codigo"));
            }
        } catch (Exception e) {
            throw e;
        } finally {
            if (rs != null && rs.isClosed() == false) {
                rs.close();
            }
            rs = null;
        }
        return co;
    }

    public void actualizar(Colaborador colaborador) throws Exception {
        String sql;
        sql = "update colaborador set Nombre = '" + colaborador.getNombre()
                + "', Apellido = '" + colaborador.getApellido()
                + "', Usuario = '" + colaborador.getUsuario()
                + "', Direccion = '" + colaborador.getDireccion()
                + "', Telefono = " + colaborador.getTelefono()
                + ", Correo = '" + colaborador.getCorreo()
                + "', Password = '" + colaborador.getPassword()
                + "', Estado = " + (colaborador.isEstado() == true ? "1" : "0")
                + ", IdOficina = " + colaborador.getOficinas().getIdOficina()
                + ", Codigo = " + colaborador.getCargos().getIdCargo()
                + " where RutColaborador = " + colaborador.getRutColaborador();
        try {
            this.conectar(false);
            this.ejecutarOrden(sql);
        } catch (Exception e) {
            throw e;
        } finally {
        }
    }

    public void eliminar(Colaborador colaborador) throws Exception {
        String sql = "delete from colaborador where RutColaborador = " + colaborador.getRutColaborador();
        try {
            this.conectar(false);
            this.ejecutarOrden(sql);
        } catch (Exception e) {
            throw e;
        } finally {

        }
    }
}
