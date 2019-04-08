package trainig;

import java.util.Scanner;

import trainig.model.schoolclass.ClassName;
import trainig.model.student.StudentName;

public class CreateStudents {
	public static void main(String[] args) {
		System.out.println("--- 生徒登録システム ---");
		System.out.println("登録する生徒名, クラス名を入力してください");
		System.out.print("生徒名 ==> ");
		Scanner scan = new Scanner(System.in);
		StudentName studentName = new StudentName(scan.nextLine());
		System.out.print("クラス名 ==> ");
		ClassName className = new ClassName(scan.nextLine());
		scan.close();


		RegisteStudent regStudent = new RegisteStudent();
		regStudent.registe(studentName, className);
	}
}
