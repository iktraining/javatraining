package session;

import model.record.ReportCard;
import model.schoolclass.SchoolClass;
import model.student.StudentName;

public class RegistrationSession {
	private StudentName studentName;
	private SchoolClass schoolClass;
	private ReportCard reportCard;

	//To Test
	public RegistrationSession(StudentName studentName, SchoolClass schoolClass) {
		this.studentName = studentName;
		this.schoolClass = schoolClass;
	}

	public RegistrationSession(StudentName studentName,  SchoolClass schoolClass, ReportCard reportCard) {
		this.studentName = studentName;
		this.schoolClass = schoolClass;
		this.reportCard = reportCard;
	}

	public StudentName getStudentName() {
		return studentName;
	}

	public  SchoolClass getSchoolClass() {
		return  schoolClass;
	}

	public ReportCard getReportCard() {
		return reportCard;
	}

	public boolean hasStudentName() {
		if(studentName == null) {
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

	public boolean hasReportCard() {
		if(reportCard == null) {
			return false;
		}
		return true;
	}
}
