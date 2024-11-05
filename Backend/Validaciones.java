package Backend;

public class Validaciones {

    // Validar campo usuario
    public String validarUsuario(String usuario) {
        String expresionUsuario = "^[a-zA-Z][a-zA-Z0-9._]{2,9}$";
        if (usuario.matches(expresionUsuario)) {
            return "Usuario valido";
        }
        return "Usuario inválido: debe iniciar con letra, tener entre 3 y 10 caracteres, y puede contener números, punto o barra baja, sin espacios";
    }

    // Validar contraseña
    public String validarContrasena(String contrasena) {
        String expresionContrasena = "^(?=.*[A-Z])(?=.*[a-z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,12}$";
        if (contrasena.matches(expresionContrasena)) {
            return "Contraseña valida";
        }
        return "Contraseña inválida: debe tener entre 8 y 12 caracteres, incluir mayúsculas, minúsculas, números y caracteres especiales, sin espacios";
    }

    // Validar dirección en Colombia
    public String validarDireccion(String direccion) {
        String expresionDireccion = "^(?i)(calle|carrera|kr|diagonal)\\s+\\d+\\s*([a-z]?)\\s*(sur)?\\s*#\\s*\\d+\\s*(-\\s*\\d+)?$";
        if (direccion.matches(expresionDireccion)) {
            return "Dirección válida";
        }
        return "Dirección inválida: debe iniciar con 'calle', 'carrera' o 'avenida', seguido de un número y opcionalmente 'norte' o 'sur'";
    }

    // Validar teléfono con prefijos y condiciones específicas
    public String validarTelefono(String telefono) {
        String expresionTelefono = "^(?:\\+57|57)?(\\s3\\d{9}|60\\d\\s\\d{7})$|" +         // Colombia
        "^(?:\\+52|52)\\s?1?\\d{2,3}\\d{7,8}$|" +                 // México
        "^(?:\\+58|58)\\s?0[24]\\d{9}$|" +                        // Venezuela
        "^(?:\\+51|51)\\s?(9\\d{8}|\\d{1,2}\\d{6,7})$";           // Perú

        if (telefono.matches(expresionTelefono)) {
            return "Teléfono válido";
        }
        return "Teléfono inválido: el número debe ser de COlombia, México, Venezuela o Perú con su respectivo prefijo o indicativo seguido de un número que comienza con 3, 5 o 7, y con longitud adecuada";
    }


    // Validar correo electrónico
    public String validarCorreo(String correo) {
        String expresionCorreo = "^[\\w.-]+@[\\w.-]+\\.[a-zA-Z]{2,6}$";
        if (correo.matches(expresionCorreo)) {
            return "Correo válido";
        }
        return "Correo inválido: el formato debe ser usuario@dominio.extension";
    }

    // Validar fecha de nacimiento
    public String validarFechaNacimiento(String fecha) {
        String expresionFecha = "^(0[1-9]|[12][0-9]|3[01])[-/](0[1-9]|1[0-2])[-/](19|20)\\d{2}$";
        if (fecha.matches(expresionFecha)) {
            return "Fecha de nacimiento válida";
        }
        return "Fecha de nacimiento inválida: debe tener el formato dd/mm/yyyy o dd-mm-yyyy, entre los años 1900 y 2099";
    }

}