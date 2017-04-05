package main;

import java.util.ArrayList;

import entites.Lecture;
import entites.Sac;

public class Main {
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		long debut = System.currentTimeMillis();
		Lecture lecture = new Lecture("instances/I0.txt");
		lecture.lireEntete();
		lecture.lireContraintesCapacite();
		for(int i=0; i<lecture.getSac().getNbGroupes();i++)
			lecture.lireGroupe(i);
		Sac sac = lecture.getSac();
		algorithme(sac);
		for(int i=0; i<lecture.getSac().getNbGroupes();i++)
			for(int j=0;j<lecture.getSac().getObjParGroupe();j++)
				System.out.println(lecture.getSac().getObjet(i, j));
		long fin = System.currentTimeMillis();
		System.out.println("Temps d'éxecution : " + (fin - debut) + " ms");
	}

	public static void algorithme(Sac sac) {
		ArrayList<Sac> listeSac = new ArrayList<Sac>();
		
		for (int i = 0; i < sac.getNbGroupes(); i++) {
			sac.setChoisit(i, 0);
		}

		for (int i = 0; i < sac.getNbGroupes(); i++) {
			for (int j = 0; j < sac.getObjParGroupe(); j++) {
				sac.setChoisit(i, j);
				listeSac.add(sac);
			}
		}

		for (int i = 0; i < sac.getNbContraintes(); i++) {
			for (Sac proposition : listeSac) {
				if (!proposition.verifierContrainte(i)) {
					listeSac.remove(proposition);
				}
			}
		}
		Sac meilleur = listeSac.get(0);
		for (Sac proposition : listeSac) {
			if (proposition.getProfit() > meilleur.getProfit()) {
				meilleur = proposition;
			}
		}
		
		System.out.println("lol");
		for(int i=0; i<meilleur.getNbGroupes();i++)
			for(int j=0;j<meilleur.getObjParGroupe();j++)
				System.out.println(meilleur.getObjet(i, j));
	}

}
