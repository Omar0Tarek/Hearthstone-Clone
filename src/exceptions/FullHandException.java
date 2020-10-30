package exceptions;

<<<<<<< HEAD
import model.cards.*;

@SuppressWarnings("serial")
public class FullHandException extends HearthstoneException {

	private Card burned;

	public FullHandException(Card b) {
		super();
		this.burned = b;
	}

	public FullHandException(String s, Card b) {
		super(s);
		this.burned = b;
	}
=======
import model.cards.Card;

@SuppressWarnings("serial")
public class FullHandException extends HearthstoneException {
	private Card burned;
	public FullHandException(Card b) {
		super();

	}

	public FullHandException(String message, Card b) {
		super(message);
		burned=b;
	}

	public Card getBurned() {
		return burned;
	}

>>>>>>> Full-Game
}
