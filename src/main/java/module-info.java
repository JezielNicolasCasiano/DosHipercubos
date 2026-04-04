module jeziel.lacarreradelsiglo.doshipercubos {
    requires javafx.controls;
    requires javafx.fxml;

    // Abrimos el paquete principal
    opens jeziel.lacarreradelsiglo.doshipercubos to javafx.fxml;
    exports jeziel.lacarreradelsiglo.doshipercubos;

    // NUEVO: Abrimos el paquete del controlador para que JavaFX pueda acceder a él
    opens jeziel.lacarreradelsiglo.doshipercubos.controlador to javafx.fxml;
}