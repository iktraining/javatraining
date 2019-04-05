package schoolclass;

public class ClassCode {
	private String classCode;

	public ClassCode(String classCode){
		this.classCode = classCode;
	}

	public String getClassCode(){
		return classCode;
	}

	public boolean isEmpty() {
		if(classCode == null || classCode.isEmpty()) {
			return true;
		}
		return false;
	}
}
