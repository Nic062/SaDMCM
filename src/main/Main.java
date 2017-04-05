package main;

import entites.Lecture;

public class Main {
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
		lecture.getSac().getNbGroupes();
		for(int i=0; i<lecture.getSac().getNbGroupes();i++)
			for(int j=0;j<lecture.getSac().getObjParGroupe();j++)
				System.out.println(lecture.getSac().getObjet(i, j));
		long fin = System.currentTimeMillis();
		System.out.println("Temps d'éxecution : " + (fin - debut) + " ms");
	}
	
	/* TODO: Mettre en place algo 1 */

}
