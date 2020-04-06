package paranoid.model.component.input;

import java.io.Serializable;

import paranoid.model.entity.GameObject;

public class DummyInputComponent implements InputComponent, Serializable {

    private static final long serialVersionUID = 5635148821747383151L;

    @Override
    public void update(final GameObject obj, final InputController c) {
        //this component does nothing
    }

}
