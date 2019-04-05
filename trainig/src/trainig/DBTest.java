package trainig;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBTest {

	public static void main(String[] args) {
		Connection conn = null;
		try {
			Class.forName("org.postgresql.Driver");
			conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres","postgres","am00001");
			//↑ 決め打ち ("jdbc:postgresql://PostgreSQLサーバ:ポート番号/DB名","ユーザ名","パスワード")
			conn.close();
			System.out.println("OK");
		}catch(Exception e){
			e.printStackTrace();
			System.out.println("NG");
		}
	}
}
