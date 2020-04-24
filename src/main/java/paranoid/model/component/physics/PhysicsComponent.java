package paranoid.model.component.physics;

import paranoid.model.entity.GameObject;
import paranoid.model.entity.World;

public interface PhysicsComponent {

    /**
     * Constant used for set entity velocity.
     */
    double SCALER = 0.001;

    /**
     * Update physic component of selected entity.
     * @param dt time elapsed from game loop.
     * @param gameObj entity of the game.
     * @param w world.
     */
    void update(int dt, GameObject gameObj, World w);

}
