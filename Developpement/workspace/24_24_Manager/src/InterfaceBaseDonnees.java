

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Hashtable;

public class InterfaceBaseDonnees {
	
	public InterfaceBaseDonnees(){
		DatabaseAccess.initialisation();
	}
	
	/*public ArrayList<Utilisateur> recupererUtilisateurs() {
		ArrayList<String[]> requete = DatabaseAccess.jdbcExecuteQuery("SELECT * FROM managerappinfo.UTILISATEUR");
		ArrayList<Utilisateur> listUtilisateurs = new ArrayList<Utilisateur>();
		for (int i = 0; i < requete.size(); i++) {
			listUtilisateurs.add(new Utilisateur(requete.get(i)[0], requete.get(i)[1], requete.get(i)[2], requete.get(i)[3], requete.get(i)[4]));
		}
		
		return listUtilisateurs;
<<<<<<< HEAD
	}
	*/
	public  Hashtable<Integer,TypeProduit> recupererStockFrigo() {
		Hashtable<Integer,TypeProduit> stockFrigo = new Hashtable<Integer,TypeProduit>();
		ResultSet requeteLot = DatabaseAccess.jdbcExecuteQuery("SELECT * FROM managerappinfo.LOT");
		ArrayList<Utilisateur> listUtilisateurs = new ArrayList<Utilisateur>();
	
		return stockFrigo;
	}

	
	public ArrayList<Utilisateur> recupererUtilisateurs() {
		ArrayList<Utilisateur> listUtilisateurs = new ArrayList<Utilisateur>();
		ResultSet requete = DatabaseAccess.jdbcExecuteQuery("SELECT * FROM managerappinfo.UTILISATEUR");
		try {
			while (requete.next()) {
				listUtilisateurs.add(new Utilisateur(requete.getString(1), requete.getString(2), requete.getString(3), requete.getString(4), requete.getString(5)));			
	
			}
			requete.close();
		} catch (Exception sqlExcept) {
			sqlExcept.printStackTrace();
			System.out.println("Execute Query Refused: Connection not established.\n");
		}
		return listUtilisateurs;
	}	
}
