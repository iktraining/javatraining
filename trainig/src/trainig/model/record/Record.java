package trainig.model.record;

import trainig.model.subject.Subject;

public class Record {
	private Subject subject;
	private RecordPoint point;

	public Record(Subject subject, RecordPoint point) {
		this.subject = subject;
		this.point = point;
	}
	public boolean hasSubject() {
		if(subject == null) {
			return false;
		}
		return true;
	}
	public boolean hasPoint() {
		if(point == null) {
			return false;
		}
		if(point.isEmpty()) {
			return false;
		}
		return true;
	}
}
