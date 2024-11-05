package Backend;

import java.util.Stack;
import java.util.ArrayList;
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
    public void agregarEnOrden(Contacto contacto) {
        Nodo nuevoNodo = new Nodo();
        nuevoNodo.setTag(0); // Etiqueta 0 indica que es un valor atómico (Contacto)
        nuevoNodo.setDato(contacto);

        // Si la lista está vacía o el contacto debe ir al inicio
        if (cabeza == null || ((Contacto) cabeza.getDato()).getNombre().compareTo(contacto.getNombre()) > 0) {
            nuevoNodo.setLiga(cabeza);
            cabeza = nuevoNodo;
        } else {
            // Insertar en orden
            Nodo actual = cabeza;
            while (actual.getLiga() != null
                    && ((Contacto) actual.getLiga().getDato()).getNombre().compareTo(contacto.getNombre()) < 0) {
                actual = actual.getLiga();
            }
            nuevoNodo.setLiga(actual.getLiga());
            actual.setLiga(nuevoNodo);
        }
    }

    // Método para mostrar la lista de contactos
    public void mostrar() {
        Nodo actual = cabeza;
        System.out.print("[");
        while (actual != null) {
            if (actual.getTag() == 0) {  // Valor atómico, es un Contacto
                System.out.print(actual.getDato());
            } else {  // Es una sub-lista
                ((ContactList) actual.getDato()).mostrar();
            }
            actual = actual.getLiga();
            if (actual != null) System.out.print(", ");
        }
        System.out.println("]");
    }
}