import java.sql.ResultSet;

public class Dao {

	public Dao() {
		DatabaseAccess.initialisation();
	}

	public ResultSet executeRequete(String requete){
		
		ResultSet resultat = null;
		
		try {
			resultat = DatabaseAccess.jdbcExecuteQuery(requete);
		} catch (Exception sqlExcept) {
			sqlExcept.printStackTrace();
			System.out.println("Erreur execution requete.\n");
		}
		return resultat;
	}
	
	public void executeRequeteInsert(String requete){
		
		//ResultSet resultat = null;
		
		try {
			DatabaseAccess.jdbcExecute(requete);
		} catch (Exception sqlExcept) {
			sqlExcept.printStackTrace();
			System.out.println("Erreur execution requete.\n");
		}
		//return resultat;
	}
	
	/*public ArrayList<String> recupererBoissonTest(){
		
		ArrayList<String> listproduit = new ArrayList<String>();
		
		ResultSet requete;
		requete = DatabaseAccess.jdbcExecuteQuery("SELECT * FROM TYPEPRODUIT");
		try {
			//requete.last();
			//listproduit= new Object[requete.getRow()][2];
			int i=0;
			while (requete.next()) {
				listproduit.add(requete.getString(1));	
			}
			requete.close();
		} catch (Exception sqlExcept) {
			sqlExcept.printStackTrace();
			System.out.println("Erreur requete recuperer boisson.\n");
		}
		return listproduit;
	}*/
}
