package Backend;

import java.io.*;
import java.util.*;

public class Entrada {
    /*
     * 
     * 
     */

    public static void menu(ContactList list) throws IOException {
        Scanner entrada = new Scanner(System.in);
        System.out.println("\n1 - Añadir Contacto.\n2 - Crear Grupo.\n3 - Editar Contacto.\n4 - Eliminar contacto.\n5 - Eliminar grupo.\n6 - Mostrar lista. \n7 - Salir.");
        String resp = entrada.nextLine();

        switch (resp) {
            case "1":
                agregarContacto(list);
                break;
            case "2":
                list.crearGrupo(list);
                break;
            case "3":
                // Editar;
                break;
            case "4":
                // Eliminar contacto
                break;
            case "5":
                // Eliminar grupo
                break;
            case "6":
                list.mostrar();
                break;
            default:
                System.exit(0);
                break;
        }
     }

    public static void agregarContacto(ContactList list) throws IOException {
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
            System.out.printf("Ingrese el teléfono 1 (móvil/fijo): ");
            telefono1 = entrada.nextLine();
            System.out.println(validacion.validarTelefono(telefono1));
        } while (!validacion.validarTelefono(telefono1).equals("Teléfono válido"));

        String telefono2;
        do {
            System.out.printf("Ingrese el teléfono 2 (móvil/fijo): ");
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

        list.agregar(new Contacto(nombre, usuario, contrasena, telefono1, telefono2, correo, direccion,
                fechaNacimiento, "NoN", "NoN"));
        
    }

    // Clase para manejar líneas y el archivo de donde provienen durante la fusión
    private static class LineaArchivo implements Comparable<LineaArchivo> {
        String linea;
        BufferedReader archivo; // para leer cada linea del archivo (lector)

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

    public static void main(String[] args) throws IOException {
        ContactList list = new ContactList();
        File archivoEntrada = new File("Contactos.txt");
        list.getFile(archivoEntrada);
        //while (true) {
            menu(list);
            menu(list);
        //}
    }

}