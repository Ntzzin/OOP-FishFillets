 package objects;

import pt.iscte.poo.game.Room;
import pt.iscte.poo.utils.Vector2D;
import tests.MessageType;
import tests.Raise;

public class BigFish extends GameCharacter {

	private static BigFish bf = new BigFish(null);
	
	private BigFish(Room room) {
		super(room);
	}
	
	public static BigFish getInstance() {
		return bf;
	}
	
	@Override
	public String getName() {
		return "bigFishLeft";
	}

	@Override
	public boolean canSupport(int[] weigths, Vector2D dir) {
		//Raise.log(MessageType.DEBUG, "the weigths that i need to push are: %d(l) %d(h)\n", weigths[ligth], weigths[heavy]);
		return ((weigths[heavy] == 0  || (weigths[heavy] == 1 && weigths[ligth] == 0)) && dir.getY() == 0) ||
				(weigths[heavy] + weigths[ligth] <= 1 && dir.getY() != 0);
	}
	
	@Override
	public boolean getPushed(Entity pusher, Vector2D dir) {
		return false;
	}
	
	@Override
	public boolean collideWith(GameObject object, Vector2D dir) {
		if (object instanceof Krab)
			this.getRoom().removeObject(object);
		else if (object instanceof Trap)
			this.getRoom().removeObject(this);
		return super.collideWith(object, dir);
	}
}
