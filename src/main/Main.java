package main;

import entites.Combinaison;
import entites.Lecture;
import entites.Sac;

public class Main {

	static int counter = 0;
	static Sac sac;
	static Combinaison lastBestCombinaison = null;
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		long debut = System.currentTimeMillis();
		Lecture lecture = new Lecture("instances/I2.txt");
		lecture.lireEntete();
		lecture.lireContraintesCapacite();
		for(int i=0; i<lecture.getSac().getNbGroupes();i++)
			lecture.lireGroupe(i);
		sac = lecture.getSac();
		algorithme();
		long fin = System.currentTimeMillis();
		System.out.println("Temps d'éxecution : " + (fin - debut) + " ms");
	}

	public static void algorithme() {
		sac.trierCroissant();
		
		// Mettre combinaison de depart !
		lastBestCombinaison = new Combinaison();
		for (int i = 0; i < sac.getNbGroupes(); i++) {
			lastBestCombinaison.getListeObjet().add(sac.getObjet(i, 0));
		}
		
		// verifier contrainte
		for (int j = 0; j < sac.getNbContraintes(); j++) {
    		if (sac.verifierContrainte(j, lastBestCombinaison)){
    			System.out.println("Contrainte " + (j + 1) + " verifiée");
    		}
    		else
    		{
    			System.out.println("Contrainte " + (j + 1) + " non verifiée");
    		}
    	}
		
		print(lastBestCombinaison);
		System.out.println("Profit = "+lastBestCombinaison.getProfit());
		
	}
	
	public static int scalaire(int[] coef, int[] contraintes) {
		int res = 0;
		for (int i = 0; i < coef.length; i++) {
			res += coef[i] * contraintes[i];
		}
		return res;
	}
	
	public static double norme(int [] vecteur) {
		double res = 0;
		for (int i = 0; i < vecteur.length; i++) {
			res += Math.pow(vecteur[i], 2.0);
		}
		
		res = Math.sqrt(res);	
		return res;
	}

	public static void print(Combinaison combinaison) {
		for (int obj = 0; obj < combinaison.getListeObjet().size(); obj++) {
			System.out.println(combinaison.getListeObjet().get(obj));
		}
	}
}
