package Modelo;
/**
 *
 * @author lainc
 */
public class Movimiento {
    private int IdMovimiento;
    private Empresa empresa;
    private TipoPago tipopagos;
    private String FechaPago;
    private double MontoTotal;
    private boolean Estado;

    public Movimiento() {
    }

    public Movimiento(int IdMovimiento, Empresa empresa, TipoPago tipopagos, String FechaPago, double MontoTotal, boolean Estado) {
        this.IdMovimiento = IdMovimiento;
        this.empresa = empresa;
        this.tipopagos = tipopagos;
        this.FechaPago = FechaPago;
        this.MontoTotal = MontoTotal;
        this.Estado = Estado;
    }

    public int getIdMovimiento() {
        return IdMovimiento;
    }

    public void setIdMovimiento(int IdMovimiento) {
        this.IdMovimiento = IdMovimiento;
    }

    public Empresa getEmpresa() {
        return empresa;
    }

    public void setEmpresa(Empresa empresa) {
        this.empresa = empresa;
    }

    public TipoPago getTipopagos() {
        return tipopagos;
    }

    public void setTipopagos(TipoPago tipopagos) {
        this.tipopagos = tipopagos;
    }

    public String getFechaPago() {
        return FechaPago;
    }

    public void setFechaPago(String FechaPago) {
        this.FechaPago = FechaPago;
    }

    public double getMontoTotal() {
        return MontoTotal;
    }

    public void setMontoTotal(double MontoTotal) {
        this.MontoTotal = MontoTotal;
    }

    public boolean isEstado() {
        return Estado;
    }

    public void setEstado(boolean Estado) {
        this.Estado = Estado;
    }
    
}
