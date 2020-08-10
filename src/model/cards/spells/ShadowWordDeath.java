package model.cards.spells;

import exceptions.InvalidTargetException;
import model.cards.Rarity;
import model.cards.minions.Minion;

public class ShadowWordDeath extends Spell implements MinionTargetSpell {
	public ShadowWordDeath() {
		super("Shadow Word: Death", 3, Rarity.BASIC);
	}

	@Override
	public void performAction(Minion m) throws InvalidTargetException {
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
}
