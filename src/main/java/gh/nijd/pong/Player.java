package gh.nijd.pong;

import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Player {
    public String name;
    public String playerType;
    public Rectangle r;
    public int points = 0;

    int xPos = 20;
    int yPos = 0;
    final Color fillColor = Color.WHITE;
    final int width = 50;
    final int height = 150;

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
        if (playerType.equals("p2")) {
            xPos = 1480 - width;
        }

        yPos = (750 / 2) - (height / 2);

        r = new Rectangle(xPos, yPos, width, height);
        r.setFill(fillColor);
        gamePane.getChildren().add(r);
    }
}
