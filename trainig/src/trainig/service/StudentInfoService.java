package trainig.service;

import java.util.Optional;

import trainig.dataaccess.schoolclass.ClassDataAccess;
import trainig.dataaccess.student.StudentDataAccess;
import trainig.model.schoolclass.ClassCode;
import trainig.model.schoolclass.ClassName;
import trainig.model.student.StudentList;

public class StudentInfoService {

	public StudentInfoService() {}

	public StudentList acquireStudentList(ClassName className) {
		ClassDataAccess classDataAccess = new ClassDataAccess();
		Optional<ClassCode> optionalClassCode = classDataAccess.getCode(className);

		StudentDataAccess studentDataAccess = new StudentDataAccess();

		return studentDataAccess.acquireStudentList(optionalClassCode.orElseThrow());
	}
}
