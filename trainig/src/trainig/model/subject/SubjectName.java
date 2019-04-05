package trainig.model.subject;

public class SubjectName {
	private String name;

	public SubjectName(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public boolean isEmpty() {
		if(name == null || name.isEmpty()) {
			return true;
		}
		return false;
	}
}
