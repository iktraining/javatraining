package trainig;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import trainig.model.schoolclass.ClassName;
import trainig.model.student.StudentName;

public class RegisteStudent {
		private StudentName studentName;
		private ClassName className;
		private Connection conn = null;
		private DBConnection db = null;

		public RegisteStudent(StudentName studentName, ClassName className) {
			this.studentName = studentName;
			this.className = className;
			db = new DBConnection();
			if(db.connect()) {//DB接続確立
				conn = db.getConnection();
				registeStudent();
			}else {
				System.out.println("データベース接続に失敗しました。");
				System.out.println("プログラムを終了します。");
			}
		}
//生徒登録
		public void registeStudent() {
			PreparedStatement pstmt = null;
			try {
				String sql = "insert into students values(?,?,?,now(),now())";
				pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, (getStudentNo()+1));
				pstmt.setString(2, studentName.getName());

				if(null == getClassCode()) {
					System.out.printf("%s というクラス名は存在しません\n", className.getName());
					System.out.println("プログラムを終了します。");
					return;
				}
				pstmt.setString(3, getClassCode());
				int num = pstmt.executeUpdate();
				System.out.println("登録が完了しました。");
			}catch(SQLException e) {
				e.printStackTrace();
			}finally {
				try {
					if(pstmt != null)pstmt.close();
					if(conn != null)db.cut();
				}catch(SQLException e) {
					e.printStackTrace();
				}
			}
		}

//登録済みの生徒数取得
//これだと同じ生徒数を取得する場合がある
		public int getStudentNo() {
			Integer number = null;

			try {
				String sql = "select count(*) from students";
				PreparedStatement pstmt = conn.prepareStatement(sql);
				ResultSet rset = pstmt.executeQuery();

				while(rset.next()){
					number = rset.getInt(1);
				}
				if(rset != null)rset.close();
			}catch(SQLException e) {
				e.printStackTrace();
			}
			return number;
		}
//クラスコード取得
		public String getClassCode() {
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
