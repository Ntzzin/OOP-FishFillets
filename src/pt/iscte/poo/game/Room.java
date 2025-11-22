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
	private static Map<Character, GameObjectFactory> symbolMap = Map.of(
		    'B', (r) -> BigFish.getInstance(),
		    'S', (r) -> SmallFish.getInstance(),
		    'W', (r) -> new Wall(r),
		    'H', (r) -> new SteelHorizontal(r),
		    'V', (r) -> new SteelVertical(r),
		    'C', (r) -> new Cup(r),
		    'R', (r) -> new Stone(r)
		);
	
	public Room() {
		objects = new ArrayList<GameObject>();
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
		objects.add(obj);
		engine.updateGUI();
	}
	
	public void removeObject(GameObject obj) {
		objects.remove(obj);
		engine.updateGUI();
	}
	
	private void addObjectX(char c, int x, int y) {
		GameObject			object;

		object = symbolMap.get(c).create(this);
		object.setPosition(x, y);
		this.addObject(object);
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
					water = new Water(r);
					water.setPosition(x, y);
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