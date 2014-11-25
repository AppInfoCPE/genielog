import java.util.*;

public class LogicielManager {//Classe qui gere les fonctions de l'utilisateur Manager
    
    Hashtable<Integer,TypeProduit> tableStockFrigo = new Hashtable<Integer,TypeProduit>();
   // Table dans laquelle seront stockés les quantités et le type de produit pour l'affichage
    InterfaceBaseDonnees interfaceBDD;
    
    
    public LogicielManager(){
    	interfaceBDD = new InterfaceBaseDonnees();
		initialisation();    	       
    }
    
    private void initialisation() {
    	tableStockFrigo = interfaceBDD.recupererStockFrigo();
		
	}
	public Hashtable affichageStockFrigo(){
        tableStockFrigo.put(10,new TypeProduit("PainChocolat", 18));
        return tableStockFrigo;//Renvoie les stocks par type de produit
    }
    
    
    
    
}
