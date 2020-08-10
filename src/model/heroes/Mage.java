package model.heroes;

import java.util.*;

import exceptions.FullFieldException;
import exceptions.FullHandException;
import exceptions.HeroPowerAlreadyUsedException;
import exceptions.NotEnoughManaException;
import exceptions.NotYourTurnException;
import model.cards.*;
import model.cards.minions.*;
import model.cards.spells.*;

public class Mage extends Hero {
	public Mage() throws Exception {
		super("Jaina Proudmoore");
		this.buildDeck();
	}

	public void useHeroPower(Minion m) throws NotEnoughManaException,
			HeroPowerAlreadyUsedException, NotYourTurnException,
			FullHandException, FullFieldException, CloneNotSupportedException {
		super.useHeroPower();
		m.getDamage(1);
	}

	public void useHeroPower(Hero h) throws NotEnoughManaException,
			HeroPowerAlreadyUsedException, NotYourTurnException,
			FullHandException, FullFieldException, CloneNotSupportedException {
		super.useHeroPower();
		this.getListener().damageOpponent(1);
	}

	public void buildDeck() throws Exception {
		ArrayList<Card> tmp = new ArrayList<Card>();
		String filePath = "neutral_minions.csv";
		ArrayList<Minion> minions = super.getAllNeutralMinions(filePath);
		ArrayList<Minion> neutralMinions = super.getNeutralMinions(minions, 13);
		tmp.add(new Polymorph());
		tmp.add(new Flamestrike());
		tmp.add(new Pyroblast());
		tmp.add(new Polymorph());
		tmp.add(new Flamestrike());
		tmp.add(new Pyroblast());
		tmp.add(new Minion("Kalycgos", 10, Rarity.LEGENDARY, 4, 12, false,
				false, false));
		tmp.addAll(neutralMinions);
		Collections.shuffle(tmp);
		ArrayList<Card> deck = super.getDeck();
		for (int i = 0; i < 20; i++) {
			Card c = tmp.get(i);
			if (c instanceof Minion) {
				Minion m = (Minion) c;
				m.setListener(this);
			}
			deck.add(c);
		}
	}
}
