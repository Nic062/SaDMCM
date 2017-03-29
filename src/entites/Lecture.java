package entites;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;

public class Lecture {
	
	private String fichier;
	private BufferedReader lecteurAvecBuffer;
	private int nbrGroupe;
	private int nbrObjetsParGroupe;
	private int nbrContraintes;
	private int[] contraintesCapacite;
	private Objet[][] listeObjets;
	
	public String getFichier() {
		return fichier;
	}

	public void setFichier(String fichier) {
		this.fichier = fichier;
	}

	public Lecture(String fichier) {
		this.fichier = fichier;
		try {
			lecteurAvecBuffer  = new BufferedReader(new FileReader(fichier));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	public void lireEntete(){
		try {
			String ligne = lecteurAvecBuffer.readLine();
			nbrGroupe = Integer.parseInt(ligne.split("-")[0]); 
			nbrObjetsParGroupe = Integer.parseInt(ligne.split("-")[1]); 
			nbrContraintes = Integer.parseInt(ligne.split("-")[2]);
			listeObjets = new Objet[nbrGroupe][nbrObjetsParGroupe];
		} catch (IOException e1) {
			// TODO Bloc catch généré automatiquement
			e1.printStackTrace();
		}
		System.out.println("Nombre de groupe="+ nbrGroupe+" Nombre d'bjets par groupe="+nbrObjetsParGroupe+" Nombre de Contraintes="+nbrContraintes);
	}
	
	public void lireContraintesCapacite(){
		try {
			String ligne = lecteurAvecBuffer.readLine();
			contraintesCapacite = new int[nbrContraintes];
			for(int i=0;i<ligne.split("-").length;i++)
				contraintesCapacite[i]=Integer.parseInt(ligne.split("-")[i]);
			
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		System.out.println("Contraintes de capacité ="+Arrays.toString(contraintesCapacite));
	}
	
	public void lireGroupe(int numGroupe){
		for(int i=0;i<nbrObjetsParGroupe;i++){
			lireObjet(numGroupe,i);
		}		
	}
	
	private void lireObjet(int numGroupe, int numObjet) {
		try {
			String ligne = lecteurAvecBuffer.readLine();
			float profit = Float.parseFloat(ligne.split("-")[0]);
			int contraintes[] = new int[nbrContraintes]; // Initialisation du tableau de contraintes pour l'objet 
			for(int j=1;j<nbrContraintes+1;j++){
				contraintes[j-1]=Integer.parseInt(ligne.split("-")[j]);
			}
			Objet obj = new Objet(profit, numGroupe, contraintes);
			listeObjets[numGroupe][numObjet] = obj; 
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		//System.out.println(Arrays.toString(listeObjets).toString());
		for(int i=0;i<listeObjets.length;i++)
			for(int j=0; j<listeObjets[i].length;j++)
				System.out.println(listeObjets[i][j].getProfit());
		
	}

	
	
	public int getNbrGroupe() {
		return nbrGroupe;
	}

	public void setNbrGroupe(int nbrGroupe) {
		this.nbrGroupe = nbrGroupe;
	}

	public int getNbrObjetsParGroupe() {
		return nbrObjetsParGroupe;
	}

	public void setNbrObjetsParGroupe(int nbrObjetsParGroupe) {
		this.nbrObjetsParGroupe = nbrObjetsParGroupe;
	}

	public int getNbrContraintes() {
		return nbrContraintes;
	}

	public void setNbrContraintes(int nbrContraintes) {
		this.nbrContraintes = nbrContraintes;
	}

	public int[] getContraintesCapacite() {
		return contraintesCapacite;
	}

	public void setContraintesCapacite(int[] contraintesCapacite) {
		this.contraintesCapacite = contraintesCapacite;
	}

	public void  lireParGroupe(int nbrLigne){
		
	}
	
	

}
