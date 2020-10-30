package model.heroes;

<<<<<<< HEAD

import java.util.*;

=======
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

import view.PlayGround;
import view.components.CardView;
>>>>>>> Full-Game
import exceptions.FullFieldException;
import exceptions.FullHandException;
import exceptions.HeroPowerAlreadyUsedException;
import exceptions.NotEnoughManaException;
import exceptions.NotYourTurnException;
<<<<<<< HEAD
import model.cards.*;
import model.cards.minions.*;
import model.cards.spells.*;

public class Warlock extends Hero {
	public Warlock () throws Exception{
		super("Gul\'dan");
		this.buildDeck();
	}
	
	public void buildDeck() throws Exception{
		ArrayList<Card> tmp = new ArrayList<Card>();
		String filePath = "neutral_minions.csv";
		ArrayList<Minion> minions = super.getAllNeutralMinions(filePath);
		ArrayList<Minion> neutralMinions = super.getNeutralMinions(minions, 13);
		tmp.add(new CurseOfWeakness());
		tmp.add(new SiphonSoul());
		tmp.add(new TwistingNether());
		tmp.add(new CurseOfWeakness());
		tmp.add(new SiphonSoul());
		tmp.add(new TwistingNether());
		tmp.add(new Minion("Wilfred Fizzlebang",6,Rarity.LEGENDARY,4,4,false,false,false));
		tmp.addAll(neutralMinions);
		Collections.shuffle(tmp);
		ArrayList<Card> deck = super.getDeck();
		for(int i=0;i<20;i++) {
			Card c = tmp.get(i);
			if(c instanceof Minion){
				Minion m = (Minion) c;
				m.setListener(this);
			}
			deck.add(c);
		}
	}
	
	@Override
	public void useHeroPower()throws NotEnoughManaException, HeroPowerAlreadyUsedException,
	NotYourTurnException, FullHandException, FullFieldException,
	CloneNotSupportedException{
		super.useHeroPower();
		super.justUseHeroPower = true;
		super.drawCard();
		super.justUseHeroPower = false;
		super.setCurrentHP(super.getCurrentHP() - 2);
	}
	
=======
import model.cards.Card;
import model.cards.Rarity;
import model.cards.minions.Minion;
import model.cards.spells.CurseOfWeakness;
import model.cards.spells.SiphonSoul;
import model.cards.spells.TwistingNether;

public class Warlock extends Hero {

	public Warlock() throws IOException, CloneNotSupportedException {
		super("Gul'dan");
	}

	@Override
	public void buildDeck() throws IOException, CloneNotSupportedException {
		ArrayList<Minion> neutrals = getNeutralMinions(
				getAllNeutralMinions("neutral_minions.csv"), 13);
		getDeck().addAll(neutrals);
		for (int i = 0; i < 2; i++) {
			getDeck().add(new CurseOfWeakness());
			getDeck().add(new SiphonSoul());
			getDeck().add(new TwistingNether());
		}
		Minion wilfred = new Minion("Wilfred Fizzlebang", 6, Rarity.LEGENDARY,
				4, 4, false, false, false);
		getDeck().add(wilfred);
		listenToMinions();
		Collections.shuffle(getDeck());
		for (Card card : getDeck()) {
			CardView cardView = new CardView(card, PlayGround.paths.get(card
					.getName()), PlayGround.paths.get(card.getName() + "_m"));
			cardView.setListener(this.ref);
			card.ref = cardView;
		}
	}

	public void useHeroPower() throws NotEnoughManaException,
			HeroPowerAlreadyUsedException, NotYourTurnException,
			FullHandException, CloneNotSupportedException, FullFieldException {
		super.useHeroPower();
		setCurrentHP(getCurrentHP() - 2);
		Card c = drawCard();
		if (fieldContains("Wilfred Fizzlebang") && c instanceof Minion) {
			c.setManaCost(0);
			if (fieldContains("Chromaggus"))
				getHand().get(getHand().size() - 1).setManaCost(0);
		}

	}
>>>>>>> Full-Game

}
