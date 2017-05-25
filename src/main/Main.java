package main;

import java.util.ArrayList;

import com.sun.org.apache.bcel.internal.generic.NEW;

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

		/**for (int i = 1; i < 14; i++) {
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
			Ecriture ecriture = new Ecriture(lastBestCombinaison, filename, lastBestCombinaison.getProfit());
			ecriture.ecrire();
			long fin = System.currentTimeMillis();
			System.out.println("Temps d'éxecution : " + (fin - debut) + " ms");
		}*/
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
			Ecriture ecriture = new Ecriture(lastBestCombinaison, filename, lastBestCombinaison.getProfit());
			ecriture.ecrire();
			long fin = System.currentTimeMillis();
			System.out.println("Temps d'éxecution : " + (fin - debut) + " ms");
		}
	}

	public static void algorithme() {
		Combinaison tmp = new Combinaison();
		Objet objAChanger = null;

		sac.trierCroissant();

		// Mettre combinaison de depart !
		lastBestCombinaison = new Combinaison();
		for (int i = 0; i < sac.getNbGroupes(); i++) {
			lastBestCombinaison.getListeObjet().add(sac.getObjet(i, 0));
		}

		// chercher contrainte minimum
		if ( !verifierContraintes(lastBestCombinaison) )
		{
			System.out.println("contrainte pas verifier init");
			setContrainteMini();
		}

		tmp.setListeObjet(lastBestCombinaison.getListeObjet());
		System.out.println("Profit = "+lastBestCombinaison.getProfit());

		do
		{
			lastBestCombinaison.setListeObjet(tmp.getListeObjet());

			objAChanger = getChangement(objAChanger);
			ArrayList<Objet> tmpliste = new ArrayList<Objet>();
			for (Objet tmpobj : lastBestCombinaison.getListeObjet()) {
				tmpliste.add(tmpobj);
			}

			tmp.setListeObjet(tmpliste);
			setChangement(objAChanger, tmp);

		} while(verifierContraintes(tmp) && System.currentTimeMillis() - debut < 59500);

		if ( verifierContraintes(lastBestCombinaison) )
		{
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

	public static void print(Combinaison combinaison) {
		for (int obj = 0; obj < combinaison.getListeObjet().size(); obj++) {
			System.out.println(combinaison.getListeObjet().get(obj));
			System.out.println("Ressources : " + ressource(combinaison.getListeObjet().get(obj)));
		}
	}

	public static Objet getChangement(Objet exclure) {
		Objet choisit = null;
		double differenceChoisit = 0.0;

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
				if (tmp != actuel && tmp != exclure) {
					if (choisit == null) {
						choisit = new Objet(tmp.getProfit(), tmp.getGroupe(), tmp.getCoef(), tmp.getPosition());
						differenceChoisit = ressource(choisit) - ressource(actuel);
					}
					double differenceTmp = ressource(tmp) - ressource(actuel);

					if (differenceTmp < differenceChoisit) {
						choisit = new Objet(tmp.getProfit(), tmp.getGroupe(), tmp.getCoef(), tmp.getPosition());;
						differenceChoisit = differenceTmp;
						break;
					}
				}
			}
		}
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
				System.out.println("Contrainte " + (j + 1) + " violée");
				return false;
			}
		}
		return true;
	}

	public static void setContrainteMini()
	{
		Combinaison tmp = new Combinaison();

		for (int i = 0; i < sac.getNbGroupes(); i++)
		{
			ArrayList<Objet> tmpliste = new ArrayList<Objet>();
			for (Objet tmpobj : lastBestCombinaison.getListeObjet()) {
				tmpliste.add(tmpobj);
			}

			tmp.setListeObjet(tmpliste);

			Objet obj = tmp.getObjetFromGroup(i);

			for(int j = 1; j < sac.getObjParGroupe(); j++)
			{
				if ( sac.getObjet(i, j).getSommeCoef() < obj.getSommeCoef())
					obj = new Objet(sac.getObjet(i, j).getProfit(), i, sac.getObjet(i, j).getCoef(), j);

				setChangement(obj, tmp);
				if ( verifierContraintes(tmp))
				{
					setChangement(obj, lastBestCombinaison);
				}
			}
		}
	}
}
