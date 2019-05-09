package model.schoolclass;

import java.util.ArrayList;

public class SchoolClassList {
	private ArrayList<SchoolClass> schoolClassList;

	public SchoolClassList(ArrayList<SchoolClass> schoolClassList) {
		this.schoolClassList = schoolClassList;
	}

	public ArrayList<SchoolClass> getList(){
		return schoolClassList;
	}

	public boolean isEmpty() {
		if(schoolClassList == null || schoolClassList.isEmpty()){
			return true;
		}
		return false;
	}
}
