package paranoid.model.entity;

import paranoid.common.P2d;
import paranoid.common.V2d;
import paranoid.model.component.physics.PhysicsComponent;

public abstract class GameObj implements GameObject {

    private P2d pos;
    private V2d vel;
    private int height;
    private int width;
    private PhysicsComponent phys;

    public GameObj(final P2d pos, final V2d vel, final int height, final int width, final PhysicsComponent phys) {
        this.pos = pos;
        this.vel = vel;
        this.height = height;
        this.width = width;
        this.phys = phys;
    }

    /**
     * @return the pos
     */
    @Override
    public P2d getPos() {
        return pos;
    }

    /**
     * @param pos the pos to set
     */
    @Override
    public void setPos(final P2d pos) {
        this.pos = pos;
    }

    /**
     * @return the vel
     */
    @Override
    public V2d getVel() {
        return vel;
    }

    /**
     * @param vel the vel to set
     */
    @Override
    public void setVel(final V2d vel) {
        this.vel = vel;
    }

    /**
     * @return the height
     */
    @Override
    public int getHeight() {
        return height;
    }

    /**
     * @param height the height to set
     */
    @Override
    public void setHeight(final int height) {
        this.height = height;
    }

    /**
     * @return the width
     */
    @Override
    public int getWidth() {
        return this.width;
    }

    /**
     * @param width the width to set
     */
    @Override
    public void setWidth(final int width) {
        this.width = width;
    }

    /**
     * 
     * @return the physical component of this gameObj
     */
    public PhysicsComponent getPhysicsComponent() {
        return this.phys;
    }

    public abstract void updatePhysics(int dt, World w);

}
