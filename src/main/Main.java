package main;

import java.util.ArrayList;

import entites.Combinaison;
import entites.Ecriture;
import entites.Lecture;
import entites.Objet;
import entites.Sac;

public class Main {

	static int counter = 0;
	static Sac sac;
	static Combinaison lastBestCombinaison = null;
	static long debut = 0;
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		for (int i = 1; i < 14; i++) {
			debut = System.currentTimeMillis();
			if ( i == 8)
				continue;
			String filename = "I" + i;
			System.out.println(filename);
			Lecture lecture = new Lecture("instances/" + filename + ".txt");
			lecture.lireEntete();
			lecture.lireContraintesCapacite();
			for (int j = 0; j < lecture.getSac().getNbGroupes(); j++)
				lecture.lireGroupe(j);
			sac = lecture.getSac();
			algorithme();
			long fin = System.currentTimeMillis();
			System.out.println("Temps d'éxecution : " + (fin - debut) + " ms");
		}
		for (int i = 1; i < 21; i++) {
			debut = System.currentTimeMillis();
			String filename = "INST" + (i < 10 ? "0" + i : i);
			System.out.println(filename);
			Lecture lecture = new Lecture("instances/" + filename + ".txt");
			lecture.lireEntete();
			lecture.lireContraintesCapacite();
			for (int j = 0; j < lecture.getSac().getNbGroupes(); j++)
				lecture.lireGroupe(j);
			sac = lecture.getSac();
			algorithme();
			long fin = System.currentTimeMillis();
			System.out.println("Temps d'éxecution : " + (fin - debut) + " ms");
		}
	}

	public static void algorithme() {
		Combinaison tmp = new Combinaison();
		Objet objAChanger = null;

		sac.trierDecroissant();

		// Mettre combinaison de depart !
		lastBestCombinaison = new Combinaison();
		for (int i = 0; i < sac.getNbGroupes(); i++) {
			lastBestCombinaison.getListeObjet().add(sac.getObjet(i, 0));
		}

		tmp.setListeObjet(lastBestCombinaison.getListeObjet());
		System.out.println("Profit = "+lastBestCombinaison.getProfit());

		do
		{
			lastBestCombinaison.setListeObjet(tmp.getListeObjet());
			//print(lastBestCombinaison);

			objAChanger = getChangement(objAChanger);
			ArrayList<Objet> tmpliste = new ArrayList<Objet>();
			for (Objet tmpobj : lastBestCombinaison.getListeObjet()) {
				tmpliste.add(tmpobj);
			}

			tmp.setListeObjet(tmpliste);
			if (objAChanger == null)
				break;

			setChangement(objAChanger, tmp);

		} while(!verifierContraintes(tmp) && System.currentTimeMillis() - debut < 59500);

		if ( verifierContraintes(tmp) )
		{
			lastBestCombinaison.setListeObjet(tmp.getListeObjet());
			System.out.println("Profit = "+lastBestCombinaison.getProfit());
		}
		else
		{
			System.out.println("Impossible");
		}
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

	public static void reverse(int[] input) {
		int last = input.length - 1;
		int middle = input.length / 2;
		for (int i = 0; i <= middle; i++) {
			int temp = input[i];
			input[i] = input[last - i];
			input[last - i] = temp;
		}
	}

	public static void print(Combinaison combinaison) {
		for (int obj = 0; obj < combinaison.getListeObjet().size(); obj++) {
			System.out.println(combinaison.getListeObjet().get(obj));
			System.out.println("Ressources : " + ressource(combinaison.getListeObjet().get(obj)));
		}
	}

	public static Objet getChangement(Objet exclure) {
		Objet choisit = null;
		double differenceChoisit = 0.0;
		int j = 0;

		for (int i = 0; i < sac.getNbGroupes(); i++) {
			Objet actuel = lastBestCombinaison.getObjetFromGroup(i);
			//System.out.println(i);

			if ( actuel.getPosition() + 1 < sac.getObjParGroupe())
			{
				//System.out.println("entre");
				Objet tmp = sac.getObjet(i, actuel.getPosition() + 1);
				if (exclure != null && tmp.getGroupe() == exclure.getGroupe() && tmp.getPosition() == exclure.getPosition()) {
					System.out.println("lol");
				}
				if (choisit == null) {
					choisit = tmp;
					differenceChoisit = ressource(choisit) - ressource(actuel);
				}
				double differenceTmp = ressource(tmp) - ressource(actuel);
				//System.out.println(differenceChoisit);

				if (differenceTmp < differenceChoisit) {
					choisit = new Objet(tmp.getProfit(), tmp.getGroupe(), tmp.getCoef(), tmp.getPosition());;
					differenceChoisit = differenceTmp;
				}
			}

		}
		//System.out.println(choisit);
		return choisit;
	}

	public static void setChangement(Objet change, Combinaison comb) {
		Objet old = null;
		for(Objet obj : comb.getListeObjet()) {
			if (obj.getGroupe() == change.getGroupe()) {
				old = obj;
			}
		}

		comb.getListeObjet().remove(old);
		comb.getListeObjet().add(change);
	}

	public static boolean verifierContraintes(Combinaison comb) {
		for (int j = 0; j < sac.getNbContraintes(); j++) {
			if (!sac.verifierContrainte(j, comb)){
				return false;
			}
		}
		return true;
	}
}
