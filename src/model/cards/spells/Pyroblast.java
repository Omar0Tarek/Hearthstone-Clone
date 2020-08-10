package model.cards.spells;

import exceptions.InvalidTargetException;
import model.cards.*;
import model.cards.minions.Minion;
import model.heroes.Hero;

public class Pyroblast extends Spell implements MinionTargetSpell,
		HeroTargetSpell {
	public Pyroblast() {
		super("Pyroblast", 10, Rarity.EPIC);
	}

	@Override
	public void performAction(Hero h) {
		h.getDamage(10);	
	}

	@Override
	public void performAction(Minion m) throws InvalidTargetException {
		m.getDamage(10);
	}

	@Override
	public Object clone() throws CloneNotSupportedException {
		// TODO Auto-generated method stub
		return new Pyroblast();
	}

}
