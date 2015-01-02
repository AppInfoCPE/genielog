import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;


public class DaoCuisson {
	public DaoCuisson() {
		DatabaseAccess.initialisation();
	}

	public boolean heurePleine(){
		Date maDate = new Date();	
		ResultSet requeteHeure = DatabaseAccess.jdbcExecuteQuery("SELECT debut, fin FROM HEURES_POINTES");
		try {
			while (requeteHeure.next()){
				if (requeteHeure.getInt(1) <= maDate.getHours() && maDate.getHours() < requeteHeure.getInt(2)) return true;
			}
			requeteHeure.close();
		} catch (Exception sqlExcept) {
			System.out.println("Erreur execution requete: Recuperer heures pointes.\n");
		}	
		return false;
	}

	public Object [][] recupererProduitACuire(String optionHeure) {
		Object [][] produitACuire = null;

		ResultSet requete = DatabaseAccess.jdbcExecuteQuery("SELECT nomtype, "+optionHeure+" FROM TYPEPRODUIT T WHERE qteminiheurestandard > (SELECT count(*) FROM PRODUIT where (status='envente' or status='four') and typeproduit = T.nomtype ) and (tempscuisson is not NULL and tempscuisson!=0)  and qtecuireheurestandard <= (select sum(quantite) FROM LOT where typeproduit = T.nomtype and statutlivraison=1)");
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
			System.out.println("Erreur execution requete: Recuperer produit a cuire.\n");
		}
		return produitACuire;
	}

	public Object[][] recupererProduitAMettreRayon(String optionHeure) {
		Object [][] produitAMettreRayon = null;

		ResultSet requete = DatabaseAccess.jdbcExecuteQuery("SELECT nomtype, "+optionHeure+" FROM TYPEPRODUIT T WHERE qteminiheurestandard > (SELECT count(*) FROM PRODUIT where status='envente' and typeproduit = T.nomtype) and (tempscuisson is NULL or tempscuisson=0) and qtecuireheurestandard <= (select sum(quantite) FROM LOT where typeproduit = T.nomtype and statutlivraison=1)");
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
			System.out.println("Erreur execution requete: Recuperer produit a mettre en rayon.\n");
		}

		return produitAMettreRayon;
	}

	public Object[][] recupererProduitFour() {
		Object [][] produitAuFour = null;

		ResultSet requete;
		requete = DatabaseAccess.jdbcExecuteQuery("SELECT typeproduit, count(*), debutcuisson FROM PRODUIT WHERE status='four' GROUP BY typeproduit, debutcuisson ");
		try {
			requete.last();
			produitAuFour = new Object[requete.getRow()][3];
			requete.beforeFirst();
			int i = 0;
			while (requete.next()) {
				produitAuFour[i][0] = requete.getString(1); 
				produitAuFour[i][1] = requete.getString(2);
				produitAuFour[i][2] = requete.getString(3);
				i++;
			}
			requete.close();
		} catch (Exception sqlExcept) {
			System.out.println("Erreur execution requete: Recuperer produit au four.\n");
		}
		return produitAuFour;
	}

	public Object[][] recupererProduitVente() {
		Object [][] produitVente = null;

		ResultSet requete = DatabaseAccess.jdbcExecuteQuery("SELECT typeproduit, count(*) FROM PRODUIT WHERE status='envente' GROUP BY typeproduit ");
		try {
			requete.last();
			produitVente = new Object[requete.getRow()][2];
			requete.beforeFirst();
			int i = 0;
			while (requete.next()) {
				produitVente[i][0] = requete.getString(1); 
				produitVente[i][1] = requete.getString(2);
				i++;
			}
			requete.close();
		} catch (Exception sqlExcept) {
			System.out.println("Erreur execution requete: Recuperer produit en vente.\n");
		}

		return produitVente;
	}

	public Object[][] recupererProduitFrigo() {
		Object [][] produitFrigo = null;

		ResultSet requete = DatabaseAccess.jdbcExecuteQuery("SELECT typeproduit, sum(quantite) FROM LOT WHERE statutlivraison=1 GROUP BY typeproduit ");
		try {
			requete.last();
			produitFrigo = new Object[requete.getRow()][2];
			requete.beforeFirst();
			int i = 0;
			while (requete.next()) {
				produitFrigo[i][0] = requete.getString(1); 
				produitFrigo[i][1] = requete.getString(2);
				i++;
			}
			requete.close();
		} catch (Exception sqlExcept) {
			System.out.println("Erreur execution requete: Recuperer produit au frigo.\n");
		}
		return produitFrigo;
	}		

	public ArrayList<String> recupererNomTypeProduit() {
		ArrayList<String>  nomTypeProduit = new ArrayList<String>();

		ResultSet requete;
		requete = DatabaseAccess.jdbcExecuteQuery("SELECT nomtype FROM TYPEPRODUIT");
		try {

			while (requete.next()) {
				nomTypeProduit.add(requete.getString(1));
			}
			requete.close();
		} catch (Exception sqlExcept) {
			System.out.println("Erreur execution requete: Recuperer nom typeproduit.\n");
		}

		return nomTypeProduit;
	}

	public void validerCuisson(String datePeremption, String typeProduit, String nombre, String debutCuisson) {
		DatabaseAccess.jdbcExecute("UPDATE PRODUIT SET dateperemption=\""+datePeremption+"\",status=\"envente\" WHERE status=\"four\" and typeproduit=\""+typeProduit+"\" and debutcuisson=\""+debutCuisson+"\"");
	}

	public void rejeterCuisson(String typeProduit, String nombre, String debutCuisson) {
		DatabaseAccess.jdbcExecute("UPDATE PRODUIT SET status=\"jete\" WHERE status=\"four\" and typeproduit=\""+typeProduit+"\" and debutcuisson=\""+debutCuisson+"\"");
	}

	public int[] minQteLotParTypeproduit(String typeProduit) {
		ResultSet requete = DatabaseAccess.jdbcExecuteQuery("SELECT id, min(quantite) FROM LOT WHERE typeproduit=\""+typeProduit+"\" and quantite!=0");
		try {
			if (requete.next()) {
				return new int[]{requete.getInt(1), requete.getInt(2)};
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return new int[]{-1, -1} ;
	}
	
	public Object[][] qteLotParTypeproduit(String typeProduit) {
		Object [][] quantiteLot = null;

		ResultSet requete = DatabaseAccess.jdbcExecuteQuery("SELECT id, quantite FROM LOT WHERE typeproduit=\""+typeProduit+"\" and quantite!=0 ORDER BY quantite ASC");
		try {
			requete.last();
			quantiteLot = new Object[requete.getRow()][2];
			requete.beforeFirst();
			int i = 0;
			while (requete.next()) {
				quantiteLot[i][0] = requete.getInt(1); 
				quantiteLot[i][1] = requete.getInt(2);
				i++;
			}
			requete.close();
		} catch (Exception sqlExcept) {
			System.out.println("Erreur execution requete: Recuperer quantite lot par type produit.\n");
		}
		return quantiteLot;
	}		

	public void decrementerQteLot(int id, int nouvelleQuantite) {
		DatabaseAccess.jdbcExecute("UPDATE LOT SET quantite="+nouvelleQuantite+" WHERE id="+id);
	}

	public void creerProduit(String datePeremption, String status, String typeProduit, String debutCuisson) {
		DatabaseAccess.jdbcExecute("INSERT INTO PRODUIT(dateperemption, status, typeproduit, debutcuisson) VALUES ("+datePeremption+", \""+status+"\",\""+typeProduit+"\","+debutCuisson+")");
	}

	public int recupererTempsValide(String typeProduit) {
		ResultSet requete = DatabaseAccess.jdbcExecuteQuery("SELECT tempsvalide FROM TYPEPRODUIT WHERE nomtype=\""+typeProduit+"\"");
		try {
			if (requete.next()) {
				return requete.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}	
	
	public int recupererTempsCuisson(String typeProduit) {
		ResultSet requete = DatabaseAccess.jdbcExecuteQuery("SELECT tempscuisson FROM TYPEPRODUIT WHERE nomtype=\""+typeProduit+"\"");
		try {
			if (requete.next()) {
				return requete.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}
}
