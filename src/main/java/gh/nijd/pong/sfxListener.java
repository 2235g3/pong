package gh.nijd.pong;

import javax.sound.sampled.LineEvent;
import javax.sound.sampled.LineListener;

public class sfxListener implements LineListener { // https://www.baeldung.com/java-play-sound
    boolean sfxFinished;

    @Override
    public void update(LineEvent event) {
        if (LineEvent.Type.STOP == event.getType()) {
            sfxFinished = true;
        }
    }
}
