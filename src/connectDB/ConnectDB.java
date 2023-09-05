package connectDB;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectDB {
//	private static ConnectDB instance;
//	private Connection connection;
//	private ConnectDB() {
//		try {
//			String url = "jdbc:sqlserver://localhost:1433;databaseName=QLLinhKien;trustServerCertificate=true";
//			connection = DriverManager.getConnection(url , "abcd", "abcd");
//			System.out.println("Connected");
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
//	}
//
//	public Connection getConnection() {
//		return connection;
//	}
//
//	public synchronized static ConnectDB getInstance() {
//		if (instance == null)
//			instance = new ConnectDB();
//		return instance;
//	}
//
//	public void close() {
//		try {
//			connection.close();
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
//	}
	
	public static Connection con = null;
	public static ConnectDB instance = new ConnectDB();
	public static ConnectDB getInstance() {
		return instance;
	}
	public void connect() throws SQLException {
		String url = "jdbc:sqlserver://localhost:1433;databaseName=QLLinhKien";
		String user = "abcd";
		String password = "abcd";
//		String user = "sa";
//		String password = "123";
		con = DriverManager.getConnection(url, user, password);
	}
	public void disconnect() {
		if(con!=null) {
			try {
				con.close();
			} catch (SQLException e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		}
	}
	public static Connection getConnection() throws SQLException {
		//connect();
		return con;
	}
}
