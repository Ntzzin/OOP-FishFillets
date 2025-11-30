package tests;

import java.util.ArrayList;


/**
 * The Raise class purpose is to provide a simple and complete way of debugging one's code.
 */

public class Raise {

	/**
	 * Holds a list of hidden message types defined in {@link MessageType}
	 * any call to {@link log} method that receives a message type contained in this list
	 * will get ignored.
	 */
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
	
	/**
	 * Prints a message according to a {@link MessageType} and a format (similar to printf) , basically a fancy debug function.
	 * In addition to printing the desired message, it will also print from where that message comes.
	 * @param msgtype the message type.
	 * @param format the format which the printed message comes (printf style).
	 * @param args the set of arguments that that are required by the format. 
	 */
	public static void log(MessageType msgtype, String format , Object... args) {
		if (hidden.contains(msgtype))
			return;		
	    System.out.printf("%s %s: %s", color(msgtype.getHead(), msgtype.getCode() + 10), 
	    		color(getCaller(), msgtype.getCode()), String.format(format, args));
	}
	/**
	 * Hides one or more message types, for more info, see {@link #hidden}.
	 * @param args the message types to hide.
	 */
	public static void hide(MessageType... args) {
		if(args.length > MessageType.values().length)
			throw new IllegalArgumentException("Too many arguments (max "+ MessageType.values().length + ")");
		for (MessageType m : args)
			if (!hidden.contains(m))
				hidden.add(m);
	}
	
	/**
	 * Unhides one or more message types, for more info, see {@link #hidden}.
	 * @param agrs the message types to hide.
	 */
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
