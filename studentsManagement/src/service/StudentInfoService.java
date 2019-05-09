package service;

import dataaccess.student.StudentDataAccess;
import model.schoolclass.SchoolClass;
import model.student.StudentList;
import model.student.StudentName;

public class StudentInfoService {

	public StudentInfoService() {}

	//在籍する生徒一覧を返す
		public StudentList getStudentListOrderByNo() {
			StudentDataAccess studentDataAccess = new StudentDataAccess();
			return studentDataAccess.getListOrderByNo();
		}
	//生徒登録
		public void register(StudentName studentName, SchoolClass schoolClass){
			StudentDataAccess studentDataAccess = new StudentDataAccess();
			studentDataAccess.register(studentName, schoolClass.getCode());
		}
}
