//Java DB1-1
package trainig;

import java.util.Scanner;

import trainig.model.schoolclass.ClassName;
import trainig.model.student.StudentName;

public class StudentRegister {
	private StudentName studentName;
	private ClassName className;

	public StudentRegister() {
		bootupMessage();
		studentNameListener();
		classNameListener();
	}

	public void bootupMessage() {
		System.out.println("--- 生徒登録システム ---");
		System.out.println("登録する生徒名, クラス名を入力してください");
	}
	public void studentNameListener() {
		System.out.print("生徒名 ==> ");
		Scanner scan = new Scanner(System.in);
		String name = scan.nextLine();
		studentName = new StudentName(name);
	}
	public void classNameListener() {
		System.out.print("クラス名 ==> ");
		Scanner scan = new Scanner(System.in);
		className = new ClassName(scan.nextLine());
		scan.close();
	}
	public StudentName getStudentName() {
		return studentName;
	}
	public ClassName getClassName() {
		return className;
	}
}
