package model.student;

import model.schoolclass.SchoolClass;

public class Student {
	private StudentNo no;
	private StudentName name;
	private SchoolClass schoolClass;

	public Student(StudentNo no, StudentName name, SchoolClass schoolClass) {
		this.no = no;
		this.name = name;
		this.schoolClass = schoolClass;
	}

	public StudentNo getNo() {
		return no;
	}

	public StudentName getName() {
		return name;
	}

	public SchoolClass getSchoolClass() {
		return schoolClass;
	}

	public boolean hasNo() {
		if(no == null) {
			return false;
		}
		if(no.isEmpty()) {
			return false;
		}
		return true;
	}

	public boolean hasName() {
		if(no == null) {
			return false;
		}if(name.isEmpty()) {
			return false;
		}
		return true;
	}

	public boolean hasSchoolClass() {
		if(schoolClass == null) {
			return false;
		}
		return true;
	}
}
