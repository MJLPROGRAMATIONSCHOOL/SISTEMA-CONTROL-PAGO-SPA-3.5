package DAO;

import Conexion.Conexion;
import Modelo.TipoPago;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author lainc
 */
public class TipoPagoDAO extends Conexion {

    public List<TipoPago> listar() throws Exception {
        List<TipoPago> tipoPagos;
        TipoPago tip;
        ResultSet rs = null;
        String sql = "select * from tipopago";
        try {
            this.conectar(false);
            rs = this.ejecutarOrdenDatos(sql);
            tipoPagos = new ArrayList<>();
            while (rs.next() == true) {
                tip = new TipoPago();
                tip.setIdTipoPago(rs.getInt(1));
                tip.setNombre(rs.getString(2));
                tipoPagos.add(tip);
            }
        } catch (Exception e) {
            throw e;
        } finally {
            if (rs != null && rs.isClosed() == false) {
                rs.close();
            }
            rs = null;
        }
        return tipoPagos;
    }
}
