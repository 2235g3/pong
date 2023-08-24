package gh.nijd.pong;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class Console {
    public Stage stage;
    private Scene newScene;
    private Parent root;
    public void selectNewScene(String fileName, ActionEvent event) {
        try {
            root = FXMLLoader.load(getClass().getResource(fileName)); // Gets the new FXML scene
            stage = (Stage) ((Node) event.getSource()).getScene().getWindow(); // Gets the stage that is to be changed
            newScene = new Scene(root); // Makes the FXML into a scene
            newScene.getRoot().requestFocus(); // Requests focus so the container can send updates and get focus
            stage.setScene(newScene); // Sets the new scene
            stage.show(); // Displays the new scene
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
