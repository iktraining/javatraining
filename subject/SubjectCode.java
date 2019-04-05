package subject;

public class SubjectCode {
	private String subjectCode;

	public SubjectCode(String subjectCode) {
		this.subjectCode = subjectCode;
	}

	public String getSubjectCode() {
		return subjectCode;
	}

	public boolean isEmpty() {
		if(subjectCode == null || subjectCode.isEmpty()) {
			return true;
		}
		return false;
	}
}
