package trainig;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
	private static final String url = "jdbc:postgresql://localhost:5432/training";
	private static final String user = "postgres";
	private static final String password = "am00001";
	private Connection conn = null;

	public DBConnection() {}

	public boolean connect() {
		try {
			conn = DriverManager.getConnection(url, user, password);
			//↑ 決め打ち ("jdbc:postgresql://PostgreSQLサーバ:ポート番号/DB名","ユーザ名","パスワード")
			return true;
		}catch(Exception e){
			e.printStackTrace();
			return false;
		}
	}

	public void cut() {
		try {
			if(conn != null)conn.close();
		}catch(SQLException e) {
			e.printStackTrace();
		}
	}

	public Connection getConnection() {
		return conn;
	}
}
