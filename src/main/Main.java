package main;

import entites.Combinaison;
import entites.Lecture;
import entites.Objet;
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
		Lecture lecture = new Lecture("instances/I1.txt");
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
		
		getChangement();
		
		print(lastBestCombinaison);
		System.out.println("Profit = "+lastBestCombinaison.getProfit());
		
	}
	
	public static int scalaire(int[] coef) {
		int res = 0;
		for (int i = 0; i < coef.length; i++) {
			res += coef[i] * sac.getContraintes()[i];
		}
		return res;
	}
	
	public static double norme() {
		double res = 0;
		for (int i = 0; i < sac.getContraintes().length; i++) {
			res += Math.pow(sac.getContraintes()[i], 2.0);
		}
		
		res = Math.sqrt(res);	
		return res;
	}
	
	public static double ressource(Objet obj) {
		int scalaire = scalaire(obj.getCoef());
		double norme = norme();
		
		return scalaire / norme;
	}

	public static void print(Combinaison combinaison) {
		for (int obj = 0; obj < combinaison.getListeObjet().size(); obj++) {
			System.out.println(combinaison.getListeObjet().get(obj));
			System.out.println("Ressources : " + ressource(combinaison.getListeObjet().get(obj)));
		}
	}
	
	public static Objet getChangement() {
		Objet choisit = null;
		for (int i = 0; i < sac.getNbGroupes(); i++) {
			Objet actuel = null;
			for( Objet obj : lastBestCombinaison.getListeObjet()) {
				if ( obj.getGroupe() == i) {
					actuel = obj;
					break;
				}
			}
			
			for (int j = actuel.getPosition() + 1; j < sac.getObjParGroupe(); j++) {
				Objet tmp = sac.getObjet(i, j);
				if (tmp != actuel) {
					if (choisit == null) {
						choisit = new Objet(tmp.getProfit(), tmp.getGroupe(), tmp.getCoef(), tmp.getPosition());
					}
					double differenceTmp = ressource(tmp) - ressource(actuel);
					double differenceChoisit = ressource(choisit) - ressource(actuel);
					System.out.println(i + " " + j);
					System.out.println("Difference tmp : " + differenceTmp);
					System.out.println("Difference choisit : " + differenceChoisit);
					if (differenceTmp < differenceChoisit) {
						System.out.println("Plus petit");
						choisit = new Objet(tmp.getProfit(), tmp.getGroupe(), tmp.getCoef(), tmp.getPosition());;
					}
				}
			}
		}
		return choisit;
	}
}
