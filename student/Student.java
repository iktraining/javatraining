package student;

import schoolclass.SchoolClass;

public class Student {
	private StudentNo no;
	private StudentName name;
	private SchoolClass schoolClass;

	public Student(StudentNo no, StudentName name, SchoolClass schoolClass) {
		this.no = no;
		this.name = name;
		this.schoolClass = schoolClass;
	}

	public StudentNo getStudentNo() {
		return no;
	}

	public StudentName getStudentName() {
		return name;
	}

	public SchoolClass getStudentClass() {
		return schoolClass;
	}

	public boolean hasStudentNo() {
		if(no == null) {
			return false;
		}
		if(no.isEmpty()) {
			return false;
		}
		return true;
	}

	public boolean hasStudentName() {
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
