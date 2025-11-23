package objects;

import pt.iscte.poo.game.Room;
import pt.iscte.poo.gui.ImageGUI;
import pt.iscte.poo.utils.Point2D;
import pt.iscte.poo.utils.Vector2D;
import tests.MessageType;
import tests.Raise;

public abstract class Entity extends GameObject {

	public Entity(Room room) {
		super(room);
	}
	
	public Entity(Point2D position, Room room) {
		super(position, room);
	}

	public GameObject checkPos(Point2D position) {
		for (GameObject object : this.getRoom().getObjects())
			if (!object.equals(this) && object.getPosition().equals(position) && object.getLayer() != 0)
				return object;
		return null;
	}
	
	public void move(Vector2D dir) {
		GameObject	object;

		Raise.log(MessageType.DEBUG, "Trying to move to pos (%d, %d)\n", this.getPosition().plus(dir).getX(), this.getPosition().plus(dir).getY());
		object = checkPos(this.getPosition().plus(dir));
		if (object != null) {
			if (object.collideWith(this, dir)) {
				Raise.log(MessageType.DEBUG, "collided with something\n");
				if(checkPos(this.getPosition().plus(dir)) == null) {
					Raise.log(MessageType.DEBUG, "moved to pos (%d, %d)\n", this.getPosition().plus(dir).getX(), this.getPosition().plus(dir).getY());
					setPosition(this.getPosition().plus(dir));
				}
			}else{
				Raise.log(MessageType.DEBUG, "moved to pos (%d, %d)\n", this.getPosition().plus(dir).getX(), this.getPosition().plus(dir).getY());
				setPosition(this.getPosition().plus(dir));
			}
		}else {
		Raise.log(MessageType.DEBUG, "moved to pos (%d, %d)\n", this.getPosition().plus(dir).getX(), this.getPosition().plus(dir).getY());
		setPosition(this.getPosition().plus(dir));
		}
		ImageGUI.getInstance().update();
	}
	
	@Override
	public boolean collideWith(GameObject object, Vector2D dir) {
		Raise.log(MessageType.DEBUG, "Checking collision with %s resulted in %s\n", object.getName(), object.getLayer() >= this.getLayer());
		if (super.collideWith(object, dir))
			move(dir);
		return (super.collideWith(object, dir));
	}
	
	@Override
	public int getLayer() {
		return 1;
	}
	
}
