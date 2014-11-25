
public class Categorie {
    private String nomCat;
    private int qteMax;
    private int qteMin;

    public Categorie(String nomCat, int qteMax, int qteMin) {
        this.nomCat = nomCat;
        this.qteMax = qteMax;
        this.qteMin = qteMin;
    }

    Categorie(Categorie cat) {
        this.nomCat=cat.getNomCat();
        this.qteMax = cat.getQteMax();
        this.qteMin = cat.getQteMin();
      //  throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public String getNomCat() {
        return nomCat;
    }

    public void setNomCat(String nomCat) {
        this.nomCat = nomCat;
    }

    public int getQteMax() {
        return qteMax;
    }

    public void setQteMax(int qteMax) {
        this.qteMax = qteMax;
    }

    public int getQteMin() {
        return qteMin;
    }

    public void setQteMin(int qteMin) {
        this.qteMin = qteMin;
    }
    
    public String toString(){
        return this.nomCat+" "+Integer.toString(this.getQteMax())+" "+Integer.toString(this.getQteMin())+"\n";
    }
    
}
