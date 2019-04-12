package trainig.service;

import java.util.Optional;

import trainig.dataaccess.schoolclass.ClassDataAccess;
import trainig.dataaccess.student.StudentDataAccess;
import trainig.model.schoolclass.ClassCode;
import trainig.model.schoolclass.ClassName;
import trainig.model.student.StudentList;
import trainig.model.student.StudentNo;

public class StudentInfoService {

	public StudentInfoService() {}
//クラス名からそのクラスに在籍する生徒一覧を返す
	public StudentList acquireStudentList(ClassName className) {
		ClassDataAccess classDataAccess = new ClassDataAccess();
		Optional<ClassCode> optionalClassCode = classDataAccess.getCode(className);

		StudentDataAccess studentDataAccess = new StudentDataAccess();

		return studentDataAccess.acquireStudentList(optionalClassCode.orElseThrow());
	}

//生徒番号からその生徒が存在するかどうかを返す
	public boolean  existenceStudent(StudentNo studentNo){
		StudentDataAccess studentDataAccess = new StudentDataAccess();
		if(studentDataAccess.findStudentByNo(studentNo)) {
			return true;
		}
		return false;
	}
}
