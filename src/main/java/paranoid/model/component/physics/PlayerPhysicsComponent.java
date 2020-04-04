package paranoid.model.component.physics;

import paranoid.common.P2d;
import paranoid.common.V2d;

import java.util.Optional;

import paranoid.common.Collision;
import paranoid.model.entity.GameObject;
import paranoid.model.entity.Player;
import paranoid.model.entity.World;

public final class PlayerPhysicsComponent implements PhysicsComponent {

    @Override
    public void update(final int dt, final GameObject gameObj, final World w) {

        final Player player = (Player) gameObj;
        final P2d posPlayer = player.getPos();
        final V2d velPlayer = player.getVel();

        player.setPos(posPlayer.sum(velPlayer.mul(SCALER * dt * player.getAgility())));
        final Optional<Collision> borderCollisionInfo = w.checkCollisionWithBoundaries(player);

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
