package objects;

import pt.iscte.poo.game.Room;
import pt.iscte.poo.utils.Direction;
import pt.iscte.poo.utils.Point2D;
import pt.iscte.poo.utils.Vector2D;

public class Trunk extends Entity{

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
	public boolean canSupport(int[] weigths, Vector2D dir) {
		return !(dir.equals(Direction.DOWN.asVector()) && weigths[heavy] >= 1);
	}
	
	@Override
	public boolean move(Vector2D dir) {
		return false;
	}
	
	@Override
	public boolean getPushed(Entity pusher, Vector2D dir) {
		return false;
	}

}
