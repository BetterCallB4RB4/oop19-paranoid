package paranoid.model.component.physics;

import paranoid.model.entity.GameObject;
import paranoid.model.entity.World;

public class DummyPhysicsComponent implements PhysicsComponent {

    /**
     * 
     * this physical component does nothing.
     */
    @Override
    public void update(final int dt, final GameObject gameObj, final World w) {
    }
}
