package Backend;

import java.util.Stack;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import javax.swing.JOptionPane;

/*public class ContactList {
    Nodo punta, ultimo;
    Stack<Nodo> pila = new Stack<Nodo>();
    ArrayList<String> subListas = new ArrayList<>();
    ArrayList<Character> Nombre_subListas = new ArrayList<>();
    Nodo temp = new Nodo();

    public ContactList() {
        this.punta = null;
        this.ultimo = null;
        punta = temp;
        ultimo = temp;
    }

    public void setSubList(String subL) {
        subListas.add(subL);
    }

    public void setSubL_name(char nameL) {
        Nombre_subListas.add(nameL);
    }

    public void crearLista(String caracteres) {
        for (int i = 0; i < caracteres.length(); i++) {
            switch (caracteres.charAt(i)) {
                case '(':
                    pila.push(ultimo);
                    temp = new Nodo();
                    ultimo.setTag(1);
                    ultimo.setDato(temp);
                    ultimo = temp;
                    break;

                case ',':
                    temp = new Nodo();
                    ultimo.setLiga(temp);
                    ultimo = temp;
                    break;

                case ')':
                    ultimo = pila.pop();
                    break;

                default:
                    char caracter = caracteres.charAt(i);
                    if (Character.isLowerCase(caracter)) {
                        ultimo.setTag(0);
                        ultimo.setDato(caracter);
                    } else if (Character.isUpperCase(caracter)){
                        if (!Nombre_subListas.contains(caracter)) {
                            String sublist = JOptionPane.showInputDialog("¿Qué datos están dentro de la sublista " + caracter + "?: ");
                            setSubL_name(caracter);
                            setSubList("("+sublist+")");
                            crearLista("("+sublist+")");
                        } else {
                            crearLista(buscar_subLista(caracter));
                        }
                    }
                    break;
            }
        }
    }

    public void Cant_sublistas_yAtomos(String lista) {
        ArrayList <Character> repetidos = new ArrayList<>();
        int contadorSublistas = 0, contadorAtomos = 0;
        for (int i = 1; i < lista.length(); i++) {
            char caracter = lista.charAt(i);
            if (caracter == '(') {
                contadorSublistas++;
            } else if (Character.isLowerCase(caracter) && !repetidos.contains(caracter)) {
                contadorAtomos++;
                repetidos.add(caracter);
            }
        }
        JOptionPane.showMessageDialog(null, "Esta lista tiene " + contadorSublistas + " sublistas y " + contadorAtomos + " átomos.", "Cantidad de sublistas y átomos", JOptionPane.INFORMATION_MESSAGE);
        repetidos.clear();
    }

    public String buscar_subLista(char caracter) {
        for (int i = 0; i < Nombre_subListas.size(); i++) {
            if (caracter == Nombre_subListas.get(i)) {
                return subListas.get(i);
            }
        }
        return "";
    }

    public String mostrarLista() {
        if (punta == null) {
            return "";
        } else {
            return mostrarL(punta);
        }
    }

    public String mostrarL(Nodo actual) {
        StringBuilder resultado = new StringBuilder();

        while (actual != null) {
            if (actual.getTag() == 0) {
                resultado.append(actual.getDato());
            } else if (actual.getTag() == 1) {
                resultado.append("(");
                resultado.append(mostrarL((Nodo) actual.getDato()));
                resultado.append(")");
            }
    
            actual = actual.getLiga();
    
            if (actual != null) {
                resultado.append(",");
            }
        }
    
        return resultado.toString();
    }

    public Nodo getPunta() {
        return punta;
    }

    public void setPunta(Nodo punta) {
        this.punta = punta;
    }

    public Nodo getUltimo() {
        return ultimo;
    }

    public void setUltimo(Nodo ultimo) {
        this.ultimo = ultimo;
    }
}*/

public class ContactList {
    private Nodo cabeza;

