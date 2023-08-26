package gh.nijd.pong;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;

public class ApplicationStart extends Application {

    private static Player p1, p2;

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(ApplicationStart.class.getResource("menu.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1500, 750);
        stage.setResizable(false);
        stage.setTitle("Pong Game");
        stage.getIcons().add(new Image((getClass().getResourceAsStream("images/pong.png"))));
        stage.setScene(scene);
        stage.show();

        p1 = new Player("Player One", "p1");
        p2 = new Player("Player Two", "p2");
    }

    public void exitGame(ActionEvent event) {
        System.exit(0);
    }

    public void startGame(ActionEvent event) {
        Player[] playerArr = {p1, p2};
        GameController GC = new GameController();
        GC.getPlayers(playerArr);

        new Console().selectNewSceneButton("game.fxml", event);
    }

    public void settings(ActionEvent event) {
        Player[] playerArr = {p1, p2};
        SettingsController SC = new SettingsController();
        SC.getPlayers(playerArr);

        new Console().selectNewSceneButton("settings.fxml", event);
    }
    public void getPlayers(Player[] playerArr) {
        p1 = playerArr[0];
        p2 = playerArr[1];
    }
    public static void main(String[] args) {
        launch();
    }
}