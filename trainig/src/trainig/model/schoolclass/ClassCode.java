package trainig.model.schoolclass;

public class ClassCode {
	private String code;

	public ClassCode(String code){
		this.code = code;
	}

	public String getCode(){
		return code;
	}

	public boolean isEmpty() {
		if(code == null || code.isEmpty()) {
			return true;
		}
		return false;
	}

	@Override
	public String toString() {
		return String.format("ClassCode [code=%s]", code);
	}

}
