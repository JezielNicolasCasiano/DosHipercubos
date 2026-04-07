package jeziel.lacarreradelsiglo.doshipercubos.modelo;

public class Gato implements Runnable{
    private BufferInterface buffer;
    private ratonListener listener;
    private int ratonesCazados;

    public Gato(BufferInterface buffer, ratonListener listener){
        this.buffer = buffer;
        this.listener = listener;
    }

    @Override
    public void run() {
        ratonesCazados = 0;
        while(ratonesCazados < 4){
            String s = buffer.get();
            if(s =="Fin"){
                ratonesCazados++;
            }else{
                listener.actualizar(s);
            }
        }
        listener.actualizarLabelRutaActual();
    }
}
