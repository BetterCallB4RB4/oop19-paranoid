package paranoid.model.entity;

import java.io.Serializable;

import paranoid.common.P2d;
import paranoid.common.V2d;
import paranoid.model.component.graphics.GraphicsAdapter;
import paranoid.model.component.graphics.GraphicsComponent;
import paranoid.model.component.input.InputComponent;
import paranoid.model.component.input.InputController;
import paranoid.model.component.physics.PhysicsComponent;

public abstract class GameObj implements GameObject, Serializable {

    private static final long serialVersionUID = 1256139078242900664L;
    private P2d pos;
    private V2d vel;
    private double speed;
    private int height;
    private int width;
    private final PhysicsComponent phys;
    private final InputComponent input;
    private final GraphicsComponent graphics;

    public GameObj(final P2d pos, final V2d vel, final double speed, final int height, final int width, final PhysicsComponent phys, final InputComponent input, final GraphicsComponent graphics) {
        this.pos = pos;
        this.vel = vel;
        this.speed = speed;
        this.height = height;
        this.width = width;
        this.phys = phys;
        this.input = input;
        this.graphics = graphics;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public P2d getPos() {
        return pos;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setPos(final P2d pos) {
        this.pos = pos;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public V2d getVel() {
        return vel;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setVel(final V2d vel) {
        this.vel = vel;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public double getSpeed() {
        return speed;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setSpeed(final double speed) {
        this.speed = speed;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getHeight() {
        return height;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setHeight(final int height) {
        this.height = height;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getWidth() {
        return this.width;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setWidth(final int width) {
        this.width = width;
    }

    /**
     * 
     * @return asofi
     */
    protected PhysicsComponent getPhysicsComponent() {
        return this.phys;
    }

    /**
     * 
     * @return dsad
     */
    protected InputComponent getInputComponent() {
        return this.input;
    }

    /**
     * 
     * @return J
     */
    protected GraphicsComponent getGraphicsComponent() {
        return this.graphics;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public abstract void updatePhysics(int dt, World w);

    /**
     * {@inheritDoc}
     */
    @Override
    public abstract void updateInput(InputController controller);

    /**
     * {@inheritDoc}
     */
    @Override
    public abstract void updateGraphics(GraphicsAdapter graphicsAdapter);

}
