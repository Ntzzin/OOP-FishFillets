package objects;

import pt.iscte.poo.game.Room;
import pt.iscte.poo.utils.Direction;
import pt.iscte.poo.utils.Point2D;
import tests.MessageType;
import tests.Raise;

public class Bomb extends GravityAffected{
	
	private boolean onFall;

	public Bomb(Point2D position, Room room) {
		super(position, room);
		onFall = false;
	}

	@Override
	public String getName() {
		return "bomb";
	}
	
	@Override
	public void onTick() {
		GameObject	object;

		object = super.checkPos(this.getPosition().plus(Direction.DOWN.asVector()));
		if (object == null) {
			onFall = true;
			Raise.log(MessageType.DEBUG, "Gravity!!\n");
			super.move(Direction.DOWN.asVector());
		} else if (onFall) {
			this.getRoom().removeObject(object);
			this.getRoom().removeObject(super.checkPos(this.getPosition().plus(Direction.UP.asVector())));
			this.getRoom().removeObject(super.checkPos(this.getPosition().plus(Direction.RIGHT.asVector())));
			this.getRoom().removeObject(super.checkPos(this.getPosition().plus(Direction.LEFT.asVector())));
			this.getRoom().removeObject(this);
		}
			
	}

}
