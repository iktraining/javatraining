package trainig;

import java.util.Scanner;

import trainig.model.schoolclass.ClassName;
import trainig.model.student.StudentName;
import trainig.service.ClassInfoService;
import trainig.service.StudentRegisterService;

public class CreateStudents {

	public static void main(String[] args) {
		try {
			DBConnection.connect();

			System.out.println("--- 生徒登録システム ---");
			System.out.println("登録する生徒名, クラス名を入力してください");
			System.out.print("生徒名 ==> ");
			Scanner scan = new Scanner(System.in);
			StudentName studentName = new StudentName(scan.nextLine());
			System.out.print("クラス名 ==> ");
			ClassName className = new ClassName(scan.nextLine());
			scan.close();

			StudentRegisterService studentRegisterService = new StudentRegisterService();
			if(!classCodeValidation(className)) {
				return;
			}

			studentRegisterService.register(studentName, className);
			System.out.println("登録が完了しました。");
		}catch(Exception e) {
			System.out.println("エラーが発生しました。");
			System.out.println("プログラムを終了します。");
		}finally {
			try {
				DBConnection.cut();
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
	}
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
