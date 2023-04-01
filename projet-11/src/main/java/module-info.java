module esalaf.projet11 {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires org.kordamp.bootstrapfx.core;
    requires java.sql;

    opens esalaf.projet11 to javafx.fxml;
    exports esalaf.projet11;
}