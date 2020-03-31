package paranoid.main;

/**
 * Rappresenta il Launcher del sistema, per bypassare i vincoli dei moduli di Java11.
 */
public final class Launcher {

    private Launcher() {

    }

    /** 
     * @param args
     */
    public static void main(final String[] args) {
        ParanoidApp.main(args);
    }
}
