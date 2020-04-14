package paranoid.model.entity;

import paranoid.common.P2d;
import paranoid.common.V2d;
import paranoid.model.component.input.InputComponent;
import paranoid.model.component.input.InputController;
import paranoid.model.component.physics.PhysicsComponent;

public interface GameObject {

    /**
     * @param pos the position to set
     */
    void setPos(P2d pos);

    /**
     * @param vel the velocity to set
     */
    void setVel(V2d vel);

    /**
     * @return the position
     */
    P2d getPos();

    /**
     * @return the velocity
     */
    V2d getVel();

    /**
     * @param speed the agility to set
     */
    void setSpeed(double speed);

    /**
     * @return the agility
     */
    double getSpeed();

    /**
     * @param height the height to set
     */
    void setHeight(int height);

    /**
     * @param width the width to set
     */
    void setWidth(int width);

    /**
     * @return the height
     */
    int getHeight();

    /**
     * @return the width
     */
    int getWidth(); 

    /**
     * @return the physical component of this gameObj
     */
    PhysicsComponent getPhysicsComponent();

    /**
     * @return the input component of this gameObj
     */
    InputComponent getInputComponent();

    /**
     * @param dt the time elapsed from game loop
     * @param w the world model
     */
    void updatePhysics(int dt, World w);

    /**
     * @param controller the input controller of this gameObj
     */
    void updateInput(InputController controller);
}
