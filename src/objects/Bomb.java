package objects;

import pt.iscte.poo.game.Room;
import pt.iscte.poo.utils.Point2D;

public class Bomb extends GameObject{

	public Bomb(Room room) {
		super(room);
	}
	
	public Bomb(Point2D position, Room room) {
		super(position, room);
	}

	@Override
	public String getName() {
		return "bomb";
	}

	@Override
	public int getLayer() {
		return 0;
	}

}
