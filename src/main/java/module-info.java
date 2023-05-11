module com.example.demo {
    requires javafx.controls;
    requires javafx.fxml;
            
        requires org.controlsfx.controls;
                    requires org.kordamp.ikonli.javafx;
    requires com.google.gson;
    requires org.apache.httpcomponents.httpcore;

    opens com.weather.app to javafx.fxml;
    exports com.weather.app;
}