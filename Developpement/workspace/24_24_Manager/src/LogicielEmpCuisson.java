import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Hashtable;


public class LogicielEmpCuisson {
	DaoCuisson dc;
	Utilisateur utilisateurActif;

	public LogicielEmpCuisson(Utilisateur actif){
		dc = new DaoCuisson();
		utilisateurActif = actif;
	}

	public String  getPrenomNomUtilisateur(){
		return utilisateurActif.getPrenom()+" "+utilisateurActif.getNom();
	}

	public Object [][] afficherProduitACuire(){
		if(dc.heurePleine())
			return dc.recupererProduitACuire("qtecuireheurepleine");
		else
			return dc.recupererProduitACuire("qtecuireheurestandard");
	}

	public Object[][] afficherProduitAMettreRayon() {
		if(dc.heurePleine())
			return dc.recupererProduitAMettreRayon("qtecuireheurepleine");
		else
			return dc.recupererProduitAMettreRayon("qtecuireheurestandard");
	}

	public Object[][] afficherProduitFour() {
		return dc.recupererProduitFour();
	}

	public Object[][] afficherProduitVente() {
		ArrayList<String> listeTypeProduit = dc.recupererNomTypeProduit();
		Object [][] produitVenteTmp = dc.recupererProduitVente();
		Object [][] produitVente = new Object[listeTypeProduit.size()][2];

		produitVente = new Object[listeTypeProduit.size()][2];
		for (int i = 0; i < listeTypeProduit.size(); i++) {
			produitVente[i][0] = listeTypeProduit.get(i);
			produitVente[i][1] = 0;
			for (int j = 0; j < produitVenteTmp.length; j++) {
				if (((String)produitVenteTmp[j][0]).equals(listeTypeProduit.get(i))){
					produitVente[i][1] = produitVenteTmp[j][1];
				}
			}
		}
		
		return produitVente;
	}

	public Object[][] afficherProduitFrigo() {
		ArrayList<String> listeTypeProduit = dc.recupererNomTypeProduit();
		Object [][] produitFrigoTmp = dc.recupererProduitFrigo();
		Object [][] produitFrigo = new Object[listeTypeProduit.size()][2];

		produitFrigo = new Object[listeTypeProduit.size()][2];
		for (int i = 0; i < listeTypeProduit.size(); i++) {
			produitFrigo[i][0] = listeTypeProduit.get(i);
			produitFrigo[i][1] = 0;
			for (int j = 0; j < produitFrigoTmp.length; j++) {
				if (((String)produitFrigoTmp[j][0]).equals(listeTypeProduit.get(i))){
					produitFrigo[i][1] = produitFrigoTmp[j][1];
				}
			}
		}
		
		return produitFrigo;	}

	public void validerCuisson(String typeProduit, String nombre, String debutCuisson) {
		Calendar cal = Calendar.getInstance();
		int tempsValide = dc.recupererTempsValide(typeProduit);
		cal.add(Calendar.HOUR_OF_DAY,tempsValide);
		Date date = cal.getTime();

		dc.validerCuisson((new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")).format(date), typeProduit, nombre, debutCuisson);
	}

	public void rejeterCuisson(String typeProduit, String nombre, String debutCuisson) {
		dc.rejeterCuisson(typeProduit, nombre, debutCuisson);		
	}

	public void mettreAuFour(String typeProduit, String nombre) {
		int reste = Integer.parseInt(nombre), j = 0;
		Object [][] qteLot = dc.qteLotParTypeproduit(typeProduit);
		while(reste > 0) {
			if (reste >= (int)qteLot[j][1]) {
				dc.decrementerQteLot((int)qteLot[j][0], 0);
			} else {
				dc.decrementerQteLot((int)qteLot[j][0], (int)qteLot[j][1] - reste);
			}
			reste -= (int)qteLot[j][1];
			j++;
		}
		
		Calendar c = Calendar.getInstance();
		String debut = c.get(Calendar.HOUR_OF_DAY)+":"+c.get(Calendar.MINUTE)+":"+c.get(Calendar.SECOND);
		for (int i = 0; i < Integer.parseInt(nombre); i++) {
			dc.creerProduit("NULL", "four", typeProduit, "\""+debut+"\"");
		}
	}

	public void mettreEnRayon(String typeProduit, String nombre, String mois, String annee) {	
		int reste = Integer.parseInt(nombre), j = 0;
		Object [][] qteLot = dc.qteLotParTypeproduit(typeProduit);
		while(reste > 0) {
			if (reste >= (int)qteLot[j][1]) {
				dc.decrementerQteLot((int)qteLot[j][0], 0);
			} else {
				dc.decrementerQteLot((int)qteLot[j][0], (int)qteLot[j][1] - reste);
			}
			reste -= (int)qteLot[j][1];
			j++;
		}

		for (int i = 0; i < Integer.parseInt(nombre); i++) {
			dc.creerProduit("\""+annee+"-"+mois+"-20 10:00:00\"", "envente", typeProduit, "NULL");
		}		
	}
	
	public static void main(String[] args) {
		LogicielEmpCuisson lec = new LogicielEmpCuisson(null);
		DaoCuisson dc = new DaoCuisson();
		Object[][] produit = dc.qteLotParTypeproduit("Eau");
		for (int i = 0; i < produit.length; i++) {
			System.out.println(produit[i][0]+" "+produit[i][1]);
		}
	}

	public int recupererTempsCuisson(String typeProduit) {
		return dc.recupererTempsCuisson(typeProduit);
	}
}
