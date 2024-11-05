package Backend;

public class Nodo {
    private int tag;
    private Object dato;
    private Nodo liga;
    
    public Nodo() {
        liga = null;
    }

    public Object getDato() {
        return dato;
    }

    public void setDato(Object dato) {
        this.dato = dato;
    }

    public int getTag() {
        return tag;
    }

    public void setTag(int Tag) {
        tag = Tag;
    }

    public Nodo getLiga() {
        return liga;
    }

    public void setLiga(Nodo Liga) {
        liga = Liga;
    }

}