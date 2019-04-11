package trainig.service;

import java.util.Optional;

import trainig.dataaccess.schoolclass.ClassDataAccess;
import trainig.dataaccess.student.StudentDataAccess;
import trainig.model.schoolclass.ClassCode;
import trainig.model.schoolclass.ClassName;
import trainig.model.student.StudentName;

public class StudentRegisterService {

		public StudentRegisterService() {}
//生徒登録
		public void register(StudentName studentName, ClassName className){
			ClassDataAccess classDataAccess = new ClassDataAccess();
			Optional<ClassCode> optionalClassCode = classDataAccess.getCode(className);
			StudentDataAccess studentDataAccess = new StudentDataAccess();
			studentDataAccess.register(studentName, optionalClassCode.get());
		}
}
