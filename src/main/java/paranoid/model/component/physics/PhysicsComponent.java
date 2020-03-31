package paranoid.model.component.physics;

import paranoid.model.entity.GameObject;
import paranoid.model.entity.World;

public interface PhysicsComponent {

    void update(int dt, GameObject gameObj, World w);

}
