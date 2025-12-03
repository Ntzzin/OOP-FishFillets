package pt.iscte.poo.game;

import java.awt.event.KeyEvent;
import java.io.File;
import java.util.HashMap;
import java.util.Map;

import objects.SmallFish;
import objects.BigFish;
import objects.GameCharacter;
import objects.GameObject;
import pt.iscte.poo.gui.ImageGUI;
import pt.iscte.poo.observer.Observed;
import pt.iscte.poo.observer.Observer;
import pt.iscte.poo.utils.Direction;
import tests.MessageType;
import tests.Raise;

public class GameEngine implements Observer {
	
	private Map<String,Room> rooms;
	private Room currentRoom;
	private int lastTickProcessed = 0;
	private GameCharacter currentCharacter;
	
	public GameEngine() {
		currentCharacter = BigFish.getInstance();
		rooms = new HashMap<String,Room>();
		loadAll();
		loadRoom("room0.txt");
	}

	private void loadAll() {
		File[] files = new File("./rooms").listFiles();
		for(File f : files) {
			rooms.put(f.getName(),Room.readRoom(f,this));
		}
	}
	
	private void loadFile(String name) {
		File[] files = new File("./rooms").listFiles();
		for(File f : files)
			if (f.getName().equals(name))
				rooms.put(f.getName(),Room.readRoom(f,this));
	}
	
	protected void loadRoom(String name) {
		Raise.log(MessageType.DEBUG, "Loading %s...\n", name);
		loadFile(name);
		currentRoom = rooms.get("room0.txt");
		updateGUI();
		SmallFish.getInstance().setRoom(currentRoom);
		BigFish.getInstance().setRoom(currentRoom);
		Raise.log(MessageType.SUCCESS, "Loaded %s\n", name);
	}

	@Override
	public void update(Observed source) {
		if (ImageGUI.getInstance().wasKeyPressed()) {
			int k = ImageGUI.getInstance().keyPressed();
			if (k == KeyEvent.VK_SPACE)
				currentCharacter = GameCharacter.getNext();
			else if (Direction.isDirection(k))
				currentCharacter.move(Direction.directionFor(k).asVector());

		}
		int t = ImageGUI.getInstance().getTicks();
		while (lastTickProcessed < t) {
			processTick();
		}
		ImageGUI.getInstance().update();
	}

	private void processTick() {		
		currentRoom.update();
		lastTickProcessed++;
	}

	public void updateGUI() {
		if(currentRoom!=null) {
			ImageGUI.getInstance().clearImages();
			ImageGUI.getInstance().addImages(currentRoom.getObjects());
		}
	}
	
}
