package trainig.model.subject;

import java.util.ArrayList;

public class SubjectList {
	private ArrayList<Subject> subjectList;

	public SubjectList(ArrayList<Subject> subjectList) {
		this.subjectList = subjectList;
	}

	public ArrayList<Subject> getList(){
		return subjectList;
	}

	public boolean isEmpty() {
		if(subjectList == null || subjectList.isEmpty()){
			return true;
		}
		return false;
	}
}
