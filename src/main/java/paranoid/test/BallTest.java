//package paranoid.test;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.junit.jupiter.api.Assertions.assertThrows;
//
//import java.util.Arrays;
//
//import org.junit.jupiter.api.Test;
//
//import paranoid.common.P2d;
//import paranoid.common.V2d;
//import paranoid.model.collision.Direction;
//import paranoid.model.entity.Ball;
//import paranoid.model.entity.Border;
//import paranoid.model.entity.StartPhase;
//import paranoid.model.entity.World;
//import paranoid.model.settings.Difficulty;
//
//public class BallTest {
//
//    /**
//     * create a ball and check that the builder sets all the fields correctly.
//     */
//    @Test
//    public void ballCreation() {
//        final Ball ball = new Ball.Builder()
//                            .setPosition(StartPhase.BALL.getSpawnPoint())
//                            .setDirection(Direction.EDGE_LEFT.getVector().mul(-1))
//                            .setHeight(StartPhase.BALL.getInitHeight())
//                            .setWidth(StartPhase.BALL.getInitWidth())
//                            .setSpeed(Difficulty.HARD.getSpeed())
//                            .build(); 
//        assertEquals(StartPhase.BALL.getSpawnPoint(), ball.getPos());
//        assertEquals(Direction.EDGE_LEFT.getVector().mul(-1), ball.getVel());
//        assertEquals(Difficulty.HARD.getSpeed(), ball.getSpeed());
//        assertEquals(StartPhase.BALL.getInitHeight(), ball.getHeight());
//        assertEquals(StartPhase.BALL.getInitWidth(), ball.getWidth());
//    }
//
//    /**
//     * create a ball with the wrong fields and check that exceptions are thrown.
//     */
//    @Test
//    public void failBallCreation() { 
//        final Ball.Builder ballBuilder = new Ball.Builder()
//                                           .setPosition(null);
//        assertThrows(IllegalStateException.class, () -> ballBuilder.build());
//        ballBuilder.setPosition(StartPhase.BALL.getSpawnPoint())
//                   .setDirection(null);
//        assertThrows(IllegalStateException.class, () -> ballBuilder.build());
//        ballBuilder.setPosition(StartPhase.BALL.getSpawnPoint())
//                   .setDirection(Direction.EDGE_LEFT.getVector().mul(-1))
//                   .setHeight(-1);
//        assertThrows(IllegalStateException.class, () -> ballBuilder.build());
//        ballBuilder.setPosition(StartPhase.BALL.getSpawnPoint())
//                   .setDirection(Direction.EDGE_LEFT.getVector().mul(-1))
//                   .setHeight(StartPhase.BALL.getInitHeight())
//                   .setWidth(-1);
//        assertThrows(IllegalStateException.class, () -> ballBuilder.build());
//    }
//
//    /**
//     * put ball into the world. Update the time to see the movement of the ball.
//     */
//    @Test
//    public void ballMovement() {
//        final World world = new World(new Border(100, 100), null);
//        final Ball.Builder ballBuilder = new Ball.Builder();
//        ballBuilder.setHeight(StartPhase.BALL.getInitHeight())
//                   .setWidth(StartPhase.BALL.getInitWidth())
//                   .setSpeed(Difficulty.EASY.getSpeed());
//        //north direction
//        double py = Math.sin(Math.toRadians(90));
//        double px = Math.cos(Math.toRadians(90));
//        ballBuilder.setPosition(new P2d(50, 50))
//                   .setDirection(new V2d(px, py));
//        world.setBalls(Arrays.asList(ballBuilder.build()));
//        assertEquals(new P2d(50, 50), world.getBalls().stream().findFirst().get().getPos());
//        world.updateState(10);
//        assertEquals(new P2d(50, 52), world.getBalls().stream().findFirst().get().getPos());
//
//        //south direction
//        py = Math.sin(Math.toRadians(270));
//        px = Math.cos(Math.toRadians(270));
//        ballBuilder.setPosition(new P2d(50, 50))
//                   .setDirection(new V2d(px, py));
//        world.setBalls(Arrays.asList(ballBuilder.build()));
//        assertEquals(new P2d(50, 50), world.getBalls().stream().findFirst().get().getPos());
//        world.updateState(10);
//        assertEquals(new P2d(50, 48), world.getBalls().stream().findFirst().get().getPos());
//
//        //east direction
//        py = Math.sin(Math.toRadians(0));
//        px = Math.cos(Math.toRadians(0));
//        ballBuilder.setPosition(new P2d(50, 50))
//                   .setDirection(new V2d(px, py));
//        world.setBalls(Arrays.asList(ballBuilder.build()));
//        assertEquals(new P2d(50, 50), world.getBalls().stream().findFirst().get().getPos());
//        world.updateState(10);
//        assertEquals(new P2d(52, 50), world.getBalls().stream().findFirst().get().getPos());
//
//        //west direction
//        py = Math.sin(Math.toRadians(180));
//        px = Math.cos(Math.toRadians(180));
//        ballBuilder.setPosition(new P2d(50, 50))
//                   .setDirection(new V2d(px, py));
//        world.setBalls(Arrays.asList(ballBuilder.build()));
//        assertEquals(new P2d(50, 50), world.getBalls().stream().findFirst().get().getPos());
//        world.updateState(10);
//        assertEquals(new P2d(48, 50), world.getBalls().stream().findFirst().get().getPos());
//    }
//
//    /**
//     *  update time in the world and check that for equal time intervals, if the ball 
//     *  has a higher speed, it will have covered a greater space.
//     */
//    @Test
//    public void ballSpeed() {
//        final double py = Math.sin(Math.toRadians(0));
//        final double px = Math.cos(Math.toRadians(0));
//        final World world = new World(new Border(100, 100), null);
//        final Ball.Builder ballBuilder = new Ball.Builder();
//        ballBuilder.setHeight(StartPhase.BALL.getInitHeight())
//                   .setWidth(StartPhase.BALL.getInitWidth())
//                   .setPosition(new P2d(50, 50))
//                   .setDirection(new V2d(px, py));
//        //0 speed
//        ballBuilder.setSpeed(0);
//        world.setBalls(Arrays.asList(ballBuilder.build()));
//        assertEquals(new P2d(50, 50), world.getBalls().stream().findFirst().get().getPos());
//        world.updateState(1000000);
//        assertEquals(new P2d(50, 50), world.getBalls().stream().findFirst().get().getPos());
//
//        //100 speed
//        ballBuilder.setSpeed(100);
//        world.setBalls(Arrays.asList(ballBuilder.build()));
//        assertEquals(new P2d(50, 50), world.getBalls().stream().findFirst().get().getPos());
//        world.updateState(10);
//        assertEquals(new P2d(51, 50), world.getBalls().stream().findFirst().get().getPos());
//
//        //1000 speed
//        ballBuilder.setSpeed(1000);
//        world.setBalls(Arrays.asList(ballBuilder.build()));
//        assertEquals(new P2d(50, 50), world.getBalls().stream().findFirst().get().getPos());
//        world.updateState(10);
//        assertEquals(new P2d(60, 50), world.getBalls().stream().findFirst().get().getPos());
//    }
//
//    /**
//     * put the ball near the edge and update time 
//     * so that the ball collides with the edge.
//     * if the ball collides with a horizontal wall 
//     * invert the component x of the velocity vector.
//     * if the ball collides with a vertical wall 
//     * invert the component y of the velocity vector.
//     */
//    @Test
//    public void ballBorderPhysics() {
//        //collision with border vertical
//        double py = Math.sin(Math.toRadians(0));
//        double px = Math.cos(Math.toRadians(0));
//        final World world = new World(new Border(100, 100), null);
//        final Ball.Builder ballBuilder = new Ball.Builder();
//        ballBuilder.setHeight(10)
//                   .setWidth(10)
//                   .setPosition(new P2d(90, 50))
//                   .setDirection(new V2d(px, py))
//                   .setSpeed(100);
//        world.setBalls(Arrays.asList(ballBuilder.build()));
//        assertEquals(new V2d(px, py), world.getBalls().stream().findFirst().get().getVel());
//        world.updateState(10); 
//        assertEquals(new V2d(-px, py), world.getBalls().stream().findFirst().get().getVel());
//
//        //collision with border orizontal
//        px = Math.cos(Math.toRadians(225));
//        py = Math.sin(Math.toRadians(255));
//        ballBuilder.setHeight(10)
//                   .setWidth(10)
//                   .setDirection(new V2d(px, py))
//                   .setPosition(new P2d(50, 0))
//                   .setSpeed(100);
//        world.setBalls(Arrays.asList(ballBuilder.build()));
//        assertEquals(new V2d(px, py), world.getBalls().stream().findFirst().get().getVel());
//        world.updateState(10); 
//        assertEquals(new V2d(px, -py), world.getBalls().stream().findFirst().get().getVel());
//    }
//
//}
