package paranoid.model.component.input;

import paranoid.model.entity.GameObject;

public interface InputComponent {

    /**
     * Update input component of selected entity.
     * @param obj
     * @param c
     */
    void update(GameObject obj, InputController c);

}
