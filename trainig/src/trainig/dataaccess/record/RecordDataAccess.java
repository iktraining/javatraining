package trainig.dataaccess.record;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

import trainig.DBConnection;
import trainig.model.record.Record;
import trainig.model.record.RecordPoint;
import trainig.model.record.ReportCard;
import trainig.model.student.StudentNo;
import trainig.model.subject.Subject;
import trainig.model.subject.SubjectCode;
import trainig.model.subject.SubjectName;

public class RecordDataAccess {

	public RecordDataAccess() {}

//成績評価点をDBに登録する
//失敗したらSQLExceptionがthrowされる
	public void register(Record record, StudentNo studentNo) {

		String sql = "insert into records (student_no, subject_code, record_point) values(?, ?, ?)";

		try (PreparedStatement pstmt = DBConnection.getConnection().prepareStatement(sql);){
				pstmt.setInt(1, studentNo.getNo());
				pstmt.setString(2, record.getSubject().getCode().getCode());
				pstmt.setInt(3, record.getPoint().getPoint());
				int num = pstmt.executeUpdate();
		}catch(SQLException e) {
			throw new RuntimeException(e);
		}
	}

//成績評価点をReportCardからDBに登録する
//失敗したらSQLExceptionがthrowされる
	public void registerByReportCard(ReportCard reportCard) {

		String sql = "insert into records (student_no, subject_code, record_point) values(?, ?, ?)";

		try (PreparedStatement pstmt = DBConnection.getConnection().prepareStatement(sql);){
			for(Record record:reportCard.getRecords()) {
				pstmt.setInt(1, reportCard.getStudentNo().getNo());
				pstmt.setString(2, record.getSubject().getCode().getCode());
				pstmt.setInt(3, record.getPoint().getPoint());
				int num = pstmt.executeUpdate();
			}
		}catch(SQLException e) {
			throw new RuntimeException(e);
		}
	}

//成績評価点をDBにアップデートする
//失敗したらSQLExceptionがthrowされるUPDATE dept
	public void update(Record record, StudentNo studentNo) {

		String sql = "update records set record_point = ?, update_at = now() where subject_code = ? and student_no = ?";

		try (PreparedStatement pstmt = DBConnection.getConnection().prepareStatement(sql);){
				pstmt.setInt(1, record.getPoint().getPoint());
				pstmt.setString(2, record.getSubject().getCode().getCode());
				pstmt.setInt(3, studentNo.getNo());
				int num = pstmt.executeUpdate();
		}catch(SQLException e) {
			throw new RuntimeException(e);
		}
	}

//成績が登録されているか、DBから探す
//あればRecordオブジェクトを返し、なければOptionalオブジェクトのnullを返す
	public Optional<Record> findRecord(StudentNo studentNo, SubjectCode subjectCode) {

		String sql = "select records.subject_code, subject_name, record_point"
				+ " from records  inner join subjects on records.subject_code = subjects.subject_code"
				+ " where student_no = ? and records.subject_code = ?";
		boolean isEmpty = true;
		int point = 0;
		String code = null;
		String name = null;

		try (PreparedStatement pstmt = DBConnection.getConnection().prepareStatement(sql);){
			pstmt.setInt(1, studentNo.getNo());
			pstmt.setString(2, subjectCode.getCode());
			ResultSet rset = pstmt.executeQuery();

			while(rset.next()) {
				code = rset.getString("subject_code");
				name = rset.getString("subject_name");
				point = rset.getInt("record_point");
				isEmpty = false;
			}

			if(isEmpty) {
				return Optional.ofNullable(null);
			}

			Subject subject = new Subject(new SubjectCode(code), new SubjectName(name));
			RecordPoint recordPoint = new RecordPoint(point);
			return Optional.ofNullable(new Record(subject, recordPoint));
			}catch(SQLException e) {
		throw new RuntimeException(e);
		}
	}
}
