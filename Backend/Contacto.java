package Backend;

public class Contacto {
    private String nombre;
    private String username;
    private String password;
    private String telefono1;
    private String telefono2;
    private String Email;
    private String direccion;
    private String bornDate;
    private String categoria;
    private String grupos;

    public Contacto(String nombre, String username, String password, String telefono1, String telefono2, String email,
            String direccion, String bornDate, String categoria, String grupos) {
        this.nombre = nombre;
        this.username = username;
        this.password = password;
        this.telefono1 = telefono1;
        this.telefono2 = telefono2;
        Email = email;
        this.direccion = direccion;
        this.bornDate = bornDate;
        this.categoria = categoria;
        this.grupos = grupos;
    }

    public String toString() {
        return "Contacto { " +
                "nombre = '" + nombre + '\'' +
                ", usuario = '" + username + '\'' +
                ", telefono1 = '" + telefono1 + '\'' +
                ", telefono2 = '" + telefono2 + '\'' +
                ", correo = '" + Email + '\'' +
                ", direccion = '" + direccion + '\'' +
                ", fechaNacimiento = '" + bornDate + '\'' +
                ", categoria = '" + categoria + '\'' +
                ", grupos = '" + grupos + '\'' +
                " }\n";
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;   
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getTelefono1() {
        return telefono1;
    }

    public void setTelefono1(String telefono1) {
        this.telefono1 = telefono1;
    }

    public String getTelefono2() {
        return telefono2;
    }

    public void setTelefono2(String telefono2) {
        this.telefono2 = telefono2;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getBornDate() {
        return bornDate;
    }

    public void setBornDate(String bornDate) {
        this.bornDate = bornDate;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String grupos) {
        this.categoria = grupos;
    }

    public String getGrupos() {
        return grupos;
    }

    public void setGrupos(String subGrupos) {
        this.grupos = subGrupos;
    }

}