//package paranoid.test;
//
//import static org.junit.Assert.assertTrue;
//import static org.junit.jupiter.api.Assertions.assertEquals;
//
//
//import java.util.Arrays;
//import java.util.Optional;
//import java.util.stream.Collectors;
//import java.util.stream.IntStream;
//
//import org.junit.jupiter.api.Test;
//import javafx.scene.paint.Color;
//import paranoid.common.P2d;
//import paranoid.common.Pair;
//import paranoid.common.PlayerId;
//import paranoid.common.StartPhase;
//import paranoid.model.collision.Collision;
//import paranoid.model.collision.Direction;
//import paranoid.model.entity.Ball;
//import paranoid.model.entity.Border;
//import paranoid.model.entity.Brick;
//import paranoid.model.entity.Player;
//import paranoid.model.entity.World;
//import paranoid.model.entity.WorldImpl;
//import paranoid.model.settings.Difficulty;
//
//public class WorldTest {
//
//    private final World world = new WorldImpl(new Border(200, 200), null);
//    private final Brick brick = new Brick.Builder().position(new P2d(50, 50))
//                                             .height(10)
//                                             .width(10)
//                                             .pointEarned(0)
//                                             .color(Color.BLACK)
//                                             .indestructible(true)
//                                             .energy(1)
//                                             .build();
//    private final Ball ball = new Ball.Builder().position(new P2d(50, 50))
//                                          .direction(Direction.EDGE_LEFT.getVector().mul(-1))
//                                          .height(StartPhase.BALL.getInitHeight())
//                                          .width(StartPhase.BALL.getInitWidth())
//                                          .speed(Difficulty.EASY.getSpeed())
//                                          .build();
//    private final Player player = new Player.Builder()
//                                      .position(StartPhase.PLAYER_ONE.getSpawnPoint())
//                                      .width(StartPhase.PLAYER_ONE.getInitWidth())
//                                      .height(StartPhase.PLAYER_ONE.getInitHeight())
//                                      .playerId(PlayerId.ONE)
//                                      .build();
//
//    /**
//     * check that by inserting a number of ball in the world 
//     * they have been inserted correctly.
//     */
//    @Test
//    public void insertBallTest() {
//        final World world = new WorldImpl(new Border(600, 600), null);
//        final Ball.Builder ballBuilder = new Ball.Builder();
//        ballBuilder.position(StartPhase.BALL.getSpawnPoint())
//                   .direction(Direction.EDGE_LEFT.getVector().mul(-1))
//                   .height(StartPhase.BALL.getInitHeight())
//                   .width(StartPhase.BALL.getInitWidth())
//                   .speed(Difficulty.HARD.getSpeed())
//                   .build();
//        assertTrue(world.getSceneEntities().isEmpty());
//        world.setBalls(IntStream.range(0, 100)
//                                .mapToObj(i -> ballBuilder.build())
//                                .collect(Collectors.toList()));
//        assertEquals(100, world.getSceneEntities().size());
//    }
//
//    /**
//     * check that by inserting a number of brick in the world 
//     * they have been inserted correctly.
//     */
//    @Test
//    public void insertBrickTest() {
//        final Brick.Builder brickBuilder = new Brick.Builder();
//        brickBuilder.position(new P2d(10, 10))
//                    .height(10)
//                    .width(10)
//                    .pointEarned(0)
//                    .color(Color.BLACK)
//                    .indestructible(true)
//                    .energy(1);
//        final World world = new WorldImpl(new Border(600, 600), null);
//        assertTrue(world.getSceneEntities().isEmpty());
//        world.setBricks(IntStream.range(0, 100)
//                                 .mapToObj(i -> brickBuilder.build())
//                                 .collect(Collectors.toList()));
//        assertEquals(100, world.getSceneEntities().size());
//    }
//
//    /**
//     * check that by inserting a number of player in the world 
//     * they have been inserted correctly.
//     */
//    @Test
//    public void insertPlayerTest() {
//        final Player.Builder playerBuilder = new Player.Builder();
//        playerBuilder.position(StartPhase.PLAYER_ONE.getSpawnPoint())
//                     .width(StartPhase.PLAYER_ONE.getInitWidth())
//                     .height(StartPhase.PLAYER_ONE.getInitHeight())
//                     .playerId(PlayerId.ONE);
//        final World world = new WorldImpl(new Border(600, 600), null);
//        assertTrue(world.getSceneEntities().isEmpty());
//        world.setPlayers(IntStream.range(0, 100)
//                                  .mapToObj(i -> playerBuilder.build())
//                                  .collect(Collectors.toList()));
//        assertEquals(100, world.getSceneEntities().size());
//    }
//
//    /**
//     * check that by inserting different types 
//     * of game objects they are inserted correctly.
//     */
//    @Test
//    public void insertEntityTest() {
//        final World world = new WorldImpl(new Border(600, 600), null);
//        assertTrue(world.getSceneEntities().isEmpty());
//        final Ball.Builder ballBuilder = new Ball.Builder();
//        ballBuilder.position(StartPhase.BALL.getSpawnPoint())
//                   .direction(Direction.EDGE_LEFT.getVector().mul(-1))
//                   .height(StartPhase.BALL.getInitHeight())
//                   .width(StartPhase.BALL.getInitWidth())
//                   .speed(Difficulty.HARD.getSpeed());
//        final Brick.Builder brickBuilder = new Brick.Builder();
//        brickBuilder.position(new P2d(10, 10))
//                    .height(10)
//                    .width(10)
//                    .pointEarned(0)
//                    .color(Color.BLACK)
//                    .indestructible(true)
//                    .energy(1);
//        final Player.Builder playerBuilder = new Player.Builder();
//        playerBuilder.position(StartPhase.PLAYER_ONE.getSpawnPoint())
//                     .width(StartPhase.PLAYER_ONE.getInitWidth())
//                     .height(StartPhase.PLAYER_ONE.getInitHeight())
//                     .playerId(PlayerId.ONE);
//        world.setBalls(Arrays.asList(ballBuilder.build()));
//        world.setPlayers(Arrays.asList(playerBuilder.build()));
//        world.setBricks(Arrays.asList(brickBuilder.build()));
//        assertEquals(3, world.getSceneEntities().size());
//    }
//
//    /**
//     * check that collisions occur with the game boarder.
//     */
//    @Test
//    public void checkBoardCollision() {
//        final World world = new WorldImpl(new Border(100, 100), null);
//        assertTrue(world.getSceneEntities().isEmpty());
//        final Ball.Builder ballBuilder = new Ball.Builder();
//        ballBuilder.position(new P2d(95, 50))
//                   .direction(Direction.EDGE_LEFT.getVector().mul(-1))
//                   .height(StartPhase.BALL.getInitHeight())
//                   .width(StartPhase.BALL.getInitWidth())
//                   .speed(Difficulty.HARD.getSpeed());
//        world.setBalls(Arrays.asList(ballBuilder.build()));
//        //set ball pos to the right edge and check for a collision
//        assertEquals(Collision.RIGHT, world.checkCollisionWithBoundaries(ballBuilder.build()).get());
//        //set ball pos to the bottom edge and check for a collision
//        ballBuilder.position(new P2d(50, 95));
//        assertEquals(Collision.BOTTOM, world.checkCollisionWithBoundaries(ballBuilder.build()).get());
//        //set ball pos to the top edge and check for a collision
//        ballBuilder.position(new P2d(50, -5));
//        assertEquals(Collision.TOP, world.checkCollisionWithBoundaries(ballBuilder.build()).get());
//        //set ball pos to the left edge and check for a collision
//        ballBuilder.position(new P2d(-5, 50));
//        assertEquals(Collision.LEFT, world.checkCollisionWithBoundaries(ballBuilder.build()).get());
//        //set ball pos to in the middle of the world and check for no collision;
//        ballBuilder.position(new P2d(50, 50));
//        assertEquals(Optional.empty(), world.checkCollisionWithBoundaries(ballBuilder.build()));
//    }
//
//    /**
//     * Check that by positioning the ball in contact with a brick,
//     * a collision is detected based on the wall where it occurs.
//     */
//    @Test
//    public void checkBrickCollision() {
//        brick.setPos(new P2d(50, 50));
//        ball.setPos(new P2d(55, 30));
//        world.setBricks(Arrays.asList(this.brick));
//        world.setBalls(Arrays.asList(this.ball));
//        //fill the map of the last presence areas of the bricks
//        Optional<Pair<Brick, Collision>> collisionResult = world.checkCollisionWithBrick(world.getBalls().stream().findFirst().get());
//        assertEquals(Optional.empty(), collisionResult);
//        world.getBalls().stream().findFirst().get().setPos(new P2d(55, 45));
//        //set ball pos on the same axis perpendicular to the brick wall and check the collisions
//        collisionResult = world.checkCollisionWithBrick(world.getBalls().stream().findFirst().get());
//        assertTrue(collisionResult.isPresent());
//        assertEquals(Collision.TOP, collisionResult.get().getY());
//        assertEquals(this.brick, collisionResult.get().getX());
//    }
//
//    /**
//     * check that according to the collision zone with the player 
//     * the ball has a direction that follows the rules of the original Akanoid.
//     * the more the collision zone will be to the right, the more acute the angle will be.
//     */
//    @Test
//    public void checkPLayerCollision() {
//        player.setPos(new P2d(50, 50));
//        ball.setPos(new P2d(55, 30));
//        world.setPlayers(Arrays.asList(this.player));
//        world.setBalls(Arrays.asList(this.ball));
//        Pair<Optional<Collision>, Optional<Direction>> collisionResult = world.checkCollisionWithPlayer(world.getBalls().stream().findFirst().get());
//        //fill the map of the last presence areas of the player
//        assertEquals(Optional.empty(), collisionResult.getX());
//        assertEquals(Optional.empty(), collisionResult.getY());
//        world.getBalls().stream().findFirst().get().setPos(new P2d(55, 45));
//        collisionResult = world.checkCollisionWithPlayer(world.getBalls().stream().findFirst().get());
//        //touching the top of the player will also present the direction that the ball will take after the collision
//        assertTrue(collisionResult.getX().isPresent());
//        assertTrue(collisionResult.getY().isPresent());
//        assertEquals(Collision.TOP, collisionResult.getX().get());
//        assertEquals(Direction.LEFT, collisionResult.getY().get());
//        //I move the ball more and more to the right, making the corner more and more sharp
//        world.getBalls().stream().findFirst().get().setPos(new P2d(60, 45));
//        collisionResult = world.checkCollisionWithPlayer(world.getBalls().stream().findFirst().get());
//        assertEquals(Direction.MID_LEFT, collisionResult.getY().get());
//        //I move the ball more and more to the right, making the corner more and more sharp
//        world.getBalls().stream().findFirst().get().setPos(new P2d(75, 45));
//        collisionResult = world.checkCollisionWithPlayer(world.getBalls().stream().findFirst().get());
//        assertEquals(Direction.EDGE_LEFT, collisionResult.getY().get());
//        //I move the ball more and more to the right, making the corner more and more sharp
//        world.getBalls().stream().findFirst().get().setPos(new P2d(85, 45));
//        collisionResult = world.checkCollisionWithPlayer(world.getBalls().stream().findFirst().get());
//        assertEquals(Direction.EDGE_RIGHT, collisionResult.getY().get());
//        //I move the ball more and more to the right, making the corner more and more sharp
//        world.getBalls().stream().findFirst().get().setPos(new P2d(105, 45));
//        collisionResult = world.checkCollisionWithPlayer(world.getBalls().stream().findFirst().get());
//        assertEquals(Direction.MID_RIGHT, collisionResult.getY().get());
//        //I move the ball more and more to the right, making the corner more and more sharp
//        world.getBalls().stream().findFirst().get().setPos(new P2d(120, 45));
//        collisionResult = world.checkCollisionWithPlayer(world.getBalls().stream().findFirst().get());
//        assertEquals(Direction.RIGHT, collisionResult.getY().get());
//        //if the ball bounces on the player's side 
//        //the direction is changed as if it had bounced on a normal vertical wall
//        world.getBalls().stream().findFirst().get().setPos(new P2d(30, 55));
//        collisionResult = world.checkCollisionWithPlayer(world.getBalls().stream().findFirst().get());
//        world.getBalls().stream().findFirst().get().setPos(new P2d(45, 55));
//        collisionResult = world.checkCollisionWithPlayer(world.getBalls().stream().findFirst().get());
//        assertEquals(Collision.LEFT, collisionResult.getX().get());
//        assertTrue(collisionResult.getY().isEmpty());
//    }
//}
