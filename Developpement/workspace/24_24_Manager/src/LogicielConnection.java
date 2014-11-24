

import java.util.ArrayList;

public class LogicielConnection {
	
	InterfaceBaseDonnees interfaceBDD;
	ArrayList<Utilisateur> listUtilisateurs;
	
	public LogicielConnection(){
		interfaceBDD = new InterfaceBaseDonnees();
		initialisation();
	}

	private void initialisation() {
		listUtilisateurs = interfaceBDD.recupererUtilisateurs();
	}

	public Utilisateur seConnecter(String id, String mdp, String role) {
		Utilisateur tempUtilisateur = new Utilisateur(-1, id, mdp, role);
		for (int i = 0; i < listUtilisateurs.size(); i++) {
			if (listUtilisateurs.get(i).equals(tempUtilisateur))
				return listUtilisateurs.get(i);
		}
		return null;
	}
}
