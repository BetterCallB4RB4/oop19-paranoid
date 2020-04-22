package paranoid.model.entity;

import paranoid.common.P2d;
import paranoid.common.V2d;
import paranoid.model.component.graphics.DummyGraphicsComponent;
import paranoid.model.component.graphics.GraphicsAdapter;
import paranoid.model.component.input.DummyInputComponent;
import paranoid.model.component.input.InputController;
import paranoid.model.component.physics.DummyPhysicsComponent;

public class PlaceHolder extends GameObj {

    private static final long serialVersionUID = 6032673900262499421L;

    public PlaceHolder(final P2d pos, final int height, final int width) {
        super(pos, new V2d(0, 0), 0, height, width, new DummyPhysicsComponent(), new DummyInputComponent(), new DummyGraphicsComponent());
    }

    /**
     * the placeholder is used to maintain a reference in space. this is a dummy method.
     */
    @Override
    public void updatePhysics(final int dt, final World w) {
        this.getPhysicsComponent().update(dt, this, w);
    }

    /**
     * the placeholder is used to maintain a reference in space. this is a dummy method.
     */
    @Override
    public void updateInput(final InputController controller) {
        this.getInputComponent().update(this, controller);
    }

    /**
     * the placeholder is used to maintain a reference in space. this is a dummy method.
     */
    @Override
    public void updateGraphics(final GraphicsAdapter graphicsAdapter) {
        this.getGraphicsComponent().update(this, graphicsAdapter);
    }
}
