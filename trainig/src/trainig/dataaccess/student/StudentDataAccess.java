package trainig.dataaccess.student;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import trainig.DBConnection;
import trainig.model.schoolclass.ClassCode;
import trainig.model.student.StudentName;

public class StudentDataAccess {

	public StudentDataAccess() {}

//生徒名とクラスコードをDBに登録する
//失敗したらSQLExceptionがthrowされる
	public void register(StudentName studentName, ClassCode classCode) {

		String sql = "insert into students(student_name, class_code) values( ? ,?)";
		try (PreparedStatement pstmt = DBConnection.getConnection().prepareStatement(sql);){
			pstmt.setString(1, studentName.getName());
			pstmt.setString(2, classCode.getCode());
			int num = pstmt.executeUpdate();
			if(pstmt != null)pstmt.close();
		}catch(SQLException e) {
			throw new RuntimeException(e);
		}
	}
}

