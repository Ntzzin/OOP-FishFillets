package objects;

import pt.iscte.poo.game.Room;
import pt.iscte.poo.utils.Point2D;
import pt.iscte.poo.utils.Vector2D;

public class Trap extends GravityAffected{

	public Trap(Point2D position, Room room) {
		super(position, room);
	}

	@Override
	public String getName() {
		return "trap";
	}
	
	@Override
	public int getWeigth() {
		return heavy;
	}
	
	@Override
	public boolean collideWith(GameObject object, Vector2D dir) {
		if (object instanceof BigFish) {
			this.getRoom().removeObject(BigFish.getInstance());
			return true;
		}
		else if (object instanceof SmallFish)
			return false;
		return super.collideWith(object, dir);
	}

}
