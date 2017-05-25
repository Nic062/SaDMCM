package entites;

import java.util.Arrays;

public class Objet implements Comparable<Objet>{
	private float profit;
	private int groupe;
	private int coef [];
	private int position;

	/**
	 * Constructeur de la classe Objet
	 * @param profit
	 * @param groupe
	 * @param coef
	 */
	public Objet(float profit, int groupe, int[] coef, int position){
		this.profit = profit;
		this.groupe = groupe;
		this.coef = coef;
		this.position = position;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		return "Objet [profit=" + profit + ", groupe=" + groupe + ", position=" + position + ", coef=" + Arrays.toString(coef)
				+ "]";
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

	public int getCoef(int nbCoef) {
		return getCoef()[nbCoef];
	}

	public int getSommeCoef() {
		int somme = 0;
		for (int coef : this.coef) {
			somme += coef;
		}

		return somme;
	}

	/**
	 * @param coef
	 */
	public void setCoef(int[] coef) {
		this.coef = coef;
	}

	public int getPosition() {
		return position;
	}

	public void setPosition(int position) {
		this.position = position;
	}

	public int compareTo(Objet obj) {
		if (this.profit > obj.getProfit())
			return 1;
		else if (this.profit < obj.getProfit())
			return -1;
		else
			return 0;
	}
}
