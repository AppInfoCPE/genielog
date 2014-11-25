public class TypeProduit {// pain au chocolat ou croissant ou coca
    
		private Categorie cat;
		private String nomType;
	    private float prix;
	    private int qteMiniHeurePleine;
	    private int qteMiniHeureStandard;
	    private int tempCuisson;
	
	
   
    public TypeProduit(Categorie cat,String nomType, float prix, int qteMiniHeurePleine, int qteMiniHeureStandard, int tempCuisson) {
        this.cat=new Categorie(cat);
        this.nomType = nomType;
        this.prix = prix;
        this.qteMiniHeurePleine = qteMiniHeurePleine;
        this.qteMiniHeureStandard = qteMiniHeureStandard;
        this.tempCuisson = tempCuisson;
    }
    
    

    public TypeProduit(String nomType) {
		super();
		this.nomType = nomType;
	}



	TypeProduit(TypeProduit type) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        this.cat=new Categorie(type.getCat());
        this.nomType = type.getNomType();
        this.prix = type.getPrix();
        this.qteMiniHeurePleine = type.getQteMiniHeurePleine();
        this.qteMiniHeureStandard = type.getQteMiniHeureStandard();
        this.tempCuisson = type.getTempCuisson();
    }

    public Categorie getCat() {
        return cat;
    }

    public void setCat(Categorie cat) {
        this.cat = cat;
    }
   

    public float getPrix() {
        return prix;
    }

    public void setPrix(float prix) {
        this.prix = prix;
    }

    public int getQteMiniHeurePleine() {
        return qteMiniHeurePleine;
    }

    public void setQteMiniHeurePleine(int qteMiniHeurePleine) {
        this.qteMiniHeurePleine = qteMiniHeurePleine;
    }

    public int getQteMiniHeureStandard() {
        return qteMiniHeureStandard;
    }

    public void setQteMiniHeureStandard(int qteMiniHeureStandard) {
        this.qteMiniHeureStandard = qteMiniHeureStandard;
    }

    public int getTempCuisson() {
        return tempCuisson;
    }

    public void setTempCuisson(int tempCuisson) {
        this.tempCuisson = tempCuisson;
    }
    
    public String toString(){
        return this.getCat()+" "+this.getNomType()+" "+Float.toString(this.getPrix())+" "+Integer.toString(this.getQteMiniHeurePleine())+" "+Integer.toString(this.getQteMiniHeureStandard())+" "+Integer.toString(this.getTempCuisson())+"\n";
        
    }

	public String getNomType() {
		return nomType;
	}

	public void setNomType(String nomType) {
		this.nomType = nomType;
	}
    
}
