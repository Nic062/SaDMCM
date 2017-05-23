package main;

import java.util.Arrays;

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
		sac.trierDecroissant();
		algorithme();
		long fin = System.currentTimeMillis();
		System.out.println("Temps d'éxecution : " + (fin - debut) + " ms");
	}

	public static void algorithme() {
		int sc = 0;
		for (int i : sac.getContraintes()) sc += i;
		System.out.println("Somme contrainte = "+sc);
		int vcmt = sc/sac.getNbGroupes();
		System.out.println("VCMT = "+vcmt);
		Objet[][] listeObj = sac.getListObjet();
		Objet[] ObjChoisit = new Objet[sac.getNbGroupes()];

		for(int i=0;i<sac.getNbGroupes();i++){
			for(int j=0;j<sac.getObjParGroupe();j++){
				int vcm = 0; 
				for (int k : listeObj[i][j].getCoef()) vcm += k;
				if(vcm<=vcmt){
					ObjChoisit[i] = listeObj[i][j];
					break;
				}
			}	
		}
		int[] sumCoef = new int[sac.getNbGroupes()];
		for(int i=0;i<ObjChoisit.length;i++){
			System.out.println(ObjChoisit[i].toString());
			for(int j=0;j<ObjChoisit[i].getCoef().length;j++){
				sumCoef[j] += ObjChoisit[i].getCoef()[j];
			}
		}
		//System.out.println("Total = " +Arrays.toString(sumCoef));
		Arrays.sort(sumCoef);
		reverse(sumCoef);		
		System.out.println("Total = " +Arrays.toString(sumCoef));

		//ArrayUtils.reverse(int[] array)
		//Collections.sort(sumCoef);
		//sumCoef.reverse
		/*System.out.println(ObjChoisit.length);
		for(int i=0;i<ObjChoisit.length;i++){
			for (int j=0;j<sac.getContraintes().length;j++){
				System.out.println(ObjChoisit[i].toString());
				if(ObjChoisit[i].getProfit()>sac.getContraintes()[j]){
					System.out.println("Contrainte dépassé !");
				}else{
					System.out.println("Contrainte OK");
				}
			}
		}*/
		
		
		
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
