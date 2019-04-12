package trainig.dataaccess.record;

import java.util.Optional;

import trainig.DBConnection;
import trainig.model.record.Record;
import trainig.model.student.StudentNo;
import trainig.model.subject.SubjectCode;

public class RecordDataAccessTester {
	public static void main(String[] args) {
		try {
			DBConnection.connect();
			StudentNo studentNo = new StudentNo(1);
			SubjectCode subjectCode = new SubjectCode("english");

			RecordDataAccess recordDataAccess = new RecordDataAccess();
			Optional<Record> optionalRecord = recordDataAccess.findRecord(studentNo, subjectCode);
			if(optionalRecord.isPresent()) {
				System.out.println("生徒番号\t科目コード\t科目名\t点数\n");
				System.out.printf("%s\t%s\t%d\n",
						optionalRecord.orElseThrow().getSubject().getCode().getCode(),
						optionalRecord.orElseThrow().getSubject().getName().getName(),
						optionalRecord.orElseThrow().getPoint().getPoint()
				);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			DBConnection.cut();
		}
	}
}
