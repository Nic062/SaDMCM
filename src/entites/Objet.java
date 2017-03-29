package entites;

public class Objet {
	private float profit;
	private int groupe;
	private int contraintes [];
	private boolean choisit;

	public Objet(float profit, int groupe, int[] contraintes, boolean choisit){
		this.profit = profit;
		this.groupe = groupe;
		this.contraintes = contraintes;
		this.profit = profit;
		this.choisit = choisit;
	}
	
	public Objet(float profit, int groupe, int[] contraintes){
		this.profit = profit;
		this.groupe = groupe;
		this.contraintes = contraintes;
		this.profit = profit;
		this.choisit = false;
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
