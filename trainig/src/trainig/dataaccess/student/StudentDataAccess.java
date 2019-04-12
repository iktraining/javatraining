package trainig.dataaccess.student;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import trainig.DBConnection;
import trainig.model.schoolclass.ClassCode;
import trainig.model.schoolclass.ClassName;
import trainig.model.schoolclass.SchoolClass;
import trainig.model.student.Student;
import trainig.model.student.StudentList;
import trainig.model.student.StudentName;
import trainig.model.student.StudentNo;

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

//DBから生徒情報を取得し、生徒オブジェクトに格納する
//失敗したらSQLExceptionがthrowされる
	public StudentList acquireStudentList(ClassCode classCode) {

		String sql = "select st.student_no, st.student_name, st.class_code, cl.class_name"
				+ " from students st inner join classes cl on st.class_code = cl.class_code"
				+ " where st.class_code = ? order by student_no";
		//結合する側の行の最初の文字列に空白がないと、前の行の文字との間に空白がないため、エラーになる

		try(PreparedStatement pstmt = DBConnection.getConnection().prepareStatement(sql);) {
			pstmt.setString(1, classCode.getCode());
			ResultSet rset = pstmt.executeQuery();

			ArrayList<Student> studentList = new ArrayList<Student>();
			while(rset.next()){
				int studentNo = rset.getInt("student_no");
				String studentName = rset.getString("student_name");
				String strClassCode = rset.getString("class_code");
				String className = rset.getString("class_name");

				studentList.add(new Student(
									new StudentNo(studentNo), new StudentName(studentName),
									new SchoolClass(
										new ClassCode(strClassCode), new ClassName(className)
									)
								)
				);
			}
			return new StudentList(studentList);
		}catch(SQLException e) {
			throw new RuntimeException(e);
		}
	}
//DBから入力された生徒番号と一致する番号があるか調べる。
//あったらtrue、なければfalseが返る
	public boolean findStudentByNo(StudentNo studentNo){
		String sql = "select student_no from students where student_no = ?";
		try(PreparedStatement pstmt = DBConnection.getConnection().prepareStatement(sql);) {
			pstmt.setInt(1, studentNo.getNo());
			ResultSet rset = pstmt.executeQuery();
			if(rset.next()){
				return true;
			}
			return false;
		}catch(SQLException e) {
			throw new RuntimeException(e);
		}
	}
}

