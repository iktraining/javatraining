package model.validation;

public class RegistrationValidation {
	private boolean normalStudentNameLength = true;
	private boolean hasStudentName = true;
	private boolean hasClassCode = true;
	private boolean matchClassCode = true;
	private boolean hasReportCard = true;

	public RegistrationValidation() {}
	public RegistrationValidation(Boolean normalStudentNameLength, Boolean hasStudentName, Boolean hasClassCode, Boolean matchClassCode) {
		this.normalStudentNameLength = normalStudentNameLength;
		this.hasStudentName = hasStudentName;
		this.hasClassCode = hasClassCode;
		this.matchClassCode = matchClassCode;
	}

	public boolean normalStudentNameLength() {
		return normalStudentNameLength;
	}

	public boolean hasStudentName() {
		return hasStudentName;
	}

	public boolean hasClassCode() {
		return hasClassCode;
	}

	public boolean matchClassCode() {
		return matchClassCode;
	}

	public boolean evaluationReportCard() {
		return hasReportCard;
	}

	public boolean studentInfoEvalution() {
		if(normalStudentNameLength == false) {
			return false;
		}
		if(hasStudentName == false) {
			return false;
		}
		if(hasClassCode == false) {
			return false;
		}
		if(matchClassCode == false) {
			return false;
		}
		return true;
	}


	public void setStudentNameLengthEvaluation(Boolean normalStudentNameLength) {
		this.normalStudentNameLength = normalStudentNameLength;
	}
	public void setStudentNameExistEvaluation(Boolean hasStudentName) {
		this.hasStudentName = hasStudentName;
	}
	public void setClassCodeExistEvaluation(Boolean hasClassCodeEmpty) {
		this.hasClassCode = hasClassCodeEmpty;
	}
	public void setMatchClassCodeEvaluation(Boolean matchClassCode) {
		this.matchClassCode = matchClassCode;
	}

}
