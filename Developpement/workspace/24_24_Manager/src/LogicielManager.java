import java.util.Hashtable;

public class LogicielManager {//Classe qui g�re les fonctions de l'utilisateur Manager
	
	Hashtable tableStockFrigo = new Hashtable();
	
	public LogicielManager(){
		//CONSTRUCTEUR : 
		//base de donn�e ?
		//initialisation ? 
		
	}
	public Hashtable affichageStockFrigo(){
		
		return tableStockFrigo;//Renvoie les stocks par type de produit
	}
	
	
}
