package objects;

import pt.iscte.poo.game.Room;
import pt.iscte.poo.utils.Point2D;

public class Water extends GameObject{

	public Water(Room room) {
		super(room);
	}
	
	public Water(Point2D position, Room room) {
		super(position, room);
	}

	@Override
	public String getName() {
		return "water";
	}

	@Override
	public int getLayer() {
		return 0;
	}

}
