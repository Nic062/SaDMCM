package main;

import java.util.ArrayList;

import entites.Combinaison;
import entites.Lecture;
import entites.Objet;
import entites.Sac;

public class Main {

	static int counter = 0;
	static Sac sac;
	static Combinaison lastBestCombinaison = null;
	static ArrayList<Combinaison> listeComb = new ArrayList<Combinaison>();

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
		sac = lecture.getSac();
		sac.trierDecroissant();
		algorithme();
		
		long fin = System.currentTimeMillis();
		System.out.println("Temps d'éxecution : " + (fin - debut) + " ms");
	}

	public static void algorithme() {
		combin2(0, sac.getListObjet(), null);
		
		print(lastBestCombinaison);
		System.out.println("Profit = "+lastBestCombinaison.getProfit());
		
		
		
	}

	public static void print(Combinaison combinaison) {
		for (int obj = 0; obj < combinaison.getListeObjet().size(); obj++) {
			System.out.println(combinaison.getListeObjet().get(obj));
		}
	}
	
	public static void combin2(int depth, Objet[][]matrix, Objet[] output){
        Objet[] row = matrix[depth];

        if(depth == 0) {
            counter = 0;
            output = new Objet[matrix.length];
            System.out.println("Taille de la matrice: " + matrix.length);
        }

        for(int i=0; i<row.length; i++) {
            output[depth] = row[i];

            if(depth == (matrix.length-1)) {
                
            	Combinaison comb = new Combinaison();
            	for (Objet objet : output) {
					comb.getListeObjet().add(objet);
				}
            	
            	
            	boolean ajout = true;
            	for(int j=0;j<sac.getNbContraintes();j++){
            		if(!sac.verifierContrainte(j, comb)){
            			ajout = false;
            			break;
            		}
            	}
            	if(ajout){
            		if(lastBestCombinaison==null || lastBestCombinaison.getProfit()<comb.getProfit()){
            			lastBestCombinaison = comb;
            		}
            	}
            	
                counter++;
            } else {
                combin2(depth+1, matrix, output);
            }
        }
    }

}
