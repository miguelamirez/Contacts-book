package Backend;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class ContactList {
    private Nodo cabeza;
    private Map<String, ContactList> sublistas;

    public ContactList() {
        sublistas = new HashMap<>();
    }

    public void agregar(Contacto contacto) throws IOException {
        Nodo nuevoNodo = new Nodo(0, contacto);

        // Si la lista está vacía o el contacto debe ir al inicio
        if (cabeza == null) {
            nuevoNodo.setLiga(cabeza);
            cabeza = nuevoNodo;
        } else {
            Nodo actual = cabeza;
            while (actual.getLiga() != null) {
                actual = actual.getLiga();
            }
            actual.setLiga(nuevoNodo);
        }
        this.quickSort();
        this.setFile("Contactos.txt");
    }

    // Método para crear un grupo
    public void crearGrupo() throws IOException {
        Scanner entrada = new Scanner(System.in);
        System.out.println("Nombre del grupo: ");
        String groupName = entrada.nextLine();

        // Verificamos si el grupo ya existe
        if (sublistas.containsKey(groupName)) {
            System.out.println("El grupo ya existe.");
        } else {
            ContactList nuevoGrupo = new ContactList();
            sublistas.put(groupName, nuevoGrupo); // Añadimos el grupo al HashMap
            System.out.println("Grupo '" + groupName + "' creado exitosamente.");
        }
        entrada.close();
        this.quickSort();
        this.setFile("Contactos.txt");
    }

    // Método para agregar un contacto a una sublista
    public void agregarAGrupo(String groupName, Contacto contacto) throws IOException {
        if (sublistas.containsKey(groupName)) {
            ContactList grupo = sublistas.get(groupName);
            try {
                grupo.agregar(contacto); // Agregamos el contacto al grupo correspondiente
            } catch (IOException e) {
                System.err.println("Error al agregar el contacto al grupo: " + e.getMessage());
            }
        } else {
            System.out.println("El grupo '" + groupName + "' no existe.");
        }
        this.quickSort();
        this.setFile("Contactos.txt");
    }

    // Método para eliminar un nodo
    public boolean eliminarContacto(String nombreContacto) throws IOException {
        return eliminarNodo(cabeza, nombreContacto);
    }

    private boolean eliminarNodo(Nodo actual, String nombre) throws IOException {
        Nodo previo = null;
    
        while (actual != null) {
            Contacto actualContact = (Contacto) actual.getDato();
            // Si el dato coincide y no es una sublista
            if (actualContact.getNombre().equals(nombre)) {
                if (previo == null) { // Eliminar la cabeza
                    cabeza = actual.getLiga();
                } else { // Eliminar un nodo intermedio o final
                    previo.setLiga(actual.getLiga());
                }
                return true; // Nodo eliminado
            }
    
            // Si el dato es una sublista, buscar dentro de ella
            if (actual.getDato() instanceof ContactList) {
                ContactList sublista = (ContactList) actual.getDato();
                if (sublista.eliminarContacto(nombre)) {
                    return true;
                }
            }
    
            // Avanzar al siguiente nodo
            previo = actual;
            actual = actual.getLiga();
        }
        this.quickSort();
        this.setFile("Contactos.txt");
        return false; // Valor no encontrado
    }

    // Método para mostrar la lista de contactos
    public void mostrar() throws IOException {
        Nodo actual = cabeza;
        System.out.print("[");

        // Mostrar los contactos de la lista principal
        while (actual != null) {
            if (actual.getTag() == 0) { // Valor atómico, es un Contacto
                System.out.print(actual.getDato());
            }
            actual = actual.getLiga();
            if (actual != null)
                System.out.print(", ");
        }

        // Mostrar las sublistas (grupos)
        for (Map.Entry<String, ContactList> entry : sublistas.entrySet()) {
            String groupName = entry.getKey();
            ContactList grupo = entry.getValue();
            System.out.println("\nGrupo: " + groupName);
            grupo.mostrar();
        }

        System.out.println("]");
    }

    public void quickSort() {
        cabeza = quickSortRec(cabeza);
    }

    private Nodo quickSortRec(Nodo start) {
        // Caso base: lista vacía o con un solo elemento
        if (start == null || start.getLiga() == null) {
            return start;
        }

        // Elegir pivote (primer nodo de la lista)
        Nodo pivot = start;

        // Dividir la lista en dos partes: menor y mayor
        Nodo menores = null, mayores = null;
        Nodo actual = start.getLiga(); // Comienza después del pivote

        while (actual != null) {
            Nodo siguiente = actual.getLiga(); // Guardar el siguiente nodo
            actual.setLiga(null); // Desconectar el nodo actual

            // Comparar con el pivote
            if (((Contacto) actual.getDato()).getNombre()
                    .compareTo(((Contacto) pivot.getDato()).getNombre()) <= 0) {
                actual.setLiga(menores);
                menores = actual;
            } else {
                actual.setLiga(mayores);
                mayores = actual;
            }

            actual = siguiente;
        }

        // Ordenar recursivamente las particiones
        menores = quickSortRec(menores);
        mayores = quickSortRec(mayores);

        // Combinar resultados
        return concatenar(menores, pivot, mayores);
    }

    private Nodo concatenar(Nodo menores, Nodo pivot, Nodo mayores) {
        // Conectar menores -> pivote
        if (menores == null) {
            pivot.setLiga(mayores);
            return pivot;
        }

        Nodo actual = menores;
        while (actual.getLiga() != null) {
            actual = actual.getLiga();
        }

        actual.setLiga(pivot); // Último de menores apunta al pivote
        pivot.setLiga(mayores); // Pivote apunta a mayores
        return menores;
    }

    public void getFile(String nombreArchivo) throws IOException {
        File archivo = new File(nombreArchivo);
        try (BufferedReader lector = new BufferedReader(new FileReader(archivo))) {
            String linea;
            while ((linea = lector.readLine()) != null) {
                String[] parametros = linea.split(",");
                if (parametros[9] == null)
                    parametros[9] = "NoN";

                if (parametros.length == 10) { // Verifica que la línea tenga los 10 campos
                    Contacto nuevoContacto = new Contacto(parametros[0], parametros[1], parametros[2],
                            parametros[3], parametros[4], parametros[5],
                            parametros[6], parametros[7], parametros[8], parametros[9]);
                    this.agregar(nuevoContacto);
                } else {
                    System.err.println("Formato incorrecto en la línea: " + linea);
                }
            }
            this.quickSort();
            this.setFile("Contactos.txt");
            System.out.println("Archivo leído con éxito.");
        } catch (IOException e) {
            System.err.println("Ocurrió un error al leer el archivo: " + e.getMessage());
        }
    }

    public void setFile(String nombreArchivo) throws IOException {
        File archivoSalida = new File(nombreArchivo);
        Nodo nodoActual = cabeza;

        try (BufferedWriter escritorSalida = new BufferedWriter(new FileWriter(archivoSalida))) {
            while (nodoActual != null) {
                Contacto actualContact = (Contacto) nodoActual.getDato();

                // Crear la cadena con los datos del contacto
                String linea = actualContact.getNombre() + "," + actualContact.getUsername() + ","
                        + actualContact.getPassword() + "," + actualContact.getTelefono1() + ","
                        + actualContact.getTelefono2() + "," + actualContact.getEmail() + ","
                        + actualContact.getDireccion() + "," + actualContact.getBornDate() + ","
                        + actualContact.getCategoria() + "," + actualContact.getGrupos();

                // Escribir la línea en el archivo
                escritorSalida.write(linea);
                escritorSalida.newLine(); // Agregar un salto de línea

                nodoActual = nodoActual.getLiga();
            }
        } catch (IOException e) {
            System.err.println("Ocurrió un error al escribir en el archivo: " + e.getMessage());
        }
    }
}