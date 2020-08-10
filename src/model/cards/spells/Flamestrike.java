package model.cards.spells;

import java.util.ArrayList;

import model.cards.*;
import model.cards.minions.Minion;

public class Flamestrike extends Spell implements AOESpell {
	public Flamestrike() {
		super("Flamestrike", 7, Rarity.BASIC);
	}

	@Override
	public void performAction(ArrayList<Minion> oppField,
			ArrayList<Minion> curField) {
		ArrayList<Minion> clonedOppField = getClone(oppField);
		for (Minion m : clonedOppField) {
			if (m.isDivine())
				m.setDivine(false);
			else {
				m.getDamage(4);
			}
		}
	}

	public ArrayList<Minion> getClone(ArrayList<Minion> list) {
		ArrayList<Minion> clone = new ArrayList<Minion>();
		for (Minion m : list)
			clone.add(m);
		return clone;
	}

	@Override
	public Object clone() throws CloneNotSupportedException {
		return new Flamestrike();
	}

}
