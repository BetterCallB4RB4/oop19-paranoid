package paranoid.model.component.input;

import paranoid.model.entity.GameObject;

public class DummyInputComponent implements InputComponent {

    @Override
    public void update(final GameObject obj, final InputController c) {
        //this component does nothing
    }

}
