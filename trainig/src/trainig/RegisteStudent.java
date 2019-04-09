package trainig;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import trainig.model.schoolclass.ClassName;
import trainig.model.student.StudentName;

public class RegisteStudent {
		private Connection conn;
		private DBConnection db;

		public RegisteStudent() {}
//生徒登録
		public boolean registe(StudentName studentName, ClassName className) {
			conn = db.getConnection();
			PreparedStatement pstmt = null;
			try {
				String sql = "insert into students(student_name, class_code) values( ? ,?)";
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
					if(conn != null)db.cut();
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
}
