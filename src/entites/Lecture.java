package entites;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;

public class Lecture {
	
	private String fichier;
	private BufferedReader lecteurAvecBuffer;
	private Sac sac;
	
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
			int nbrGroupe = Integer.parseInt(ligne.split("-")[0]); 
			int nbrObjetsParGroupe = Integer.parseInt(ligne.split("-")[1]); 
			int nbrContraintes = Integer.parseInt(ligne.split("-")[2]);
			
			this.sac = new Sac(nbrGroupe, nbrObjetsParGroupe, nbrContraintes);
			
			System.out.println("Nombre de groupe = "+ nbrGroupe+" Nombre d'objets par groupe = "+nbrObjetsParGroupe+" Nombre de Contraintes = "+nbrContraintes);
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}
	
	public void lireContraintesCapacite(){
		try {
			String ligne = lecteurAvecBuffer.readLine();
			for(int i=0;i<ligne.split("-").length;i++) {
				this.sac.setContraintes(i, Integer.parseInt(ligne.split("-")[i]));
			}		
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		
		System.out.println("Contraintes de capacité = "+Arrays.toString(this.sac.getContraintes()));
	}
	
	public void lireGroupe(int numGroupe){
		for(int i=0;i<this.sac.getObjParGroupe();i++){
			lireObjet(numGroupe,i);
		}
	}
	
	public BufferedReader getLecteurAvecBuffer() {
		return lecteurAvecBuffer;
	}

	public void setLecteurAvecBuffer(BufferedReader lecteurAvecBuffer) {
		this.lecteurAvecBuffer = lecteurAvecBuffer;
	}

	private void lireObjet(int numGroupe, int numObjet) {
		try {
			String ligne = lecteurAvecBuffer.readLine();
			float profit = Float.parseFloat(ligne.split("-")[0]);
			int contraintes[] = new int[this.sac.getNbContraintes()]; // Initialisation du tableau de contraintes pour l'objet 
			
			for(int j=1;j<=this.sac.getNbContraintes();j++){
				contraintes[j-1]=Integer.parseInt(ligne.split("-")[j].trim());
			}
			
			Objet obj = new Objet(profit, numGroupe, contraintes, numObjet);
			this.sac.addObjet(numGroupe, numObjet, obj);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public Sac getSac() {
		return sac;
	}

	public void setSac(Sac sac) {
		this.sac = sac;
	}
	
}
