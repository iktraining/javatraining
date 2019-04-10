package trainig;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import trainig.model.record.Record;
import trainig.model.student.StudentNo;

public class UpdateStudents {
	private static Connection conn = null;
	private static int no = 0;

	public static void main(String[] args) {
		System.out.println("--- 生徒情報修正システム ---");
		System.out.println("情報修正したい生徒番号を入力してください");
		System.out.print("生徒番号 ==> ");

		if(!inputNumberCheck(inputLine())) {
			System.out.println("整数値を入力してください。");
			System.out.println("プログラムを終了します。");
			return;
		}
		if(!isConnectDB()) {
			System.out.println("データベース接続に失敗しました。");
			System.out.println("プログラムを終了します。");
			return;
		}
		if(!existenceStudent(no)) {
			System.out.printf("%d 番の生徒は存在しません\n", no);
			System.out.println("プログラムを終了します。");
			return;
		}
		StudentNo studentNo = new StudentNo(no);
	//名前修正
		requestModifyStudentName(studentNo);
	//クラス修正
		requestModifyClassName(studentNo);
	//成績修正
		requestModifyRecord(studentNo);
		DBConnection.cut();
		System.out.println("生徒情報修正システム終了");
	}
//入力メソッド
	public static String inputLine() {
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		String line = null;
		try {
			line = reader.readLine();
		}catch(IOException e) {
			e.printStackTrace();
		}
		return line;
	}
//名前修正メソッド
	public static void requestModifyStudentName(StudentNo studentNo) {
		AcquireStudentName acquireStudentName = new AcquireStudentName();
		acquireStudentName.acquireName(studentNo);
		System.out.printf("登録されている氏名は %s です。修正しますか？\n", acquireStudentName.getStudentName().getName());
		System.out.println("yes/noを入力してください ==> ");
		if(isModification(inputLine())) {
			ModificationStudentName modificationStudentName = new ModificationStudentName();
			modificationStudentName.modifyName(studentNo);
		}
	}
//クラス修正メソッド
	public static void requestModifyClassName(StudentNo studentNo) {
		AcquireClassName acquireClassName = new AcquireClassName();
		acquireClassName.acquireName(studentNo);
		System.out.printf("登録されているクラスは %s です。修正しますか？\n", acquireClassName.getClassName().getName());
		System.out.println("yes/noを入力してください ==> ");
		if(isModification(inputLine())) {
			ModificationClass modificationClass = new ModificationClass();
			modificationClass.modifyClass(studentNo);
		}
	}
//成績修正メソッド
	public static void requestModifyRecord(StudentNo studentNo) {
		AcquireRecord acquireRecode = new AcquireRecord();
		acquireRecode.acquireRecord(studentNo);
		System.out.println("登録されている成績は以下の値です。修正しますか？");
		ArrayList<Record> recordList = acquireRecode.getRecordList();
		for(int i = 0; i < recordList.size(); i++) {
			System.out.printf("%s\t", recordList.get(i).getSubject().getName().getName());
			System.out.printf("%d\n", recordList.get(i).getRecordPoint().getPoint());
		}
		System.out.println("yes/noを入力してください ==> ");
		if(isModification(inputLine())) {
			ModificationRecord modificationRecord = new ModificationRecord();
			modificationRecord.modifyRecord(studentNo);
		}
	}
//修正実行確認
	public static boolean isModification(String tmp) {
		if(tmp.equals("yes")) {
			return true;
		}
		if(tmp.equals("no")) {
			return false;
		}
		return false;
	}
//数値チェック
	public static boolean inputNumberCheck(String number) {
		try{
			no = Integer.parseInt(number);
			return true;
		}catch(NumberFormatException e) {
			return false;
		}
	}
//在籍確認
	public static boolean existenceStudent(int no) {
		try {
			String sql = "select student_no from students where student_no = ?";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, no);
			ResultSet rset = pstmt.executeQuery();
			if(rset.next()){
				return true;
			}
			if(rset != null)rset.close();
			return false;
		}catch(SQLException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			DBConnection.cut();
			return false;
		}
	}
//DB接続
	public static boolean isConnectDB() {
		if(DBConnection.connect()) {//DB接続確立
			conn = DBConnection.getConnection();
			return true;
		}
		return false;
	}
}
