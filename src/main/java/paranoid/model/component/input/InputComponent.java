package paranoid.model.component.input;

import paranoid.model.entity.GameObject;

public interface InputComponent {

    /**
     * Update input component of selected entity.
     * @param obj to update
     * @param c input control via an input device
     */
    void update(GameObject obj, InputController c);

}
