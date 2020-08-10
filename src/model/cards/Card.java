package model.cards;

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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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
	}

}
