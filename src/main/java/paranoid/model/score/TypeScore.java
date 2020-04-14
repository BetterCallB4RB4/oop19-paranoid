package paranoid.model.score;

import paranoid.main.ParanoidApp;

public enum TypeScore {

    STORY(ParanoidApp.SCORE_STORY),
    CUSTOM(ParanoidApp.SCORE_CUSTOM);

    private final String path;
    TypeScore(final String path) {
        this.path = path;
    }
    
    public String getPath() {
        return this.path;
    }
}
