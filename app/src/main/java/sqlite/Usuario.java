package sqlite;

public class Usuario {

    int id;
    String usuario;
    String contrasena;
    String nombre;
    String apellidos;
    String email;

    public Usuario(String usuario,String contrasena,String nombre,String apellidos,String email){
        this.usuario=usuario;
        this.contrasena=contrasena;
        this.nombre=nombre;
        this.apellidos=apellidos;
        this.email=email;
    }

    @Override
    public String toString() {
        return this.usuario + ":" + this.contrasena;
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsuario() {
        return this.usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getContrasena() {
        return this.contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}