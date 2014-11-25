
public class TypeProduit {
	
	private String nomType;
	private float prixType;
	private String cheminImage;
	private float tempsCuisson;
	private int qt�MinimumHS; // HS : heure standart
	private int qt�MinimumHP; // HP : heure de pointe
	public TypeProduit(String nomType, int qt�MinimumHS,String cheminImage) {
		super();
		this.nomType = nomType;
		this.qt�MinimumHS = qt�MinimumHS;
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
	public int getQt�MinimumHS() {
		return qt�MinimumHS;
	}
	public void setQt�MinimumHS(int qt�MinimumHS) {
		this.qt�MinimumHS = qt�MinimumHS;
	}
	public int getQt�MinimumHP() {
		return qt�MinimumHP;
	}
	public void setQt�MinimumHP(int qt�MinimumHP) {
		this.qt�MinimumHP = qt�MinimumHP;
	}
	
	
	
	
}
