package paranoid.model.component.physics;

import paranoid.common.P2d;
import paranoid.common.V2d;

import java.util.Optional;

import paranoid.common.Collision;
import paranoid.model.entity.GameObject;
import paranoid.model.entity.Player;
import paranoid.model.entity.World;

public final class PlayerPhysicsComponent implements PhysicsComponent {

    /**
     * {@inheritDoc}
     */
    @Override
    public void update(final int dt, final GameObject gameObj, final World w) {

        final Player player = (Player) gameObj;
        final P2d posPlayer = player.getPos();
        final V2d velPlayer = player.getVel();

        player.setPos(posPlayer.sum(velPlayer.mul(SCALER * dt * player.getSpeed())));

        //check collision with world border
        final Optional<Collision> borderCollisionInfo = w.checkCollisionWithBoundaries(player);

        // if collision is present check where happened and don't allow the player to move out of the border
        if (borderCollisionInfo.isPresent()) {
            final Collision typeCol = borderCollisionInfo.get();

            if (typeCol.equals(Collision.LEFT)) {
                player.setPos(new P2d(w.getBorder().getUpperleftCorner().getX(), 
                        player.getPos().getY()));
            } else {
                player.setPos(new P2d(w.getBorder().getBottomRightCorner().getX() - player.getWidth(), 
                        player.getPos().getY()));
            }
        }
    }

}
