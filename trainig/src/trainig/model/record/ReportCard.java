package trainig.model.record;

import java.util.ArrayList;

import trainig.model.student.StudentNo;

public class ReportCard {
	private StudentNo studentNo;
	private ArrayList<Record> records;

	public ReportCard(StudentNo studentNo, ArrayList<Record> records) {
		this.studentNo = studentNo;
		this.records = records;
	}

	public StudentNo getStudentNo() {
		return studentNo;
	}

	public ArrayList<Record> getRecords() {
		return records;
	}

	public boolean hasStudentNo() {
		if(studentNo == null) {
			return false;
		}
		return true;
	}

	public boolean hasRecords() {
		if(records.isEmpty()) {
			return false;
		}
		return true;
	}
	public int getRecordsSize() {
		return records.size();
	}

	@Override
	public String toString() {
		return String.format("ReportCard [studentNo=%s, records=%s]", studentNo, records);
	}
}
