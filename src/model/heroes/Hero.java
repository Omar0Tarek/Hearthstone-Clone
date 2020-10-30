package model.heroes;

<<<<<<< HEAD
import java.util.*;
import java.io.*;

import model.cards.*;
import model.cards.minions.*;
import model.cards.spells.*;
import engine.*;
import exceptions.*;
=======
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import view.PlayGround;
import view.components.CardView;
import view.components.HeroView;
import engine.ActionValidator;
import exceptions.CannotAttackException;
import exceptions.FullFieldException;
import exceptions.FullHandException;
import exceptions.HeroPowerAlreadyUsedException;
import exceptions.InvalidTargetException;
import exceptions.NotEnoughManaException;
import exceptions.NotSummonedException;
import exceptions.NotYourTurnException;
import exceptions.TauntBypassException;
import model.cards.Card;
import model.cards.Rarity;
import model.cards.minions.Icehowl;
import model.cards.minions.Minion;
import model.cards.minions.MinionListener;
import model.cards.spells.AOESpell;
import model.cards.spells.FieldSpell;
import model.cards.spells.HeroTargetSpell;
import model.cards.spells.LeechingSpell;
import model.cards.spells.MinionTargetSpell;
import model.cards.spells.Spell;
>>>>>>> Full-Game

public abstract class Hero implements MinionListener {
	private String name;
	private int currentHP;
<<<<<<< HEAD

	private boolean heroPowerUsed;
	private int totalManaCrystals;
	private int currentManaCrystals;
	private HeroListener listener;
	private ActionValidator validator;
=======
	private boolean heroPowerUsed;
	private int totalManaCrystals;
	private int currentManaCrystals;
>>>>>>> Full-Game
	private ArrayList<Card> deck;
	private ArrayList<Minion> field;
	private ArrayList<Card> hand;
	private int fatigueDamage;
<<<<<<< HEAD
	protected boolean justUseHeroPower = false;

	public Hero(String name) {
		this.name = name;
		this.currentHP = 30;
		this.deck = new ArrayList<Card>();
		this.field = new ArrayList<Minion>();
		this.hand = new ArrayList<Card>();
		this.fatigueDamage = 0;
	}

	public final static ArrayList<Minion> getAllNeutralMinions(String filePath)
			throws IOException {
		ArrayList<Minion> minions = new ArrayList<Minion>();
		FileReader fileReader = new FileReader(filePath);
		BufferedReader br = new BufferedReader(fileReader);
		String line = "";
		while ((line = br.readLine()) != null) {
			String[] attr = line.split(",");
			int manaCost = Integer.parseInt(attr[1]);
			Rarity rarity = Rarity.BASIC; // default !!!
			switch (attr[2]) {
			case "b":
				rarity = Rarity.BASIC;
				break;
			case "c":
				rarity = Rarity.COMMON;
				break;
			case "r":
				rarity = Rarity.RARE;
				break;
			case "e":
				rarity = Rarity.EPIC;
				break;
			case "l":
				rarity = Rarity.LEGENDARY;
				break;
			}
			int attack = Integer.parseInt(attr[3]);
			int maxHP = Integer.parseInt(attr[4]);
			boolean reset[] = new boolean[3];
			for (int i = 0; i < 3; i++) {
				if (attr[i + 5].equals("TRUE"))
					reset[i] = true;
				else
					reset[i] = false;
			}
			if (!attr[0].equals("Icehowl"))
				minions.add(new Minion(attr[0], manaCost, rarity, attack,
						maxHP, reset[0], reset[1], reset[2]));
			else
				minions.add(new Icehowl());
		}
		br.close();
		fileReader.close();
		return minions;
	}

	@Override
	public void onMinionDeath(Minion m) {
		int index = this.field.indexOf(m);
		if (index != -1)
			this.field.remove(field.indexOf(m));
	}

	public HeroListener getListener() {
		return listener;
	}

	public void setListener(HeroListener listener) {
		this.listener = listener;
	}

