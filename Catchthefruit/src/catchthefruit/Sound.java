package catchthefruit;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.IOException;

public class Sound {
    private Clip clip;

    public void play(String soundFile) {
        new Thread(() -> {
            try {
                if (clip != null && clip.isRunning()) {
                    clip.stop();
                }
                clip = AudioSystem.getClip();
                AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(getClass().getResource(soundFile));
                clip.open(audioInputStream);
                clip.setFramePosition(0);
                clip.start();
            } catch (IOException | UnsupportedAudioFileException | LineUnavailableException e) {
                e.printStackTrace();
            }
        }).start();
    }
}
