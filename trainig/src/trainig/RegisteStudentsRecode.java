package trainig;

import java.util.ArrayList;
import java.util.Optional;
import java.util.Scanner;

import trainig.model.record.Record;
import trainig.model.record.RecordPoint;
import trainig.model.record.ReportCard;
import trainig.model.student.StudentNo;
import trainig.model.subject.Subject;
import trainig.model.subject.SubjectList;
import trainig.model.validation.Validation;
import trainig.service.RecordInfoService;
import trainig.service.RecordRegisterService;
import trainig.service.StudentInfoService;
import trainig.service.SubjectInfoService;

public class RegisteStudentsRecode {

	public static void main(String[] args) {
		System.out.println("--- 生徒成績登録システム ---");
		Scanner scan = new Scanner(System.in);
		try {
			DBConnection.connect();
			StudentNo studentNo = new StudentNo(studentNoListener(scan));
			StudentInfoService studentInfoService = new StudentInfoService();
		//生徒存在確認
			if(!studentInfoService.existenceStudent(studentNo)) {
				System.out.printf("%d 番の生徒は存在しません\n", studentNo.getNo());
				System.out.println("プログラムを終了します。");
				return;
			}
		//成績表入力
			 ReportCard reportCard = inputReportCard(scan, studentNo);
		//入力した成績を表示
			inputViewer(reportCard);
		//成績登録の実行判断
			if(execution(scan)) {
				executionRecordRegiter(scan, reportCard);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			DBConnection.cut();
			scan.close();
			System.out.println("生徒成績登録終了");
		}
	}
//生徒番号入力
	public static int studentNoListener(Scanner scan) {
		String	strNo = null;
		System.out.println("登録したい生徒番号を入力してください");
		System.out.print("生徒番号 ==> ");
		do {
			strNo = scan.nextLine();
			if(Validation.numberOfStringType(strNo)) {
				break;
			}
			System.out.print("整数値を入力してください。==> ");
		}while(true);
		return Integer.parseInt(strNo);
	}
//入力内容表示
	public static void inputViewer(ReportCard reportCard) {
		System.out.println("入力内容はこちらでよろしいですか？");
		for(Record record:reportCard.getRecords()) {
			System.out.printf("%s\t", record.getSubject().getName().getName());
			System.out.printf("%d\n", record.getPoint().getPoint());
		}
	}
//実行するかどうかを判断
	public static boolean execution(Scanner scan) {
		String executionCommand = null;
		do {
			System.out.println("yes/noを入力してください ==> ");
			executionCommand = scan.nextLine();
			if(Validation.isExecutionCommand(executionCommand)) {
				break;
			}
		}while(true);
		if(executionCommand.equals("yes")) {
			return true;
		}
		return false;
	}
//成績の入力
	public static ReportCard inputReportCard(Scanner scan, StudentNo studentNo) {
		SubjectInfoService subjectInfoService = new SubjectInfoService();
		SubjectList subjectList = subjectInfoService.acquireSubjectList();

		String strPoint = null;
		ArrayList<Record> recordList = new ArrayList<Record>();

		for(Subject subject: subjectList.getList()) {
			do{
				System.out.printf("%sの成績を入力してください ==> ", subject.getName().getName());
				strPoint = scan.nextLine();
				 if(!Validation.numberOfStringType(strPoint)) {
					 System.out.println("成績を１～１００の整数値で入力してください");
					 continue;
				 }
				 if(!RecordPoint.rangeValidation(Integer.parseInt(strPoint))) {
					 System.out.println("成績を１～１００の整数値で入力してください");
					 continue;
				 }
				 break;
			}while(true);
			recordList.add(
				new Record(
					subject, new RecordPoint(Integer.parseInt(strPoint))
				)
			);
		}
		ReportCard reportCard = new ReportCard(studentNo, recordList);
		return reportCard;
	}
//成績登録の実行
	public static void executionRecordRegiter(Scanner scan, ReportCard reportCard) {
		 {
			for(Record record: reportCard.getRecords()) {
				RecordRegisterService recordRegisterService = new RecordRegisterService();
				//既に登録済みの場合の処理
				RecordInfoService recordInfoService = new RecordInfoService();
				if(recordInfoService.hasRecord(reportCard.getStudentNo(), record.getSubject().getName())) {
					Optional<Record> optionalRegisteredRecord = recordInfoService.acqireRecord(reportCard.getStudentNo(), record.getSubject().getName());
					Record registeredRecord = optionalRegisteredRecord.orElseThrow();
					System.out.printf("既に %s の成績は %d 点で登録済みです。\n",
							registeredRecord.getSubject().getName().getName(),
							registeredRecord.getPoint().getPoint()
					);
					System.out.println("上書きしますか？");
					if(execution(scan)) {
						//上書き実行
						recordRegisterService.updateRegisteredRecord(record, reportCard.getStudentNo());
						System.out.printf("%s の成績を %d 点で上書き登録しました。\n",
							record.getSubject().getName().getName(),
							record.getPoint().getPoint()
						);
					}
					continue;
				}
				recordRegisterService.register(record, reportCard.getStudentNo());
			}
		}
	}
}
