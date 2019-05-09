package service;

import java.util.Optional;

import dataaccess.subject.SubjectDataAccess;
import model.subject.SubjectCode;
import model.subject.SubjectList;
import model.subject.SubjectName;

public class SubjectInfoService {

	//科目コード・科目名一覧取得
	public SubjectList getSubjectList() {
		SubjectDataAccess subjectDataAccess = new SubjectDataAccess();
		return subjectDataAccess.getList();
	}


	//科目コードオブジェクトから科目名オブジェクト取得
	//空も想定してOptional使用
	public Optional<SubjectName> getSubjectName(SubjectCode subjectCode) {
		SubjectDataAccess subjectDataAccess = new SubjectDataAccess();
		Optional<SubjectName> optionalSubjectName =  subjectDataAccess.getName(subjectCode);
		if(!optionalSubjectName.isPresent()) {
			return Optional.ofNullable(null);
		}
		return optionalSubjectName;
	}
}
