package schoolclass;

public class ClassName {
	private String className;

	public ClassName(String className){
		this.className = className;
	}

	public String getClassName(){
		return className;
	}

	public boolean isEmpty() {
		if(className == null || className.isEmpty()) {
			return true;
		}
		return false;
	}
}
