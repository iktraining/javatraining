package dataaccess.dbconnection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
	private static final String DRIVER_NAME = "org.postgresql.Driver";//サーバで使用する場合必要になる
	private static final String URL = "jdbc:postgresql://localhost:5432/*****";//jdbc:postgresql://ip/database-name
	private static final String USER = "*****";
	private static final String PASSWORD = "*****";
	private static Connection conn = null;

	public DBConnection() {}

	public static Connection connect() {
		try {
			Class.forName(DRIVER_NAME);//サーバで使用する場合必要になる
			conn = DriverManager.getConnection(URL, USER, PASSWORD);
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
