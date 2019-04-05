package subject;

public class Subject {
	private SubjectCode subjectCode;
	private SubjectName subjectName;

	public Subject(SubjectCode code, SubjectName name) {
		this.subjectCode = code;
		this.subjectName = name;
	}

	public SubjectCode getCode() {
		return subjectCode;
	}

	public SubjectName getName() {
		return subjectName;
	}

	public boolean hasCode() {
		if(subjectCode == null) {
			return false;
		}
		if(subjectCode.isEmpty()) {
			return false;
		}
		return true;
	}

	public boolean hasName() {
		if(subjectName == null) {
			return false;
		}
		if(subjectName.isEmpty()) {
			return false;
		}
		return true;
	}
}
