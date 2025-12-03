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
/*
	public void onTick() {
		GameObject	object;
		object = super.checkPos(this.getPosition().plus(Direction.DOWN.asVector()));GameObject	object;
		object = super.checkPos(this.getPosition().plus(Direction.DOWN.asVector()));
		if (object == null || !collideWith(object, Direction.DOWN.asVector()) || object instanceof GravityAffected) {
			Raise.log(MessageType.DEBUG, "Gravity!!\n");
			move(Direction.DOWN.asVector());
		} else if(object instanceof Entity && !((Entity) object).canSupport(this.getTotalWeigth(Direction.UP.asVector()), Direction.DOWN.asVector()))
			this.getRoom().removeObject(object);
			
	}
*/
	public void onTick() {
			GameObject	object;
			object = super.checkPos(this.getPosition().plus(Direction.DOWN.asVector()));
			Raise.log(MessageType.DEBUG, "Gravity!!\n");
			move(Direction.DOWN.asVector());
			if(object instanceof Entity && !((Entity) object).canSupport(this.getTotalWeigth(Direction.UP.asVector()), Direction.DOWN.asVector()))
				this.getRoom().removeObject(object);
			
	}
}
