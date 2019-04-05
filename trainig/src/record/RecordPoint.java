package record;

public class RecordPoint {
	private int recordPoint = -1;

	public RecordPoint(int recordPoint) {
		this.recordPoint = recordPoint;
	}

	public int getRecordPoint() {
		return recordPoint;
	}

	public boolean isEmpty() {
		if(recordPoint == -1) {
			return true;
		}
		return false;
	}
}
