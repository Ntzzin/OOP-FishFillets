package objects;

import pt.iscte.poo.game.Room;

public interface GameObjectFactory {
	public GameObject create(Room room);
}