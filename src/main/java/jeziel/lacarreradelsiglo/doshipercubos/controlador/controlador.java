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
        buffer = new Buffer();
        lista = FXCollections.observableArrayList();
        rutas.setItems(lista);
        rutas.getSelectionModel().selectedItemProperty().addListener((observable, valorAnterior, valorNuevo) ->{
            if(valorNuevo != null){

            }
        });
    }

    @FXML
    public void clickEmpezar(){
        lista.clear();
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

    public void mostrarRuta(){

    }
}
