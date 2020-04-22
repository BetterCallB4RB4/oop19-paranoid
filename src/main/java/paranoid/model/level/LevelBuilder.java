package paranoid.model.level;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import javafx.scene.paint.Color;
import paranoid.common.P2d;
import paranoid.common.Pair;
import paranoid.common.ScreenConstant;
import paranoid.model.entity.Brick;
import paranoid.model.entity.Brick.Builder;
import paranoid.model.entity.PlaceHolder;

public class LevelBuilder {

    //build the map between bricks in the world and coordinates
    private final Map<PlaceHolder, Pair<Integer, Integer>> builderCanvas = new HashMap<>();
    //build the map between bricks in the show in the canvas and coordinates
    private final Map<Pair<Integer, Integer>, Pair<PlaceHolder, Optional<Brick>>> gameCanvas = new HashMap<>();
    private final int builderBrickDimensionY = (int) (ScreenConstant.CANVAS_HEIGHT / ScreenConstant.BRICK_NUMBER_Y);
    private final int builderBrickDimensionX = (int) (ScreenConstant.CANVAS_WIDTH / ScreenConstant.BRICK_NUMBER_X);
    private final int gameBrickDimensionY = (int) (ScreenConstant.WORLD_HEIGHT / ScreenConstant.BRICK_NUMBER_Y);
    private final int gameBrickDimensionX = (int) (ScreenConstant.WORLD_WIDTH / ScreenConstant.BRICK_NUMBER_X);

    private String levelName;
    private Music song;
    private BackGround backGround;

    public LevelBuilder() {
        int currentXpos = 0;
        for (int i = 0; i < ScreenConstant.BRICK_NUMBER_X; i++) {
            int currentYpos = 0;
            for (int j = 0; j < ScreenConstant.BRICK_NUMBER_Y; j++) {
                final Pair<Integer, Integer> coordinates = new Pair<>(i, j);
                final PlaceHolder ph = new PlaceHolder(new P2d(currentXpos, currentYpos), builderBrickDimensionY, builderBrickDimensionX);
                this.builderCanvas.put(ph, coordinates);
                currentYpos += builderBrickDimensionY;
            }
            currentXpos += builderBrickDimensionX;
        }
        currentXpos = 0;
        for (int i = 0; i < ScreenConstant.BRICK_NUMBER_X; i++) {
            int currentYpos = 0;
            for (int j = 0; j < ScreenConstant.BRICK_NUMBER_Y; j++) {
                final Pair<Integer, Integer> coordinates = new Pair<>(i, j);
                final PlaceHolder ph = new PlaceHolder(new P2d(currentXpos, currentYpos), gameBrickDimensionY, gameBrickDimensionX);
                this.gameCanvas.put(coordinates, new Pair<>(ph, Optional.empty()));
                currentYpos += gameBrickDimensionY;
            }
            currentXpos += gameBrickDimensionX;
        }
    }

    /**
     * first compare the x, y coordinates of the click with the grid containing 
     * the place holders built on the dimensions of the current size of the screen 
     * by returning the number of the brick hit 
     * check in the grid containing the place holders built on the size of the world 
     * which coordinate was hit. 
     * checks if the player has already selected that brick 
     * if he had not selected I call the brick builder and build the brick with the form inputs 
     * and the dimensions of the placeholder built on the size of the world 
     * @param x mouse coordinates mouse x coordinate
     * @param y mouse coordinates mouse y coordinate
     * @param color color selected
     * @param isIndestructibile if the brick is indestructibile
     * @param point point earned
     * @param lives lives remaining
     * @return current game grid state
     */
    public Pair<PlaceHolder, Boolean> hit(final double x, final double y, 
                                          final Color color, final boolean isIndestructibile, 
                                          final int point, final int lives) {
        Pair<PlaceHolder, Boolean> res = new Pair<>(new PlaceHolder(new P2d(0, 0), 0, 0), false);
        for (final PlaceHolder ph : this.builderCanvas.keySet()) {
            if (x > ph.getPos().getX() && x < ph.getPos().getX() + ph.getWidth() && y > ph.getPos().getY()
                    && y < ph.getPos().getY() + ph.getHeight()) {
                final Pair<Integer, Integer> hit = this.builderCanvas.get(ph);
                if (this.gameCanvas.get(hit).getY().isPresent()) {
                    this.gameCanvas.replace(hit, new Pair<>(this.gameCanvas.get(hit).getX(), Optional.empty()));
                    res = new Pair<>(ph, false);
                } else {
                    final Builder builder = new Builder();
                    final PlaceHolder gamePlaceHolder = this.gameCanvas.get(hit).getX();
                    final Brick brick = builder.position(new P2d(gamePlaceHolder.getPos().getX(), gamePlaceHolder.getPos().getY()))
                                               .height(this.gameCanvas.get(hit).getX().getHeight())
                                               .width(this.gameCanvas.get(hit).getX().getWidth())
                                               .pointEarned(point)
                                               .color(color)
                                               .indestructible(isIndestructibile)
                                               .energy(lives)
                                               .build();
                    this.gameCanvas.replace(hit, new Pair<>(this.gameCanvas.get(hit).getX(), Optional.of(brick)));
                    res = new Pair<>(ph, true);
                }
            }
        }
        return res;
    }

    /**
     * delate all the brick.
     */
    public void delateAll() {
        for (final var elem : this.gameCanvas.keySet()) {
            this.gameCanvas.replace(elem, new Pair<>(this.gameCanvas.get(elem).getX(), Optional.empty()));
        }
    }

    /**
     * 
     * @return the built level
     */
    public Level build() {
        final Set<Brick> level = this.gameCanvas.entrySet().stream()
                                                            .map(i -> i.getValue().getY())
                                                            .filter(i -> i.isPresent())
                                                            .map(i -> i.get())
                                                            .collect(Collectors.toSet());
        return new Level(level, levelName, song, backGround);
    }

    /**
     * @param levelName the name to set
     */
    public void setLevelName(final String levelName) {
        this.levelName = levelName;
    }

    /**
     * @param song to set
     */
    public void setSong(final String song) {
        this.song = Music.getMusicByName(song);
    }

    /**
     * @param backGround to set
     */
    public void setBackGround(final String backGround) {
        this.backGround = BackGround.getBackGroundByName(backGround);
    }

}
