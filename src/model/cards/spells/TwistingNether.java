package model.cards.spells;

import java.util.ArrayList;

import model.cards.Rarity;
import model.cards.minions.Minion;

public class TwistingNether extends Spell implements AOESpell {
<<<<<<< HEAD
	public TwistingNether() {
		super("Twisting Nether", 8, Rarity.EPIC);
	}

	@Override
	public void performAction(ArrayList<Minion> oppField,
			ArrayList<Minion> curField) {
		ArrayList<Minion> diedMinions = new ArrayList<Minion>();
		for (Minion m : oppField) diedMinions.add(m);
		for (Minion m : curField) diedMinions.add(m);
		for (Minion m : diedMinions) m.die();

	}

	@Override
	public Object clone() throws CloneNotSupportedException {
		// TODO Auto-generated method stub
		return new TwistingNether();
	}
=======

	public TwistingNether() {
		super("Twisting Nether", 8, Rarity.EPIC);

	}

	@Override
	public void performAction(ArrayList<Minion> oppField, ArrayList<Minion> curField) {
		while (!oppField.isEmpty()) {

			oppField.get(0).setCurrentHP(0);

		}
		while (!curField.isEmpty()) {

			curField.get(0).setCurrentHP(0);

		}

	}

>>>>>>> Full-Game
}
