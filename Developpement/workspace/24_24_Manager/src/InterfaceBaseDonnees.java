

import java.util.ArrayList;

public class InterfaceBaseDonnees {
	
	public InterfaceBaseDonnees(){
		DatabaseAccess.initialisation();
	}
	
	public ArrayList<Utilisateur> recupererUtilisateurs() {
		ArrayList<String[]> requete = DatabaseAccess.jdbcExecuteQuery("SELECT * FROM managerappinfo.UTILISATEUR");
		ArrayList<Utilisateur> listUtilisateurs = new ArrayList<Utilisateur>();
		for (int i = 0; i < requete.size(); i++) {
			listUtilisateurs.add(new Utilisateur(requete.get(i)[0], requete.get(i)[1], requete.get(i)[2], requete.get(i)[3], requete.get(i)[4]));
		}
		
		return listUtilisateurs;
	}

}
