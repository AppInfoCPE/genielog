import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JOptionPane;


public class LogicielVendeur {

	Dao dv;
	Dao dv2;
	ArrayList<String> listProduitBoison;
	Utilisateur utilisateurActif;
	
	public LogicielVendeur(Utilisateur actif){
		dv = new Dao();
		utilisateurActif = actif;
	}
	
	public LogicielVendeur() {
		dv2 = new Dao();
	}

	public String getPrenomNomUtilisateur() {
		// TODO Auto-generated method stub
		return utilisateurActif.getPrenom()+" "+utilisateurActif.getNom();
	}
	
	public Object[][] listProduitBoison(){
		ResultSet resultat;
		Object[][] listProduitBoisson = null;
		resultat=dv.executeRequete("SELECT typeproduit, id FROM PRODUIT INNER join TYPEPRODUIT on PRODUIT.typeproduit=TYPEPRODUIT.nomtype WHERE status='envente' AND categorie='boisson' GROUP BY typeproduit ");
		int i=0;
		try {
			resultat.last();
			listProduitBoisson = new Object[resultat.getRow()][2];
			resultat.beforeFirst();
			while (resultat.next()) {
				listProduitBoisson[i][0]=resultat.getString(1);
				listProduitBoisson[i][1]=resultat.getString(2);	
				i++;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return listProduitBoisson;
	}

	public Object[][] listTypeProduitBoisson() {
		ResultSet resultat;
		Object[][] listTypeProduitsBoisson = null;
		resultat=dv.executeRequete("SELECT nomtype FROM TYPEPRODUIT WHERE categorie='boisson'");
		int i=0;
		try {
			resultat.last();
			listTypeProduitsBoisson = new Object[resultat.getRow()][2];
			resultat.beforeFirst();
			while (resultat.next()) {
				listTypeProduitsBoisson[i][0]=resultat.getString(1);
				i++;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return listTypeProduitsBoisson;
	}
	
	public Object[][] listProduitVeinoiserie() {
		ResultSet resultat;
		Object[][] listProduitsVeinoiserie = null;
		resultat=dv.executeRequete("SELECT typeproduit, id FROM PRODUIT INNER join TYPEPRODUIT on PRODUIT.typeproduit=TYPEPRODUIT.nomtype WHERE status='envente' AND categorie='viennoiserie' GROUP BY typeproduit");
		int i=0;
		try {
			resultat.last();
			listProduitsVeinoiserie = new Object[resultat.getRow()][2];
			resultat.beforeFirst();
			while (resultat.next()) {
				listProduitsVeinoiserie[i][0]=resultat.getString(1);
				listProduitsVeinoiserie[i][1]=resultat.getString(2);	
				i++;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return listProduitsVeinoiserie;
	}

	
	public Object[][] listTypeProduitVeinoiserie() {
		ResultSet resultat;
		Object[][] listTypeProduitsVeinoiserie = null;
		resultat=dv.executeRequete("SELECT nomtype FROM TYPEPRODUIT WHERE categorie='viennoiserie'");
		int i=0;
		try {
			resultat.last();
			listTypeProduitsVeinoiserie = new Object[resultat.getRow()][2];
			resultat.beforeFirst();
			while (resultat.next()) {
				listTypeProduitsVeinoiserie[i][0]=resultat.getString(1);
				i++;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return listTypeProduitsVeinoiserie;
	}
		
	public Object[][] rechercherProduitEnVente(int numVente){
		Object[][] produits = null;
		ResultSet resultat;
		float prixtotal;
		
		//liste des tous les types de produits
		try {
			int i=0;
			resultat=dv.executeRequete("SELECT id,status,typeproduit,nomtype,prixtype,COUNT(typeproduit) AS Quantite, idproduit,numvente FROM PRODUIT INNER JOIN TYPEPRODUIT on typeproduit=nomtype INNER JOIN LIENPRODUITVENTE on idproduit = id WHERE numvente = "+numVente+" and status='courvente' GROUP BY typeproduit");
			resultat.last();
			produits = new Object[resultat.getRow()][5];
			resultat.beforeFirst();
			while (resultat.next()) {
				prixtotal=0.2f;
				produits[i][0]=resultat.getString(3);//type produit		
				produits[i][1]=resultat.getString(6);//quantite
				produits[i][2]=resultat.getString(5);//prix unite
				prixtotal=(Float.valueOf(resultat.getString(5)).floatValue())*(Float.valueOf(resultat.getString(6)).floatValue());
				produits[i][3]=String.valueOf(prixtotal);//prix total
				produits[i][4]="X";
				i++;
			}
			resultat.close();	
			
		} catch (Exception e) {
			System.out.println(e);
		}
		
		return produits;		
	}

	public Float rechercherTotalVenteJournee(Utilisateur uti){
		ResultSet resultat;
		float prixtotalJournee=0;
		
		//liste des tous les types de produits
		try {
			resultat=dv.executeRequete("SELECT COUNT( TYPEPRODUIT.nomtype ) AS quantite, SUM( TYPEPRODUIT.prixtype ) AS prixTotalVente FROM VENTE INNER JOIN LIENPRODUITVENTE ON VENTE.numvente = LIENPRODUITVENTE.numvente INNER JOIN PRODUIT ON PRODUIT.id = LIENPRODUITVENTE.idproduit INNER JOIN TYPEPRODUIT ON PRODUIT.typeproduit = TYPEPRODUIT.nomtype WHERE VENTE.`status`='fini' AND `identifiant` = '"+uti.getLogin() +"' AND DATE_FORMAT(VENTE.`datevente`, '%Y%m%d') = DATE_FORMAT(CURDATE(), '%Y%m%d')");
			resultat.last();

			resultat.beforeFirst();
			while (resultat.next()) {
				prixtotalJournee=Float.valueOf(resultat.getString(2));
			}
			resultat.close();	
			
		} catch (Exception e) {
			System.out.println(e);
		}
		
		return prixtotalJournee;		
	}
	
	public void ajoutProduitVente(String nomProduit, Integer numVente) {
		// TODO Auto-generated method stub
		//Affiche le produit
		ResultSet resultat;
		//System.out.println(nomProduit);
		System.out.println(numVente);
		//Va ajouter  une vente en cours
		//dv.executeRequeteInsert("INSERT INTO `LIENPRODUITVENTE`(`idproduit`, `numvente`) VALUES ((SELECT PRODUIT.id FROM `PRODUIT` WHERE `dateperemption`= (SELECT min(`dateperemption`) FROM `PRODUIT` WHERE `typeproduit`='"+nomProduit+"' and `status` = 'envente') AND `typeproduit`='"+nomProduit+"' and `status` = 'envente' GROUP BY PRODUIT.typeProduit),"+numVente+")");
		
		try {
			resultat=dv.executeRequete("SELECT PRODUIT.id FROM `PRODUIT` WHERE `dateperemption`= (SELECT min(`dateperemption`) FROM `PRODUIT` WHERE `typeproduit`='"+nomProduit+"' and `status` = 'envente') AND `typeproduit`='"+nomProduit+"' and `status` = 'envente' GROUP BY PRODUIT.typeProduit" );
			resultat.last();
			resultat.beforeFirst();
			while (resultat.next()) {

				//System.out.println(resultat.getString(1));
				String id=resultat.getString(1);
				dv.executeRequeteInsert("Update PRODUIT set status='courvente' where id="+id);
				dv.executeRequeteInsert("INSERT INTO LIENPRODUITVENTE (`idproduit`, `numvente`) VALUES ("+id+","+numVente+")");
			}
		}
		catch (Exception e) {
			System.out.println(e);
		}
	}

	// Marche 
	public void annulerVente(Integer numVente) {
		// TODO Auto-generated method stub
		ResultSet resultat;
		
		try {
			resultat=dv.executeRequete("Select idproduit from LIENPRODUITVENTE where numVente="+numVente);
			resultat.last();
			resultat.beforeFirst();
			while (resultat.next()) {
			//	System.out.println("produit "+resultat.getString(1));
				dv.executeRequeteInsert("Update PRODUIT set status='envente' where id="+resultat.getString(1));
			}
		}
		catch (Exception e) {
			System.out.println(e);
		}
		dv.executeRequeteInsert("DELETE FROM `LIENPRODUITVENTE` WHERE `numvente`="+numVente);
	}

	public void terminerVente(Integer numVente,String Paiement) {
		// TODO Auto-generated method stub
		ResultSet resultat;
				
		try {
			resultat=dv.executeRequete("Select idproduit from LIENPRODUITVENTE where numVente="+numVente);
			resultat.last();
			resultat.beforeFirst();
			while (resultat.next()) {
				//	System.out.println("produit "+resultat.getString(1));
				dv.executeRequeteInsert("Update PRODUIT set status='vendu' where id="+resultat.getString(1));
			}
			
			//requete MAJ vente
		}
		catch (Exception e) {
			System.out.println(e);
		}
		dv.executeRequeteInsert("Update `VENTE` set status='fini', typepaiement='"+Paiement+"' WHERE `numvente`="+numVente);
	}
	//Tester sur interface 
	public void annulerPrduitVente(Integer numVente, String typeProduit) {
		// TODO Auto-generated method stub
		ResultSet resultat;
		//requete allant chercher l'id du produit qui a la adate de peremption la plus loin
		try {
			resultat=dv.executeRequete("SELECT id  FROM PRODUIT INNER JOIN LIENPRODUITVENTE on PRODUIT.id=LIENPRODUITVENTE.idproduit where typeproduit='"+typeProduit+"'and numvente="+numVente+" ORDER BY dateperemption DESC LIMIT 0,1" );
			resultat.last();
			resultat.beforeFirst();
			while (resultat.next()) {
				dv.executeRequeteInsert("Update PRODUIT set status='envente' where id="+resultat.getString(1));
				dv.executeRequeteInsert("DELETE FROM LIENPRODUITVENTE WHERE numVente="+numVente+" and idproduit="+resultat.getString(1));
			}
		}
		catch (Exception e) {
			System.out.println(e);
		}
	}

	public ArrayList<String> produitPerime() {
		ArrayList<String> liste = new ArrayList<String>();
		ResultSet resultat;
		liste = null;
		try {
			resultat=dv2.executeRequete("SELECT typeproduit, COUNT(typeproduit) as quantite FROM PRODUIT WHERE dateperemption IS NOT NULL AND dateperemption < NOW() AND status = 'envente' GROUP BY typeproduit");
			resultat.last();
			resultat.beforeFirst();
			while (resultat.next()){
				liste.add(resultat.getString(2)+" "+resultat.getString(1));
			}
		}
		catch (Exception e) {
			System.out.println(e);
		}		
		return liste;
	}
	
	public void miseAJourProduitPerime() {
		ResultSet resultat;
		try {
			resultat=dv2.executeRequete("SELECT id from PRODUIT WHERE dateperemption < NOW() AND dateperemption IS NOT NULL and status='envente'");
			resultat.last();
			resultat.beforeFirst();
			while (resultat.next()) {
			dv2.executeRequeteInsert("Update PRODUIT set status='perime' where id="+resultat.getString(1));
			}
		}
		catch (Exception e) {
			System.out.println(e);
		}
	}
}
