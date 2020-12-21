package Modelo;
/**
 *
 * @author lainc
 */
public class Login {

    private int idLogin;
    private String usuario;
    private String pass;

    private int RutColaborador;
    private String cargo;
    private String nombres;
    private boolean estado;

    public Login() {
    }

    public Login(int idLogin, String usuario, String pass, int RutColaborador, String cargo, String nombres, boolean estado) {
        this.idLogin = idLogin;
        this.usuario = usuario;
        this.pass = pass;
        this.RutColaborador = RutColaborador;
        this.cargo = cargo;
        this.nombres = nombres;
        this.estado = estado;
    }

    public int getIdLogin() {
        return idLogin;
    }

    public void setIdLogin(int idLogin) {
        this.idLogin = idLogin;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public int getRutColaborador() {
        return RutColaborador;
    }

    public void setRutColaborador(int RutColaborador) {
        this.RutColaborador = RutColaborador;
    }

    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public boolean isEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }

    
}
