package engine;

import model.cards.Card;
import model.cards.minions.Icehowl;
import model.cards.minions.Minion;
import model.heroes.*;

import java.util.*;

import exceptions.CannotAttackException;
import exceptions.FullFieldException;
import exceptions.FullHandException;
import exceptions.HeroPowerAlreadyUsedException;
import exceptions.InvalidTargetException;
import exceptions.NotEnoughManaException;
import exceptions.NotSummonedException;
import exceptions.NotYourTurnException;
import exceptions.TauntBypassException;

public class Game implements ActionValidator, HeroListener {
	private Hero firstHero;
	private Hero secondHero;
	private Hero currentHero;
	private Hero opponent;
	private GameListener listener;
	private int NGame;

	public Game(Hero p1, Hero p2) throws Exception {
		this.firstHero = p1;
		this.secondHero = p2;
		this.firstHero.setListener(this);
		this.firstHero.setValidator(this);
		this.secondHero.setListener(this);
		this.secondHero.setValidator(this);
		Random rand = new Random();
		int s = rand.nextInt(2);
		if (s == 1) {
			this.currentHero = this.firstHero;
			this.opponent = this.secondHero;
		} else {
			this.currentHero = this.secondHero;
			this.opponent = this.firstHero;
		}
		this.NGame = 1;
		this.currentHero.setTotalManaCrystals(NGame);
		for (int i = 0; i < 3; i++)
			this.currentHero.drawCard();
		for (int i = 0; i < 4; i++)
			this.opponent.drawCard();
	}

	public void setListener(GameListener listener) {
		this.listener = listener;
	}

	public Hero getCurrentHero() {
		return currentHero;
	}

	public Hero getOpponent() {
		return opponent;
	}

	@Override
	public void validateTurn(Hero user) throws NotYourTurnException {
		if (user != this.currentHero)
			throw new NotYourTurnException();
	}

	@Override
	public void validateAttack(Minion attacker, Minion target)
			throws CannotAttackException, NotSummonedException,
			TauntBypassException, InvalidTargetException {
		ArrayList<Minion> currentHeroField = this.currentHero.getField();
		ArrayList<Minion> opponentField = this.opponent.getField();
		if (!currentHeroField.contains(attacker))
			throw new NotSummonedException();
		if (currentHeroField.contains(target))
			throw new InvalidTargetException();
		if (!opponentField.contains(target))
			throw new NotSummonedException();
		if (attacker.isSleeping() || attacker.isAttacked()
				|| attacker.getAttack() == 0)
			throw new CannotAttackException();
		if (!target.isTaunt()) {
			boolean isThereTaunt = false;
			for (Minion m : opponentField)
				if (m.isTaunt()) {
					isThereTaunt = true;
					break;
				}
			if (isThereTaunt)
				throw new TauntBypassException();
		}
	}

	@Override
	public void validateAttack(Minion attacker, Hero target)
			throws CannotAttackException, NotSummonedException,
			TauntBypassException, InvalidTargetException {
		ArrayList<Minion> currentHeroField = this.currentHero.getField();
		if (target != opponent)
			throw new InvalidTargetException();
		if (!currentHeroField.contains(attacker))
			throw new NotSummonedException();
		if (attacker.getName().equals("Icehowl"))
			throw new InvalidTargetException();
		if (attacker.isSleeping() || attacker.isAttacked() || attacker.getAttack() == 0)
			throw new CannotAttackException();
		ArrayList<Minion> opponentField = this.opponent.getField();
		boolean isThereTaunt = false;
		for (Minion m : opponentField)
			if (m.isTaunt()) {
				isThereTaunt = true;
				break;
			}
		if (isThereTaunt)
			throw new TauntBypassException();
	}

	@Override
	public void validateManaCost(Card card) throws NotEnoughManaException {
		if (this.currentHero.getCurrentManaCrystals() < card.getManaCost())
			throw new NotEnoughManaException();
	}

	@Override
	public void validatePlayingMinion(Minion minion) throws FullFieldException {
		if (this.currentHero.getField().size() == 7)
			throw new FullFieldException();
	}

	@Override
	public void validateUsingHeroPower(Hero hero)
			throws NotEnoughManaException, HeroPowerAlreadyUsedException {
		if (hero == currentHero) {
			if (hero.isHeroPowerUsed())
				throw new HeroPowerAlreadyUsedException();
			if (hero.getCurrentManaCrystals() < 2)
				throw new NotEnoughManaException();
		}
	}

	@Override
	public void onHeroDeath() {
		this.listener.onGameOver();
	}
	

	@Override
	public void damageOpponent(int amount) {
		this.opponent.getDamage(amount);
	}

	@Override
	public void endTurn() throws FullHandException, CloneNotSupportedException {
		Hero hero = this.currentHero;
		this.currentHero = this.opponent;
		this.opponent = hero;
		this.currentHero.setTotalManaCrystals(this.currentHero
				.getTotalManaCrystals() + 1);
		this.currentHero.setHeroPowerUsed(false);
		ArrayList<Minion> currentHeroField = this.currentHero.getField();
		for (Minion m : currentHeroField) {
			m.setAttacked(false);
			m.setSleeping(false);
		}
		this.currentHero.drawCard();
	}
}
