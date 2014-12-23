import java.text.SimpleDateFormat;
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
		return dc.afficherProduitVente();
	}

	public Object[][] afficherProduitFrigo() {
		return dc.afficherProduitFrigo();
	}

	public void validerCuisson(String typeProduit, String nombre) {
		Calendar cal = Calendar.getInstance();
		int tempsValide = dc.recupererTempsValide(typeProduit);
		cal.add(Calendar.HOUR_OF_DAY,tempsValide);
		Date date = cal.getTime();
		
		dc.validerCuisson((new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")).format(date), typeProduit, nombre);
	}

	public void rejeterCuisson(String typeProduit, String nombre) {
		dc.rejeterCuisson(typeProduit, nombre);		
	}

	public void mettreAuFour(String typeProduit, String nombre) {
		int reste = Integer.parseInt(nombre);
		int [] idEtMin;
		while(reste > 0) {
			idEtMin = dc.minQteLotParTypeproduit(typeProduit);
			
			if (reste >= idEtMin[1]) {
				dc.decrementerQteLot(idEtMin[0], 0);
			} else {
				dc.decrementerQteLot(idEtMin[0], idEtMin[1] - reste);
			}
			reste -= idEtMin[1];
		}

		for (int i = 0; i < Integer.parseInt(nombre); i++) {
			dc.creerProduit("NULL", "four", typeProduit);
		}
	}

	public void mettreEnRayon(String typeProduit, String nombre) {	
		int reste = Integer.parseInt(nombre);
		int [] idEtMin;
		while(reste > 0) {
			idEtMin = dc.minQteLotParTypeproduit(typeProduit);
			
			if (reste >= idEtMin[1]) {
				dc.decrementerQteLot(idEtMin[0], 0);
			} else {
				dc.decrementerQteLot(idEtMin[0], idEtMin[1] - reste);
			}
			reste -= idEtMin[1];
		}

		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.YEAR,1);
		Date date = cal.getTime();
		for (int i = 0; i < Integer.parseInt(nombre); i++) {
			dc.creerProduit("\""+(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")).format(date)+"\"", "envente", typeProduit);
		}		
	}
}
