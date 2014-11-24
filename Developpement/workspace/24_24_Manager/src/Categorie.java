
public class Categorie {
 private String nomCat;
 private String qteMax;
 private String qteMin;
    Categorie(String nomCat,String qteMax,String qteMin){
    	this.nomCat=nomCat;
    	this.qteMax=qteMax;
    	this.qteMin=qteMin;
    }
	public String getQteMax() {
		return qteMax;
	}
	public void setQteMax(String qteMax) {
		this.qteMax = qteMax;
	}
	public String getNomCat() {
		return nomCat;
	}
	public void setNomCat(String nomCat) {
		this.nomCat = nomCat;
	}
	public String getQteMin() {
		return qteMin;
	}
	public void setQteMin(String qteMin) {
		this.qteMin = qteMin;
	}
	 
	 
 
}
