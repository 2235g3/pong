package gh.nijd.pong;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import javax.sound.sampled.*;
import java.io.BufferedInputStream;
import java.io.File;

public class Console {
    private Scene newScene;
    private Parent root;

    public void selectNewScene(String fileName, Stage stage, boolean fullscreen) {
        try {
            root = FXMLLoader.load(getClass().getResource(fileName)); // Gets the new FXML scene
            newScene = new Scene(root); // Makes the FXML into a scene
            newScene.getRoot().requestFocus(); // Requests focus so the container can send updates and get focus
            stage.setScene(newScene); // Sets the new scene
            stage.setFullScreen(fullscreen); // Sets the screen to the selected screen size
            stage.show(); // Displays the new scene
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void playMusic(String audioName) { // https://www.baeldung.com/java-play-sound
        try {
            boolean clipClosed = false;
            AudioInputStream AISone = AudioSystem.getAudioInputStream(new File(System.getProperty("user.dir") + "/src/main/resources/gh/nijd/pong/sfx/game.wav"));
            BufferedInputStream BIS = new BufferedInputStream(AISone);
            AudioInputStream AIStwo = new AudioInputStream(BIS, AISone.getFormat(), AISone.getFrameLength());
            AudioFormat AF = AIStwo.getFormat();
            DataLine.Info audioInfo = new DataLine.Info(Clip.class, AF);
            Line audioLine = AudioSystem.getLine(audioInfo);
            Clip audioClip = (Clip) audioLine;
            audioClip.addLineListener(new sfxListener());
            audioClip.open(AIStwo);
            audioClip.start();
            AIStwo.close();
            AISone.close();
            BIS.close();
            while (audioClip.isActive()) {
                while (!audioClip.isRunning() && clipClosed) {
                    audioClip.close();
                    clipClosed = true;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
