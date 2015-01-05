import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;


import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;

/**
 *
 * @author PC
 */
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;



/**
 *
 * @author PC
 */


/**
 *
 * @author PC
 */
public class Commande {
    private int id;
    private Date DateCmd;

    public Commande(Date DateCmd) {
        this.DateCmd=DateCmd;
    }

    public int getId() {
        
        int id = 0;
         try
        {   

            //  Connect to an Access Database
           /*  Class.forName("com.mysql.jdbc.Driver");
             Connection connection = DriverManager.getConnection( "jdbc:mysql://127.0.0.1/managerappinfo", "root", "" );
             
             
            //  Read data from a table

            String sql = "Select id from COMMANDE where date='"+d+"'";
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery( sql );*/
        	DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd 00:00:00");
        	String d=dateFormat.format(this.DateCmd);
        	ResultSet rs= DatabaseAccess.jdbcExecuteQuery("Select id from COMMANDE where date='"+d+"'");
            ResultSetMetaData md = rs.getMetaData();
            int columns = md.getColumnCount();            

            //  Get row data

            while (rs.next())
            {
             id=rs.getInt("id");
          
            }

            rs.close();
           // stmt.close();
           // connection.close();
        }
        catch(Exception e)
        {
            System.out.println( e );
        }
        return id;
        
    }

  /*  public void setId(int id) {
        this.id = id;
    }*/

    public String getDateCmd() {
             DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd 00:00:00");
             String d=dateFormat.format(this.DateCmd);
             return d;
    }

    public void setDateCmd(Date DateCmd) {
        this.DateCmd = DateCmd;
    }
    

  
    
}
    

