import java.util.Scanner;

public class Validaciones {

    // Validar campo usuario
    public static String validarUsuario(String usuario) {
        String expresionUsuario = "^[a-zA-Z][a-zA-Z0-9._]{2,9}$";
        if (usuario.matches(expresionUsuario)) {
            return "Usuario valido";
        }
        return "Usuario inválido: debe iniciar con letra, tener entre 3 y 10 caracteres, y puede contener numeros, punto o barra baja, sin espacios";
    }

    // Validar contraseña
    public static String validarContrasena(String contrasena) {
        String expresionContrasena = "^(?=.*[A-Z])(?=.*[a-z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,12}$";
        if (contrasena.matches(expresionContrasena)) {
            return "Contrasena valida";
        }
        return "Contrasena invalida: debe tener entre 8 y 12 caracteres, incluir mayusculas, minusculas, numeros y caracteres especiales, sin espacios";
    }

    // Validar dirección en Colombia (Calle o Carrera o Avenida, número, y ubicación opcional)
    public static String validarDireccion(String direccion) {
        String expresionDireccion = "^(calle|carrera|avenida)\\s\\d+\\s?(norte|sur)?$";
        if (direccion.matches(expresionDireccion)) {
            return "Direccion valida";
        }
        return "Dirección invalida: debe iniciar con 'calle', 'carrera' o 'avenida', seguido de un numero y opcionalmente 'norte' o 'sur'";
    }

    // Validar teléfono (fijo o móvil) con prefijos de país
    public static String validarTelefono(String telefono) {
        String expresionTelefono = "^(\\+57|\\+54|\\+52|\\+56)\\s\\d{3}\\s\\d{7}$";
        if (telefono.matches(expresionTelefono)) {
            return "Telefono valido";
        }
        return "Telefono invalido: debe iniciar con un prefijo de pais (+57, +54, +52, +56), seguido de tres digitos, un espacio y siete digitos (Ejemplo: +57 300 1234567)";
    }

    // Validar correo electrónico
    public static String validarCorreo(String correo) {
        String expresionCorreo = "^[\\w.-]+@[\\w.-]+\\.[a-zA-Z]{2,6}$";
        if (correo.matches(expresionCorreo)) {
            return "Correo valido";
        }
        return "Correo invalido: el formato debe ser usuario@dominio.extension";
    }

    // Validar fecha de nacimiento (Formato dd/mm/yyyy)
    public static String validarFechaNacimiento(String fecha) {
        String expresionFecha = "^(0[1-9]|[12][0-9]|3[01])[-/](0[1-9]|1[0-2])[-/](19|20)\\d{2}$";
        if (fecha.matches(expresionFecha)) {
            return "Fecha de nacimiento valida";
        }
        return "Fecha de nacimiento invalida: debe tener el formato dd/mm/yyyy o dd-mm-yyyy, entre los anos 1900 y 2099";
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Solicitar datos al usuario y validar cada campo
        System.out.print("Ingrese el usuario: ");
        String usuario = scanner.nextLine();
        System.out.println(validarUsuario(usuario));

        System.out.print("Ingrese la contrasena: ");
        String contrasena = scanner.nextLine();
        System.out.println(validarContrasena(contrasena));

        System.out.print("Ingrese la direccion (Ejemplo: calle 123 norte): ");
        String direccion = scanner.nextLine();
        System.out.println(validarDireccion(direccion));

        System.out.print("Ingrese el telefono (Ejemplo: +57 300 1234567): ");
        String telefono = scanner.nextLine();
        System.out.println(validarTelefono(telefono));

        System.out.print("Ingrese el correo electronico: ");
        String correo = scanner.nextLine();
        System.out.println(validarCorreo(correo));

        System.out.print("Ingrese la fecha de nacimiento (dd/mm/yyyy): ");
        String fechaNacimiento = scanner.nextLine();
        System.out.println(validarFechaNacimiento(fechaNacimiento));

        scanner.close();
    }
}