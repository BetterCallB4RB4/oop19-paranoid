package paranoid.controller.event;

import java.util.ArrayList;
import java.util.List;

import paranoid.common.Collision;
import paranoid.controller.gameLoop.GamePhase;
import paranoid.controller.gameLoop.GameState;
import paranoid.model.entity.Ball;
import paranoid.model.entity.Brick;
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
                gameState.setMultiplier(0);
                HitBorderEvent hit = (HitBorderEvent) ev;
                if (hit.getCollision().equals(Collision.BOTTOM)) {
                    gameState.getWorld().removeBall(hit.getBall());
                    if (gameState.getWorld().getBalls().isEmpty()) {
                        gameState.setLives(gameState.getLives() - 1);
                        gameState.setPhase(GamePhase.INIT);
                    }
                }
                this.player.playEffect(Effect.BOARD_COLLISION);
            } else if (ev instanceof HitBrickEvent) {
                gameState.setMultiplier(gameState.getMultiplier() + 1);
                Brick brick = ((HitBrickEvent) ev).getBrick();
                gameState.setPlayerScore(gameState.getPlayerScore() 
                        + (brick.getPointEarned() * gameState.getMultiplier()));
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


    private void isOver() {
        if (gameState.getLives() == 0) {
            gameState.setPhase(GamePhase.LOST);
        } else if (gameState.getWorld().getBricks().stream()
            .filter(i -> !i.isIndestructible()).count() == 0) {

            switch (gameState.getDifficulty()) {
            case EASY:
                gameState.setPlayerScore(gameState.getPlayerScore() + 5000);
                break;
            case NORMAL:
                gameState.setPlayerScore(gameState.getPlayerScore() + 10_000);
                break;
            case HARD:
                gameState.setPlayerScore(gameState.getPlayerScore() + 15_000);
            default:
                break;
            }
            gameState.setPhase(GamePhase.WIN);
           }
    }

    /**
     * 
     * @param player effect
     */
    public void addMusicPlayer(final MusicPlayer player) {
        this.player = player;
    }
}
