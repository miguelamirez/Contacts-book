public class Nodo {
    private String username;
    private String password;
    private String telefono1;
    private String telefono2;
    private String Email;
    private String direccion;
    private String grupos;
    
    public Nodo(String username, String password, String telefono1, String telefono2, String email, String direccion,
            String grupos) {
        this.username = username;
        this.password = password;
        this.telefono1 = telefono1;
        this.telefono2 = telefono2;
        Email = email;
        this.direccion = direccion;
        this.grupos = grupos;
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

    public String getGrupos() {
        return grupos;
    }

    public void setGrupos(String grupos) {
        this.grupos = grupos;
    }

}