package gh.nijd.pong;

import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Player {
    public String name, playerType;
    public Rectangle r;
    public int points = 0, sensitivity = 10;
    public double xPos = 20.0, yPos = 0.0, rWidth = 50.0, rHeight = 150.0, windowHeight, windowWidth;
    public Color fillColour = Color.WHITE;
    private final Color borderColour = Color.WHITE;

    public Player(String name, String playerType) {
        this.name = name;
        this.playerType = playerType;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void increasePoints() {
        points++;
    }

    public void initRectangle(Pane gamePane) {
        rectangleDimensions();
        r = new Rectangle(xPos, yPos, rWidth, rHeight);
        r.setFill(fillColour);
        r.strokeProperty().set(borderColour);
        gamePane.getChildren().add(r);
    }

    private void rectangleDimensions() {
        if (playerType.equals("p2")) {
            xPos = windowWidth - rWidth;
        }

        yPos = (windowHeight / 2) - (rHeight / 2);
    }
}
