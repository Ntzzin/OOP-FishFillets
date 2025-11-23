package tests;

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
