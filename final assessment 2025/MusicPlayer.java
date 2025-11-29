import javax.sound.sampled.*;
import java.io.File;

public class MusicPlayer {
    private Clip clip;

    public void playBackgroundMusic(String filePath) {
        try {
            AudioInputStream audioStream = AudioSystem.getAudioInputStream(new File("jazzlofi.wav"));
            clip = AudioSystem.getClip();
            clip.open(audioStream);
            clip.loop(Clip.LOOP_CONTINUOUSLY); // repeat forever
            clip.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    
}
