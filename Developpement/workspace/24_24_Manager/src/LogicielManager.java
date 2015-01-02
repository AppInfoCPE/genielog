/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.sql.ResultSet;
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
            
        
     
        public void recupererTableStock(Vector<Object> columnNames,Vector<Object> data){
            dm.recupererTableStock(columnNames,data);
        }
        
         public float prixTotal(String produit,int quantite){
             return dm.prixTotal(produit, quantite);
         }
         public void updateCommande(Commande cmd){
                 dm.updateCommande(cmd);
          
        }
        public void updateLot(Commande cmd,int status,String typeProd,int qte){
                dm.updateLot(cmd, status, typeProd, qte);
        }
        public void valideLivraison(String nomType,int numCmd,int qte){
            dm.valideLivraison(nomType, numCmd, qte);
        }
        public void annuleLivraison(String nomType,int numCmd,int qte){
            dm.annuleLivraison(nomType, numCmd, qte);
        }
        public ResultSet prodVendu1(){
             return dm.prodVendu1();
        }
        public ResultSet prodVendu2(){
            return dm.prodVendu2();
        }
         public ResultSet prodVendu3(){
             return dm.prodVendu3();
         }
          public ResultSet prodVendu4(){
              return dm.prodVendu4();
          }
          
       public ResultSet prodJet1(){
             return dm.prodJet1();
        }
        public ResultSet prodJet2(){
            return dm.prodJet2();
        }
         public ResultSet prodJet3(){
             return dm.prodJet3();
         }
          public ResultSet prodJet4(){
              return dm.prodJet4();
          }   
          
         public ResultSet prodPerdu1(){
             return dm.prodPerdu1();
        }
        public ResultSet prodPerdu2(){
            return dm.prodPerdu2();
        }
         public ResultSet prodPerdu3(){
             return dm.prodPerdu3();
         }
          public ResultSet prodPerdu4(){
              return dm.prodPerdu4();
          }   
          
          
         public ResultSet UserVente1(){
             return dm.UserVente1();
        }
        public ResultSet UserVente2(){
            return dm.UserVente2();
        }
         public ResultSet UserVente3(){
             return dm.UserVente3();
         }
          public ResultSet UserVente4(){
              return dm.UserVente4();
          } 
          
          
        public ResultSet getVendeur(){
              return dm.getVendeur();
          }   
          
          
          
   
}
