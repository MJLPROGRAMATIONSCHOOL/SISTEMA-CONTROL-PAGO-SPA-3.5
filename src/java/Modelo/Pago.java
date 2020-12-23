
package Modelo;

/**
 *
 * @author lainc
 */
public class Pago {
    private int IdPago;
    private String Servicio;
    private String Descripcion;
    private boolean Estado;

    public Pago() {
    }

    public Pago(int IdPago, String Servicio, String Descripcion, boolean Estado) {
        this.IdPago = IdPago;
        this.Servicio = Servicio;
        this.Descripcion = Descripcion;
        this.Estado = Estado;
    }

    public int getIdPago() {
        return IdPago;
    }

    public void setIdPago(int IdPago) {
        this.IdPago = IdPago;
    }

    public String getServicio() {
        return Servicio;
    }

    public void setServicio(String Servicio) {
        this.Servicio = Servicio;
    }

    public String getDescripcion() {
        return Descripcion;
    }

    public void setDescripcion(String Descripcion) {
        this.Descripcion = Descripcion;
    }

    public boolean isEstado() {
        return Estado;
    }

    public void setEstado(boolean Estado) {
        this.Estado = Estado;
    }
    
}
