<<<<<<< HEAD
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
=======
 package model.cards.spells;

import java.util.ArrayList;

import model.cards.Rarity;
import model.cards.minions.Minion;

public class MultiShot extends Spell implements AOESpell {

	public MultiShot() {
		super("Multi-Shot", 4, Rarity.BASIC);

	}

	@Override
	public void performAction(ArrayList<Minion> oppField, ArrayList<Minion> curField) {

		if (oppField.size() == 1)
			affectMinion(oppField.get(0));
		else if (oppField.size() > 1) {
			int r1 = 0;
			int r2 = 0;
			ArrayList<Minion> pool = new ArrayList<Minion>();
			pool.addAll(oppField);
			while (r1 == r2) {
				r1 = (int) (Math.random() * pool.size());
				r2 = (int) (Math.random() * pool.size());
			}
			affectMinion(pool.get(r1));
			affectMinion(pool.get(r2));
		}

	}

	private static void affectMinion(Minion m) {
		if (m.isDivine())
			m.setDivine(false);
		else
			m.setCurrentHP(m.getCurrentHP() - 3);
	}

>>>>>>> Full-Game
}
