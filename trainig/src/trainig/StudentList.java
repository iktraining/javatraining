package trainig;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

import trainig.model.schoolclass.ClassName;

public class StudentList {
		private static Connection conn;
		private static DBConnection db;

		public static void main(String[] args) {
			System.out.println("--- 生徒一覧表示システム ---");
			System.out.println("閲覧したいクラス名を入力してください");
			System.out.print("クラス名 ==> ");
			Scanner scan = new Scanner(System.in);
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


			AcquireStudent acquireStudent = new AcquireStudent();
			acquireStudent.acquire(className);
			ArrayList<StudentListModel> studentList = acquireStudent.getStudentList();
			if(studentList.isEmpty()) {
				System.out.println("このクラスに生徒は存在しません。");
				return;
			}
			System.out.printf("\nクラス： %s\n", className.getName());
			System.out.printf("生徒番号\t生氏名\n");
			for(int i = 0; i<studentList.size(); i++) {
				System.out.printf("%4d\t", studentList.get(i).getStudentNo().getNo());
				System.out.printf("\t%s\n", studentList.get(i).getStudentName().getName());
			}
			System.out.println("生徒一覧出力終了");
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
