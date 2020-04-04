package paranoid.model.entity;

import paranoid.common.P2d;
import paranoid.common.V2d;
import paranoid.model.component.input.InputController;
import paranoid.model.component.input.PlayerInputComponent;
import paranoid.model.component.physics.DummyPhysicsComponent;

/**
 * Classe che gestisce l'entità del giocatore con i relativi componenti fisici e di input.
 *
 */
public class Player extends GameObj {

    public Player(final P2d pos, final V2d vel, final double agility, final int height, final int width) {
        super(pos, vel, agility, height, width, new DummyPhysicsComponent(), new PlayerInputComponent());
    }
    /**
     * Gestisce le collisioni con il bordo di gioco ed effettua lo postamento del giocatore.
     */
    @Override
    public void updatePhysics(final int dt, final World w) {
        super.getPhysicsComponent().update(dt, this, w);
    }
    /**
     * Gestisce la direzione del movimento tramite comandi da tastiera.
     */
    @Override
    public void updateInput(final InputController controller) {
        super.getInputComponent().update(this, controller);

    }

}
