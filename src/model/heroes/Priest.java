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

public class Priest extends Hero {

	public Priest() throws Exception {
		super("Anduin Wrynn");
		this.buildDeck();
	}

	public void buildDeck() throws Exception {
		ArrayList<Card> tmp = new ArrayList<Card>();
		String filePath = "neutral_minions.csv";
		ArrayList<Minion> minions = super.getAllNeutralMinions(filePath);
		ArrayList<Minion> neutralMinions = super.getNeutralMinions(minions, 13);
		tmp.add(new DivineSpirit());
		tmp.add(new HolyNova());
		tmp.add(new ShadowWordDeath());
		tmp.add(new DivineSpirit());
		tmp.add(new HolyNova());
		tmp.add(new ShadowWordDeath());
		tmp.add(new Minion("Prophet Velen", 7, Rarity.LEGENDARY, 7, 7, false,
				false, false));
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
	
	public int checkForPV(){
		for(Minion m : this.getField()){
			if(m.getName().equals("Prophet Velen")) return 6;
		}
		return 0;
	}

	@Override
	public void useHeroPower(Hero target) throws NotEnoughManaException,
			HeroPowerAlreadyUsedException, NotYourTurnException,
			FullHandException, FullFieldException, CloneNotSupportedException {
		super.useHeroPower(target);
		int restoredHP = 2 + checkForPV();
		target.setCurrentHP(target.getCurrentHP() + restoredHP);
	}

	@Override
	public void useHeroPower(Minion target) throws NotEnoughManaException,
			HeroPowerAlreadyUsedException, NotYourTurnException,
			FullHandException, FullFieldException, CloneNotSupportedException {
		super.useHeroPower(target);
		int restoredHP = 2 + checkForPV();
		target.setCurrentHP(target.getCurrentHP() + restoredHP);
	}
}





=======
import model.cards.Card;
import model.cards.Rarity;
import model.cards.minions.Minion;
import model.cards.spells.DivineSpirit;
import model.cards.spells.HolyNova;
import model.cards.spells.ShadowWordDeath;

public class Priest extends Hero {

	public Priest() throws IOException, CloneNotSupportedException {
		super("Anduin Wrynn");
	}

	@Override
	public void buildDeck() throws IOException, CloneNotSupportedException {
		ArrayList<Minion> neutrals = getNeutralMinions(
				getAllNeutralMinions("neutral_minions.csv"), 13);
		getDeck().addAll(neutrals);
		for (int i = 0; i < 2; i++) {
			getDeck().add(new DivineSpirit());
			getDeck().add(new HolyNova());
			getDeck().add(new ShadowWordDeath());
		}
		Minion velen = new Minion("Prophet Velen", 7, Rarity.LEGENDARY, 7, 7,
				false, false, false);
		getDeck().add(velen);
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

	}

	public void useHeroPower(Minion m) throws NotEnoughManaException,
			HeroPowerAlreadyUsedException, NotYourTurnException,
			FullHandException, CloneNotSupportedException, FullFieldException {
		super.useHeroPower();
		if (fieldContains("Prophet Velen"))
			m.setCurrentHP(m.getCurrentHP() + 8);
		else
			m.setCurrentHP(m.getCurrentHP() + 2);
	}

	public void useHeroPower(Hero h) throws NotEnoughManaException,
			HeroPowerAlreadyUsedException, NotYourTurnException,
			FullHandException, CloneNotSupportedException, FullFieldException {
		super.useHeroPower();
		if (fieldContains("Prophet Velen"))
			h.setCurrentHP(h.getCurrentHP() + 8);
		else
			h.setCurrentHP(h.getCurrentHP() + 2);
	}

}
>>>>>>> Full-Game
