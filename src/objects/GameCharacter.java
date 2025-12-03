package objects;

import java.util.ArrayList;

import pt.iscte.poo.game.Room;
import pt.iscte.poo.utils.Point2D;
import pt.iscte.poo.utils.Vector2D;

public abstract class GameCharacter extends Entity {
	
	private static ArrayList<GameCharacter> characters = new ArrayList<GameCharacter>();
	private static int pointer = 0;
	
	public GameCharacter(Room room) {
		super(room);
		characters.add(this);
	}
	
	public static GameCharacter getNext() {
		pointer = (pointer + 1) % characters.size();
		return characters.get(pointer);
	}
	
	@Override
	public int getLayer() {
		return 1;
	}
	
} 