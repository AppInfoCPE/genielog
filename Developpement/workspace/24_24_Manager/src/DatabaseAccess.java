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

	private static String dbURLServer = "jdbc:derby://localhost:1527/Manager_Database;create=true;user=root;password=root";
	private static Connection conn = null;
	private static Statement stmt = null;
	private static ResultSet results = null;

	private DatabaseAccess() {
	}

	/*	
	 * Use for Select SQL Query
	 */
	public static ArrayList<String[]> derbyExecuteQuery(String requete) {
		ArrayList<String[]> tmp = new ArrayList<String[]>();

		createConnection();

		try {
			stmt = conn.createStatement();
			results = stmt.executeQuery(requete);
			String[] line;
			while (results.next()) {
				line = new String[results.getMetaData().getColumnCount()];

				for (int j = 1; j <= results.getMetaData().getColumnCount(); j++) {
					line[j - 1] = results.getString(j);
				}
				tmp.add(line);
			}
			results.close();
		} catch (Exception sqlExcept) {
			sqlExcept.printStackTrace();
			System.out.println("Execute Query Refused: Connection not established.\n");
		}

		shutdown();

		return tmp;
	}

	/*	
	 * Use for Insert, Update and Delete SQL Query
	 */
	public synchronized static void derbyExecute(String requete) {
		createConnection();

		try {
			stmt = conn.createStatement();
			stmt.execute(requete);
		} catch (Exception sqlExcept) {
			sqlExcept.printStackTrace();
			System.out.println("Execute Query Refused: Connection not established.\n");
		}

		shutdown();
	}

	private static void createConnection() {
		try {
			// Get a connection
			Class.forName("org.apache.derby.jdbc.ClientDriver").newInstance();
			conn = DriverManager.getConnection(dbURLServer);
			// conn.setAutoCommit(false);
		} catch (Exception except) {
			System.out.println("Connection Refused : Server may be not launched.\n");
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
}
