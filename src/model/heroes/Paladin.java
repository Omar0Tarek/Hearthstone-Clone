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

	}

}
