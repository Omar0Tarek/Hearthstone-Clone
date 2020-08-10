package model.cards.spells;

import java.util.*;
import model.cards.*;
import model.cards.minions.Minion;

public class MultiShot extends Spell implements AOESpell {
	public MultiShot() {
		super("Multi-Shot", 4, Rarity.BASIC);
	}

	@Override
	public void performAction(ArrayList<Minion> oppField,
			ArrayList<Minion> curField) {
		int size = oppField.size();
		if (size == 0)
			return;
		if (size == 1) {
			oppField.get(0).getDamage(3);
		} else {
			ArrayList<Integer> random = new ArrayList<Integer>();
			for(int i=0;i<oppField.size();i++) random.add(i);
			Collections.shuffle(random);
			ArrayList<Minion> clonedOppField = getClone(oppField);
			for(int i=0;i<2;i++){
				Minion m = clonedOppField.get(random.get(i));
				m.getDamage(3);
			}
			random.clear();
		}
	}
	
	private ArrayList<Minion> getClone (ArrayList<Minion> l){
		ArrayList<Minion> res = new ArrayList<Minion>();
		for(Minion m : l) res.add(m);
		return res;
	}

	@Override
	public Object clone() throws CloneNotSupportedException {
		// TODO Auto-generated method stub
		return new MultiShot();
	}
}
