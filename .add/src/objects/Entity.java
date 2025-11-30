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

	public boolean move(Vector2D dir) {
		GameObject	object;
		Point2D		target;

		target = this.getPosition().plus(dir);
		Raise.log(MessageType.DEBUG, "Trying to move to pos (%d, %d)\n", target.getX(), target.getY());
		object = getRoom().checkPos(target);
		if (object != null) {
			if (object.collideWith(this, dir)) {
				Raise.log(MessageType.WARNING, "collided with something\n");
				if(object instanceof Entity && object.getTotalWeigth(dir) < this.getMaxWeigth()) {
					Raise.log(MessageType.SUCCESS, "moved to pos (%d, %d)\n", target.getX(), target.getY());
					((Entity) object).move(dir);
					setPosition(target);
				}else {
					return false;
				}
			}else{
				Raise.log(MessageType.SUCCESS, "moved to pos (%d, %d)\n", target.getX(), target.getY());
				setPosition(target);
			}
		}else {
		Raise.log(MessageType.SUCCESS, "moved to pos (%d, %d)\n", target.getX(), target.getY());
		setPosition(target);
		}
		ImageGUI.getInstance().update();
		return true;
	}
	
	@Override
	public int getLayer() {
		return 1;
	}
	
	@Override
	public int getWeigth() {
		return ligth;
	}
	
	public int getMaxWeigth() {
		return 99;
	}
	
	
}
