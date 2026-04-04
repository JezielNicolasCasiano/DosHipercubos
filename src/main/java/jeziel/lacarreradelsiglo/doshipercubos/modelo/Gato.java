package jeziel.lacarreradelsiglo.doshipercubos.modelo;

public class Gato implements Runnable{
    private BufferInterface buffer;
    private ratonListener listener;

    public Gato(BufferInterface buffer, ratonListener listener){
        this.buffer = buffer;
        this.listener = listener;
    }

    @Override
    public void run() {
        listener.actualizar(buffer.get());
    }
}
