package model.heroes;

import java.util.*;
import java.io.*;

import model.cards.*;
import model.cards.minions.*;
import model.cards.spells.*;
import engine.*;
import exceptions.*;

public abstract class Hero implements MinionListener {
	private String name;
	private int currentHP;

	private boolean heroPowerUsed;
	private int totalManaCrystals;
	private int currentManaCrystals;
	private HeroListener listener;
	private ActionValidator validator;
	private ArrayList<Card> deck;
	private ArrayList<Minion> field;
	private ArrayList<Card> hand;
	private int fatigueDamage;
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
	}

	public void useHeroPower() throws NotEnoughManaException,
			HeroPowerAlreadyUsedException, NotYourTurnException,
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
	}

	public void playMinion(Minion m) throws NotYourTurnException,
			NotEnoughManaException, FullFieldException {
		validator.validateTurn(this);
		validator.validateManaCost(m);
		validator.validatePlayingMinion(m);
		this.hand.remove(hand.indexOf(m));
		this.field.add(m);
		this.setCurrentManaCrystals(this.getCurrentManaCrystals()
				- m.getManaCost());
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
	}

	public void castSpell(MinionTargetSpell s, Minion m)
			throws NotYourTurnException, NotEnoughManaException,
			InvalidTargetException {
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
	}

	public int getCurrentHP() {
		return currentHP;
	}

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
	}

	public int getTotalManaCrystals() {
		return totalManaCrystals;
	}

	public void setTotalManaCrystals(int totalManaCrystals) {
		this.totalManaCrystals = Math.min(10, totalManaCrystals);
		this.totalManaCrystals = Math.max(this.totalManaCrystals, 0);
		this.setCurrentManaCrystals(this.getTotalManaCrystals());
	}

	public int getCurrentManaCrystals() {
		return currentManaCrystals;
	}

	public void setCurrentManaCrystals(int currentManaCrystals) {
		this.currentManaCrystals = Math.min(10, currentManaCrystals);
		this.currentManaCrystals = Math.max(this.currentManaCrystals, 0);
	}

	public ArrayList<Card> getDeck() {
		return deck;
	}

	public ArrayList<Minion> getField() {
		return field;
	}

	public void setField(ArrayList<Minion> field) {
		this.field = field;
	}

	public ArrayList<Card> getHand() {
		return hand;
	}
	
	public void getDamage(int amount) {
		this.setCurrentHP(this.getCurrentHP() - amount);
	}

	public void die() {
		this.listener.onHeroDeath();
	}

}
