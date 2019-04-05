package subject;

public class SubjectName {
	private String subjectName;

	public SubjectName(String subjectName) {
		this.subjectName = subjectName;
	}

	public String getSubjectName() {
		return subjectName;
	}

	public boolean isEmpty() {
		if(subjectName == null || subjectName.isEmpty()) {
			return true;
		}
		return false;
	}
}
