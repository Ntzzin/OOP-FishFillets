package objects;

import pt.iscte.poo.game.Room;
import pt.iscte.poo.utils.Point2D;

public interface GameObjectFactory {
	public GameObject create(Point2D position, Room room);
}