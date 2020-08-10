package model.cards.spells;

import exceptions.InvalidTargetException;
import model.cards.*;
import model.cards.minions.Minion;
import model.heroes.Hero;

public class KillCommand extends Spell implements HeroTargetSpell,
		MinionTargetSpell {
	@Override
	public Object clone() throws CloneNotSupportedException {
		return new KillCommand();
	}

	public KillCommand() {
		super("Kill Command", 3, Rarity.COMMON);
	}

	@Override
	public void performAction(Minion m) throws InvalidTargetException {
		m.getDamage(5);
	}

	@Override
	public void performAction(Hero h) {
		h.getDamage(3);
	}
}
