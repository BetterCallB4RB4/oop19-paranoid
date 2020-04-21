/*
package paranoid.test;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import paranoid.common.Collision;
import paranoid.common.P2d;
import paranoid.common.PlayerId;
import paranoid.controller.gameLoop.GameState;
import paranoid.model.entity.Border;
import paranoid.model.entity.Player;
import paranoid.model.entity.World;

public class TestPlayerCollision {
    private World world;
    private Player e1;
    private Player e2;
    private Player e3;

    @BeforeEach
    public void createEntity() {
        final GameState gameState = new GameState();
        this.world = new World(new Border(600, 600), gameState);
        this.e1 = createPlayer(new P2d(290, 580));
        this.e2 = createPlayer(new P2d(600, 580));
        this.e3 = createPlayer(new P2d(-10, 580));

    }
    @Test
    public void testCollisionWithBoundaries() {
        assertFalse(world.checkCollisionWithBoundaries(e1).isPresent());
        assertTrue(world.checkCollisionWithBoundaries(e2).isPresent());
        assertTrue(world.checkCollisionWithBoundaries(e3).isPresent());
        assertEquals(Collision.LEFT, world.checkCollisionWithBoundaries(e3).get());
        assertEquals(Collision.RIGHT, world.checkCollisionWithBoundaries(e2).get());
    }

    private Player createPlayer(final P2d pos) {
        return new Player.Builder()
                         .position(pos)
                         .width(78)
                         .height(20)
                         .playerId(PlayerId.ONE)
                         .build();

    }
}
*/