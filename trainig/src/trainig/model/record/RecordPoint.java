package trainig.model.record;

public class RecordPoint {
	private Integer point;

	public RecordPoint(int point) {
		this.point = point;
	}

	public Integer getPoint() {
		return point;
	}

	public boolean isEmpty() {
		if(point == null) {
			return true;
		}
		return false;
	}

	@Override
	public String toString() {
		return String.format("RecordPoint [recordPoint=%s]", point);
	}


}
