package trainig.service;

import java.util.Scanner;

import trainig.dataaccess.record.RecordDataAccess;
import trainig.model.record.Record;
import trainig.model.record.ReportCard;

public class ReportCardRegisterService {

	public void register(ReportCard reportCard){
		RecordInfoService recordInfoService = new RecordInfoService();
		RecordDataAccess recordDataAccess = new RecordDataAccess();
		Scanner scan = new Scanner(System.in);

		for(Record record: reportCard.getRecords()) {
			//既に登録済みの場合の処理
			if(recordInfoService.hasRecord(reportCard.getStudentNo(), record.getSubject().getName())) {
				//updateRegisteredRecord(record, reportCard.getStudentNo());
				continue;
			}
			recordDataAccess.registerByReportCard(reportCard);
		}
	}
}
