package trainig.service;

import java.util.Optional;

import trainig.dataaccess.schoolclass.ClassDataAccess;
import trainig.model.schoolclass.ClassCode;
import trainig.model.schoolclass.ClassName;

public class ClassInfoService {

	public ClassInfoService() {}

//DBにクラス名と紐づけられているクラスコードがあるか確認
	public boolean existenceClassCode(ClassName className){
		ClassDataAccess classDataAccess = new ClassDataAccess();
		Optional<ClassCode> optionalClassCode = classDataAccess.getCode(className);
		if(optionalClassCode.isPresent()){
			return true;
		}
		return false;
	}
}
