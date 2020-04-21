package paranoid.controller.event;

import paranoid.common.Collision;
import paranoid.model.entity.Ball;

public class HitBorderEvent implements Event {

    private final Ball ball;
    private final Collision collision;

    public HitBorderEvent(final Ball ball, final Collision collision) {
        this.ball = ball;
        this.collision = collision;
    }

    /**
     * 
     * @return as
     */
    public Ball getBall() {
        return this.ball;
    }

    /**
     * 
     * @return fw
     */
    public Collision getCollision() {
        return this.collision;
    }
}
