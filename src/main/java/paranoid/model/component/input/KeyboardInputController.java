package paranoid.model.component.input;

public final class KeyboardInputController implements InputController {

    private boolean moveRight;
    private boolean moveLeft;

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isMoveRight() {
        return this.moveRight;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isMoveLeft() {
        return this.moveLeft;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void notifyMoveRight(final boolean condition) {
        this.moveRight = condition;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void notifyMoveLeft(final boolean condition) {
        this.moveLeft = condition;
    }


}
