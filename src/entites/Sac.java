/**
 * 
 */
package entites;

public class Sac {
	private Objet[][] listObjet;
	private int[] contraintes;
	private int nbGroupes;
	private int objParGroupe;
	private int nbContraintes;
	
	public Sac(int nbGroupes,
			int objParGroupe, int nbContraintes) {
		super();
		this.nbGroupes = nbGroupes;
		this.objParGroupe = objParGroupe;
		this.nbContraintes = nbContraintes;
		
		this.listObjet = new Objet[nbGroupes][objParGroupe];
		this.setContraintes(new int[nbContraintes]);
	}
	
	public int getNbGroupes() {
		return nbGroupes;
	}
	
	public void setNbGroupes(int nbGroupes) {
		this.nbGroupes = nbGroupes;
	}
	
	public int getObjParGroupe() {
		return objParGroupe;
	}
	
	public void setObjParGroupe(int objParGroupe) {
		this.objParGroupe = objParGroupe;
	}
	
	public int getNbContraintes() {
		return nbContraintes;
	}
	
	public void setNbContraintes(int nbContraintes) {
		this.nbContraintes = nbContraintes;
	}
	
	public int[] getContraintes() {
		return contraintes;
	}

	public void setContraintes(int[] contraintes) {
		this.contraintes = contraintes;
	}

	public double getProfit() {
		double profit = 0;
		
		for ( int i = 0; i < this.nbGroupes; i++) {
			boolean choisit = false;
			for (int j = 0; j < this.objParGroupe; j++) {
				if (!choisit && this.listObjet[i][j].isChoisit()) {
					profit += this.listObjet[i][j].getProfit();
				}
			}
		}
		
		return profit;
	}

	public void addObjet(int groupe, int position, Objet obj) {
		this.listObjet[groupe][position] = obj;
	}
	
	public Objet[] getGroupe(int groupe) {
		return this.listObjet[groupe];
	}
	
	public Objet getObjet(int groupe, int position) {
		return this.listObjet[groupe][position];
	}
	
	public double getProfitMax() {
		double max = 0;
		
		for ( int i = 0; i < this.nbGroupes; i++) {
			double profitMax = 0;
			for ( int j = 0; j < this.objParGroupe; j++) {
				if (this.getObjet(i, j).getProfit() > profitMax) {
					profitMax = this.getObjet(i, j).getProfit();
				}
			}
			
			max += profitMax;
		}
		
		return max;
	}
	
	/*
	 * TODO: Verifier contraintes en utilisant le boolean "choisit"
	 * Verifier qu'il y a un obj choisit par groupe ( fonction globale appelant d'autre fonctions ? )
	 */
}
