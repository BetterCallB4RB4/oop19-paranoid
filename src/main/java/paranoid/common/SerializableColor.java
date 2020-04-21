package paranoid.common;

import java.io.Serializable;

import javafx.scene.paint.Color;

public class SerializableColor implements Serializable {

    private static final long serialVersionUID = -6024360006502976365L;
    private final double red;
    private final double green;
    private final double blue;

    public SerializableColor(final Color color) {
        this.red = color.getRed();
        this.green = color.getGreen();
        this.blue = color.getBlue();
    }

    /**
     * 
     * @return color composed of its 3 parts
     */
    public Color getColor() {
        return Color.color(red, green, blue);
    }

}
