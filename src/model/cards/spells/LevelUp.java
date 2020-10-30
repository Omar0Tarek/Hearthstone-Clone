package model.cards.spells;

import java.util.ArrayList;

<<<<<<< HEAD
import model.cards.*;
import model.cards.minions.Minion;

public class LevelUp extends Spell implements FieldSpell {
	public LevelUp() {
		super("Level Up!", 6, Rarity.EPIC);
=======
import model.cards.Rarity;
import model.cards.minions.Minion;

public class LevelUp extends Spell implements FieldSpell {

	public LevelUp() {
		super("Level Up!", 6, Rarity.EPIC);

>>>>>>> Full-Game
	}

	@Override
	public void performAction(ArrayList<Minion> field) {
<<<<<<< HEAD
		for(Minion m: field){
			if(m.getName().equals("Silver Hand Recruit")){
=======
		for (int i = 0; i < field.size(); i++) {
			Minion m = field.get(i);
			if (m.getName().equals("Silver Hand Recruit")) {
>>>>>>> Full-Game
				m.setAttack(m.getAttack() + 1);
				m.setMaxHP(m.getMaxHP() + 1);
				m.setCurrentHP(m.getCurrentHP() + 1);
			}
		}
<<<<<<< HEAD
	}

	@Override
	public  Object clone() throws CloneNotSupportedException {
		// TODO Auto-generated method stub
		return new LevelUp();
	}
=======

	}

>>>>>>> Full-Game
}
