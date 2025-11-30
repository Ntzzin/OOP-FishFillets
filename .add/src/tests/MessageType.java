package tests;

/**
 * The MessageType enum purpose is to define a set of message types in order to
 * have a concrete way of displaying such messages, a direct support to {@link Raise}
 */

public enum MessageType {
	DEBUG(90, "[Debug]"), WARNING(33, "[Warning]"), ERROR(31, "[Error]"), SUCCESS(32, "[Success]"), DEFAULT(0, null);
	
	private final int ccode;
	private final String head;
	
	private MessageType(int ccode, String head) {
		this.ccode = ccode;
		this.head = head;
	}
	
	public int getCode() {
		return ccode;
	}
	
	public String getHead() {
		return head;
	}
	
}
