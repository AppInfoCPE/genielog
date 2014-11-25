

import java.util.ArrayList;
import java.util.Hashtable;

public class InterfaceBaseDonnees {
	
	public InterfaceBaseDonnees(){
		DatabaseAccess.initialisation();
	}
	
	public ArrayList<Utilisateur> recupererUtilisateurs() {
		ArrayList<String[]> requete = DatabaseAccess.jdbcExecuteQuery("SELECT * FROM managerappinfo.UTILISATEUR");
		ArrayList<Utilisateur> listUtilisateurs = new ArrayList<Utilisateur>();
		for (int i = 0; i < requete.size(); i++) {
			listUtilisateurs.add(new Utilisateur(Integer.parseInt(requete.get(i)[0]), requete.get(i)[1],requete.get(i)[2],requete.get(i)[3]));
		}
		
		return listUtilisateurs;
	}
	
	public  Hashtable<Integer,TypeProduit> recupererStockFrigo() {
		Hashtable<Integer,TypeProduit> stockFrigo = new Hashtable<Integer,TypeProduit>();
		ArrayList<String[]> requeteLot = DatabaseAccess.jdbcExecuteQuery("SELECT * FROM managerappinfo.LOT");
		ArrayList<Utilisateur> listUtilisateurs = new ArrayList<Utilisateur>();
	
		return stockFrigo;
	}

}
