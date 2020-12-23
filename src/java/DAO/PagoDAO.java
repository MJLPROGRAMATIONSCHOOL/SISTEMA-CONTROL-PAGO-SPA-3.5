package DAO;

import Conexion.Conexion;
import Modelo.Oficina;
import Modelo.Pago;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
/**
 *
 * @author lainc
 */
public class PagoDAO extends Conexion{
    public List<Pago> listar() throws Exception {
        List<Pago> pagos;
        Pago pag;
        ResultSet rs = null;
        String sql = "select * from tipodepago";
        try {
            this.conectar(false);
            rs = this.ejecutarOrdenDatos(sql);
            pagos = new ArrayList<>();
            while (rs.next() == true) {
                pag = new Pago();
                pag.setIdPago(rs.getInt(1));
                pag.setServicio(rs.getString(2));
                pag.setDescripcion(rs.getString(3));
                pag.setEstado(rs.getBoolean(4));
                pagos.add(pag);
            }
        } catch (Exception e) {
            throw e;
        } finally {
            if (rs != null && rs.isClosed() == false) {
                rs.close();
            }
            rs = null;
        }
        return pagos;
    }
}
