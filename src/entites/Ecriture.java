/**
 * 
 */
package entites;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class Ecriture {

	private Combinaison combinaison;
	private String filename;

	public Ecriture(Combinaison combinaison, String filename, double profit) {
		super();
		this.setCombinaison(combinaison);
		this.filename = filename;
	}

	public Combinaison getCombinaison() {
		return combinaison;
	}

	public void setCombinaison(Combinaison combinaison) {
		this.combinaison = combinaison;
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
			buffer.write(combinaison.getProfit() + "\n");
			for (int i = 0; i < combinaison.getListeObjet().size(); i++) {
				buffer.write("Objet " + combinaison.getListeObjet().get(i).getPosition() + " du groupe " + combinaison.getListeObjet().get(i).getGroupe() + " : ");
				buffer.write(combinaison.getListeObjet().get(i).getProfit() + " ");
				for (int k = 0; k < combinaison.getListeObjet().get(i).getCoef().length; k++) {
					buffer.write(combinaison.getListeObjet().get(i).getCoef()[k] + " ");
				}
				buffer.write("\n");
			}
			buffer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
