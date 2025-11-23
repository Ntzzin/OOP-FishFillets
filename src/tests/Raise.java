package tests;

import java.util.ArrayList;

public class Raise {
	
	private static ArrayList<MessageType> hidden = new ArrayList<MessageType>();
	
	private	static String getColor(int code) {
		return ("\u001B[" + code + "m");
	}
	
	private static String color(String str, int code) {
		return (getColor(code) + str + getColor(MessageType.DEFAULT.getCode()));
	}
	
	private static String getCaller() {
		return(Thread.currentThread().getStackTrace()[3].getClassName() + "." + Thread.currentThread().getStackTrace()[3].getMethodName() + "()");
	}
	
	public static void log(MessageType msgtype, String format , Object... args) {
		if (hidden.contains(msgtype))
			return;		
	    System.out.printf("%s %s: %s", color(msgtype.getHead(), msgtype.getCode() + 10), 
	    		color(getCaller(), msgtype.getCode()), String.format(format, args));
	}
	
	public static void hide(MessageType... args) {
		if(args.length > MessageType.values().length)
			throw new IllegalArgumentException("Too many arguments (max "+ MessageType.values().length + ")");
		for (MessageType m : args)
			if (!hidden.contains(m))
				hidden.add(m);
	}
	
	public static void unhide(MessageType... args) {
		if(args.length > MessageType.values().length)
			throw new IllegalArgumentException("Too many arguments (max "+ MessageType.values().length + ")");
		for (MessageType m : args)
			if (hidden.contains(m))
				hidden.remove(m);
	}
	
	public static void hideAll() {
		hide(MessageType.values());
	}
	
	public static void unhideAll() {
		unhide(MessageType.values());
	}
}
