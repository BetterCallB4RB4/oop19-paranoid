//package paranoid.test;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.junit.jupiter.api.Assertions.assertFalse;
//import static org.junit.jupiter.api.Assertions.assertNotEquals;
//import static org.junit.jupiter.api.Assertions.assertThrows;
//import static org.junit.jupiter.api.Assertions.assertTrue;
//
//import java.util.HashMap;
//import java.util.Map;
//import java.util.Set;
//
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//
//import paranoid.common.Collision;
//import paranoid.common.P2d;
//import paranoid.common.PlayerId;
//import paranoid.controller.gameloop.GameState;
//import paranoid.model.component.input.InputController;
//import paranoid.model.component.input.KeyboardInputController;
//import paranoid.model.entity.Border;
//import paranoid.model.entity.Player;
//import paranoid.model.entity.StartPhase;
//import paranoid.model.entity.World;
//
///**
// * TestPlayer. Test the creation of the player, the collision and the movement.
// */
//public class TestPlayer {
//    private World world;
//    private Player e1;
//    private Player e2;
//    private Player e3;
//    private Player e4;
//    private final Map<PlayerId, InputController> inputController = new HashMap<>();
//
//    /**
//     * Initialize fields before the test start.
//     */
//    @BeforeEach
//    public void createEntity() {
//        final GameState gameState = new GameState();
//        this.world = new World(new Border(600, 600), gameState);
//        this.e1 = createPlayer(StartPhase.PLAYER_ONE.getSpawnPoint(), PlayerId.ONE);
//        this.e2 = createPlayer(new P2d(600, 580), PlayerId.ONE);
//        this.e3 = createPlayer(new P2d(-10, 580), PlayerId.ONE);
//        this.e4 = createPlayer(StartPhase.PLAYER_TWO.getSpawnPoint(), PlayerId.TWO);
//        final Set<Player> playerContainer = Set.of(e1, e2, e3, e4);
//        this.world.setPlayers(playerContainer);
//        this.inputController.put(PlayerId.ONE, new KeyboardInputController());
//        this.inputController.put(PlayerId.TWO, new KeyboardInputController());
//
//    }
//
//    /**
//     * Test if the builder create correctly the players.
//     */
//    @Test
//    public void testPlayerCreation() {
//        assertEquals(new P2d(290, 580), e1.getPos());
//        assertEquals(new P2d(190, 580), e4.getPos());
//        assertEquals(78, e1.getWidth());
//        assertEquals(20, e1.getHeight());
//        assertEquals(PlayerId.ONE, e1.getPlayerId());
//        assertNotEquals(PlayerId.ONE, e4.getPlayerId());
//        assertThrows(IllegalStateException.class, () -> new Player.Builder().build());
//        assertThrows(IllegalStateException.class, () -> new Player.Builder()
//                                                                .position(null).build());
//        assertThrows(IllegalStateException.class, () -> new Player.Builder()
//                                                               .width(-1).build());
//        assertThrows(IllegalStateException.class, () -> new Player.Builder()
//                                                               .height(0).build());
//    }
//
//    /**
//     * Test if the collision with boundaries are correctly checked.
//     */
//    @Test
//    public void testCollisionWithBoundaries() {
//        assertFalse(world.checkCollisionWithBoundaries(e1).isPresent());
//        assertTrue(world.checkCollisionWithBoundaries(e2).isPresent());
//        assertTrue(world.checkCollisionWithBoundaries(e3).isPresent());
//        assertEquals(Collision.LEFT, world.checkCollisionWithBoundaries(e3).get());
//        assertEquals(Collision.RIGHT, world.checkCollisionWithBoundaries(e2).get());
//    }
//
//    /**
//     * Test if the inputController notify correctly the movement of the player with
//     * different player id and if it moves depending on speed of player and delta time
//     * given by the gameLoop.
//     */
//    @Test
//    public void testPlayerMovement() {
//        assertEquals((new P2d(290, 580)), e1.getPos());
//        assertEquals((new P2d(190, 580)), e4.getPos());
//        inputController.get(PlayerId.ONE).notifyMoveRight(true);
//        inputController.get(PlayerId.TWO).notifyMoveLeft(true);
//        inputController.entrySet().forEach(i -> {
//            world.movePlayer(i.getKey(), i.getValue());
//        });
//        world.updateState(20);
//        assertEquals((new P2d(299,580)), e1.getPos());
//        assertEquals((new P2d(181,580)), e4.getPos());
//    }
//
//    private Player createPlayer(final P2d pos, final PlayerId playerId) {
//        return new Player.Builder()
//                         .position(pos)
//                         .width(78)
//                         .height(20)
//                         .playerId(playerId)
//                         .build();
//
//    }
//}
