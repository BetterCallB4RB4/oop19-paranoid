package paranoid.model.level;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import javafx.scene.paint.Color;
import paranoid.common.P2d;
import paranoid.common.Pair;
import paranoid.common.dimension.ScreenConstant;
import paranoid.model.entity.Brick;
import paranoid.model.entity.Brick.Builder;
import paranoid.model.entity.PlaceHolder;

public class LevelBuilder {

    //questa mi serve per capire quanle mattoncino ho cliccato nel canvasa 
    //restituaentdomi la coordinata del mattoncino
    private final Map<PlaceHolder, Pair<Integer, Integer>> builderCanvas = new HashMap<>();

    //questo mi permette di andare a vedere se ho già cliccato il mattoncino
    //se non è presente costruisco il mattoncino con le caratteristiche del place holder
    //e dell form presenti nell'interfaccia grafica
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
        //costrusco il riferimento alla griglia del builder
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

        //costruisco il riferimento alla griglia del gioco
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
     * prima confronto le coordinate x, y del click con la griglia contenente i place holder
     * costruiti sulle dimensioni della grandezza attuale dello schermo restituendo il numero del mattoncino
     * colpito mediante le coordinate
     * poi controlla nella griglia contenete i place holder costruiti sulla dimensione del mondo quale 
     * coordinata è stata colpit
     * controlla se il giocatore ha già selezionato quel mattoncino 
     * se non lo aveva selezionato richiamo il brick builder e costruisco il mattoncino con 
     * gli input delle form e le dimensioni del placeholder costruito sulla grandezza del mondo
     * visto che tecnicamente la tabella hash accede alla informazioni in maniera costante 
     * il ciclo for fa pesare il costo computazione teta n.
     * @param x mouse coordinates
     * @param y mouse coordinates
     * @param color selected
     * @param isIndestructibile
     * @param point
     * @param lives
     * @return Pair<Brick, Boolean>
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
                                         .destructible(isIndestructibile)
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
        final List<Brick> level = this.gameCanvas.entrySet().stream()
                                                            .map(i -> i.getValue().getY())
                                                            .filter(i -> i.isPresent())
                                                            .map(i -> i.get())
                                                            .collect(Collectors.toList());
        return new Level(level, levelName, song, backGround);
    }

    /**
     * @param levelName the levelName to set
     */
    public void setLevelName(final String levelName) {
        this.levelName = levelName;
    }

    /**
     * @param song the song to set
     */
    public void setSong(final String song) {
        this.song = Music.getMusicByName(song);
    }

    /**
     * @param backGround the backGround to set
     */
    public void setBackGround(final String backGround) {
        this.backGround = BackGround.getBackGroundByName(backGround);
    }

}
