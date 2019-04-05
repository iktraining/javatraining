package trainig.model.schoolclass;

public class SchoolClass {
	private ClassCode code;
	private ClassName name;

	public SchoolClass(ClassCode code, ClassName name){
		this.code  = code;
		this.name  = name;
	}

	public ClassCode getCode(){
		return code;
	}

	public ClassName getName(){
		return name;
	}

	public boolean hasCode() {
		if(code == null) {
			return false;
		}
		if(code.isEmpty()) {
			return false;
		}
		return true;
	}

	public boolean hasName() {
		if(name == null) {
			return false;
		}
		if(name.isEmpty()) {
			return false;
		}
		return true;
	}

}
