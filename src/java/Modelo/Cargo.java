package Modelo;
/**
 *
 * @author lainc
 */
public class Cargo {

    private int IdCargo;
    private String NombreCargo;
    private boolean Estado;

    public Cargo() {
    }

    public Cargo(int IdCargo, String NombreCargo, boolean Estado) {
        this.IdCargo = IdCargo;
        this.NombreCargo = NombreCargo;
        this.Estado = Estado;
    }

    public int getIdCargo() {
        return IdCargo;
    }

    public void setIdCargo(int IdCargo) {
        this.IdCargo = IdCargo;
    }

    public String getNombreCargo() {
        return NombreCargo;
    }

    public void setNombreCargo(String NombreCargo) {
        this.NombreCargo = NombreCargo;
    }

    public boolean isEstado() {
        return Estado;
    }

    public void setEstado(boolean Estado) {
        this.Estado = Estado;
    }

}
