package Backend;

import java.io.*;
import java.util.*;

public class Entrada {

    // Clase para manejar líneas y el archivo de donde provienen durante la fusión
    private static class LineaArchivo implements Comparable<LineaArchivo> {
        String linea;
        BufferedReader archivo;

        public LineaArchivo(String linea, BufferedReader archivo) {
            this.linea = linea;
            this.archivo = archivo;
        }

        public int compareTo(LineaArchivo otro) {
            try {
                // Intentamos comparar numéricamente si ambas líneas son números
                double num1 = Double.parseDouble(this.linea.trim());
                double num2 = Double.parseDouble(otro.linea.trim());
                return Double.compare(num1, num2); // Comparación numérica
            } catch (NumberFormatException e) {
                // Si no son números, las comparamos como texto
                return this.linea.compareTo(otro.linea);
            }
        }
    }

    /*
     * 
     * 
     */

    public static void agregarContactos(ContactList list) {
        Scanner entrada = new Scanner(System.in);
        Validaciones validacion = new Validaciones();

        // Solicitar datos al usuario con validación en bucle
        System.out.println("Agregar nuevo contacto...");
        System.out.print("Nombre: ");
        String nombre = entrada.nextLine();

        String usuario;
        do {
            System.out.print("Ingrese el nombre de usuario: ");
            usuario = entrada.nextLine();
            System.out.println(validacion.validarUsuario(usuario));
        } while (!validacion.validarUsuario(usuario).equals("Usuario valido"));

        String contrasena;
        do {
            System.out.print("Ingrese la contraseña: ");
            contrasena = entrada.nextLine();
            System.out.println(validacion.validarContrasena(contrasena));
        } while (!validacion.validarContrasena(contrasena).equals("Contraseña valida"));

        String direccion;
        do {
            System.out.print("Ingrese la dirección (Ejemplo: calle 123 norte): ");
            direccion = entrada.nextLine();
            System.out.println(validacion.validarDireccion(direccion));
        } while (!validacion.validarDireccion(direccion).equals("Dirección válida"));

        String telefono1;
        do {
            System.out.printf("Ingrese el teléfono 1 (Ejemplo: +57 3xxxxxxx): ");
            telefono1 = entrada.nextLine();
            System.out.println(validacion.validarTelefono(telefono1));
        } while (!validacion.validarTelefono(telefono1).equals("Teléfono válido"));

        String telefono2;
        do {
            System.out.printf("Ingrese el teléfono 1 (Ejemplo: +57 3xxxxxxx): ");
            telefono2 = entrada.nextLine();
            System.out.println(validacion.validarTelefono(telefono2));
        } while (!validacion.validarTelefono(telefono2).equals("Teléfono válido"));

        String correo;
        do {
            System.out.print("Ingrese el correo electrónico: ");
            correo = entrada.nextLine();
            System.out.println(validacion.validarCorreo(correo));
        } while (!validacion.validarCorreo(correo).equals("Correo válido"));

        String fechaNacimiento;
        do {
            System.out.print("Ingrese la fecha de nacimiento (dd/mm/yyyy): ");
            fechaNacimiento = entrada.nextLine();
            System.out.println(validacion.validarFechaNacimiento(fechaNacimiento));
        } while (!validacion.validarFechaNacimiento(fechaNacimiento).equals("Fecha de nacimiento válida"));

        System.out.println("Grupos a los que pertenece (Separe los grupos por el simbolo '-->': ");
        String grupos = entrada.nextLine();

        list.agregarEnOrden(new Contacto(nombre, usuario, contrasena, telefono1, telefono2, correo, direccion, fechaNacimiento, grupos));

    }

    public static void main(String[] args) {
        ContactList list = new ContactList();
        agregarContactos(list);
        agregarContactos(list);
        list.mostrar();
    }

}