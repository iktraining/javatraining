package trainig.dataaccess.subject;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Optional;

import trainig.DBConnection;
import trainig.model.subject.Subject;
import trainig.model.subject.SubjectCode;
import trainig.model.subject.SubjectList;
import trainig.model.subject.SubjectName;

public class SubjectDataAccess {

	public SubjectDataAccess() {}

	public SubjectList acquireSubjectList() {
		String sql = " select subject_code, subject_name from subjects";

		try(PreparedStatement pstmt = DBConnection.getConnection().prepareStatement(sql);) {
			ResultSet rset = pstmt.executeQuery();

			ArrayList<Subject> subjectList = new ArrayList<Subject>();
			while(rset.next()){
				String subjectCode = rset.getString("subject_code");
				String subjectName = rset.getString("subject_name");

				subjectList.add(new Subject(new SubjectCode(subjectCode), new SubjectName(subjectName)));
			}
			return new SubjectList(subjectList);
		}catch(SQLException e) {
			throw new RuntimeException(e);
		}
	}
//科目名オブジェクトからOptionalでラップされた科目コードオブジェクトを返す
//科目コードが取得できなかった場合、nullのOptionalを返す。
	public Optional<SubjectCode> getCode(SubjectName subjectName) {
		String code = null;
		String sql = "select subject_code from subjects where subject_name = ?";
		boolean isEmpty = true;

		try (PreparedStatement pstmt =  DBConnection.getConnection().prepareStatement(sql);){
			pstmt.setString(1, subjectName.getName());
			ResultSet rset = pstmt.executeQuery();
			while(rset.next()){
				code = rset.getString(1);
				isEmpty = false;
			}
			if(isEmpty) {
				return Optional.ofNullable(null);
			}
			return Optional.ofNullable(new SubjectCode(code));
		}catch(SQLException e) {
			throw new RuntimeException(e);
		}
	}
}
