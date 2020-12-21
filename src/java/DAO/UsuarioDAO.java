package DAO;

import Conexion.Conexion;
import Modelo.Cargo;
import Modelo.Colaborador;
import Modelo.Login;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author lainc
 */
public class UsuarioDAO extends Conexion {

    public Login identificar(Login login) throws Exception {
        Login log = null;
        ResultSet rs = null;
        String sql = "SELECT E.RutColaborador, E.Nombre, E.Apellido, E.Estado, C.NombreCargo FROM Usuario U "
                + "INNER JOIN colaborador E ON E.IdLogin = U.IdLogin INNER JOIN cargo C ON C.Codigo = E.Codigo "
                + "WHERE U.Usuario = '" + login.getUsuario() + "' AND U.Password= '" + login.getPass() + "'";

        try {
            this.conectar(false);
            rs = this.ejecutarOrdenDatos(sql);
            if (rs.next() == true) {
                log = new Login();
                log.setRutColaborador(rs.getInt("RutColaborador"));
                log.setNombres(rs.getString("Nombre") + " " + rs.getString("Apellido"));
                log.setCargo(rs.getString("NombreCargo"));
                log.setEstado(rs.getBoolean("Estado"));
            }
        } catch (Exception e) {
            throw e;
        } finally {
            if (rs != null && rs.isClosed() == false) {
                rs.close();
            }
            rs = null;
        }
        return log;
    }
    
}
