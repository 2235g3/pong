package gh.nijd.pong;

import javafx.animation.AnimationTimer;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
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

    public boolean up = false, down = false;

    public void initialize() {
        Player p1 = new Player("Player One", "p1");
        Player p2 = new Player("Player Two", "p2");

        EventHandler<KeyEvent> pressedEventHandler = new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent keyEvent) {
                switch (keyEvent.getCode()) {
                    case S:
                        System.out.println("S Pressed");
                        down = true;
                        break;
                    case W:
                        System.out.println("Test");
                        up = true;
                        break;
                }
            }
        };

        EventHandler<KeyEvent> releasedEventHandler = new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent keyEvent) {
                switch (keyEvent.getCode()) {
                    case S:
                        System.out.println("S Released");
                        down = false;
                        break;
                    case W:
                        System.out.println("W Released");
                        up = false;
                        break;
                }
            }
        };

        AnimationTimer gameLoop = new AnimationTimer() {
            @Override
            public void handle(long l) {
                if (down) {
                    p1.yPos += 15;
                    p1.r.setY(p1.yPos);
                }
                else if (up) {
                    p1.yPos -= 15;
                    p1.r.setY(p1.yPos);
                }
            }
        };



        p1.initRectangle(gamePane);
        p2.initRectangle(gamePane);

        //gamePane.getScene().setOnKeyPressed(pressedEventHandler);
        //gamePane.getScene().setOnKeyReleased(releasedEventHandler);
        gameLoop.start();
    }
}
