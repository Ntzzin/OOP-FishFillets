package objects;

import pt.iscte.poo.game.Room;
import pt.iscte.poo.gui.ImageGUI;
import pt.iscte.poo.utils.Direction;
import pt.iscte.poo.utils.Point2D;
import pt.iscte.poo.utils.Vector2D;
import tests.MessageType;
import tests.Raise;

public class Stone extends GravityAffected {
	
	private boolean hasSpawnedKrab;

	public Stone(Point2D position, Room room) {
		super(position, room);
		hasSpawnedKrab = false;
	}

	@Override
	public String getName() {
		return "stone";
	}
	
	@Override
	public int getWeigth() {
		return heavy;
	}
	
	@Override
	public boolean move(Vector2D dir) {
		boolean	moved;

		moved = super.move(dir);
		if (moved && dir.getY() == 0 && !hasSpawnedKrab)
			if (checkPos(this.getPosition().plus(Direction.UP.asVector())) == null) {
				Raise.log(MessageType.DEBUG, "Created a Krab!!\n");
				this.getRoom().addObject(new Krab(this.getPosition().plus(Direction.UP.asVector()), this.getRoom()));
				hasSpawnedKrab = true;
			}
		return moved;
	}

}
