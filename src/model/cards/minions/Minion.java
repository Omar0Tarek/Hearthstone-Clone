package model.cards.minions;

<<<<<<< HEAD
import model.cards.*;
import exceptions.*;
import model.heroes.*;
=======
import model.cards.Card;
import model.cards.Rarity;
import model.heroes.Hero;
import exceptions.InvalidTargetException;
>>>>>>> Full-Game

public class Minion extends Card implements Cloneable {
	private int attack;
	private int maxHP;
	private int currentHP;
<<<<<<< HEAD

	private boolean taunt;
	private boolean divine;
	private boolean sleeping;
	private boolean attacked;
	private MinionListener listener;

	public Minion() {
	}

	public Minion(String name, int manaCost, Rarity rarity, int attack,
			int maxHP, boolean taunt, boolean divine, boolean charge) {
=======
	private boolean taunt;
	private boolean divine;
	public boolean sleeping;
	private boolean attacked;
	private MinionListener listener;

	public Minion(String name, int manaCost, Rarity rarity, int attack, int maxHP, boolean taunt, boolean divine,
			boolean charge) {
>>>>>>> Full-Game
		super(name, manaCost, rarity);
		this.attack = attack;
		this.maxHP = maxHP;
		this.currentHP = maxHP;
		this.taunt = taunt;
		this.divine = divine;
<<<<<<< HEAD
		this.sleeping = !charge;
	}

	public void attack(Minion target) {
		this.getDamage(target.getAttack());
		target.getDamage(this.getAttack());
		this.setAttacked(true);
	}
	
	public void getDamage(int amount) {
		if(this.isDivine() && amount > 0) {
			this.setDivine(false);
		} else {
			this.setCurrentHP(this.getCurrentHP() - amount);
		}
	}
	public void attack(Hero target) throws InvalidTargetException {
		if(this.getName().equals("Icehowl")) {
			throw new InvalidTargetException();
		}
		
		target.getDamage(this.attack);
		this.setAttacked(true);
	}

	@Override
	public Object clone() throws CloneNotSupportedException {
		return new Minion(this.getName(), this.getManaCost(), this.getRarity(),
				this.attack, this.maxHP, this.taunt, this.divine,
				!this.sleeping);
	}

	public void setListener(MinionListener listener) {
		this.listener = listener;
	}

	public int getAttack() {
		return attack;
	}

	public void setAttack(int attack) {
		this.attack = Math.max(0, attack);
=======
		if (!charge)
			this.sleeping = true;
	}

	public void attack(Minion target) {

		if (divine && target.divine)

		{
			if (target.attack > 0)
				divine = false;
			if (attack > 0)
				target.divine = false;
		} else if (divine) {
			target.setCurrentHP(target.currentHP - attack);
			if (target.getAttack() > 0)
				divine = false;

		} else if (target.divine) {
			setCurrentHP(currentHP - target.attack);
			if (attack > 0)
				target.divine = false;
		} else {
			target.setCurrentHP(target.currentHP - attack);
			setCurrentHP(currentHP - target.attack);

		}
		attacked = true;
	}

	public void attack(Hero target) throws InvalidTargetException {
		attacked = true;
		target.setCurrentHP(target.getCurrentHP() - attack);

	}

	public boolean isTaunt() {
		return taunt;
>>>>>>> Full-Game
	}

	public int getMaxHP() {
		return maxHP;
	}

<<<<<<< HEAD
	public void setMaxHP(int maxHP) {
		this.maxHP = maxHP;
		if(this.maxHP <= 0)
			this.die();
=======
	public void setMaxHP(int maxHp) {
		this.maxHP = maxHp;
>>>>>>> Full-Game
	}

	public int getCurrentHP() {
		return currentHP;
	}

	public void setCurrentHP(int currentHP) {
<<<<<<< HEAD
		this.currentHP = Math.min(currentHP, this.maxHP);
		this.currentHP = Math.max(this.currentHP, 0);
		if (this.currentHP == 0)
			this.die();
	}

	public boolean isTaunt() {
		return taunt;
	}

	public void setTaunt(boolean taunt) {
		this.taunt = taunt;
	}

	public boolean isDivine() {
		return divine;
=======
		this.currentHP = currentHP;
		if (this.currentHP > maxHP)
			currentHP = maxHP;
		else if (this.currentHP <= 0) {
			this.currentHP = 0;
			listener.onMinionDeath(this);
		}
		this.ref.setHealthPoints(this.currentHP);
	}

	public void setListener(MinionListener listener) {
		this.listener = listener;
	}

	public int getAttack() {
		return attack;
	}

	public void setAttack(int attack) {
		this.attack = attack;
		if (this.attack <= 0)
			this.attack = 0;
		this.ref.setAttackPoints(this.attack);
	}

	public void setTaunt(boolean isTaunt) {
		this.taunt = isTaunt;
>>>>>>> Full-Game
	}

	public void setDivine(boolean divine) {
		this.divine = divine;
<<<<<<< HEAD
=======
		this.ref.setDivine(divine);
	}

	public boolean isAttacked() {
		return attacked;
	}

	public void setAttacked(boolean attacked) {
		this.attacked = attacked;
>>>>>>> Full-Game
	}

	public boolean isSleeping() {
		return sleeping;
	}

	public void setSleeping(boolean sleeping) {
		this.sleeping = sleeping;
<<<<<<< HEAD
	}

	public boolean isAttacked() {
		return attacked;
	}

	public void setAttacked(boolean attacked) {
		this.attacked = attacked;
	}

	public void die() {
		this.listener.onMinionDeath(this);
	}

=======
		this.ref.setSleeping(sleeping);
	}

	public boolean isDivine() {
		return divine;
	}

	public Minion clone() throws CloneNotSupportedException {
		return (Minion) super.clone();
	}
>>>>>>> Full-Game
}
