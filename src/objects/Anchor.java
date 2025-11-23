package objects;

import pt.iscte.poo.game.Room;
import pt.iscte.poo.utils.Point2D;

public class Anchor extends GameObject{

	public Anchor(Room room) {
		super(room);
	}
	
	public Anchor(Point2D position, Room room) {
		super(position, room);
	}

	@Override
	public String getName() {
		return "anchor";
	}

	@Override
	public int getLayer() {
		return 0;
	}

}
