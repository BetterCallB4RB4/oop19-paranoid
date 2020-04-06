package paranoid.model.entity;

import java.io.Serializable;

import paranoid.common.P2d;
import paranoid.common.V2d;
import paranoid.model.component.input.InputComponent;
import paranoid.model.component.input.InputController;
import paranoid.model.component.physics.PhysicsComponent;

public abstract class GameObj implements GameObject, Serializable {

    private static final long serialVersionUID = 1256139078242900664L;
    private P2d pos;
    private V2d vel;
    private double agility;
    private int height;
    private int width;
    private PhysicsComponent phys;
    private InputComponent input;

    public GameObj(final P2d pos, final V2d vel, final double agility, final int height, final int width, final PhysicsComponent phys, final InputComponent input) {
        this.pos = pos;
        this.vel = vel;
        this.agility = agility;
        this.height = height;
        this.width = width;
        this.phys = phys;
        this.input = input;
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
    public double getAgility() {
        return agility;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setAgility(final double agility) {
        this.agility = agility;
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
     * {@inheritDoc}
     */
    @Override
    public PhysicsComponent getPhysicsComponent() {
        return this.phys;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public InputComponent getInputComponent() {
        return this.input;
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

}
