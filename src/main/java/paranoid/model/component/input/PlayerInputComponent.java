package paranoid.model.component.input;

import paranoid.common.V2d;
import paranoid.model.entity.GameObject;
import paranoid.model.entity.Player;

public final class PlayerInputComponent implements InputComponent {

    /**
     * {@inheritDoc}
     */
    @Override
    public void update(final GameObject obj, final InputController c) {
        final Player player = (Player) obj;

        if (c.isMoveRight()) {
            player.setVel(new V2d(1, 0));
        } else if (c.isMoveLeft()) {
            player.setVel(new V2d(-1, 0));
        } else {
            player.setVel(new V2d(0, 0));
        }
    }

}
