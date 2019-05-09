package model.subject;

public class SubjectCode {
	private String code;

	public SubjectCode(String code) {
		this.code = code;
	}

	public String getCode() {
		return code;
	}

	public boolean isEmpty() {
		if(code == null || code.isEmpty()) {
			return true;
		}
		return false;
	}
}
