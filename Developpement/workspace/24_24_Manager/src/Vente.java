import java.util.Date;


public class Vente {
	
	private int numVente;
	private Date dateVente;
	private char typePaiement; //'c' pour chèque, 'b' pour carte bancaire et 'l' pour liquide
	
	public int getNumVente() {
		return numVente;
	}
	public void setNumVente(int numVente) {
		this.numVente = numVente;
	}
	public Date getDateVente() {
		return dateVente;
	}
	public void setDateVente(Date dateVente) {
		this.dateVente = dateVente;
	}
	public char getTypePaiement() {
		return typePaiement;
	}
	public void setTypePaiement(char typePaiement) {
		this.typePaiement = typePaiement;
	}
	
	
	
}
