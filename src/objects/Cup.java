package objects;

import pt.iscte.poo.game.Room;
import pt.iscte.poo.utils.Point2D;

public class Cup extends GravityAffected {

	public Cup(Point2D position, Room room) {
		super(position, room);
	}

	@Override
	public String getName() {
		return "cup";
	}

}
