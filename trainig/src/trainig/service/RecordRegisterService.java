package trainig.service;

import trainig.dataaccess.record.RecordDataAccess;
import trainig.model.record.Record;
import trainig.model.student.StudentNo;

public class RecordRegisterService {

	public RecordRegisterService() {}

//成績登録
	public void register(Record record, StudentNo studentNo){
		RecordDataAccess recordDataAccess = new RecordDataAccess();
		recordDataAccess.register(record, studentNo);
	}

//成績の上書き
	public void updateRegisteredRecord(Record record, StudentNo studentNo) {
		RecordDataAccess recordDataAccess = new RecordDataAccess();
		recordDataAccess.update(record, studentNo);
	}
}