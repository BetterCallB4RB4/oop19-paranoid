package paranoid.main;

/**
 * Launcher of the application, for bypass java11 modules constraints.
 */
public final class Launcher {

    private Launcher() {

    }

    /** 
     * @param args command input
     */
    public static void main(final String[] args) {
        ParanoidApp.main(args);
    }
}
