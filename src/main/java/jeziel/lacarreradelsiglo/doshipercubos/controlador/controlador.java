package jeziel.lacarreradelsiglo.doshipercubos.controlador;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.scene.shape.Shape;
import jeziel.lacarreradelsiglo.doshipercubos.modelo.*;
import javafx.scene.control.Button;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.ResourceBundle;

public class controlador implements Initializable, ratonListener {
    private Buffer buffer;
    private Gato gato;
    private Raton raton1;
    private Raton raton2;
    private Raton raton3;
    private Raton raton4;
    private StringBuilder sb;
    private ObservableList<String> lista;
    private Random random;

    @FXML
    private TextField inicioRuta;
    @FXML
    private TextField finalRuta;
    @FXML
    private Button comenzarBoton;
    @FXML
    private ListView<String> rutas;
    @FXML
    private Label rutaActual;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        random = new Random();

        lista = FXCollections.observableArrayList();
        rutas.setItems(lista);
        rutas.getSelectionModel().selectedItemProperty().addListener((observable, valorAnterior, valorNuevo) ->{
            if(valorNuevo != null){
                mostrarRuta(valorNuevo);
                rutaActual.setText(valorNuevo);
            }
        });
    }

    @FXML
    public void clickEmpezar(){
        lista.clear();
        buffer = new Buffer();
        this.gato = new Gato(this.buffer, this);
        Thread g1 = new Thread(gato);
        g1.setDaemon(true);
        g1.start();
        this.raton1 = new Raton(0,inicioRuta.getText(),finalRuta.getText(),this, this.buffer);
        this.raton2 = new Raton(1,inicioRuta.getText(),finalRuta.getText(),this, this.buffer);
        this.raton3 = new Raton(2,inicioRuta.getText(),finalRuta.getText(),this, this.buffer);
        this.raton4 = new Raton(3,inicioRuta.getText(),finalRuta.getText(),this, this.buffer);
        Thread t1 = new Thread(raton1);
        Thread t2 = new Thread(raton2);
        Thread t3 = new Thread(raton3);
        Thread t4 = new Thread(raton4);
        t1.start();
        t2.start();
        t3.start();
        t4.start();
    }

    @Override
    public void actualizar(String s) {
        Platform.runLater(() -> {
            lista.add(s);
        });
    }

    @Override
    public void actualizarLabelRutaActual() {
        Platform.runLater(() -> {
            int indice = random.nextInt(0,rutas.getItems().size());
            rutas.getSelectionModel().select(indice);
            rutas.scrollTo(indice);
            rutaActual.setText(rutas.getItems().get(indice));
        });
    }


    public void mostrarRuta(String rutaSeleccionada){
        restaurarLineas();
        String rutaLimpia = rutaSeleccionada.replace("{", "").replace("}", "");
        String[] nodos = rutaLimpia.split("->");
        for (int i = 0; i < nodos.length - 1; i++) {
            int num1 = Integer.parseInt(nodos[i], 2);
            int num2 = Integer.parseInt(nodos[i+1], 2);
            int menor = Math.min(num1, num2);
            int mayor = Math.max(num1, num2);
            String idBuscado = "#linea" + menor + "y" + mayor;
            Shape linea = (Shape) rutas.getScene().lookup(idBuscado);
            if (linea != null) {
                linea.setStroke(Color.RED);
                linea.setStrokeWidth(3.0);
            } else {
                System.out.println("No se encontró  " + idBuscado);
            }
        }
    }

    private void restaurarLineas() {
        if (rutas.getScene() == null) return;
        for (javafx.scene.Node nodo : rutas.getScene().getRoot().lookupAll("*")) {
            if (nodo.getId() != null && nodo.getId().startsWith("linea") && nodo instanceof Shape) {
                Shape forma = (Shape) nodo;
                forma.setStroke(Color.BLACK);
                forma.setStrokeWidth(1.0);
            }
        }
    }

}
