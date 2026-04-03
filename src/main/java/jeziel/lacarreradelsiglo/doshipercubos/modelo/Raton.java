package jeziel.lacarreradelsiglo.doshipercubos.modelo;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

public class Raton implements Runnable{
    private int nivel;
    private boolean apply = true;
    private String inicio;
    private Queso actual;
    private Deque<Queso> abiertos;
    private List<Queso> suscesores;
    private StringBuilder sb;
    private List<Queso> quesoFinal;

    public Raton(int nivel, String inicio){
        sb = new StringBuilder();
        if (inicio.charAt(nivel) == '1'){
            sb.append(inicio);
            sb.setCharAt(nivel,'0');
            this.inicio = sb.toString();
            sb.setLength(0);
            this.nivel = nivel;
        }else{
            apply = false;
        }
    }

    @Override
    public void run() {
        if(!apply){
            return; //El hilo se suicida
        }
        abiertos = new ArrayDeque<>();
        suscesores = new ArrayList<>();
        quesoFinal = new ArrayList<>();
        Queso root = new Queso(null, inicio);
        abiertos.push(root);
        buscar();
    }

    public void buscar(){
        while(!abiertos.isEmpty()){
            //Funcion para regresar cuando llegue a un tope(comprobacion que ha llegado a un tope)
            this.actual = abiertos.pop();
            generarSuscesores(this.actual);

            if(suscesores.isEmpty()){
                quesoFinal.add(this.actual);
            }
            suscesores.clear();
        }
    }

    public void generarSuscesores(Queso actual){
        for(int i=0; i<actual.getActual().length(); i++){
            if(actual.getActual().charAt(i) == '1'){
                sb.append(actual.getActual());
                sb.setCharAt(i,'0');
                suscesores.add(new Queso(actual, sb.toString()));
                sb.setLength(0);
            }
        }
        //Vaciar suscesores en abiertos
        for(int i = 0; i < suscesores.size(); i++){
            abiertos.push(suscesores.get(i));
        }
    }

    public int contar(String texto){
        int contador = 0;
        for (int i = 0; i < texto.length(); i++) {
            if (texto.charAt(i) == '1') {
                contador++;
            }
        }
        return contador;
    }

    //Getters y setters por si son necesarios

    public int getNivel() {
        return nivel;
    }

    public void setNivel(int nivel) {
        this.nivel = nivel;
    }

    public boolean isApply() {
        return apply;
    }

    public void setApply(boolean apply) {
        this.apply = apply;
    }

    public String getInicio() {
        return inicio;
    }

    public void setInicio(String inicio) {
        this.inicio = inicio;
    }

    public Queso getActual() {
        return actual;
    }

    public void setActual(Queso actual) {
        this.actual = actual;
    }

    public Deque<Queso> getAbiertos() {
        return abiertos;
    }

    public void setAbiertos(Deque<Queso> abiertos) {
        this.abiertos = abiertos;
    }

    public List<Queso> getSuscesores() {
        return suscesores;
    }

    public void setSuscesores(List<Queso> suscesores) {
        this.suscesores = suscesores;
    }

    public StringBuilder getSb() {
        return sb;
    }

    public void setSb(StringBuilder sb) {
        this.sb = sb;
    }
}
