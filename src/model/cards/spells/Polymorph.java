package model.cards.spells;

<<<<<<< HEAD
import exceptions.InvalidTargetException;
import model.cards.*;
import model.cards.minions.Minion;

public class Polymorph extends Spell implements MinionTargetSpell {
=======
import model.cards.Rarity;
import model.cards.minions.Minion;

public class Polymorph extends Spell implements MinionTargetSpell {

>>>>>>> Full-Game
	public Polymorph() {
		super("Polymorph", 4, Rarity.BASIC);
	}

	@Override
<<<<<<< HEAD
	public void performAction(Minion m) throws InvalidTargetException {
		// TODO Auto-generated method stub
		m.setName("Sheep");
		m.setMaxHP(1);
		m.setCurrentHP(1);
		m.setAttack(1);
		m.setManaCost(1);
		m.setDivine(false);
		m.setTaunt(false);
		m.setSleeping(true);
	}

	@Override
	public Object clone() throws CloneNotSupportedException {
		// TODO Auto-generated method stub
		return new Polymorph();
	}

=======
	public void performAction(Minion m) {
		m.setCurrentHP(1);
		m.setMaxHP(1);
		m.setAttack(1);
		m.setName("Sheep");
		m.setSleeping(true);
		m.setTaunt(false);
		m.setDivine(false);
		m.setManaCost(1);

	}
>>>>>>> Full-Game
}
