package model.student;

public class StudentName {
	private String name;

	public StudentName(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}
	public boolean isEmpty() {
		if(name == null) {
			return true;
		}
		if(name.isEmpty()) {
			return true;
		}
		return false;
	}

	public boolean lengthValidation() {
		if(name.length() >= 1 && name.length() <= 60) {
			return true;
		}
		return false;
	}

	public boolean lengthValidation(String name) {
		if(name.length() >= 1 && name.length() <= 60) {
			return true;
		}
		return false;
	}

	@Override
	public String toString() {
		return String.format("StudentName [studentName=%s]", name);
	}
}
