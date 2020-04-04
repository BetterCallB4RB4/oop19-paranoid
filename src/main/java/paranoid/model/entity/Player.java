package paranoid.model.entity;

import paranoid.common.P2d;
import paranoid.common.V2d;
import paranoid.model.component.input.InputController;
import paranoid.model.component.input.PlayerInputComponent;
import paranoid.model.component.physics.PlayerPhysicsComponent;

/**
 * Player entity.
 */
public class Player extends GameObj {

    public Player(final P2d pos, final V2d vel, final double agility, final int height, final int width) {
        super(pos, vel, agility, height, width, new PlayerPhysicsComponent(), new PlayerInputComponent());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void updatePhysics(final int dt, final World w) {
        super.getPhysicsComponent().update(dt, this, w);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void updateInput(final InputController controller) {
        super.getInputComponent().update(this, controller);

    }

}
