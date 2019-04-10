package trainig;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
	private static final String url = "jdbc:postgresql://localhost:5432/training";
	private static final String user = "postgres";
	private static final String password = "am00001";
	private static Connection conn = null;

	public DBConnection() {}

	public static Connection connect() {
		try {
			conn = DriverManager.getConnection(url, user, password);
			//↑ 決め打ち ("jdbc:postgresql://PostgreSQLサーバ:ポート番号/DB名","ユーザ名","パスワード")
			return conn;
		}catch(Exception e){
			e.printStackTrace();
			return conn;
		}
	}

	public static void cut() {
		try {
			if(conn != null)conn.close();
		}catch(SQLException e) {
			e.printStackTrace();
		}
	}

	public static Connection getConnection() {
			return conn;

	}
	//DB接続確認
	public static boolean isConnectionDB() {
		if(null == DBConnection.getConnection()) {
			return false;
		}
		return true;
	}
}
