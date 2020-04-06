package paranoid.model.settings;

import java.io.Serializable;

public final class Settings implements Serializable {

    private static final long serialVersionUID = 7866419986850266123L;
    private Difficulty difficulty;
    private boolean playMusic;
    private boolean playEffects;
    private int playerNumber;
    private String selectedLevel;

    private Settings(final Difficulty difficulty, final boolean playMusic, final boolean playEffect,
                    final int playerNumber, final String selectedLevel) {
        this.difficulty = difficulty;
        this.playEffects = playEffect;
        this.playMusic = playMusic;
        this.playerNumber = playerNumber;
        this.selectedLevel = selectedLevel;
    }

    /**
     * @return the difficulty
     */
    public Difficulty getDifficulty() {
        return difficulty;
    }

    /**
     * @return the playMusic
     */
    public boolean isPlayMusic() {
        return playMusic;
    }

    /**
     * @return the playEffects
     */
    public boolean isPlayEffects() {
        return playEffects;
    }

    /**
     * @return the playerNumber
     */
    public int getPlayerNumber() {
        return playerNumber;
    }

    /**
     * @return the selectedLevel
     */
    public String getSelectedLevel() {
        return selectedLevel;
    }

    public static final class Builder {

        private Difficulty difficulty;
        private boolean playMusic;
        private boolean playEffects;
        private int playerNumber;
        private String selectedLevel;

        public Builder() {
            this.difficulty = Difficulty.NORMAL;
            this.playMusic = false;
            this.playEffects = true;
            this.playerNumber = 1;
            this.selectedLevel = "level1";
        }

        public Builder fromSettings(final Settings settings) {
            this.difficulty = settings.getDifficulty();
            this.playMusic = settings.isPlayMusic();
            this.playEffects = settings.isPlayEffects();
            this.playerNumber = settings.getPlayerNumber();
            this.selectedLevel = settings.getSelectedLevel();
            return this;
        }

        public Builder difficulty(final Difficulty diffculty) {
            this.difficulty = diffculty;
            return this;
        }

        public Builder playMusic(final boolean playMusic) {
            this.playMusic = playMusic;
            return this;
        }

        public Builder playEffect(final boolean playEffect) {
            this.playEffects = playEffect;
            return this;
        }

        public Builder playerNumber(final int playerNumber) {
            this.playerNumber = playerNumber;
            return this;
        }

        public Builder selectLevel(final String selectedLevel) {
            this.selectedLevel = selectedLevel;
            return this;
        }

        public Settings build() {
            if (this.difficulty == null || this.playerNumber <= 0 || this.playerNumber > 2
                    || this.selectedLevel == null) {
                throw new IllegalStateException();
            }
            return new Settings(difficulty, playMusic, playEffects, playerNumber, selectedLevel);
        }
    }

}

