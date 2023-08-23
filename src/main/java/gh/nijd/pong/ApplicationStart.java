package gh.nijd.pong;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class ApplicationStart extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(ApplicationStart.class.getResource("menu.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1500, 750);
        stage.setResizable(false);
        stage.setTitle("Pong Game");
        stage.setScene(scene);
        stage.show();
    }

    public void exitGame(ActionEvent event) {
        System.exit(1);
    }

    public void startGame(ActionEvent event) {
        new Console().selectNewScene("game.fxml", event);
    }

    public static void main(String[] args) {
        launch();
    }
}