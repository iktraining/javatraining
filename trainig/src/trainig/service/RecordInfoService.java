package trainig.service;

import java.util.Optional;

import trainig.dataaccess.record.RecordDataAccess;
import trainig.dataaccess.subject.SubjectDataAccess;
import trainig.model.record.Record;
import trainig.model.student.StudentNo;
import trainig.model.subject.SubjectCode;
import trainig.model.subject.SubjectName;

public class RecordInfoService {

//既に成績が登録されているか確認
	public boolean hasRecord(StudentNo studentNo, SubjectName subjectName) {
		SubjectDataAccess subejctDataAccess = new SubjectDataAccess();
		Optional<SubjectCode> optionalSubjectCode = subejctDataAccess.getCode(subjectName);
		if(!optionalSubjectCode.isPresent()){
			return false;
		}
		RecordDataAccess recordDataAccess = new RecordDataAccess();
		Optional<Record> optionalRecord =  recordDataAccess.findRecord(studentNo, optionalSubjectCode.orElseThrow());
		if(!optionalRecord.isPresent()) {
			return false;
		}
		return true;
	}
//登録されている成績を取得する
	public Optional<Record> acqireRecord(StudentNo studentNo, SubjectName subjectName){
		SubjectDataAccess subejctDataAccess = new SubjectDataAccess();
		Optional<SubjectCode> optionalSubjectCode = subejctDataAccess.getCode(subjectName);
		if(!optionalSubjectCode.isPresent()){
			return Optional.ofNullable(null);
		}
		RecordDataAccess recordDataAccess = new RecordDataAccess();
		Optional<Record> optionalRecord =  recordDataAccess.findRecord(studentNo, optionalSubjectCode.orElseThrow());
		if(!optionalRecord.isPresent()) {
			return Optional.ofNullable(null);
		}
		return optionalRecord;
	}

}
