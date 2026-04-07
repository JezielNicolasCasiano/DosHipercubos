package jeziel.lacarreradelsiglo.doshipercubos.modelo;

import java.util.*;

public class Raton implements Runnable{
    private BufferInterface buffer;
    private int nivel;
    private boolean apply = true;
    private String inicio;
    private String nodoInicial;
    private Queso actual;
    private Deque<Queso> abiertos;
    private List<Queso> suscesores;
    private StringBuilder sb;
    private List<Queso> quesoFinal;
    private ratonListener listener;
    private Random random;
    private StringBuilder bs;

    public Raton(int nivel, String inicio, String meta, ratonListener listener, BufferInterface buffer ){
        sb = new StringBuilder();
        bs = new StringBuilder();
        this.nodoInicial = inicio;
        this.inicio = Integer.toBinaryString(Integer.parseInt(inicio,2)^Integer.parseInt(meta,2));
        sb.append(this.inicio);
        for(int i=this.inicio.length();i<4;i++){
            sb.insert(0,"0");
        }
        inicio = sb.toString();
        sb.setLength(0);
        if (inicio.charAt(nivel) == '1'){
            sb.append(inicio);
            sb.setCharAt(nivel,'0');
            this.inicio = sb.toString();
            sb.setLength(0);
            this.nivel = nivel;
            this.listener = listener;
            this.buffer = buffer;
            random = new Random();

        }else{
            apply = false;
            this.buffer = buffer;
        }
    }

    @Override
    public void run() {
        if(!apply){
            buffer.put("Fin");
            return; //El hilo se suicida
        }
        abiertos = new ArrayDeque<>();
        suscesores = new ArrayList<>();
        quesoFinal = new ArrayList<>();
        Queso root = new Queso(null, inicio,nodoInicial);
        abiertos.push(root);
        buscar();
        ArrayList<String> rutas = obtenerRuta(quesoFinal);
        int indice = 0;
        while (!(rutas.isEmpty())){
            indice = random.nextInt(0,rutas.size());
            buffer.put(rutas.get(indice));
            rutas.remove(indice);
        }
        buffer.put("Fin");
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
        for(int i = 0; i<actual.getRutaActual().length(); i++){
            if(actual.getRutaActual().charAt(i) == '1'){
                bs.repeat("0", actual.getRutaActual().length());
                bs.setCharAt(i,'1');
                sb.append(actual.getRutaActual());
                sb.setCharAt(i,'0');
                suscesores.add(new Queso(actual, sb.toString(), moverNodoActual(bs.toString(),actual.getNodoActual())));
                sb.setLength(0);
                bs.setLength(0);
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

    //Metodo de un qeusoFinal obtiene los String de cada queso
    public ArrayList<String>  obtenerRuta(List<Queso> quesoFinal){
        int j = 0;
        for(int i=quesoFinal.size()-1; i>=0; i--){
            System.out.printf(String.valueOf(quesoFinal.get(i).getRutaActual()));
        }

        sb = new StringBuilder();
        ArrayList<String> anterior = new ArrayList<>();
        ArrayList<String> temp = new ArrayList<>();
        for (int i = quesoFinal.size(); i > 0; i--) {
            Queso actual = quesoFinal.get(j);
            while (actual != null) {
                temp.add(actual.getRutaActual());
                actual = actual.getAnterior();
            }
            System.out.printf(String.valueOf(temp));
            sb.append("{");
            for (int k = temp.size(); k > 0; k--) {
                sb.append(temp.get(k - 1));
                sb.append("->");
            }
            sb.append("}");
            anterior.add(sb.toString());
            sb.setLength(0);
            temp.clear();
            j++;
        }
        System.out.printf(String.valueOf(anterior));
        return anterior;
    }

    public String moverNodoActual(String cambio, String nodoActual){
        return Integer.toBinaryString(Integer.parseInt(cambio,2)^Integer.parseInt(nodoActual,2));
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
