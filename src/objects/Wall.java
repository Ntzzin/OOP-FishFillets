package objects;

import pt.iscte.poo.game.Room;
import pt.iscte.poo.utils.Point2D;

public class Wall extends GameObject {

	public Wall(Room room) {
		super(room);
	}
	
	public Wall(Point2D position, Room room) {
		super(position, room);
	}

	@Override
	public String getName() {
		return "wall";
	}	

	@Override
	public int getLayer() {
		return 1;
	}

}
