package model.cards.spells;

<<<<<<< HEAD
import model.cards.minions.*;
import exceptions.*;
=======
import exceptions.InvalidTargetException;
import model.cards.minions.Minion;
>>>>>>> Full-Game

public interface MinionTargetSpell {
	public void performAction(Minion m) throws InvalidTargetException;
}
