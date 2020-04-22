package paranoid.controller.event;

import java.util.LinkedList;
import java.util.Queue;

import paranoid.common.Collision;
import paranoid.controller.gameloop.GamePhase;
import paranoid.controller.gameloop.GameState;
import paranoid.model.entity.Brick;
import paranoid.model.level.MusicPlayer;

public class EventConsumer {

    private final Queue<Event> events = new LinkedList<>();
    private final GameState gameState;
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
                final HitBorderEvent hit = (HitBorderEvent) ev;
                gameState.flatMultiplier();
                if (hit.getCollision().equals(Collision.BOTTOM)) {
                    gameState.getWorld().removeBall(hit.getBall());
                    if (gameState.getWorld().getBalls().isEmpty()) {
                        gameState.decLives();
                        gameState.setPhase(GamePhase.INIT);
                    }
                }
                this.player.playEffect(Effect.BOARD_COLLISION);
            } else if (ev instanceof HitBrickEvent) {
                final Brick brick = ((HitBrickEvent) ev).getBrick();
                gameState.incMultiplier();
                gameState.addPoint(brick.getPointEarned());
                brick.decEnergy();
                if (brick.getEnergy() == 0 && !brick.isIndestructible()) {
                    gameState.getWorld().removeBrick(brick);
                }
                this.player.playEffect(Effect.BRICK_COLLISION);
            } else if (ev instanceof HitPlayerEvent) {
                this.player.playEffect(Effect.PLAYER_COLLISION);
            }
        });
        isOver();
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
     * check if the game can continue.
     */
    private void isOver() {
        if (gameState.getLives() == 0) {
            gameState.setPhase(GamePhase.LOST);
        } else if (gameState.getWorld().getBricks().stream()
                                                   .filter(i -> !i.isIndestructible())
                                                   .count() == 0) {
            gameState.addBonus();
            gameState.setPhase(GamePhase.WIN);
        }
    }

    /**
     * set a player to play sound effects when specific events occur.
     * @param player 
     */
    public void addMusicPlayer(final MusicPlayer player) {
        this.player = player;
    }
}
