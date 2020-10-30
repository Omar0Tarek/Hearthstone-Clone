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

public class Paladin extends Hero {

	public Paladin() throws Exception {
		super("Uther Lightbringer");
		this.buildDeck();
	}

	public void buildDeck() throws Exception {
		ArrayList<Card> tmp = new ArrayList<Card>();
		String filePath = "neutral_minions.csv";
		ArrayList<Minion> minions = super.getAllNeutralMinions(filePath);
		ArrayList<Minion> neutralMinions = super.getNeutralMinions(minions, 15);
		tmp.add(new SealOfChampions());
		tmp.add(new LevelUp());
		tmp.add(new SealOfChampions());
		tmp.add(new LevelUp());
		tmp.add(new Minion("Tirion Fordring", 4, Rarity.LEGENDARY, 6, 6, true,
				true, false));
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
	public void useHeroPower() throws NotEnoughManaException,
			HeroPowerAlreadyUsedException, NotYourTurnException,
			FullHandException, FullFieldException, CloneNotSupportedException {
		ArrayList<Minion> heroField = this.getField();
		super.useHeroPower();
		Minion minion = new Minion("Silver Hand Recruit",1,Rarity.BASIC,1,1,false,false,false);
		minion.setCurrentHP(1);
		minion.setListener(this);
		if(heroField.size() == 7) throw new FullFieldException();
		heroField.add(minion);

=======
import model.cards.Card;
import model.cards.Rarity;
import model.cards.minions.Minion;
import model.cards.spells.LevelUp;
import model.cards.spells.SealOfChampions;

public class Paladin extends Hero {
	public Paladin() throws IOException, CloneNotSupportedException {
		super("Uther Lightbringer");
	}

	public void useHeroPower() throws NotEnoughManaException,
			HeroPowerAlreadyUsedException, NotYourTurnException,
			FullHandException, CloneNotSupportedException, FullFieldException {
		if (getField().size() < 7) {
			super.useHeroPower();
			Minion silverHand = new Minion("Silver Hand Recruit", 1,
					Rarity.BASIC, 1, 1, false, false, false);
			CardView cardView = new CardView(silverHand, PlayGround.paths.get(silverHand
					.getName()), PlayGround.paths.get(silverHand.getName() + "_m"));
			cardView.setListener(this.ref);
			cardView.setPlayed(true);
			cardView.changeImage();
			silverHand.ref = cardView;
			silverHand.setListener(this);
			getField().add(silverHand);
			this.ref.getField().addCard(cardView);
		} else
			throw new FullFieldException("There is no place for this minion");
	}

	@Override
	public void buildDeck() throws IOException, CloneNotSupportedException {
		ArrayList<Minion> neutrals = getNeutralMinions(
				getAllNeutralMinions("neutral_minions.csv"), 15);
		getDeck().addAll(neutrals);
		for (int i = 0; i < 2; i++) {
			getDeck().add(new SealOfChampions());
			getDeck().add(new LevelUp());
		}
		Minion tirion = new Minion("Tirion Fordring", 4, Rarity.LEGENDARY, 6,
				6, true, true, false);
		getDeck().add(tirion);
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

}
