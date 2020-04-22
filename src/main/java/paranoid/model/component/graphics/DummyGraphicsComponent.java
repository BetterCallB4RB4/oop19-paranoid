package paranoid.model.component.graphics;

import java.io.Serializable;

import paranoid.model.entity.GameObject;

public class DummyGraphicsComponent implements GraphicsComponent, Serializable {

    private static final long serialVersionUID = 520121868558537070L;

    @Override
    public void update(final GameObject obj, final GraphicsAdapter w) {
        //dummy method do nothing
    }

}
