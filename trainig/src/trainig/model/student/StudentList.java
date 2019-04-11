package trainig.model.student;

import java.util.ArrayList;

public class StudentList {
	private ArrayList<Student> studentList;

	public StudentList(ArrayList<Student> studeList) {
		this.studentList = studeList;
	}

	public ArrayList<Student> getList(){
		return studentList;
	}

	public void remove(int index) {
		//学生番号をして消すように変更
		studentList.remove(index);
	}
	public boolean isEmpty() {
		if(studentList == null || studentList.isEmpty()){
			return true;
		}
		return false;
	}
}
