package paranoid.model.entity;

import paranoid.common.P2d;
import paranoid.common.V2d;
import paranoid.model.component.graphics.DummyGraphicsComponent;
import paranoid.model.component.graphics.GraphicsAdapter;
import paranoid.model.component.input.DummyInputComponent;
import paranoid.model.component.input.InputController;
import paranoid.model.component.physics.DummyPhysicsComponent;

public class PlaceHolder extends GameObj {

    public PlaceHolder(final P2d pos, final int height, final int width) {
        super(pos, new V2d(0, 0), 0, height, width, new DummyPhysicsComponent(), new DummyInputComponent(), new DummyGraphicsComponent());
    }

    /**
     * update physics component.
     */
    @Override
    public void updatePhysics(final int dt, final World w) {
        this.getPhysicsComponent().update(dt, this, w);
    }

    /**
     * update input component.
     */
    @Override
    public void updateInput(final InputController controller) {
        this.getInputComponent().update(this, controller);
    }

    /**
     * allows to update the graphics component of the placeHolder.
     */
    @Override
    public void updateGraphics(final GraphicsAdapter graphicsAdapter) {
        this.getGraphicsComponent().update(this, graphicsAdapter);
    }
}
