package entites;

public class Objet {
	private float profit;
	private int groupe;
	private int contraintes [];

	public Objet(float profit, int groupe, int[] contraintes) {
		this.profit = profit;
		this.groupe = groupe;
		this.contraintes = contraintes;
	}

	public float getProfit() {
		return profit;
	}

	public void setProfit(float profit) {
		this.profit = profit;
	}

	public int getGroupe() {
		return groupe;
	}

	public void setGroupe(int groupe) {
		this.groupe = groupe;
	}

	public int[] getContraintes() {
		return contraintes;
	}

	public void setContraintes(int[] contraintes) {
		this.contraintes = contraintes;
	}


}
