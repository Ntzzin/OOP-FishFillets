package objects;

import pt.iscte.poo.game.Room;
import pt.iscte.poo.utils.Point2D;
import pt.iscte.poo.utils.Vector2D;

public abstract class GameCharacter extends Entity {
	
	public GameCharacter(Room room) {
		super(room);
	}
	
	@Override
	public int getLayer() {
		return 1;
	}
	
}