package paranoid.model.entity;

import paranoid.common.P2d;
import paranoid.common.V2d;

public abstract class GameObj implements GameObject {

    private P2d pos;
    private V2d vel;
    private int height;
    private int width;

    public GameObj(final P2d pos, final V2d vel, final int height, final int width) {
        this.pos = pos;
        this.vel = vel;
        this.height = height;
        this.width = width;
    }

    /**
     * @return the pos
     */
    public P2d getPos() {
        return pos;
    }

    /**
     * @param pos the pos to set
     */
    public void setPos(final P2d pos) {
        this.pos = pos;
    }

    /**
     * @return the vel
     */
    public V2d getVel() {
        return vel;
    }

    /**
     * @param vel the vel to set
     */
    public void setVel(final V2d vel) {
        this.vel = vel;
    }

    /**
     * @return the height
     */
    public int getHeight() {
        return height;
    }

    /**
     * @param height the height to set
     */
    public void setHeight(final int height) {
        this.height = height;
    }

    /**
     * @return the width
     */
    public int getWidth() {
        return this.width;
    }

    /**
     * @param width the width to set
     */
    public void setWidth(final int width) {
        this.width = width;
    }

}
