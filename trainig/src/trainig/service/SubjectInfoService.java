package trainig.service;

import trainig.dataaccess.subject.SubjectDataAccess;
import trainig.model.subject.SubjectList;

public class SubjectInfoService {

	public SubjectList acquireSubjectList() {
		SubjectDataAccess subjectDataAccess = new SubjectDataAccess();
		return subjectDataAccess.acquireSubjectList();
	}
}
