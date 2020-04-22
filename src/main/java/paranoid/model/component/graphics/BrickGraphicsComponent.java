package paranoid.model.component.graphics;

import java.io.Serializable;

import paranoid.common.SerializableColor;
import paranoid.model.entity.Brick;
import paranoid.model.entity.GameObject;

public class BrickGraphicsComponent implements GraphicsComponent, Serializable {

    private static final long serialVersionUID = 5169287434461523175L;
    private final SerializableColor color;

    /**
     * Constructor.
     * @param color the serializableColor wrapper that bring information of color.
     */
    public BrickGraphicsComponent(final SerializableColor color) {
        this.color = color;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void update(final GameObject obj, final GraphicsAdapter ga) {
        ga.drawBrick((Brick) obj, this.color.getColor());
    }

}
