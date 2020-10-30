package model.heroes;

<<<<<<< HEAD
import exceptions.*;

public interface HeroListener {
	
	public void onHeroDeath();
	public void damageOpponent(int amount);
=======
import exceptions.FullHandException;

public interface HeroListener {
	public void onHeroDeath();

	public void damageOpponent(int amount);

>>>>>>> Full-Game
	public void endTurn() throws FullHandException, CloneNotSupportedException;

}
