package model.student;

public class StudentNo {
	private Integer no;

	public StudentNo(Integer no) {
		this.no = no;
	}

	public Integer getNo() {
		return no;
	}

	public boolean isEmpty() {
		if(no == null) {
			return true;
		}
		return false;
	}

	@Override
	public String toString() {
		return String.format("StudentNo [studentNo=%s]", no);
	}
}
