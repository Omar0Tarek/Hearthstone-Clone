package engine;

<<<<<<< HEAD
import model.cards.Card;
import model.cards.minions.Icehowl;
import model.cards.minions.Minion;
import model.heroes.*;

import java.util.*;

=======
>>>>>>> Full-Game
import exceptions.CannotAttackException;
import exceptions.FullFieldException;
import exceptions.FullHandException;
import exceptions.HeroPowerAlreadyUsedException;
import exceptions.InvalidTargetException;
import exceptions.NotEnoughManaException;
import exceptions.NotSummonedException;
import exceptions.NotYourTurnException;
import exceptions.TauntBypassException;
<<<<<<< HEAD
=======
import model.cards.Card;
import model.cards.minions.Minion;
import model.heroes.Hero;
import model.heroes.HeroListener;
import model.heroes.Paladin;
>>>>>>> Full-Game

public class Game implements ActionValidator, HeroListener {
	private Hero firstHero;
	private Hero secondHero;
	private Hero currentHero;
	private Hero opponent;
<<<<<<< HEAD
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
=======

	private GameListener listener;

	public Game(Hero p1, Hero p2) throws FullHandException, CloneNotSupportedException {
		firstHero = p1;
		secondHero = p2;
		firstHero.setListener(this);
		secondHero.setListener(this);
		firstHero.setValidator(this);
		secondHero.setValidator(this);
		int coin = (int) (Math.random() * 2);
		currentHero = coin == 0 ? firstHero : secondHero;
		opponent = currentHero == firstHero ? secondHero : firstHero;
		currentHero.setCurrentManaCrystals(1);
		currentHero.setTotalManaCrystals(1);
		System.out.println("in");
		for (int i = 0; i < 3; i++) {
			currentHero.drawCard();
		}
		for (int i = 0; i < 4; i++) {
			opponent.drawCard();
		}
>>>>>>> Full-Game
	}

	@Override
	public void validateTurn(Hero user) throws NotYourTurnException {
<<<<<<< HEAD
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
=======
		if (user == opponent)
			throw new NotYourTurnException("You can not do any action in your opponent's turn");
	}

	public void validateAttack(Minion a, Minion t)
			throws TauntBypassException, InvalidTargetException, NotSummonedException, CannotAttackException {
		if (a.getAttack() <= 0)
			throw new CannotAttackException("This minion Cannot Attack");
		if (a.isSleeping())
			throw new CannotAttackException("Give this minion a turn to get ready");
		if (a.isAttacked())
			throw new CannotAttackException("This minion has already attacked");
		if (!currentHero.getField().contains(a))
			throw new NotSummonedException("You can not attack with a minion that has not been summoned yet");
		if (currentHero.getField().contains(t))
			throw new InvalidTargetException("You can not attack a friendly minion");
		if (!opponent.getField().contains(t))
			throw new NotSummonedException("You can not attack a minion that your opponent has not summoned yet");
		if (!t.isTaunt()) {
			for (int i = 0; i < opponent.getField().size(); i++) {
				if (opponent.getField().get(i).isTaunt())
					throw new TauntBypassException("A minion with taunt is in the way");
			}

		}

	}

	public void validateAttack(Minion m, Hero t)
			throws TauntBypassException, NotSummonedException, InvalidTargetException, CannotAttackException {
		if (m.getAttack() <= 0)
			throw new CannotAttackException("This minion Cannot Attack");
		if (m.isSleeping())
			throw new CannotAttackException("Give this minion a turn to get ready");
		if (m.isAttacked())
			throw new CannotAttackException("This minion has already attacked");
		if (!currentHero.getField().contains(m))
			throw new NotSummonedException("You can not attack with a minion that has not been summoned yet");
		if (t.getField().contains(m))
			throw new InvalidTargetException("You can not attack yourself with your minions");
		for (int i = 0; i < opponent.getField().size(); i++) {
			if (opponent.getField().get(i).isTaunt())
				throw new TauntBypassException("A minion with taunt is in the way");
		}
	}

	public void validateManaCost(Card c) throws NotEnoughManaException {
		System.out.println(c);
		if (currentHero.getCurrentManaCrystals() < c.getManaCost())
			throw new NotEnoughManaException("I don't have enough mana !!");
	}

	public void validatePlayingMinion(Minion m) throws FullFieldException {
		if (currentHero.getField().size() == 7)
			throw new FullFieldException("No space for this minion");
	}

	public void validateUsingHeroPower(Hero h) throws NotEnoughManaException, HeroPowerAlreadyUsedException {
		if (h.getCurrentManaCrystals() < 2)
			throw new NotEnoughManaException("I don't have enough mana !!");
		if (h.isHeroPowerUsed())
			throw new HeroPowerAlreadyUsedException(" I already used my hero power");
>>>>>>> Full-Game
	}

	@Override
	public void onHeroDeath() {
<<<<<<< HEAD
		this.listener.onGameOver();
	}
	

	@Override
	public void damageOpponent(int amount) {
		this.opponent.getDamage(amount);
=======

		listener.onGameOver();

	}

	@Override
	public void damageOpponent(int amount) {

		opponent.setCurrentHP(opponent.getCurrentHP() - amount);
	}

	public Hero getCurrentHero() {
		return currentHero;
	}

	public void setListener(GameListener listener) {
		this.listener = listener;
>>>>>>> Full-Game
	}

	@Override
	public void endTurn() throws FullHandException, CloneNotSupportedException {
<<<<<<< HEAD
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
=======
		Hero temp = currentHero;
		currentHero = opponent;
		opponent = temp;
		currentHero.setTotalManaCrystals(currentHero.getTotalManaCrystals() + 1);
		currentHero.setCurrentManaCrystals(currentHero.getTotalManaCrystals());
		currentHero.setHeroPowerUsed(false);
		for (Minion m : currentHero.getField()) {
			m.setAttacked(false);
			m.setSleeping(false);
		}
		currentHero.drawCard();

	}

	public Hero getOpponent() {
		return opponent;
	}
	
>>>>>>> Full-Game
}
