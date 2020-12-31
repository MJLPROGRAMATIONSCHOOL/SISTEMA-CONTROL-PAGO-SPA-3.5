package Modelo;

/**
 *
 * @author lainc
 */
public class TipoPago {
    private int IdTipoPago;
    private String Nombre;

    public TipoPago() {
    }

    public TipoPago(int IdTipoPago, String Nombre) {
        this.IdTipoPago = IdTipoPago;
        this.Nombre = Nombre;
    }

    public int getIdTipoPago() {
        return IdTipoPago;
    }

    public void setIdTipoPago(int IdTipoPago) {
        this.IdTipoPago = IdTipoPago;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String Nombre) {
        this.Nombre = Nombre;
    }
     
}
