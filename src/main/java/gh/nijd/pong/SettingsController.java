package gh.nijd.pong;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.Label;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Popup;

public class SettingsController {

    @FXML
    Pane settingsPane;
    @FXML
    ColorPicker p1ColourBox, p2ColourBox;
    @FXML
    private Spinner<Integer> p1SensBox, p2SensBox;

    private static Player p1, p2;


    public void p1SaveSettings(ActionEvent event) { // Messy, needs cleaning
        p1.fillColor = p1ColourBox.getValue();


        p1.sensitivity = p1SensBox.getValue();


        Popup savedPU = new Popup();
        Label savedLabel = new Label("Player 1 settings saved!");
        savedLabel.setTextFill(Color.RED);
        savedLabel.setStyle(" -fx-background-color: white;");
        savedLabel.setPadding(new Insets(10,10,10,10));
        savedPU.getContent().add(savedLabel);
        savedPU.setAutoHide(true);
        savedPU.show((settingsPane.getScene().getWindow()));
    }

    public void p2SaveSettings(ActionEvent event) { // Messy, needs cleaning
        p2.fillColor = p2ColourBox.getValue();
        p2.sensitivity = p2SensBox.getValue();
        Popup savedPU = new Popup();
        Label savedLabel = new Label("Player 2 settings saved!");
        savedLabel.setTextFill(Color.RED);
        savedLabel.setStyle(" -fx-background-color: white;");
        savedLabel.setPadding(new Insets(10,10,10,10));
        savedPU.getContent().add(savedLabel);
        savedPU.setAutoHide(true);
        savedPU.show((settingsPane.getScene().getWindow()));

    }

    public void backToMenu(ActionEvent event) {
        Player[] playerArr = {p1, p2};

        ApplicationStart AS = new ApplicationStart();
        AS.getPlayers(playerArr);
        new Console().selectNewSceneButton("menu.fxml", event);
    }

    public void getPlayers(Player[] playerArr) {
        p1 = playerArr[0];
        p2 = playerArr[1];
    }

    public void initialize() {
        SpinnerValueFactory<Integer> p1SPFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(1,50, p1.sensitivity);
        SpinnerValueFactory<Integer> p2SPFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(1,50, p2.sensitivity);
        p1SensBox.setValueFactory(p1SPFactory);
        p2SensBox.setValueFactory(p2SPFactory);

        p1ColourBox.setValue(p1.fillColor);
        p2ColourBox.setValue(p2.fillColor);
    }
}
