package objects;

import pt.iscte.poo.game.Room;
import pt.iscte.poo.utils.Vector2D;

public class SmallFish extends GameCharacter {

	private static SmallFish sf = new SmallFish(null);
	
	private SmallFish(Room room) {
		super(room);
	}

	public static SmallFish getInstance() {
		return sf;
	}
	
	@Override
	public String getName() {
		return "smallFishLeft";
	}
	
	@Override
	public boolean canSupport(int[] weigths, Vector2D dir) {
		return weigths[ligth] <= 1 && weigths[heavy] < 1;
	}
	
	@Override
	public boolean getPushed(Entity pusher, Vector2D dir) {
		return false;
	}
	
	@Override
	public boolean collideWith(GameObject object, Vector2D dir) {
		if (object instanceof Krab) {
			this.getRoom().removeObject(this);
			return true;
		}
		return super.collideWith(object, dir);
	}

}
