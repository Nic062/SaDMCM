/**
 * 
 */
package entites;

import java.util.Arrays;
import java.util.Collections;

public class Sac {
	private Objet[][] listObjet;
	private int[] contraintes;
	private int nbGroupes;
	private int objParGroupe;
	private int nbContraintes;

	/**
	 * Constructeur de la classe Sac
	 * Il instancie l'attribut listObjet et contraintes
	 * @param nbGroupes
	 * @param objParGroupe
	 * @param nbContraintes
	 */
	public Sac(int nbGroupes,
			int objParGroupe, int nbContraintes) {
		super();
		this.nbGroupes = nbGroupes;
		this.objParGroupe = objParGroupe;
		this.nbContraintes = nbContraintes;

		this.listObjet = new Objet[nbGroupes][objParGroupe];
		this.setContraintes(new int[nbContraintes]);
	}

	/**
	 * @return le nombre de groupe
	 */
	public int getNbGroupes() {
		return nbGroupes;
	}

	/**
	 * 
	 * @param nbGroupes le nombre de groupe
	 */
	public void setNbGroupes(int nbGroupes) {
		this.nbGroupes = nbGroupes;
	}

	/**
	 * @return le nombre d'objet par groupe
	 */
	public int getObjParGroupe() {
		return objParGroupe;
	}

	/**
	 * @param objParGroupe le nombre d'objet par groupe
	 */
	public void setObjParGroupe(int objParGroupe) {
		this.objParGroupe = objParGroupe;
	}

	/**
	 * @return le nombre de contrainte
	 */
	public int getNbContraintes() {
		return nbContraintes;
	}

	/**
	 * @param nbContraintes le nombre de contrainte
	 */
	public void setNbContraintes(int nbContraintes) {
		this.nbContraintes = nbContraintes;
	}

	/**
	 * @return un tableau de contrainte
	 */
	public int[] getContraintes() {
		return contraintes;
	}

	/**
	 * @param position la position à partir de 0
	 * @param valeur la valeur de la contrainte
	 */
	public void setContraintes(int position, int valeur) {
		this.contraintes[position] = valeur;
	}

	/**
	 * @param contraintes un tableau de contrainte
	 */
	public void setContraintes(int[] contraintes) {
		this.contraintes = contraintes;
	}

	/**
	 * @param groupe le numero du groupe, 0 <= groupe < nbGroupe
	 * @param position la position dans le groupe, 0 <= groupe < objParGroupe
	 * @param obj l'objet à inserer
	 */
	public void addObjet(int groupe, int position, Objet obj) {
		this.listObjet[groupe][position] = obj;
	}

	/**
	 * @param groupe le numero du groupe
	 * @return un tableau d'objet appartenant au groupe
	 */
	public Objet[] getGroupe(int groupe) {
		return this.listObjet[groupe];
	}

	public Objet[][] getListObjet() {
		return listObjet;
	}

	/**
	 * @param groupe le numero du groupe à partir de 0
	 * @param position la position à partir de 0
	 * @return un objet du groupe
	 */
	public Objet getObjet(int groupe, int position) {
		return this.listObjet[groupe][position];
	}

	/**
	 * Cette fonction addition la plus grosse valeur de chaque groupe pour en faire le profit max
	 * @return le profit max possible du sac
	 */
	public double getProfitMax() {
		double max = 0;

		for ( int i = 0; i < this.nbGroupes; i++) {
			double profitMax = 0;
			for ( int j = 0; j < this.objParGroupe; j++) {
				if (this.getObjet(i, j).getProfit() > profitMax) {
					profitMax = this.getObjet(i, j).getProfit();
				}
			}

			max += profitMax;
		}

		return max;
	}

	public String toString() {
		return "Sac [listObjet=" + Arrays.toString(listObjet) + ", contraintes=" + Arrays.toString(contraintes)
				+ ", nbGroupes=" + nbGroupes + ", objParGroupe=" + objParGroupe + ", nbContraintes=" + nbContraintes
				+ "]";
	}

	public boolean verifierContrainte(int numContrainte, Combinaison combinaison) {
		int sommeContrainte = 0;
		for (int i = 0; i < combinaison.getListeObjet().size(); i++) {
			sommeContrainte += combinaison.getListeObjet().get(i).getCoef()[numContrainte];
		}

		if (sommeContrainte > this.getContraintes()[numContrainte]) {
			return false;
		}

		return true;
	}
	
	public void trierCroissant(){
		for ( int i = 0; i < this.nbGroupes; i++) {
			Arrays.sort(listObjet[i]);
		}
		for ( int i = 0; i < this.nbGroupes; i++) {
			for ( int j = 0; j < this.objParGroupe; j++) {
				this.getObjet(i, j).setPosition(j);
			}
		}
	}
	
	public void trierDecroissant(){
		for ( int i = 0; i < this.nbGroupes; i++) {
			Arrays.sort(listObjet[i], Collections.reverseOrder());
		}
		for ( int i = 0; i < this.nbGroupes; i++) {
			for ( int j = 0; j < this.objParGroupe; j++) {
				this.getObjet(i, j).setPosition(j);
			}
		}
	}
	
}
