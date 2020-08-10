package model.cards.spells;

import java.util.ArrayList;

import model.cards.*;
import model.cards.minions.Minion;

public class HolyNova extends Spell implements AOESpell {
	public HolyNova() {
		super("Holy Nova", 5, Rarity.BASIC);
	}

	@Override
	public Object clone() throws CloneNotSupportedException {
		return new HolyNova();
	}

	@Override
	public void performAction(ArrayList<Minion> oppField,
			ArrayList<Minion> curField) {
		ArrayList<Minion> clonedOppField = getClone(oppField);
		for (Minion m : clonedOppField) 
			m.getDamage(2);
		for (Minion m : curField)
			m.setCurrentHP(m.getCurrentHP()+2);
	}
	
	public ArrayList<Minion> getClone (ArrayList<Minion> list){
		ArrayList<Minion> clone = new ArrayList<Minion>();
		for(Minion m : list) clone.add(m);
		return clone;
	}

}
