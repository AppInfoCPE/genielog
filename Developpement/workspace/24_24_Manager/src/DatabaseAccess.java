import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;


/*
 * Use with DatabaseAccess.Name_Method() in your program
 */
public class DatabaseAccess {

	private static String dbClass = "com.mysql.jdbc.Driver";
	private static String dbUrl = "jdbc:mysql://db4free.net:3306/managerappinfo";
	private static String dbLogin = "julien";
	private static String dbMdp = "skateboard1";
	private static Connection conn = null;
	private static Statement stmt = null;
	private static ResultSet results = null;

	private DatabaseAccess() {
	}

	/*	
	 * Use for Select SQL Query
	 */
	public static ResultSet jdbcExecuteQuery(String requete) {
		//ArrayList<String[]> tmp = new ArrayList<String[]>();

		//createConnection();

		try {
			stmt = conn.createStatement();
			results = stmt.executeQuery(requete);
			/*String[] line;
			while (results.next()) {
				line = new String[results.getMetaData().getColumnCount()];

				for (int j = 1; j <= results.getMetaData().getColumnCount(); j++) {
					line[j - 1] = results.getString(j);
				}
				tmp.add(line);
			}
			results.close();*/
		} catch (Exception sqlExcept) {
			sqlExcept.printStackTrace();
			System.out.println("Execute Query Refused: Connection not established.\n");
		}
		return results;

		//shutdown();

		//return tmp;
	}

	/*	
	 * Use for Insert, Update and Delete SQL Query
	 */
	public synchronized static void jdbcExecute(String requete) {
		//createConnection();

		try {
			stmt = conn.createStatement();
			stmt.executeUpdate(requete);
		} catch (Exception sqlExcept) {
			sqlExcept.printStackTrace();
			System.out.println("Execute Query Refused: Connection not established.\n");
		}

		//shutdown();
	}

	private static void createConnection() {
		try {
			// Get a connection
			Class.forName(dbClass).newInstance();
			conn = DriverManager.getConnection(dbUrl, dbLogin, dbMdp);
			// conn.setAutoCommit(false);
		} catch (Exception except) {
			System.out.println(except);
		}

	}

	private static void shutdown() {
		try {
			if (stmt != null) {
				stmt.close();
			}
			if (conn != null) {
				// DriverManager.getConnection(dbURLServer + ";shutdown=true");
				conn.close();
			}
		} catch (SQLException sqlExcept) {
			sqlExcept.printStackTrace();
		}
	}

	public static void initialisation() {
		createConnection();
	}
}
