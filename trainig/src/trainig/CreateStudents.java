package trainig;

public class CreateStudents {
	public static void main(String[] args) {
		StudentRegister data = new StudentRegister();
		RegisteStudent regStudent = new RegisteStudent(data.getStudentName(), data.getClassName());
	}
}
