package gh.nijd.pong;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;

public class GameController {
    @FXML
    Pane gamePane;
    @FXML
    Label pOneScore;
    @FXML
    Label pTwoScore;

    public void initialize() {
        Player p1 = new Player("Player One", "p1");
        Player p2 = new Player("Player Two", "p2");
        p1.initRectangle(gamePane);
        p2.initRectangle(gamePane);
    }
}
