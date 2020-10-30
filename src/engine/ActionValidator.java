package engine;

<<<<<<< HEAD
import exceptions.*;
import model.heroes.*;
import model.cards.minions.*;
import model.cards.*;

public interface ActionValidator {
	public void validateTurn(Hero user) throws NotYourTurnException;
	
	public void validateAttack(Minion attacker,Minion target) throws
	CannotAttackException, NotSummonedException, TauntBypassException,
	InvalidTargetException;
	
	public void validateAttack(Minion attacker,Hero target) throws
	CannotAttackException, NotSummonedException, TauntBypassException,
	InvalidTargetException;
	
	public void validateManaCost(Card card) throws NotEnoughManaException;
	
	public void validatePlayingMinion(Minion minion) throws FullFieldException;
	
	public void validateUsingHeroPower(Hero hero) throws NotEnoughManaException,
	HeroPowerAlreadyUsedException;

=======
import exceptions.CannotAttackException;
import exceptions.FullFieldException;
import exceptions.HeroPowerAlreadyUsedException;
import exceptions.InvalidTargetException;
import exceptions.NotEnoughManaException;
import exceptions.NotSummonedException;
import exceptions.NotYourTurnException;
import exceptions.TauntBypassException;
import model.cards.Card;
import model.cards.minions.Minion;
import model.heroes.Hero;

public interface ActionValidator {
	public void validateTurn(Hero user) throws NotYourTurnException;

	public void validateAttack(Minion a, Minion t)
			throws TauntBypassException, InvalidTargetException, NotSummonedException, CannotAttackException;

	public void validateAttack(Minion m, Hero t)
			throws TauntBypassException, NotSummonedException, InvalidTargetException, CannotAttackException;

	public void validateManaCost(Card card) throws NotEnoughManaException;

	public void validatePlayingMinion(Minion m) throws FullFieldException;

	public void validateUsingHeroPower(Hero h) throws NotEnoughManaException, HeroPowerAlreadyUsedException;
>>>>>>> Full-Game
}
