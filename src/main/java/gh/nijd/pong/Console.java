package gh.nijd.pong;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Console {
    private Scene newScene;
    private Parent root;

    public void selectNewScene(String fileName, Stage stage, boolean fullscreen) {
        try {
            root = FXMLLoader.load(getClass().getResource(fileName)); // Gets the new FXML scene
            newScene = new Scene(root); // Makes the FXML into a scene
            newScene.getRoot().requestFocus(); // Requests focus so the container can send updates and get focus
            stage.setScene(newScene); // Sets the new scene
            stage.setFullScreen(fullscreen); // Sets the screen to the selected screen size
            stage.show(); // Displays the new scene
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
