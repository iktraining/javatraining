//Java DB 1-2
package trainig;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import trainig.model.schoolclass.ClassCode;
import trainig.model.schoolclass.ClassName;
import trainig.model.student.StudentName;
import trainig.model.student.StudentNo;

public class AcquireStudent {
	private ClassName className;
	private ClassCode classCode;
	private Connection conn;
	private DBConnection db;
	private ArrayList<StudentListModel> studentList;

	public AcquireStudent() {}
//生徒情報取得
	public void acquire(ClassName className) {
		conn = db.getConnection();
		PreparedStatement pstmt = null;
		try {
			ClassCode classCode = new ClassCode(getClassCode(className));

			String sql = "select student_no, student_name from students where class_code = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, getClassCode(className));
			ResultSet rset = pstmt.executeQuery();

			studentList = new ArrayList<StudentListModel>();
			while(rset.next()){
				int no = rset.getInt("student_no");
				String name = rset.getString("student_name");
				studentList.add(new StudentListModel(new StudentNo(no), new StudentName(name)));
				//リストにStudentListModelオブジェクト登録
			}
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
//生徒リスト
	public ArrayList<StudentListModel>  getStudentList(){
		return studentList;
	}
}
