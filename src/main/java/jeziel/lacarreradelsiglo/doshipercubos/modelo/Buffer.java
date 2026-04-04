package jeziel.lacarreradelsiglo.doshipercubos.modelo;

public class Buffer implements BufferInterface{
    private boolean avaliable = false;
    private  String content;

    public synchronized String get(){
        while(!avaliable){
            try{
                wait(); //Espera a que available sea verdadero
            }catch (InterruptedException e){}

        }
        avaliable = false;
        notifyAll(); //Le avisa al productor que la caja ya esta vacia
        return content;
    }

    public synchronized void put(String value) {
        while (avaliable == true) {
            try {
                wait(); // Espera a que available sea falso
            } catch (InterruptedException e) {
            }
        }
        content = value;
        avaliable = true;
        notifyAll(); //Le avisa al consumidor que hay algo en la caja
    }

}
