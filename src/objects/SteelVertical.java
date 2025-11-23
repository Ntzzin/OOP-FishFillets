package objects;

import pt.iscte.poo.game.Room;
import pt.iscte.poo.utils.Point2D;
import pt.iscte.poo.utils.Vector2D;
import tests.MessageType;
import tests.Raise;

public class SteelVertical extends GameObject {

	public SteelVertical(Room room) {
		super(room);
	}
	
	public SteelVertical(Point2D position, Room room) {
		super(position, room);
	}

	@Override
	public String getName() {
		return "steelVertical";
	}

	@Override
	public int getLayer() {
		return 1;
	}
	
	public boolean collideWith(GameObject object, Vector2D dir) {
		Raise.log(MessageType.DEBUG, "Checking collision with %s resulted in %s\n", object.getName(), object.getLayer() >= this.getLayer() && dir.getX() != 0);
		return object.getLayer() >= this.getLayer() && dir.getX() != 0;
	}

}
