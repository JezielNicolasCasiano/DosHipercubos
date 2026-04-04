package jeziel.lacarreradelsiglo.doshipercubos.controlador;

import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import jeziel.lacarreradelsiglo.doshipercubos.modelo.*;

import javax.swing.text.html.ListView;
import java.awt.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
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

    @FXML
    private TextField inicioRuta;
    @FXML
    private TextField finalRuta;
    @FXML
    private Button comenzarBoton;
    @FXML
    private ListView rutas;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        buffer = new Buffer();
        lista = FXCollections.observableArrayList();
        lista.addListener((ListChangeListener<String>) cambio ->{
            while(cambio.next()){

            }
        });
    }

    @FXML
    public void clickEmpezar(){
        this.gato = new Gato(this.buffer, this);
        Thread g1 = new Thread(gato);
        g1.setDaemon(true);
        g1.start();
        this.raton1 = new Raton(0,inicioRuta.getText(),this, this.buffer);
        this.raton2 = new Raton(1,inicioRuta.getText(),this, this.buffer);
        this.raton3 = new Raton(2,inicioRuta.getText(),this, this.buffer);
        this.raton4 = new Raton(3,inicioRuta.getText(),this, this.buffer);
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
        lista.add(s);
    }

}
