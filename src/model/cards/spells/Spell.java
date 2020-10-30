package model.cards.spells;

<<<<<<< HEAD
import model.cards.*;

public abstract class Spell extends Card {
	
	public Spell() {}
	
	public Spell(String name, int manaCost ,Rarity rarity) {
		super(name, manaCost, rarity);
	}

	@Override
	public Object clone() throws CloneNotSupportedException {
		return super.clone();
	}
	
=======
import model.cards.Card;
import model.cards.Rarity;

public abstract class Spell extends Card {

	public Spell(String n, int m, Rarity r) {
		super(n, m, r);
	}

>>>>>>> Full-Game
}
