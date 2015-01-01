import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;


public class Vente {
	
	private int numVente;
	private Date dateVente;
	private String personne;
	private char typePaiement; //'c' pour chèque, 'b' pour carte bancaire et 'l' pour liquide
	Dao dv;
	public int getNumVente() {
		return numVente;
	}
	public void setNumVente(int numVente) {
		this.numVente = numVente;
	}
	public Date getDateVente() {
		return dateVente;
	}
	public void setDateVente(Date dateVente) {
		this.dateVente = dateVente;
	}
	public char getTypePaiement() {
		return typePaiement;
	}
	public void setTypePaiement(char typePaiement) {
		this.typePaiement = typePaiement;
	}
	
	
	public void vente(int numVente, Date dateVente, char typePaiement){
		this.numVente=numVente;
		this.dateVente=dateVente;
		this.typePaiement=typePaiement;
	}
	
	public  Vente(String personne){
		//mf.crŽerVente();
		dv = new Dao();
		
		//System.out.println(personne);
		this.personne=personne;
		//this.numVente=2;
		this.numVente=creerVente(personne);
		//System.out.println("Vente creer");
		//System.out.println(numVente);
	}
	private int creerVente(String personne) {
		// TODO Auto-generated method stub
		Integer idVente=null;
		String num = null;
		ResultSet resultat;
		//System.out.println(personne);
		dv.executeRequeteInsert("INSERT INTO `VENTE`( `datevente`, `typepaiement`, `identifiant`, `status`) VALUES (NOW(),NULL,'"+personne+"','encour')");

		resultat=dv.executeRequete("SELECT `numvente` FROM 	VENTE  WHERE `status`='encour' and `identifiant`='"+personne+"'");
		int i=0;
		try {
			resultat.last();
			resultat.beforeFirst();
			while (resultat.next()) {
				num=resultat.getString(1);
			}
			idVente=Integer.valueOf(num);
			//System.out.println(idVente);

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return idVente;
	}
	public String getPersonne() {
		return personne;
	}
	public void setPersonne(String personne) {
		this.personne = personne;
	}
	
	
}
