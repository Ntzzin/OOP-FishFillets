package objects;

import pt.iscte.poo.game.Room;
import pt.iscte.poo.gui.ImageGUI;
import pt.iscte.poo.gui.ImageTile;
import pt.iscte.poo.utils.Direction;
import pt.iscte.poo.utils.Point2D;
import pt.iscte.poo.utils.Vector2D;
import tests.MessageType;
import tests.Raise;

public abstract class GameObject implements ImageTile{
	
	private Point2D position;
	private Room room;

	public GameObject(Room room) {
		this.room = room;
	}
	
	public GameObject(Point2D position, Room room) {
		this.position = position;
		this.room = room;
	}

	public void setPosition(int i, int j) {
		position = new Point2D(i, j);
	}
	
	public void setPosition(Point2D position) {
		this.position = position;
	}

	@Override
	public Point2D getPosition() {
		return position;
	}
	
	public Room getRoom() {
		return room;
	}
	
	public void setRoom(Room room) {
		this.room = room;
	}

	public boolean collideWith(GameObject object, Vector2D dir) {
		Raise.log(MessageType.DEBUG, "Checking collision with %s resulted in %s\n", object.getName(), object.getLayer() <= this.getLayer());
		return object.getLayer() <= this.getLayer();
	}
	
	public int getWeigth() {
		return 0;
	}
	
	public int getTotalWeigth(Vector2D dir) {
		GameObject	o;
		int			res;

		res = this.getWeigth();
		o = getRoom().checkPos(this.getPosition().plus(Direction.UP.asVector()));
		if (o != null)
			res += o.getTotalWeigth(dir);
		o = getRoom().checkPos(this.getPosition().plus(dir));
		if (o != null)
			res += o.getTotalWeigth(dir);
		return res;
	}
}
