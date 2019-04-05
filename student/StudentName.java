package student;

public class StudentName {
	private String studentName;

	public StudentName(String studentName) {
		this.studentName = studentName;
	}

	public String getStudentName() {
		return studentName;
	}
	public boolean isEmpty() {
		if(studentName == null) {
			return true;
		}
		if(studentName.isEmpty()) {
			return true;
		}
		return false;
	}
}
