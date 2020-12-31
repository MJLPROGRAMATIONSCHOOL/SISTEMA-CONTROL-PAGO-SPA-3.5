
package Modelo;

/**
 *
 * @author lainc
 */
public class Carrito {
    private int item;
    private Articulo articulo;
    String servicio;
    String descripcion;
    double precioPago;
    double subTotal;

    public Carrito() {
    }

    public Carrito(int item, Articulo articulo, String servicio, String descripcion, double precioPago, double subTotal) {
        this.item = item;
        this.articulo = articulo;
        this.servicio = servicio;
        this.descripcion = descripcion;
        this.precioPago = precioPago;
        this.subTotal = subTotal;
    }

    public int getItem() {
        return item;
    }

    public void setItem(int item) {
        this.item = item;
    }

    public Articulo getArticulo() {
        return articulo;
    }

    public void setArticulo(Articulo articulo) {
        this.articulo = articulo;
    }

    public String getServicio() {
        return servicio;
    }

    public void setServicio(String servicio) {
        this.servicio = servicio;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public double getPrecioPago() {
        return precioPago;
    }

    public void setPrecioPago(double precioPago) {
        this.precioPago = precioPago;
    }

    public double getSubTotal() {
        return subTotal;
    }

    public void setSubTotal(double subTotal) {
        this.subTotal = subTotal;
    }
    
    
    
}
