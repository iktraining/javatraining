package trainig;

import java.util.Scanner;

import trainig.model.schoolclass.ClassName;
import trainig.model.student.Student;
import trainig.service.ClassInfoService;
import trainig.service.StudentInfoService;

public class StudentList {

	public static void main(String[] args) {
		System.out.println("--- 生徒一覧表示システム ---");

		try {
			DBConnection.connect();

			System.out.println("閲覧したいクラス名を入力してください");
			System.out.print("クラス名 ==> ");
			Scanner scan = new Scanner(System.in);
			ClassName className = new ClassName(scan.nextLine());
			scan.close();

			if(!classCodeValidation(className)) {
				return;
			}
			StudentInfoService studentInfoService = new StudentInfoService();
			trainig.model.student.StudentList studentList = studentInfoService.acquireStudentList(className);
			if(studentList.isEmpty()) {
				System.out.println("このクラスに生徒は在籍していません。");
				return;
			}
			System.out.printf("\nクラス： %s\n", className.getName());
			System.out.printf("生徒番号\t生徒氏名\n");
			for(Student student:studentList.getList()) {
				System.out.printf("%4d\t", student.getNo().getNo());
				System.out.printf("\t%s\n", student.getName().getName());
			}
			System.out.println("\n生徒一覧出力終了");
		}catch(Exception e) {
			System.out.println("エラーが発生しました。");
			System.out.println("プログラムを終了します。");
		}finally {
			try{
				DBConnection.cut();
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
	}
//Validation
	public static boolean classCodeValidation(ClassName className) {
		ClassInfoService classInfoService = new ClassInfoService();
		if(classInfoService.existenceClassCode(className)) {
			return true;
		}
		System.out.printf("%s というクラス名は存在しません\n", className.getName());
		System.out.println("プログラムを終了します。");
		return false;
	}
}
