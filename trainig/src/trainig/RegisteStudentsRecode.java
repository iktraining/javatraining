package trainig;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

import trainig.model.record.RecordPoint;
import trainig.model.student.StudentNo;
import trainig.model.subject.SubjectCode;

public class RegisteStudentsRecode {
	private static Connection conn;
	private static int no;
	private static String[] subjectNameArr = {"英語","数学","国語","社会","理科"};
	private static String[] subjectCodeArr = {"english","math","japanese","social","science"};

	public static void main(String[] args) {
		int[] points = new int[5];

		System.out.println("--- 生徒成績登録システム ---");
		System.out.println("登録したい生徒番号を入力してください");
		System.out.print("生徒番号 ==> ");
		Scanner scan = new Scanner(System.in);
		String	tmp = scan.nextLine();

		if(!inputNumberCheck(tmp)) {
			System.out.println("整数値を入力してください。");
			System.out.println("プログラムを終了します。");
			scan.close();
			return;
		}
		if(!isConnectDB()) {
			System.out.println("データベース接続に失敗しました。");
			System.out.println("プログラムを終了します。");
			scan.close();
			return;
		}
		if(!existenceStudent(no)) {
			System.out.printf("%d 番の生徒は存在しません\n", no);
			System.out.println("プログラムを終了します。");
			scan.close();
			return;
		}
		StudentNo studentNo = new StudentNo(no);

		for(int i= 0; i < 5; i++) {
			boolean pass = true;
			do{
				if(!pass) {
					System.out.println("成績を１～１００の整数値で入力してください");
				}
				System.out.printf("%sの成績を入力してください ==> ", subjectNameArr[i]);
				tmp = scan.nextLine();
				 if(!inputNumberCheck(tmp)) {
					 pass = false;
					 continue;
				 }
				 pass = recodeRangeCheck(no);
			}while(!pass);
			points[i] = no;
		}

		System.out.println("入力内容はこちらでよろしいですか？");
		displayInputItems(points);
		System.out.println("yes/noを入力してください ==> ");
		tmp = scan.nextLine();
		scan.close();
		if(!isRunningRegiste(tmp)) {
			System.out.println("プログラムを終了します。");
			return;
		}

		//登録開始
		ArrayList<SubjectCode> subjectCodeList = new ArrayList<SubjectCode>();
		ArrayList<RecordPoint> recordPointList = new ArrayList<RecordPoint>();

		for(int i= 0; i < points.length; i++) {
			subjectCodeList.add(new SubjectCode(subjectCodeArr[i]));
			recordPointList.add(new RecordPoint(points[i]));
		}
		RecodeDBRegister recordRegister = new RecodeDBRegister();
		recordRegister.registe(studentNo,subjectCodeList, recordPointList);
		System.out.println("生徒成績登録終了");
	}
//登録実行確認
	public static boolean isRunningRegiste(String tmp) {
		if(tmp.equals("yes")) {
			return true;
		}
		if(tmp.equals("no")) {
			return false;
		}
		return false;
	}
//入力項目一覧表示
	public static void displayInputItems(int[] points) {
		for(int i = 0; i < points.length; i++) {
			System.out.printf("%s\t", subjectNameArr[i]);
			System.out.printf("%d\n", points[i]);
		}
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
//成績範囲チェック
	public static boolean recodeRangeCheck(int recode) {
		if(recode < 1) {
			return false;
		}
		if(recode > 100) {
			return false;
		}
		return true;
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
