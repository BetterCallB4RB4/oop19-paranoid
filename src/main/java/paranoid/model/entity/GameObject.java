package paranoid.model.entity;

import paranoid.common.P2d;
import paranoid.common.V2d;

public interface GameObject {

    void setPos(P2d pos);

    void setVel(V2d vel);

    P2d getPos();

    V2d getVel();

    void setHeight(int height);

    void setWidth(int width);

    int getHeight();

    int getWidth(); 

}
