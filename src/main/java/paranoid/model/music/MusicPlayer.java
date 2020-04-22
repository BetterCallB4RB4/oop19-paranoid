package paranoid.model.music;

import paranoid.controller.event.Effect;
import paranoid.model.level.Music;

public interface MusicPlayer {

    /**
     * reproduces the music by automatically releasing 
     * resources as soon as the end audio event is generated.
     * @param music to play
     */
    void playMusic(Music music);

    /**
     * stop the current music.
     * and generate the end music event
     */
    void stopMusic();

    /**
     * reproduces the effect by automatically releasing 
     * resources as soon as the end audio event is generated.
     * @param effect to play
     */
    void playEffect(Effect effect);

    /**
     * @param isMusicEnable enable music to play
     */
    void setMusicEnable(boolean isMusicEnable);

    /**
     * @param isEffectEnable enable effect to play
     */
    void setEffectEnable(boolean isEffectEnable);

}
