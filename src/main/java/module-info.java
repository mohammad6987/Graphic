module workshop {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.graphics;
    exports view;
    opens view to javafx.fxml;
    exports model;
    requires com.google.gson;
    requires javafx.media;
    opens model to com.google.gson;
}