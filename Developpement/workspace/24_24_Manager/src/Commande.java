import java.util.Date;


import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;

/**
 *
 * @author PC
 */
//
public class Commande {
    private int idCmd;
    static int idBis=0;
    private Date DateCmd;
    ArrayList<Produit> listCmd;

    public Commande(Date DateCmd) {
        this.idCmd=idBis;
        idBis++;
        this.idCmd = idCmd;
        this.DateCmd = DateCmd;
        this.listCmd = new ArrayList<Produit>();
    }
    Commande(Commande cmd) {
        this.idCmd=cmd.getIdCmd();
        this.DateCmd=cmd.getDateCmd();
        this.listCmd=cmd.getListCmd();
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    public int getIdCmd() {
        return idCmd;
    }

    public void setIdCmd(int idCmd) {
        this.idCmd = idCmd;
    }


    public Date getDateCmd() {
        return DateCmd;
    }

    public void setDateCmd(Date DateCmd) {
        this.DateCmd = DateCmd;
    }

    public ArrayList<Produit> getListCmd() {
        return listCmd;
    }
    public String AffichageProduitCmd(){
        String s="";
        Iterator it = listCmd.iterator();
        
        while (it.hasNext()){

// itération de la liste

             Produit p = (Produit) it.next();
             s=s+"---"+p.toString();
      //récupération de l'objet se trouvant à l'index courant de la liste

            }
        s=s+"\n";
        return s;
    }
    public String toString(){
        
        
        return this.getIdCmd()+" "+this.getDateCmd()+"\n"+AffichageProduitCmd();
        
    }
    
    public void addProduit(Produit p){
        this.listCmd.add(p);
    }
    
    public void delProduit(Produit p){
        this.listCmd.remove(p);
    }
    
    
}
