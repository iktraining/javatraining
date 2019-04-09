package trainig;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;

import trainig.model.record.RecordPoint;
import trainig.model.student.StudentNo;
import trainig.model.subject.SubjectCode;

public class RecodeDBRegister {
	private Connection conn;

	public RecodeDBRegister() {}
//生徒情報取得
	public void registe(
			StudentNo studentNo,
			ArrayList<SubjectCode> subjectCodeList,
			ArrayList<RecordPoint> recordPointList) {
		conn = DBConnection.getConnection();
		PreparedStatement pstmt = null;

		try {
			for(int i = 0; i < recordPointList.size(); i++) {
				String sql = "insert into records (student_no, subject_code, record_point) values(?, ?, ?)";
				pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, studentNo.getNo());
				pstmt.setString(2, subjectCodeList.get(i).getCode());
				pstmt.setInt(3, recordPointList.get(i).getPoint());
				int num = pstmt.executeUpdate();
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				if(pstmt != null)pstmt.close();
				if(conn != null)DBConnection.cut();
			}catch(SQLException e) {
				e.printStackTrace();
			}
		}
	}
}
