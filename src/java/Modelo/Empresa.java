package Modelo;
/**
 *
 * @author lainc
 */
public class Empresa {

    private int RutEmpresa;
    private String RazonSocial;
    private String Direccion;
    private String Giro;
    private boolean Estado;
    private Oficina oficinas;

    public Empresa() {
    }

    public Empresa(int RutEmpresa, String RazonSocial, String Direccion, String Giro, boolean Estado, Oficina oficinas) {
        this.RutEmpresa = RutEmpresa;
        this.RazonSocial = RazonSocial;
        this.Direccion = Direccion;
        this.Giro = Giro;
        this.Estado = Estado;
        this.oficinas = oficinas;
    }

    public int getRutEmpresa() {
        return RutEmpresa;
    }

    public void setRutEmpresa(int RutEmpresa) {
        this.RutEmpresa = RutEmpresa;
    }

    public String getRazonSocial() {
        return RazonSocial;
    }

    public void setRazonSocial(String RazonSocial) {
        this.RazonSocial = RazonSocial;
    }

    public String getDireccion() {
        return Direccion;
    }

    public void setDireccion(String Direccion) {
        this.Direccion = Direccion;
    }

    public String getGiro() {
        return Giro;
    }

    public void setGiro(String Giro) {
        this.Giro = Giro;
    }

    public boolean isEstado() {
        return Estado;
    }

    public void setEstado(boolean Estado) {
        this.Estado = Estado;
    }

    public Oficina getOficinas() {
        return oficinas;
    }

    public void setOficinas(Oficina oficinas) {
        this.oficinas = oficinas;
    }
    
    
}
