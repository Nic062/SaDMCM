package main;

import java.util.ArrayList;

import entites.Combinaison;
import entites.Ecriture;
import entites.Lecture;
import entites.Objet;
import entites.Sac;

public class Main {

	static int counter = 0;
	static ArrayList<Combinaison> ListeComb = new ArrayList<Combinaison>();
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
		
		combin2(0, sac.getListObjet(), null);

		ArrayList<Combinaison> listeValide = new ArrayList<Combinaison>();

		for (int i = 0; i < sac.getNbContraintes(); i++) {
			for (Combinaison combinaison : ListeComb) {
				if (sac.verifierContrainte(i, combinaison)) {
					listeValide.add(combinaison);
				}
			}
		}

		Combinaison meilleur = listeValide.get(0);
		for (Combinaison combinaison : listeValide) {
			if (combinaison.getProfit() > meilleur.getProfit()) {
				meilleur = combinaison;
			}
		}

		print(meilleur);
		System.out.println(meilleur.getProfit());
		Ecriture out = new Ecriture(meilleur, "test", meilleur.getProfit());
		out.ecrire();
	}

	public static void print(Combinaison combinaison) {
		for (int obj = 0; obj < combinaison.getListeObjet().size(); obj++) {
			System.out.println(combinaison.getListeObjet().get(obj));
		}
	}
	
	public static void combin2(int depth, Objet[][]matrix, Objet[] output)
    {
        Objet[] row = matrix[depth];

        if(depth == 0) {
            counter = 0;
            output = new Objet[matrix.length];
            System.out.println("matrix length: " + matrix.length);
        }

        for(int i=0; i<row.length; i++) {
            output[depth] = row[i];

            if(depth == (matrix.length-1)) {
                
            	Combinaison comb = new Combinaison();
            	for (Objet objet : output) {
					comb.getListeObjet().add(objet);
				}
            	ListeComb.add(comb);
            	
                counter++;
            } else {
                //recursively generate the combination
                combin2(depth+1, matrix, output);
            }
        }
    }

}
