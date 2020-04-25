package paranoid.model.component.physics;

import java.io.Serializable;

import paranoid.model.entity.GameObject;
import paranoid.model.entity.WorldImpl;

public class DummyPhysicsComponent implements PhysicsComponent, Serializable {

    private static final long serialVersionUID = 1377987276200508059L;

    @Override
    public void update(final int dt, final GameObject gameObj, final WorldImpl w) {
        //this physical component does nothing.
    }
}
