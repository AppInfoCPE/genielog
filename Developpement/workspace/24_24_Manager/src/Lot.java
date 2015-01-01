
public class Lot {
		

private int id;
private Commande cmd;
private int qte;
private String typeProduit;
private int status;

    public Lot(Commande cmd, int qte, String typeProduit, int status) {
        this.cmd = cmd;
        this.qte = qte;
        this.typeProduit = typeProduit;
        this.status = status;
    }

  /*  public int getId() {
       
    
    }

    public void setId(int id) {
        this.id = id;
    }*/

    public Commande getCmd() {
        return cmd;
    }

    public void setCmd(Commande cmd) {
        this.cmd = cmd;
    }

    public int getQte() {
        return qte;
    }

    public void setQte(int qte) {
        this.qte = qte;
    }

    public String getTypeProduit() {
        return typeProduit;
    }

    public void setTypeProduit(String typeProduit) {
        this.typeProduit = typeProduit;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

 
}

