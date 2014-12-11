import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;


public class DaoCuisson {
	public DaoCuisson() {
		DatabaseAccess.initialisation();
	}

	public Object [][] recupererProduitACuire() {
		Object [][] produitACuire = null;
		Boolean hp = false;
		Date maDate = new Date();	
		ResultSet requeteHeure = DatabaseAccess.jdbcExecuteQuery("SELECT debut, fin FROM HEURES_POINTES");
		try {
			while (requeteHeure.next()){
				if (requeteHeure.getInt(1) <= maDate.getHours() && maDate.getHours() < requeteHeure.getInt(2)) hp = true;
			}
			requeteHeure.close();
		} catch (Exception sqlExcept) {
			sqlExcept.printStackTrace();
			System.out.println("Erreur execution requete: Recuperer heures pointes.\n");
		}
		ResultSet requete;
		if (hp)
			requete = DatabaseAccess.jdbcExecuteQuery("SELECT nomtype, qtecuireheurepleine FROM TYPEPRODUIT T WHERE qteminiheurepleine > (SELECT count(*) FROM PRODUIT where (status='vente' or status='four') and typeproduit = T.nomtype) and (tempscuisson is not NULL and tempscuisson!=0) and qtecuireheurepleine <= (select sum(quantite) FROM LOT where typeproduit = T.nomtype ) ");
		else
			requete = DatabaseAccess.jdbcExecuteQuery("SELECT nomtype, qtecuireheurestandard FROM TYPEPRODUIT T WHERE qteminiheurestandard > (SELECT count(*) FROM PRODUIT where (status='vente' or status='four') and typeproduit = T.nomtype ) and (tempscuisson is not NULL and tempscuisson!=0)  and qtecuireheurestandard <= (select sum(quantite) FROM LOT where typeproduit = T.nomtype )");
		try {
			requete.last();
			produitACuire = new Object[requete.getRow()][2];
			requete.beforeFirst();
			int i = 0;
			while (requete.next()) {
				produitACuire[i][0] = requete.getString(1); 
				produitACuire[i][1] = requete.getString(2);
				i++;
			}
			requete.close();
		} catch (Exception sqlExcept) {
			sqlExcept.printStackTrace();
			System.out.println("Erreur execution requete: Recuperer produit a cuire.\n");
		}
		return produitACuire;
	}

	public Object[][] recupererProduitAMettreRayon() {
		Object [][] produitAMettreRayon = null;
		Boolean hp = false;
		Date maDate = new Date();	
		ResultSet requeteHeure = DatabaseAccess.jdbcExecuteQuery("SELECT debut, fin FROM HEURES_POINTES");
		try {
			while (requeteHeure.next()){
				if (requeteHeure.getInt(1) <= maDate.getHours() && maDate.getHours() < requeteHeure.getInt(2)) hp = true;
			}
			requeteHeure.close();
		} catch (Exception sqlExcept) {
			sqlExcept.printStackTrace();
			System.out.println("Erreur execution requete: Recuperer heures pointes.\n");
		}
		ResultSet requete;
		if (hp)
			requete = DatabaseAccess.jdbcExecuteQuery("SELECT nomtype, qtecuireheurepleine FROM TYPEPRODUIT T WHERE qteminiheurepleine > (SELECT count(*) FROM PRODUIT where status='vente' and typeproduit = T.nomtype) and (tempscuisson is NULL or tempscuisson=0) and qtecuireheurepleine <= (select sum(quantite) FROM LOT where typeproduit = T.nomtype )");
		else
			requete = DatabaseAccess.jdbcExecuteQuery("SELECT nomtype, qtecuireheurestandard FROM TYPEPRODUIT T WHERE qteminiheurestandard > (SELECT count(*) FROM PRODUIT where status='vente' and typeproduit = T.nomtype) and (tempscuisson is NULL or tempscuisson=0) and qtecuireheurestandard <= (select sum(quantite) FROM LOT where typeproduit = T.nomtype )");
		try {
			requete.last();
			produitAMettreRayon = new Object[requete.getRow()][2];
			requete.beforeFirst();
			int i = 0;
			while (requete.next()) {
				produitAMettreRayon[i][0] = requete.getString(1); 
				produitAMettreRayon[i][1] = requete.getString(2);
				i++;
			}
			requete.close();
		} catch (Exception sqlExcept) {
			sqlExcept.printStackTrace();
			System.out.println("Erreur execution requete: Recuperer produit a mettre en rayon.\n");
		}
		
		return produitAMettreRayon;
	}

