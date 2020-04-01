package paranoid.model.entity;

import paranoid.common.P2d;
import paranoid.common.V2d;
import paranoid.model.component.input.InputComponent;
import paranoid.model.component.input.InputController;
import paranoid.model.component.physics.PhysicsComponent;

public interface GameObject {

    void setPos(P2d pos);

    void setVel(V2d vel);

    P2d getPos();

    V2d getVel();

    void setHeight(int height);

    void setWidth(int width);

    int getHeight();

    int getWidth(); 

    PhysicsComponent getPhysicsComponent();

    InputComponent getInputComponent();

    void updatePhysics(int dt, World w);

    void updateInput(InputController controller);
}
