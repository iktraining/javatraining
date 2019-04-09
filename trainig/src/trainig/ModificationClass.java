package trainig;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

import trainig.model.student.StudentNo;

public class ModificationClass {
	public ModificationClass() {}

	public void modifyClass(StudentNo studentNo) {
		//修正後のクラス
		System.out.print("新しいクラスを入力してください ==> ");
		Scanner scan = new Scanner(System.in);
		String modifiedClass = scan.nextLine();
		//これでよいか？
		System.out.printf("%s で登録してよろしいですか？\n", modifiedClass);
		System.out.println("yes/noを入力してください ==> ");
		if(!UpdateStudents.isModification(scan.nextLine())) {
			System.out.println("クラス修正を終了します。");
			return;
		}
		if(!existenceClass(modifiedClass)) {
			System.out.println("入力されたクラスは存在しません。");
			System.out.println("クラス修正を終了します。");
			return;
		}
		//登録実行
		registeModifyClass(studentNo, modifiedClass);
		System.out.println("クラスを変更しました。");
	}
	public void registeModifyClass(StudentNo studentNo, String modifiedClass) {
		Connection conn = DBConnection.getConnection();
		PreparedStatement pstmt = null;
		try {
			String sql = "update students set class_code = ? where student_no = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, modifiedClass);
			pstmt.setInt(2, studentNo.getNo());
			int num = pstmt.executeUpdate();
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				if(pstmt != null)pstmt.close();
			}catch(SQLException e) {
				e.printStackTrace();
			}
		}
	}
//クラスコード存在確認チェック
		public static boolean existenceClass(String modifiedClass) {
			Connection conn = DBConnection.getConnection();
			try {
				String sql = "select class_code from classes where class_name = ?";
				PreparedStatement pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, modifiedClass);
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
}
