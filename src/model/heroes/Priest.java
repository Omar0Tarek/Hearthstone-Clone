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





