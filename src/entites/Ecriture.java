/**
 * 
 */
package entites;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class Ecriture {

	private Sac sac;
	private String filename;
	
	public Ecriture(Sac sac, String filename, double profit) {
		super();
		this.setSac(sac);
		this.filename = filename;
	}

	public Sac getSac() {
		return sac;
	}

	public void setSac(Sac sac) {
		this.sac = sac;
	}

	public String getFilename() {
		return filename;
	}

	public void setFilename(String filename) {
		this.filename = filename;
	}

	public void ecrire() {
		try {
			BufferedWriter buffer = new BufferedWriter(new FileWriter(filename + ".out"));
			buffer.write(filename + "\n");
			buffer.write(sac.getProfit() + "\n");
			for (int i = 0; i < sac.getNbGroupes(); i++) {
				for (int j = 0; j < sac.getObjParGroupe(); j++) {
					buffer.write("Objet " + (j + 1) + " du groupe " + (i + 1) + " : ");
					buffer.write(sac.getObjet(i, j).isChoisit() ? "Choisit -> " : "Pas choisit -> ");
					buffer.write(sac.getObjet(i, j).getProfit() + " ");
					for (int k = 0; k < sac.getObjet(i, j).getContraintes().length; k++) {
						buffer.write(sac.getObjet(i, j).getContraintes()[k] + " ");
					}
					buffer.write("\n");
				}
			}
			buffer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
