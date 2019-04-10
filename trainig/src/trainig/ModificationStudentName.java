package trainig;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

import trainig.model.student.StudentNo;

public class ModificationStudentName {

	public ModificationStudentName() {}

	public void modifyName(StudentNo studentNo) {
		//修正後の名前入力
		System.out.print("新しい氏名を入力してください ==> ");
		Scanner scan = new Scanner(System.in);
		String modifiedName = scan.nextLine();
		//これでよいか？
		System.out.printf("%s で登録してよろしいですか？\n", modifiedName);
		System.out.println("yes/noを入力してください ==> ");
		if(!UpdateStudents.isModification(scan.nextLine())) {
			System.out.println("氏名修正を終了します。");
			return;
		}
		//登録実行
		registeModifyName(studentNo, modifiedName);
		System.out.println("氏名を変更しました。");
	}
	public void registeModifyName(StudentNo studentNo, String modifiedName) {
		Connection conn = DBConnection.getConnection();
		PreparedStatement pstmt = null;
		try {
			String sql = "update students set student_name = ? where student_no = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, modifiedName);
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
}
