package objects;

import pt.iscte.poo.game.Room;
import pt.iscte.poo.utils.Point2D;

public class Trunk extends GameObject{

	public Trunk(Room room) {
		super(room);
	}
	
	public Trunk(Point2D position, Room room) {
		super(position, room);
	}

	@Override
	public String getName() {
		return "trunk";
	}

	@Override
	public int getLayer() {
		return 2;
	}

}
