package paranoid.common;

import java.io.Serializable;

import javafx.scene.paint.Color;

public class SerializableColor implements Serializable {

    private static final long serialVersionUID = -6024360006502976365L;
    private double red;
    private double green;
    private double blue;

    public SerializableColor(final Color color) {
        this.red = color.getRed();
        this.green = color.getGreen();
        this.blue = color.getBlue();
    }

    /**
     * 
     * @return color scomposed int his parts
     */
    public Color getColor() {
        return Color.rgb((int) red, (int) green, (int) blue);
    }

}
