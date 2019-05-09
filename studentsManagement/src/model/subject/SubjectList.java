package model.subject;

import java.util.ArrayList;

import model.subject.Subject;

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
