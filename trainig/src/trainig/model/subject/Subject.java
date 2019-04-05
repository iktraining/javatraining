package trainig.model.subject;

public class Subject {
	private SubjectCode code;
	private SubjectName name;

	public Subject(SubjectCode code, SubjectName name) {
		this.code = code;
		this.name = name;
	}

	public SubjectCode getCode() {
		return code;
	}

	public SubjectName getName() {
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
