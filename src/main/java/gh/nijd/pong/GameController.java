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
import javafx.stage.Stage;

import java.util.Optional;

public class GameController {
    @FXML
    Pane gamePane;
    @FXML
    Label pOneScore, pTwoScore;
    @FXML
    Line leftEnd, rightEnd;

    boolean p1up = false, p1down = false, p2up = false, p2down = false, pause = false;
    private static Player p1, p2;

    @FXML
    public void keyPressed(KeyEvent keyEvent) {
        switch (keyEvent.getCode()) { // When a key is pressed, its corresponding bool var becomes true, indicating it has been pressed, to be dealt with in the game loop
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
            case P:
                pause = true;
                break;
        }
    }

    @FXML
    public void keyReleased(KeyEvent keyEvent) {
        switch (keyEvent.getCode()) { // When a key is released, its corresponding bool var becomes false, indicating it is no longer pressed
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
            case P:
                pause = false;
                break;
        }
    }

    public void getPlayers(Player[] playerArr) {
        p1 = playerArr[0];
        p2 = playerArr[1];
    }

    public Alert gamePausedAlert() {
        Alert gamePaused = new Alert(Alert.AlertType.CONFIRMATION);
        gamePaused.setHeaderText("Game Paused");
        gamePaused.setTitle("Game Paused");
        gamePaused.setContentText("");
        gamePaused.getButtonTypes().clear(); // Clears current buttons on alert

        // Creates new button options for the alert
        ButtonType quitButton = new ButtonType("Quit");
        ButtonType playButton = new ButtonType("Continue");

        // Adds button options to the alert
        gamePaused.getButtonTypes().addAll(quitButton, playButton);
        return gamePaused;
    }

    public void playerMovement(boolean keyOne, boolean keyTwo, Player currPlayer) {
        if (keyOne) {
            if (currPlayer.yPos > currPlayer.windowHeight - currPlayer.rHeight - currPlayer.sensitivity) { // If the player's sensitivity will take them below the screen it will set the player to the lowest they can go, no matter their sensitivity they will always end at the same yPos
                currPlayer.r.setY(currPlayer.windowHeight - currPlayer.rHeight);
            }
            else{ // Moves the player down by their sensitivity
                currPlayer.yPos += currPlayer.sensitivity;
                currPlayer.r.setY(currPlayer.yPos);
            }
        }
        if (keyTwo) {
            if (currPlayer.yPos < currPlayer.sensitivity) { // Same as above, except for going up
                currPlayer.r.setY(0);
            }
            else {
                currPlayer.yPos -= currPlayer.sensitivity;
                currPlayer.r.setY(currPlayer.yPos);
            }
        }
    }

    public void initialize() {
        Alert gamePaused = gamePausedAlert(); // Creates alert to be used when the game is paused

        // --- Inspiration from https://stackoverflow.com/questions/13796595/return-result-from-javafx-platform-runlater and https://stackoverflow.com/questions/29962395/how-to-write-a-keylistener-for-javafx ---
        AnimationTimer gameLoop = new AnimationTimer() { // Creates the game loop
            @Override
            public void handle(long l) {
                playerMovement(p1down, p1up, p1);
                playerMovement(p2down, p2up, p2);

                if (pause) {
                    stop(); // When escape is pressed the game loop stops
                    final Optional<ButtonType>[] bt = new Optional[1]; // Inits arr to store result from alert
                    Platform.runLater(new Runnable() { // Using Alert.showAndWait() causes a runtime error due to the current loop being performed so the action must be performed after the current loop is finished
                        @Override
                        public void run() {
                            bt[0] = gamePaused.showAndWait();
                            if (bt[0].get().getText().equals("Quit")) { // If the player quits, they get taken to the menu page
                                new Console().selectNewScene("menu.fxml", (Stage) gamePane.getScene().getWindow(), ((Stage) gamePane.getScene().getWindow()).isFullScreen());
                            }
                            else {
                                start(); // If they want to continue, the game loop starts
                            }
                            pause = p1up = p1down = p2up = p2down = false; // These bool vars need to be reset otherwise the player will move constantly until user input after the game restarts
                        }
                    });
                }
            }
        };

        // Inits player rectangles
        p1.initRectangle(gamePane);
        p2.initRectangle(gamePane);

        gameLoop.start(); // Starts the gameloop for the first time
    }
}
