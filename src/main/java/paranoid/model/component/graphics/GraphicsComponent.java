package paranoid.model.component.graphics;

import paranoid.model.entity.GameObject;

public interface GraphicsComponent {

    /**
     * updates the graphic component.
     * @param obj to draw
     * @param ga Graphics adapter
     */
    void update(GameObject obj, GraphicsAdapter ga);

}
