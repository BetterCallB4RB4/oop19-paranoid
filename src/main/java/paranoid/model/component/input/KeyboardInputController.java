package paranoid.model.component.input;

public final class KeyboardInputController implements InputController {

    private boolean moveRight;
    private boolean moveLeft;

    @Override
    public boolean isMoveRight() {
        return this.moveRight;
    }

    @Override
    public boolean isMoveLeft() {
        return this.moveLeft;
    }

    @Override
    public void notifyMoveRight(final boolean condition) {
        this.moveRight = condition;
    }

    @Override
    public void notifyMoveLeft(final boolean condition) {
        this.moveLeft = condition;
    }


}
