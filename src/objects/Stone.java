package objects;

import pt.iscte.poo.game.Room;
import pt.iscte.poo.gui.ImageGUI;
import pt.iscte.poo.utils.Point2D;
import pt.iscte.poo.utils.Vector2D;
import tests.MessageType;
import tests.Raise;

public class Stone extends GravityAffected {

	public Stone(Point2D position, Room room) {
		super(position, room);
	}

	@Override
	public String getName() {
		return "stone";
	}
	
	@Override
	public int getWeigth() {
		return heavy;
	}

}