	public Object[][] recupererProduitFour() {
		Object [][] produitAuFour = null;

		ResultSet requete;
		requete = DatabaseAccess.jdbcExecuteQuery("SELECT typeproduit, count(*) FROM PRODUIT WHERE status='four' GROUP BY typeproduit ");
		try {
			requete.last();
			produitAuFour = new Object[requete.getRow()][2];
			requete.beforeFirst();
			int i = 0;
			while (requete.next()) {
				produitAuFour[i][0] = requete.getString(1); 
				produitAuFour[i][1] = requete.getString(2);
				i++;
			}
			requete.close();
		} catch (Exception sqlExcept) {
			sqlExcept.printStackTrace();
			System.out.println("Erreur execution requete: Recuperer produit au four.\n");
		}

		return produitAuFour;
	}

	public Object[][] afficherProduitVente() {
		ArrayList<String> nomTypeProduit = recupererNomTypeProduit();
		Object [][] produitVente = null;

		ResultSet requete;
		requete = DatabaseAccess.jdbcExecuteQuery("SELECT typeproduit, count(*) FROM PRODUIT WHERE status='vente' GROUP BY typeproduit ");
		try {
			produitVente = new Object[nomTypeProduit.size()][2];
			for (int i = 0; i < nomTypeProduit.size(); i++) {
				produitVente[i][0] = nomTypeProduit.get(i);
				produitVente[i][1] = 0;
			}
			while (requete.next()) {
				for (int j = 0; j < produitVente.length; j++) {
					if (produitVente[j][0].equals(requete.getString(1)))
							produitVente[j][1] = requete.getString(2);
				}
			}
			requete.close();
		} catch (Exception sqlExcept) {
			sqlExcept.printStackTrace();
			System.out.println("Erreur execution requete: Recuperer produit en vente.\n");
		}
		
		return produitVente;
	}
	
	public Object[][] afficherProduitFrigo() {
		ArrayList<String> nomTypeProduit = recupererNomTypeProduit();
		
		Object [][] produitFrigo = null;

		ResultSet requete;
		requete = DatabaseAccess.jdbcExecuteQuery("SELECT typeproduit, sum(quantite) FROM LOT GROUP BY typeproduit ");
		try {
			produitFrigo = new Object[nomTypeProduit.size()][2];
			for (int i = 0; i < nomTypeProduit.size(); i++) {
				produitFrigo[i][0] = nomTypeProduit.get(i);
				produitFrigo[i][1] = 0;
			}
			while (requete.next()) {
				for (int j = 0; j < produitFrigo.length; j++) {
					if (produitFrigo[j][0].equals(requete.getString(1)))
						produitFrigo[j][1] = requete.getString(2);
				}
			}			
			
			requete.close();
		} catch (Exception sqlExcept) {
			sqlExcept.printStackTrace();
			System.out.println("Erreur execution requete: Recuperer produit au frigo.\n");
		}
		
		return produitFrigo;
	}		

	private ArrayList<String> recupererNomTypeProduit() {
		ArrayList<String>  nomTypeProduit = new ArrayList<String>();

		ResultSet requete;
		requete = DatabaseAccess.jdbcExecuteQuery("SELECT nomtype FROM TYPEPRODUIT");
		try {
			
			while (requete.next()) {
				nomTypeProduit.add(requete.getString(1));
			}
			requete.close();
		} catch (Exception sqlExcept) {
			sqlExcept.printStackTrace();
			System.out.println("Erreur execution requete: Recuperer nom typeproduit.\n");
		}
		
		return nomTypeProduit;
	}

	public static void validerCuisson(String typeProduit, String nombre) {
		DatabaseAccess.jdbcExecute("UPDATE PRODUIT SET status=\"vente\" WHERE status=\"four\" and numlot in (SELECT id FROM LOT WHERE typeproduit=\""+typeProduit+"\")");
	}

	public static void rejeterCuisson(String typeProduit, String nombre) {
		DatabaseAccess.jdbcExecute("UPDATE PRODUIT SET status=\"jeté\" WHERE status=\"four\" and numlot in (SELECT id FROM LOT WHERE typeproduit=\""+typeProduit+"\")");
	}
}
