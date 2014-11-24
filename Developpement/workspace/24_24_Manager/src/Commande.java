import java.util.Date;


public class Commande {
 private String id;
 static String id_bis="0"; 
 private Date dateCmd;
 private Lot lot;
 
 	Commande(Date dateCmd,String typeLot,int quantite, int numLot){
 		
 	 int i_bis = Integer.parseInt(id_bis);
 	 int i = i_bis;
 	 id = Integer.toString(i);
 	 i_bis ++; 
     id_bis=Integer.toString(i_bis);
 
	 this.dateCmd=dateCmd;
	 
	 this.lot=new Lot(typeLot,quantite,numLot);
	 
 	}

	public String getId() {
		return id;
	}
	
	public void setId(String id) {
		this.id = id;
	}
	
	public Date getDateCmd() {
		return dateCmd;
	}
	
	public void setDateCmd(Date dateCmd) {
		this.dateCmd = dateCmd;
	}
	
	public Lot getLot() {
		return lot;
	}
	
	public void setLot(Lot lot) {
		this.lot = lot;
	}
 
 
 
 
}
