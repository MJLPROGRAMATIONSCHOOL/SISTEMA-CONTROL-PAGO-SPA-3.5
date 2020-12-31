package DAO;

import Conexion.Conexion;
import Modelo.DetMovimiento;
import Modelo.Empresa;
import Modelo.Movimiento;
import Modelo.TipoPago;
import Modelo.Articulo;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author lainc
 */
public class MovimientoDAO {

    Connection con;
    Conexion cn = new Conexion();
    PreparedStatement ps;
    ResultSet rs;

//    public int guardarCompra(Compra co) {
//        String sql = "insert into Compras(idCliente,idPago, FechaCompras,Monto,Estado)values(?,?,?,?,?)";
//        try {
//            con = cn.getConnection();
//            ps = con.prepareStatement(sql);
//            ps.setInt(1, co.getIdCliente());
//            ps.setInt(2, co.getIdPago());
//            ps.setString(3, co.getFecha());
//            ps.setDouble(4, co.getMonto());
//            ps.setString(5, co.getEstado());
//            ps.executeUpdate();
//        } catch (Exception e) {
//        }
//        return 1;
//    }
//    public int guardarDetalleCompra(DetalleCompra dc) {
//        String sql = "insert into Detalle_Compras(idProducto,idCompras, Cantidad, PrecioCompra)values(?,?,?,?)";
//        try {
//            con = cn.getConnection();
//            ps = con.prepareStatement(sql);
//            ps.setInt(1, dc.getIdproducto());
//            ps.setInt(2, dc.getIdcompra());
//            ps.setInt(3, dc.getCantidad());
//            ps.setDouble(4, dc.getPrecioCompra());
//            ps.executeUpdate();
//            con.close();
//        } catch (Exception e) {
//        }
//        return 1;
//    }
    public List misMovimientos(int rut) {
        List lista = new ArrayList();
        String sql = "select * from movimiento where RutEmpresa=" + rut;
        try {
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                Movimiento mov = new Movimiento();
                mov.setIdMovimiento(rs.getInt(1));
                mov.setEmpresa(new Empresa());
                mov.getEmpresa().setRutEmpresa(rs.getInt(2));
                mov.setTipopagos(new TipoPago());
                mov.getTipopagos().setIdTipoPago(rs.getInt(3));
                mov.setFechaPago(rs.getString(4));
                mov.setMontoTotal(rs.getDouble(5));
                mov.setEstado(rs.getBoolean(6));
                lista.add(mov);
            }
        } catch (Exception e) {
        }
        return lista;
    }

//    public List Detalle(int id) {
//        List lista = new ArrayList();
//        String sql = "select DC.idDetalle, P.Foto, P.Nombres, DC.idCompras, DC.Cantidad, DC.PrecioCompra FROM detalle_compras DC inner join producto P on P.idProducto = DC.idProducto where idCompras=" + id;
//        try {
//            con = cn.getConnection();
//            ps = con.prepareStatement(sql);
//            rs = ps.executeQuery();
//            while (rs.next()) {
//                DetMovimiento dmov = new DetMovimiento();
//                dmov.setIdDetMovimiento(rs.getInt(1));
//                dmov.(new TipoPago());
//                dmov.getTipoPago().setServicio(rs.getString(2));
//                dmov.getTipoPago().setDescripcion(rs.getString(3));
//                dmov.setMovimiento(new Movimiento());
//                dmov.getMovimiento().setIdMovimiento(rs.getInt(4));
//                dmov.setPrecioPago(rs.getDouble(6));
//                lista.add(dmov);
//            }
//        } catch (Exception e) {
//        }
//        return lista;
//    }

    public int Pagar(double monto) {
        String sql = "insert into pago(Monto)values(?)";
        try {
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            ps.setDouble(1, monto);
            ps.executeUpdate();
        } catch (Exception e) {
        }
        return 1;
    }

    public int IdPago() {
        int idpago = 0;
        String sql = "select max(idPago) from pago";
        try {
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                idpago = rs.getInt(1);
            }
        } catch (Exception e) {
        }
        return idpago;
    }
}
