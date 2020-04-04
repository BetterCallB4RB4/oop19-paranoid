package paranoid.model.entity;

import paranoid.common.P2d;
import paranoid.common.V2d;
import paranoid.model.component.input.DummyInputComponent;
import paranoid.model.component.input.InputController;
import paranoid.model.component.physics.DummyPhysicsComponent;

public class Brick extends GameObj {

    public Brick(final P2d pos, final V2d vel, final double agility, final int height, final int width) {
        super(pos, vel, agility, height, width, new DummyPhysicsComponent(), new DummyInputComponent());
    }

    @Override
    public void updatePhysics(final int dt, final World w) {
        super.getPhysicsComponent().update(dt, this, w);
    }

    @Override
    public void updateInput(final InputController controller) {
        super.getInputComponent().update(this, controller);

    }

}
