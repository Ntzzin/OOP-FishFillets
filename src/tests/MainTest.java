package tests;
import	pt.iscte.poo.game.*;
import	objects.*;


public class MainTest {
	
	public static boolean ver(GameObject go) {
		return go.getClass() == GameCharacter.class;
	}
	
	public static void main(String[] args) {
		Entity e = new BigFish(null);
		System.out.println(ver(e));
	}
}
