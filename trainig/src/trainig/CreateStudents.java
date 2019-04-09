package trainig;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

import trainig.model.schoolclass.ClassName;
import trainig.model.student.StudentName;

public class CreateStudents {
	private static Connection conn;
	private static DBConnection db;

	public static void main(String[] args) {
		System.out.println("--- 生徒登録システム ---");
		System.out.println("登録する生徒名, クラス名を入力してください");
		System.out.print("生徒名 ==> ");
		Scanner scan = new Scanner(System.in);
		StudentName studentName = new StudentName(scan.nextLine());
		System.out.print("クラス名 ==> ");
		ClassName className = new ClassName(scan.nextLine());
		scan.close();

		if(!isConnectDB()) {
			System.out.println("データベース接続に失敗しました。");
			System.out.println("プログラムを終了します。");
			return;
		}
		if(!existenceClass(className)) {
			System.out.printf("%s というクラス名は存在しません\n", className.getName());
			System.out.println("プログラムを終了します。");
			return;
		}
		RegisteStudent registeStudent = new RegisteStudent();
		if(registeStudent.registe(studentName, className)) {
			System.out.println("登録が完了しました。");
		}else {
			System.out.println("エラーが発生しました。");
			System.out.println("プログラムを終了します。");
		}
	}
//クラスコード存在確認チェック
	public static boolean existenceClass(ClassName className) {
		try {
			String sql = "select class_code from classes where class_name = ?";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, className.getName());
			ResultSet rset = pstmt.executeQuery();
			if(rset.next()){
				return true;
			}
			if(rset != null)rset.close();
			return false;
		}catch(SQLException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			db.cut();
			return false;
		}
	}
//DB接続
	public static boolean isConnectDB() {
		if(db.connect()) {//DB接続確立
			conn = db.getConnection();
			return true;
		}
		return false;
	}

}
