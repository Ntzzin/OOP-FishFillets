package pt.iscte.poo.game;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import objects.GameObject;
import pt.iscte.poo.utils.Point2D;
import java.util.Scanner;
import java.util.Map;
import objects.*;

public class Room {
	
	private List<GameObject> objects;
	private String roomName;
	private GameEngine engine;
	private Point2D smallFishStartingPosition;
	private Point2D bigFishStartingPosition;
	private static Map<Character, GameObjectFactory> symbolMap = Map.ofEntries(
			Map.entry('B', (p,r) -> {BigFish.getInstance().setPosition(p); BigFish.getInstance().setRoom(r); return BigFish.getInstance();}),
			Map.entry('S', (p,r) -> {SmallFish.getInstance().setPosition(p); SmallFish.getInstance().setRoom(r); return SmallFish.getInstance();}),
		    Map.entry('W', (p,r) -> new Wall(p, r)),
		    Map.entry('H', (p,r) -> new SteelHorizontal(p, r)),
		    Map.entry('V', (p,r) -> new SteelVertical(p, r)),
		    Map.entry('C', (p,r) -> new Cup(p, r)),
		    Map.entry('R', (p,r) -> new Stone(p, r)),
		    Map.entry('A', (p,r) -> new Anchor(p, r)),
		    Map.entry('b', (p,r) -> new Bomb(p, r)),
		    Map.entry('T', (p,r) -> new Trap(p, r)),
		    Map.entry('Y', (p,r) -> new Trunk(p, r)),
		    Map.entry('X', (p,r) -> new HoledWall(p, r))
		);
	private	ArrayList<Updatable> updatableObjects;
	
	public Room() {
		objects = new ArrayList<GameObject>();
		updatableObjects = new ArrayList<Updatable>();
	}

	private void setName(String name) {
		roomName = name;
	}
	
	public String getName() {
		return roomName;
	}
	
	private void setEngine(GameEngine engine) {
		this.engine = engine;
	}

	public void addObject(GameObject obj) {
		synchronized (objects) {
	        objects.add(obj);
	    }
		engine.updateGUI();
	}
	
	public void removeObject(GameObject obj) {
		objects.remove(obj);
		engine.updateGUI();
	}
	
	private void addObjectX(char c, int x, int y) {
		GameObject			object;

		object = symbolMap.get(c).create(new Point2D(x, y), this);
		this.addObject(object);
	}
	
	public void addUpdatable(Updatable u) {
		updatableObjects.add(u);
	}
	
	public void update() {
		for (Updatable u : updatableObjects)
			u.onTick();
	}
	
	public GameObject checkPos(Point2D position) {
		for (GameObject object : objects)
	    	if (object.getPosition().equals(position) && object.getLayer() != 0)
	    		return object;
	    return null;
	}
	
	public List<GameObject> getObjects() {
		return objects;
	}

	public void setSmallFishStartingPosition(Point2D heroStartingPosition) {
		this.smallFishStartingPosition = heroStartingPosition;
	}
	
	public Point2D getSmallFishStartingPosition() {
		return smallFishStartingPosition;
	}
	
	public void setBigFishStartingPosition(Point2D heroStartingPosition) {
		this.bigFishStartingPosition = heroStartingPosition;
	}
	
	public Point2D getBigFishStartingPosition() {
		return bigFishStartingPosition;
	}
	
	public static Room readRoom(File f, GameEngine engine) {
		
		try {
			GameObject	water;
			Scanner 	scanner;
			String		line;
			Room 		r;
			int			x;
			int			y;

			r = new Room();
			r.setEngine(engine);
			r.setName(f.getName());
			
			scanner = new Scanner(f);
			y = 0;
			while (scanner.hasNext())
			{
				line = scanner.nextLine();
				for (x = 0; x < 10; x++) 
				{
					if (x < line.length() && line.charAt(x) != ' ')
						r.addObjectX(line.charAt(x), x, y);
					water = new Water(new Point2D(x, y), r);
					r.addObject(water);
				}
				y++;
			}
			scanner.close();
			return (r);

		}catch(FileNotFoundException e) {
			System.err.println("\u001B[41mError\u001B[0m: invalid level file: " + f.getName());
			return null;
		}
	}
	
}