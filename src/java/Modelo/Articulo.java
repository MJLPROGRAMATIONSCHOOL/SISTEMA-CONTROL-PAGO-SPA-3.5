
package Modelo;

/**
 *
 * @author lainc
 */
public class Articulo {
    private int IdArticulo;
    private String Servicio;
    private String Descripcion;
    private double Precio;
    private boolean Estado;

    public Articulo() {
    }

    public Articulo(int IdArticulo, String Servicio, String Descripcion, double Precio, boolean Estado) {
        this.IdArticulo = IdArticulo;
        this.Servicio = Servicio;
        this.Descripcion = Descripcion;
        this.Precio = Precio;
        this.Estado = Estado;
    }
    
    

    public int getIdArticulo() {
        return IdArticulo;
    }

    public void setIdArticulo(int IdArticulo) {
        this.IdArticulo = IdArticulo;
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

    public double getPrecio() {
        return Precio;
    }

    public void setPrecio(double Precio) {
        this.Precio = Precio;
    }

    public boolean isEstado() {
        return Estado;
    }

    public void setEstado(boolean Estado) {
        this.Estado = Estado;
    }
    
    

}
