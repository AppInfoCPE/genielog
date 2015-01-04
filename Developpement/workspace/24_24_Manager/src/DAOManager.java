        /*
         * To change this license header, choose License Headers in Project Properties.
         * To change this template file, choose Tools | Templates
         * and open the template in the editor.
         */
     
    import java.sql.DriverManager;
        import java.sql.ResultSet;
        import java.sql.ResultSetMetaData;
        import java.sql.SQLException;
    import java.sql.Statement;
        import java.util.*;
    import java.util.logging.Level;
    import java.util.logging.Logger;
        import javax.swing.DefaultListModel;
    import javax.swing.JFrame;
    import javax.swing.JOptionPane;
        import javax.swing.JTable;

        /**
         *
         * @author PC
         */
        public class DAOManager {

            public DAOManager() {
                        DatabaseAccess.initialisation();
                }

            public void recupererTableConfig(Vector<Object> columnNames,Vector<Object> data){
                try
                {
                    String sql = "Select nomtype,prixtype,tempscuisson,qteminiheurepleine,qteminiheurestandard,qtecuireheurepleine,qtecuireheurestandard,categorie,qteministock,qtemaxstock from TYPEPRODUIT";
                    ResultSet rs = DatabaseAccess.jdbcExecuteQuery(sql);
                    ResultSetMetaData md = rs.getMetaData();
                    int columns = md.getColumnCount();
                    //  Get column names
                    for (int i = 1; i <= columns; i++)
                    {
                        columnNames.addElement( md.getColumnName(i) );
                    }
                    while (rs.next())
                    {
                        Vector<Object> row = new Vector<Object>(columns);
                        for (int i = 1; i <= columns; i++)
                        {
                            row.addElement( rs.getObject(i) );
                        }
                        data.addElement( row );
                    }
                    rs.close();
                }
                catch(Exception e)
                {
                    System.out.println( e );
                }      
            }


             public DefaultListModel recupererListHeurePointe()
            {    
             DefaultListModel dlm= new DefaultListModel();
             try

                {
                    String sql = "Select debut,fin from HEURES_POINTES";   
                    ResultSet rs = DatabaseAccess.jdbcExecuteQuery(sql );

                    while (rs.next())
                    {
                        String debut=rs.getString("debut");
                        String fin=rs.getString("fin");
                        HeurePointe hp= new HeurePointe(debut,fin);
                        dlm.addElement(hp);
                    }

                    rs.close();
                }
                catch(Exception e)
                {
                    System.out.println( e );
                }

             return dlm;
         }

                public static void ajoutHeurePointe(String debutTime,String finTime)
                {

                                   String sql = null;
                                   DatabaseAccess.jdbcExecute(("INSERT INTO HEURES_POINTES(DEBUT,FIN) VALUES('"+debutTime+"','"+finTime+"')"));
                }



                public static void supprHeurePointe(String debut, String fin ){

                    try
                           {
                               //  Read data from a table
                               String sql = "DELETE FROM HEURES_POINTES WHERE debut='"+debut+"' AND fin='"+fin+"'";
                               DatabaseAccess.jdbcExecute(sql );
                               //  Get row data

                               /*while (rs.next())
                               {
                                   String debut=rs.getString("debut");
                                   String fin=rs.getString("fin");
                                   HeurePointe hp= new HeurePointe(debut,fin);
                                   dlm.addElement(hp);
                               }*/                
                           }
                           catch(Exception e)
                           {
                               System.out.println( e );
                           }

           }




            public void updateTableConfig(JFrame jFrame,JTable jTable1) {

                try {

                String sql = null;
                for(int i=0;i<jTable1.getRowCount();i++){
                // System.out.println(table.getRowCount());

                String nomType=jTable1.getValueAt(i, 0).toString();
                float prix=(float) jTable1.getValueAt(i,1 );

                float tempCuisson=0.0f;
                if(jTable1.getValueAt(i, 2)!=null){
                tempCuisson=(float) jTable1.getValueAt(i,2);
                }
                int qteMiniHeureP=(int) jTable1.getValueAt(i, 3);
                int qteMiniHeureS= (int) jTable1.getValueAt(i,4 );
                int qteCuireHeureP=(int) jTable1.getValueAt(i, 5);
                int qteCuireHeureS=(int) jTable1.getValueAt(i, 6);

                String Cat=jTable1.getValueAt(i, 7).toString();


                int qteMiniStock=(int) jTable1.getValueAt(i, 8);
                int qteMaxStock=(int) jTable1.getValueAt(i, 9);

                if(qteMaxStock>=qteMiniStock && qteCuireHeureP>=qteCuireHeureS && qteMiniHeureP>=qteMiniHeureS){

                if(Cat.equals("Boisson")){

                sql = "UPDATE TYPEPRODUIT "
                + "SET nomtype='"+nomType+"',"
                + "prixtype="+prix+","

                + "tempscuisson=NULL,"
                + "qteminiheurepleine="+qteMiniHeureP+","
                + "qteminiheurestandard="+qteMiniHeureS+","
                + "qtecuireheurepleine="+qteCuireHeureP+","
                + "qtecuireheurestandard="+qteCuireHeureS+","
                + "categorie='"+Cat+"',"
                + "qteministock="+qteMiniStock+","
                + "qtemaxstock="+qteMaxStock+" "

                + "WHERE  nomtype= '"+nomType+"'";

                }
                else{
                sql = "UPDATE TYPEPRODUIT "
                + "SET nomtype='"+nomType+"',"
                + "prixtype="+prix+","

                + "tempscuisson="+tempCuisson+","
                + "qteminiheurepleine="+qteMiniHeureP+","
                + "qteminiheurestandard="+qteMiniHeureS+","
                + "qtecuireheurepleine="+qteCuireHeureP+","
                + "qtecuireheurestandard="+qteCuireHeureS+","
                + "categorie='"+Cat+"',"
                + "qteministock="+qteMiniStock+","
                + "qtemaxstock="+qteMaxStock+" "

                + "WHERE  nomtype= '"+nomType+"'";
                }

                DatabaseAccess.jdbcExecute(sql );

                }
                else {//qteMaxStock<qteMiniStock && qteCuireHeureP<qteCuireHeureS && qteMiniHeureP<qteMiniHeureS

                sql = "Select * from TYPEPRODUIT";
                ResultSet rs =DatabaseAccess.jdbcExecuteQuery(sql );

                while (rs.next())
                {
                System.out.println(rs.getRow());
                jTable1.setValueAt(rs.getInt("qtecuireheurepleine"), rs.getRow()-1,5);
                jTable1.setValueAt(rs.getInt("qtecuireheurestandard"),rs.getRow()-1,6);
                jTable1.setValueAt(rs.getInt("qteminiheurepleine"), rs.getRow()-1,3);
                jTable1.setValueAt(rs.getInt("qteminiheurestandard"),rs.getRow()-1,4);
                jTable1.setValueAt(rs.getInt("qteministock"),rs.getRow()-1,8);
                jTable1.setValueAt(rs.getInt("qtemaxstock"),rs.getRow()-1,9);


                }

                JOptionPane.showMessageDialog(jFrame,
                "Qte(Cuire/Stock) Heure Pleine doit > Qte(Cuire/Stock) Heure Standard",
                "Insane error",JOptionPane.ERROR_MESSAGE); break;
                }
                }


                } catch (SQLException ex) {
               // Logger.getLogger(test.class.getName()).log(Level.SEVERE, null, ex);
                } 
            } 




            public Vector<Object> recupererTableStock(){
                            Vector<Object> data = new Vector<Object>();

                        try
                        {

                             String sql="Select LOT.typeproduit,SUM(LOT.quantite) as qte,TYPEPRODUIT.qteministock,TYPEPRODUIT.qtemaxstock from LOT,TYPEPRODUIT WHERE TYPEPRODUIT.nomtype=LOT.typeproduit  AND LOT.statutlivraison=1 GROUP BY LOT.typeproduit,TYPEPRODUIT.qteministock,TYPEPRODUIT.qtemaxstock ";
                       // Statement stmt = connection.createStatement();
                            ResultSet rs = DatabaseAccess.jdbcExecuteQuery(sql);
                            ResultSetMetaData md = rs.getMetaData();
                            int columns = md.getColumnCount();


                            //  Get row data

                            while (rs.next())
                            {
                                Vector<Object> row = new Vector<Object>(columns);

                                for (int i = 1; i <= columns; i++)
                                {
                                    row.addElement( rs.getObject(i) );
                                }

                                data.addElement( row );
                            }

                            rs.close();

                        }
                        catch(Exception e)
                        {
                            System.out.println( e );
                        }
                    return data;
                    }


            public void recupererTableStock(Vector<Object> columnNames1,Vector<Object> data1){
                 try
            {

                //  Read data from a table
                String sql="Select LOT.typeproduit,SUM(LOT.quantite) as qte,TYPEPRODUIT.qteministock,TYPEPRODUIT.qtemaxstock from LOT,TYPEPRODUIT WHERE TYPEPRODUIT.nomtype=LOT.typeproduit  AND LOT.statutlivraison=1 GROUP BY LOT.typeproduit,TYPEPRODUIT.qteministock,TYPEPRODUIT.qtemaxstock ";
                //String sql = "Select TYPEPRODUIT.nomtype,SUM(quantite),qteministock,qtemaxstock from TYPEPRODUIT,LOT where TYPEPRODUIT.nomtype=LOT.typeproduit AND LOT.statutlivraison=1 GROUP BY LOT.typeproduit";
                ResultSet rs = DatabaseAccess.jdbcExecuteQuery(sql);
                ResultSetMetaData md = rs.getMetaData();
                int columns = md.getColumnCount();

                //  Get column names

                for (int i = 1; i <= columns; i++)
                {
                    columnNames1.addElement( md.getColumnName(i) );
                }

                //  Get row data

                while (rs.next())
                {
                    Vector<Object> row = new Vector<Object>(columns);

                    for (int i = 1; i <= columns; i++)
                    {
                        row.addElement( rs.getObject(i) );
                    }

                    data1.addElement( row );
                }

                rs.close();

            }
            catch(Exception e)
            {
                System.out.println( e );
            }
            }

            public float prixTotal(String produit,int quantite){
                float prixTotal=0;

            //Cacul prix total
            try
            {
                //  Connect to an Access Database

                //  Read data from a table

                String sql = "Select prixtype from TYPEPRODUIT where nomtype='"+produit+"'";
                ResultSet rs = DatabaseAccess.jdbcExecuteQuery(sql);
                ResultSetMetaData md = rs.getMetaData();
                int columns = md.getColumnCount();            

                //  Get row data

                while (rs.next())
                {
                 float prix=rs.getFloat("prixtype");
                 System.out.println(prix);

                 prixTotal=prix * quantite;
                }

                rs.close();

            }
            catch(Exception e)
            {
                System.out.println( e );
            }


                return prixTotal;
            }        

            public void updateCommande(Commande cmd){
                     DatabaseAccess.jdbcExecute("INSERT INTO COMMANDE(date) VALUES('"+cmd.getDateCmd()+"')");

            }

            public void updateLot(Commande cmd,int status,String typeProd,int qte){

            DatabaseAccess.jdbcExecute("INSERT INTO LOT(idcommande,quantite,typeproduit,statutlivraison) VALUES("+cmd.getId()+","+qte+",'"+typeProd+"',"+status+")");


            }

            public void valideLivraison(String nomType,int numCmd,int qte){
                 String sql = "UPDATE LOT "
                        + " SET statutlivraison=1 "
                        + " WHERE  typeproduit= '"+nomType+"' "
                        + " AND  idcommande= "+numCmd+" "
                        + " AND  quantite= "+qte+" "
                        + " AND  statutlivraison= 0 ";
                 DatabaseAccess.jdbcExecute(sql);
            }

            public void annuleLivraison(String nomType,int numCmd,int qte){
                 String sql = "DELETE FROM LOT "

                        + " WHERE  typeproduit= '"+nomType+"' "
                        + " AND  idcommande= "+numCmd+" "
                        + " AND  quantite= "+qte+" "
                        ;
                 DatabaseAccess.jdbcExecute(sql);
            }

            public ResultSet prodVendu1(){
                String sql = "SELECT COUNT(idproduit) as nbrProd FROM VENTE v, LIENPRODUITVENTE l, PRODUIT p WHERE l.numvente=v.numvente AND v.datevente = CURRENT_DATE AND p.id= l.idproduit AND p.status='vendu'";          
                ResultSet rs =DatabaseAccess.jdbcExecuteQuery(sql );

             return rs;       
            }
            public ResultSet prodVendu2(){
                String sql = "SELECT COUNT(idproduit) as nbrProd FROM VENTE v, LIENPRODUITVENTE l, PRODUIT p WHERE l.numvente=v.numvente AND WEEK(v.datevente) = WEEK(CURRENT_DATE) AND p.id= l.idproduit AND p.status='vendu'";
                  ResultSet rs =DatabaseAccess.jdbcExecuteQuery(sql );

             return rs;       
            }
             public ResultSet prodVendu3(){
                String  sql = "SELECT COUNT(idproduit) as nbrProd FROM VENTE v, LIENPRODUITVENTE l, PRODUIT p WHERE l.numvente=v.numvente AND MONTH(v.datevente) = MONTH(CURRENT_DATE) AND p.id= l.idproduit AND p.status='vendu'";
                  ResultSet rs =DatabaseAccess.jdbcExecuteQuery(sql );

             return rs;       
            }
             public ResultSet prodVendu4(){
                String  sql = "SELECT COUNT(idproduit) as nbrProd FROM VENTE v, LIENPRODUITVENTE l, PRODUIT p WHERE l.numvente=v.numvente AND YEAR(v.datevente) = YEAR(CURRENT_DATE) AND p.id= l.idproduit AND p.status='vendu'";
                  ResultSet rs =DatabaseAccess.jdbcExecuteQuery(sql );         
             return rs;       
            }

              public ResultSet prodJet1(){
            	  String    sql = "SELECT COUNT(id) as nbrProdJet FROM PRODUIT p WHERE  DAY(p.dateperemption) = DAY(NOW()) AND p.status='jete'";
                 ResultSet rs =DatabaseAccess.jdbcExecuteQuery(sql );
                 return rs;   
              }
              public ResultSet prodJet2(){
            	  String    sql = "SELECT COUNT(id) as nbrProdJet FROM PRODUIT p WHERE  WEEK(p.dateperemption) = WEEK(NOW()) AND p.status='jete'";
                   ResultSet rs =DatabaseAccess.jdbcExecuteQuery(sql );
                  return rs;   
              }
              public ResultSet prodJet3(){
            	  String    sql = "SELECT COUNT(id) as nbrProdJet FROM PRODUIT p WHERE  MONTH(p.dateperemption) = MONTH(NOW()) AND p.status='jete'";
                     ResultSet rs =DatabaseAccess.jdbcExecuteQuery(sql );
                  return rs;   
              }
              public ResultSet prodJet4(){
            	  String    sql = "SELECT COUNT(id) as nbrProdJet FROM PRODUIT p WHERE  YEAR(p.dateperemption) = YEAR(NOW()) AND p.status='jete'";
                ResultSet rs =DatabaseAccess.jdbcExecuteQuery(sql );
                  return rs;   
              }

              public ResultSet prodPerdu1(){
            	  String    sql = "SELECT COUNT(id) as nbrProdPerdus FROM PRODUIT p WHERE  DAY(p.dateperemption) = DAY(NOW()) AND p.status='perime'";
             ResultSet rs =DatabaseAccess.jdbcExecuteQuery(sql );
                 return rs;   
              }
              public ResultSet prodPerdu2(){
            	  String    sql = "SELECT COUNT(id) as nbrProdPerdus FROM PRODUIT p WHERE  WEEK(p.dateperemption) = WEEK(NOW()) AND p.status='perime'";
                  ResultSet rs =DatabaseAccess.jdbcExecuteQuery(sql );
                  return rs;   
              }
              public ResultSet prodPerdu3(){
            	  String    sql = "SELECT COUNT(id) as nbrProdPerdus FROM PRODUIT p WHERE  MONTH(p.dateperemption) = MONTH(NOW()) AND p.status='perime'";
                ResultSet rs =DatabaseAccess.jdbcExecuteQuery(sql );
                  return rs;   
              }
              public ResultSet prodPerdu4(){
            	  String    sql = "SELECT COUNT(id) as nbrProdPerdus FROM PRODUIT p WHERE  YEAR(p.dateperemption) = YEAR(NOW()) AND p.status='perime'";
                       ResultSet rs =DatabaseAccess.jdbcExecuteQuery(sql );
                  return rs;   
              }

              public ResultSet UserVente1(){
                    String      sql = "SELECT u.identifiant as user, COUNT(idproduit) as nbrProd FROM VENTE v, LIENPRODUITVENTE l, PRODUIT p , UTILISATEUR u WHERE u.identifiant = v.identifiant AND l.numvente=v.numvente AND v.datevente =CURRENT_DATE AND p.id= l.idproduit AND p.status='vendu' GROUP BY u.identifiant";
                      ResultSet rs =DatabaseAccess.jdbcExecuteQuery(sql );
                  return rs;   
              }
              public ResultSet UserVente2(){
                    String    sql ="SELECT u.identifiant as user, COUNT(idproduit) as nbrProd FROM VENTE v, LIENPRODUITVENTE l, PRODUIT p , UTILISATEUR u WHERE u.identifiant = v.identifiant AND l.numvente=v.numvente AND WEEK(v.datevente) = WEEK (CURRENT_DATE) AND p.id= l.idproduit AND p.status='vendu' GROUP BY u.identifiant" ;
                   ResultSet rs =DatabaseAccess.jdbcExecuteQuery(sql );
                  return rs;   
              }
              public ResultSet UserVente3(){
                    String  sql = "SELECT u.identifiant as user, COUNT(idproduit) as nbrProd FROM VENTE v, LIENPRODUITVENTE l, PRODUIT p , UTILISATEUR u WHERE u.identifiant = v.identifiant AND l.numvente=v.numvente AND MONTH(v.datevente) =MONTH(CURRENT_DATE) AND p.id= l.idproduit AND p.status='vendu' GROUP BY u.identifiant";
        ResultSet rs =DatabaseAccess.jdbcExecuteQuery(sql );
                  return rs;   
              }
              public ResultSet UserVente4(){
                    String      sql = "SELECT u.identifiant as user, COUNT(idproduit) as nbrProd FROM VENTE v, LIENPRODUITVENTE l, PRODUIT p , UTILISATEUR u WHERE u.identifiant = v.identifiant AND l.numvente=v.numvente AND YEAR(v.datevente) = YEAR(CURRENT_DATE) AND p.id= l.idproduit AND p.status='vendu' GROUP BY u.identifiant";
                   ResultSet rs =DatabaseAccess.jdbcExecuteQuery(sql );
                  return rs;   
              }

         public ResultSet getVendeur(){
             String  sql = "Select identifiant from UTILISATEUR where role='Vendeur'";           
             ResultSet rs =DatabaseAccess.jdbcExecuteQuery(sql );
                  return rs;
         }



         public void recupererTableLivraison(JTable jTable4){
             try
        {
            

            //  Read data from a table

            String sql="Select typeproduit,idcommande,quantite,prixtype,TYPEPRODUIT.prixtype * LOT.quantite as prixtotal from TYPEPRODUIT,LOT where TYPEPRODUIT.nomtype=LOT.typeproduit AND LOT.statutlivraison=0";
           
            ResultSet rs = DatabaseAccess.jdbcExecuteQuery(sql );
            ResultSetMetaData md = rs.getMetaData();  
            //  Get row data

            while (rs.next())
            {          
 
                    String typeproduit= rs.getString("typeproduit");
                    int numCmd=rs.getInt("idcommande");
                    int qte=rs.getInt("quantite");
                    float prix=rs.getFloat("prixtotal");
                    Object[] donnee = new Object[]
                    {typeproduit,numCmd , qte,prix};
                    ((TableModelGestion2)jTable4.getModel()).addRow(donnee);

              
            }

            rs.close();  
        }
        catch(Exception e)
        {
            System.out.println( e );
        }
         }
         
         
         
         public ResultSet getTypeProduit(){
             String sql = "Select nomtype from TYPEPRODUIT";
           ResultSet rs =DatabaseAccess.jdbcExecuteQuery(sql );
                  return rs;
         }























    }







