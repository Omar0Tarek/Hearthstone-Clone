package exceptions;

@SuppressWarnings("serial")
public abstract class HearthstoneException extends Exception {

	public HearthstoneException() {
		super("HearthstoneException");
	}

	public HearthstoneException(String s) {
		super(s);
	}
}
