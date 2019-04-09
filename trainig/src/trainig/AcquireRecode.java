package trainig;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import trainig.model.record.Record;
import trainig.model.record.RecordPoint;
import trainig.model.student.StudentNo;
import trainig.model.subject.Subject;
import trainig.model.subject.SubjectCode;
import trainig.model.subject.SubjectName;

public class AcquireRecode {
	private ArrayList<Record> recordList;

	public AcquireRecode() {}

	public void acquireRecode(StudentNo studentNo) {
		Connection conn = DBConnection.getConnection();
		PreparedStatement pstmt = null;
		try {
			String sql = "select rc.subject_code, sj.subject_name, rc.record_point from records rc inner join subjects sj on rc.subject_code = sj.subject_code where student_no = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, studentNo.getNo());
			ResultSet rset = pstmt.executeQuery();
			recordList = new ArrayList<Record>();
			while(rset.next()){
				String code = rset.getString("subject_code");
				String name = rset.getString("subject_name");
				int point = rset.getInt("record_point");
				recordList.add(
					new Record(
						new Subject(new SubjectCode(code), new SubjectName(name)),
						new RecordPoint(point)
					)
				);

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

	public ArrayList<Record> getRecordList() {
		return recordList;
	}

	public boolean isRecordListEmpty() {
		if(recordList.isEmpty()) {
			return true;
		}
		return false;
	}
}
