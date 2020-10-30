package model.cards;

<<<<<<< HEAD
public abstract class Card implements Cloneable {

	private String name;
	private int manaCost;
	private Rarity rarity;

	public Card() {}


	@Override
	public Object clone() throws CloneNotSupportedException {
		// TODO Auto-generated method stub
		return super.clone();
	}


	public Card(String name, int manaCost, Rarity rarity) {
		this.name = name;
		this.setManaCost(manaCost);
		this.rarity = rarity;
	}

=======
import view.components.CardView;

public abstract class Card implements Cloneable {
	private String name;
	private int manaCost;
	private Rarity rarity;
	
	public CardView ref;

	public Card(String name, int manaCost, Rarity rarity) {
		this.name = name;
		this.manaCost = manaCost;
		this.rarity = rarity;
	}

	public int getManaCost() {
		return manaCost;
	}

	public Rarity getRarity() {
		return rarity;
	}

>>>>>>> Full-Game
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
<<<<<<< HEAD
	}

	public int getManaCost() {
		return manaCost;
	}

	public void setManaCost(int manaCost) {
		this.manaCost = Math.min(10, manaCost);
		this.manaCost = Math.max(0, this.manaCost);
	}

	public Rarity getRarity() {
		return rarity;
=======
		this.ref.setName(name);
	}

	public void setManaCost(int manaCost) {
		this.manaCost = manaCost;
		if (this.manaCost > 10)
			this.manaCost = 10;
		if (this.manaCost < 0)
			this.manaCost = 0;
	}

	public Card clone() throws CloneNotSupportedException {
		return (Card) super.clone();
>>>>>>> Full-Game
	}

}
