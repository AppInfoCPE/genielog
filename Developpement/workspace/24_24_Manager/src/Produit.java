import java.util.Date;


public class Produit {
    
    TypeProduit type;
    private String status; //nom produit : pain au chocolat
    private Date peremption;

    public Produit(TypeProduit type, String status, Date peremption) {
        this.type = new TypeProduit(type);
        this.status = status;
        this.peremption = peremption;
    }

    public Produit(Produit prod) {
        this.type = new TypeProduit(prod.getType());
        this.status = prod.getStatus();
        this.peremption = prod.getPeremption();
        // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    
    public TypeProduit getType() {
        return type;
    }

    public void setType(TypeProduit type) {
        this.type = type;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getPeremption() {
        return peremption;
    }

    public void setPeremption(Date peremption) {
        this.peremption = peremption;
    }

    public String toString(){
        return this.getType()+" "+this.getStatus()+" "+this.getPeremption()+"\n";
    }
   
}
