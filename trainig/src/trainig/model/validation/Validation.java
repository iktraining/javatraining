package trainig.model.validation;

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
//正しい実行コマンドかチェック
	public static boolean isExecutionCommand(String executionCommand) {
		if(executionCommand.equals("yes") || executionCommand.equals("no")) {
			return true;
		}
		return false;
	}
}
