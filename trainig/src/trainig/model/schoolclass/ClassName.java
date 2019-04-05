package trainig.model.schoolclass;

public class ClassName {
	private String name;

	public ClassName(String name){
		this.name = name;
	}

	public String getName(){
		return name;
	}

	public boolean isEmpty() {
		if(name == null || name.isEmpty()) {
			return true;
		}
		return false;
	}

	@Override
	public String toString() {
		return String.format("ClassName [name=%s]", name);
	}

}
