/**
 * 
 */
package entites;

import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;

/**
 * @author vince
 *
 */
public class Ecriture {

	private Objet[][] listObjet;
	private String filename;
	private double profit;
	
	/**
	 * @param listObjet
	 * @param filename
	 */
	public Ecriture(Objet[][] listObjet, String filename, double profit) {
		super();
		this.listObjet = listObjet;
		this.filename = filename;
		this.profit = profit;
	}

	/**
	 * @return the listObjet
	 */
	public Objet[][] getListObjet() {
		return listObjet;
	}

	/**
	 * @param listObjet the listObjet to set
	 */
	public void setListObjet(Objet[][] listObjet) {
		this.listObjet = listObjet;
	}

	/**
	 * @return the filename
	 */
	public String getFilename() {
		return filename;
	}

	/**
	 * @param filename the filename to set
	 */
	public void setFilename(String filename) {
		this.filename = filename;
	}
	
	/**
	 * @return the profit
	 */
	public double getProfit() {
		return profit;
	}

	/**
	 * @param profit the profit to set
	 */
	public void setProfit(double profit) {
		this.profit = profit;
	}

	public void ecrire() {
		try {
			BufferedWriter buffer = new BufferedWriter(new FileWriter(filename + ".out"));
			buffer.write(filename + "\n");
			buffer.write(profit + "\n");
			for (int i = 0; i < listObjet.length; i++) {
				for (int j = 0; j < listObjet[i].length; j++) {
					buffer.write("Objet " + (j + 1) + " du groupe " + (i + 1) + " : ");
					buffer.write(listObjet[i][j].isChoisit() ? "Choisit" : "Pas choisit");
					buffer.write("\n");
				}
			}
			buffer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
