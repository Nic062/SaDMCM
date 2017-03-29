package main;

import entites.Ecriture;
import entites.Objet;

public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int nbGroupes = 3;
		int nbObjParGroupe = 2;
		int nbContraintes = 2;
		int[] contraintes = new int[]{25,50};
		Objet[][] listObjet = new Objet[3][2];
		
		listObjet[0][0] = new Objet(20, 1, new int[]{15,22}, true);
		listObjet[0][1] = new Objet(18, 1, new int[]{16,21});
		listObjet[1][0] = new Objet(15, 1, new int[]{12,16}, true);
		listObjet[1][1] = new Objet(14, 1, new int[]{12,14});
		listObjet[2][0] = new Objet(12, 1, new int[]{10,15});
		listObjet[2][1] = new Objet(10, 1, new int[]{8,13});
		
		double max = max(listObjet);
		
		Ecriture ecriture = new Ecriture(listObjet, "test.txt", max);
		
		ecriture.ecrire();
	}
	
	public static double max(Objet[][] listObjet) {
		double max = 0;
		
		for (int i = 0; i < listObjet.length; i++) {
			for (int j = 0; j < listObjet[i].length; j++) {
				max += listObjet[i][j].getProfit();
			}
		}
		return max;
	}

}
