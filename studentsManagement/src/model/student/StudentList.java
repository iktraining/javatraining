package model.student;

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
		//TODO 下記を学生番号を指定して消すようなメソッドに変更
		studentList.remove(index);
	}
	public boolean isEmpty() {
		if(studentList == null || studentList.isEmpty()){
			return true;
		}
		return false;
	}
}
