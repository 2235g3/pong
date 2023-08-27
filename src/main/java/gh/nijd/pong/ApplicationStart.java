package gh.nijd.pong;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;

public class ApplicationStart extends Application {

    private static Player p1, p2; // Creates Player objects

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(ApplicationStart.class.getResource("menu.fxml")); // Gets the FXML file
        Scene scene = new Scene(fxmlLoader.load(), 1500, 750); // Loads FXML file as a scene
        stage.setResizable(false); // Prevents player from changing window size
        stage.setTitle("Pong Game"); // Sets the window title
        stage.getIcons().add(new Image((getClass().getResourceAsStream("images/pong.png")))); // Adds the application icon to the window
        stage.setScene(scene); // Adds the scene to the window
        stage.show();

        p1 = new Player("Player One", "p1");
        p2 = new Player("Player Two", "p2");
    }

    public void exitGame(ActionEvent event) {
        System.exit(0); // Exits the program
    }

    public void startGame(ActionEvent event) {
        p1.windowHeight = ((Node) event.getSource()).getScene().getHeight();
        p1.windowWidth = ((Node) event.getSource()).getScene().getWidth();
        p2.windowHeight = ((Node) event.getSource()).getScene().getHeight();
        p2.windowWidth = ((Node) event.getSource()).getScene().getWidth();

        Player[] playerArr = {p1, p2}; // Allows for both Player objects to be transferred to the next controller by creating an instance of the controller and sending the data to the receiving method
        GameController GC = new GameController();
        GC.getPlayers(playerArr);

        new Console().selectNewScene("game.fxml", (Stage)((Node) event.getSource()).getScene().getWindow(), ((Stage)((Node) event.getSource()).getScene().getWindow()).isFullScreen());
    }

    public void settings(ActionEvent event) {
        Player[] playerArr = {p1, p2};
        SettingsController SC = new SettingsController();
        SC.getPlayers(playerArr);

        new Console().selectNewScene("settings.fxml", (Stage)((Node) event.getSource()).getScene().getWindow(), ((Stage)((Node) event.getSource()).getScene().getWindow()).isFullScreen());
    }
    public void getPlayers(Player[] playerArr) { // Receives player data when stage switched
        p1 = playerArr[0];
        p2 = playerArr[1];
    }
    public static void main(String[] args) {
        launch();
    }
}