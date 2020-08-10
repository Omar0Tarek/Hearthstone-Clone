package model.cards.minions;

import model.cards.*;
import exceptions.*;
import model.heroes.*;

public class Minion extends Card implements Cloneable {
	private int attack;
	private int maxHP;
	private int currentHP;

	private boolean taunt;
	private boolean divine;
	private boolean sleeping;
	private boolean attacked;
	private MinionListener listener;

	public Minion() {
	}

	public Minion(String name, int manaCost, Rarity rarity, int attack,
			int maxHP, boolean taunt, boolean divine, boolean charge) {
		super(name, manaCost, rarity);
		this.attack = attack;
		this.maxHP = maxHP;
		this.currentHP = maxHP;
		this.taunt = taunt;
		this.divine = divine;
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
	}

	public int getMaxHP() {
		return maxHP;
	}

	public void setMaxHP(int maxHP) {
		this.maxHP = maxHP;
		if(this.maxHP <= 0)
			this.die();
	}

	public int getCurrentHP() {
		return currentHP;
	}

	public void setCurrentHP(int currentHP) {
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
	}

	public void setDivine(boolean divine) {
		this.divine = divine;
	}

	public boolean isSleeping() {
		return sleeping;
	}

	public void setSleeping(boolean sleeping) {
		this.sleeping = sleeping;
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

}
