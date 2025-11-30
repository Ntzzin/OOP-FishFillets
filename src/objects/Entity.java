package objects;

import java.util.List;

import pt.iscte.poo.game.Room;
import pt.iscte.poo.gui.ImageGUI;
import pt.iscte.poo.utils.Direction;
import pt.iscte.poo.utils.Point2D;
import pt.iscte.poo.utils.Vector2D;
import tests.MessageType;
import tests.Raise;

public abstract class Entity extends GameObject {
	
	public final int ligth = 1;
	public final int heavy = (ImageGUI.getInstance().getGridDimension().height/48) - 1; //max ligth elements that a fish can support
	
	public Entity(Room room) {
		super(room);
	}
	
	public Entity(Point2D position, Room room) {
		super(position, room);
	}

	public GameObject checkPos(Point2D position) {
		List<GameObject>	objects;

		objects = this.getRoom().getObjects();
		for (GameObject object : objects)
	    	if (!object.equals(this) && object.getPosition().equals(position) && object.getLayer() != 0)
	    		return object;
	    return null;
	}
	
	public boolean move(Vector2D dir) {
		GameObject	object;
		Point2D		target;

		target = this.getPosition().plus(dir);
		Raise.log(MessageType.DEBUG, "Trying to move to pos (%d, %d)\n", target.getX(), target.getY());
		object = checkPos(target);
		if (object != null && object.collideWith(this, dir)) {
			Raise.log(MessageType.WARNING, "collided with something\n");
			if(object instanceof Entity && ((Entity) object).getTotalWeigth(dir) <= this.getMaxWeigth() && ((Entity) object).move(dir)) {
				Raise.log(MessageType.SUCCESS, "moved to pos (%d, %d)\n", target.getX(), target.getY());
				setPosition(target);
				return true;
			}
			return false;
		}else {
		Raise.log(MessageType.SUCCESS, "moved to pos (%d, %d)\n", target.getX(), target.getY());
		setPosition(target);
		}
		return true;
	}
	
	@Override
	public boolean collideWith(GameObject object, Vector2D dir) {
		Raise.log(MessageType.DEBUG, "Checking collision with %s resulted in %s\n", object.getName(), object.getLayer() <= this.getLayer());
		return (super.collideWith(object, dir));
	}
	
	@Override
	public int getLayer() {
		return 1;
	}

	public int getWeigth() {
		return ligth;
	}
	
	public int getTotalWeigth(Vector2D dir) {
		GameObject	o;
		int			res;

		res = this.getWeigth();
		o = checkPos(this.getPosition().plus(dir));
		if (o != null && o instanceof Entity)
			res += ((Entity) o).getTotalWeigth(dir);
		return res;
	}
	
	public int getMaxWeigth() {
		return 999;
	}
	
}
