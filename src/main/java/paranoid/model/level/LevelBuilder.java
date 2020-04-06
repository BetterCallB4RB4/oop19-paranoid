package paranoid.model.level;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import javafx.scene.paint.Color;
import paranoid.common.P2d;
import paranoid.common.Pair;
import paranoid.model.entity.Brick;
import paranoid.model.entity.Brick.Builder;
import paranoid.model.entity.PlaceHolder;

public class LevelBuilder {

    private Map<PlaceHolder, Optional<Brick>> underConstruction;
    private String levelName;
    //private List<Brick> currentBricks;
    //private List<Pair<PlaceHolder, Boolean>> gridBrick;
    private int tileX;
    private int tileY;

    public LevelBuilder(final int tileX, final int tileY, final int numberX, final int numberY) {
        this.tileX = tileX;
        this.tileY = tileY;
        this.levelName = "noNameLevel";
        //this.currentBricks = new ArrayList<>();
        //this.gridBrick = new ArrayList<>();
        this.underConstruction = new HashMap<>();
        //qui inizializzo la griglia con i place holder
        int currentXpos = 0;
        for (int i = 0; i < numberX; i++) {
            int currentYpos = 0;
            for (int j = 0; j < numberY; j++) {
                this.underConstruction.put(new PlaceHolder(new P2d(currentXpos, currentYpos), tileY, tileX), Optional.empty());
                //this.gridBrick.add(new Pair<>(new PlaceHolder(new P2d(currentXpos, currentYpos), tileY, tileX), false));
                currentYpos += tileY;
            }
            currentXpos += tileX;
        }
    }

    /**
     * 
     * @param x mouse coordinates
     * @param y mouse coordinates
     * @param color selected
     * @param isDestructibile
     * @param point
     * @param lives
     * @return Pair<Brick, Boolean>
     */
    public Pair<PlaceHolder, Boolean> getTileClicked(final double x, final double y, 
                                               final Color color, final boolean isDestructibile, 
                                               final int point, final int lives) {
        List<PlaceHolder> phList = underConstruction.entrySet().stream()
                                                               .map(i -> i.getKey())
                                                               .collect(Collectors.toList());
        Pair<PlaceHolder, Boolean> result = new Pair<>(new PlaceHolder(new P2d(0, 0), tileY, tileX), false);
        for (PlaceHolder ph : phList) {
            if (x > ph.getPos().getX() && x < ph.getPos().getX() + ph.getWidth() && y > ph.getPos().getY()
                    && y < ph.getPos().getY() + ph.getHeight()) {
                if (underConstruction.get(ph).isPresent()) {
                    underConstruction.replace(ph, Optional.empty());
                    result = new Pair<PlaceHolder, Boolean>(ph, false);
                } else {
                    underConstruction.replace(ph, Optional.of(new Builder().position(new P2d(ph.getPos().getX(), ph.getPos().getY()))
                                                              .height(this.tileY)
                                                              .width(this.tileX)
                                                              .pointEarned(point)
                                                              .color(color)
                                                              .destructible(isDestructibile)
                                                              .energy(lives)
                                                              .build()));
                    result = new Pair<PlaceHolder, Boolean>(ph, true);
                }
            }
        }
        return result;
    }

    /**
     * 
     * @return the built level
     */
    public Level build() {
        List<Brick> level = underConstruction.entrySet().stream()
                                                        .filter(i -> i.getValue().isPresent())
                                                        .map(i -> i.getValue())
                                                        .map(i -> i.get())
                                                        .collect(Collectors.toList());
        return new Level(level, levelName);
    }


    /**
     * 
     * @param levelName 
     * @return use pattern builder
     */
    public LevelBuilder setLevelName(final String levelName) {
        this.levelName = levelName;
        return this;
    }
}
