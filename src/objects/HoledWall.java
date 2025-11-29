package objects;

import pt.iscte.poo.game.Room;
import pt.iscte.poo.utils.Point2D;
import pt.iscte.poo.utils.Vector2D;
import tests.MessageType;
import tests.Raise;

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
		return 2;
	}
	
	public boolean collideWith(GameObject object, Vector2D dir) {
		Raise.log(MessageType.DEBUG, "Checking collision with %s resulted in %s\n", object.getName(), object.getLayer() <= this.getLayer() && object != SmallFish.getInstance());
		return (super.collideWith(object, dir) && object != SmallFish.getInstance());
	}

}
