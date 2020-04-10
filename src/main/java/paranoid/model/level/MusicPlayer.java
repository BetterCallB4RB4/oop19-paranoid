package paranoid.model.level;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class MusicPlayer {

    private Clip clip;

    /**
     * 
     * @param music to play
     */
    public void playMusic(final Music music) {
        try {
            AudioInputStream audioIn = AudioSystem.getAudioInputStream(ClassLoader.getSystemResourceAsStream(music.getLocation()));
            this.clip = AudioSystem.getClip();
            this.clip.open(audioIn);
            this.clip.start();
            this.clip.loop(Clip.LOOP_CONTINUOUSLY);
        } catch (Exception  e) {
            e.printStackTrace();
        }
    }

    /**
     * stop the current music.
     */
    public void stopMusic() {
        this.clip.stop();
    }

    /**
     * 
     * @param effect to play
     */
    public void playEffect(final Effect effect) {
        try {
            AudioInputStream audioIn = AudioSystem.getAudioInputStream(ClassLoader.getSystemResourceAsStream(effect.getLocation()));
            this.clip = AudioSystem.getClip();
            this.clip.open(audioIn);
            this.clip.start();
        } catch (Exception  e) {
            e.printStackTrace();
        }
    }
}
