package model.cards.spells;

import exceptions.InvalidTargetException;
import model.cards.Rarity;
import model.cards.minions.Minion;

public class ShadowWordDeath extends Spell implements MinionTargetSpell {
<<<<<<< HEAD
	public ShadowWordDeath() {
		super("Shadow Word: Death", 3, Rarity.BASIC);
=======

	public ShadowWordDeath() {
		super("Shadow Word: Death", 3, Rarity.BASIC);

>>>>>>> Full-Game
	}

	@Override
	public void performAction(Minion m) throws InvalidTargetException {
<<<<<<< HEAD
		if(m.getAttack() >= 5) {
			m.setDivine(false);
			m.getDamage(m.getCurrentHP());
		}
	}

	@Override
	public Object clone() throws CloneNotSupportedException {
		// TODO Auto-generated method stub
		return new ShadowWordDeath();
	}
=======
		if (m.getAttack() < 5)
			throw new InvalidTargetException("Choose a minion with 5 or more attack");
		m.setCurrentHP(0);

	}

>>>>>>> Full-Game
}
