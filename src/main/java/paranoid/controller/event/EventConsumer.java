package paranoid.controller.event;

import java.util.ArrayList;
import java.util.List;

import paranoid.controller.gameLoop.GameState;
import paranoid.model.level.Effect;
import paranoid.model.level.MusicPlayer;

public class EventConsumer {

    private List<Event> events = new ArrayList<>();
    private GameState gameState;
    private MusicPlayer player;

    public EventConsumer(final GameState gameState) {
        this.gameState = gameState;
    }
    /**
     * each event will have specific behavior based on its instance.
     */
    public void resolveEvent() {
        events.stream().forEach(ev -> {
            if (ev instanceof HitBorderEvent) {
                this.player.playEffect(Effect.BOARD_COLLISION);
            } else if (ev instanceof HitBrickEvent) {
                HitBrickEvent hit = (HitBrickEvent) ev;
                gameState.setScore(gameState.getScore() 
                        + (hit.getBrick().getPointEarned() * gameState.getMultiplier()));
                hit.getBrick().decEnergy();
                this.player.playEffect(Effect.BRICK_COLLISION);
            }
        });
        events.clear();
    }

    /**
     * 
     * @param event to add to event queue
     */
    public void addEvent(final Event event) {
        this.events.add(event);
    }

    /**
     * 
     * @param player effect
     */
    public void addMusicPlayer(final MusicPlayer player) {
        this.player = player;
    }
}
