package paranoid.model.component.input;

/**
 * Interface that check if user use input device.
 */
public interface InputController {

    /**
     * @return true if user try to move right.
     */
    boolean isMoveRight();

    /**
     * @return true if user try to move left.
     */
    boolean isMoveLeft();

    /**
     * @param condition set the flag moveRight to true if user try to move right.
     */
    void notifyMoveRight(boolean condition);

    /**
     * @param condition set the flag moveRight to true if user try to move left.
     */
    void notifyMoveLeft(boolean condition);

}
