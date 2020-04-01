package paranoid.model.component.input;

import paranoid.model.entity.GameObject;

public interface InputComponent {

    void update(GameObject obj, InputController c);

}
