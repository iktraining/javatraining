package student;

public class StudentNo {
	private int studentNo = -1;

	public StudentNo(int studentNo) {
		this.studentNo = studentNo;
	}

	public int getStudentNo() {
		return studentNo;
	}

	public boolean isEmpty() {
		if(studentNo == -1) {
			return true;
		}
		return false;
	}
}
