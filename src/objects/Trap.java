package objects;

import pt.iscte.poo.game.Room;
import pt.iscte.poo.utils.Point2D;

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

}
