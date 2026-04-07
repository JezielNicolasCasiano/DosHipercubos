package jeziel.lacarreradelsiglo.doshipercubos.modelo;

public class Queso {
    private Queso anterior;
    private String rutaActual;
    private String nodoActual;

    public Queso (Queso anterior, String actual, String nodoActual) {
        this.rutaActual = actual;
        this.anterior = anterior;
        this.nodoActual = nodoActual;
    }

    public Queso getAnterior() {
        return anterior;
    }

    public void setAnterior(Queso anterior) {
        this.anterior = anterior;
    }

    public String getRutaActual() {
        return rutaActual;
    }

    public void setRutaActual(String rutaActual) {
        this.rutaActual = rutaActual;
    }

    public String getNodoActual() {
        return nodoActual;
    }

    public void setNodoActual(String nodoActual) {
        this.nodoActual = nodoActual;
    }
}


