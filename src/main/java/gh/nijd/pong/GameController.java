package gh.nijd.pong;

import javafx.animation.AnimationTimer;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Line;

public class GameController {
    @FXML
    Pane gamePane;
    @FXML
    Label pOneScore;
    @FXML
    Label pTwoScore;
    @FXML
    Line leftEnd;
    @FXML
    Line rightEnd;

    public boolean p1up = false, p1down = false, p2up = false, p2down = false;

    @FXML
    public void keyPressed(KeyEvent keyEvent) {
        switch (keyEvent.getCode()) {
            case S:
                p1down = true;
                break;
            case W:
                p1up = true;
                break;
            case DOWN:
                p2down = true;
                break;
            case UP:
                p2up = true;
                break;
        }
    }

    @FXML
    public void keyReleased(KeyEvent keyEvent) {
        switch (keyEvent.getCode()) {
            case S:
                p1down = false;
                break;
            case W:
                p1up = false;
                break;
            case DOWN:
                p2down = false;
                break;
            case UP:
                p2up = false;
                break;
        }
    }

    public void initialize() {
        Player p1 = new Player("Player One", "p1");
        Player p2 = new Player("Player Two", "p2");

        AnimationTimer gameLoop = new AnimationTimer() {
            @Override
            public void handle(long l) {
                if (p1down) {
                    p1.yPos += p1.sensitivity;
                    p1.r.setY(p1.yPos);
                }
                if (p1up) {
                    p1.yPos -= p1.sensitivity;
                    p1.r.setY(p1.yPos);
                }
                if (p2down) {
                    p2.yPos += p2.sensitivity;
                    p2.r.setY(p2.yPos);
                }
                if (p2up) {
                    p2.yPos -= p2.sensitivity;
                    p2.r.setY(p2.yPos);
                }
            }
        };



        p1.initRectangle(gamePane);
        p2.initRectangle(gamePane);

        gameLoop.start();
    }


}
