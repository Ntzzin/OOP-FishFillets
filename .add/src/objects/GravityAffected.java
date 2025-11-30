package objects;

import pt.iscte.poo.game.Room;
import pt.iscte.poo.utils.Direction;
import pt.iscte.poo.utils.Point2D;
import pt.iscte.poo.utils.Vector2D;
import tests.MessageType;
import tests.Raise;

public abstract class GravityAffected extends Entity implements Updatable {

	public GravityAffected(Point2D position, Room room) {
		super(position, room);
		room.addUpdatable(this);
	}

	public void onTick() {
		GameObject	object;
		object = getRoom().checkPos(this.getPosition().plus(Direction.DOWN.asVector()));
		if (object == null) {
			Raise.log(MessageType.DEBUG, "Gravity!!\n");
			super.move(Direction.DOWN.asVector());
		}
	}

}
