package objects;

import pt.iscte.poo.game.Room;
import pt.iscte.poo.utils.Point2D;

public class Anchor extends GravityAffected{

	public Anchor(Point2D position, Room room) {
		super(position, room);
	}

	@Override
	public String getName() {
		return "anchor";
	}
	
	@Override
	public int getWeigth() {
		return heavy;
	}

	@Override
	public int getLayer() {
		return 2;
	}

}
