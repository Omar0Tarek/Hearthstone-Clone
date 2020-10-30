<<<<<<< HEAD
package model.cards.spells;
=======
	package model.cards.spells;
>>>>>>> Full-Game

import java.util.ArrayList;

import model.cards.Rarity;
import model.cards.minions.Minion;

public class CurseOfWeakness extends Spell implements AOESpell {
<<<<<<< HEAD
	
	public CurseOfWeakness() {
		super("Curse of Weakness", 2, Rarity.RARE);
	}

	@Override
	public void performAction(ArrayList<Minion> oppField, ArrayList<Minion> currField) {
		for(Minion m : oppField)
			m.setAttack(m.getAttack() - 2);
	}

	@Override
	public Object clone() throws CloneNotSupportedException {
		return new CurseOfWeakness();
	}	
	
=======

	public CurseOfWeakness() {
		super("Curse of Weakness", 2, Rarity.RARE);

	}

	@Override
	public void performAction(ArrayList<Minion> oppField, ArrayList<Minion> curField) {
		for (int i = 0; i < oppField.size(); i++) {
			oppField.get(i).setAttack(oppField.get(i).getAttack() - 2);
		}

	}

>>>>>>> Full-Game
}
