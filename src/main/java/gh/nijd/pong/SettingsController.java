package gh.nijd.pong;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Popup;
import javafx.stage.Stage;

public class SettingsController {

    @FXML
    private Pane settingsPane;
    @FXML
    private ColorPicker p1ColourBox, p2ColourBox;
    @FXML
    private Spinner<Integer> p1SensBox, p2SensBox;
    @FXML
    private CheckBox fullscreenOption;

    private static Player p1, p2;


    public void p1SaveSettings(ActionEvent event) {
        p1.fillColour = p1ColourBox.getValue(); // Gets values of player settings
        p1.sensitivity = p1SensBox.getValue();
        createSavedPU("Player 1");
    }

    public void p2SaveSettings(ActionEvent event) {
        p2.fillColour = p2ColourBox.getValue();
        p2.sensitivity = p2SensBox.getValue();
        createSavedPU("Player 2");
    }

    public void generalSaveSettings(ActionEvent event) { // Annoying, many features need fixing, cba rn --- NEEDS ATTENTION
        ((Stage)(((Node) event.getSource()).getScene().getWindow())).setFullScreen(fullscreenOption.isSelected());
        createSavedPU("General");
    }

    public void createSavedPU(String playerType) { // Creates popup telling the player their data is saved
        Popup savedPU = new Popup();
        Label savedLabel = new Label(playerType + " settings saved!");
        savedLabel.setTextFill(Color.RED);
        savedLabel.setStyle(" -fx-background-color: white;");
        savedLabel.setPadding(new Insets(10,10,10,10));
        savedPU.getContent().add(savedLabel);
        savedPU.setAutoHide(true); // The popup disappears when focus lost
        savedPU.show((settingsPane.getScene().getWindow()));
    }

    public void backToMenu(ActionEvent event) {
        Player[] playerArr = {p1, p2};

        ApplicationStart AS = new ApplicationStart();
        AS.getPlayers(playerArr);
        new Console().selectNewScene("menu.fxml", (Stage)((Node) event.getSource()).getScene().getWindow(), ((Stage)((Node) event.getSource()).getScene().getWindow()).isFullScreen());
    }

    public void getPlayers(Player[] playerArr) {
        p1 = playerArr[0];
        p2 = playerArr[1];
    }

    public void initialize() {
        // Inits the spinners for sensitivity
        SpinnerValueFactory<Integer> p1SPFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(1,50, p1.sensitivity);
        SpinnerValueFactory<Integer> p2SPFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(1,50, p2.sensitivity);
        p1SensBox.setValueFactory(p1SPFactory);
        p2SensBox.setValueFactory(p2SPFactory);

        // Inits the colour pickers
        p1ColourBox.setValue(p1.fillColour);
        p2ColourBox.setValue(p2.fillColour);
    }
}
