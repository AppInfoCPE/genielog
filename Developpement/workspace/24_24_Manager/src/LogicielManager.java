import java.util.*;
import java.sql.SQLException;
import java.util.Vector;
import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JTable;

/**
 *
 * @author PC
 */
public class LogicielManager {
    DAOManager dm;
    Utilisateur utilisateurActif;
    
    	
	
	public LogicielManager(Utilisateur actif){
		dm = new DAOManager();
		utilisateurActif = actif;
	}
	
	public String  getPrenomNomUtilisateur(){
		return utilisateurActif.getPrenom()+" "+utilisateurActif.getNom();
	}
	
	public void recupererTableConfig(Vector<Object> columnNames,Vector<Object> data){
            dm.recupererTableConfig(columnNames,data);
        }
        
        public DefaultListModel recupererListHeurePointe(){
            return dm.recupererListHeurePointe();
        }
        
        public void ajoutHeurePointe(String debutTime,String finTime){
            dm.ajoutHeurePointe(debutTime, finTime);
        }
        public void supprHeurePointe(String debut, String fin ){
            dm.supprHeurePointe(debut, fin);
        }
        
        public void updateTableConfig(JFrame jFrame,JTable jTable1) throws SQLException
            {
                dm.updateTableConfig(jFrame,jTable1);
            }
            
        
     
   
}
