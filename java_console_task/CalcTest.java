// Javaコンソール課題1-3
package trainig;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class CalcTest {
	public static void main(String[] args) {
		boolean runFlag = validation(args);
		if(runFlag) {
			calc(args[0], args[1], args[2]);
		}
	}

//calc method
	public static void calc(String inNum1, String inNum2, String operator) {
		double num1 = Double.parseDouble(inNum1);
		double num2 = Double.parseDouble(inNum2);
		double ans = 0.0;
		switch(operator) {
			case "+" : ans = num1 + num2;
			break;
			case "-" : ans = num1 - num2;
			break;
			case "*" : ans = num1 * num2;
			break;
			case "/" : ans = num1 / num2;
			break;
			case "%" : ans = num1 % num2;
			break;
			default: break;
		}
		BigDecimal ansBD = new BigDecimal(ans);
		System.out.println(ansBD.setScale(2,RoundingMode.HALF_UP));
	}
//varidation metho
	public static boolean validation(String[] args) {
		boolean argLengthPass = argLengthChecker(args);
		if(!argLengthPass) {
			return false;
		}
		String inNum1 = args[0];
		String inNum2 = args[1];
		String operator = args[2];
		boolean num1Pass = numChecker(inNum1, 1);
		boolean num2Pass = numChecker(inNum2, 2);
		boolean operatorPass = operatorChecker(operator);
		if(num1Pass && num2Pass && operatorPass) {
			return false;
		}
		return false;
	}
//checker method
	public static boolean argLengthChecker(String[] args) {
		if(args.length > 3) {
			System.out.println("引数が多すぎます。");
			return false;
		}
		if(args.length < 3) {
			System.out.println("引数が足りません。");
			return false;
		}
		return true;
	}
	public static boolean operatorChecker(String operator) {
		if(operator.matches("[+|-|*|/|%]")) {
			return  true;
		}else {
			System.out.println("入力できる記号は、「+」、「-」、「*」、「/」、「%」です。");
			return false;
		}

	}
	public static boolean numChecker(String num, int index) {
		boolean checkResult = false;
		try {
			double d = Double.parseDouble(num);
			checkResult = true;
		}catch(NumberFormatException e) {
			System.out.printf("%d番目の引数は数値で入力してください\n", index);
		}
		return checkResult;
	}
}
