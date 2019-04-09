package trainig;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

import trainig.model.student.StudentNo;

public class ModificationRecord {
	public ModificationRecord() {}

	public void modifyRecord(StudentNo studentNo) {
		Scanner scan = new Scanner(System.in);
		//英語修正するか
		System.out.println("英語の評価点を変更しますか？");
		System.out.print("yes/noを入力してください ==> ");
		String jad = scan.nextLine();
		if(UpdateStudents.isModification(jad)) {
			modifyEnglish(studentNo);
		}
		//数学修正するか
		System.out.println("数学の評価点を変更しますか？");
		System.out.print("yes/noを入力してください ==> ");
		jad = scan.nextLine();
		if(UpdateStudents.isModification(jad)) {
			modifyMath(studentNo);
		}
		//国語修正するか
		System.out.println("国語の評価点を変更しますか？");
		System.out.print("yes/noを入力してください ==> ");
		jad = scan.nextLine();
		if(UpdateStudents.isModification(jad)) {
			modifyJapanese(studentNo);
		}
		//社会修正するか
		System.out.println("社会の評価点を変更しますか？");
		System.out.print("yes/noを入力してください ==> ");
		jad = scan.nextLine();
		if(UpdateStudents.isModification(jad)) {
			modifySocial(studentNo);
		}
		//理科修正するか
		System.out.println("理科の評価点を変更しますか？");
		System.out.print("yes/noを入力してください ==> ");
		jad = scan.nextLine();
		if(UpdateStudents.isModification(jad)) {
			modifyScience(studentNo);
		}
		System.out.println("成績修正を終了します。");
	}
//English
	public void modifyEnglish(StudentNo studentNo) {
		System.out.print("英語の新しい成績評価点を入力してください ==> ");
		Scanner scan = new Scanner(System.in);
		String point = null;
		do{
			point = scan.nextLine();
			if(!UpdateStudents.inputNumberCheck(point)) {
				System.out.println("1～100の整数値で入力してください ==> ");
			}
		}while(!UpdateStudents.inputNumberCheck(point));
		System.out.printf("%s で登録してよろしいですか？\n", point);
		System.out.println("yes/noを入力してください ==> ");
		if(!UpdateStudents.isModification(scan.nextLine())) {
			System.out.println("英語の評価点修正を終了します。");
			return;
		}
		//登録実行
		registeModifyRecordPoint(studentNo, "english",point);
		System.out.println("英語の評価点を変更しました。");
	}
//Math
	public void modifyMath(StudentNo studentNo) {
		System.out.print("数学の新しい成績評価点を入力してください ==> ");
		Scanner scan = new Scanner(System.in);
		String point = null;
		do{
			point = scan.nextLine();
			if(!UpdateStudents.inputNumberCheck(point)) {
				System.out.println("1～100の整数値で入力してください ==> ");
			}
		}while(!UpdateStudents.inputNumberCheck(point));
		System.out.printf("%s で登録してよろしいですか？\n", point);
		System.out.println("yes/noを入力してください ==> ");
		if(!UpdateStudents.isModification(scan.nextLine())) {
			System.out.println("数学の評価点修正を終了します。");
			return;
		}
		//登録実行
		registeModifyRecordPoint(studentNo, "math",point);
		System.out.println("数学の評価点を変更しました。");
	}
//Japanese
	public void modifyJapanese(StudentNo studentNo) {
		System.out.print("国語の新しい成績評価点を入力してください ==> ");
		Scanner scan = new Scanner(System.in);
		String point = null;
		do{
			point = scan.nextLine();
			if(!UpdateStudents.inputNumberCheck(point)) {
				System.out.println("1～100の整数値で入力してください ==> ");
			}
		}while(!UpdateStudents.inputNumberCheck(point));
		System.out.printf("%s で登録してよろしいですか？\n", point);
		System.out.println("yes/noを入力してください ==> ");
		if(!UpdateStudents.isModification(scan.nextLine())) {
			System.out.println("国語の評価点修正を終了します。");
			return;
		}
		//登録実行
		registeModifyRecordPoint(studentNo, "japanese",point);
		System.out.println("国語の評価点を変更しました。");
	}
//Social
	public void modifySocial(StudentNo studentNo) {
		System.out.print("社会の新しい成績評価点を入力してください ==> ");
		Scanner scan = new Scanner(System.in);
		String point = null;
		do{
			point = scan.nextLine();
			if(!UpdateStudents.inputNumberCheck(point)) {
				System.out.println("1～100の整数値で入力してください ==> ");
			}
		}while(!UpdateStudents.inputNumberCheck(point));
		System.out.printf("%s で登録してよろしいですか？\n", point);
		System.out.println("yes/noを入力してください ==> ");
		if(!UpdateStudents.isModification(scan.nextLine())) {
			System.out.println("社会の評価点修正を終了します。");
			return;
		}
		//登録実行
		registeModifyRecordPoint(studentNo, "social",point);
		System.out.println("社会の評価点を変更しました。");
	}
//Science
	public void modifyScience(StudentNo studentNo) {
		System.out.print("理科の新しい成績評価点を入力してください ==> ");
		Scanner scan = new Scanner(System.in);
		String point = null;
		do{
			point = scan.nextLine();
			if(!UpdateStudents.inputNumberCheck(point)) {
				System.out.println("1～100の整数値で入力してください ==> ");
			}
		}while(!UpdateStudents.inputNumberCheck(point));
		System.out.printf("%s で登録してよろしいですか？\n", point);
		System.out.println("yes/noを入力してください ==> ");
		if(!UpdateStudents.isModification(scan.nextLine())) {
			System.out.println("理科の評価点修正を終了します。");
			return;
		}
		//登録実行
		registeModifyRecordPoint(studentNo, "science",point);
		System.out.println("理科の評価点を変更しました。");
	}
//変更実行
	public void registeModifyRecordPoint(StudentNo studentNo, String subjectName, String point) {
		Connection conn = DBConnection.getConnection();
		PreparedStatement pstmt = null;
		try {
			String sql = "update records set record_point = ? where student_no = ? and subject_code = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, Integer.parseInt(point));
			pstmt.setInt(2, studentNo.getNo());
			pstmt.setString(3, subjectName);
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
