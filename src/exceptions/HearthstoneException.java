package exceptions;

@SuppressWarnings("serial")
public abstract class HearthstoneException extends Exception {
<<<<<<< HEAD

	public HearthstoneException() {
		super("HearthstoneException");
	}

	public HearthstoneException(String s) {
		super(s);
=======
	public HearthstoneException() {
		super();
	}

	public HearthstoneException(String message)

	{
		super(message);
>>>>>>> Full-Game
	}
}
