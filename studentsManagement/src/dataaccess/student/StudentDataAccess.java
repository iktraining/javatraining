package dataaccess.student;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import dataaccess.dbconnection.DBConnection;
import model.schoolclass.ClassCode;
import model.schoolclass.ClassName;
import model.schoolclass.SchoolClass;
import model.student.Student;
import model.student.StudentList;
import model.student.StudentName;
import model.student.StudentNo;

public class StudentDataAccess {

	public StudentDataAccess() {}

//DBから生徒情報を取得し、生徒リストオブジェクトに格納する
//失敗したらSQLExceptionがthrowされる
	public StudentList getListOrderByNo() {

		String sql = "select st.student_no, st.student_name, st.class_code, cl.class_name from students st inner join classes cl on st.class_code = cl.class_code order by student_no";
		//改行した場合、結合する側の行の最初の文字列に空白がないと、前の行の文字との間に空白がないため、エラーになる

		try(PreparedStatement pstmt = DBConnection.getConnection().prepareStatement(sql);) {
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
//生徒名とクラスコードをDBに登録する
//失敗したらSQLExceptionがRuntimeExceptionとしてthrowされる
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
