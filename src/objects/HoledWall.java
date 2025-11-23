package objects;

import pt.iscte.poo.game.Room;
import pt.iscte.poo.utils.Point2D;

public class HoledWall extends GameObject{

	public HoledWall(Room room) {
		super(room);
	}
	
	public HoledWall(Point2D position, Room room) {
		super(position, room);
	}

	@Override
	public String getName() {
		return "holedWall";
	}

	@Override
	public int getLayer() {
		return 0;
	}

}
