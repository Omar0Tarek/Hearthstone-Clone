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

public class Hunter extends Hero {
	public Hunter() throws Exception {
		super("Rexxar");
		this.buildDeck();
	}
	
	@Override
	public void useHeroPower(Hero target) throws NotEnoughManaException,
			HeroPowerAlreadyUsedException, NotYourTurnException,
			FullHandException, FullFieldException, CloneNotSupportedException {
		super.useHeroPower(target);
		target.setCurrentHP(target.getCurrentHP() - 2);
	}
	
	public void useHeroPower() throws NotEnoughManaException, HeroPowerAlreadyUsedException, NotYourTurnException, FullHandException, FullFieldException, CloneNotSupportedException {
		super.useHeroPower();
		this.getListener().damageOpponent(2);
	}

	public void buildDeck() throws Exception{
		ArrayList<Card> tmp = new ArrayList<Card>();
		String filePath = "neutral_minions.csv";
		ArrayList<Minion> minions = super.getAllNeutralMinions(filePath);
		ArrayList<Minion> neutralMinions = super.getNeutralMinions(minions, 15);
		tmp.add(new KillCommand());
		tmp.add(new KillCommand());
		tmp.add(new MultiShot());
		tmp.add(new MultiShot());
		tmp.add(new Minion("King Krush",9,Rarity.LEGENDARY,8,8,false,false,true));
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

}
