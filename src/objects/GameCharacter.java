package objects;

import pt.iscte.poo.game.Room;
import pt.iscte.poo.utils.Point2D;
import pt.iscte.poo.utils.Vector2D;

public abstract class GameCharacter extends GameObject {
	
	public GameCharacter(Room room) {
		super(room);
	}
	
	public GameCharacter(Point2D position, Room room) {
		super(position, room);
	}
	
	public void move(Vector2D dir) {
		setPosition(this.getPosition().plus(dir));		
	}

	@Override
	public int getLayer() {
		return 2;
	}
	
}