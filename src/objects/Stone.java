package objects;

import pt.iscte.poo.game.Room;
import pt.iscte.poo.gui.ImageGUI;
import pt.iscte.poo.utils.Point2D;
import pt.iscte.poo.utils.Vector2D;
import tests.MessageType;
import tests.Raise;

public class Stone extends Entity {

	public Stone(Room room) {
		super(room);
	}
	
	public Stone(Point2D position, Room room) {
		super(position, room);
	}

	public void move(Vector2D dir) {
		super.move(dir);
		//Raise.log(MessageType.DEBUG, "object beneath is %s\n", (object != null) ? object.getName() : "(null)");
		new Thread(() -> {
			GameObject	object;
			object = super.checkPos(this.getPosition().plus(new Vector2D(0, 1)));
			while (object == null) {
			Raise.log(MessageType.DEBUG, "Gravity!!\n");
			for (int i = 0; i < 1000000000; i++);
			super.move(new Vector2D(0, 1));
			object = super.checkPos(this.getPosition().plus(new Vector2D(0, 1)));
		}}).start();
	}

	@Override
	public String getName() {
		return "stone";
	}

	@Override
	public int getLayer() {
		return 1;
	}

}
