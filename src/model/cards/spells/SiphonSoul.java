package model.cards.spells;

import model.cards.Rarity;
import model.cards.minions.Minion;

public class SiphonSoul extends Spell implements LeechingSpell {
	public SiphonSoul() {
		super("Siphon Soul", 6, Rarity.RARE);
	}

	@Override
	public int performAction(Minion m) {
		m.setDivine(false);
		m.getDamage(m.getCurrentHP());
		return 3;
	}

	@Override
	public Object clone() throws CloneNotSupportedException {
		// TODO Auto-generated method stub
		return new SiphonSoul();
	}
}
