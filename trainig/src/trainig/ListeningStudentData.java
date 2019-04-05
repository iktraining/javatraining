package trainig;

import java.util.Scanner;

public class ListeningStudentData {



	public ListeningStudentData() {
		bootupMessage();
	}

	public void bootupMessage() {
		System.out.println("--- 生徒登録システム ---");
		System.out.println("登録する生徒名を入力してください");
	}
	public void ListeningStudentName() {
		System.out.print("生徒名 ==> ");
		Scanner scan = new Scanner(System.in);
		scan.nextLine();
	}
}
