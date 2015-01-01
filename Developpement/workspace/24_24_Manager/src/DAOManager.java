
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.*;
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

        
        
}
