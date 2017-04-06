package entites;

import java.util.ArrayList;

public class Combinaison {

	private ArrayList<Objet> listeObjet;
	
	public Combinaison() {
		this.listeObjet = new ArrayList<Objet>();
	}

	public ArrayList<Objet> getListeObjet() {
		return listeObjet;
	}

	public void setListeObjet(ArrayList<Objet> listeObjet) {
		this.listeObjet = listeObjet;
	}
	
	public float getProfit() {
		float somme = 0;
		
		for (Objet obj : listeObjet) {
			somme += obj.getProfit();
		}
		
		return somme;
	}
}
