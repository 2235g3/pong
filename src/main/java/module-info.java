module gh.nijd.pong {
    requires javafx.controls;
    requires javafx.fxml;

    requires com.almasb.fxgl.all;
    requires java.desktop;

    opens gh.nijd.pong to javafx.fxml;
    exports gh.nijd.pong;
}