
public class TypeProduit {
	
	private String nomType;
	private float prixType;
	private String cheminImage;
	private float tempsCuisson;
	private int qtéMinimumHS; // HS : heure standart
	private int qtéMinimumHP; // HP : heure de pointe
	public TypeProduit(String nomType, int qtéMinimumHS,String cheminImage) {
		super();
		this.nomType = nomType;
		this.qtéMinimumHS = qtéMinimumHS;
		this.cheminImage=cheminImage;
	}
	
	public String getNomType() {
		return nomType;
	}
	public void setNomType(String nomType) {
		this.nomType = nomType;
	}
	public float getPrixType() {
		return prixType;
	}
	public void setPrixType(float prixType) {
		this.prixType = prixType;
	}
	public String getCheminImage() {
		return cheminImage;
	}
	public void setCheminImage(String cheminImage) {
		this.cheminImage = cheminImage;
	}
	public float getTempsCuisson() {
		return tempsCuisson;
	}
	public void setTempsCuisson(float tempsCuisson) {
		this.tempsCuisson = tempsCuisson;
	}
	public int getQtéMinimumHS() {
		return qtéMinimumHS;
	}
	public void setQtéMinimumHS(int qtéMinimumHS) {
		this.qtéMinimumHS = qtéMinimumHS;
	}
	public int getQtéMinimumHP() {
		return qtéMinimumHP;
	}
	public void setQtéMinimumHP(int qtéMinimumHP) {
		this.qtéMinimumHP = qtéMinimumHP;
	}
	
	
	
	
}
