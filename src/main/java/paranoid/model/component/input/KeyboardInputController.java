package paranoid.model.component.input;

public class KeyboardInputController implements InputController {

    private boolean isBallRolling;

    /**
     * controll if the player started the match.
     */
    @Override
    public boolean isBallRolling() {
        return isBallRolling;
    }

    /**
     * notifies that the player press the button to starte the game.
     */
    public void notifyBallRoll() {
        isBallRolling = true;
    }

    /**
     * notifies that the player press the button to pause the game.
     */
    public void notifyNoMoreBallRoll() {
        isBallRolling = false;
    }

}
