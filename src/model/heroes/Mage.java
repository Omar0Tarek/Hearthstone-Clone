package model.heroes;

<<<<<<< HEAD
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
=======
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

import view.PlayGround;
import view.components.*;
import exceptions.FullFieldException;
import exceptions.FullHandException;
import exceptions.HeroPowerAlreadyUsedException;
import exceptions.InvalidTargetException;
import exceptions.NotEnoughManaException;
import exceptions.NotYourTurnException;
import model.cards.Card;
import model.cards.Rarity;
import model.cards.minions.Minion;
import model.cards.spells.AOESpell;
import model.cards.spells.Flamestrike;
import model.cards.spells.HeroTargetSpell;
import model.cards.spells.MinionTargetSpell;
import model.cards.spells.Polymorph;
import model.cards.spells.Pyroblast;
import model.cards.spells.Spell;

public class Mage extends Hero {

	public Mage() throws IOException, CloneNotSupportedException {
		super("Jaina Proudmoore");
	}

	@Override
	public void buildDeck() throws IOException, CloneNotSupportedException {
		ArrayList<Minion> neutrals = getNeutralMinions(
				getAllNeutralMinions("neutral_minions.csv"), 13);
		getDeck().addAll(neutrals);
		for (int i = 0; i < 2; i++) {
			getDeck().add(new Polymorph());
			getDeck().add(new Flamestrike());
			getDeck().add(new Pyroblast());
		}
		Minion kalycgos = (new Minion("Kalycgos", 10, Rarity.LEGENDARY, 4, 12,
				false, false, false));
		getDeck().add(kalycgos);
		listenToMinions();
		Collections.shuffle(getDeck());
		for (Card card : getDeck()) {
			// System.out.println(card.getName() + " " +
			// PlayGround.paths.get(card.getName()));
			CardView cardView = new CardView(card, PlayGround.paths.get(card
					.getName()), PlayGround.paths.get(card.getName() + "_m"));
			cardView.setListener(this.ref);
			card.ref = cardView;
		}
>>>>>>> Full-Game
	}

	public void useHeroPower(Minion m) throws NotEnoughManaException,
			HeroPowerAlreadyUsedException, NotYourTurnException,
<<<<<<< HEAD
			FullHandException, FullFieldException, CloneNotSupportedException {
		super.useHeroPower();
		m.getDamage(1);
=======
			FullHandException, CloneNotSupportedException, FullFieldException {
		super.useHeroPower();
		if (m.isDivine())
			m.setDivine(false);
		else
			m.setCurrentHP(m.getCurrentHP() - 1);
>>>>>>> Full-Game
	}

	public void useHeroPower(Hero h) throws NotEnoughManaException,
			HeroPowerAlreadyUsedException, NotYourTurnException,
<<<<<<< HEAD
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
=======
			FullHandException, CloneNotSupportedException, FullFieldException {
		super.useHeroPower();
		h.setCurrentHP(h.getCurrentHP() - 1);
	}

	public void castSpell(AOESpell s, ArrayList<Minion> oppField)
			throws NotYourTurnException, NotEnoughManaException {
		if (fieldContains("Kalycgos")) {
			if (((Spell) s).getManaCost() - 4 > this.getCurrentManaCrystals())
				throw new NotEnoughManaException("I don't have enough Mana");
			((Spell) s).setManaCost(((Spell) s).getManaCost() - 4);
		}
		super.castSpell(s, oppField);
	}

	public void castSpell(MinionTargetSpell s, Minion m)
			throws NotYourTurnException, NotEnoughManaException,
			InvalidTargetException {
		if (fieldContains("Kalycgos")) {
			if (((Spell) s).getManaCost() - 4 > this.getCurrentManaCrystals())
				throw new NotEnoughManaException("I don't have enough Mana");
			((Spell) s).setManaCost(((Spell) s).getManaCost() - 4);
		}
		super.castSpell(s, m);
	}

	public void castSpell(HeroTargetSpell s, Hero h)
			throws NotYourTurnException, NotEnoughManaException {
		if (fieldContains("Kalycgos")) {
			if (((Spell) s).getManaCost() - 4 > this.getCurrentManaCrystals())
				throw new NotEnoughManaException("I don't have enough Mana");
			((Spell) s).setManaCost(((Spell) s).getManaCost() - 4);
		}
		super.castSpell(s, h);
	}

>>>>>>> Full-Game
}
