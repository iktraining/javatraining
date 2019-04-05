package schoolclass;

public class SchoolClass {
	private ClassCode classCode;
	private ClassName className;

	public SchoolClass(ClassCode code, ClassName name){
		classCode  = code;
		className  = name;
	}

	public ClassCode getCode(){
		return classCode;
	}

	public ClassName getName(){
		return className;
	}

	public boolean hasCode() {
		if(classCode == null) {
			return false;
		}
		if(classCode.isEmpty()) {
			return false;
		}
		return true;
	}

	public boolean hasName() {
		if(className == null) {
			return false;
		}
		if(className.isEmpty()) {
			return false;
		}
		return true;
	}

}
