package model.validation;

public class Validation {
	public Validation() {}

	//数値チェック
		public static boolean numberOfStringType(String number) {
			try{
				Integer.parseInt(number);
				return true;
			}catch(NumberFormatException e) {
				return false;
			}
		}
}
