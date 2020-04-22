package paranoid.model.music;

import java.io.IOException;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;
import javax.sound.sampled.LineEvent;
import javax.sound.sampled.LineListener;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.sound.sampled.LineEvent.Type;

import paranoid.controller.event.Effect;
import paranoid.model.level.Music;

public class MusicPlayerImpl implements MusicPlayer {

    private Clip clip;
    private boolean isMusicEnable;
    private boolean isEffectEnable;
    private static float volumeReduction = 10.0f;

    /**
     * {@inheritDoc}
     */
    @Override
    public void playMusic(final Music music) {
        if (isMusicEnable) {
            try (AudioInputStream audioIn = AudioSystem.getAudioInputStream(music.getLocation())) {
                this.clip = AudioSystem.getClip();
                this.clip.open(audioIn);
                final FloatControl gainControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
                gainControl.setValue(volumeReduction);
                this.clip.start();
                clip.addLineListener(new LineListener() {
                    @Override
                    public void update(final LineEvent event) {
                        if (event.getType().equals(Type.STOP)) {
                            clip.close();
                        }
                    }
                });
                this.clip.loop(Clip.LOOP_CONTINUOUSLY);
            } catch (IOException e1) {
                e1.printStackTrace();
            } catch (UnsupportedAudioFileException e2) {
                e2.printStackTrace();
            } catch (LineUnavailableException e3) {
                e3.printStackTrace();
            }
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void stopMusic() {
        if (this.isMusicEnable) {
            this.clip.stop();
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @SuppressWarnings("PMD.CloseResource")
    public void playEffect(final Effect effect) {
        if (isEffectEnable) {
            try (AudioInputStream audioIn = AudioSystem.getAudioInputStream(effect.getLocation())) {
                final Clip effectClip = AudioSystem.getClip();
                effectClip.open(audioIn);
                effectClip.start();
                effectClip.addLineListener(new LineListener() {
                    @Override
                    public void update(final LineEvent event) {
                        if (event.getType().equals(Type.STOP)) {
                            effectClip.close();
                        }
                    }
                });
            } catch (IOException e1) {
                e1.printStackTrace();
            } catch (UnsupportedAudioFileException e2) {
                e2.printStackTrace();
            } catch (LineUnavailableException e3) {
                e3.printStackTrace();
            }
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setMusicEnable(final boolean isMusicEnable) {
        this.isMusicEnable = isMusicEnable;
    }


    /**
     * {@inheritDoc}
     */
    @Override
    public void setEffectEnable(final boolean isEffectEnable) {
        this.isEffectEnable = isEffectEnable;
    }
}
