package main;

import java.util.ArrayList;

import entites.Lecture;
import entites.Objet;
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
		long fin = System.currentTimeMillis();
		System.out.println("Temps d'éxecution : " + (fin - debut) + " ms");
	}

	public static void algorithme(Sac sac) {
		for (int i = 0; i < sac.getNbGroupes(); i++) {
			sac.setChoisit(i, 0);
		}
		
		ArrayList<Sac> listeSac = new ArrayList<Sac>();
		
		int nb = 0;		
		for (int i = 0; i < sac.getObjParGroupe(); i++) {
			sac.setChoisit(0, i);
			for (int j = 0; j < sac.getObjParGroupe(); j++) {
				sac.setChoisit(1, j);
				for (int k = 0; k < sac.getObjParGroupe(); k++) {
					sac.setChoisit(2, k);
					Sac tmp = new Sac(sac.getNbGroupes(), sac.getObjParGroupe(), sac.getNbContraintes());
					tmp.setContraintes(sac.getContraintes());
					
					for (int groupe = 0; groupe < sac.getNbGroupes(); groupe++) {
						for (int obj = 0; obj < sac.getObjParGroupe(); obj++) {
							tmp.addObjet(sac.getObjet(groupe, obj).getGroupe(),
									obj,
									new Objet(sac.getObjet(groupe, obj).getProfit(), groupe, sac.getObjet(groupe, obj).getCoef(), sac.getObjet(groupe, obj).isChoisit()));
						}
					}
					
					listeSac.add(tmp);
					nb++;
				}
			}
		}
		
		ArrayList<Sac> listeValide = new ArrayList<Sac>();
		
		for (int i = 0; i < sac.getNbContraintes(); i++) {
			for (Sac proposition : listeSac) {
				if (proposition.verifierContrainte(i)) {
					listeValide.add(proposition);
				}
			}
		}
		
		Sac meilleur = listeValide.get(0);
		for (Sac proposition : listeSac) {
			if (proposition.getProfit() > meilleur.getProfit()) {
				meilleur = proposition;
			}
		}
		
		print(meilleur);
		System.out.println(meilleur.getProfit());
	}
	
	public static void print(Sac sac) {
		for (int groupe = 0; groupe < sac.getNbGroupes(); groupe++) {
			for (int obj = 0; obj < sac.getObjParGroupe(); obj++) {
				System.out.println(sac.getObjet(groupe, obj));
			}
		}
	}

}
