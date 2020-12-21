package Modelo;
/**
 *
 * @author lainc
 */
public class Colaborador {

    private int RutColaborador;
    private String Nombre;
    private String Apellido;
    private String Usuario;
    private String Direccion;
    private int Telefono;
    private String Correo;
    private String Password;
    private boolean Estado;
    private Oficina oficinas;
    private Cargo cargos;

    public Colaborador() {
    }

    public Colaborador(int RutColaborador, String Nombre, String Apellido, String Usuario, String Direccion, int Telefono, String Correo, String Password, boolean Estado, Oficina oficinas, Cargo cargos) {
        this.RutColaborador = RutColaborador;
        this.Nombre = Nombre;
        this.Apellido = Apellido;
        this.Usuario = Usuario;
        this.Direccion = Direccion;
        this.Telefono = Telefono;
        this.Correo = Correo;
        this.Password = Password;
        this.Estado = Estado;
        this.oficinas = oficinas;
        this.cargos = cargos;
    }

    public int getRutColaborador() {
        return RutColaborador;
    }

    public void setRutColaborador(int RutColaborador) {
        this.RutColaborador = RutColaborador;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String Nombre) {
        this.Nombre = Nombre;
    }

    public String getApellido() {
        return Apellido;
    }

    public void setApellido(String Apellido) {
        this.Apellido = Apellido;
    }

    public String getUsuario() {
        return Usuario;
    }

    public void setUsuario(String Usuario) {
        this.Usuario = Usuario;
    }

    public String getDireccion() {
        return Direccion;
    }

    public void setDireccion(String Direccion) {
        this.Direccion = Direccion;
    }

    public int getTelefono() {
        return Telefono;
    }

    public void setTelefono(int Telefono) {
        this.Telefono = Telefono;
    }

    public String getCorreo() {
        return Correo;
    }

    public void setCorreo(String Correo) {
        this.Correo = Correo;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String Password) {
        this.Password = Password;
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

    public Cargo getCargos() {
        return cargos;
    }

    public void setCargos(Cargo cargos) {
        this.cargos = cargos;
    }
    
}
