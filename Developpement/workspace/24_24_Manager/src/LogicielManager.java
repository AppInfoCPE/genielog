import java.util.Hashtable;

public class LogicielManager {//Classe qui gere les fonctions de l'utilisateur Manager
    
    Hashtable tableStockFrigo = new Hashtable();
    
    public LogicielManager(){
        //CONSTRUCTEUR : 
        //base de donnee?
        //initialisation ? 
        
    }
    public Hashtable affichageStockFrigo(){
        
        return tableStockFrigo;//Renvoie les stocks par type de produit
    }
    
    
}
