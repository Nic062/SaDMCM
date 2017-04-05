package main;

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
		long fin = System.currentTimeMillis();
		System.out.println("Temps d'éxecution : " + (fin - debut) + " ms");
	}

	public static void algorithme(Sac sac) {
		
	}

}
