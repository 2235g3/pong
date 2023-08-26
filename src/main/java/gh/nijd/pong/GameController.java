package gh.nijd.pong;

import javafx.animation.AnimationTimer;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Line;

import java.util.Optional;

public class GameController {
    @FXML
    Pane gamePane;
    @FXML
    Label pOneScore, pTwoScore;
    @FXML
    Line leftEnd, rightEnd;

    boolean p1up = false, p1down = false, p2up = false, p2down = false, escape = false;
    private static Player p1, p2;

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
            case ESCAPE:
                escape = true;
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
            case ESCAPE:
                escape = false;
                break;
        }
    }

    public void getPlayers(Player[] playerArr) {
        p1 = playerArr[0];
        p2 = playerArr[1];
    }

    public void quitGame() {
        // Make it go to the menu (Idk where an Event will come from, ns if possible)
    }

    public void initialize() { // Messy, needs cleaning
        Alert gamePaused = new Alert(Alert.AlertType.CONFIRMATION);
        gamePaused.setHeaderText("Game Paused");
        gamePaused.setTitle("Game Paused");
        gamePaused.setContentText("");
        gamePaused.getButtonTypes().clear();
        ButtonType quitButton = new ButtonType("Quit");
        ButtonType playButton = new ButtonType("Continue");

        gamePaused.getButtonTypes().addAll(quitButton, playButton);

        AnimationTimer gameLoop = new AnimationTimer() {
            @Override
            public void handle(long l) { // Player movement has to become flexible to window height --- messy, needs cleaning
                if (p1down) {
                    if (p1.yPos > 750 - p1.height - p1.sensitivity) {
                        p1.r.setY(750 - p1.height);
                    }
                    else{
                        p1.yPos += p1.sensitivity;
                        p1.r.setY(p1.yPos);
                    }
                }
                if (p1up) {
                    if (p1.yPos < p1.sensitivity) {
                        p1.r.setY(0);
                    }
                    else {
                        p1.yPos -= p1.sensitivity;
                        p1.r.setY(p1.yPos);
                    }
                }
                if (p2down) {
                    if (p2.yPos > 750 - p2.height - p2.sensitivity) {
                        p2.r.setY(750 - p2.height);
                    }
                    else {
                        p2.yPos += p2.sensitivity;
                        p2.r.setY(p2.yPos);
                    }
                }
                if (p2up) {
                    if (p2.yPos < p2.sensitivity) {
                        p2.r.setY(0);
                    }
                    else {
                        p2.yPos -= p2.sensitivity;
                        p2.r.setY(p2.yPos);
                    }
                }
                if (escape) {
                    stop();
                    final Optional<ButtonType>[] bt = new Optional[1];
                    Platform.runLater(new Runnable() {
                        @Override
                        public void run() {
                            bt[0] = gamePaused.showAndWait();
                            if (bt[0].get().getText().equals("Quit")) {
                                System.exit(0); // Need to make it go to menu screen, but I am too tired, apologies future me (or Nik)
                            }
                            else {
                                start();
                            }
                            escape = false;
                            p1up = false;
                            p1down = false;
                            p2up = false;
                            p2down = false;
                        }
                    });
                }
            }
        };

        p1.initRectangle(gamePane);
        p2.initRectangle(gamePane);

        gameLoop.start();
    }
}
