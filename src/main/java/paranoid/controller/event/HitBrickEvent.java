package paranoid.controller.event;

import paranoid.model.entity.Brick;

public class HitBrickEvent implements Event {
    private final Brick brick;

    public HitBrickEvent(final Brick brick) {
        this.brick = brick;
    }

    /**
     * @return the brick hit.
     */
    public Brick getBrick() {
        return this.brick;
    }
}
