// Javaコンソール課題1-2
package trainig;

import java.text.DecimalFormat;

public class OutputSum {

	public static void main(String[] args) {
		double sum = 0.0;
		try {
			for(String n:args) {
				sum += Double.parseDouble(n);
			}
			DecimalFormat dFormat = new DecimalFormat("0.###");
			System.out.println(String.format("入力された値の合計は %sです。", dFormat.format(sum)));
		}catch(NumberFormatException e) {
			System.out.println("数字で入力してください。");
		}catch(NullPointerException e) {
			System.out.println(e);
		}catch(IllegalArgumentException e) {
			System.out.println(e);
		}
	}
}
