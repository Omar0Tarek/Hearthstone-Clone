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
	

}
