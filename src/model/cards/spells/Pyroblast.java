package model.cards.spells;

<<<<<<< HEAD
import exceptions.InvalidTargetException;
import model.cards.*;
import model.cards.minions.Minion;
import model.heroes.Hero;

public class Pyroblast extends Spell implements MinionTargetSpell,
		HeroTargetSpell {
=======
import model.cards.Rarity;
import model.cards.minions.Minion;
import model.heroes.Hero;

public class Pyroblast extends Spell implements HeroTargetSpell, MinionTargetSpell {
>>>>>>> Full-Game
	public Pyroblast() {
		super("Pyroblast", 10, Rarity.EPIC);
	}

	@Override
<<<<<<< HEAD
	public void performAction(Hero h) {
		h.getDamage(10);	
	}

	@Override
	public void performAction(Minion m) throws InvalidTargetException {
		m.getDamage(10);
	}

	@Override
	public Object clone() throws CloneNotSupportedException {
		// TODO Auto-generated method stub
		return new Pyroblast();
=======
	public void performAction(Minion m) {
		if (m.isDivine())
			m.setDivine(false);
		else
			m.setCurrentHP(m.getCurrentHP() - 10);
		System.out.println("done");

	}

	@Override
	public void performAction(Hero h) {
		h.setCurrentHP(h.getCurrentHP() - 10);

>>>>>>> Full-Game
	}

}
