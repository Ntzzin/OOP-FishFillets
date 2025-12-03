package objects;

import pt.iscte.poo.game.Room;
import pt.iscte.poo.gui.ImageGUI;
import pt.iscte.poo.utils.Direction;
import pt.iscte.poo.utils.Point2D;
import pt.iscte.poo.utils.Vector2D;
import tests.MessageType;
import tests.Raise;

public class Krab extends GravityAffected{
	
	//private static Point2D[] lastPositions = {
	//	    BigFish.getInstance().getPosition(),
	//	    SmallFish.getInstance().getPosition()
	//	};
	
	private Point2D[] lastPositions;

	public Krab(Point2D position, Room room) {
		super(position, room);
		lastPositions = new Point2D[2];
		room.addUpdatable(this);
	}
	
	@Override
	public String getName() {
		return "krab";
	}
	
	@Override
	public boolean move(Vector2D dir) {
		return super.move(dir);
	}
	
	@Override
	public void onTick() {
		if(lastPositions[0] == null || lastPositions[1] == null) {
			lastPositions[0] = BigFish.getInstance().getPosition();
			lastPositions[1] = SmallFish.getInstance().getPosition();
		}
		else if(!BigFish.getInstance().getPosition().equals(lastPositions[0]) || !SmallFish.getInstance().getPosition().equals(lastPositions[1])) {
			lastPositions[0] = BigFish.getInstance().getPosition();
			lastPositions[1] = SmallFish.getInstance().getPosition();
			super.move(Direction.values()[2].asVector());
		}
		super.onTick();
	}
	
	@Override
	public boolean collideWith(GameObject object, Vector2D dir) {
		if (object instanceof SmallFish || object instanceof BigFish || object instanceof Trap) {
			if (object instanceof SmallFish)
				this.getRoom().removeObject(SmallFish.getInstance());
			else
				this.getRoom().removeObject(this);
			return true;
		}
		return super.collideWith(object, dir);
	}
	
	@Override
	public boolean getPushed(Entity pusher, Vector2D dir) {
		return false;
	}

}
