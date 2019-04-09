//Java DB1-2
package trainig;

import trainig.model.student.StudentName;
import trainig.model.student.StudentNo;

public class StudentListModel {
	private StudentName studentName;
	private StudentNo studentNo;

	public StudentListModel(StudentNo studentNo, StudentName studentName) {
		this.studentNo = studentNo;
		this.studentName = studentName;
	}

	public StudentNo getStudentNo() {
		return studentNo;
	}
	public StudentName getStudentName() {
		return studentName;
	}
}
