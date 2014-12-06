import java.util.Hashtable;


public class LogicielEmpCuisson {
	DaoCuisson dc;
	Utilisateur utilisateurActif;
	
	public LogicielEmpCuisson(Utilisateur actif){
		dc = new DaoCuisson();
		utilisateurActif = actif;
	}
	
	public Object [][] afficherProduitACuire(){
		return dc.recupererProduitACuire();
	}
	
	public Object[][] afficherProduitAMettreRayon() {
		return dc.recupererProduitAMettreRayon();
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

	public static void validerCuisson(String typeProduit, String nombre) {
		DaoCuisson.validerCuisson(typeProduit, nombre);
	}

	public static void rejeterCuisson(String typeProduit, String nombre) {
		DaoCuisson.rejeterCuisson(typeProduit, nombre);		
	}
}
