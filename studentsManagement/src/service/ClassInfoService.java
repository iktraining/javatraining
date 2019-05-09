package service;

import java.util.Optional;

import dataaccess.schoolclass.ClassDataAccess;
import model.schoolclass.ClassCode;
import model.schoolclass.ClassName;
import model.schoolclass.SchoolClassList;

public class ClassInfoService {

	public ClassInfoService() {}

	public SchoolClassList getSchoolClassList() {
		ClassDataAccess classDataAccess = new ClassDataAccess();
		return classDataAccess.getList();
	}

	public Optional<ClassName> getClassName(ClassCode classCode) {
		ClassDataAccess classDataAccess = new ClassDataAccess();
		Optional<ClassName> optionalClassName =  classDataAccess.getName(classCode);
		if(!optionalClassName.isPresent()) {
			return Optional.ofNullable(null);
		}
		return optionalClassName;
	}
}
