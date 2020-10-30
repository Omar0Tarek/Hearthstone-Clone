package model.cards.spells;

import java.util.ArrayList;

<<<<<<< HEAD
import model.cards.*;
import model.cards.minions.Minion;

public class Flamestrike extends Spell implements AOESpell {
=======
import model.cards.Rarity;
import model.cards.minions.Minion;

public class Flamestrike extends Spell implements AOESpell {

>>>>>>> Full-Game
	public Flamestrike() {
		super("Flamestrike", 7, Rarity.BASIC);
	}

<<<<<<< HEAD
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
=======
	public void performAction(ArrayList<Minion> oppField, ArrayList<Minion> curField) {

		for (int i = 0; i < oppField.size(); i++) {
			Minion m = oppField.get(i);
			if (m.isDivine())
				m.setDivine(false);
			else {
				m.setCurrentHP(oppField.get(i).getCurrentHP() - 4);
				if (m.getCurrentHP() == 0)
					i--;
			}
		}

>>>>>>> Full-Game
	}

}
