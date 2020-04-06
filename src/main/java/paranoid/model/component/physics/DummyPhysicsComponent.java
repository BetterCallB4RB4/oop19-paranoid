package paranoid.model.component.physics;

import java.io.Serializable;

import paranoid.model.entity.GameObject;
import paranoid.model.entity.World;

public class DummyPhysicsComponent implements PhysicsComponent, Serializable {

    private static final long serialVersionUID = 1377987276200508059L;

    /**
     * 
     * this physical component does nothing.
     */
    @Override
    public void update(final int dt, final GameObject gameObj, final World w) {
    }
}
