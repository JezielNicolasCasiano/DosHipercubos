package jeziel.lacarreradelsiglo.doshipercubos.controlador;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import jeziel.lacarreradelsiglo.doshipercubos.modelo.Queso;
import jeziel.lacarreradelsiglo.doshipercubos.modelo.Raton;
import jeziel.lacarreradelsiglo.doshipercubos.modelo.ratonListener;

import java.awt.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class controlador implements Initializable, ratonListener {
    private Raton raton1;
    private Raton raton2;
    private Raton raton3;
    private Raton raton4;
    private StringBuilder sb;
    private ObservableList observableList;

    @FXML
    private TextField inicio;
    @FXML
    private TextField final1;
    @FXML
    private Button comenzar;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    @FXML
    public void clickEmpezar(){
        raton1 = new Raton(1,inicio.getText(),this);
        raton2 = new Raton(2,inicio.getText(),this);
        raton3 = new Raton(3,inicio.getText(),this);
        raton4 = new Raton(4,inicio.getText(),this);
    }


    @Override
    public void actualizar(List<Queso> quesoFinal) {

    }




}