	public void setValidator(ActionValidator validator) {
		this.validator = validator;
=======
	private HeroListener listener;
	private ActionValidator validator;

	public HeroView ref;

	public Hero(String name) throws IOException, CloneNotSupportedException {
		this.name = name;
		currentHP = 30;
		deck = new ArrayList<Card>();
		field = new ArrayList<Minion>(7);
		hand = new ArrayList<Card>(10);
		this.ref = new HeroView(this,PlayGround.UNKWON);
		buildDeck();
	}

	public void setRef(HeroView ref) {
		this.ref = ref;
		for (Card c : deck)
			c.ref.setListener(ref);
	}

	public abstract void buildDeck() throws IOException,
			CloneNotSupportedException;

	public static final ArrayList<Minion> getAllNeutralMinions(String filePath)
			throws IOException {
		BufferedReader br = new BufferedReader(new FileReader(filePath));
		ArrayList<Minion> minions = new ArrayList<Minion>();
		String current = br.readLine();
		while (current != null) {
			String[] line = current.split(",");
			Minion minion = null;
			String n = line[0];
			int m = Integer.parseInt(line[1]);
			Rarity r = null;
			switch ((line[2])) {
			case "b":
				r = Rarity.BASIC;
				break;
			case "c":
				r = Rarity.COMMON;
				break;
			case "r":
				r = Rarity.RARE;
				break;
			case "e":
				r = Rarity.EPIC;
				break;
			case "l":
				r = Rarity.LEGENDARY;
				break;
			}
			int a = Integer.parseInt(line[3]);
			int p = Integer.parseInt(line[4]);
			boolean t = line[5].equals("TRUE") ? true : false;
			boolean d = line[6].equals("TRUE") ? true : false;
			boolean c = line[7].equals("TRUE") ? true : false;
			if (!n.equals("Icehowl"))
				minion = new Minion(n, m, r, a, p, t, d, c);
			else
				minion = new Icehowl();
			minions.add(minion);
			current = br.readLine();
		}
		br.close();
		return minions;
	}

	public static final ArrayList<Minion> getNeutralMinions(
			ArrayList<Minion> minions, int count)
			throws CloneNotSupportedException {
		ArrayList<Minion> res = new ArrayList<Minion>();
		int i = 0;
		while (i < count) {

			int index = (int) (Math.random() * minions.size());
			Minion minion = minions.get(index);
			int occ = 0;
			for (int j = 0; j < res.size(); j++) {
				if (res.get(j).getName().equals(minion.getName()))
					occ++;
			}
			if (occ == 0) {
				res.add(minion.clone());
				i++;
			} else if (occ == 1 && minion.getRarity() != Rarity.LEGENDARY) {

				res.add(minion.clone());
				i++;
			}
		}
		return res;
	}

	public void listenToMinions() {
		for (Card c : deck) {
			if (c instanceof Minion)
				((Minion) c).setListener(this);
		}
>>>>>>> Full-Game
	}

	public void useHeroPower() throws NotEnoughManaException,
			HeroPowerAlreadyUsedException, NotYourTurnException,
<<<<<<< HEAD
			FullHandException, FullFieldException, CloneNotSupportedException {
		validator.validateTurn(this);
		validator.validateUsingHeroPower(this);
		this.setCurrentManaCrystals(this.currentManaCrystals - 2);
		this.heroPowerUsed = true;
	}

	public void useHeroPower(Hero target) throws NotEnoughManaException,
			HeroPowerAlreadyUsedException, NotYourTurnException,
			FullHandException, FullFieldException, CloneNotSupportedException {
		validator.validateTurn(this);
		validator.validateUsingHeroPower(this);
		this.setCurrentManaCrystals(this.currentManaCrystals - 2);
		this.heroPowerUsed = true;
	}

	public void useHeroPower(Minion target) throws NotEnoughManaException,
			HeroPowerAlreadyUsedException, NotYourTurnException,
			FullHandException, FullFieldException, CloneNotSupportedException {
		validator.validateTurn(this);
		validator.validateUsingHeroPower(this);
		this.setCurrentManaCrystals(this.currentManaCrystals - 2);
		this.heroPowerUsed = true;
=======
			FullHandException, CloneNotSupportedException, FullFieldException {
		validator.validateTurn(this);
		validator.validateUsingHeroPower(this);
		this.setCurrentManaCrystals(currentManaCrystals - 2);
		heroPowerUsed = true;
>>>>>>> Full-Game
	}

	public void playMinion(Minion m) throws NotYourTurnException,
			NotEnoughManaException, FullFieldException {
		validator.validateTurn(this);
		validator.validateManaCost(m);
		validator.validatePlayingMinion(m);
<<<<<<< HEAD
		this.hand.remove(hand.indexOf(m));
		this.field.add(m);
		this.setCurrentManaCrystals(this.getCurrentManaCrystals()
				- m.getManaCost());
=======
		this.setCurrentManaCrystals(currentManaCrystals - m.getManaCost());
		hand.remove(m);
		field.add(m);

>>>>>>> Full-Game
	}

	public void attackWithMinion(Minion attacker, Minion target)
			throws CannotAttackException, NotYourTurnException,
			TauntBypassException, InvalidTargetException, NotSummonedException {
		validator.validateTurn(this);
		validator.validateAttack(attacker, target);
		attacker.attack(target);
	}

	public void attackWithMinion(Minion attacker, Hero target)
			throws CannotAttackException, NotYourTurnException,
			TauntBypassException, NotSummonedException, InvalidTargetException {
		validator.validateTurn(this);
		validator.validateAttack(attacker, target);
		attacker.attack(target);
	}

<<<<<<< HEAD
	public int checkMageAK() {
		if (this instanceof Mage) {
			boolean isKalycgos = false;
			for (Minion m : this.field)
				if (m.getName().equals("Kalycgos"))
					isKalycgos = true;
			if (isKalycgos)
				return 4;
		}
		return 0;
	}

	public void castSpell(FieldSpell s) throws NotYourTurnException,
			NotEnoughManaException {
		validator.validateTurn(this);
		int LSA = checkMageAK();
		Spell spell = (Spell) s;
		spell.setManaCost(spell.getManaCost() - LSA);
		try {
			validator.validateManaCost(spell);
		} catch (NotEnoughManaException e) {
			spell.setManaCost(spell.getManaCost() + LSA);
			throw new NotEnoughManaException();
		}
		int index = this.hand.indexOf(spell);
		s.performAction(this.field);
		this.setCurrentManaCrystals(this.currentManaCrystals
				- spell.getManaCost());
		if (index != -1)
			this.hand.remove(index);
	}

	public void castSpell(AOESpell s, ArrayList<Minion> oppField)
			throws NotYourTurnException, NotEnoughManaException {
		validator.validateTurn(this);
		int LSA = checkMageAK();
		Spell spell = (Spell) s;
		spell.setManaCost(spell.getManaCost() - LSA);
		try {
			validator.validateManaCost(spell);
		} catch (NotEnoughManaException e) {
			spell.setManaCost(spell.getManaCost() + LSA);
			throw new NotEnoughManaException();
		}
		int index = this.hand.indexOf(spell);
		s.performAction(oppField, this.field);
		this.setCurrentManaCrystals(this.currentManaCrystals
				- spell.getManaCost());
		if (index != -1)
			this.hand.remove(index);
=======
	public void castSpell(FieldSpell s) throws NotYourTurnException,
			NotEnoughManaException {
		validator.validateTurn(this);
		validator.validateManaCost((Spell) s);

		s.performAction(field);
		castSpellCommons((Spell) s);

>>>>>>> Full-Game
	}

	public void castSpell(MinionTargetSpell s, Minion m)
			throws NotYourTurnException, NotEnoughManaException,
			InvalidTargetException {
<<<<<<< HEAD
		validator.validateTurn(this);
		int LSA = checkMageAK();
		Spell spell = (Spell) s;
		spell.setManaCost(spell.getManaCost() - LSA);
		try {
			validator.validateManaCost(spell);
		} catch (NotEnoughManaException e) {
			spell.setManaCost(spell.getManaCost() + LSA);
			throw new NotEnoughManaException();
		}
		int index = this.hand.indexOf(spell);
		s.performAction(m);
		this.setCurrentManaCrystals(this.currentManaCrystals
				- spell.getManaCost());
		if (index != -1)
			this.hand.remove(index);
	}

	public void castSpell(HeroTargetSpell s, Hero h)
			throws NotYourTurnException, NotEnoughManaException {
		validator.validateTurn(this);
		int LSA = checkMageAK();
		Spell spell = (Spell) s;
		spell.setManaCost(spell.getManaCost() - LSA);
		try {
			validator.validateManaCost(spell);
		} catch (NotEnoughManaException e) {
			spell.setManaCost(spell.getManaCost() + LSA);
			throw new NotEnoughManaException();
		}

		int index = this.hand.indexOf(spell);
		s.performAction(h);
		this.setCurrentManaCrystals(this.currentManaCrystals
				- spell.getManaCost());
		if (index != -1)

			this.hand.remove(index);
	}

	public void castSpell(LeechingSpell s, Minion m)
			throws NotYourTurnException, NotEnoughManaException {
		validator.validateTurn(this);
		int LSA = checkMageAK();
		Spell spell = (Spell) s;
		spell.setManaCost(spell.getManaCost() - LSA);
		try {
			validator.validateManaCost(spell);
		} catch (NotEnoughManaException e) {
			spell.setManaCost(spell.getManaCost() + LSA);
			throw new NotEnoughManaException();
		}
		int index = this.hand.indexOf(spell);
		int heal = s.performAction(m);
		this.setCurrentHP(this.getCurrentHP() + heal);
		this.setCurrentManaCrystals(this.currentManaCrystals
				- spell.getManaCost());
		if (index != -1)
			this.hand.remove(index);
	}

	public void endTurn() throws FullHandException, CloneNotSupportedException {
		this.listener.endTurn();
	}

	public Card drawCard() throws FullHandException, CloneNotSupportedException {
		boolean isWilfred = false;
		boolean isChromaggus = false;

		if (this.deck.size() == 0) {
			this.setCurrentHP(this.currentHP - (this.fatigueDamage++));
			return null;
		}

		Card card = this.deck.remove(0);
		if (deck.size() == 0)
			this.fatigueDamage = 1;

		if (this.hand.size() == 10)
			throw new FullHandException(card);

		if (this instanceof Warlock) {
			if (this.justUseHeroPower) {
				for (Minion m : this.field)
					if (m.getName().equals("Wilfred Fizzlebang"))
						isWilfred = true;
				if (isWilfred)
					if (card instanceof Minion)
						card.setManaCost(0);
			}
		}
		for (Minion m : this.field)
			if (m.getName().equals("Chromaggus"))
				isChromaggus = true;
		if (isChromaggus) {
			if (this.hand.size() < 9)
				this.hand.add((Card) card.clone());
		}
		this.hand.add(card);
		return card;
	}

	public final static ArrayList<Minion> getNeutralMinions(
			ArrayList<Minion> minions, int count)
			throws CloneNotSupportedException {
		ArrayList<Minion> selectedMinion = new ArrayList<Minion>();
		HashMap<Minion, Integer> selected = new HashMap<Minion, Integer>();
		Random rand = new Random();
		while (count != 0) {
			int index = rand.nextInt(minions.size());
			Minion minion = minions.get(index);
			Integer i = selected.get(minion);
			if (i == null) {
				selectedMinion.add(minion);
				if (minion.getRarity() != Rarity.LEGENDARY)
					selected.put(minion, 1);
				else
					selected.put(minion, 2);
				count--;
			} else {
				if (i.equals(1)) {
					selectedMinion.add((Minion) minion.clone());
					selected.put(minion, 2);
					count--;
				}
			}
		}

		return selectedMinion;
	}

	public abstract void buildDeck() throws Exception;

	public String getName() {
		return name;
=======
		System.out.println("in");
		validator.validateTurn(this);
		validator.validateManaCost((Spell) s);
		s.performAction(m);
		castSpellCommons((Spell) s);
		System.out.println("done");

	}

	public void castSpell(LeechingSpell s, Minion m)
			throws NotYourTurnException, NotEnoughManaException {
		validator.validateTurn(this);
		validator.validateManaCost((Spell) s);
		int v = s.performAction(m);
		setCurrentHP(currentHP + v);
		castSpellCommons((Spell) s);
	}

	public void castSpell(HeroTargetSpell s, Hero h)
			throws NotYourTurnException, NotEnoughManaException {
		validator.validateTurn(this);
		validator.validateManaCost((Spell) s);
		s.performAction(h);
		castSpellCommons((Spell) s);

	}

	public void castSpell(AOESpell s, ArrayList<Minion> oppField)
			throws NotYourTurnException, NotEnoughManaException {
		validator.validateTurn(this);
		validator.validateManaCost((Spell) s);
		s.performAction(oppField, field);
		castSpellCommons((Spell) s);

	}

	private void castSpellCommons(Spell s) {
		this.setCurrentManaCrystals(currentManaCrystals -s.getManaCost());
		hand.remove(s);
	}

	public void endTurn() throws FullHandException, CloneNotSupportedException {
		listener.endTurn();
	}

	public Card drawCard() throws FullHandException, CloneNotSupportedException {
		if (fatigueDamage > 0) {
			setCurrentHP(currentHP - fatigueDamage);
			fatigueDamage++;
			return null;
		}

		Card c = deck.remove(0);

		this.ref.updateDeckSize();
		if (deck.size() == 0)
			fatigueDamage = 1;
		if (hand.size() == 10)
			throw new FullHandException("My hand is too full !!!", c);
		hand.add(c);
		this.ref.drawCard(c.ref);
		if (fieldContains("Chromaggus") && hand.size() < 10) {
			Card cloned = c.clone();
			CardView cardView = new CardView(cloned, PlayGround.paths.get(cloned
					.getName()), PlayGround.paths.get(cloned.getName() + "_m"));
			cardView.setListener(this.ref);
			cloned.ref = cardView;
			hand.add(c);
			this.ref.drawCard(cardView);
		}
		return c;

>>>>>>> Full-Game
	}

	public int getCurrentHP() {
		return currentHP;
	}

<<<<<<< HEAD
	public void setCurrentHP(int currentHP) {
		this.currentHP = Math.min(30, currentHP);
		this.currentHP = Math.max(0, this.currentHP);
		if (this.currentHP == 0)
			this.die();
	}

	public boolean isHeroPowerUsed() {
		return heroPowerUsed;
	}

	public void setHeroPowerUsed(boolean heroPowerUsed) {
		this.heroPowerUsed = heroPowerUsed;
=======
	public void setCurrentHP(int hp) {
		this.currentHP = hp;
		if (this.currentHP > 30)
			this.currentHP = 30;
		else if (this.currentHP <= 0) {
			this.currentHP = 0;
			listener.onHeroDeath();
		}
		if (ref != null)
			this.ref.setHealthPoints(this.currentHP);
>>>>>>> Full-Game
	}

	public int getTotalManaCrystals() {
		return totalManaCrystals;
	}

	public void setTotalManaCrystals(int totalManaCrystals) {
<<<<<<< HEAD
		this.totalManaCrystals = Math.min(10, totalManaCrystals);
		this.totalManaCrystals = Math.max(this.totalManaCrystals, 0);
		this.setCurrentManaCrystals(this.getTotalManaCrystals());
=======
		this.totalManaCrystals = totalManaCrystals;
		if (this.totalManaCrystals > 10)
			this.totalManaCrystals = 10;
>>>>>>> Full-Game
	}

	public int getCurrentManaCrystals() {
		return currentManaCrystals;
	}

	public void setCurrentManaCrystals(int currentManaCrystals) {
<<<<<<< HEAD
		this.currentManaCrystals = Math.min(10, currentManaCrystals);
		this.currentManaCrystals = Math.max(this.currentManaCrystals, 0);
	}

	public ArrayList<Card> getDeck() {
		return deck;
=======
		this.currentManaCrystals = currentManaCrystals;
		if (this.currentManaCrystals > 10)
			this.currentManaCrystals = 10;
		this.ref.setManaCrystal(this.currentManaCrystals);
	}

	public void onMinionDeath(Minion m) {
		field.remove(m);
	}

	public HeroListener getListener() {
		return listener;
>>>>>>> Full-Game
	}

	public ArrayList<Minion> getField() {
		return field;
	}

<<<<<<< HEAD
	public void setField(ArrayList<Minion> field) {
		this.field = field;
=======
	public void setListener(HeroListener listener) {
		this.listener = listener;
>>>>>>> Full-Game
	}

	public ArrayList<Card> getHand() {
		return hand;
	}
<<<<<<< HEAD
	
	public void getDamage(int amount) {
		this.setCurrentHP(this.getCurrentHP() - amount);
	}

	public void die() {
		this.listener.onHeroDeath();
=======

	public boolean isHeroPowerUsed() {
		return heroPowerUsed;
	}

	public ArrayList<Card> getDeck() {
		return deck;
	}

	public void setHeroPowerUsed(boolean powerUsed) {
		this.heroPowerUsed = powerUsed;
	}

	public boolean fieldContains(String n) {
		for (Minion m : field) {
			if (m.getName().equals(n))
				return true;
		}
		return false;
	}

	public String getName() {
		return name;
	}

	public void setValidator(ActionValidator validator) {
		this.validator = validator;
>>>>>>> Full-Game
	}

}
