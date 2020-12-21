package DAO;

import Conexion.Conexion;
import Modelo.Cargo;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author lainc
 */
public class CargoDAO extends Conexion {

    public List<Cargo> listar() throws Exception {
        List<Cargo> cargos;
        Cargo car;
        ResultSet rs = null;
        String sql = "select * from cargo";

        try {
            this.conectar(false);
            rs = this.ejecutarOrdenDatos(sql);
            cargos = new ArrayList<>();
            while (rs.next() == true) {
                car = new Cargo();
                car.setIdCargo(rs.getInt(1));
                car.setNombreCargo(rs.getString(2));
                car.setEstado(rs.getBoolean(3));
                cargos.add(car);
            }
        } catch (Exception e) {
            throw e;
        } finally {
            if (rs != null && rs.isClosed() == false) {
                rs.close();
            }
            rs = null;
        }
        return cargos;
    }

    public void registrar(Cargo cargo) throws Exception {
        String sql;
        sql = "insert into cargo (NombreCargo, Estado)"
                + "values('" + cargo.getNombreCargo() + "',"
                + (cargo.isEstado() == true ? "1" : "0") + ")";
        try {
            this.conectar(false);
            this.ejecutarOrden(sql);
        } catch (Exception e) {
            throw e;
        } finally {
        }
    }

    public Cargo leer(Cargo cargo) throws Exception {
        Cargo car = null;
        ResultSet rs = null;
        String sql = "select C.NombreCargo, C.Estado from cargo C where C.Codigo = " + cargo.getIdCargo();

        try {
            this.conectar(false);
            rs = this.ejecutarOrdenDatos(sql);
            if (rs.next() == true) {
                car = new Cargo();
                car.setIdCargo(cargo.getIdCargo());
                car.setNombreCargo(rs.getString("NombreCargo"));
                car.setEstado(rs.getBoolean("Estado"));
            }
        } catch (Exception e) {
            throw e;
        } finally {
            if (rs != null && rs.isClosed() == false) {
                rs.close();
            }
            rs = null;
        }
        return car;
    }

    public void actualizar(Cargo cargo) throws Exception {
        String sql;
        sql = "UPDATE cargo SET NombreCargo = '" + cargo.getNombreCargo()
                + "', Estado = " + (cargo.isEstado() == true ? "1" : "0")
                + " WHERE Codigo = " + cargo.getIdCargo();
        try {
            this.conectar(false);
            this.ejecutarOrden(sql);
        } catch (Exception e) {
            throw e;
        } finally {
        }
    }

    public void eliminar(Cargo cargo) throws Exception {
        String sql = "delete from cargo where Codigo = " + cargo.getIdCargo();
        try {
            this.conectar(false);
            this.ejecutarOrden(sql);
        } catch (Exception e) {
            throw e;
        } finally {

        }
    }
}
