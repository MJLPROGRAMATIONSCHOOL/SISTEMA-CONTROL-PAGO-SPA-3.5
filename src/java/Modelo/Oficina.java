package Modelo;

/**
 *
 * @author lainc
 */
public class Oficina {

    private int IdOficina;
    private String Nombre;
    private String Direccion;
    private boolean Estado;

    public Oficina() {
    }

    public Oficina(int IdOficina, String Nombre, String Direccion, boolean Estado) {
        this.IdOficina = IdOficina;
        this.Nombre = Nombre;
        this.Direccion = Direccion;
        this.Estado = Estado;
    }

    public int getIdOficina() {
        return IdOficina;
    }

    public void setIdOficina(int IdOficina) {
        this.IdOficina = IdOficina;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String Nombre) {
        this.Nombre = Nombre;
    }

    public String getDireccion() {
        return Direccion;
    }

    public void setDireccion(String Direccion) {
        this.Direccion = Direccion;
    }

    public boolean isEstado() {
        return Estado;
    }

    public void setEstado(boolean Estado) {
        this.Estado = Estado;
    }
    
    
}
