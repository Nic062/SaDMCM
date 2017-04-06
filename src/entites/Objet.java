package entites;

import java.util.Arrays;

public class Objet {
	private float profit;
	private int groupe;
	private int coef [];
	private boolean choisit;

	/**
	 * Constructeur de la classe Objet
	 * @param profit
	 * @param groupe
	 * @param coef
	 * @param choisit
	 */
	public Objet(float profit, int groupe, int[] coef, boolean choisit){
		this.profit = profit;
		this.groupe = groupe;
		this.coef = coef;
		this.choisit = choisit;
	}
	
	/**
	 * Constructeur de la classe Objet avec l'attribut choisit à false
	 * @param profit
	 * @param groupe
	 * @param coef
	 */
	public Objet(float profit, int groupe, int[] coef){
		this.profit = profit;
		this.groupe = groupe;
		this.coef = coef;
		this.choisit = false;
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		return "Objet [profit=" + profit + ", groupe=" + groupe + ", coef=" + Arrays.toString(coef)
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
	 * @return le tableau de coef de l'objet
	 */
	public int[] getCoef() {
		return coef;
	}

	/**
	 * @param coef
	 */
	public void setCoef(int[] coef) {
		this.coef = coef;
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
