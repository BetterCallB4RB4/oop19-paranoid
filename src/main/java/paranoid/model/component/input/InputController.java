package paranoid.model.component.input;

/**
 * Interfaccia che verifica se l'utente sta cercando di muoversi usando dispositivi di input.
 *
 */
public interface InputController {

    /**
     * Controlla se l'utente sta cercando di muovere a destra il componente.
     * @return ritorna un booleano.
     */
    boolean isMoveRight();

    /**
     * Controlla se l'utente sta cercando di muovere a sinistra il componente.
     * @return ritorna un booleano.
     */
    boolean isMoveLeft();

    /**
     * Metodo che viene richiamato quando l'utente sta effettivamente muovendosi o meno a destra.
     * @param condition vero se si muove a destra, falso altrimenti.
     */
    void notifyMoveRight(boolean condition);

    /**
     * Metodo che viene richiamato quando l'utente sta effettivamente muovendosi o meno a sinistra.
     * @param condition vero se si muove a sinistra, falso altrimenti.
     */
    void notifyMoveLeft(boolean condition);

}
