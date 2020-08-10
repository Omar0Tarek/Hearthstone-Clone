package model.cards.spells;

import exceptions.InvalidTargetException;
import model.cards.*;
import model.cards.minions.Minion;

public class Polymorph extends Spell implements MinionTargetSpell {
	public Polymorph() {
		super("Polymorph", 4, Rarity.BASIC);
	}

	@Override
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

}
