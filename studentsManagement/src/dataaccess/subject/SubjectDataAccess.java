package dataaccess.subject;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Optional;

import dataaccess.dbconnection.DBConnection;
import model.subject.Subject;
import model.subject.SubjectCode;
import model.subject.SubjectList;
import model.subject.SubjectName;

public class SubjectDataAccess {

	public SubjectDataAccess() {}

	public SubjectList getList() {
		String sql = "SELECT subject_code, subject_name FROM subjects ORDER BY display_priority";

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
	//科目コードオブジェクトからOptionalでラップされた科目名オブジェクトを返す
	//科目名が取得できなかった場合、nullのOptionalを返す。
		public Optional<SubjectName> getName(SubjectCode subjectCode) {
			String name = null;
			String sql = "SELECT subject_name FROM subjects WHERE subject_code = ?";
			boolean isEmpty = true;

			try (PreparedStatement pstmt =  DBConnection.getConnection().prepareStatement(sql);){
				pstmt.setString(1, subjectCode.getCode());
				ResultSet rset = pstmt.executeQuery();
				while(rset.next()){
					name = rset.getString(1);
					isEmpty = false;
				}
				if(isEmpty) {
					return Optional.ofNullable(null);
				}
				return Optional.ofNullable(new SubjectName(name));
			}catch(SQLException e) {
				throw new RuntimeException(e);
			}
		}
}
