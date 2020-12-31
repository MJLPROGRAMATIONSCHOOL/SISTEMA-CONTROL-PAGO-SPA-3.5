package Modelo;

/**
 *
 * @author lainc
 */
public class DetMovimiento {

    private int IdDetMovimiento;
    private Articulo articulo;
    private Movimiento movimiento;
    private double precioPago;

    public DetMovimiento() {
    }

    public DetMovimiento(int IdDetMovimiento, Articulo articulo, Movimiento movimiento, double precioPago) {
        this.IdDetMovimiento = IdDetMovimiento;
        this.articulo = articulo;
        this.movimiento = movimiento;
        this.precioPago = precioPago;
    }

    public int getIdDetMovimiento() {
        return IdDetMovimiento;
    }

    public void setIdDetMovimiento(int IdDetMovimiento) {
        this.IdDetMovimiento = IdDetMovimiento;
    }

    public Articulo getTipoPago() {
        return articulo;
    }

    public void setTipoPago(Articulo tipoPago) {
        this.articulo = tipoPago;
    }

    public Movimiento getMovimiento() {
        return movimiento;
    }

    public void setMovimiento(Movimiento movimiento) {
        this.movimiento = movimiento;
    }

    public double getPrecioPago() {
        return precioPago;
    }

    public void setPrecioPago(double precioPago) {
        this.precioPago = precioPago;
    }

    
}
