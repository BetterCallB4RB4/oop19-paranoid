package paranoid.model.component.graphics;

import java.io.Serializable;

import paranoid.common.SerializableColor;
import paranoid.model.entity.Brick;
import paranoid.model.entity.GameObject;

public class BrickGraphicsComponent implements GraphicsComponent, Serializable {
    private static final long serialVersionUID = 5169287434461523175L;
    private final SerializableColor color;

    public BrickGraphicsComponent(final SerializableColor color) {
        this.color = color;
    }

    @Override
    public void update(GameObject obj, GraphicsAdapter ga) {
        ga.drawBrick((Brick) obj, this.color.getColor());
    }

}
