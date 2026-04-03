package jeziel.lacarreradelsiglo.doshipercubos.modelo;

public class Queso {
    private Queso anterior;
    private String actual;

    public Queso (Queso anterior, String actual) {
        this.actual = actual;
        this.anterior = anterior;
    }

    public Queso getAnterior() {
        return anterior;
    }

    public void setAnterior(Queso anterior) {
        this.anterior = anterior;
    }

    public String getActual() {
        return actual;
    }

    public void setActual(String actual) {
        this.actual = actual;
    }
}