    // Método para agregar un contacto de forma ordenada
    public void agregar(Contacto contacto) throws IOException {
        Nodo nuevoNodo = new Nodo(0, contacto);

        // Si la lista está vacía o el contacto debe ir al inicio
        if (cabeza == null) {
            nuevoNodo.setLiga(cabeza);
            cabeza = nuevoNodo;
        } else {
            // Insertar en orden
            Nodo actual = cabeza;
            actual.setLiga(nuevoNodo);
        }
        this.quickSort();
    }

    public void crearGrupo(ContactList grupo) {
        Scanner entrada = new Scanner(System.in);
        System.out.println("Nombre del grupo: ");
        String groupName = entrada.nextLine();

        Nodo nuevoNodo = new Nodo(1, grupo); // Tag 1 para sublista
        if (cabeza == null) {
            cabeza = nuevoNodo;
        } else {
            Nodo actual = cabeza;
            while (actual.getLiga() != null) {
                actual = actual.getLiga();
            }
            actual.setLiga(nuevoNodo);
        }
        entrada.close();
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

            actual = siguiente; // Avanzar al siguiente nodo
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

    public void getFile(File archivoEntrada) throws IOException {
        try (BufferedReader lector = new BufferedReader(new FileReader(archivoEntrada))) {
            String linea;
            while ((linea = lector.readLine()) != null) {
                String[] parametros = linea.split(",");
                if (parametros[8] == null)
                    parametros[8] = "NoN";

                if (parametros.length == 10) { // Verifica que la línea tenga los 9 campos
                    Contacto nuevoContacto = new Contacto(parametros[0], parametros[1], parametros[2],
                            parametros[3], parametros[4], parametros[5],
                            parametros[6], parametros[7], parametros[8], parametros[9]);
                    agregar(nuevoContacto);
                } else {
                    System.err.println("Formato incorrecto en la línea: " + linea);
                }
            }
            System.out.println("Archivo leído con éxito.");
        } catch (IOException e) {
            System.err.println("Ocurrió un error al leer el archivo: " + e.getMessage());
        }
    }

    public void setFile(File archivoSalida) throws IOException {
        Nodo nodoActual = cabeza;
        
        try (BufferedWriter escritorSalida = new BufferedWriter(new FileWriter(archivoSalida))) {
            while (nodoActual != null) { // Condición para recorrer hasta el final de la lista
                Contacto actualContact = (Contacto) nodoActual.getDato();  // Castear el dato del nodo
    
                // Crear la cadena con los datos del contacto
                String linea = actualContact.getNombre() + "," + actualContact.getUsername() + ","
                        + actualContact.getPassword() + "," + actualContact.getTelefono1() + ","
                        + actualContact.getTelefono2() + "," + actualContact.getEmail() + ","
                        + actualContact.getDireccion() + "," + actualContact.getBornDate() + ","
                        + actualContact.getCategoria() + "," + actualContact.getGrupos();
    
                // Escribir la línea en el archivo
                escritorSalida.write(linea);
                escritorSalida.newLine(); // Agregar un salto de línea
    
                // Avanzar al siguiente nodo
                nodoActual = nodoActual.getLiga();
            }
        } catch (IOException e) {
            // Manejo de la excepción
            System.err.println("Ocurrió un error al escribir en el archivo: " + e.getMessage());
        }
    }
    

    // Método para mostrar la lista de contactos
    public void mostrar() throws IOException {
        Nodo actual = cabeza;
        System.out.print("[");
        while (actual != null) {
            if (actual.getTag() == 0) { // Valor atómico, es un Contacto
                System.out.print(actual.getDato());
            } else { // Es una sub-lista
                ((ContactList) actual.getDato()).mostrar();
            }
            actual = actual.getLiga();
            if (actual != null)
                System.out.print(", ");
        }
        System.out.println("]");

        File archivoSalida = new File("Contactos.txt");
        this.setFile(archivoSalida);
    }
}