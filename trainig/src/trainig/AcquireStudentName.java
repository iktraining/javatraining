package trainig;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import trainig.model.student.StudentName;
import trainig.model.student.StudentNo;

public class AcquireStudentName {
	private StudentName studentName;

	public AcquireStudentName() {}

	public void acquireName(StudentNo studentNo) {
		Connection conn = DBConnection.getConnection();
		PreparedStatement pstmt = null;
		try {
			String sql = "select student_name from students where student_no = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, studentNo.getNo());
			ResultSet rset = pstmt.executeQuery();
			if(rset.next()){
				String name = rset.getString("student_name");
				studentName = new StudentName(name);
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				if(pstmt != null)pstmt.close();
			}catch(SQLException e) {
				e.printStackTrace();
			}
		}
	}

	public StudentName getStudentName() {
		return studentName;
	}

	public boolean hasStudentName() {
		if(studentName == null) {
			return false;
		}
		return true;
	}
}
