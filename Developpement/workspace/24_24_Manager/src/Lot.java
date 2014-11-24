
public class Lot {
	
	
	
private String typeLot;
private int quantite;
private int numLot;
 
 
	Lot(String typeLot,int quantite, int numLot ){
		this.typeLot=typeLot;
		this.quantite=quantite;
		this.numLot=numLot;
	}


	public String getTypeLot() {
		return typeLot;
	}
	public void setTypeLot(String typeLot) {
		this.typeLot = typeLot;
	}
	public int getQuantite() {
		return quantite;
	}
	public void setQuantite(int quantite) {
		this.quantite = quantite;
	}
	public int getNumLot() {
		return numLot;
	}
	public void setNumLot(int numLot) {
		this.numLot = numLot;
	}
	 
 
 
}
