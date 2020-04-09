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

    public Ball getBall() {
        return this.ball;
    }

    public Collision getCollision() {
        return this.collision;
    }
}
