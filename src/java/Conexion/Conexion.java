package Conexion;

import com.mysql.jdbc.jdbc2.optional.MysqlConnectionPoolDataSource;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.List;

/**
 *
 * @author lainc
 */
public class Conexion {

    Connection con;
    String url = "jdbc:mysql://localhost:3306/controlpagobd";
    String user = "root";
    String pass = "";
    private Connection conexion;
    private boolean transaccionIniciada;

    protected Connection getConexion() {
        return conexion;
    }

    protected void conectar(boolean Transaccion) throws Exception {
        MysqlConnectionPoolDataSource ds = new MysqlConnectionPoolDataSource();
        ds.setServerName("localhost");
        ds.setPort(3306);
        ds.setDatabaseName("controlpagobd");
        conexion = ds.getConnection("root", "");

        if (Transaccion == true) {
            this.conexion.setAutoCommit(false);
            this.transaccionIniciada = true;
        } else {
            this.conexion.setAutoCommit(true);
            this.transaccionIniciada = false;
        }
    }

    protected void cerrar(boolean Estado) throws Exception {
        if (this.conexion != null) {
            if (this.transaccionIniciada == true) {
                try {
                    if (Estado == true) {
                        this.conexion.commit();
                    } else {
                        this.conexion.rollback();
                    }
                } catch (Exception e) {
                    throw e;
                }
            }
            try {
                this.conexion.close();
            } catch (Exception e) {
            }
        }
        this.conexion = null;
    }

    protected void ejecutarOrden(String wSQL) throws Exception {
        Statement st;

        if (this.conexion != null) {
            st = this.conexion.createStatement();
            st.executeUpdate(wSQL);
        }
    }

    protected ResultSet ejecutarOrdenDatos(String wSQL) throws Exception {
        Statement st;
        ResultSet rs = null;

        if (this.conexion != null) {
            st = this.conexion.createStatement();
            rs = st.executeQuery(wSQL);
        }

        return rs;
    }

    protected Object ejecutarProcedimiento(String wProcedimiento,
            List<Parametro> wParametros) throws Exception {
        CallableStatement cs;
        Object valor = null;
        String parNombre = "";

        try {
            cs = this.getConexion().prepareCall(wProcedimiento);
            if (wParametros != null) {
                for (Parametro par : wParametros) {
                    if (par.isEntrada() == true) {
                        cs.setObject(par.getNombre(), par.getValor());
                    } else {
                        parNombre = par.getNombre();
                        cs.registerOutParameter(par.getNombre(), par.getTipo());
                    }
                }
            }
            cs.executeUpdate();
            if (parNombre.length() > 0) {
                valor = cs.getObject(parNombre);
            }
        } catch (Exception e) {
            throw e;
        } finally {
            cs = null;
        }

        return valor;
    }

    protected ResultSet ejecutarProcedimientoDatos(String Procedimiento,
            List<Parametro> wParametros) throws Exception {
        CallableStatement cs;
        ResultSet rs = null;

        try {
            cs = this.getConexion().prepareCall(Procedimiento);
            if (wParametros != null) {
                for (Parametro par : wParametros) {
                    if (par.isEntrada() == true) {
                        cs.setObject(par.getNombre(), par.getValor());
                    } else {
                        // parametro de salida
                    }
                }
            }
            rs = cs.executeQuery();
        } catch (Exception e) {
            throw e;
        } finally {
            cs = null;
        }

        return rs;
    }

    public Connection getConnection() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection(url, user, pass);
        } catch (Exception e) {
        }
        return con;
    }
}
