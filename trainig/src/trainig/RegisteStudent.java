package trainig;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import trainig.model.schoolclass.ClassName;
import trainig.model.student.StudentName;
import trainig.DBConnection;

public class RegisteStudent {
		private Connection conn;

		public RegisteStudent() {}
//生徒登録
		public boolean registe(StudentName studentName, ClassName className) {
			PreparedStatement pstmt = null;
			conn =  DBConnection.getConnection();
			String sql = "insert into students(student_name, class_code) values( ? ,?)";
			if(!isConnectDB()) {
				return false;
			}
			try {
				conn = DBConnection.getConnection();
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, studentName.getName());
				pstmt.setString(2, getClassCode(className));
				int num = pstmt.executeUpdate();
				return true;
			}catch(SQLException e) {
				e.printStackTrace();
				return false;
			}finally {
				try {
					if(pstmt != null)pstmt.close();
					if(conn != null)DBConnection.cut();
				}catch(SQLException e) {
					e.printStackTrace();
				}
			}
		}

//クラスコード取得
		public String getClassCode(ClassName className) {
			String code = null;
			try {
				String sql = "select class_code from classes where class_name = ?";
				PreparedStatement pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, className.getName());
				ResultSet rset = pstmt.executeQuery();
				while(rset.next()){
					code = rset.getString(1);
				}
				if(rset != null)rset.close();
			}catch(SQLException e) {
				e.printStackTrace();
			}
			return code;
		}
//DB接続確認
		public boolean isConnectDB() {
			try{
				if(!DBConnection.connect()) {//DB接続確立
					System.out.println("DB接続に失敗しました");
					return false;
				}
				return true;
			}catch(Exception e) {
				e.printStackTrace();
				return false;
			}finally {
				try {
					if(conn != null)DBConnection.cut();
				}catch(Exception e) {
					e.printStackTrace();
				}
			}

		}
}
