package entites;

import java.util.Arrays;

public class Objet {
	private float profit;
	private int groupe;
	private int contraintes [];
	private boolean choisit;

	/**
	 * Constructeur de la classe Objet
	 * @param profit
	 * @param groupe
	 * @param contraintes
	 * @param choisit
	 */
	public Objet(float profit, int groupe, int[] contraintes, boolean choisit){
		this.profit = profit;
		this.groupe = groupe;
		this.contraintes = contraintes;
		this.choisit = choisit;
	}
	
	/**
	 * Constructeur de la classe Objet avec l'attribut choisit à false
	 * @param profit
	 * @param groupe
	 * @param contraintes
	 */
	public Objet(float profit, int groupe, int[] contraintes){
		this.profit = profit;
		this.groupe = groupe;
		this.contraintes = contraintes;
		this.choisit = false;
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		return "Objet [profit=" + profit + ", groupe=" + groupe + ", contraintes=" + Arrays.toString(contraintes)
				+ ", choisit=" + choisit + "]";
	}

	/**
	 * @return le profit de l'objet
	 */
	public float getProfit() {
		return profit;
	}

	/**
	 * @param profit
	 */
	public void setProfit(float profit) {
		this.profit = profit;
	}

	/**
	 * @return le numero du groupe
	 */
	public int getGroupe() {
		return groupe;
	}

	/**
	 * @param groupe
	 */
	public void setGroupe(int groupe) {
		this.groupe = groupe;
	}

	/**
	 * @return le tableau de contrainte de l'objet
	 */
	public int[] getContraintes() {
		return contraintes;
	}

	/**
	 * @param contraintes
	 */
	public void setContraintes(int[] contraintes) {
		this.contraintes = contraintes;
	}

	/**
	 * @return true si l'objet est choisit, sinon false
	 */
	public boolean isChoisit() {
		return choisit;
	}

	/**
	 * @param choisit
	 */
	public void setChoisit(boolean choisit) {
		this.choisit = choisit;
	}
}
