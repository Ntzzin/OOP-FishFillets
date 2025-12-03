package objects;

import pt.iscte.poo.game.Room;
import pt.iscte.poo.utils.Direction;
import pt.iscte.poo.utils.Point2D;
import pt.iscte.poo.utils.Vector2D;
import tests.MessageType;
import tests.Raise;

public class Buoy extends GravityAffected {

	public Buoy(Point2D position, Room room) {
		super(position, room);
	}
	
	@Override
	public String getName() {
		return "buoy";
	}
	
	@Override
	public boolean getPushed(Entity pusher, Vector2D dir) {
		if (dir.getY() != 0 && pusher.equals(SmallFish.getInstance()))
			return false;
		return super.getPushed(pusher, dir);
	}
	
	@Override
	public void onTick() {
		GameObject	object;
		object = super.checkPos(this.getPosition().plus(Direction.UP.asVector()));
		if (object == null && !(object instanceof GravityAffected)) {
			Raise.log(MessageType.DEBUG, "Reverse Gravity!!\n");
			super.move(Direction.UP.asVector());
		}	
		
	}

}
